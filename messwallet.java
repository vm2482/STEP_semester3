class eating{
    private double balance;
    public eating(double balance) {
        if (balance < 0){
            System.out.println("Warning: Negative opening balance. Starting balance set to 0.");
            this.balance = 0;
        }

        else{
            this.balance = balance;
        }
    }
    public void topUp(double amount){
        if (amount <= 0) {
            System.out.println("Top-up rejected: Amount must be greater than 0.");
        }
        else{
            balance += amount;
            System.out.println("Top-up successful. Amount added: " + amount);
        }
    }
    public void deduct(double amount) {
        if (amount <= 0) {
            System.out.println("Deduction rejected: Amount must be greater than 0.");
        }
        else if (amount > balance) {
            System.out.println("Deduction rejected: Insufficient balance.");
        }
        else {
            balance -= amount;
            System.out.println("Deduction successful. Amount deducted: " + amount);
        }
    }
    public double getBalance() {
        return balance;
    }
}


public class messwallet {
    public static void main(String[] args) {

        eating wallet = new eating(1000);
        System.out.println("Current Balance: " + wallet.getBalance());
        wallet.topUp(500);
        System.out.println("Current Balance: " + wallet.getBalance());
        wallet.deduct(300);
        System.out.println("Current Balance: " + wallet.getBalance());
        wallet.deduct(1500);
        System.out.println("Current Balance: " + wallet.getBalance());
        wallet.topUp(-100);
        System.out.println("Current Balance: " + wallet.getBalance());
    }
}