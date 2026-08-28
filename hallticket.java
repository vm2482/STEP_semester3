class Hall{
    String studentName;
    int seatNumber;
    Hall(String studentName, int seatNumber) {
        this.studentName = studentName;
        this.seatNumber = seatNumber;
    }
}
public class hallticket{
    public static void main(String[] args) {
        Hall priya = new Hall("Priya",0);
        Hall copy = priya;
        copy.seatNumber = 45;
        Hall separate = new Hall("Priya", 45);
        System.out.println("Priya's seatNumber (via first variable): " + priya.seatNumber);
        System.out.println("copy == priya: " + (copy == priya));
        System.out.println("separate == priya: " + (separate == priya));
    }
}
