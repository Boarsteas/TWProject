import java.net.*;
import java.sql.ResultSet;
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
			
			
			String Id = in.readLine();
			int intId = Integer.parseInt(Id);
			if(intId>500 && intId<1000)
			{
			out.println("Bitte vierstelligen Passwort eingeben:");
			
			String pw =in.readLine();
			
			if(pw.contains("1234") )
			{
				out.println("Ready");
				kontrolle();
				
			}
			else
			{
				out.println("Falsch Idiot");
			}
			
			}
			else
			{
				
			}
				
			
			out.close();
		    in.close();
			
		}
		catch (Exception e) {
    	    e.printStackTrace();
    	}
	}
/////////hier Methoden
	
	public void kontrolle() throws IOException
	{
	String ware=in.readLine();	
	String anzahl= in.readLine();
	
	/*try {	////nachgucken ob ware vorhanden

		ResultSet results = stmt.executeQuery("SELECT Ware FROM "+ tabelle);
	}
	
	
	*/
	
	}



}