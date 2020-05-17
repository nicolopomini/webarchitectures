/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass7.server.dao;

import it.unitn.disi.webarch.ass7.server.entities.Professor;
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
public class ProfessorDAO {
    private SessionFactory factory;
    
    public ProfessorDAO() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }
    
    public Professor getById(int id) { 
        Session session = this.factory.openSession();
        Transaction tx = null;
        Professor professor = null;
        try{
            tx = session.beginTransaction();
            String query = "FROM Professor P WHERE P.id = " + id;
            List professors = session.createQuery(query).list();
            for (Iterator i = professors.iterator(); i.hasNext();) {
               professor = (Professor) i.next();
            }
            tx.commit();
        } catch(HibernateException | NullPointerException e) {
            if (tx!=null) tx.rollback();
        } finally {
            session.close();
        }
        return professor;
    }
    
    public Professor addProfessor(String name, String surname) {
        Session session = this.factory.openSession();
        Transaction tx = null;
        Professor professor = null;
        try {
            tx = session.beginTransaction();
            professor = new Professor(name, surname);
            Integer profID = (Integer) session.save(professor); 
            tx.commit();
            professor.setId(profID);
        } catch (HibernateException | NullPointerException e) {
            if (tx!=null) tx.rollback();
        } finally {
            session.close(); 
        }
        return professor;
    }
    
    public ArrayList<Professor> getAll() { 
        Session session = this.factory.openSession();
        Transaction tx = null;
        Professor professor = null;
        ArrayList<Professor> all = new ArrayList<>();
        try{
            tx = session.beginTransaction();
            String query = "FROM Professor P";
            List professors = session.createQuery(query).list();
            for (Iterator i = professors.iterator(); i.hasNext();) {
               professor = (Professor) i.next();
               all.add(professor);
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
