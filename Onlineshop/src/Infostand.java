import java.net.*;
import java.io.*;

public class Infostand implements Runnable{
private Socket server;
private Thread runner;
	

	public Infostand(Socket server) throws IOException
	{
		this.server=server;
		runner = new Thread(this);
		  runner.start();
	}
	
	public void run()
	 {
		try 
		 {
			
			Socket server = new Socket("localhost",Mainserver.GetPort());
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
		 catch(IOException e)
		 {
		System.out.print(e);	 
		 }
	}
	


	
}