package jarinspection;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.lang.reflect.*;
import java.net.URL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pomo
 */
public class Inspector {
    public static void main(String[] args) {
        String jarpath = "/Users/pomo/Desktop/mistery.jar";   //absolute path of the jar
        ArrayList<String> classes = new ArrayList<>();          //list of strings containing the names of classes
        //if(args.length > 0)
        //    jarpath = args[0];
        try {
            //open and read the jar
            JarInputStream crunchifyJarFile = new JarInputStream(new FileInputStream(jarpath));
            JarEntry crunchifyJar = crunchifyJarFile.getNextJarEntry();
            while(crunchifyJar != null) {
                if ((crunchifyJar.getName().endsWith(".class"))) {  //for every class found
                    String className = crunchifyJar.getName().replaceAll("/", "\\.");
                    String myClass = className.substring(0, className.lastIndexOf('.'));
                    //parse the class name and add it to the list
                    classes.add(myClass);
                }
                crunchifyJar = crunchifyJarFile.getNextJarEntry();
            }
            URLClassLoader loader = new URLClassLoader(new URL[] {new URL("file:///" + jarpath)});  //load the jar to perform reflection
            for(String c: classes) {
                try {
                    //for each class, print its name and its superclass
                    Class classe = Class.forName(c, true, loader);
                    String superclass = classe.getSuperclass().getName();
                    System.out.println("Class name: " + c + "\t-> Inherits from " + superclass);
                } catch (ClassNotFoundException ex) {
                    System.err.println("Class not found");
                }
            }
            System.out.println();
            //now analyze the "Mistery" class
            for(String c: classes) {
                Class classe;
                try {
                    classe = Class.forName(c, true, loader);
                    if(c.contains("Mistery")) { //analyze the class Mistery
                        //get and print all the methods
                        Method m[] = classe.getDeclaredMethods();
                        System.out.println("Methods of the class Mistery:");
                        for(Method me: m)
                            System.out.println(me.toString());
                        
                        //final part: try every method, starting from the static one
                        //in order to have a Mistery class instance
                        try {
                            //create method
                            Method method = classe.getMethod("create", null);
                            Object o = method.invoke(null, null);
                            
                            //setColor method
                            method = classe.getMethod("setColor", int.class);
                            method.invoke(o, 0);
                            
                            //setC method
                            method = classe.getMethod("setC", int.class);
                            method.invoke(o, 40);
                            
                            //setW method
                            method = classe.getMethod("setW", int.class);
                            method.invoke(o, 400);
                            
                            //setH method
                            method = classe.getMethod("setH", int.class);
                            //method.invoke(o, 400);
                            
                            //setCW method
                            method = classe.getMethod("setCW", int.class);
                            method.invoke(o, 56);
                            
                            //setCH method
                            method = classe.getMethod("setCH", int.class);
                            method.invoke(o, -1);
                        } catch (Exception ex) {
                            System.err.println(ex);
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    System.err.println("Class not found");
                }
            }
            
        } catch (FileNotFoundException ex) {
            System.err.println("Error in reading the jar file");
        } catch (IOException ex) {
            System.err.println("Error in reading the jar file");
        }
    }
}
