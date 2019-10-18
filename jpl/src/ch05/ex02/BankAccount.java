package ch05.ex02;

public class BankAccount {
    private long number;
    private long balance;
    private Action lastAct;
    private History header = null;
    private int historySize = 0;

    BankAccount(long number) {
        this.number = number;
    }

    public class Action {
        private String act;
        private long amount;

        Action (String act, long amount) {
            this.act = act;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return number + ": " + act + " " + amount;
        }
    }

    public class History {
        Action act;
        History nextHistory;

        History(Action act) {
            this.act = act;
        }

        void add(Action act) {
            History history = new History(act);
            History ptr = header;
            while (ptr.nextHistory != null) {
                ptr = ptr.nextHistory;
            }
            ptr.nextHistory = history;
            if (historySize == 10) {
                header = header.nextHistory;
            } else if (historySize < 10) {
                historySize++;
            }
        }

        Action next () {
            return nextHistory.act;
        }

        void printAllData() {
            History ptr = header;
            while (ptr != null) {
                System.out.println(ptr.act + " -> ");
                ptr = ptr.nextHistory;
            }
        }
    }

    private void addAction(Action act){
        if (header == null) {
            header = new History(act);
            historySize++;
            return;
        }
        header.add(act);
    }

    public void deposit(long amount) {
        balance += amount;
        lastAct = new Action("deposit", amount);
        addAction(lastAct);
    }

    public void withdraw(long amount) {
        balance -= amount;
        lastAct = new Action("withdraw", amount);
        addAction(lastAct);
    }

    public void transfer(BankAccount other, long amount) {
        other.withdraw(amount);
        deposit(amount);
        lastAct = this.new Action("transfer", amount);
        addAction(lastAct);
        other.lastAct = other.new Action("transfer", amount);
        other.addAction(other.lastAct);
    }

    public History history () {
        return header;
    }

    public static void main(String[] args) {
        BankAccount account1 = new BankAccount(1000);
        account1.deposit(100);
        account1.withdraw(50);
        account1.withdraw(10);
        account1.deposit(120);
        account1.withdraw(30);
        account1.withdraw(60);
        account1.withdraw(10);
        account1.deposit(200);
        account1.deposit(20);
        account1.deposit(400);
        account1.withdraw(100);
        account1.withdraw(100);
        account1.withdraw(70);
        account1.history().printAllData();
    }

}
