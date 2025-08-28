// Write a program to create a Library Book management system, 
// demonstrating object relationships 
// Hint => 
// a. Create a Book class with private variables: bookId (String), title (String), author 
// (String), 
// isAvailable (boolean), and static variables totalBooks (int), 
// availableBooks (int) 
// b. Create a constructor for Book class and methods: issueBook(), returnBook(), 
// displayBookInfo() 
// c. Create a Member class with private variables: memberId (String), memberName 
// (String), booksIssued (String array to store book IDs), bookCount (int to track number 
// of books issued) 
// d. Create methods in Member class: borrowBook(Book book) which checks if book 
// is available and updates both book and member status, returnBook(String 
// bookId, Book[] books) to return a specific book 
// 3 
// e. Create static methods in both classes to generate unique IDs and track statistics 
// f. In main, create arrays of Book and Member objects, demonstrate borrowing and 
// returning books, showing how objects interact with each other 


public class LibraryManagementSystem {

    static class Book {
        private String bookId;
        private String title;
        private String author;
        private boolean isAvailable;
        private static int totalBooks = 0;
        private static int availableBooks = 0;

        // Constructor
        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.bookId = generateBookId();
            this.isAvailable = true;
            totalBooks++;
            availableBooks++;
        }

        // Static method to generate unique book ID
        public static String generateBookId() {
            return String.format("B%03d", totalBooks + 1);
        }

        // Issue book
        public void issueBook() {
            if (isAvailable) {
                isAvailable = false;
                availableBooks--;
            } else {
                System.out.println("Book " + bookId + " is already issued.");
            }
        }

        // Return book
        public void returnBook() {
            if (!isAvailable) {
                isAvailable = true;
                availableBooks++;
            } else {
                System.out.println("Book " + bookId + " was not issued.");
            }
        }

        // Display book info
        public void displayBookInfo() {
            System.out.println("Book ID     : " + bookId);
            System.out.println("Title       : " + title);
            System.out.println("Author      : " + author);
            System.out.println("Available   : " + isAvailable);
            System.out.println("---------------------------");
        }

        // Static methods for statistics
        public static int getTotalBooks() {
            return totalBooks;
        }

        public static int getAvailableBooks() {
            return availableBooks;
        }

        public String getBookId() {
            return bookId;
        }

        public boolean isAvailable() {
            return isAvailable;
        }
    }

    static class Member {
        private String memberId;
        private String memberName;
        private String[] booksIssued;
        private int bookCount;
        private static int totalMembers = 0;

        // Constructor
        public Member(String memberName) {
            this.memberName = memberName;
            this.memberId = generateMemberId();
            this.booksIssued = new String[5]; // Max 5 books
            this.bookCount = 0;
            totalMembers++;
        }

        // Static method to generate unique member ID
        public static String generateMemberId() {
            return String.format("M%03d", totalMembers + 1);
        }

        // Borrow book
        public void borrowBook(Book book) {
            if (bookCount >= booksIssued.length) {
                System.out.println("Member " + memberId + " has reached book limit.");
                return;
            }
            if (book.isAvailable()) {
                book.issueBook();
                booksIssued[bookCount++] = book.getBookId();
                System.out.println(memberName + " borrowed book " + book.getBookId());
            } else {
                System.out.println("Book " + book.getBookId() + " is not available.");
            }
        }

        // Return book
        public void returnBook(String bookId, Book[] books) {
            boolean found = false;
            for (int i = 0; i < bookCount; i++) {
                if (booksIssued[i].equals(bookId)) {
                    booksIssued[i] = null;
                    bookCount--;
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Book " + bookId + " not found in member's issued list.");
                return;
            }

            for (Book book : books) {
                if (book.getBookId().equals(bookId)) {
                    book.returnBook();
                    System.out.println(memberName + " returned book " + bookId);
                    return;
                }
            }
            System.out.println("Book " + bookId + " not found in library.");
        }

        // Display member info
        public void displayMemberInfo() {
            System.out.println("Member ID   : " + memberId);
            System.out.println("Name        : " + memberName);
            System.out.print("Books Issued: ");
            for (String id : booksIssued) {
                if (id != null) System.out.print(id + " ");
            }
            System.out.println("\n---------------------------");
        }

        public static int getTotalMembers() {
            return totalMembers;
        }
    }

    public static void main(String[] args) {
        // Create books
        Book[] books = new Book[3];
        books[0] = new Book("The Alchemist", "Paulo Coelho");
        books[1] = new Book("Clean Code", "Robert C. Martin");
        books[2] = new Book("Java: The Complete Reference", "Herbert Schildt");

        // Create members
        Member[] members = new Member[2];
        members[0] = new Member("Alice");
        members[1] = new Member("Bob");

        // Borrowing books
        members[0].borrowBook(books[0]);
        members[1].borrowBook(books[1]);
        members[0].borrowBook(books[1]); // Should show unavailable

        // Returning books
        members[1].returnBook("B002", books);
        members[0].borrowBook(books[1]); // Now available

        // Display info
        for (Book book : books) {
            book.displayBookInfo();
        }

        for (Member member : members) {
            member.displayMemberInfo();
        }

        // Show statistics
        System.out.println("Total Books     : " + Book.getTotalBooks());
        System.out.println("Available Books : " + Book.getAvailableBooks());
        System.out.println("Total Members   : " + Member.getTotalMembers());
    }
}
