import java.net.*;
import java.io.*;

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
			
			System.out.println("hi bin der Server. Bist du da Client?");
			String Id = in.readLine();
			int intId = Integer.parseInt(Id);
			if(intId>500 && intId<1000)
			{
			out.println("Bitte Passwort angeben: \n>");
			String pw =in.readLine();
			
			if(pw.contains("123456") )
			{
				out.println("Ready");
			}
			else
			{
				out.println("Falsch Idiot");
			}
			
			}
			else
			{
				
			}
			Thread.sleep(2000);
			out.close();
		    in.close();
			
		}
		catch (Exception e) {
    	    e.printStackTrace();
    	}
	}
}