package finki.ukim.mk.safenetkids.service.application;

import finki.ukim.mk.safenetkids.web.dto.ScoreSessionCreateDto;
import finki.ukim.mk.safenetkids.web.dto.ScoreSessionDisplayDto;
import finki.ukim.mk.safenetkids.web.dto.ScoreSessionPointsDto;

import java.util.List;
import java.util.Optional;

public interface ScoreSessionApplicationService {
    List<ScoreSessionDisplayDto> findAll();

    Optional<ScoreSessionDisplayDto> findById(Long id);

    ScoreSessionDisplayDto create(ScoreSessionCreateDto createDto);

    ScoreSessionDisplayDto addPoints(String sessionId, ScoreSessionPointsDto pointsDto);
}

