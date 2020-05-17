/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch18.ass5.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author pomo
 */
public interface DocumentValidation extends Remote {
    public Document validate(Document document) throws RemoteException;
}
