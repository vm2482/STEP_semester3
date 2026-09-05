class Employee{
    String empId;
    double salary;
    Employee(String empId, double salary){
        this.empId = empId;
        this.salary = salary;
    }
    void raiseSalary(double salary){
        this.salary = this.salary + salary;
    }
    void display(){
        System.out.println(empId + " | Final Salary: Rs " + salary);
    }
}
public class employ{
    public static void main(String[] args){
        Employee[] employees={
                new Employee("E-101", 40000),
                new Employee("E-102", 55000),
                new Employee("E-103", 62000),
                new Employee("E-104", 48000)
        };
        double bonus = 5000;
        for (Employee e : employees){
            e.raiseSalary(bonus);
        }
        for (Employee e : employees){
            e.display();
        }
    }
}