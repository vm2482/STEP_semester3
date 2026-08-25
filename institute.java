class object {
    String name;
    double attendance;
    static String collegeName = "SRM Institute of Science and Technology";
    static int studentCount = 0;
    public object(String name, double attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++;
    }
    static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }
}
public class institute {
    public static void main(String[] args) {
        object s1 = new object("Ravi", 85.5);
        object s2 = new object("Kumar", 90.0);
        object.printCollegeInfo();
    }
}