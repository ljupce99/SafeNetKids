package finki.ukim.mk.safenetkids.service.domain.impl;

import finki.ukim.mk.safenetkids.models.ScoreSession;
import finki.ukim.mk.safenetkids.repository.ScoreSessionRepository;
import finki.ukim.mk.safenetkids.service.domain.ScoreSessionDomainService;
import finki.ukim.mk.safenetkids.web.dto.ScoreSessionCreateDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScoreSessionDomainServiceImpl implements ScoreSessionDomainService {

    private final ScoreSessionRepository scoreSessionRepository;

    public ScoreSessionDomainServiceImpl(ScoreSessionRepository scoreSessionRepository) {
        this.scoreSessionRepository = scoreSessionRepository;
    }

    @Override
    public List<ScoreSession> findAll() {
        return this.scoreSessionRepository.findAll();
    }

    @Override
    public Optional<ScoreSession> findById(Long id) {
        return this.scoreSessionRepository.findById(id);
    }

    @Override
    public ScoreSession create(ScoreSessionCreateDto createDto) {
        ScoreSession scoreSession = new ScoreSession();
        scoreSession.setSessionId(createDto.sessionId());
        scoreSession.setPoints(createDto.points());
        scoreSession.setFinished(createDto.finished());
        scoreSession.setCreatedAt(LocalDateTime.now());

        return this.scoreSessionRepository.save(scoreSession);
    }

    @Override
    public ScoreSession addPoints(String sessionId, int pointsToAdd) {
        ScoreSession scoreSession = this.scoreSessionRepository.findBySessionId(sessionId)
                .orElseGet(() -> {
                    ScoreSession newSession = new ScoreSession();
                    newSession.setSessionId(sessionId);
                    newSession.setPoints(0);
                    newSession.setFinished(false);
                    newSession.setCreatedAt(LocalDateTime.now());
                    return newSession;
                });

        scoreSession.setPoints(scoreSession.getPoints() + pointsToAdd);
        return this.scoreSessionRepository.save(scoreSession);
    }
}

