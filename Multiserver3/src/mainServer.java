import java.net.*;
import java.io.*;

public class mainServer {
	
	mainServer() throws IOException
	 {
		 ServerSocket serverSocket= null;
		 boolean listeningSocket = true;
		 try {
	            serverSocket = new ServerSocket(2343);
	        } catch (IOException e) {
	            System.err.println("Could not listen on port: 2343");
	        }

	        while(listeningSocket){
	            Socket clientSocket = serverSocket.accept();
	            
	            ThreadServer mini = new ThreadServer(clientSocket);
	            mini.start();
	            break;
	        }       
	       serverSocket.close();
	    }
	 

	public static void main(String[] args)
	 {
		 try 
		 {	
		@SuppressWarnings("unused")
		mainServer server = new mainServer();
		 }
		 catch(IOException e)
		 {
		System.out.print(e);	 
		 }
		
	 }
}
