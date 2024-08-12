package com.Quiz.Quiz.Controller;

import com.Quiz.Quiz.Models.Score;
import com.Quiz.Quiz.Models.User;
import com.Quiz.Quiz.Services.ScoreService;
import com.Quiz.Quiz.Services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private UserService userService;

    @GetMapping("/topThree")
    public List<Score> getTopThreeScores() {
        return scoreService.getTopThreeScores();
    }
}

//    @PostMapping("/save")
//    public ResponseEntity<Score> saveScore(@RequestBody Score score, HttpServletResponse response) {
//        User user = userService.findById(score.getUser().getId());
//        if (user != null) {
//            score.setUser(user);
//            Score savedScore = scoreService.saveScore(score);
//            Cookie scoreCookie = new Cookie("ScoreID", savedScore.getId().toString());
//            scoreCookie.setPath("/");
//            scoreCookie.setMaxAge(3600); // 1 hour
//            response.addCookie(scoreCookie);
//
//            return ResponseEntity.ok(savedScore);
//        } else {
//            return ResponseEntity.status(404).body(null);
//        }


