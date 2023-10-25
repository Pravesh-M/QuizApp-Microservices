package com.practice.quiz.model;

import lombok.Data;

@Data
public class QuizDto {

    private String quizName;
    private String category;
    private int noOfQuestions;

}
