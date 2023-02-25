package hse.seminar11.concurrency.problems.deadlock;

import hse.seminar6.generic.common.Account;

public class AccountDeadlock {
  void transferMoney(Account fromAccount, Account toAccount, int amount) throws InsufficientFundsException {
    synchronized (fromAccount) {
      synchronized (toAccount) {
        if (fromAccount.getBalance() < amount) {
          throw new InsufficientFundsException();
        } else {
          fromAccount.debit(amount);
          toAccount.credit(amount);
        }
      }
    }
  }
}

class InsufficientFundsException extends RuntimeException {}
