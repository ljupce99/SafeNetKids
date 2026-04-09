package finki.ukim.mk.safenetkids.service.domain;

import finki.ukim.mk.safenetkids.models.ScoreSession;
import finki.ukim.mk.safenetkids.web.dto.ScoreSessionCreateDto;

import java.util.List;
import java.util.Optional;

public interface ScoreSessionDomainService {
    List<ScoreSession> findAll();

    Optional<ScoreSession> findById(Long id);

    ScoreSession create(ScoreSessionCreateDto createDto);

    ScoreSession addPoints(String sessionId, int pointsToAdd);
}

