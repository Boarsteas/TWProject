import java.net.*;
import java.io.*;

public class Mainserver {
	ServerSocket server= new ServerSocket(1236);
	
	Mainserver() throws IOException 
	{
		while(true)
		 {
		Socket client	= server.accept();
		InputStream input = client.getInputStream();
		OutputStream output = client.getOutputStream();
		int zahl1 = input.read();
		int zahl2 = input.read();
		output.write(zahl1+zahl2);
		output.flush();
		input.close();
		output.close();
		 }	 
	}


public static void main(String[] args)
{
	 try 
	 {	
	@SuppressWarnings("unused")
	Mainserver server = new Mainserver();
	
	 }
	 catch(IOException e)
	 {
	System.out.print(e);	 
	 }
	 verbindeInfo();
}


public static void verbindeInfo()
{
   Thread t1 = null;
try {
	t1 = new Thread( new Infostand() );
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
     t1.  start();
     
}
}