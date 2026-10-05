import java.util.*;

enum MembershipStatus {
    ACTIVE,
    FROZEN,
    EXPIRED;

    @Override
    public String toString() {
        switch (this) {
            case ACTIVE: return "Active";
            case FROZEN: return "Frozen";
            case EXPIRED: return "Expired";
            default: return name();
        }
    }
}

abstract class MembershipPlan {
    private String planName;
    private int durationMonths;
    private double discountPercentage;

    public MembershipPlan(String planName, int durationMonths, double discountPercentage) {
        this.planName = planName;
        this.durationMonths = durationMonths;
        this.discountPercentage = discountPercentage;
    }

    public String getPlanName() {
        return planName;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public double calculateFee(double baseRatePerMonth) {
        double totalBase = baseRatePerMonth * durationMonths;
        return totalBase * (1.0 - discountPercentage);
    }
}

class MonthlyPlan extends MembershipPlan {
    public MonthlyPlan() {
        super("Monthly", 1, 0.0); // Full price
    }
}

class QuarterlyPlan extends MembershipPlan {
    public QuarterlyPlan() {
        super("Quarterly", 3, 0.10); // 10% off
    }
}

class AnnualPlan extends MembershipPlan {
    public AnnualPlan() {
        super("Annual", 12, 0.25); // 25% off
    }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Membership {
    private static final double BASE_RATE_PER_MONTH = 1000.0;

    private Member member;
    private MembershipPlan plan;
    private double fee;
    private MembershipStatus status;

    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.fee = plan.calculateFee(BASE_RATE_PER_MONTH);
        this.status = MembershipStatus.ACTIVE;
        System.out.printf("%s membership created for %s. Fee: ₹%.2f. Status: %s.%n",
                plan.getPlanName(), member.getName(), fee, status);
    }

    public Member getMember() {
        return member;
    }

    public MembershipPlan getPlan() {
        return plan;
    }

    public double getFee() {
        return fee;
    }

    public MembershipStatus getStatus() {
        return status;
    }

    public boolean checkIn() {
        if (status == MembershipStatus.ACTIVE) {
            System.out.println(member.getName() + " checked in successfully.");
            return true;
        } else {
            System.out.println("Check-in denied: " + member.getName() + "'s membership is " + status + ".");
            return false;
        }
    }

    public boolean freeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
            return false;
        }
        if (status == MembershipStatus.FROZEN) {
            System.out.println("Membership is already frozen.");
            return false;
        }
        this.status = MembershipStatus.FROZEN;
        System.out.println(member.getName() + "'s membership frozen. Status: " + status + ".");
        return true;
    }

    public boolean unfreeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
            return false;
        }
        if (status == MembershipStatus.ACTIVE) {
            System.out.println("Membership is already active.");
            return false;
        }
        this.status = MembershipStatus.ACTIVE;
        System.out.println(member.getName() + "'s membership unfrozen. Status: " + status + ".");
        return true;
    }

    public void expire() {
        this.status = MembershipStatus.EXPIRED;
        System.out.println(member.getName() + "'s membership expired. Status: " + status + ".");
    }
}

public class M4 {
    public static void main(String[] args) {
        System.out.println("=== The FitZone Membership Desk ===");

        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        // Asha buys a Quarterly membership
        Membership ashaMembership = new Membership(asha, new QuarterlyPlan());

        // Ravi buys a Monthly membership
        Membership raviMembership = new Membership(ravi, new MonthlyPlan());

        // Asha checks in
        ashaMembership.checkIn();

        // Asha freezes her membership
        ashaMembership.freeze();

        // Asha attempts to check in
        ashaMembership.checkIn();

        // Ravi's membership expires
        raviMembership.expire();

        // Ravi attempts to freeze his membership
        raviMembership.freeze();
    }
}
