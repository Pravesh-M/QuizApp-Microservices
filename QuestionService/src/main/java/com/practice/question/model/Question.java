package com.practice.question.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;
    private String questionText;
    private List<String> options;
    private String correctAnswer;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String level;
}
