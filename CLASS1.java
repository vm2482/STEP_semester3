import java.util.*;

class Book {
    private String isbn;
    private String title;

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "(" + isbn + ", " + title + ")";
    }
}

public class M1 {
    
    /**
     * Efficiently finds a book's title given its ISBN in a pre-sorted catalog.
     * Uses Binary Search to achieve O(log n) time complexity.
     * 
     * @param catalog    List of Book records pre-sorted by ISBN in ascending order.
     * @param targetIsbn ISBN string to search for.
     * @return Title of the book if found, or "Not Found" otherwise.
     */
    public static String findBook(List<Book> catalog, String targetIsbn) {
        if (catalog == null || targetIsbn == null || catalog.isEmpty()) {
            return "Not Found";
        }

        int low = 0;
        int high = catalog.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Book midBook = catalog.get(mid);
            int comparison = midBook.getIsbn().compareTo(targetIsbn);

            if (comparison == 0) {
                return midBook.getTitle();
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 1: Library Catalog Lookup ===");

        List<Book> catalog = Arrays.asList(
            new Book("0001112223", "Introduction to Algebra"),
            new Book("0002223334", "Beginning Python"),
            new Book("0003334445", "Classic Mythology"),
            new Book("0004445556", "Data and Society"),
            new Book("0005556667", "European History")
        );

        // Example 1
        String targetIsbn1 = "0003334445";
        String result1 = findBook(catalog, targetIsbn1);
        System.out.println("Target ISBN: " + targetIsbn1);
        System.out.println("Output: " + result1); // Expected: "Classic Mythology"

        // Example 2
        String targetIsbn2 = "0009998887";
        String result2 = findBook(catalog, targetIsbn2);
        System.out.println("\nTarget ISBN: " + targetIsbn2);
        System.out.println("Output: " + result2); // Expected: "Not Found"
    }
}
