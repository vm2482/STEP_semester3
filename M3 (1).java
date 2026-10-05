import java.util.*;

abstract class Question {
    private String id;
    private String text;
    private int maxPoints;

    public Question(String id, String text, int maxPoints) {
        this.id = id;
        this.text = text;
        this.maxPoints = maxPoints;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public abstract boolean evaluate(String studentAnswer);
}

class MultipleChoiceQuestion extends Question {
    private String correctAnswer;

    public MultipleChoiceQuestion(String id, String text, int maxPoints, String correctAnswer) {
        super(id, text, maxPoints);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String studentAnswer) {
        return studentAnswer != null && studentAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
    }
}

class TrueFalseQuestion extends Question {
    private String correctAnswer;

    public TrueFalseQuestion(String id, String text, int maxPoints, String correctAnswer) {
        super(id, text, maxPoints);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String studentAnswer) {
        return studentAnswer != null && studentAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
    }
}

class ShortAnswerQuestion extends Question {
    private String correctAnswer;

    public ShortAnswerQuestion(String id, String text, int maxPoints, String correctAnswer) {
        super(id, text, maxPoints);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String studentAnswer) {
        return studentAnswer != null && studentAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
    }
}

class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class Examination {
    private String id;
    private String title;
    private List<Question> questions;

    public Examination(String id, String title) {
        this.id = id;
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public List<Question> getQuestions() {
        return questions;
    }
}

class Attempt {
    private Student student;
    private Examination examination;
    private Map<Question, String> answers;
    private boolean submitted;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        this.answers = new LinkedHashMap<>();
        this.submitted = false;
        System.out.println(examination.getTitle() + " started by " + student.getName() + ".");
    }

    public boolean recordAnswer(Question question, String answer) {
        if (submitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return false;
        }
        answers.put(question, answer);
        System.out.println("Answer recorded for " + question.getId() + ".");
        return true;
    }

    public void submit() {
        if (submitted) {
            System.out.println("Examination already submitted.");
            return;
        }
        submitted = true;
        int totalEarned = 0;
        int totalPossible = 0;
        List<String> results = new ArrayList<>();

        for (Question q : examination.getQuestions()) {
            totalPossible += q.getMaxPoints();
            String ans = answers.get(q);
            boolean isCorrect = q.evaluate(ans);
            if (isCorrect) {
                totalEarned += q.getMaxPoints();
                results.add(q.getId() + ": Correct (" + q.getMaxPoints() + " points)");
            } else {
                results.add(q.getId() + ": Incorrect (0 points)");
            }
        }

        System.out.println(examination.getTitle() + " submitted by " + student.getName() + 
                ". Result: " + String.join(", ", results) + 
                ". Total score: " + totalEarned + "/" + totalPossible + ".");
    }
}

public class M3 {
    public static void main(String[] args) {
        System.out.println("=== Online Examination System ===");

        Examination examA = new Examination("EX101", "Exam A");
        Question q1 = new MultipleChoiceQuestion("Question 1", "What is Java?", 5, "C");
        Question q2 = new TrueFalseQuestion("Question 2", "Is Java dynamically typed?", 5, "False");
        examA.addQuestion(q1);
        examA.addQuestion(q2);

        Student s1 = new Student("S1", "Student 1");

        // Student 1 starts Exam A
        Attempt attempt = new Attempt(s1, examA);

        // Student 1 answers Question 1 (MCQ) with option C
        attempt.recordAnswer(q1, "C");

        // Student 1 answers Question 2 (TF) with True
        attempt.recordAnswer(q2, "True");

        // Student 1 submits Exam A
        attempt.submit();

        // Student 1 attempts to change answer for Question 1
        attempt.recordAnswer(q1, "A");
    }
}
