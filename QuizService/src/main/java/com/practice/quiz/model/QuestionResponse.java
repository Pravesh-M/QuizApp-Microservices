package com.practice.quiz.model;

import lombok.Data;

@Data
public class QuestionResponse {

    private long questionId;
    private String selectedOption;
}
