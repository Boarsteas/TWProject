import java.io.*;
import java.net.*;

public class ServerSocket extends Thread
{
  private ServerSocket sckServer;
  private Socket sckClient;
  private int port;
  
  public ServerSocket(int port)
  {
    this.port = port;
    
    try
    {
      sckServer = new ServerSocket(port);
    }
    catch(IOException e)
    {
      //Error-Output
    }
  }
  
  public int GetPort()
  {
    return port;
  }
  
  public void Close()
  {
    try
    {
      sckServer.close();
    }
    catch(IOException e)
    {
      //Error-Output
    }
  }
  
  public void Run()
  {
    try
    {
      while(true)
      {
        sckClient = sckServer.accept();
        System.out.println("Incoming Connection from " + sckClient.getInetAddress());
        new ClientWorker(sckClient);
      }
    }
    catch(IOException e)
    {
      //Error-Output
    }
  }
}