package com.Quiz.Quiz.Controller;

import com.Quiz.Quiz.Models.Score;
import com.Quiz.Quiz.Services.ScoreService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/cookies")
public class CookieController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping("/setScoreCookie")
    public ResponseEntity<String> createScoreCookie(@RequestParam String scoreId, HttpServletResponse response) {
        Cookie scoreCookie = new Cookie("ScoreID", scoreId);
        scoreCookie.setPath("/");
        scoreCookie.setMaxAge(3600);
        response.addCookie(scoreCookie);

        return new ResponseEntity<>("Score cookie set", HttpStatus.OK);
    }

    @PostMapping("/updateScore")
    public ResponseEntity<String> updateScore(@RequestBody Score score, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            boolean isScoreCookiePresent = Arrays.stream(cookies)
                    .anyMatch(cookie -> "ScoreID".equals(cookie.getName()) && score.getId().equals(cookie.getValue()));

            if (isScoreCookiePresent) {
                scoreService.saveScore(score);
                return new ResponseEntity<>("Database updated", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Score cookie not found or invalid", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("No cookies found", HttpStatus.UNAUTHORIZED);
        }
    }
}
