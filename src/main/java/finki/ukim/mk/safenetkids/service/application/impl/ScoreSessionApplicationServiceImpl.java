package finki.ukim.mk.safenetkids.service.application.impl;

import finki.ukim.mk.safenetkids.models.ScoreSession;
import finki.ukim.mk.safenetkids.service.application.ScoreSessionApplicationService;
import finki.ukim.mk.safenetkids.service.domain.ScoreSessionDomainService;
import finki.ukim.mk.safenetkids.web.dto.ScoreSessionCreateDto;
import finki.ukim.mk.safenetkids.web.dto.ScoreSessionDisplayDto;
import finki.ukim.mk.safenetkids.web.dto.ScoreSessionPointsDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreSessionApplicationServiceImpl implements ScoreSessionApplicationService {

    private final ScoreSessionDomainService scoreSessionDomainService;

    public ScoreSessionApplicationServiceImpl(ScoreSessionDomainService scoreSessionDomainService) {
        this.scoreSessionDomainService = scoreSessionDomainService;
    }

    @Override
    public List<ScoreSessionDisplayDto> findAll() {
        return this.scoreSessionDomainService.findAll()
                .stream()
                .map(this::mapToDisplayDto)
                .toList();
    }

    @Override
    public Optional<ScoreSessionDisplayDto> findById(Long id) {
        return this.scoreSessionDomainService.findById(id)
                .map(this::mapToDisplayDto);
    }

    @Override
    public ScoreSessionDisplayDto create(ScoreSessionCreateDto createDto) {
        ScoreSession createdScoreSession = this.scoreSessionDomainService.create(createDto);
        return mapToDisplayDto(createdScoreSession);
    }

    @Override
    public ScoreSessionDisplayDto addPoints(ScoreSessionPointsDto pointsDto) {
        ScoreSession scoreSession = this.scoreSessionDomainService.addPoints(pointsDto.sessionId(), pointsDto.pointsToAdd());
        return mapToDisplayDto(scoreSession);
    }

    private ScoreSessionDisplayDto mapToDisplayDto(ScoreSession scoreSession) {
        return new ScoreSessionDisplayDto(
                scoreSession.getId(),
                scoreSession.getSessionId(),
                scoreSession.getPoints(),
                scoreSession.isFinished(),
                scoreSession.getCreatedAt()
        );
    }
}

