class LibraryMember {
    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;
    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }
        if (borrowLimit <= 0) {
            throw new IllegalArgumentException("Invalid borrow limit");
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        booksBorrowed = 0;
    }
    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }
    public int getBooksBorrowed() {
        return booksBorrowed;
    }
    public void displayInfo() {
        System.out.println("General Member | Books Borrowed: " + booksBorrowed);
    }
}
class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }
    public void displayInfo() {
        System.out.println("Student Member | Course: " + course
                + " | Books Borrowed: " + getBooksBorrowed());
    }
}
class HonorsStudentMember extends StudentMember {
    private int bonusLimit;
    public HonorsStudentMember(String memberId, int borrowLimit,
                               String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }
    public void displayInfo() {
        System.out.println("Honors Student Member | Course: "
                + getCourse() + " | Bonus Limit: " + bonusLimit
                + " | Books Borrowed: " + getBooksBorrowed());
    }
    public String getCourse() {
        return "";
    }
}
class FacultyMember extends LibraryMember {
    private String department;
    public FacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }
    public void displayInfo() {
        System.out.println("Faculty Member | Department: "
                + department + " | Books Borrowed: " + getBooksBorrowed());
    }
}
public class W6_2_prob {
    public static String classifyGeneration(LibraryMember member) {

        if (member instanceof HonorsStudentMember) {
            return "Honors Student Member";
        }
        else if (member instanceof StudentMember) {
            return "Student Member";
        }
        else if (member instanceof FacultyMember) {
            return "Faculty Member";
        }
        else {
            return "General Member";
        }
    }
    public static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;
        for (int i = 0; i < members.length; i++) {
            total += members[i].getBooksBorrowed();
        }
        return total;
    }
    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember("STU1", 3);
        StudentMember m2 = new StudentMember("STU2", 3, "CSE");
        HonorsStudentMember m3 = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember m4 = new FacultyMember("STU4", 5, "Physics");

        m1.displayInfo();
        m2.displayInfo();
        m3.displayInfo();
        m4.displayInfo();
    }
}

