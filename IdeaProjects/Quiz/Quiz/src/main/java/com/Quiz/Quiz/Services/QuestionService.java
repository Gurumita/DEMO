package com.Quiz.Quiz.Services;


import com.Quiz.Quiz.Models.Question;
import com.Quiz.Quiz.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    public Question getRandomQuestion() {
        List<Question> allQuestions = questionRepository.findAll();
        Random random = new Random();
        return allQuestions.get(random.nextInt(allQuestions.size()));
    }
}
