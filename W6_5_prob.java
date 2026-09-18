class LibraryMember {
    private static int counter = 100;
    public final String memberNumber;
    private int borrowLimit;
    private int booksBorrowed;
    public LibraryMember(int borrowLimit) {
        counter++;
        memberNumber = "LIB-" + counter;
        this.borrowLimit = borrowLimit;
        booksBorrowed = 0;
    }
    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }
    public void borrowBook(String genre) {
        borrowBook();
    }
    public int getBooksBorrowed() {
        return booksBorrowed;
    }
    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'R') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }
        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }
        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }

        return true;
    }
    public static int getMembersEnrolled() {
        return counter - 100;
    }
}
class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }
}

public class W6_5_prob {
    static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int skipped = 0;
        int faculty = 0;
        int regular = 0;
        for (int i = 0; i < members.length; i++) {
            if (members[i] == null) {
                skipped++;
            }
            else {
                processed++;

                if (members[i] instanceof FacultyMember) {
                    faculty++;
                }
                else {
                    regular++;
                }
            }
        }

        return processed + " processed | "
                + skipped + " null skipped | "
                + faculty + " faculty | "
                + regular + " regular";
    }

    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember(3);

        System.out.println(m1.memberNumber);
        System.out.println(LibraryMember.getMembersEnrolled());
        System.out.println(LibraryMember.isValidRenewalCode("R12A"));
        System.out.println(LibraryMember.isValidRenewalCode("R1A"));
        System.out.println(LibraryMember.isValidRenewalCode("X12A"));
        m1.borrowBook();
        m1.borrowBook("Fiction");

        System.out.println(m1.getBooksBorrowed());

        LibraryMember[] members = {
                new FacultyMember(5, "Physics"),
                null,
                new LibraryMember(3)
        };
        System.out.println(LibraryMember.processNightlyAudit(members));
    }
}

