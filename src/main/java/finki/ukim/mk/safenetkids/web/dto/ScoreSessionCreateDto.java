package finki.ukim.mk.safenetkids.web.dto;

public record ScoreSessionCreateDto(
        String sessionId,
        int points,
        boolean finished
) {
}

