import java.net.ServerSocket;


public class Starteserver {
	
	public static void main(String args[])
	  {
	    ServerSocket app = new ServerSocket(1236);
	    app.start();
	    
	    System.out.println("Listening on port " + app.GetPort() + " ...");
	  }
}
