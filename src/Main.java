import java.util.ArrayList;
import java.util.List;

class Person {
    String getId();
    String getName();
    String getEmail();
}

public interface Borrowable {
    void borrow();
}

class Member implements Person {
    private String memberID;
    private String name;
    private String email;

    public Member(String memberID, String name, String email) {
        this.memberID = memberID;
        this.name = name;
        this.email = email;
    }

    @Override
    public String getId() {
        return memberID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void borrowBook(Borrowable item) {
        item.borrow();
    }
}

public class Librarian implements Person {
    private String employeeID;
    private String name;
    private String email;

    public Librarian(String employeeID, String name, String email) {
        this.employeeID = employeeID;
        this.name = name;
        this.email = email;
    }

    @Override
    public String getId() {
        return employeeID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void addBook(Library library, Book book) {
        library.getBooks().add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void removeBook(Library library, Book book) {
        library.getBooks().remove(book);
        System.out.println("Book removed: " + book.getTitle());
    }
}



public class Library {
    private String name;
    private String address;
    private List<Book> books = new ArrayList<>();
    private List<Librarian> librarians = new ArrayList<>();

    public Library(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void listBooks() {
        System.out.println("Books in library:");
        for (Book b : books) {
            System.out.println("- " + b.getTitle());
        }
    }

    public void employsLibrarian(Librarian librarian) {
        librarians.add(librarian);
        System.out.println("Librarian employed: " + librarian.getName());
    }
}

public class Book implements Borrowable {
    private String title;
    private String author;
    private String ISBN;
    private int publicationYear;
    private boolean available = true;

    public Book(String title, String author, String ISBN, int publicationYear) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public void borrow() {
        if (available) {
            available = false;
            System.out.println("Book borrowed: " + title);
        } else {
            System.out.println("Book is already borrowed.");
        }
    }
}

public class Magazine implements Borrowable {
    private String title;
    private String issueDate;
    private boolean available = true;

    public Magazine(String title, String issueDate) {
        this.title = title;
        this.issueDate = issueDate;
    }

    @Override
    public void borrow() {
        if (available) {
            available = false;
            System.out.println("Magazine borrowed: " + title + " (" + issueDate + ")");
        } else {
            System.out.println("Magazine is already borrowed.");
        }
    }
}

public class BorrowingTransactions {
    private String transactionID;
    private String borrowDate;
    private String returnDate;

    public BorrowingTransactions(String transactionID, String borrowDate, String returnDate) {
        this.transactionID = transactionID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public boolean returned() {
        return returnDate != null && !returnDate.isEmpty();
    }

    public void transactionDetails() {
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Borrow Date: " + borrowDate);
        System.out.println("Return Date: " + returnDate);
    }
}

public class Main {
    public static void main(String[] args) {

        Library library = new Library("City Library", "123 Main St");


        Librarian librarian = new Librarian("L001", "Alice", "alice@library.com");
        library.employsLibrarian(librarian);


        Book book1 = new Book("Java Basics", "John Doe", "123456789", 2020);
        Book book2 = new Book("Advanced Java", "Jane Smith", "987654321", 2021);


        librarian.addBook(library, book1);
        librarian.addBook(library, book2);


        Magazine mag1 = new Magazine("Tech Monthly", "August 2025");


        library.listBooks();


        Member member = new Member("M001", "Bob", "bob@email.com");


        member.borrowBook(book1);


        member.borrowBook(book1);


        member.borrowBook(mag1);


        BorrowingTransactions transaction = new BorrowingTransactions(
                "T001", "2025-08-12", ""
        );
        transaction.transactionDetails();
        System.out.println("Returned? " + transaction.returned());
    }
}
