package com.practice.quiz.feign;

import com.practice.quiz.model.QuestionResponse;
import com.practice.quiz.model.QuestionWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "QUESTION-SERVICE")
public interface QuestionClient {

    @GetMapping("/question/generateQuiz/{category}/{numOfQuestions}")
    ResponseEntity<List<Long>> generateQuestionsForQuiz(@PathVariable String category, @PathVariable int numOfQuestions);

    @PostMapping("/question/getQuestions")
    ResponseEntity<List<QuestionWrapper>> getQuestionsOfQuiz(@RequestBody List<Long> questions);
    @PostMapping("/question/scores")
    ResponseEntity<Integer> getScore(@RequestBody List<QuestionResponse> response);
}
