package com.practice.question.controller;

import com.practice.question.model.Category;
import com.practice.question.model.Question;
import com.practice.question.model.QuestionResponse;
import com.practice.question.model.QuestionWrapper;
import com.practice.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionService service;

    @PostMapping
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        service.addQuestion(question);
        return new ResponseEntity<>("Question Added", HttpStatus.CREATED);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable long questionId) {
        return new ResponseEntity<>(service.getQuestion(questionId),HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAll() {
        return new ResponseEntity<>(service.getAll(),HttpStatus.FOUND);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getByCategory(@PathVariable String category) {
        return new ResponseEntity<>(service.getByCategory(Category.valueOf(category.toUpperCase())),HttpStatus.FOUND);
    }

    @GetMapping("/generateQuiz/{category}/{numOfQuestions}")
    public ResponseEntity<List<Long>> generateQuestionsForQuiz(@PathVariable String category, @PathVariable int numOfQuestions) {
        return new ResponseEntity<>(service.generateQuestions(category,numOfQuestions),HttpStatus.OK);
    }


    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsOfQuiz(@RequestBody List<Long> questions) {
        return new ResponseEntity<>(service.getQuestionsOfQuiz(questions),HttpStatus.OK);
    }

    @PostMapping("/scores")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuestionResponse> response) {
        return new ResponseEntity<>(service.getScores(response),HttpStatus.OK);
    }
    @PutMapping("/{questionId}")
    public ResponseEntity<String> updateQuestion(@PathVariable long questionId, @RequestBody Question question) {
        service.updateQuestion(questionId,question);
        return new ResponseEntity<>("Question Updated",HttpStatus.OK);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable long questionId) {
        service.deleteQuestion(questionId);
        return new ResponseEntity<>("Question Deleted", HttpStatus.OK);
    }

}
