import java.util.*;

class GymMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getMonthlyFee() {
        return monthlyFee;
    }

    public static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;

        for (String id : memberIds) {
            try {
                new GymMember(id, monthlyFee);
                signedUp++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }
}

class PremiumMember extends GymMember {
    protected String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }
}

public class M1 {
    public static void main(String[] args) {
        System.out.println("=== Assignment Problem 1: Gym Membership Foundation & Batch Trial Sign-up Validator ===");

        // Test Example 1: Constructor validation rejection
        System.out.print("Test 1 [new GymMember(\"GM1\", 1000)]: ");
        try {
            new GymMember("GM1", 1000);
            System.out.println("Signed up successfully");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Test Example 2: PremiumMember attending sessions
        PremiumMember p = new PremiumMember("MEM01", 2000, "Coach Riya");
        p.attendSession();
        p.attendSession();
        System.out.println("Test 2 [p.getSessionsAttended()]: " + p.getSessionsAttended());

        // Test Example 3: Batch sign-up validation
        String[] ids = {"MEM1", "GM1", "MEM2", " ", "MEM3"};
        System.out.println("Test 3 [signUpBatch]: " + GymMember.signUpBatch(ids, 1000));
    }
}
