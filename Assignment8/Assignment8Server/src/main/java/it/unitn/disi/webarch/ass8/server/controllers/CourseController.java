/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass8.server.controllers;

import it.unitn.disi.webarch.ass8.server.entities.Course;
import it.unitn.disi.webarch.ass8.server.entities.Professor;
import it.unitn.disi.webarch.ass8.server.entities.Student;
import it.unitn.disi.webarch.ass8.server.exceptions.CourseNotFoundException;
import it.unitn.disi.webarch.ass8.server.exceptions.ProfessorNotFoundException;
import it.unitn.disi.webarch.ass8.server.exceptions.StudentNotFoundException;
import it.unitn.disi.webarch.ass8.server.repositories.CourseRepository;
import it.unitn.disi.webarch.ass8.server.repositories.ProfessorRepository;
import it.unitn.disi.webarch.ass8.server.repositories.StudentRepository;
import java.util.Collection;
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
@RequestMapping(path="/course")
public class CourseController {
    @Autowired
    private CourseRepository repository;
    
    @Autowired
    private ProfessorRepository profRepository;
    
    @Autowired
    private StudentRepository studRepository;
    
    @CrossOrigin
    @GetMapping(path="")
    public @ResponseBody Iterable<Course> getAllCourses() {
            return repository.findAll();
    }
    
    @CrossOrigin
    @GetMapping(path="/{id}")
    public @ResponseBody Course  getCourse(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new CourseNotFoundException(id)
        );
    }
    
    @CrossOrigin
    @PostMapping(path="")
    public @ResponseBody Course add(@RequestParam("name") String name, @RequestParam("professor") int professor) {
        Professor p = profRepository.findById(professor).orElseThrow(
                () -> new ProfessorNotFoundException(professor)
        );
        Course c = repository.save(new Course(name));
        p.setCourse(c);
        profRepository.save(p);
        return c;
    }
    
    @CrossOrigin
    @PostMapping(path="/{courseid}/enroll/{studentid}")
    public @ResponseBody Course enrollStudent(@PathVariable("courseid") int courseid, @PathVariable("studentid") int studentid) {
        Student s = studRepository.findById(studentid).orElseThrow(
                () -> new StudentNotFoundException(studentid)
        );
        Course c = repository.findById(courseid).orElseThrow(
                () -> new CourseNotFoundException(courseid)
        );
        Collection<Course> studentCourses = s.getCourses();
        studentCourses.add(c);
        s.setCourses(studentCourses);
        
        Collection<Student> courseStudents = c.getStudents();
        courseStudents.add(s);
        c.setStudents(courseStudents);
        
        studRepository.save(s);
        return repository.save(c);   
    }
    
    @CrossOrigin
    @GetMapping(path="/{courseid}/enrolled")
    public @ResponseBody Collection<Student> enrolledStudents(@PathVariable int courseid) {
        Course c = repository.findById(courseid).orElseThrow(
                () -> new CourseNotFoundException(courseid)
        );
        return c.getStudents();
    }
}
