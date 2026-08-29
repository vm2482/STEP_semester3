class book{
    String title;
    String isbn;
    public book(String title, String isbn){
        this.title = title;
        this.isbn = isbn;
        System.out.println(title + " | " + isbn + " | Catalogued: True");
    }
    public book(String title){
        this.title = title;
        System.out.println(title + " | PENDING | Catalogued: True");
    }
}
public class librarybook {
    public static void main(String[] args){
        book[] books = new book[4];
        books[0] = new book("Clean Code", "978-0132350884");
        books[1] = new book("Untitled Draft");
        books[2] = new book("1984", "9780451524935");
        books[3] = new book("Notes");

    }
}
