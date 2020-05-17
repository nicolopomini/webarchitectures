/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch18.ass5.client;

import it.unitn.disi.webarch18.ass5.common.DocumentValidation;
import it.unitn.disi.webarch18.ass5.common.Document;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author pomo
 */
public class Client {
    public static void main(String[] a){
        //prepare a document adding some random string for a random time
        Random r = new Random();
        int times = 2 + r.nextInt(3);
        Document document = new Document();
        for(int i = 0; i < times; i++)
            document.addString(UUID.randomUUID().toString() + " ");
        System.out.println("Document before validation");
        System.out.println(document.toString());
        try {
            //validate the object
            DocumentValidation remoteServer = (DocumentValidation) Naming.lookup("rmi://localhost/Server");
            document = remoteServer.validate(document);            
            // Print the new "viewed" document
            System.out.println("Document after validation");
            System.out.println(document.toString());           
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {         
            System.err.println("Unable to sign the document, the following error happened:\n" + ex.getMessage());            
        }
    }
}
