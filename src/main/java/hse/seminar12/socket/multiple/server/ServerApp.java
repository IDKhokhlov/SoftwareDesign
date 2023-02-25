package hse.seminar12.socket.multiple.server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerApp {

  private static final int PORT = 3333;

  public static void main(String[] args) throws IOException {
    var serverSocket = new ServerSocket(PORT);
    var server = new Server(serverSocket);
    server.startServer();
  }
}
