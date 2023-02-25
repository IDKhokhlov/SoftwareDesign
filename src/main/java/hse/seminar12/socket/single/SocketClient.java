package hse.seminar12.socket.single;

import static hse.seminar12.socket.single.SocketServer.SERVER_PORT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Client socket implementation.
 *
 * @author Igor Khokhlov
 */
public class SocketClient {

  private static final String HOST = "localhost";

  public static void main(String[] args) {
    try {
      Socket clientSocket = new Socket(HOST, SERVER_PORT);
      InputStream is = clientSocket.getInputStream();

      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      String receivedData = br.readLine();

      System.out.println("Received from server: " + receivedData);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
