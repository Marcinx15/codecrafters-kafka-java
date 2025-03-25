import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Main {
  public static void main(String[] args){
      ServerSocket serverSocket;
      Socket clientSocket = null;
      OutputStream out;
      InputStream in;
      int port = 9092;
      try {
          System.err.println("Whatever");
          serverSocket = new ServerSocket(port);
          serverSocket.setReuseAddress(true);
          clientSocket = serverSocket.accept();
          out = clientSocket.getOutputStream();
          in = clientSocket.getInputStream();
          int requestSize = ByteBuffer.wrap(in.readNBytes(4)).getInt();
          System.err.println(requestSize);
          KafkaMessage request = KafkaMessage.fromBytes(requestSize, in.readNBytes(requestSize - 4));
          System.err.println(request);
          out.write(new KafkaMessage(0, request.getHeader()).toBytes());
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
