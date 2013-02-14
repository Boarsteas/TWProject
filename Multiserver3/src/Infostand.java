import java.io.*;
import java.net.*;

public class Infostand {
	private PrintWriter out = null;
	 private BufferedReader in = null;
	Socket clientSocket=null;
	
	Infostand() throws IOException
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
	        out.println("Ja hallo ich bin da");
	        //out.close();
		    //in.close();
	        break;
	         
	}
		clientSocket.close();
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
