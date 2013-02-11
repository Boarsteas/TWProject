import java.io.*;
import java.net.*;

public class Chatter{
   public final static String END_CHAT_SESSION =
      new Character((char)0).toString(); // Indicates the end of a session

    private String spitzname;
    private ChatFenster cFenster;
    private Socket meinSocket;
    private PrintWriter writer;
    private BufferedReader bReader;
    private static ChatNameWindow cnwin;
    
    public Chatter(String nickName){
        spitzname = nickName;
        cFenster = new ChatFenster(this);
        meinSocket = null;
        writer = null;
        bReader = null;
    }

    // Methode public static void main()
    // Startpunkt für alle Java Programme unter Eclipse
    // öffnet Fenster zur Eingabe eines Chat-Namens   
    public static void main(String[] args) {
    	cnwin = new ChatNameWindow();
    }

    // diese Methode wird nach Eingabe eines Chat-Namens vom
    // Fenster ChatNameWindow aufgerufen und startet den Chat
    // statische Methode, da noch keine Instanz von Chatter vorhanden
   public static void startChat(String spitzname){
	    // Fenster ChatNameWindow abräumen 
	    cnwin.dispose();
   		// Chat starten
	    new Chatter(spitzname);	   
   }
    
   // führt den Connect auf den Server durch
   // Server (127.0.0.1) und Port (1234) sind fest hinterlegt
   //
   // Um automatisch zu empfangen wird ein Thread gestartet
   // EmpfangsThread ist innere Klasse in  Klasse Chatter
   // siehe Ende der Datei
    public void connect() {
        if (meinSocket != null) {
            anzeigen("[Status] You are already connected!");
            return;
        }
        
        try {
            meinSocket = new Socket("127.0.0.1", 1234);

            // Einen Ausgabestrom öffnen. Da der Ausgabestrom des Sockets
            // nur begrenzte Möglichkeiten bietet, wird dieser üblicherweise
            // direkt mit einem Highlevel-Strom wie z.B. PrintWriter verknüpft.
            // Der zweite Parameter ist optional und gibt an, dass Daten nicht
            // gepuffert, sondern direkt geschickt werden sollen.
            writer = new PrintWriter(meinSocket.getOutputStream(), true);
            bReader = new BufferedReader(new InputStreamReader(meinSocket.getInputStream()));
            
            // sende Chatname an den Server
            writer.println(spitzname);
            // erzeuge EmpfangsThread
            new EmpfangsThread();

        } catch (Exception e) {
            anzeigen("[Status] Sorry, couldn't connect to Server!");
            return;
        }    
      // Statusmeldung anzeigen
      anzeigen("[Status] Connected to Server");
    }
    
    public String spitznameGeben(){
        return spitzname;
    }
    
    public void anzeigen(String text){
        cFenster.anzeigen(text);
    }
    
    public void senden(String text){
        if (meinSocket == null) {
            anzeigen("[Status] You are not connected a Server!");
            return;
        }
        
            anzeigen("[Outgoing]: "+ text);
            writer.println(spitzname + "::" + text);
    }
    
    public void empfangen(){
    	// wenn keine aktive Verbindung --> zurück
        if (meinSocket == null) {
            return;
        }
        
        String s = null;
        try {
            if (!(bReader.ready()) )
              return;
            s = bReader.readLine();
        } catch (Exception e) {
                e.printStackTrace();
         }  
        // falls etwas empfangen wurde
        if ((s != null) &&  (s.length() != 0)) {
        	// falls der Server mir ein Disconnect geschickt hat ...
          if (s.equals(END_CHAT_SESSION)	)
        	  disconnect();
          else
        	  anzeigen("[Incoming]: " + s ); 
      }
    }
    
   public void disconnect() {
        if (meinSocket == null) {
            anzeigen("[Status] You are not connected a Server!");
            return;
        }
        try {
        		// Trennung der Verbindung durch den Client
                anzeigen("[Status] Verbindung getrennt");
                writer.println(END_CHAT_SESSION);
                meinSocket.close();
                meinSocket = null; 
            } 
            catch (Exception e) {
                e.printStackTrace();
            }  
    }
   
   // Methode shutdown
   // wird aufgerufen, wenn Chat-Fenster des Clients geschlossen wurde
   public void shutdown() {
	   // System.out.println("Shutdown initiated");
	   disconnect();
	   System.exit(0);
   }
    
// Klasse EmpfangsThread
// lauscht solange auf dem Socket, solange eine aktive Verbindung besteht
// empfangen() wird hier 1 mal pro Sekunde aufgerufen: Thread.sleep(1000 ms)   
   class EmpfangsThread extends Thread 
    { 
        EmpfangsThread() 
        { 
            start(); 
        } 
        public void run() 
        { 
         // Solange eine Verbindung zum Client besteht ...	
         while (meinSocket != null) {   
            try {
                Thread.sleep(1000);
            } 
            catch (Exception e) {
                e.printStackTrace();
            }                
            empfangen();
         }  
        } 
   
    }
}
