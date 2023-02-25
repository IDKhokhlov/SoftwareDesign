package hse.seminar12.socket.multiple.client;

import static hse.seminar12.socket.multiple.utils.IOUtils.close;
import static hse.seminar12.socket.multiple.utils.IOUtils.writeAndFlush;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

  private final Socket socket;
  private final String username;

  private BufferedWriter bufferedWriter;
  private BufferedReader bufferedReader;

  public Client(Socket socket, String username) {
    this.username = username;
    this.socket = socket;
    try {
      bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    } catch (IOException e) {
      closeEverything(socket, bufferedWriter, bufferedReader);
    }
  }

  public void sendMessages() {
    try {
      writeAndFlush(bufferedWriter, username);

      Scanner scanner = new Scanner(System.in);
      while (socket.isConnected()) {
        String message = scanner.nextLine();
        writeAndFlush(bufferedWriter, username + ": " + message);
      }
    } catch (IOException e) {
      closeEverything(socket, bufferedWriter, bufferedReader);
    }
  }

  public void listenMessages() {
    new Thread(() -> {
      String messageFromChat;

      while (socket.isConnected()) {
        try {
          messageFromChat = bufferedReader.readLine();
          System.out.println(messageFromChat);
        } catch (IOException e) {
          closeEverything(socket, bufferedWriter, bufferedReader);
        }
      }
    }).start();
  }

  private void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
    close(socket);
    close(bufferedWriter);
    close(bufferedReader);
  }
}
