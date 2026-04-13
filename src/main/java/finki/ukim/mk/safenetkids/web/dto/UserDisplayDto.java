package finki.ukim.mk.safenetkids.web.dto;

import finki.ukim.mk.safenetkids.models.Role;

public record UserDisplayDto(
        Long id,
        String username,
        Role role,
        String town,
        String school
) {
}

