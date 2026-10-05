import java.util.*;

abstract class WashType {
    private String name;
    private int durationMinutes;
    private double charge;

    public WashType(String name, int durationMinutes, double charge) {
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.charge = charge;
    }

    public String getName() {
        return name;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public double getCharge() {
        return charge;
    }
}

class QuickWash extends WashType {
    public QuickWash() {
        super("Quick", 30, 20.0);
    }
}

class NormalWash extends WashType {
    public NormalWash() {
        super("Normal", 45, 30.0);
    }
}

class HeavyWash extends WashType {
    public HeavyWash() {
        super("Heavy", 60, 45.0);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class WashingMachine {
    private String id;
    private boolean busy;

    public WashingMachine(String id) {
        this.id = id;
        this.busy = false;
    }

    public String getId() {
        return id;
    }

    public boolean isBusy() {
        return busy;
    }

    // Encapsulated status management
    protected void setBusy(boolean busy) {
        this.busy = busy;
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;
    private boolean completed;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
        this.completed = false;
    }

    public Student getStudent() {
        return student;
    }

    public WashingMachine getMachine() {
        return machine;
    }

    public WashType getWashType() {
        return washType;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void complete() {
        this.completed = true;
    }
}

class LaundrySystem {
    public WashCycle startWash(Student student, WashingMachine machine, WashType washType) {
        if (machine.isBusy()) {
            System.out.println("Machine " + machine.getId() + " is currently busy.");
            return null;
        }

        // Set machine to busy via internal encapsulation
        machine.setBusy(true);
        WashCycle cycle = new WashCycle(student, machine, washType);
        System.out.printf("%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",
                washType.getName(), machine.getId(), student.getName(), washType.getDurationMinutes(), washType.getCharge());
        return cycle;
    }

    public void completeWashCycle(WashingMachine machine, WashCycle cycle) {
        if (cycle != null && cycle.getMachine().equals(machine)) {
            cycle.complete();
            machine.setBusy(false);
            System.out.println(machine.getId() + " cycle completed. " + machine.getId() + " is now free.");
        }
    }
}

public class M1 {
    public static void main(String[] args) {
        System.out.println("=== The Hostel Laundry Queue ===");
        LaundrySystem system = new LaundrySystem();

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        WashType quick = new QuickWash();
        WashType normal = new NormalWash();
        WashType heavy = new HeavyWash();

        // Asha starts a Quick wash on Machine M1
        WashCycle cycle1 = system.startWash(asha, m1, quick);

        // Ravi attempts to start a Heavy wash on Machine M1
        system.startWash(ravi, m1, heavy);

        // Ravi starts a Heavy wash on Machine M2
        WashCycle cycle2 = system.startWash(ravi, m2, heavy);

        // Machine M1 completes its cycle
        system.completeWashCycle(m1, cycle1);

        // Neha starts a Normal wash on Machine M1
        system.startWash(neha, m1, normal);
    }
}
