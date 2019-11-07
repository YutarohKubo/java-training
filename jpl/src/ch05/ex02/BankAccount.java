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

        //headerに対して要素加える
        void add(Action act) {
            if (header == null) {
                return;
            }
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
        if (balance < amount) {
            System.out.println("Cannot withdraw " + amount);
            return;
        }
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
        BankAccount account2 = new BankAccount(2000);
        account1.deposit(100); //お金の処理1回目(account1)
        account1.withdraw(50); //お金の処理2回目(account1)
        account1.withdraw(10); //お金の処理3回目(account1)
        account1.withdraw(500); //お金の処理4回目(account1)
        account1.deposit(120); //お金の処理5回目(account1)
        account1.withdraw(30); //お金の処理6回目(account1)
        account1.withdraw(60); //お金の処理7回目(account1)
        account1.withdraw(10); //お金の処理8回目(account1)
        account2.transfer(account1, 20); //お金の処理9,10回目(account1), お金の処理1,2回目(account2)
        account1.deposit(200); //お金の処理11回目(account1)
        account1.deposit(20); //お金の処理12回目(account1)
        account1.deposit(400); //お金の処理13回目(account1)
        account1.withdraw(100); //お金の処理14回目(account1)
        account1.withdraw(100); //お金の処理15回目(account1)
        account1.withdraw(70); //お金の処理16回目(account1)
        System.out.println("------account1の処理履歴------");
        account1.history().printAllData();
        System.out.println("------account2の処理履歴------");
        account2.history().printAllData();
    }

}
