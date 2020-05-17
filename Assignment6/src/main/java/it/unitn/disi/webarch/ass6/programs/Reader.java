/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass6.programs;

import it.unitn.disi.webarch.ass6.dbmanager.DBManager;
import it.unitn.disi.webarch.ass6.models.Book;
import it.unitn.disi.webarch.ass6.models.Writer;
import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author pomo
 */
public class Reader {
    public static void main(String[] a) {
        try{
            SessionFactory factory = new Configuration().configure().buildSessionFactory();
            DBManager manager = new DBManager(factory);
            int thresholdYear = 2000;
            ArrayList<Writer> writers = manager.getAllWriters();
            writers.forEach((author) -> {
                ArrayList<Book> books = manager.getWrittenAfter(author, thresholdYear);
                System.out.println("List of books written after " + thresholdYear + " by " + author.getName() + " " + author.getSurname() + " (writer with id " + author.getId() + "):");
                books.forEach((book) -> {
                    System.out.println(book);
                });
            });            
            factory.close();
        } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
        }
    }
}
