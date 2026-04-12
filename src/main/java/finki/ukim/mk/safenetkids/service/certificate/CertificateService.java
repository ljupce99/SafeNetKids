package finki.ukim.mk.safenetkids.service.certificate;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class CertificateService {

    public byte[] generateCertificate(String name, int points) {
        try {
            // 1. Load template
            String html = new String(
                    Files.readAllBytes(Paths.get("C:\\Users\\ljupc\\Downloads\\wp-2024-main\\SafeNetKids\\src\\main\\resources\\static\\certificate_template.html"))
            );

            // 2. Replace variables
            html = html.replace("${name}", name);
            html = html.replace("${points}", String.valueOf(points));

            // 3. Convert to PDF
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, null);
            builder.toStream(outputStream);
            builder.run();

            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generating certificate", e);
        }
    }
}