class payrollacc{
    private double basicSalary;
    private double bonus;
    public payrollacc(double basicSalary){
        if(basicSalary < 0){
            System.out.println("Warning: Negative basic salary. Starting at Rs 0.0");
            this.basicSalary = 0;
        }
        else{
            this.basicSalary = basicSalary;
        }
        this.bonus = 0;
    }
    public void credit(double amount){
        if (amount<=0){
            System.out.println("Invalid bonus: Bonus must be greater than 0.");
        }
        else{
            bonus+=amount;
            System.out.println("Bonus credited: Rs " + amount);
        }
    }
    public void deduct(double percent){
        if(percent<0||percent>100){
            System.out.println("Invalid tax percentage.");
        }
        else{
            basicSalary=basicSalary-(basicSalary * percent / 100);
            System.out.println("Tax deducted: " + percent + "%");
        }
    }
    public double getNetSalary(){
        return basicSalary+bonus;
    }
}
public class payroll{
    public static void main(String[] args) {
        payrollacc account = new payrollacc(50000);
        account.credit(5000);
        account.deduct(10);
        System.out.println("Net salary: Rs " + account.getNetSalary());
    }
}

