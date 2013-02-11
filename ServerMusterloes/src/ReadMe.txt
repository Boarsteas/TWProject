Erläuterungen zur Musterlösung

Alle Dateien wurden mit Eclipse Galileo erzeugt und übersetzt.
Sie sollten deshalb problemlos in eure Eclipse-Projekte importierbar sein.
Auch in BlueJ sollten sie ablauffähig sein.

Kurze Erläuterung der Klassen:

Client:
	Chatter.java	Startklasse für Client
			zentrale Steuerung
			Methoden werden aus dem ChatFenster aufgerufen
	ChatNameWindow	kleines Fenster um zu Beginn den Namen des Chatters einzulesen
	ChatFenster	ChatFenster des Clients
			Drücken der Knöpfe bewirkt MEthodenaufruf in der Chatter-Instanz


Server
	Server.java		Startklasse für den Server
				erzeugt den zentralen Server-Socket, das ServerFenster und wartet auf eingehende Verbindungen 
				kommt ein Client wird ein neuer WorkerThread erzeugt
	ServerGUI.java		ChatFenster des Servers
	WorkerThread.java	kümmert sich um die Verbindung eines einzelnen Clients

Die hier vorgestellte Lösung ist nach allen Seiten ausbaubar. Was z.B. noch nicht funktioniert, ist die 
Kommunikation der Chatter untereinander, d.h. User1 schickt Nachricht an USer2. Der Einbau einer solchen Lösung
sollte aber nach Verstehen der Lösung kein Problem sein und kann von euch vorgenommen werden.
zB: '@User2@Hallo' beirkt ein Senden von Hallo an USer2.
	