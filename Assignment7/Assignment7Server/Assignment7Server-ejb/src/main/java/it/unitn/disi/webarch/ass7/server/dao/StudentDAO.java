/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass7.server.dao;

import it.unitn.disi.webarch.ass7.server.entities.Student;
import java.util.ArrayList;
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
public class StudentDAO {
    private SessionFactory factory;
    
    public StudentDAO() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }
    
    public Student addStudent(String name, String surname, String matricola) {
        Session session = this.factory.openSession();
        Transaction tx = null;
        Student student = null;
        try {
            tx = session.beginTransaction();
            student = new Student(name, surname, matricola);
            Integer profID = (Integer) session.save(student); 
            tx.commit();
            student.setId(profID);
        } catch (HibernateException | NullPointerException e) {
            if (tx!=null) tx.rollback();
        } finally {
            session.close(); 
        }
        return student;
    }
    
    public Student getById(int id) { 
        Session session = this.factory.openSession();
        Transaction tx = null;
        Student student = null;
        try{
            tx = session.beginTransaction();
            String query = "FROM Student S WHERE S.id = " + id;
            List students = session.createQuery(query).list();
            for (Iterator i = students.iterator(); i.hasNext();) {
               student = (Student) i.next();
            }
            tx.commit();
        } catch(HibernateException | NullPointerException e) {
            if (tx!=null) tx.rollback();
        } finally {
            session.close();
        }
        return student;
    }
    
    public ArrayList<Student> getAll() { 
        Session session = this.factory.openSession();
        Transaction tx = null;
        Student student = null;
        ArrayList<Student> all = new ArrayList<>();
        try{
            tx = session.beginTransaction();
            String query = "FROM Student S";
            List students = session.createQuery(query).list();
            for (Iterator i = students.iterator(); i.hasNext();) {
               student = (Student) i.next();
               all.add(student);
            }
            tx.commit();
        } catch(HibernateException | NullPointerException e) {
            if (tx!=null) tx.rollback();
        } finally {
            session.close();
        }
        return all;
    }
}
