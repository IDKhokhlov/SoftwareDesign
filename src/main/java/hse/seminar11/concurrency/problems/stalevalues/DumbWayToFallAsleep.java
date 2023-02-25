package hse.seminar11.concurrency.problems.stalevalues;

class DumbWayToFallAsleep implements Runnable {
  private boolean asleep;

  public void setAsleep(boolean asleep) {
    this.asleep = asleep;
  }

  @Override
  public void run() {
    while (!asleep) {
      // doSomething()
      // Will we sleep?
    }
  }

  public static void main(String[] args) throws InterruptedException {
    var dumbWayToFallAsleep = new DumbWayToFallAsleep();
    Thread thread = new Thread(dumbWayToFallAsleep);
    thread.start();

    Thread.sleep(1000L);
    dumbWayToFallAsleep.setAsleep(true);
  }

}
