package hse.seminar10.concurrency.thread;

public class ThreadExample extends Thread {

  public ThreadExample() {
    this.start();
  }

  public void run() {
    System.out.println(Thread.currentThread().getName() + " уступает свое место другим");
    Thread.yield();
    System.out.println(Thread.currentThread().getName() + " has finished executing.");
  }

  public static void main(String[] args) {
    new ThreadExample();
    new ThreadExample();
    new ThreadExample();
  }

  public void daemonThreads() {
    Thread daemonThread = new ThreadExample();
    Thread userThread = new ThreadExample();
    daemonThread.setDaemon(true);
    daemonThread.start();
    userThread.start();

    System.out.println(daemonThread.isDaemon());
    System.out.println(userThread.isDaemon());
  }
}