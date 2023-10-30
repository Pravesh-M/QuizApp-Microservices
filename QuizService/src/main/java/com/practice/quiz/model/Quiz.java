package com.practice.quiz.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long quizId;
    private String quizName;

    @ElementCollection
    private List<Long> questions;

    public Quiz(String quizName, List<Long> questions) {
        this.quizName = quizName;
        this.questions = questions;
    }
}
