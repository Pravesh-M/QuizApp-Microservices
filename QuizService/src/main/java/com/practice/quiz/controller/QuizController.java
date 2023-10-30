package com.practice.quiz.controller;


import com.practice.quiz.model.QuestionResponse;
import com.practice.quiz.model.QuestionWrapper;
import com.practice.quiz.model.QuizDto;
import com.practice.quiz.service.QuizService;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService service;

    @PostMapping("/create")
    @CircuitBreaker(name = "questionBreaker",fallbackMethod = "createQuizFallBack")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto dto) {
        long quizId = service.createQuiz(dto);
        return new ResponseEntity<>("Quiz : "+dto.getQuizName()+" Created with Quiz Id : "+quizId, HttpStatus.CREATED);
    }
    public ResponseEntity<String> createQuizFallBack(@RequestBody QuizDto dto,FeignException.ServiceUnavailable ex) {
        return new ResponseEntity<>("Question Service is Down", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/{quizId}")
    @CircuitBreaker(name = "getQuizBreaker",fallbackMethod = "getQuizFallBack")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable long quizId) {
        return new ResponseEntity<>(service.getQuiz(quizId), HttpStatus.FOUND);
    }
    public ResponseEntity<List<QuestionWrapper>> getQuizFallBack(@PathVariable long quizId, FeignException.ServiceUnavailable ex) {
        QuestionWrapper wrapper = new QuestionWrapper(0,"Dummy Data because Question-Service is Down",new ArrayList<>());
        List<QuestionWrapper> dummyData = new ArrayList<>();
        dummyData.add(wrapper);
        return new ResponseEntity<>(dummyData, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @PostMapping("/submit/{quizId}")
    @CircuitBreaker(name ="submitQuizBreaker",fallbackMethod = "submitQuizFallback")
    public ResponseEntity<String> submitQuiz(@PathVariable long quizId, @RequestBody List<QuestionResponse> response) {
        int marks = service.getMarks(quizId,response);
        return new ResponseEntity<>("Total Marks for Quiz with Id "+quizId+" are : "+marks,HttpStatus.OK);
    }
    public ResponseEntity<String> submitQuizFallback(@PathVariable long quizId, @RequestBody List<QuestionResponse> response,FeignException.ServiceUnavailable ex) {
        return new ResponseEntity<>("Question Service is Down",HttpStatus.SERVICE_UNAVAILABLE);
    }
}
