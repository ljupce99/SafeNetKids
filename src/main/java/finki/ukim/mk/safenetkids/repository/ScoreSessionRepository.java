package finki.ukim.mk.safenetkids.repository;

import finki.ukim.mk.safenetkids.models.ScoreSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoreSessionRepository extends JpaRepository<ScoreSession, Long> {
	Optional<ScoreSession> findBySessionId(String sessionId);
}

