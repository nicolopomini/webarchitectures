/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass7.server.dao;

import it.unitn.disi.webarch.ass7.server.entities.Course;
import it.unitn.disi.webarch.ass7.server.entities.Enrolled;
import it.unitn.disi.webarch.ass7.server.entities.Exam;
import it.unitn.disi.webarch.ass7.server.entities.Student;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author pomo
 */
public class ExamDAO {
    private SessionFactory factory;
    
    public ExamDAO() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }
    public Exam addExam(int courseId, String date) {
        Session session = this.factory.openSession();
        Transaction tx = null;
        Exam exam = null;
        Course course = null;
        try{
            tx = session.beginTransaction();
            course = (Course) session.get(Course.class, courseId);
            exam = new Exam(date);
            Collection<Exam> exams = course.getExams();
            exams.add(exam);
            course.setExams(exams);
            exam.setCourse(course);
            session.save(exam);
            tx.commit();       
        } catch(HibernateException | NullPointerException e) {
            if (tx!=null) tx.rollback();
        } finally {
            session.close();
        }
        return exam;
    }
    public ArrayList<Exam> getAll() { 
        Session session = this.factory.openSession();
        Transaction tx = null;
        Exam exam = null;
        ArrayList<Exam> all = new ArrayList<>();
        try{
            tx = session.beginTransaction();
            String query = "FROM Exam E";
            List exams = session.createQuery(query).list();
            for (Iterator i = exams.iterator(); i.hasNext();) {
               exam = (Exam) i.next();
               all.add(exam);
            }
            tx.commit();
        } catch(HibernateException | NullPointerException e) {
            if (tx!=null) tx.rollback();
        } finally {
            session.close();
        }
        return all;
    }
    public boolean enrollToExam(int examId, int studentId) {
        Session session = this.factory.openSession();
        Transaction tx = null;
        boolean result = false;
        try {
            tx = session.beginTransaction();
            Exam exam = (Exam) session.get(Exam.class, examId);
            Student student = (Student) session.get(Student.class, studentId);
            Enrolled enrollment = new Enrolled(student, exam);
            //exam.getEnrolledStudents().add(enrollment);
            student.getEnrolledExams().add(enrollment);
            session.update(student);
            tx.commit();
            result = true;
        } catch(HibernateException | NullPointerException e) {
            if (tx!=null) tx.rollback();
        } finally {
            session.close();
        }
        return result;
    }
    public boolean addMark(int examId, int studentId, String mark) {
        Session session = this.factory.openSession();
        Transaction tx = null;
        boolean result = false;
        try{
            tx = session.beginTransaction();
            Exam exam = (Exam) session.get(Exam.class, examId);
            Set<Enrolled> enrolled = exam.getEnrolledStudents();
            for(Enrolled e: enrolled) {
                if(e.getStudent().getId() == studentId)
                    e.setMark(mark);
            }
            exam.setEnrolledStudents(enrolled);
            session.update(exam);
            tx.commit();
            result = true;
        }catch(HibernateException | NullPointerException e) {
            if (tx!=null) tx.rollback();
        } finally {
            session.close();
        }
        return result;
    }
    public Collection<Student> getEnrolledStudents(int examId) {
        Session session = this.factory.openSession();
        Collection<Student> students = new ArrayList<>();
        try{
            Exam exam = (Exam) session.get(Exam.class, examId);
            Set<Enrolled> enrolled = exam.getEnrolledStudents();
            for(Enrolled e: enrolled) {
                students.add(e.getStudent());
            }
        }catch(HibernateException | NullPointerException e) {
        } finally {
            session.close();
        }
        return students;
    }
    public ArrayList<String> getMarksForExam(int examId) {
        Session session = this.factory.openSession();
        ArrayList<String> marks = new ArrayList<>();
        try{
            Exam exam = (Exam) session.get(Exam.class, examId);
            Set<Enrolled> enrolled = exam.getEnrolledStudents();
            for(Enrolled e: enrolled) {
                if(e.getExam() == null)
                    marks.add("No mark registered for student" + e.getStudent().getId());
                else
                    marks.add("Mark for student" + e.getStudent().getId() + ": " + e.getMark());
            }
        }catch(HibernateException | NullPointerException e) {
        } finally {
            session.close();
        }
        return marks;
    }
}
