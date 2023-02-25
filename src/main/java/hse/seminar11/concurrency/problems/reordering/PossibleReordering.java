package hse.seminar11.concurrency.problems.reordering;

public class PossibleReordering {

  static int x = 0, y = 0, a = 0, b = 0;

  public static void main(String... args) throws InterruptedException {
    Thread one = new Thread(() -> {
      a = 1; x = b;
    });
    Thread two = new Thread(() -> {
      b = 1; y = a;
    });

    one.start();
    two.start();

    one.join();
    two.join();

    System.out.printf("%d,%d", x, y);
    //??,??
  }
}
