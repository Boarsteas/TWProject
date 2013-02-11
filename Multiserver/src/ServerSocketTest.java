public class ServerSocketTest
{
  public static void main(String args[])
  {
    ServerSocket app = new ServerSocket(1200);
    app.start();
    
    System.out.println("Listening on port " + app.GetPort() + " ...");
  }
}