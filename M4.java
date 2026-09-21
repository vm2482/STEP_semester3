import java.util.*;

class GymMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public String displayInfo() {
        return "Standard | Sessions: " + sessionsAttended;
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

    @Override
    public String displayInfo() {
        return "Premium | Trainer: " + trainerName + " | Sessions: " + sessionsAttended;
    }
}

public class M4 {

    public static String batchPrint(GymMember[] members) {
        StringBuilder sb = new StringBuilder();
        for (GymMember member : members) {
            if (member != null) {
                sb.append(member.displayInfo());
                if (member instanceof PremiumMember) {
                    PremiumMember pm = (PremiumMember) member;
                    sb.append(" [Trainer via downcast: ").append(pm.getTrainerName()).append("]");
                }
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("=== Assignment Problem 4: The Monthly Attendance Announcer ===");

        GymMember[] members = {
            new GymMember("MEM6", 1000),
            new PremiumMember("MEM7", 2000, "Coach Riya")
        };

        String announcement = batchPrint(members);
        System.out.println("Generated Announcement:");
        System.out.println(announcement);

        System.out.println("\nTesting unchecked downcast scenario:");
        GymMember plain = new GymMember("MEM8", 1000);
        try {
            PremiumMember bad = (PremiumMember) plain;
            System.out.println(bad.getTrainerName());
        } catch (ClassCastException e) {
            System.out.println("ClassCastException caught at runtime as expected!");
        }
    }
}
