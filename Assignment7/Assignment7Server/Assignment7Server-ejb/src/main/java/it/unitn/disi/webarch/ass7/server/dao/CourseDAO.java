/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass7.server.dao;

import it.unitn.disi.webarch.ass7.server.entities.Course;
import it.unitn.disi.webarch.ass7.server.entities.Professor;
import it.unitn.disi.webarch.ass7.server.entities.Student;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author pomo
 */
public class CourseDAO {
    private SessionFactory factory;
    
    public CourseDAO() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }
    
    public Course addCourse(int professorId, String courseName) {
        Session session = this.factory.openSession();
        Transaction tx = null;
        Professor professor = null;
        Course course = null;
        try{
            tx = session.beginTransaction();
            course = new Course(courseName);
            professor = (Professor) session.get(Professor.class, professorId); 
            course.setProfessor(professor);
            professor.setCourse(course);
            session.update(professor);
            tx.commit();       
        } catch(HibernateException | NullPointerException e) {
            if (tx!=null) tx.rollback();
        } finally {
            session.close();
        }
        return course;
    }
    public ArrayList<Course> getAll() { 
        Session session = this.factory.openSession();
        Transaction tx = null;
        Course course = null;
        ArrayList<Course> all = new ArrayList<>();
        try{
            tx = session.beginTransaction();
            String query = "FROM Course C";
            List courses = session.createQuery(query).list();
            for (Iterator i = courses.iterator(); i.hasNext();) {
               course = (Course) i.next();
               all.add(course);
            }
            tx.commit();
        } catch(HibernateException | NullPointerException e) {
            if (tx!=null) tx.rollback();
        } finally {
            session.close();
        }
        return all;
    }
    public boolean enrollStudentinCourse(int courseId, int studentId) {
        Session session = this.factory.openSession();
        Transaction tx = null;
        boolean result = false;
        try{
            tx = session.beginTransaction();
            Course course = (Course) session.get(Course.class, courseId);
            Student student = (Student) session.get(Student.class, studentId);
            course.getStudents().add(student);
            session.update(course);
            tx.commit();
            result = true;
        }catch(HibernateException | NullPointerException e) {
            if (tx!=null) tx.rollback();
        } finally {
            session.close();
        }
        return result;
    }
    public Collection<Student> getEnrolledStudents(int courseId) {
        Session session = this.factory.openSession();
        Collection<Student> students = null;
        try{
            Course course = (Course) session.get(Course.class, courseId);
            students = course.getStudents();
        }catch(HibernateException | NullPointerException e) {} 
        finally {
            session.close();
        }
        return students;
    }
}
