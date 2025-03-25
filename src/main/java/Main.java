import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args){
      ServerSocket serverSocket;
      Socket clientSocket = null;
      OutputStream out;
      InputStream in;
      int port = 9092;
      try {
          serverSocket = new ServerSocket(port);
          serverSocket.setReuseAddress(true);
          clientSocket = serverSocket.accept();
          out = clientSocket.getOutputStream();
          in = clientSocket.getInputStream();
          KafkaMessage request = KafkaMessage.fromBytes(in.readAllBytes());
          out.write(request.toBytes());
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
