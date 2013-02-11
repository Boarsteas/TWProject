import java.io.*;
import java.net.*;
public class Starteserver extends Thread{

    private Socket socket = null;

    public Starteserver(Socket socket) {

        super("Starteserver");
        this.socket = socket;

    }

    public void run(){
            //Read input and process here
    }
            //implement your methods here

}


/*import java.io.IOException;




public class Starteserver {
	
	public static void main(String args[]) throws IOException
	  {
	    Mainserver app = new Mainserver(1236);
	    app.start();
	    
	    System.out.println("Listening on port " + app.GetPort() + " ...");
	  }
}
*/