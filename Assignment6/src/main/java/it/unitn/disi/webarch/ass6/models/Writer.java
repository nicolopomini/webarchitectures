/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass6.models;

import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author pomo
 */
public class Writer {
    private int id;
    private String name, surname;
    private Set books;
    
    public Writer() {}
    public Writer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set getBooks() {
        return books;
    }

    public void setBooks(Set books) {
        this.books = books;
    }

    @Override
    public String toString() {
        String s = "Writer(name: " + this.name + ", surname: " + this.surname + ", books: [";
        for(Iterator i = this.books.iterator(); i.hasNext();) {
            s += (Book) i.next();
        }
        return s + "]";
    }
    
    
}
