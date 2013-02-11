import java.net.*;
import java.io.*;
import java.util.*;

public class ThreadServer extends Thread {
	 private PrintWriter out = null;
	 private BufferedReader in = null;
	private Socket socket= null;

	public ThreadServer(Socket socket) {

	        super("ThreadServer");
	        this.socket = socket;
	        try {
	    	    // Setze Reader und Writer für den ClientSocket
	    		out = new PrintWriter(socket.getOutputStream(), true);
	    		in = new BufferedReader(
	    			    new InputStreamReader(
	    			    socket.getInputStream()));
	    	} catch (Exception e) {
	    	    e.printStackTrace();
	    	}
	    }
	public void run() {   	
		try {
			//if (!in.ready())
			
			System.out.println("\n hi bin der Server. Bist du da Client?");
			String text = in.readLine();
			System.out.println(text);
			Thread.sleep(2000);
			out.close();
		    in.close();
			
		}
		catch (Exception e) {
    	    e.printStackTrace();
    	}
	}
}