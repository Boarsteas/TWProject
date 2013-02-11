import java.io.*;
import java.net.*;
public class Mainserver {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;

        boolean listeningSocket = true;
        try {
            serverSocket = new ServerSocket(2343);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 2343");
        }

        while(listeningSocket){
            Socket clientSocket = serverSocket.accept();
            Starteserver mini = new Starteserver(clientSocket);
            mini.start();
        }
        serverSocket.close();       
    }

}




/*import java.net.*;
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
	output.close();
	 }
	 }
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}	 
}*/
