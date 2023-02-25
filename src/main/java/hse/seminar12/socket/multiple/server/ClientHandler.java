package hse.seminar12.socket.multiple.server;

import static hse.seminar12.socket.multiple.utils.IOUtils.close;
import static hse.seminar12.socket.multiple.utils.IOUtils.writeAndFlush;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {

  public static List<ClientHandler> clientHandlers = new ArrayList<>();

  private Socket socket;
  private BufferedWriter bufferedWriter;
  private BufferedReader bufferedReader;
  private String clientUsername;

  public ClientHandler(Socket socket) {
    try {
      this.socket = socket;
      bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      this.clientUsername = bufferedReader.readLine();
      clientHandlers.add(this);
      publishMessage("SERVER: " + clientUsername + " has entered the chat!");
    } catch (IOException e) {
      closeEverything(socket, bufferedWriter, bufferedReader);
    }
  }

  @Override
  public void run() {
    String messageFromClient;

    while (socket.isConnected()) {
      try {
        messageFromClient = bufferedReader.readLine();
        publishMessage(messageFromClient);
      } catch (IOException e) {
        closeEverything(socket, bufferedWriter, bufferedReader);
        break;
      }
    }
  }

  private void publishMessage(final String messageToPublish) {
    for (ClientHandler clientHandler : clientHandlers) {
      try {
        if (!clientHandler.clientUsername.equals(clientUsername)) {
          writeAndFlush(clientHandler.bufferedWriter, messageToPublish);
        }
      } catch (IOException e) {
        closeEverything(socket, bufferedWriter, bufferedReader);
      }
    }
  }

  private void removeClientHandler() {
    clientHandlers.remove(this);
    publishMessage("SERVER: " + clientUsername + " has left the chat!");
  }

  private void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
    removeClientHandler();
    close(socket);
    close(bufferedWriter);
    close(bufferedReader);
  }

}
