// Library Management System with Fine 
// Calculation 
// Topic: Real-world Application with Business Logic 
// Problem Statement: Develop a comprehensive library management system with member 
// management and fine calculations. 
// Requirements: 
// ● Create a Book class with: bookId (String), title (String), author (String), isbn 
// (String), category (String), isIssued (boolean), issueDate (String), dueDate 
// (String) 
// ● Create a Member class with: memberId (String), memberName (String), memberType 
// (String), booksIssued (Book array), totalFines (double), membershipDate (String) 
// ● Include static variables: totalBooks (int), totalMembers (int), libraryName 
// (String), finePerDay (double), maxBooksAllowed (int) 
// ● Implement methods: issueBook(), returnBook(), calculateFine(), 
// renewBook(), searchBooks(), reserveBook() 
// ● Create different member types (Student, Faculty, General) with different borrowing 
// privileges 
// ● Include static methods: generateLibraryReport(), getOverdueBooks(), 
// getMostPopularBooks() 
// ● Implement automatic fine calculation based on overdue days 
// Deliverables: Full library system with member management, book operations, fine calculations, 
// and comprehensive reporting features.


import java.util.*;

public class LibraryManagementSystem {

    static class Book {
        String bookId, title, author, isbn, category;
        boolean isIssued;
        String issueDate, dueDate;

        static int totalBooks = 0;

        public Book(String title, String author, String isbn, String category) {
            this.bookId = generateBookId();
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.category = category;
            this.isIssued = false;
            this.issueDate = "";
            this.dueDate = "";
            totalBooks++;
        }

        public static String generateBookId() {
            return "B" + (totalBooks + 1);
        }

        public void displayBookInfo() {
            System.out.println(bookId + " | " + title + " | " + author + " | " + category + " | Issued: " + isIssued);
        }
    }

    static class Member {
        String memberId, memberName, memberType, membershipDate;
        Book[] booksIssued;
        double totalFines;

        static int totalMembers = 0;
        static String libraryName = "Central Library";
        static double finePerDay = 2.0;
        static int maxBooksAllowed = 3;

        public Member(String memberName, String memberType, String membershipDate) {
            this.memberId = generateMemberId();
            this.memberName = memberName;
            this.memberType = memberType;
            this.membershipDate = membershipDate;
            this.booksIssued = new Book[maxBooksAllowed];
            this.totalFines = 0.0;
            totalMembers++;
        }

        public static String generateMemberId() {
            return "M" + (totalMembers + 1);
        }

        public void issueBook(Book book, String issueDate, String dueDate) {
            if (book.isIssued) {
                System.out.println("Book already issued.");
                return;
            }
            for (int i = 0; i < booksIssued.length; i++) {
                if (booksIssued[i] == null) {
                    booksIssued[i] = book;
                    book.isIssued = true;
                    book.issueDate = issueDate;
                    book.dueDate = dueDate;
                    System.out.println("Book " + book.bookId + " issued to " + memberName);
                    return;
                }
            }
            System.out.println("Max book limit reached for " + memberType);
        }

        public void returnBook(String bookId, String returnDate) {
            for (int i = 0; i < booksIssued.length; i++) {
                if (booksIssued[i] != null && booksIssued[i].bookId.equals(bookId)) {
                    double fine = calculateFine(booksIssued[i].dueDate, returnDate);
                    totalFines += fine;
                    booksIssued[i].isIssued = false;
                    booksIssued[i].issueDate = "";
                    booksIssued[i].dueDate = "";
                    System.out.println("Book " + bookId + " returned. Fine: ₹" + fine);
                    booksIssued[i] = null;
                    return;
                }
            }
            System.out.println("Book not found in issued list.");
        }

        public double calculateFine(String dueDate, String returnDate) {
            try {
                String[] d1 = dueDate.split("-");
                String[] d2 = returnDate.split("-");
                Calendar due = Calendar.getInstance();
                Calendar ret = Calendar.getInstance();
                due.set(Integer.parseInt(d1[0]), Integer.parseInt(d1[1]) - 1, Integer.parseInt(d1[2]));
                ret.set(Integer.parseInt(d2[0]), Integer.parseInt(d2[1]) - 1, Integer.parseInt(d2[2]));
                long diff = (ret.getTimeInMillis() - due.getTimeInMillis()) / (1000 * 60 * 60 * 24);
                return diff > 0 ? diff * finePerDay : 0;
            } catch (Exception e) {
                return 0;
            }
        }

        public void renewBook(String bookId, String newDueDate) {
            for (Book b : booksIssued) {
                if (b != null && b.bookId.equals(bookId)) {
                    b.dueDate = newDueDate;
                    System.out.println("Book " + bookId + " renewed. New due date: " + newDueDate);
                    return;
                }
            }
            System.out.println("Book not found for renewal.");
        }

        public void displayMemberInfo() {
            System.out.println("Member ID: " + memberId + " | Name: " + memberName + " | Type: " + memberType + " | Fines: ₹" + totalFines);
        }

        public static void generateLibraryReport(Book[] books, Member[] members) {
            System.out.println("=== " + libraryName + " Report ===");
            System.out.println("Total Books   : " + Book.totalBooks);
            System.out.println("Total Members : " + totalMembers);
            double totalFine = 0;
            for (Member m : members) totalFine += m.totalFines;
            System.out.println("Total Fines   : ₹" + totalFine);
            System.out.println("==============================");
        }

        public static void getOverdueBooks(Book[] books, String currentDate) {
            System.out.println("Overdue Books as of " + currentDate + ":");
            for (Book b : books) {
                if (b.isIssued && compareDates(currentDate, b.dueDate) > 0) {
                    System.out.println(b.bookId + " - " + b.title + " (Due: " + b.dueDate + ")");
                }
            }
        }

        public static int compareDates(String d1, String d2) {
            try {
                String[] a = d1.split("-");
                String[] b = d2.split("-");
                Calendar c1 = Calendar.getInstance();
                Calendar c2 = Calendar.getInstance();
                c1.set(Integer.parseInt(a[0]), Integer.parseInt(a[1]) - 1, Integer.parseInt(a[2]));
                c2.set(Integer.parseInt(b[0]), Integer.parseInt(b[1]) - 1, Integer.parseInt(b[2]));
                return c1.compareTo(c2);
            } catch (Exception e) {
                return 0;
            }
        }

        public static void getMostPopularBooks(Book[] books) {
            System.out.println("Popular Books (Issued):");
            for (Book b : books) {
                if (b.isIssued) {
                    System.out.println(b.bookId + " - " + b.title);
                }
            }
        }
    }

    public static void main(String[] args) {
        Book[] books = {
            new Book("Java Basics", "Herbert Schildt", "ISBN001", "Programming"),
            new Book("Data Structures", "Mark Allen", "ISBN002", "CS"),
            new Book("Operating Systems", "Galvin", "ISBN003", "CS")
        };

        Member m1 = new Member("Alice", "Student", "2025-01-10");
        Member m2 = new Member("Bob", "Faculty", "2025-02-15");

        m1.issueBook(books[0], "2025-08-01", "2025-08-10");
        m2.issueBook(books[1], "2025-08-05", "2025-08-15");

        m1.returnBook("B1", "2025-08-12"); // 2 days late
        m2.renewBook("B2", "2025-08-20");

        m1.displayMemberInfo();
        m2.displayMemberInfo();

        Member.generateLibraryReport(books, new Member[]{m1, m2});
        Member.getOverdueBooks(books, "2025-08-21");
        Member.getMostPopularBooks(books);
    }
}
