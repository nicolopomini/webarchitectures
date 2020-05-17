/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass6.dbmanager;

import it.unitn.disi.webarch.ass6.models.Book;
import it.unitn.disi.webarch.ass6.models.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author pomo
 */
public class DBManager {
    private SessionFactory factory;

    public DBManager(SessionFactory factory) {
            this.factory = factory;
    }
    /**
     * Create and insert a new Writer
     * @param name the name of the new writer
     * @param surname the surname of the new writer
     * @param books the set of books written by the new writer
     * @return the new Writer
     */
    public Writer addWriter(String name, String surname, Set books){
        Session session = this.factory.openSession();
        Transaction tx = null;
        Writer writer = null;
      
        try {
            tx = session.beginTransaction();
            writer = new Writer(name, surname);
            writer.setBooks(books);
            Integer writerID = (Integer) session.save(writer); 
            tx.commit();
            writer.setId(writerID);
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return writer;
    }
    /**
     * Get all writers (with all the books they have written)
     * @return an ArrayList containing all writers
     */
    public ArrayList<Writer> getAllWriters() {
        Session session = this.factory.openSession();
        Transaction tx = null;
        ArrayList<Writer> all = new ArrayList<>();
        try{
            tx = session.beginTransaction();
            List writers = session.createQuery("FROM Writer").list();
            for (Iterator i = writers.iterator(); i.hasNext();) {
                Writer w = (Writer) i.next();
                all.add(w);
            }
            tx.commit();
        } catch(HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return all;
    }
    /**
     * Get all the books written by a given author after a given year
     * @param writer the author of the books
     * @param year the year threshold. Every book written after the parameter will be returned
     * @return the list of all books published by the author with a publicationYear > year
     */
    public ArrayList<Book> getWrittenAfter(Writer writer, int year) {
        Session session = this.factory.openSession();
        Transaction tx = null;
        ArrayList<Book> writtenAfter = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            String query = "FROM Writer W WHERE W.id = " + writer.getId();
            List w= session.createQuery(query).list();
            for (Iterator writerIterator = w.iterator(); writerIterator.hasNext();) {
                Writer wr = (Writer) writerIterator.next();
                Set books = wr.getBooks();
                for(Iterator bookIter = books.iterator(); bookIter.hasNext();) {
                    Book b = (Book)bookIter.next();
                    if(b.getPublicationYear() > year)
                        writtenAfter.add(b);
                }
            }
        } catch(HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return writtenAfter;
    }
    /**
     * Get a single Writer
     * @param id the id of the Writer
     * @return the requested Writer, or null if it does not exist
     */
    public Writer getWriter(int id) {
        Session session = this.factory.openSession();
        Transaction tx = null;
        Writer w = null;
        try{
            tx = session.beginTransaction();
            String query = "FROM Writer W WHERE W.id = " + id;
            List writers = session.createQuery(query).list();
            for (Iterator i = writers.iterator(); i.hasNext();) {
               w = (Writer) i.next();
            }
            tx.commit();
        } catch(HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return w;
    }
}
