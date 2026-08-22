import java.util.Scanner;

class PlacementRecord{
    String studentName;
    String company;
    double packageLpa;

    PlacementRecord(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }

    void printRecord(){
        System.out.println(studentName + " -> " + company + " @ " + packageLpa + " LPA\n");
    }
}


public class stepbs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlacementRecord[] people =  new PlacementRecord[3];
        for(int i =0; i<=2; i++){
            System.out.println("Enter Student Name : ");
            String studentName = sc.nextLine();
            System.out.println("Enter Company : ");
            String company = sc.nextLine();
            System.out.println("Enter Package LPA : ");
            double packageLpa = sc.nextDouble();

            sc.nextLine();

            people[i] = new PlacementRecord(studentName, company, packageLpa);
            people[i].printRecord();


    }
        sc.close();
    }
}
