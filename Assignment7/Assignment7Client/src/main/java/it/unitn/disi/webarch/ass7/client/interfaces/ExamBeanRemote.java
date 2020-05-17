/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass7.client.interfaces;

import javax.ejb.Remote;

/**
 *
 * @author pomo
 */
@Remote
public interface ExamBeanRemote {
    public String addExam(int courseId, String date);
    public String getAllExams();
    public boolean enrollToExam(int examId, int studentId);
    public boolean addMark(int examId, int studentId, String mark);
    public String getEnrolledStudents(int examId);
    public String getMarksForExam(int examId);
}
