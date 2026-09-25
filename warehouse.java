interface Printable {
    String printLabel();
}

class PackageBox implements Printable{
    private String trackingId;
    public PackageBox(String trackingId) {
        this.trackingId = trackingId;
    }

    public String printLabel() {
        return "Package label: " + trackingId;
    }
}

class Invoice implements Printable {
    private String invoiceNumber;
    public Invoice(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String printLabel() {
        return "Invoice label: " + invoiceNumber;
    }
}

public class warehouse {
    static void printAll(Printable[] items) {
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i].printLabel());
        }
    }
    public static void main(String[] args) {
        PackageBox p = new PackageBox("TRK-88");
        Invoice i = new Invoice("INV-42");

        System.out.println(p.printLabel());
        System.out.println(i.printLabel());

        printAll(new Printable[]{p, i});
    }
}