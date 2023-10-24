package com.practice.question.service;

import com.practice.question.model.Category;
import com.practice.question.model.Question;
import com.practice.question.model.QuestionResponse;
import com.practice.question.model.QuestionWrapper;
import com.practice.question.repository.QuestionRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo repo;

    public void addQuestion(Question question) {
        repo.save(question);
    }

    public Question getQuestion(long questionId) {
        return repo.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question With QuestionId : "+questionId+" Not Found"));
    }
    public List<Question> getAll() {
        return repo.findAll();
    }

    public List<Question> getByCategory(Category category) {
        return repo.findByCategory(category);
    }
    public List<Long> generateQuestions(String category, int numOfQuestions) {
        return repo.findRandomQuestionsByCategory(Category.valueOf(category.toUpperCase()),numOfQuestions);
    }

    public List<QuestionWrapper> getQuestionsOfQuiz(List<Long> questions) {

        List<QuestionWrapper> questionsOfQuiz = new ArrayList<>();

        for(long id : questions) {
            Question q = getQuestion(id);
            QuestionWrapper questionWrapper = new QuestionWrapper
                    (q.getQuestionId(),q.getQuestionText(),q.getOptions());
            questionsOfQuiz.add(questionWrapper);
        }
        return questionsOfQuiz;
    }
    public int getScores(List<QuestionResponse> response) {
        int score=0;
        for(QuestionResponse res : response) {
            Question question = getQuestion(res.getQuestionId());
            if(question.getCorrectAnswer().equals(res.getSelectedOption())) {
                score++;
            }
        }
        return score;
    }

    public void updateQuestion(long questionId, Question question) {
        Question dbQuestion = getQuestion(questionId);
        dbQuestion.setQuestionText(question.getQuestionText());
        dbQuestion.setOptions(question.getOptions());
        dbQuestion.setCategory(question.getCategory());
        dbQuestion.setLevel(question.getLevel());
        dbQuestion.setCorrectAnswer(question.getCorrectAnswer());

        repo.save(dbQuestion);
    }
    public void deleteQuestion(long questionId) {
        repo.deleteById(questionId);
    }

}
