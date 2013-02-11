import java.net.*;
import java.util.HashMap;
import java.io.*;

// Klasse Server
// Startklasse für die Serverseite des Chats

public class Server {

	/**
	 * @param args
	 */
	private ServerGUI myGUI = null;
    ServerSocket serverSocket = null;
    boolean listening = true;
    private HashMap<String,WorkerThread> userThreadMap = null; 

	
	public static void main(String[] args) {
		new Server();
		
	}
	
	// Konstruktor
	public Server() {
		// Zeige das Server-Fenster an
		myGUI = new ServerGUI(this);
		// lege eine HashMap an, die die Zuordnung User --> UserThread vornimmt
		userThreadMap = new HashMap<String, WorkerThread>();

        try {
        	// Initislisiere den Server-Socket
            serverSocket = new ServerSocket(1234);
            
        } catch (IOException e) {
            System.err.println("Could not listen on port: 1234.");
            System.exit(-1);
        }
		showStatus("Listening");

        try {
        	// Solange der Server nicht beendet wurde
        	while (listening) {
        		// warte auf eingehende Verbindung
        		// wenn Verbindung kommt, wird ein Socket durch accept erzeugt
        	    Socket client = serverSocket.accept();
        	    // Erzeuge einen neuen Thread, der sich um die Verbindung kümmert
        		WorkerThread myWorker = new WorkerThread(client, this);
        		// Starte den WorkerThread
        		myWorker.start();
        		
        		showStatus("Connected");
        	}
        	// Server Socket abräumen
        	serverSocket.close();
        } catch (IOException e) {
            System.err.println("SocketCommunication Error");
            System.exit(-1);
        }
       	
	}
	
	public void showMessage(String text) {
		myGUI.showMessage(text);
	}

	public void showStatus(String text) {
		myGUI.showStatus(text);
	}
	
	// zeige USer im ServerWindow
	public void showUser(String text) {
		myGUI.showUser(text);
	}
	// lösche den User von der Anzeige im ServerWindow
	public void deleteUser(String text) {
		myGUI.deleteUser(text);
	}	
	// sendet den TExt text an den User user
	public void send(String user, String text) {
		WorkerThread wt = userThreadMap.get(user);
		wt.send(text);
	}

	// führt den Disconnect für einen bestimmten User durch
	public void disconnect(String user) {
		WorkerThread wt = userThreadMap.get(user);
		wt.disconnect();
	}	
	// nimmt einen neuen User mit seinem UserThread in die HashMap auf
	public void addUserToMap(String user, WorkerThread w) {
		userThreadMap.put(user,w);
	}
	
}
