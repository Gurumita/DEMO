package com.Quiz.Quiz.Repository;

import com.Quiz.Quiz.Models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Long questionId);
    Answer findByQuestionIdAndIsCorrectTrue(Long questionId);
}
