// Author: Muhammad Akbar Reishandy
package logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

public class Receipt {
    private Person borrower;
    private ArrayList<Book> books;
    protected LocalDate borrowDate, returnDate;

    public Receipt() {
        books = new ArrayList<>();
        borrowDate = LocalDate.now();
    }

    public Person getBorrower() {
        return borrower;
    }

    public void setBorrower(Person borrower) {
        this.borrower = borrower;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addBook(ArrayList<Book> books) {
        this.books = books;
    }

    public boolean setReturnDate(String date) {
        // Check regex for inputted date, then convert that to LocalDate
        if (Pattern.matches("\\d{4}-\\d{2}-\\d{2}", date)) {
            returnDate = LocalDate.parse(date);
            return true;
        } else return false;
    }

    public String listBook() {
        StringBuilder books = new StringBuilder();
        for (Book book : this.books) {
            books.append(book.getTitle()).append("(%d), ".formatted(book.getId()));
        }
        return books.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receipt receipt)) return false;

        if (!borrower.equals(receipt.borrower)) return false;
        if (!books.equals(receipt.books)) return false;
        if (!borrowDate.equals(receipt.borrowDate)) return false;
        return returnDate.equals(receipt.returnDate);
    }

    @Override
    public int hashCode() {
        int result = borrower.hashCode();
        result = 31 * result + books.hashCode();
        result = 31 * result + borrowDate.hashCode();
        result = 31 * result + returnDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        if (returnDate == null) return null;
        return """
                --Borrowing receipt--
                Borrower    : %s (%d)
                Book/s      : %s
                Date borrow : %s
                Date return : %s
                """.formatted(borrower.getName(), borrower.getId(), listBook(), borrowDate, returnDate);
    }
}
