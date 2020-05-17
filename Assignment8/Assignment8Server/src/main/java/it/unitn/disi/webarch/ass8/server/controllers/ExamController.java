/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass8.server.controllers;

import it.unitn.disi.webarch.ass8.server.entities.Course;
import it.unitn.disi.webarch.ass8.server.entities.Enrolled;
import it.unitn.disi.webarch.ass8.server.entities.EnrolledId;
import it.unitn.disi.webarch.ass8.server.entities.Exam;
import it.unitn.disi.webarch.ass8.server.entities.Student;
import it.unitn.disi.webarch.ass8.server.exceptions.CourseNotFoundException;
import it.unitn.disi.webarch.ass8.server.exceptions.EnrolledNotFoundException;
import it.unitn.disi.webarch.ass8.server.exceptions.ExamNotFoundException;
import it.unitn.disi.webarch.ass8.server.exceptions.StudentNotFoundException;
import it.unitn.disi.webarch.ass8.server.repositories.CourseRepository;
import it.unitn.disi.webarch.ass8.server.repositories.EnrolledRepository;
import it.unitn.disi.webarch.ass8.server.repositories.ExamRepository;
import it.unitn.disi.webarch.ass8.server.repositories.StudentRepository;
import java.util.HashSet;
import java.util.Set;
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
@RequestMapping(path="/exam")
public class ExamController {
    @Autowired
    private ExamRepository repository;
    
    @Autowired
    private CourseRepository courseRepo;
    
    @Autowired
    private StudentRepository studentRepo;
    
    @Autowired
    private EnrolledRepository enrolledRepo;
    
    @CrossOrigin
    @GetMapping(path="")
    public @ResponseBody Iterable<Exam> getAllExams() {
            return repository.findAll();
    }
    
    @CrossOrigin
    @GetMapping(path="/{id}")
    public @ResponseBody Exam  getExam(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ExamNotFoundException(id)
        );
    }
    
    @CrossOrigin
    @PostMapping(path="")
    public @ResponseBody Exam add(@RequestParam("courseid") int courseid, @RequestParam("date") String date) {
        Course c = courseRepo.findById(courseid).orElseThrow(
                () -> new CourseNotFoundException(courseid)
        );
        Exam e = new Exam(date);
        e.setCourse(c);
        c.getExams().add(e);
        courseRepo.save(c);
        return repository.save(e);
    }
    
    @CrossOrigin
    @PostMapping(path="/{examid}/enroll/{studentid}")
    public @ResponseBody Enrolled enrollStudent(@PathVariable("examid") int examId, @PathVariable("studentid") int studentId) {
        Exam e = repository.findById(examId).orElseThrow(
                () -> new ExamNotFoundException(examId)
        );
        Student s = studentRepo.findById(studentId).orElseThrow(
                () -> new StudentNotFoundException(studentId)
        );
        Enrolled enrollment = new Enrolled(s, e);
        s.getEnrolledExams().add(enrollment);
        e.getEnrolledStudents().add(enrollment);
        studentRepo.save(s);
        repository.save(e);
        return enrolledRepo.save(enrollment);
    }
    
    @CrossOrigin
    @GetMapping(path="/{examid}/enrolled")
    public @ResponseBody Set<Enrolled> getEnrolledStudents(@PathVariable("examid") int examId) {
        Exam e = repository.findById(examId).orElseThrow(
                () -> new ExamNotFoundException(examId)
        );
        HashSet<Enrolled> all = new HashSet<>();
        for(Enrolled en: enrolledRepo.findAll()) {
            if(en.getExam().equals(e))
                all.add(en);
        }
        return all;
    }
    
    @CrossOrigin
    @PostMapping(path="/{examid}/enrolled/{studentid}")
    public @ResponseBody Enrolled addMark(@PathVariable("examid") int examId, @PathVariable("studentid") int studentId, @RequestParam("mark") String mark) {
        Exam e = repository.findById(examId).orElseThrow(
                () -> new ExamNotFoundException(examId)
        );
        Student s = studentRepo.findById(studentId).orElseThrow(
                () -> new StudentNotFoundException(studentId)
        );
        EnrolledId id = new EnrolledId(s, e);
        Enrolled en = enrolledRepo.findById(id).orElseThrow(
                () -> new EnrolledNotFoundException(examId, studentId)
        );
        en.setMark(mark);
        return enrolledRepo.save(en);
    }
    
    @CrossOrigin
    @GetMapping(path="/{examid}/enrolled/{studentid}")
    public @ResponseBody Enrolled getMark(@PathVariable("examid") int examId, @PathVariable("studentid") int studentId) {
        Exam e = repository.findById(examId).orElseThrow(
                () -> new ExamNotFoundException(examId)
        );
        Student s = studentRepo.findById(studentId).orElseThrow(
                () -> new StudentNotFoundException(studentId)
        );
        EnrolledId id = new EnrolledId(s, e);
        return enrolledRepo.findById(id).orElseThrow(
                () -> new EnrolledNotFoundException(examId, studentId)
        );
    }

}
