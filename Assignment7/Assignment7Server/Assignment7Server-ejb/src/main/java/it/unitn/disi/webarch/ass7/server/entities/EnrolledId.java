/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass7.server.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author pomo
 */
@Embeddable
public class EnrolledId implements Serializable{
    private Student student; 
    
    private Exam exam;
    
    public EnrolledId(Student student, Exam exam) {
        this.student = student;
        this.exam = exam;
    }
    public EnrolledId() {}

    @ManyToOne
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) 
            return false;
        if (this == o) return true;
        EnrolledId obj = (EnrolledId)o;
        return this.student == obj.student && this.exam == obj.exam;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.student);
        hash = 97 * hash + Objects.hashCode(this.exam);
        return hash;
    }

    
}
