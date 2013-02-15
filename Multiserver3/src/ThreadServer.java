import java.net.*;
import java.sql.*;
import java.io.*;
import com.mysql.jdbc.Driver;

public class ThreadServer extends Thread {
	 private PrintWriter out = null;
	 private BufferedReader in = null;
	private Socket socket= null;
	Connection con = null;
	static Statement stmt = null;
	ResultSet rs = null;
	
	public ThreadServer(Socket socket) {

	        super("ThreadServer");
	        this.socket = socket;
	        try {
	    	    // Setze Reader und Writer für den ClientSocket
	    		out = new PrintWriter(socket.getOutputStream(), true);
	    		in = new BufferedReader(
	    			    new InputStreamReader(
	    			    socket.getInputStream()));
	    		
	    		////////////Verbindung zu Datenbank
	    		Class.forName("com.mysql.jdbc.Driver");
	    		Connection con = DriverManager.getConnection( "jdbc:mysql://localhost/firma","root","" );
	    		System.out.println("Verbindung erfolgreich hergestellt !");
	    		stmt = con.createStatement();
	    		
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
	
	String text = ("SELECT Ware FROM Waren");
		out.write(text);
	
	}
public void schreibeDb() throws IOException
{
	try {
		String text=in.readLine();	
		stmt.executeQuery(text);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	

}