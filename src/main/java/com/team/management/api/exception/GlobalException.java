package com.team.management.api.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public String globalException(Exception ex, Model model) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(ex.getMessage());
        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setOccurTime(LocalDateTime.now());
        model.addAttribute("error", errorObject);
        return "error";
    }
}
