package hse.seminar10.logging;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingSample {

  public static void main(String[] args) throws FileNotFoundException {
    var userId = "a8514f9e-4fb8-4fdc-8d07-a1c30b1fd635";
    // Setting up a logging output file
    System.setErr(new PrintStream("log.txt"));

    // Logging messages
    System.err.println("Message 1, some important userID: " + userId);
    System.err.println("Message 2, args: " + Arrays.toString(args));

    // Logging error messages
    try {
      throw new Exception("Some error message");
    } catch (Exception e) {
      log.error("Some important info, ex: ", e);
      e.printStackTrace();
    }

    log.info("Message 1, userID: {}", userId);
  }

}


