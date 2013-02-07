import java.net.*;
import java.io.*;

public class Mainserver extends Thread {
	private Mainserver server;
	private Socket client;
	private static int port;
	
	public Mainserver(int port) throws IOException 
	{
		this.port=port;
		server = new Mainserver(port);
	}




	public static int GetPort()
	  {
	    return port;
	  }

public void Run()
{
	try{
	while(true)
	 {
	client= server.accept();
	new Infostand(client);
	
	
	/*InputStream input = client.getInputStream();
	OutputStream output = client.getOutputStream();
	int zahl1 = input.read();
	int zahl2 = input.read();
	output.write(zahl1+zahl2);
	output.flush();
	input.close();
	output.close();*/
	 }
	}
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}	 
}
