/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass7.server.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author pomo
 */
@Entity(name="Student")
@Table(name="student")
public class Student extends Person implements Serializable{
    @Column(name="matricola", nullable=false, unique=true)
    private String matricola;
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "students")
    private Collection<Course> courses;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.student", cascade=CascadeType.ALL)
    private Set<Enrolled> enrolledExams;

    public Set<Enrolled> getEnrolledExams() {
        return enrolledExams;
    }

    public void setEnrolledExams(Set<Enrolled> enrolledExams) {
        this.enrolledExams = enrolledExams;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }
    
    public Student() {
        super();
    }
    
    public Student(String name, String surname, String matricola) {
        super();
        super.name = name;
        super.surname = surname;
        this.matricola = matricola;
        this.courses = new ArrayList<>();
        this.enrolledExams = new HashSet<>();
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    @Override
    public String toString() {
        return "Student(id: " + super.id + ", name: " + super.name + 
                ", surname: " + super.surname + ", matricola: " + 
                this.matricola + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
}
