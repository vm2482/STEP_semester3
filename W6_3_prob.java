class LibraryMember {
    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;

    private int[] fineHistory = new int[10];
    private int fineCount = 0;
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
    protected void chargeFine(int amount) {
        if (fineCount < 10) {
            fineHistory[fineCount] = amount;
            fineCount++;
        }
    }
    public int[] getFineHistory() {
        int[] copy = new int[fineCount];
        for (int i = 0; i < fineCount; i++) {
            copy[i] = fineHistory[i];
        }
        return copy;
    }
    public int getTotalFine() {
        int total = 0;
        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }
        return total;
    }
}
class StudentMember extends LibraryMember {
    private String course;
    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }
}
public class W6_3_prob {
    public static void main(String[] args) {
        StudentMember s = new StudentMember("STU5", 3, "CSE");
        s.chargeFine(100);
        System.out.println(s.getTotalFine());
    }
}

