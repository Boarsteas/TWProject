Erl�uterungen zur Musterl�sung

Alle Dateien wurden mit Eclipse Galileo erzeugt und �bersetzt.
Sie sollten deshalb problemlos in eure Eclipse-Projekte importierbar sein.
Auch in BlueJ sollten sie ablauff�hig sein.

Kurze Erl�uterung der Klassen:

Client:
	Chatter.java	Startklasse f�r Client
			zentrale Steuerung
			Methoden werden aus dem ChatFenster aufgerufen
	ChatNameWindow	kleines Fenster um zu Beginn den Namen des Chatters einzulesen
	ChatFenster	ChatFenster des Clients
			Dr�cken der Kn�pfe bewirkt MEthodenaufruf in der Chatter-Instanz


Server
	Server.java		Startklasse f�r den Server
				erzeugt den zentralen Server-Socket, das ServerFenster und wartet auf eingehende Verbindungen 
				kommt ein Client wird ein neuer WorkerThread erzeugt
	ServerGUI.java		ChatFenster des Servers
	WorkerThread.java	k�mmert sich um die Verbindung eines einzelnen Clients

Die hier vorgestellte L�sung ist nach allen Seiten ausbaubar. Was z.B. noch nicht funktioniert, ist die 
Kommunikation der Chatter untereinander, d.h. User1 schickt Nachricht an USer2. Der Einbau einer solchen L�sung
sollte aber nach Verstehen der L�sung kein Problem sein und kann von euch vorgenommen werden.
zB: '@User2@Hallo' beirkt ein Senden von Hallo an USer2.
	