package hse.seminar10.concurrency.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {
  private final ExecutorService threadPool = Executors.newFixedThreadPool(2);
  private final List<Future<Integer>> futureList = new ArrayList<>();

  static class Task implements Callable<Integer> {
    private final int number;

    public Task(int number) {
      this.number = number;
    }

    @Override
    public Integer call() {
      int result = 0;
      for(int i = 1; i <= number; i++)
        result += i;

      return result;
    }
  }

  public void exampleOfFuture() throws Exception {
    for (int i = 0; i < 5; i++) {
      var future = threadPool.submit(new Task(i));
      futureList.add(future);
    }

    for (var future : futureList) {
      System.out.println("Result: " + future.get());
    }

    threadPool.shutdown();
  }
}