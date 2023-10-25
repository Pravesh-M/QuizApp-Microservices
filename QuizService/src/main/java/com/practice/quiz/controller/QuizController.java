package com.practice.quiz.controller;


import com.practice.quiz.model.QuestionResponse;
import com.practice.quiz.model.QuestionWrapper;
import com.practice.quiz.model.QuizDto;
import com.practice.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService service;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto dto) {
        long quizId = service.createQuiz(dto);
        return new ResponseEntity<>("Quiz : "+dto.getQuizName()+" Created with Quiz Id : "+quizId, HttpStatus.CREATED);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable long quizId) {
        return new ResponseEntity<>(service.getQuiz(quizId), HttpStatus.FOUND);
    }

    @PostMapping("/submit/{quizId}")
    public ResponseEntity<String> submitQuiz(@PathVariable long quizId, @RequestBody List<QuestionResponse> response) {
        int marks = service.getMarks(quizId,response);
        return new ResponseEntity<>("Total Marks for the Quiz are : "+marks,HttpStatus.OK);
    }
}
