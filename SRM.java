class Student{
    String name;
    Student(String name){
        this.name = name;
    }
    void display(){
        System.out.println("Student record created: " + name);
    }
}
public class SRM {
    public static void main(String[] args){
        String[] names={"Ravi", "Meera", "Karthik", "Divya", "Anitha"};
        for (int i = 0; i < names.length; i++) {
            Student student = new Student(names[i]);
            student.display();
        }
    }
}