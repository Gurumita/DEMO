package com.Quiz.Quiz.Controller;

import com.Quiz.Quiz.Models.Answer;

import com.Quiz.Quiz.Services.AnswerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;
    @PostMapping
    public ResponseEntity<Answer> createAnswer(@RequestBody Answer answer) {
        Answer createdAnswer = answerService.createAnswer(answer);
        return ResponseEntity.ok(createdAnswer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long id, @RequestBody Answer updatedAnswer) {
        Answer answer = answerService.updateAnswer(id, updatedAnswer);
        if (answer != null) {
            return ResponseEntity.ok(answer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable Long id) {
        Answer answer = answerService.getAnswerById(id);
        if (answer != null) {
            return ResponseEntity.ok(answer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Answer>> getAllAnswers() {
        List<Answer> answers = answerService.getAllAnswers();
        return ResponseEntity.ok(answers);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/byQuestion/{questionId}")
    public ResponseEntity<List<Answer>> getAnswersByQuestionId(@PathVariable Long questionId) {
        List<Answer> answers = answerService.getAnswersByQuestionId(questionId);
        return ResponseEntity.ok(answers);
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitAnswer(HttpSession session, @RequestBody Answer submittedAnswer) {
        boolean isCorrect = validateAnswer(submittedAnswer);

        if (!isCorrect) {

            Integer wrongAnswerCount = (Integer) session.getAttribute("wrongAnswerCount");
            if (wrongAnswerCount == null) {
                wrongAnswerCount = 0;
            }
            session.setAttribute("wrongAnswerCount", wrongAnswerCount + 1);
        }

        return ResponseEntity.ok(isCorrect ? "Correct answer" : "Wrong answer");
    }

    private boolean validateAnswer(Answer submittedAnswer) {
        if (submittedAnswer.getQuestion() == null) {
            return false;
        }

        Answer correctAnswer = answerService.getCorrectAnswerByQuestionId(submittedAnswer.getQuestion().getId());
        return correctAnswer != null && correctAnswer.getAnswerText().equals(submittedAnswer.getAnswerText());
    }


}
