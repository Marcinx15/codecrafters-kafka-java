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
      MessageProcessor processor = new MessageProcessor();
      int port = 9092;
      try {
          serverSocket = new ServerSocket(port);
          serverSocket.setReuseAddress(true);
          clientSocket = serverSocket.accept();
          out = clientSocket.getOutputStream();
          in = clientSocket.getInputStream();
          KafkaMessage request = deserializeRequest(in);
          ApiVersionsResponseV4 response = processor.process(request);
          out.write(response.toBytes());
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

  public static KafkaMessage deserializeRequest(InputStream in) throws IOException {
      int requestSize = ByteBuffer.wrap(in.readNBytes(4)).getInt();
      return KafkaMessage.fromBytes(requestSize, in.readNBytes(requestSize - 4));
  }
}
