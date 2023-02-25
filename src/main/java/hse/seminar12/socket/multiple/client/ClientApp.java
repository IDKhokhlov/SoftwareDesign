package hse.seminar12.socket.multiple.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {

  private static final int PORT = 3333;
  private static final String HOST = "localhost";

  public static void main(String[] args) throws IOException {
    var scanner = new Scanner(System.in);
    System.out.println("Please, enter your username for the group chat: ");
    var username = scanner.nextLine();

    var socket = new Socket(HOST, PORT);
    var client = new Client(socket, username);
    client.listenMessages();
    client.sendMessages();
  }
}
