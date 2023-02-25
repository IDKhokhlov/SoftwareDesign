package hse.seminar11.concurrency.lock.waitnotify;

public class Program {

  public static void main(String[] args) {
    Store store = new Store();

    Producer producer = new Producer(store);
    Consumer consumer = new Consumer(store);

    new Thread(producer).start();
    new Thread(consumer).start();
  }
}


class Store {
  private int product = 0;

  public synchronized void get() {
    while (product < 1) {
      try {
        wait();
      } catch (InterruptedException ignored) {}
    }
    product--;
    System.out.println("Consumer has bought 1 item");
    System.out.println("Items left: " + product);
    notify();
  }

  public synchronized void put() {
    while (product >= 3) {
      try {
        wait();
      } catch (InterruptedException ignored) {}
    }
    product++;
    System.out.println("Supplier has added 1 item");
    System.out.println("Items left: " + product);
    notify();
  }
}


class Producer implements Runnable {

  private final Store store;

  Producer(Store store) {
    this.store = store;
  }

  public void run() {
    for (int i = 1; i < 6; i++) {
      store.put();
    }
  }
}


class Consumer implements Runnable {

  private final Store store;

  Consumer(Store store) {
    this.store = store;
  }

  public void run() {
    for (int i = 1; i < 6; i++) {
      store.get();
    }
  }
}
