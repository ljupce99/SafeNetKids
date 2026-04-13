package finki.ukim.mk.safenetkids.web.dto;

public record UserRegistrationDto(
        String username,
        String password,
        String role,
        String town,
        String school
) {
}

