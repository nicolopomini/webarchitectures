/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass8.server.controllers;

import it.unitn.disi.webarch.ass8.server.entities.Professor;
import it.unitn.disi.webarch.ass8.server.exceptions.ProfessorNotFoundException;
import it.unitn.disi.webarch.ass8.server.repositories.ProfessorRepository;
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
@RequestMapping(path="/professor")
public class ProfessorController {
    @Autowired
    private ProfessorRepository repository;
    
    @CrossOrigin
    @GetMapping(path="")
    public @ResponseBody Iterable<Professor> getAllProfessors() {
            return repository.findAll();
    }
    
    @CrossOrigin
    @GetMapping(path="/{id}")
    public @ResponseBody Professor  getProfessor(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ProfessorNotFoundException(id)
        );
    }
    
    @CrossOrigin
    @PostMapping(path="")
    public @ResponseBody Professor add(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        Professor p = new Professor(name, surname);
        return repository.save(p);
    }
}
