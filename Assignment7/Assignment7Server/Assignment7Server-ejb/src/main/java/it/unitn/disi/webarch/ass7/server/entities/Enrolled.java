/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass7.server.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author pomo
 */
@Entity(name="Enrolled")
@Table(name="enrolled")
@AssociationOverrides({
		@AssociationOverride(name = "id.student", 
			joinColumns = @JoinColumn(name = "student")),
		@AssociationOverride(name = "id.exam", 
			joinColumns = @JoinColumn(name = "exam")) })
public class Enrolled implements Serializable{
    private EnrolledId id;
    
    private String mark;
    
    public Enrolled() {}
    public Enrolled(Student student, Exam exam) {
        this.id = new EnrolledId(student, exam);
    }

    @EmbeddedId
    public EnrolledId getId() {
        return id;
    }

    public void setId(EnrolledId id) {
        this.id = id;
    }

    @Transient
    public Student getStudent() {
        return this.id.getStudent();
    }

    public void setStudent(Student student) {
        this.id.setStudent(student);
    }

    @Transient
    public Exam getExam() {
        return this.id.getExam();
    }

    public void setExam(Exam exam) {
        this.id.setExam(exam);
    }

    @Column(name="mark")
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
                return true;
        if (o == null || getClass() != o.getClass())
                return false;
        Enrolled obj = (Enrolled) o;
        return this.id.equals(obj.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
    
    
}
