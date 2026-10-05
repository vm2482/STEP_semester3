import java.util.*;

interface NotificationChannel {
    String getChannelName();
    void send(Student student, Notice notice);
}

class EmailChannel implements NotificationChannel {
    @Override
    public String getChannelName() {
        return "Email";
    }

    @Override
    public void send(Student student, Notice notice) {
        System.out.println("[Email -> " + student.getName() + "] " + notice.getTitle() + ".");
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public String getChannelName() {
        return "SMS";
    }

    @Override
    public void send(Student student, Notice notice) {
        System.out.println("[SMS -> " + student.getName() + "] " + notice.getTitle() + ".");
    }
}

class AppChannel implements NotificationChannel {
    @Override
    public String getChannelName() {
        return "App";
    }

    @Override
    public void send(Student student, Notice notice) {
        System.out.println("[App -> " + student.getName() + "] " + notice.getTitle() + ".");
    }
}

class Student {
    private String name;
    private String department;
    private List<NotificationChannel> preferredChannels;

    public Student(String name, String department) {
        this.name = name;
        this.department = department;
        this.preferredChannels = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void addPreferredChannel(NotificationChannel channel) {
        preferredChannels.add(channel);
    }

    public List<NotificationChannel> getPreferredChannels() {
        return preferredChannels;
    }
}

class Notice {
    private String title;
    private List<String> targetDepartments;

    public Notice(String title, List<String> targetDepartments) {
        this.title = title;
        this.targetDepartments = (targetDepartments != null) ? new ArrayList<>(targetDepartments) : new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTargetDepartments() {
        return targetDepartments;
    }

    public boolean isValid() {
        return title != null && !title.trim().isEmpty() && targetDepartments != null && !targetDepartments.isEmpty();
    }
}

class NoticeBoard {
    private List<Student> students;

    public NoticeBoard() {
        this.students = new ArrayList<>();
    }

    public void registerStudent(Student student) {
        students.add(student);
    }

    public boolean postNotice(Notice notice) {
        if (!notice.isValid()) {
            System.out.println("Cannot post notice: At least one target department is required.");
            return false;
        }

        System.out.println("Notice '" + notice.getTitle() + "' posted to " + String.join(", ", notice.getTargetDepartments()) + ".");

        for (Student student : students) {
            if (notice.getTargetDepartments().contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getPreferredChannels()) {
                    channel.send(student, notice);
                }
            }
        }
        return true;
    }
}

public class M5 {
    public static void main(String[] args) {
        System.out.println("=== The Campus Notice Broadcaster ===");
        NoticeBoard noticeBoard = new NoticeBoard();

        NotificationChannel email = new EmailChannel();
        NotificationChannel sms = new SmsChannel();
        NotificationChannel app = new AppChannel();

        // Asha (CSE) prefers Email and App
        Student asha = new Student("Asha", "CSE");
        asha.addPreferredChannel(email);
        asha.addPreferredChannel(app);
        noticeBoard.registerStudent(asha);

        // Ravi (ECE) prefers SMS
        Student ravi = new Student("Ravi", "ECE");
        ravi.addPreferredChannel(sms);
        noticeBoard.registerStudent(ravi);

        // Admin posts notice 'Lab Closed Tomorrow' for CSE
        Notice notice1 = new Notice("Lab Closed Tomorrow", Arrays.asList("CSE"));
        noticeBoard.postNotice(notice1);

        // Admin posts notice 'Fee Deadline Extended' for CSE and ECE
        Notice notice2 = new Notice("Fee Deadline Extended", Arrays.asList("CSE", "ECE"));
        noticeBoard.postNotice(notice2);

        // Admin attempts to post notice 'Sports Day' with no target department
        Notice notice3 = new Notice("Sports Day", Collections.emptyList());
        noticeBoard.postNotice(notice3);
    }
}
