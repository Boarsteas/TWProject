import java.io.*;
import java.net.*;

public class ClientWorker implements Runnable
{
  private Socket sckClient;
  private BufferedReader in;
  private PrintWriter out;
  private Thread runner;

  public ClientWorker(Socket sckClient)
  {
    this.sckClient = sckClient;

    try
    {
      in = new BufferedReader(new InputStreamReader(sckClient.getInputStream()));
      out = new PrintWriter(new OutputStreamWriter(sckClient.getOutputStream()),true);

      runner = new Thread(this);
      runner.start();
    }
    catch(IOException e)
    {
      //Error-Output
    }
  }

  public void run()
  {
    try
    {
      while(true)
      {
        String rawPacket = in.readLine();

        if(rawPacket != null)
        {
          ProcessPacket(rawPacket);
        }
        else
        {
          sckClient.close();
          sckClient = null;
          runner = null;
          break;
        }
      }
    }
    catch(IOException e)
    {
      //Error-Output
    }
  }

  private void ProcessPacket(String rawPacket)
  {
    System.out.println("Incoming Packet : " + rawPacket);
  }
}