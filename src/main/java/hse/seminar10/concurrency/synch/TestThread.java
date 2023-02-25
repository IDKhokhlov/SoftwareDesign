package hse.seminar10.concurrency.synch;

class TestThread extends Thread {
  private final String name;
  private final SynchronizedExample theDemo;

  public TestThread(String name, SynchronizedExample theDemo) {
    this.theDemo = theDemo;
    this.name = name;
    start();
  }

  @Override
  public void run() {
    theDemo.test(name);
  }
}
