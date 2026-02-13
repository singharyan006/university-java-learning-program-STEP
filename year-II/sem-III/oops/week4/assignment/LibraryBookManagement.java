//  Library Book Management 
// �
// �
 
// Design a system for managing Library Books. 
// ● Class Book with fields: String title, String author, String isbn, boolean 
// isAvailable. 
// ● Constructor overloading: 
// ○ Default constructor → empty book. 
// ○ Constructor with title and author. 
// ○ Constructor with all details. 
// ● Methods: 
// ○ borrowBook() → sets available = false. 
// ○ returnBook() → sets available = true. 
// ○ displayBookInfo(). 
// ● In main(): Create books, borrow/return them, display info. 


class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    // Default constructor
    public Book() {
        this("Untitled", "Unknown", "0000000000", true);
    }

    // Constructor with title and author
    public Book(String title, String author) {
        this(title, author, "0000000000", true);
    }

    // Full constructor
    public Book(String title, String author, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    // Borrow the book
    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("📕 '" + title + "' has been borrowed.");
        } else {
            System.out.println("⚠️ '" + title + "' is already borrowed.");
        }
    }

    // Return the book
    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("📗 '" + title + "' has been returned.");
        } else {
            System.out.println("ℹ️ '" + title + "' was not borrowed.");
        }
    }

    // Display book info
    public void displayBookInfo() {
        System.out.println("📖 Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("---------------------------");
    }
}

public class LibraryBookManagement {
    public static void main(String[] args) {
        Book b1 = new Book();
        Book b2 = new Book("The Alchemist", "Paulo Coelho");
        Book b3 = new Book("1984", "George Orwell", "9780451524935", true);

        b1.displayBookInfo();
        b2.displayBookInfo();
        b3.displayBookInfo();

        b2.borrowBook();
        b2.displayBookInfo();

        b2.returnBook();
        b2.displayBookInfo();
    }
}
