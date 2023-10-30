package com.practice.quiz.service;

import com.practice.quiz.feign.QuestionClient;
import com.practice.quiz.model.*;
import com.practice.quiz.repository.QuizRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    QuestionClient client;


    public long createQuiz(QuizDto dto) {

        List<Long> questions = client.generateQuestionsForQuiz(dto.getCategory(),dto.getNoOfQuestions()).getBody();

        Quiz quiz = new Quiz(dto.getQuizName(),questions);
        //Saving Quiz and returning Quiz ID
        return quizRepo.save(quiz).getQuizId();
    }

    public List<QuestionWrapper> getQuiz(long quizId) {
        //Checking Quiz is Present with quizId
        Quiz quiz = getQuizById(quizId);
        return client.getQuestionsOfQuiz(quiz.getQuestions()).getBody();
    }

    public int getMarks(long quizId, List<QuestionResponse> response) {
        Quiz quiz = getQuizById(quizId);
        return client.getScore(response).getBody();
    }
    public Quiz getQuizById(long  quizId) {
        return quizRepo.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz with quizID : "+quizId+" is not Present"));
    }
}
