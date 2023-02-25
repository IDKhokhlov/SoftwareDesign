package hse.seminar11.concurrency.problems.racecondition;

import java.util.stream.IntStream;

public class DumbCounter {
  int count;
  void increment(){
    count++;
  }
}

class Main {
  int[] accounts;
  public static void main(String[] args){
    DumbCounter c1 = new DumbCounter();
    IntStream.range(0, 1000000).forEach(i->c1.increment());

    DumbCounter c2 = new DumbCounter();
    IntStream.range(0, 1000000).parallel().forEach(i->c2.increment());

    System.out.printf("%d, %d%n", c1.count, c2.count);
  }

  void dumbMoneyTransfer(
    int from, int to, int amount) {

    accounts[from]-=amount;
    accounts[to]+=amount;
  }

}

