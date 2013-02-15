import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Infostand {
	private PrintWriter out = null;
	 private BufferedReader in = null;
	Socket clientSocket=null;
	Kasse kassegreif;
	Scanner sc =new Scanner(System.in);
	int id = 1005;
	Infostand() throws IOException
	{
		while(true)
		{
		 clientSocket = new Socket("localhost", 2341);

		 out = new PrintWriter(clientSocket.getOutputStream(), true);
 		in = new BufferedReader(
 			    new InputStreamReader(
 			    		clientSocket.getInputStream()));

 		 out.println(id);
	      //  String text = in.readLine();
	       // System.out.println(text);
	        out.println("Ja hallo ich bin da");
	        //out.close();
		    //in.close();
	        eingabeW();
	       kassegreif = new Kasse();
	       
	        break;
	         
	}
		clientSocket.close();
	}
	
	public void zeigeWarenstand()
	{
		String text = ("SELECT WName AS Ware,WPreis AS Preis,WID AS WarenID FROM Waren");
		out.write(text);
	}
	public void eingabeW()
	{
		
		System.out.println("Bitte WarenID eingeben:");
		String wareid =sc.next();
		////
		System.out.println("Bitte Anzahl angeben:");
		String anzahl = sc.next();
		/////
		System.out.println("Sind sie mit dem einkauf fertig(1)? Möchten sie was ändern(2) oder löschen(3)");
		String wahl= sc.next();
		String text= ("INSERT INTO Warenkorb VALUES("+wareid+","+anzahl+")");
		out.println(text);
		if(wahl.contains("1"))
		{
		System.out.println("Siw erden zur kasse weitergeleitet......");
		
		}
		if(wahl.contains("2") )
		{
			eingabeW();
		}
	}
	
	
	public static void main(String[] args)
	 {
		 try 
		 {
		
		@SuppressWarnings("unused")
		Infostand client = new Infostand();
		 }
		 catch(IOException e)
		 {
		System.out.print(e);	 
		 }
	
}

}
