/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass8.server.exceptions;

/**
 *
 * @author pomo
 */
public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException(int id) {
        super("Could not find course with id " + id);
    }
    
}
