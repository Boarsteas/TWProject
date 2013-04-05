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
	String kID= null;
	
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
	
	/////////
	public void ichwillmichanmelden() throws IOException///////////Ob Kunde sich anmelden oder registrieren möchte
	{
		System.out.println("Hallo lieber Kunde wollen sie sich anmelden(1) oder neu registrieren(2) ");
		String wahl= sc.next();
		if(wahl.contains("1"))
		{
			System.out.println("Geben sie ihre KundenID an:");
			kID=sc.next();
			System.out.println("Geben sie ihr Passwort ein:");
			String pw=sc.next();
			out.println(1);
			//////vorher hier kontrolle ob Kunde existiert
			out.println(kID);
			out.println(pw);
			String antw= in.readLine();
			System.out.println(antw);
			if(antw.contains("Sie sind angemeldet."))
			{
				eingabeW();
			}
			
			if(antw.contains("Falsche Passwort. Versuch Sie es noch einmal."))
			{
				ichwillmichanmelden();	
			}
			//////////Wareneingabe und aussuche	
			if(antw.contains("Bitte registrieren Sie sich"))
			{
				
				ichwillmichanmelden();
			}
		}
		if(wahl.contains("2"))
		{
		
		out.println(2);
		registration();
		}
		}
	
	
	
	
	/////////////
	public void registration() throws IOException{
		
		System.out.println("Geben sie ihren Vornamen ein:");
	String VName=sc.next();
	
	System.out.println("Geben sie ihren Nachnamen ein");
String NName=sc.next();

System.out.println("Geben sie ihre Passwort");
String PW=sc.next();
int neuPW = Integer.parseInt(PW);



System.out.println("Geben sie ihren Wohnort ein: \n Ort");
String Livingplaceofdoom=sc.next();
System.out.println("PLZ:");
String PLZ=sc.next();

System.out.println("Geben sie ihre Strasse+Hausnummer");
String Str=sc.next();

/////////neue KAID
String selectkaid = ("SELECT KAID FROM kadresse order by KAID desc limit 1;");
out.println(selectkaid);

String kaid = in.readLine();
int kaidint = Integer.parseInt(kaid);
kaidint=kaidint+1;                      
System.out.println(kaidint);
String insertkadresse = ("INSERT INTO kadresse (KAID,KOrt,KPLZ,KStrasse) Values ("+kaidint+",'"+Livingplaceofdoom+"','"+PLZ+"','"+Str+"') ");
out.println(insertkadresse);

String insertkunde = ("INSERT INTO kunde (KID,KVorname,KName,Passwort,KAID) Values (2,'"+VName+"','"+NName+"',"+neuPW+","+kaidint+") ");
out.println(insertkunde);

System.out.println("Ihre Kundennummer ist:");
String KID=in.readLine();
System.out.println(KID);
System.out.println("Bitte bewahren sie ihre kundennummer auf.");

	}
	
	
	
	
	////////
	public void zeigeWarenstand() throws IOException ///Zeigt welche Waren es gibt
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
	////////////
	public void eingabeW() throws IOException//////Zum eingeben der Waren
	{
		zeigeWarenstand();
		/////
		System.out.println("\nBitte WarenID eingeben:");
		String wareid =sc.next();
		////
		System.out.println("Bitte Anzahl angeben:");
		String anzahl = sc.next();
		/////
		String text= ("INSERT INTO warenkorb (KorbID,WID,WMenge,KID) VALUES("+korbID()+","+wareid+","+anzahl+","+kID+")");
		out.println(text);
		//////
		System.out.println("Sind sie mit dem Einkauf fertig(1)? Möchten sie was ändern bzw. löschen(2)");
		String wahl= sc.next();
		
		if(wahl.contains("1"))
		{
		System.out.println("Sie werden zur kasse weitergeleitet......");
		out.println("1");
		}
		if(wahl.contains("2") )
		{
			out.println("2");
			System.out.println("WarenID\t Warenmenge\t Warenpreis\t KundenID");
			boolean listener= true;
			while(listener){
			String warenkorb = in.readLine();
			
			if(warenkorb.equalsIgnoreCase("ende")){
				listener=false;
				nochmal();
				
			}else{
				System.out.println(warenkorb);
			}
			
			
	}
		}
	}		
	///////////
	public int korbID() throws IOException ///zum ändern der KorbID damit mehr Waren in den Warenkorb kommen
	{
		String text = ("SELECT KorbID FROM warenkorb order by KorbID desc limit 1;");
		out.println(text);
		String korbID = in.readLine();
		int kID = Integer.parseInt(korbID);
		kID=kID+1;
		return kID;
	}
	
	
	
	
	public void nochmal() throws IOException
	{
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
