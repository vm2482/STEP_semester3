import java.util.*;

abstract class Seat {
    private String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public abstract double getPrice();

    @Override
    public String toString() {
        return seatNumber;
    }
}

class RegularSeat extends Seat {
    public RegularSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 150.0;
    }
}

class PremiumSeat extends Seat {
    public PremiumSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 250.0;
    }
}

class ReclinerSeat extends Seat {
    public ReclinerSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 400.0;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Show {
    private String title;
    private String time;
    private Map<String, Seat> bookedSeats;

    public Show(String title, String time) {
        this.title = title;
        this.time = time;
        this.bookedSeats = new HashMap<>();
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public boolean isSeatBooked(String seatNumber) {
        return bookedSeats.containsKey(seatNumber);
    }

    public void bookSeat(Seat seat) {
        bookedSeats.put(seat.getSeatNumber(), seat);
    }

    public void releaseSeat(String seatNumber) {
        bookedSeats.remove(seatNumber);
    }
}

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private double totalAmount;
    private boolean active;

    public Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>(seats);
        this.totalAmount = 0.0;
        for (Seat s : seats) {
            this.totalAmount += s.getPrice();
        }
        this.active = true;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isActive() {
        return active;
    }

    public void cancel() {
        this.active = false;
        for (Seat s : seats) {
            show.releaseSeat(s.getSeatNumber());
        }
    }
}

class TicketCounter {
    public Booking bookTickets(Customer customer, Show show, List<Seat> requestedSeats) {
        if (requestedSeats == null || requestedSeats.isEmpty()) {
            System.out.println("No seats selected for booking.");
            return null;
        }

        if (requestedSeats.size() > 6) {
            System.out.println("Cannot book more than 6 seats per booking.");
            return null;
        }

        for (Seat s : requestedSeats) {
            if (show.isSeatBooked(s.getSeatNumber())) {
                System.out.println("Seat " + s.getSeatNumber() + " is already booked for this show.");
                return null;
            }
        }

        for (Seat s : requestedSeats) {
            show.bookSeat(s);
        }

        Booking booking = new Booking(customer, show, requestedSeats);
        List<String> seatNums = new ArrayList<>();
        for (Seat s : requestedSeats) {
            seatNums.add(s.getSeatNumber());
        }

        System.out.printf("Booking confirmed for %s: %s. Total: ₹%.2f.%n",
                customer.getName(), String.join(", ", seatNums), booking.getTotalAmount());
        return booking;
    }

    public void cancelBooking(Booking booking, boolean isBeforeShowStart) {
        if (booking != null && booking.isActive()) {
            if (isBeforeShowStart) {
                List<String> seatNums = new ArrayList<>();
                for (Seat s : booking.getSeats()) {
                    seatNums.add(s.getSeatNumber());
                }
                booking.cancel();
                System.out.println(booking.getCustomer().getName() + "'s booking cancelled. Seats " +
                        String.join(", ", seatNums) + " released.");
            } else {
                System.out.println("Cannot cancel booking after show has started.");
            }
        }
    }
}

public class M3 {
    public static void main(String[] args) {
        System.out.println("=== The Campus Premiere Ticket Counter ===");
        TicketCounter counter = new TicketCounter();

        Show show7PM = new Show("Movie Show", "7 PM");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Seat a1 = new RegularSeat("A1");
        Seat a2 = new RegularSeat("A2");
        Seat f5 = new PremiumSeat("F5");
        Seat r1 = new ReclinerSeat("R1");

        // Asha books Regular seats A1 and A2 and Premium seat F5 for the 7 PM show
        Booking bookingAsha = counter.bookTickets(asha, show7PM, Arrays.asList(a1, a2, f5));

        // Ravi attempts to book seat A2 for the same show
        counter.bookTickets(ravi, show7PM, Arrays.asList(a2));

        // Ravi books Recliner seat R1 for the same show
        Booking bookingRavi = counter.bookTickets(ravi, show7PM, Arrays.asList(r1));

        // Asha cancels her booking before the show starts
        counter.cancelBooking(bookingAsha, true);

        // Neha books seat A2 for the same show
        counter.bookTickets(neha, show7PM, Arrays.asList(a2));
    }
}
