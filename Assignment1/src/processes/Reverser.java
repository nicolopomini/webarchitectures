/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processes;

/**
 *
 * @author pomo
 */
public class Reverser {
    public static boolean palindrome(String s) {
        int i1 = 0, i2 = s.length() - 1;
        while(i2 > i1) {
            if(s.charAt(i1) != s.charAt(i2))
                return false;
            i1++;
            i2--;
        }
        return true;
    }
    /*
    Small simple protocol: 
    all the strings that have to be outputted by the socket of the server start with 
    a double '*' 
    For example '**AMOR' has to be outputted by the server
    */
    public static void main(String argv[]) {
        System.out.println("New process started");
        if(argv.length != 2) {
            System.err.println("Error, two parameters must be specified");
            System.exit(1);
        }
        //the following variables are the GET query parameters
        String par1 = argv[0];
        boolean par2 = Boolean.parseBoolean(argv[1]);
        if(par2)
            System.out.println("**" + new StringBuilder(par1).reverse().toString());
        else
            System.out.println("**" + palindrome(par1));
    }
}
