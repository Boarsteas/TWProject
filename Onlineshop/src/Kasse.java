import java.net.*;
import java.io.*;

public class Kasse {

	Kasse()throws IOException
	{
		Socket server = new Socket("localhost",1235);
		InputStream input = server.getInputStream();
		OutputStream output = server.getOutputStream();
		output.write(20);
		output.write(10);
		output.flush();
		System.out.println(input.read());
		server.close();
		input.close();
		output.close();
	}
	
	public static void main(String[] args)
	 {
		 try 
		 {
		
		@SuppressWarnings("unused")
		Kasse kasse = new Kasse();
		 }
		 catch(IOException e)
		 {
		System.out.print(e);	 
		 }
	
}
	
	
}
