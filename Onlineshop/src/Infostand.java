import java.net.*;
import java.io.*;

public class Infostand implements Runnable{

	Infostand()throws IOException
	{
		Socket server = new Socket("localhost",1236);
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
	//test
	public void run()
	 {
		try 
		 {
		
		@SuppressWarnings("unused")
		Infostand infost = new Infostand();
		 }
		 catch(IOException e)
		 {
		System.out.print(e);	 
		 }
	}
	


	
}