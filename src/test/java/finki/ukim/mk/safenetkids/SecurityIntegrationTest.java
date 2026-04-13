package finki.ukim.mk.safenetkids;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecurityIntegrationTest {

    @Value("${local.server.port}")
    private int port;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Test
    void apiShouldRequireAuthentication() throws Exception {
        HttpResponse<String> response = this.httpClient.send(request("/api/score-sessions").build(), HttpResponse.BodyHandlers.ofString());
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.statusCode());
    }

    @Test
    void authenticatedApiShouldBeAccessible() throws Exception {
        HttpResponse<String> response = this.httpClient.send(
                request("/api/score-sessions")
                        .header("Authorization", basicAuth())
                        .build(),
                HttpResponse.BodyHandlers.ofString()
        );
        assertEquals(HttpStatus.OK.value(), response.statusCode());
    }

    @Test
    void swaggerDocsShouldBePublic() throws Exception {
        HttpResponse<String> response = this.httpClient.send(request("/v3/api-docs").build(), HttpResponse.BodyHandlers.ofString());
        assertEquals(HttpStatus.OK.value(), response.statusCode());
    }

    private HttpRequest.Builder request(String path) {
        return HttpRequest.newBuilder(URI.create("http://localhost:" + this.port + path))
                .GET();
    }

    private String basicAuth() {
        String token = "admin:admin123";
        return "Basic " + Base64.getEncoder().encodeToString(token.getBytes(StandardCharsets.UTF_8));
    }
}





