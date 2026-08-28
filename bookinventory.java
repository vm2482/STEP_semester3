class books {
    String title;
    String author;
    int copies;
    books(String title, String author, int copies){
        this.title = title;
        this.author = author;
        this.copies = copies;
    }
    void printEntry(){
        System.out.println(title + " by " + author + " - " + copies + " copies available");
    }
}
public class bookinventory{
    public static void main(String[] args){
        books[] books = new books[4];
        books[0] = new books("Clean Code", "Robert C. Martin", 3);
        books[1] = new books("Effective Java", "Joshua Bloch", 5);
        books[2] = new books("Refactoring", "Martin Fowler", 0);
        books[3] = new books("Design Patterns", "GoF", 2);
        for (int i = 0;i<books.length;i++){
            books[i].printEntry();
        }
    }
}
