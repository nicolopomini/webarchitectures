/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass7.server.beans;

import it.unitn.disi.webarch.ass7.client.interfaces.ExamBeanRemote;
import it.unitn.disi.webarch.ass7.server.dao.ExamDAO;
import it.unitn.disi.webarch.ass7.server.entities.Exam;
import it.unitn.disi.webarch.ass7.server.entities.Student;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;

/**
 *
 * @author pomo
 */
@Stateless
public class ExamBean implements ExamBeanRemote {

    @Override
    public String addExam(int courseId, String date) {
        ExamDAO dao = new ExamDAO();
        return dao.addExam(courseId, date).toString();
    }

    @Override
    public String getAllExams() {
        ExamDAO dao = new ExamDAO();
        ArrayList<Exam> all = dao.getAll();
        String rtr = "All exams:\n";
        for(Exam c: all) {
            rtr += c.toString() + "\n";
        }
        return rtr;
    }

    @Override
    public boolean enrollToExam(int examId, int studentId) {
        ExamDAO dao = new ExamDAO();
        return dao.enrollToExam(examId, studentId);
    }

    @Override
    public boolean addMark(int examId, int studentId, String mark) {
        ExamDAO dao = new ExamDAO();
        return dao.addMark(examId, studentId, mark);
    }

    @Override
    public String getEnrolledStudents(int examId) {
        ExamDAO dao = new ExamDAO();
        Collection<Student> s = dao.getEnrolledStudents(examId);
        if(s == null || s.isEmpty())
            return "No students are enrolled in exam " + examId;
        else {
            String rtr = "Students enrolled into exam " + examId + ":\n";
            for(Student c: s) {
                rtr += c.toString() + "\n";
            }
            return rtr;
        }
    }

    @Override
    public String getMarksForExam(int examId) {
        ExamDAO dao = new ExamDAO();
        ArrayList<String> marks = dao.getMarksForExam(examId);
        String rtr = "Marks for exam " + examId + ":\n";
        for(String s: marks)
            rtr += s;
        return rtr;
    }
    
    

}
