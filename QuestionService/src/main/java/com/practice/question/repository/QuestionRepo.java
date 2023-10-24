package com.practice.question.repository;


import com.practice.question.model.Category;
import com.practice.question.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question,Long> {

    List<Question> findByCategory(Category category);

    @Query("SELECT q.questionId FROM Question q WHERE q.category = :category ORDER BY RAND() LIMIT :noOfQuestions")
    List<Long> findRandomQuestionsByCategory(Category category, int noOfQuestions);
}
