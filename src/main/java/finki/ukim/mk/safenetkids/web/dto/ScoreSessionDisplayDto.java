package finki.ukim.mk.safenetkids.web.dto;

import java.time.LocalDateTime;

public record ScoreSessionDisplayDto(
        Long id,
        String sessionId,
        int points,
        boolean finished,
        LocalDateTime createdAt
) {
}

