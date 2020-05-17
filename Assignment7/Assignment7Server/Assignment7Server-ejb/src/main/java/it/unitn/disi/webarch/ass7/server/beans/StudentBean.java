/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass7.server.beans;

import it.unitn.disi.webarch.ass7.client.interfaces.StudentBeanRemote;
import it.unitn.disi.webarch.ass7.server.dao.StudentDAO;
import it.unitn.disi.webarch.ass7.server.entities.Student;
import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author pomo
 */
@Stateless
public class StudentBean implements StudentBeanRemote {

    @Override
    public String addStudent(String name, String surname, String matricola) {
        StudentDAO dao = new StudentDAO();
        return dao.addStudent(name, surname, matricola).toString();
    }

    @Override
    public String getAllStudents() {
        StudentDAO dao = new StudentDAO();
        ArrayList<Student> all = dao.getAll();
        String rtr = "All students:\n";
        for(Student s: all) {
            rtr += s.toString() + "\n";
        }
        return rtr;
    }
    
}
