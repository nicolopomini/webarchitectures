/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass8.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author pomo
 */
@ControllerAdvice
public class Advice {
    @ResponseBody
    @ExceptionHandler(ProfessorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String professorNotFoundHandler(ProfessorNotFoundException ex) {
            return ex.getMessage();
    }
    
    @ResponseBody
    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String studentNotFoundHandler(StudentNotFoundException ex) {
            return ex.getMessage();
    }
    
    @ResponseBody
    @ExceptionHandler(CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String courseNotFoundHandler(CourseNotFoundException ex) {
            return ex.getMessage();
    }
    
    @ResponseBody
    @ExceptionHandler(ExamNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String examNotFoundHandler(ExamNotFoundException ex) {
            return ex.getMessage();
    }
    
    @ResponseBody
    @ExceptionHandler(EnrolledNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String enrollNotFoundHandler(EnrolledNotFoundException ex) {
            return ex.getMessage();
    }
}
