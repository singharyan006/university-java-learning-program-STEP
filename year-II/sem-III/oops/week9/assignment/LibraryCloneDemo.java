import java.util.ArrayList;
import java.util.List;

class Book implements Cloneable {
    private String title;

    public Book(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    protected Book clone() {
        return new Book(this.title);
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "'}";
    }
}

class Library implements Cloneable {
    private List<Book> books;

    public Library(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    // Shallow clone: copies reference to the same book list
    protected Library shallowClone() throws CloneNotSupportedException {
        return (Library) super.clone();
    }

    // Deep clone: clones each Book object individually
    protected Library deepClone() {
        List<Book> clonedBooks = new ArrayList<>();
        for (Book book : books) {
            clonedBooks.add(book.clone());
        }
        return new Library(clonedBooks);
    }

    @Override
    public String toString() {
        return "Library{books=" + books + "}";
    }
}

public class LibraryCloneDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        List<Book> originalBooks = new ArrayList<>();
        originalBooks.add(new Book("Java Fundamentals"));
        originalBooks.add(new Book("Operating Systems"));

        Library originalLibrary = new Library(originalBooks);

        Library shallowClonedLibrary = originalLibrary.shallowClone();
        Library deepClonedLibrary = originalLibrary.deepClone();

        // Modify a book in shallow clone
        shallowClonedLibrary.getBooks().get(0).setTitle("Modified Java Book");

        System.out.println("Original Library (after shallow modification):");
        System.out.println(originalLibrary);

        System.out.println("Shallow Cloned Library:");
        System.out.println(shallowClonedLibrary);

        System.out.println("Deep Cloned Library:");
        System.out.println(deepClonedLibrary);
    }
}
