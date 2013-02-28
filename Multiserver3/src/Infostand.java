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
	        
	        //out.close();
		    //in.close();
	        ichwillmichanmelden();
	       kassegreif = new Kasse();
	       
	        break;
	         
	}
		clientSocket.close();
	}
	
	public void zeigeWarenstand() throws IOException
	{
		String text = ("SELECT WID AS WarenID,WName AS Ware,WPreis AS Preis FROM ware;");
		out.println(text);
		String satz = in.readLine();
		System.out.println(satz);
		/////////
		int warenzahl;
		boolean listener= true;
		while(listener){
		String waren = in.readLine();
		warenzahl=waren.length();
		if(waren.equalsIgnoreCase("ende")){
			listener=false;
			
			
		}else
			System.out.println(waren);
		
		}
	
	}
	public void eingabeW() throws IOException
	{
		zeigeWarenstand();
		
		System.out.println("\nBitte WarenID eingeben:");
		String wareid =sc.next();
		////
		System.out.println("Bitte Anzahl angeben:");
		String anzahl = sc.next();
		/////
		System.out.println("Sind sie mit dem einkauf fertig(1)? Möchten sie was ändern(2) oder löschen(3)");
		String wahl= sc.next();
		String text= ("INSERT INTO warenkorb (KorbID,WID,WMenge) VALUES(1,"+wareid+","+anzahl+")");
		out.println(text);
		
		if(wahl.contains("1"))
		{
		System.out.println("Sie erden zur kasse weitergeleitet......");
		
		}
		if(wahl.contains("2") )
		{
			eingabeW();
		}
	}
	
	public void ichwillmichanmelden() throws IOException
	{
		System.out.println("Hallo lieber Kunde wollen sie sich anmelden(1) oder neu registrieren(2) ");
		eingabeW();
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
