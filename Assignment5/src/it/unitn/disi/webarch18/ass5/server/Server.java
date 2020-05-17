/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch18.ass5.server;

import it.unitn.disi.webarch18.ass5.common.DocumentValidation;
import it.unitn.disi.webarch18.ass5.common.Document;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author pomo
 */
public class Server extends UnicastRemoteObject implements DocumentValidation{
    
    public Server() throws RemoteException {}

    @Override
    public Document validate(Document document) throws RemoteException {
        System.out.println("Validating a new document");
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        document.addString("Validated on " + dateFormat.format(now));
        System.out.println("Document validated");
        return document;
    }   
    
    public static void main(String[] a) {
        Server server;
        try {
            // Create regestry
            LocateRegistry.createRegistry(1099);
            //register the server
            server = new Server();
            Naming.bind("rmi:///Server", server);
            System.out.println("The server is ready");
        } catch (RemoteException | AlreadyBoundException | MalformedURLException ex) {
            System.err.println("Startup server error:\n" + ex.getMessage());
        }
    }
}
