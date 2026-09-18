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
        System.out.print("General | Books: " + booksBorrowed);
    }
}
class StudentMember extends LibraryMember {
    private String course;
    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }
    public String getCourse() {
        return course;
    }
    public void displayInfo() {
        System.out.print("Student | Course: " + course
                + " | Books: " + getBooksBorrowed());
    }
}
public class W6_4_prob {
    static String batchPrint(LibraryMember[] members) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < members.length; i++) {
            members[i].displayInfo();
            if (members[i] instanceof StudentMember) {
                StudentMember student = (StudentMember) members[i];

                result.append("Student | Course: ");
                result.append(student.getCourse());
                result.append(" | Books: ");
                result.append(student.getBooksBorrowed());
                result.append(" [Course via downcast: ");
                result.append(student.getCourse());
                result.append("] | ");
            }
            else {
                result.append("General | Books: ");
                result.append(members[i].getBooksBorrowed());
                result.append(" | ");
            }
        }
        return result.toString();
    }
    public static void main(String[] args) {
        LibraryMember[] members = {new LibraryMember("LB5", 3), new StudentMember("STU6", 3, "ECE")
        };
        System.out.println(batchPrint(members));
    }
}

