package finki.ukim.mk.safenetkids.web;

import finki.ukim.mk.safenetkids.service.application.ScoreSessionApplicationService;
import finki.ukim.mk.safenetkids.web.dto.ScoreSessionDisplayDto;
import finki.ukim.mk.safenetkids.web.dto.ScoreSessionPointsDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/score-sessions")
public class ScoreSessionController {

    private final ScoreSessionApplicationService scoreSessionApplicationService;

    public ScoreSessionController(ScoreSessionApplicationService scoreSessionApplicationService) {
        this.scoreSessionApplicationService = scoreSessionApplicationService;
    }

    @GetMapping
    public List<ScoreSessionDisplayDto> findAll() {
        return this.scoreSessionApplicationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScoreSessionDisplayDto> findById(@PathVariable Long id) {
        return this.scoreSessionApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/points")
    public ResponseEntity<ScoreSessionDisplayDto> addPoints(@RequestBody ScoreSessionPointsDto pointsDto,
                                                            HttpSession session) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.scoreSessionApplicationService.addPoints(session.getId(), pointsDto));
    }
}

