/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass8.server.controllers;

import it.unitn.disi.webarch.ass8.server.entities.Student;
import it.unitn.disi.webarch.ass8.server.exceptions.StudentNotFoundException;
import it.unitn.disi.webarch.ass8.server.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author pomo
 */
@Controller
@RequestMapping(path="/student")
public class StudentController {
    @Autowired
    private StudentRepository repository;
    
    @CrossOrigin
    @GetMapping(path="")
    public @ResponseBody Iterable<Student> getAllStudents() {
            return repository.findAll();
    }
    
    @CrossOrigin
    @GetMapping(path="/{id}")
    public @ResponseBody Student  getStudent(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new StudentNotFoundException(id)
        );
    }
    
    @CrossOrigin
    @PostMapping(path="")
    public @ResponseBody Student add(@RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("matricola") String matricola) {
        Student s = new Student(name, surname, matricola);
        return repository.save(s);
    }
}
