import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Kasse {
	private PrintWriter out = null;
	 private BufferedReader in = null;
	Socket clientSocket=null;
	Scanner sc =new Scanner(System.in);
	private int id =600;
	
	Kasse() throws IOException
	{
		while(true)
		{
		 clientSocket = new Socket("localhost", 2343);

		 out = new PrintWriter(clientSocket.getOutputStream(), true);
 		in = new BufferedReader(
 			    new InputStreamReader(
 			    		clientSocket.getInputStream()));

 		
 		
	      //  String text = in.readLine();
	       // System.out.println(text);
	        out.println(id);
	        anmelden();
	        //out.close();
		    //in.close();
	        break;
	         
	}
		clientSocket.close();
	}
	
	
	public void anmelden() throws IOException
	{
		String text = in.readLine();
		System.out.println(text);
 		String pw =sc.next();	
 		out.println(pw);
 		text =in.readLine();
 		System.out.println(text);
 		if(text.contains("Ready"))
 		{
 			eingabeW();
 		}
	}
	
	public void bezahlen()
	{
		
	}
	
	public void eingabeW()
	{
		System.out.println("Bitte Waren scannen:");
		String ware =sc.next();
		out.println(ware);
		System.out.println("Bitte Anzahl angeben:");
		String anzahl = sc.next();
		out.println(anzahl);
			
	}
	
	public void loschenW()
	{
	
	}
	
	public static void main(String[] args)
	 {
		 try 
		 {
		
		@SuppressWarnings("unused")
		Kasse client = new Kasse();
		 }
		 catch(IOException e)
		 {
		System.out.print(e);	 
		 }
	
}

}
