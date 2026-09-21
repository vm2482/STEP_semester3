import java.util.*;

class GymMember {
    private static int counter = 2000;
    private static int membersEnrolled = 0;

    public final String membershipNumber;
    protected int monthlyFee;
    protected int feesPaid;
    protected String lastPaymentMode;

    public GymMember(int monthlyFee) {
        if (monthlyFee <= 0) {
            throw new IllegalArgumentException("monthlyFee must be positive");
        }
        this.monthlyFee = monthlyFee;
        this.feesPaid = 0;
        this.membershipNumber = "GYM-" + (++counter);
        membersEnrolled++;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }

    public void payFee(int amount) {
        if (amount > 0) {
            feesPaid += amount;
        }
    }

    public void payFee(int amount, String mode) {
        this.lastPaymentMode = mode;
        this.payFee(amount); // Delegate to one-argument version
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public String getLastPaymentMode() {
        return lastPaymentMode;
    }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'G') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }
        return true;
    }

    public static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        if (members != null) {
            for (GymMember member : members) {
                if (member == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (member instanceof GroupClassMember) {
                        group++;
                    } else {
                        individual++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + group + " group | " + individual + " individual";
    }
}

class GroupClassMember extends GymMember {
    protected String className;

    public GroupClassMember(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}

public class M5 {
    public static void main(String[] args) {
        System.out.println("=== Assignment Problem 5: Membership Numbers, Referral Codes & Weekly Check-in Settlement ===");

        // Test 1: Membership number assignment & static counter
        GymMember m1 = new GymMember(1000);
        System.out.println("m1.membershipNumber: " + m1.membershipNumber);
        System.out.println("GymMember.getMembersEnrolled(): " + GymMember.getMembersEnrolled());

        // Test 2: Referral code format validation
        System.out.println("\nReferral Code Tests:");
        System.out.println("isValidReferralCode(\"G45B\"): " + GymMember.isValidReferralCode("G45B"));
        System.out.println("isValidReferralCode(\"G4B\"): " + GymMember.isValidReferralCode("G4B"));
        System.out.println("isValidReferralCode(\"X45B\"): " + GymMember.isValidReferralCode("X45B"));

        // Test 3: Overloaded payFee(...) delegation
        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println("\nm1.getFeesPaid(): " + m1.getFeesPaid());

        // Test 4: Weekly check-in batch settlement
        GymMember[] checkInBatch = {
            new GroupClassMember(1500, "Zumba"),
            null,
            new GymMember(1000)
        };
        System.out.println("\nWeekly Check-in Result:");
        System.out.println(GymMember.processWeeklyCheckIn(checkInBatch));
    }
}
