package hse.seminar11.concurrency.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockSample {
  private final ReentrantLock bankLock = new ReentrantLock();
  private final Condition sufficientFunds = bankLock.newCondition();
  private final int[] accounts = new int[10];

  private final Object lock = new Object();

  void moneyTransfer(int from, int to, int amount) throws InterruptedException {
    synchronized (lock) {
      while (accounts[from] < amount)
        lock.wait();

      accounts[from]-=amount;
      accounts[to]+=amount;

      lock.notifyAll();
    }
  }

  /** Enter intrinsic lock on *this* */
  synchronized void moneyTransferSync(int from, int to, int amount) throws InterruptedException {
    while (accounts[from] < amount)
      wait(); //wait on intrinsic object's lock condition

    accounts[from]-=amount;
    accounts[to]+=amount;

    notifyAll(); //notify all threads waiting on the condition
  }

  void moneyTransferRL(int from, int to, int amount) throws InterruptedException {
    bankLock.lock();
    try {
      while (accounts[from] < amount)
        sufficientFunds.await();

      accounts[from] -= amount;
      accounts[to] += amount;

      sufficientFunds.signalAll();
    } finally {
      bankLock.unlock();
    }
  }

  void moneyTransfer0(int from, int to, int amount) {
    bankLock.lock();
    try {
      accounts[from]-=amount;
      accounts[to]+=amount;
    } finally {
      bankLock.unlock();
    }
  }

  void moneyTransfer2(int from, int to, int amount) {
    while (accounts[from] < amount) {
      //wait ....
    }

    bankLock.lock();
    try {
      //transfer funds ...
    } finally {
      bankLock.unlock();
    }
  }

  void moneyTransfer3(int from, int to, int amount) {
    bankLock.lock();
    try {
      while (accounts[from] < amount) {
        //wait ....
      }
      //transfer funds ...
    } finally {
      bankLock.unlock();
    }
  }


}
