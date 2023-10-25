package com.practice.quiz.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ExceptionResponse {

    private String message;
    private String path;

}
