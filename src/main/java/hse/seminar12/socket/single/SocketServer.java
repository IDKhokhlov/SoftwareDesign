package hse.seminar12.socket.single;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server socket implementation.
 *
 * @author Igor Khokhlov
 */
public class SocketServer {
  public static final int SERVER_PORT = 4444;

  public static void main(String[] args) {
    try {
      ServerSocket server = new ServerSocket(SERVER_PORT);
      Socket clientConn = server.accept();

      DataOutputStream serverOutput = new DataOutputStream(clientConn.getOutputStream());
      serverOutput.writeBytes("Welcome to the club, client!\n");

      clientConn.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
