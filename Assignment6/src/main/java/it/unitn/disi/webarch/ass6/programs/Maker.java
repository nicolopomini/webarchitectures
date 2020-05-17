/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass6.programs;

import it.unitn.disi.webarch.ass6.dbmanager.DBManager;
import it.unitn.disi.webarch.ass6.models.Book;
import it.unitn.disi.webarch.ass6.models.Writer;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author pomo
 */
public class Maker {
    public static void main(String[] a) {
        try {
            SessionFactory factory = new Configuration().configure().buildSessionFactory();
            DBManager manager = new DBManager(factory);
            Random r = new Random();
            HashSet books;
            String name, surname;
            Writer w;
            int bookNumber;
            for(int i = 0; i < 3; i++) {
                name = UUID.randomUUID().toString().substring(0, 8);
                surname = UUID.randomUUID().toString().substring(0, 8);
                books = new HashSet();
                bookNumber = 1 + r.nextInt(3);
                for(int j = 0; j < bookNumber; j++) {
                    books.add(new Book(UUID.randomUUID().toString().substring(0, 8), 1980 + r.nextInt(38)));
                }
                w = manager.addWriter(name, surname, books);
                System.out.println("Writer added");
                System.out.println(w);
            }
            factory.close();
        } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
        }
    }
}
