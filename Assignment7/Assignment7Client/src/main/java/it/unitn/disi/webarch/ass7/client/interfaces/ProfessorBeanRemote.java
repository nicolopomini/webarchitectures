/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass7.client.interfaces;

import javax.ejb.Remote;

/**
 *
 * @author pomo
 */
@Remote
public interface ProfessorBeanRemote {
    public String addProfessor(String name, String surname);
    public String getAllProfessors();
    
}
