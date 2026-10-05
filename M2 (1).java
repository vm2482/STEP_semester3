import java.util.*;

enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED;

    @Override
    public String toString() {
        switch (this) {
            case PENDING: return "Pending";
            case APPROVED: return "Approved";
            case REJECTED: return "Rejected";
            default: return name();
        }
    }
}

abstract class Employee {
    private String id;
    private String name;
    private String employeeType;

    public Employee(String id, String name, String employeeType) {
        this.id = id;
        this.name = name;
        this.employeeType = employeeType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmployeeType() {
        return employeeType;
    }
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String id, String name) {
        super(id, name, "Full-Time");
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String id, String name) {
        super(id, name, "Part-Time");
    }
}

class Contractor extends Employee {
    public Contractor(String id, String name) {
        super(id, name, "Contractor");
    }
}

class LeaveRequest {
    private Employee employee;
    private String dateRange;
    private int days;
    private LeaveStatus status;

    public LeaveRequest(Employee employee, String dateRange, int days) {
        this.employee = employee;
        this.dateRange = dateRange;
        this.days = days;
        this.status = LeaveStatus.PENDING;
        System.out.println("Leave request submitted for " + employee.getName() + " (" + dateRange + "). Status: " + this.status + ".");
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getDateRange() {
        return dateRange;
    }

    public int getDays() {
        return days;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public boolean approve(String managerName) {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + status + " to Approved.");
            return false;
        }
        this.status = LeaveStatus.APPROVED;
        System.out.println(employee.getName() + "'s leave request (" + dateRange + ") approved. Status: " + status + ".");
        return true;
    }

    public boolean reject(String managerName) {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + status + " to Rejected.");
            return false;
        }
        this.status = LeaveStatus.REJECTED;
        System.out.println(employee.getName() + "'s leave request (" + dateRange + ") rejected. Status: " + status + ".");
        return true;
    }

    public boolean setStatus(LeaveStatus newStatus) {
        if (this.status != LeaveStatus.PENDING && newStatus == LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + this.status + " to Pending.");
            return false;
        }
        if (this.status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + this.status + " to " + newStatus + ".");
            return false;
        }
        this.status = newStatus;
        return true;
    }
}

public class M2 {
    public static void main(String[] args) {
        System.out.println("=== Employee Leave Request Workflow ===");

        Employee john = new FullTimeEmployee("E101", "John");
        Employee jane = new PartTimeEmployee("E102", "Jane");

        // FullTimeEmployee John submits leave request for 5 days (Jan 1-5)
        LeaveRequest johnRequest = new LeaveRequest(john, "Jan 1-5", 5);

        // Manager Alice reviews John's request and approves it
        johnRequest.approve("Alice");

        // PartTimeEmployee Jane submits leave request for 2 days (Feb 10-11)
        LeaveRequest janeRequest = new LeaveRequest(jane, "Feb 10-11", 2);

        // Manager Bob reviews Jane's request and rejects it
        janeRequest.reject("Bob");

        // John attempts to change his approved leave request to Pending
        johnRequest.setStatus(LeaveStatus.PENDING);
    }
}
