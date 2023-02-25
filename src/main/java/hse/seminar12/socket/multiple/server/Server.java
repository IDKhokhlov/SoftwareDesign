package hse.seminar12.socket.multiple.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  private final ServerSocket serverSocket;

  public Server(ServerSocket serverSocket) {
    this.serverSocket = serverSocket;
  }

  public void startServer() {
    try {
      while (!serverSocket.isClosed()) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("A new client has connected!");
        ClientHandler clientHandler = new ClientHandler(clientSocket);

        Thread clientThread = new Thread(clientHandler);
        clientThread.start();
      }
    } catch (IOException e) {
      closeServerSocket();
    }
  }

  private void closeServerSocket() {
    try {
      if (serverSocket != null) {
        serverSocket.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
