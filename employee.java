class emp {
    String empId;
    String empName;
    double salary;
    boolean isIntern;
    public emp(String empId, String empName, double salary){
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false;
    }
    public emp(String empId, String empName){
        this(empId,empName,0);
        this.isIntern=true;
    }
    public void printProfile(){
        System.out.println(empId+" | "+empName+" | Rs "+ salary+" | Intern: "+isIntern);
    }
}

public class employee{
    public static void main(String[] args){
        emp permanent = new emp("E-101", "Divya", 65000);
        emp intern = new emp("E-102", "Arjun");
        permanent.printProfile();
        intern.printProfile();
    }
}
