package com.practice.question.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
public class QuestionWrapper {

    private long questionId;
    private String questionText;
    private List<String> options;

    public QuestionWrapper(long questionId, String questionText, List<String> options) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.options = options;
    }
}
