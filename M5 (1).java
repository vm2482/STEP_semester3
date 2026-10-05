import java.util.*;

interface PaymentMethod {
    String getMethodName();
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public String getMethodName() {
        return "Credit Card";
    }

    @Override
    public boolean processPayment(double amount) {
        // Simulated successful payment
        return true;
    }
}

class PayPalPayment implements PaymentMethod {
    private boolean shouldSucceed;

    public PayPalPayment(boolean shouldSucceed) {
        this.shouldSucceed = shouldSucceed;
    }

    public PayPalPayment() {
        this(false); // Default to failure for demonstration if specified
    }

    @Override
    public String getMethodName() {
        return "PayPal";
    }

    @Override
    public boolean processPayment(double amount) {
        return shouldSucceed;
    }
}

class BankTransferPayment implements PaymentMethod {
    @Override
    public String getMethodName() {
        return "Bank Transfer";
    }

    @Override
    public boolean processPayment(double amount) {
        return true;
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
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

enum OrderStatus {
    PENDING,
    PAID;

    @Override
    public String toString() {
        switch (this) {
            case PENDING: return "Pending";
            case PAID: return "Paid";
            default: return name();
        }
    }
}

class Order {
    private String orderId;
    private Customer customer;
    private List<OrderItem> items;
    private OrderStatus status;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        System.out.println("Order created for " + customer.getName() + ".");
    }

    public void addProduct(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getOrderId() {
        return orderId;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public boolean processPayment(PaymentMethod paymentMethod) {
        if (items.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return false;
        }

        System.out.println("Payment initiated via " + paymentMethod.getMethodName() + " for Order " + customer.getName().replace("Customer ", "") + ".");
        boolean success = paymentMethod.processPayment(calculateTotal());

        if (success) {
            this.status = OrderStatus.PAID;
            System.out.println("Payment for Order " + customer.getName().replace("Customer ", "") + " successful. Order status: " + status + ".");
        } else {
            System.out.println("Payment for Order " + customer.getName().replace("Customer ", "") + " failed. Order status: " + status + ".");
        }
        return success;
    }
}

public class M5 {
    public static void main(String[] args) {
        System.out.println("=== Payment Processing for a Shopping System ===");

        Product prodA = new Product("Product A", 50.0);
        Product prodB = new Product("Product B", 30.0);
        Product prodC = new Product("Product C", 100.0);

        Customer custX = new Customer("Customer X");
        Customer custY = new Customer("Customer Y");
        Customer custZ = new Customer("Customer Z");

        // Customer X creates an order with Product A (qty 2) and Product B (qty 1)
        Order orderX = new Order("ORD101", custX);
        orderX.addProduct(prodA, 2);
        orderX.addProduct(prodB, 1);

        // Customer X attempts to pay for the order using Credit Card. Credit Card payment is successful.
        orderX.processPayment(new CreditCardPayment());

        // Customer Y creates an empty order
        Order orderY = new Order("ORD102", custY);

        // Customer Y attempts to pay for the empty order
        orderY.processPayment(new CreditCardPayment());

        // Customer Z creates an order with Product C (qty 1)
        Order orderZ = new Order("ORD103", custZ);
        orderZ.addProduct(prodC, 1);

        // Customer Z attempts to pay for the order using PayPal. PayPal payment fails.
        orderZ.processPayment(new PayPalPayment(false));
    }
}
