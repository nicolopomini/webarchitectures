/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pomo
 */
public class HTMLPrinter {
    public static void main(String[] argv) {
		System.out.println("<!DOCTYPE html");
		System.out.println("<html>");
		System.out.println("<head>");
		System.out.println("<title>Parameters</title>");
		System.out.println("</head>");
		System.out.println("<body>");
                
		if(argv.length == 0)
			System.out.println("No parameters were given.<br>");
		else if(argv.length == 1)	//GET request
			parseArgs(argv[0]);
		else						//POST request
			parseArgs(argv[1]);
                
		System.out.println("</body>");
		System.out.println("</html>");
	}

	public static void parseArgs(String p) {
            System.out.println("<ul>");
            String[] vars = p.split("&");
            for(int i = 0; i < vars.length; i++) {
                System.out.println("<li>");
                String[] param = vars[i].split("=");
                if(param.length == 1)
                    System.out.println(param[0] + ": No value passed for this attribute");
                else
                    System.out.println(param[0] + ": " + param[1]);
                System.out.println("</li>");
            }
            System.out.println("</ul>");
	}
}
