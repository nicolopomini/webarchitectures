/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tinyhttpd;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringTokenizer;

class TinyHttpdConnection extends Thread {

    Socket sock;

    TinyHttpdConnection(Socket s) {
        sock = s;
        setPriority(NORM_PRIORITY - 1);
        start();
    }
    
    /**
     * This function handles the launch of the external process that deals with string operations
     * @param par1 the string to be analyzed
     * @param par2 boolean value to indicate whether the par1 has to be reversed (true) or checked  if the string is a palindrome (false)
     * @return a string containing the output of the computation
     */
    private String startNewProcess(String par1, boolean par2) {
        String javaexecutable = System.getProperty("java.home") + "/bin/java";
        BufferedReader bri = null;
        BufferedReader bre = null;
        String line = "";
        String output = "";
        try{
            Process p = Runtime.getRuntime().exec(javaexecutable + " processes.Reverser " + par1 + " " + par2);
            bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
            bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            
            //Read the output stream
            while ((line = bri.readLine()) != null) {
                //in case the line has to be put into the socket
                if(line.startsWith("**")) {
                    line = line.substring(2);
                    output = line;
                }
                System.out.println(line);
            }
            
            //Read the error stream
            while ((line = bre.readLine()) != null) {
                System.err.println(line);
            }

            //Wait for the execution of the external process
            p.waitFor();

            //The external process is finished
            System.out.println("Process has finished.");
            
        } catch (IOException | InterruptedException err) {
            
            System.err.println("Exception catched: " + err.getMessage());
            
        } finally {

            try {
                if (bri != null) {
                    bri.close();
                }
                if (bre != null) {
                    bre.close();
                }
            } catch (IOException e) {
                System.err.println("Error while closing process streams.");
            }
            
        }
        return output;
    }
    
    /**
     * Method to start an empty process. Used in case the URL starts with "process" but it's not
     * followed by "reverse..."
     */
    private void startEmptyProcess() {
        String javaexecutable = System.getProperty("java.home") + "/bin/java";
        BufferedReader bri = null;
        try {
            Process p = Runtime.getRuntime().exec(javaexecutable + " processes.Empty");
            bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            
            //Read the output stream
            while ((line = bri.readLine()) != null) {
                System.out.println(line);
            }
            
            //Wait for the execution of the external process
            p.waitFor();

            //The external process is finished
            System.out.println("Process has finished.");
        } catch (IOException | InterruptedException ex) {
            System.err.println("Exception catched: " + ex.getMessage());
        } finally {

            try {
                if (bri != null) {
                    bri.close();
                }
            } catch (IOException e) {
                System.err.println("Error while closing process streams.");
            }
            
        }
    }

    public void run() {
        System.out.println("=========");
        OutputStream out = null;
        try {
            out = sock.getOutputStream();
            DataInputStream d = new DataInputStream(
                    sock.getInputStream());
            String req = d.readLine();
            System.out.println("Request: " + req);
            StringTokenizer st = new StringTokenizer(req);
            if ((st.countTokens() >= 2) && st.nextToken().equals("GET")) {
                if ((req = st.nextToken()).startsWith("/")) {
                    req = req.substring(1);
                }
                if (req.endsWith("/") || req.equals("")) {
                    req = req + "index.html";
                } 
                
                    try {
                        if(req.startsWith("process")) { //time to start an external process
                            req = req.substring(7); //remove the "process" string
                            
                            if(req.startsWith("/reverse?")) {   //do the task with the string
                                req = req.substring(9); //remove the reverse query
                                int paramNumber = 0;    //counter for the parameters
                                String par1 = "";
                                boolean par2 = false;
                                String[] parameters = req.split("&");
                                for(String p: parameters) {
                                    if(p.startsWith("par1=")) {
                                        par1 = p.substring(5);
                                        paramNumber++;
                                    }
                                    else if(p.startsWith("par2=")) {
                                        par2 = Boolean.parseBoolean(p.substring(5));
                                        paramNumber++;
                                    }
                                }
                                if(paramNumber != 2) {  //params are not enough
                                    System.err.println("400 Bad Request: par1 and par2 are required");
                                    new PrintStream(out).println("400 Bad Request: par1 and par2 are required");
                                } else {
                                    String output = startNewProcess(par1, par2);
                                    new PrintStream(out).println(output);
                                }
                            } else {    //no "reverse" requested, just start an empty process
                                startEmptyProcess();
                            }
                        }
                        //normal execution
                        else {
                            FileInputStream fis = new FileInputStream(req);
                            byte[] data = new byte[fis.available()];
                            fis.read(data);
                            out.write(data);
                        }
                    } catch (FileNotFoundException e) {
                        new PrintStream(out).println("404 Not Found");
                        System.out.println("404 Not Found: " + req);

                    }
                    
            } else {
                    new PrintStream(out).println("400 Bad Request");
                    System.out.println("400 Bad Request: " + req);
                    sock.close();
                
            }
        } catch (IOException e) {
            System.out.println("Generic I/O error " + e);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                System.out.println("I/O error on close" + ex);
            }
        }
    }
}
