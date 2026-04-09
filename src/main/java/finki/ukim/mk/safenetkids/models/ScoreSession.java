package finki.ukim.mk.safenetkids.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class ScoreSession {

    @Id
    @GeneratedValue
    private Long id;

    private String sessionId;

    private int points;

    private boolean finished;

    private LocalDateTime createdAt;

}