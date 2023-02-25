package hse.seminar10.concurrency.synch;

public class SynchronizedExample {
  public static void main(String[] args) {
    SynchronizedExample theDemo = new SynchronizedExample();
    new TestThread("THREAD 1", theDemo);
    new TestThread("THREAD 2", theDemo);
    new TestThread("THREAD 3", theDemo);
  }

  public synchronized void test(String name) {
    for (int i = 0; i < 10; i++) {
      Printer.print(name + " :: " + i);
      try {
        Thread.sleep(500);
      } catch (Exception e) {
        Printer.print(e.getMessage());
      }
    }
  }
}
