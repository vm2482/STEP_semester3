class FeeAcc {
    void Payment(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }
}
class HostelFeeAcc extends FeeAcc {
    void Payment(double amount) {
        System.out.println("Paid in two installments (hostel account)");
    }
}
public class hostel {
    public static void main(String[] args) {
        FeeAcc[] accounts = {new HostelFeeAcc(), new HostelFeeAcc(), new FeeAcc(), new FeeAcc()};
        int hostelCount = 0;
        int dayScholarCount = 0;
        for (FeeAcc account : accounts){
            account.Payment(60000);
            if (account instanceof HostelFeeAcc){
                hostelCount++;
            }
            else{
                dayScholarCount++;
            }
        }
        System.out.println("Hostel accounts processed: " + hostelCount + " | Day-scholar accounts processed: " + dayScholarCount);
    }
}