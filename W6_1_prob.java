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
        this.booksBorrowed = 0;
    }
    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }
    public int getBooksBorrowed() {
        return booksBorrowed;
    }
    public static String enrollBatch(String[] memberIds, int borrowLimit) {
        int rejected = 0;
        for (int i = 0; i < memberIds.length; i++) {
            try {
                LibraryMember member = new LibraryMember(memberIds[i], borrowLimit);
            }
            catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Rejected: " + rejected;
    }
}
class StudentMember extends LibraryMember {
    private String course;
    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }
}
public class W6_1_prob {
    public static void main(String[] args) {
        StudentMember s = new StudentMember("STU10", 3, "CSE");
        s.borrowBook();
        s.borrowBook();
        System.out.println(s.getBooksBorrowed());
    }
}
