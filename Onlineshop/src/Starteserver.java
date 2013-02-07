import java.io.IOException;




public class Starteserver {
	
	public static void main(String args[]) throws IOException
	  {
	    Mainserver app = new Mainserver(1236);
	    app.start();
	    
	    System.out.println("Listening on port " + app.GetPort() + " ...");
	  }
}
