/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass8.server.repositories;

import it.unitn.disi.webarch.ass8.server.entities.Enrolled;
import it.unitn.disi.webarch.ass8.server.entities.EnrolledId;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pomo
 */
public interface EnrolledRepository extends CrudRepository<Enrolled, EnrolledId> {
    
}
