package hse.seminar10.concurrency.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {

  private final ExecutorService threadPool = Executors.newFixedThreadPool(2);

  static class Task implements Runnable {
    private final String taskName;
    private final int delay;

    public Task(String taskName, int delay) {
      this.delay = delay;
      this.taskName = taskName;
    }

    @Override
    public void run() {
      System.out.println("Thread " + taskName + " started!");

      try {
        for (int i = 0; i < 5; i++) {
          System.out.println(taskName + " generate number: " + i);
          TimeUnit.SECONDS.sleep(delay);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println("Thread " + taskName + " finished!");
    }
  }

  public void exampleOfTasks() {
    for (int i = 0; i < 5; i++) {
      threadPool.submit(new Task("Thread#" + i, 5-i));
    }

    Thread testThread = new Thread(new Task("123", 0));
    testThread.start();
    threadPool.shutdown();
  }
}