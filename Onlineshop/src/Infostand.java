

import java.io.*;
import java.net.*;

class Infostand {
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;
      while(true){
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("myname.domain.com", 2343);

        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.println("Ready");
        sentence = in.readLine();
        out.writeBytes(sentence + '\n');
        modifiedSentence = in.readLine();
        System.out.println(modifiedSentence);
       }
      
    }
}


/*import java.net.*;
import java.io.*;

public class Infostand implements Runnable{
private Socket server;
private Thread runner;
	

	public Infostand(Socket server) throws IOException
	{
		this.server=server;
		runner = new Thread(this);
		  runner.start();
	}
	
	public void run()
	 {
		try 
		 {
			
			Socket server = new Socket("localhost",Mainserver.GetPort());
			InputStream input = server.getInputStream();
			OutputStream output = server.getOutputStream();
			output.write(20);
			output.write(10);
			output.flush();
			System.out.println(input.read());
			server.close();
			input.close();
			output.close();		
		
		 }
		 catch(IOException e)
		 {
		System.out.print(e);	 
		 }
	}
	


	
}*/