class MovieTicket {
    protected double ticketPrice = 500;
}

class PremiumTicket extends MovieTicket {

    void display() {
        System.out.println(ticketPrice);
    }
}

public class movie {
    public static void main(String[] args) {

        PremiumTicket p = new PremiumTicket();

        p.display();
    }
}