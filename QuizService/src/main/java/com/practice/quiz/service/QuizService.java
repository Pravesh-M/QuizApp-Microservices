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

        Quiz quiz = new Quiz();
        quiz.setQuizName(dto.getQuizName());
        quiz.setQuestions(questions);
        //Saving Quiz and returning Quiz ID
        return quizRepo.save(quiz).getQuizId();
    }

    public List<QuestionWrapper> getQuiz(long quizId) {
        //Checking Quiz is Present with quizId
        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz With QuizId : "+quizId+" Not Found"));

        return client.getQuestionsOfQuiz(quiz.getQuestions()).getBody();
    }

    public int getMarks(long quizId, List<QuestionResponse> response) {
        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz With QuizId : "+quizId+" Not Found"));
        return client.getScore(response).getBody();
    }
}
