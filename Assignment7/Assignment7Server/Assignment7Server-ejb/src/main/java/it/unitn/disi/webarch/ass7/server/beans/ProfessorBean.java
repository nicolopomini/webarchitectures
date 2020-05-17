/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass7.server.beans;

import it.unitn.disi.webarch.ass7.client.interfaces.ProfessorBeanRemote;
import it.unitn.disi.webarch.ass7.server.dao.ProfessorDAO;
import it.unitn.disi.webarch.ass7.server.entities.Professor;
import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author pomo
 */
@Stateless
public class ProfessorBean implements ProfessorBeanRemote {
    @Override
    public String addProfessor(String name, String surname) {
        ProfessorDAO dao = new ProfessorDAO();
        return dao.addProfessor(name, surname).toString();
    }

    @Override
    public String getAllProfessors() {
        ProfessorDAO dao = new ProfessorDAO();
        ArrayList<Professor> all = dao.getAll();
        String rtr = "All professors:\n";
        for(Professor p: all) {
            rtr += p.toString() + "\n";
        }
        return rtr;
    }

}
