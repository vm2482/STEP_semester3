import java.util.*;

abstract class Vehicle {
    private String registrationNumber;
    private String model;
    private boolean available;

    public Vehicle(String registrationNumber, String model) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.available = true;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculateRentalCharge(int days);

    @Override
    public String toString() {
        return model;
    }
}

class Sedan extends Vehicle {
    private double dailyRate;

    public Sedan(String registrationNumber, String model, double dailyRate) {
        super(registrationNumber, model);
        this.dailyRate = dailyRate;
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * dailyRate;
    }
}

class SUV extends Vehicle {
    private double dailyRate;

    public SUV(String registrationNumber, String model, double dailyRate) {
        super(registrationNumber, model);
        this.dailyRate = dailyRate;
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * dailyRate;
    }
}

class Truck extends Vehicle {
    private double dailyRate;

    public Truck(String registrationNumber, String model, double dailyRate) {
        super(registrationNumber, model);
        this.dailyRate = dailyRate;
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * dailyRate;
    }
}

class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Rental {
    private Customer customer;
    private Vehicle vehicle;
    private int durationDays;
    private double totalCharge;
    private boolean active;

    public Rental(Customer customer, Vehicle vehicle, int durationDays) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.durationDays = durationDays;
        this.totalCharge = vehicle.calculateRentalCharge(durationDays);
        this.active = true;
        vehicle.setAvailable(false);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public boolean isActive() {
        return active;
    }

    public void returnVehicle() {
        this.active = false;
        vehicle.setAvailable(true);
    }
}

class VehicleRentalSystem {
    public Rental rentVehicle(Customer customer, Vehicle vehicle, int days) {
        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getModel() + " is currently unavailable.");
            return null;
        }
        Rental rental = new Rental(customer, vehicle, days);
        System.out.printf("%s rented successfully by %s. Rental charge: $%.2f%n",
                vehicle.getModel(), customer.getName(), rental.getTotalCharge());
        return rental;
    }

    public void returnVehicle(Rental rental) {
        if (rental != null && rental.isActive()) {
            rental.returnVehicle();
            System.out.println(rental.getVehicle().getModel() + " returned by " + rental.getCustomer().getName() + ".");
        }
    }
}

public class M1 {
    public static void main(String[] args) {
        System.out.println("=== Vehicle Rental System ===");
        VehicleRentalSystem system = new VehicleRentalSystem();

        Customer c1 = new Customer("C1", "Customer 1");
        Customer c2 = new Customer("C2", "Customer 2");
        Customer c3 = new Customer("C3", "Customer 3");

        Vehicle sedanA = new Sedan("V101", "Sedan A", 50.0);
        Vehicle suvB = new SUV("V102", "SUV B", 80.0);

        // Customer 1 rents Sedan A for 3 days
        Rental r1 = system.rentVehicle(c1, sedanA, 3);

        // Customer 2 attempts to rent Sedan A for 2 days (while it's rented by Customer 1)
        system.rentVehicle(c2, sedanA, 2);

        // Customer 1 returns Sedan A
        system.returnVehicle(r1);

        // Customer 3 rents SUV B for 5 days
        system.rentVehicle(c3, suvB, 5);
    }
}
