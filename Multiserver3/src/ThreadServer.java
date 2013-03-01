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
	    		try{
	    		Class.forName("com.mysql.jdbc.Driver");
	    		Connection con = DriverManager.getConnection( "jdbc:mysql://localhost/mydb","root","" );
	    		System.out.println("Verbindung erfolgreich hergestellt !");
	    		stmt = con.createStatement();
	    		}
	    		catch (SQLException sqle) {
	    			System.out.println(sqle.toString());
	    			}
	    	} catch (Exception e) {
	    	    e.printStackTrace();
	    	}
	    }
	public void run() {   	
		try {
			//if (!in.ready())
			
			
			String Id = in.readLine();
			int intId = Integer.parseInt(Id);
			if(intId>500 && intId<1000) //////Kassse
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
			else ///////Infostand
			{
				
				schreibeWakorb();
				
				
				
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
public void schreibeWakorb() throws IOException
{
	try {
		zeigeWaren();
		String text=in.readLine();	
		System.out.println(text);
		stmt.execute(text);
		System.out.println("Update erfolgreich durchgeführt!");
		String wahl =in.readLine();
		System.out.println("Die wahl:"+wahl);
		if(wahl.contains("1"))////Kasseweiterleiten
		{
		System.out.println("kasse weiterleiten");
		}
		if(wahl.contains("2"))//// Warenkorb ändern
		{
			zeigeWarenkorb();
		}
		
	} catch ( SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public void zeigeWaren() throws IOException
{
	String waren = in.readLine();
	System.out.println(waren);
	try {
		ResultSet results =stmt.executeQuery(waren);
		out.println("WarenID\t Warenname\t Warenpreis");
		while ( results.next() ){
			
			out.println(results.getString(1)+"\t"+results.getString(2)+"\t"+ results.getString(3) );
			}
		out.println("ende");
		//out.println(results);
	} catch (SQLException sqle) {
		// TODO Auto-generated catch block
		System.out.println(sqle.toString());
	}
	
}
public void zeigeWarenkorb()
{
	String text = ("SELECT w.WID AS WarenID,w.WMenge,wa.WPreis FROM warenkorb w, ware wa group by w.WID;");
	try {
		ResultSet results =stmt.executeQuery(text);
		
		while ( results.next() ){
			
			out.println(results.getString(1)+"\t"+results.getString(2)+"\t"+ results.getString(3) );
			}
		out.println("ende");
	}
	catch (SQLException sqle) {
		// TODO Auto-generated catch block
		System.out.println(sqle.toString());
	}
}
}