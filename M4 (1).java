import java.util.*;

abstract class Room {
    private String roomNumber;
    private String category;
    private double ratePerNight;
    private List<Reservation> activeReservations;

    public Room(String roomNumber, String category, double ratePerNight) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.ratePerNight = ratePerNight;
        this.activeReservations = new ArrayList<>();
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getRatePerNight() {
        return ratePerNight;
    }

    public abstract double calculatePrice(int nights);

    public boolean isAvailable(String dateRange) {
        for (Reservation r : activeReservations) {
            if (r.isActive()) {
                return false;
            }
        }
        return true;
    }

    public void addReservation(Reservation r) {
        activeReservations.add(r);
    }

    public void removeReservation(Reservation r) {
        activeReservations.remove(r);
    }

    @Override
    public String toString() {
        return category + " " + roomNumber;
    }
}

class StandardRoom extends Room {
    public StandardRoom(String roomNumber, double ratePerNight) {
        super(roomNumber, "Standard Room", ratePerNight);
    }

    @Override
    public double calculatePrice(int nights) {
        return nights * getRatePerNight();
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String roomNumber, double ratePerNight) {
        super(roomNumber, "Deluxe Room", ratePerNight);
    }

    @Override
    public double calculatePrice(int nights) {
        return nights * getRatePerNight();
    }
}

class Suite extends Room {
    public Suite(String roomNumber, double ratePerNight) {
        super(roomNumber, "Suite", ratePerNight);
    }

    @Override
    public double calculatePrice(int nights) {
        return nights * getRatePerNight();
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

class Reservation {
    private Customer customer;
    private Room room;
    private String dateRange;
    private int nights;
    private double totalPrice;
    private boolean active;

    public Reservation(Customer customer, Room room, String dateRange, int nights) {
        this.customer = customer;
        this.room = room;
        this.dateRange = dateRange;
        this.nights = nights;
        this.totalPrice = room.calculatePrice(nights);
        this.active = true;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public String getDateRange() {
        return dateRange;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isActive() {
        return active;
    }

    public void cancel() {
        this.active = false;
        room.removeReservation(this);
    }
}

class HotelBookingSystem {
    public boolean checkAvailability(Room room, String dateRange) {
        boolean available = room.isAvailable(dateRange);
        if (available) {
            System.out.println(room + " is available from " + dateRange + ".");
        } else {
            System.out.println(room + " is not available from " + dateRange + ".");
        }
        return available;
    }

    public Reservation reserveRoom(Customer customer, Room room, String dateRange, int nights) {
        if (!room.isAvailable(dateRange)) {
            System.out.println(room + " is not available from " + dateRange + ".");
            return null;
        }
        Reservation reservation = new Reservation(customer, room, dateRange, nights);
        room.addReservation(reservation);
        System.out.printf("Reservation confirmed for %s, %s (%s). Price: $%.2f.%n",
                customer.getName(), room, dateRange, reservation.getTotalPrice());
        return reservation;
    }

    public void cancelReservation(Reservation reservation, boolean beforeDeadline) {
        if (reservation != null && reservation.isActive()) {
            if (beforeDeadline) {
                reservation.cancel();
                System.out.println("Reservation for " + reservation.getCustomer().getName() +
                        ", " + reservation.getRoom() + " (" + reservation.getDateRange() + ") cancelled successfully.");
            } else {
                System.out.println("Cancellation failed: Passed cancellation deadline.");
            }
        }
    }
}

public class M4 {
    public static void main(String[] args) {
        System.out.println("=== Hotel Booking System ===");
        HotelBookingSystem system = new HotelBookingSystem();

        Customer customerA = new Customer("Customer A");
        Customer customerB = new Customer("Customer B");
        Customer customerC = new Customer("Customer C");

        Room room101 = new StandardRoom("101", 100.0);
        Room room201 = new DeluxeRoom("201", 150.0);

        // Customer A checks availability for Standard Room 101 from Jan 1 to Jan 5
        system.checkAvailability(room101, "Jan 1 to Jan 5");

        // Customer A reserves Standard Room 101 from Jan 1 to Jan 5 (4 nights)
        Reservation resA = system.reserveRoom(customerA, room101, "Jan 1-5", 4);

        // Customer B attempts to reserve Standard Room 101 from Jan 3 to Jan 7
        system.reserveRoom(customerB, room101, "Jan 3 to Jan 7", 4);

        // Customer A cancels reservation for Standard Room 101 for Jan 1-5 (before deadline)
        system.cancelReservation(resA, true);

        // Customer C reserves Deluxe Room 201 from Feb 10 to Feb 12 (2 nights)
        system.reserveRoom(customerC, room201, "Feb 10-12", 2);
    }
}
