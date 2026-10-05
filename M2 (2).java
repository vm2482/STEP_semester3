import java.util.*;

enum SubmissionStatus {
    SUBMITTED,
    GRADED;

    @Override
    public String toString() {
        switch (this) {
            case SUBMITTED: return "Submitted";
            case GRADED: return "Graded";
            default: return name();
        }
    }
}

abstract class Assignment {
    private String title;
    private int maxMarks;
    private int dueDay; // Representing due date as day of month for simplicity (e.g. 10 for Mar 10)

    public Assignment(String title, int maxMarks, int dueDay) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDay = dueDay;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public int getDueDay() {
        return dueDay;
    }

    public abstract double calculateLatePenaltyPercentage(int lateDays);
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    @Override
    public double calculateLatePenaltyPercentage(int lateDays) {
        if (lateDays <= 0) return 0.0;
        return lateDays * 0.10; // 10% per day late
    }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    @Override
    public double calculateLatePenaltyPercentage(int lateDays) {
        if (lateDays <= 0) return 0.0;
        return lateDays * 0.20; // 20% per day late
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

class Submission {
    private Student student;
    private Assignment assignment;
    private int submitDay;
    private SubmissionStatus status;
    private double finalMarks;

    public Submission(Student student, Assignment assignment, int submitDay) {
        this.student = student;
        this.assignment = assignment;
        this.submitDay = submitDay;
        this.status = SubmissionStatus.SUBMITTED;
        this.finalMarks = 0.0;

        int lateDays = getLateDays();
        String timing = (lateDays <= 0) ? "(on time)" : "(" + lateDays + " days late)";
        System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received " + timing + ". Status: " + status + ".");
    }

    public Student getStudent() {
        return student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public int getSubmitDay() {
        return submitDay;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public int getLateDays() {
        int late = submitDay - assignment.getDueDay();
        return Math.max(0, late);
    }

    public boolean grade(double awardedMarks) {
        if (status == SubmissionStatus.GRADED) {
            System.out.println("Submission already graded.");
            return false;
        }

        int lateDays = getLateDays();
        double penaltyPercent = assignment.calculateLatePenaltyPercentage(lateDays);
        this.finalMarks = awardedMarks * (1.0 - penaltyPercent);
        this.status = SubmissionStatus.GRADED;

        int finalMarksInt = (int) Math.round(finalMarks);

        if (penaltyPercent > 0) {
            int penaltyInt = (int) Math.round(penaltyPercent * 100);
            System.out.println(student.getName() + " graded: " + finalMarksInt + "/" + assignment.getMaxMarks() +
                    " after " + penaltyInt + "% late penalty. Status: " + status + ".");
        } else {
            System.out.println(student.getName() + " graded: " + finalMarksInt + "/" + assignment.getMaxMarks() +
                    ". Status: " + status + ".");
        }
        return true;
    }

    public boolean resubmit(int newSubmitDay) {
        if (status == SubmissionStatus.GRADED) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
            return false;
        }
        this.submitDay = newSubmitDay;
        System.out.println(student.getName() + " resubmitted '" + assignment.getTitle() + "'.");
        return true;
    }
}

public class M2 {
    public static void main(String[] args) {
        System.out.println("=== The Assignment Submission Portal ===");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        // Coding assignment 'Linked List Lab' (max 50, due Mar 10)
        Assignment linkedListLab = new CodingAssignment("Linked List Lab", 50, 10);
        // Written assignment 'Design Essay' (max 50, due Mar 12)
        Assignment designEssay = new WrittenAssignment("Design Essay", 50, 12);

        // Asha submits 'Linked List Lab' on Mar 10
        Submission subAsha = new Submission(asha, linkedListLab, 10);

        // Ravi submits 'Design Essay' on Mar 14
        Submission subRavi = new Submission(ravi, designEssay, 14);

        // Faculty awards Asha 45 marks
        subAsha.grade(45);

        // Faculty awards Ravi 40 marks
        subRavi.grade(40);

        // Asha attempts to resubmit 'Linked List Lab'
        subAsha.resubmit(15);
    }
}
