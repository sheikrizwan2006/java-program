public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    // Default constructor → empty book
    public Book() {
        this("", "", "", true);
    }

    // Constructor with title and author
    public Book(String title, String author) {
        this(title, author, "Unknown ISBN", true);
    }

    // Constructor with all details
    public Book(String title, String author, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    // borrowBook → sets available = false if available
    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println(title + " has been borrowed.");
        } else {
            System.out.println(title + " is currently unavailable.");
        }
    }

    // returnBook → sets available = true
    public void returnBook() {
        isAvailable = true;
        System.out.println(title + " has been returned.");
    }

    // displayBookInfo
    public void displayBookInfo() {
        System.out.println("Book Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("------------------------");
    }

    // Main method to test
    public static void main(String[] args) {
        Book b1 = new Book();  // empty book
        Book b2 = new Book("1984", "George Orwell");
        Book b3 = new Book("The Hobbit", "J.R.R. Tolkien", "978-0261102217", true);

        b1.displayBookInfo();
        b2.displayBookInfo();
        b3.displayBookInfo();

        b2.borrowBook();
        b2.borrowBook(); // try to borrow again

        b3.borrowBook();

        b2.returnBook();
        b2.borrowBook();

        b2.displayBookInfo();
        b3.displayBookInfo();
    }
}