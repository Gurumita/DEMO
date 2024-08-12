package com.Quiz.Quiz.Services;


import com.Quiz.Quiz.Models.Answer;
import com.Quiz.Quiz.Repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public Answer createAnswer(Answer answer) {
        if (answer.getQuestion() == null) {
            throw new IllegalArgumentException("Answer must be associated with a valid question");
        }
        return answerRepository.save(answer);
    }


    public Answer updateAnswer(Long id, Answer updatedAnswer) {
        Optional<Answer> existingAnswer = answerRepository.findById(id);
        if (existingAnswer.isPresent()) {
            Answer answer = existingAnswer.get();
            answer.setAnswerText(updatedAnswer.getAnswerText());
            answer.setCorrect(updatedAnswer.isCorrect());
            answer.setQuestion(updatedAnswer.getQuestion());
            return answerRepository.save(answer);
        }
        return null;
    }

    // Get an Answer by ID
    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id).orElse(null);
    }
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    public List<Answer> getAnswersByQuestionId(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }
    public Answer getCorrectAnswerByQuestionId(Long questionId) {
        return answerRepository.findByQuestionIdAndIsCorrectTrue(questionId);
    }
}
