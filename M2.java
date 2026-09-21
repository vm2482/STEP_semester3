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

    public void attendSession() {
        sessionsAttended++;
    }

    public void setSessionsAttended(int count) {
        this.sessionsAttended = count;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public String displayInfo() {
        return "Standard Member | Sessions: " + sessionsAttended;
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
        return "Premium Member | Trainer: " + trainerName + " | Sessions: " + sessionsAttended;
    }
}

class EliteMember extends PremiumMember {
    protected String lockerNumber;

    public EliteMember(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    public String getLockerNumber() {
        return lockerNumber;
    }

    @Override
    public String displayInfo() {
        return "Elite Member | Trainer: " + trainerName + " | Locker: " + lockerNumber + " | Sessions: " + sessionsAttended;
    }
}

class GroupClassMember extends GymMember {
    protected String className;

    public GroupClassMember(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String displayInfo() {
        return "Group Class Member | Class: " + className + " | Sessions: " + sessionsAttended;
    }
}

public class M2 {

    public static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof PremiumMember) {
            return "Direct subclass (2 generations deep)";
        } else {
            return "Base class (1 generation)";
        }
    }

    public static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        for (GymMember member : members) {
            if (member != null) {
                total += member.getSessionsAttended();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("=== Assignment Problem 2: Three Tiers of Gym Membership ===");

        GymMember standard = new GymMember("MEM1", 1000);
        PremiumMember premium = new PremiumMember("MEM2", 2000, "Coach Riya");
        EliteMember elite = new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember groupClass = new GroupClassMember("MEM4", 1500, "Zumba");

        // Test displayInfo()
        System.out.println(standard.displayInfo());
        System.out.println(premium.displayInfo());
        System.out.println(elite.displayInfo());
        System.out.println(groupClass.displayInfo());

        // Test classifyGeneration(...)
        System.out.println("\nClassification (Elite): " + classifyGeneration(elite));
        System.out.println("Classification (GroupClass): " + classifyGeneration(groupClass));

        // Test getTotalSessionsAttended(...)
        premium.setSessionsAttended(3);
        elite.setSessionsAttended(2);
        groupClass.setSessionsAttended(4);

        GymMember[] members = {premium, elite, groupClass};
        System.out.println("\nTotal Sessions Attended: " + getTotalSessionsAttended(members));
    }
}
