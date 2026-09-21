import java.util.*;

class GymMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;
    private int[] lateFeeHistory = new int[10];
    private int feeCount = 0;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    protected void chargeLateFee(int amount) {
        if (feeCount < lateFeeHistory.length) {
            lateFeeHistory[feeCount++] = amount;
        }
    }

    public int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }

    public int getTotalLateFees() {
        int sum = 0;
        for (int i = 0; i < feeCount; i++) {
            sum += lateFeeHistory[i];
        }
        return sum;
    }
}

class PremiumMember extends GymMember {
    protected String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }
}

public class M3 {
    public static void main(String[] args) {
        System.out.println("=== Assignment Problem 3: The Premium Loyalty Discount & Late-Fee Ledger ===");

        PremiumMember p = new PremiumMember("MEM5", 2000, "Coach Riya");

        // Test 1: Premium discount (halved late fee)
        p.chargeLateFee(200);
        System.out.println("Test 1 [p.getTotalLateFees() after chargeLateFee(200)]: " + p.getTotalLateFees());

        // Test 2: Defensive copy verification
        int[] history = p.getLateFeeHistory();
        System.out.println("History before external edit: " + Arrays.toString(p.getLateFeeHistory()));

        // Attempt external tampering
        history[0] = 999;

        System.out.println("History after external edit attempt (history[0] = 999): " + Arrays.toString(p.getLateFeeHistory()));
    }
}
