import java.net.*;
import java.io.*;


// Klasse WorkerThread
// kümmert sich um die Chat-Verbindung eines Clients

public class WorkerThread extends Thread {
    private Socket socket = null;
    private Server parent = null;
    private String user = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    public final static String END_CHAT_SESSION =
        new Character((char)0).toString(); // Indicates the end of a session
    
    // Konstruktor
 // Parameter socket --> SocketVerbindungd es zugehörigen Clients
 // Parameter parent --> Verweis auf die Server-Instanz
 // parent wird benötigt, wenn z.B. empfangener Text angezeigt werden soll

    public WorkerThread(Socket socket, Server parent) {
	super("WorkerThread");
	this.socket = socket;
	this.parent = parent;
	
	try {
	    // Setze Reader und Writer für den ClientSocket
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(
			    new InputStreamReader(
			    socket.getInputStream()));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    // Methode run()
    // Startmethode des Threads
    public void run() {   	
	try {
	    // Znächst wird der Eintrag in der UserMap des Servers gesetzt
		// dazu schickt der Client als erstes seinen Namen
	    String text = in.readLine();
	    this.user = text;
	    parent.addUserToMap(text, this);
	    // Anzeige des Namens in ServerWindow
	    parent.showUser(text);
	    
	    while(true) {
	    	// der Socket wird alle 0,5 sek. gelesen
	    	if (!in.ready())
	    		Thread.sleep(500);
	    	else {
	    		text = in.readLine();
	    		// Wenn ein Disconnect vom Client kommt --> Abbruch des Threads
	    		if (text.equals(END_CHAT_SESSION))
	    			break;
	    		// Anzeige von normalem Text
	    		parent.showMessage(text);
	    	}
	    }
	    	
	    parent.showMessage("Verbindung zu "+ user + " getrennt!");
	    // Lösche den USer nach Disconnect
	    parent.deleteUser(user);
	    
	    // Abräumen des Sockets
	    out.close();
	    in.close();
	    socket.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}
   }
    
    // sende Text über den vorhanden Socket des WorkerThreads
	public void send(String text) {
		out.println(text);
    }
	
	// schicke dem Server den Disconnect()
	public void disconnect() {
		out.println(END_CHAT_SESSION);
    }
}
