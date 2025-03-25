import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args){
      ServerSocket serverSocket;
      Socket clientSocket = null;
      int port = 9092;
      try {
          serverSocket = new ServerSocket(port);
          serverSocket.setReuseAddress(true);
          clientSocket = serverSocket.accept();
          OutputStream out = clientSocket.getOutputStream();
          KafkaMessage message = new KafkaMessage(new HeaderV0(7));
          out.write(message.toBytes());
      } catch (IOException e) {
          System.out.println("IOException: " + e.getMessage());
      } finally {
          try {
              if (clientSocket != null) {
                  clientSocket.close();
              }
          } catch (IOException e) {
              System.out.println("IOException: " + e.getMessage());
          }
      }
  }
}
