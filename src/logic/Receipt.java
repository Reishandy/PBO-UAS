// Author: Muhammad Akbar Reishandy
package logic;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Receipt {
    private boolean returned;
    private Person borrower;
    private final ArrayList<Book> books;
    protected LocalDate borrowDate, returnDate;

    public Receipt() {
        books = new ArrayList<>();
        borrowDate = LocalDate.now();
    }

    public void setBorrower(Person borrower) {
        this.borrower = borrower;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean setReturnDate(String date) {
        // Check regex for inputted date
        if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2}", date)) return false;
        // check if legit date
        try {
            returnDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        // Check if valid date, if valid return true
        return !returnDate.isBefore(borrowDate) && !returnDate.isEqual(borrowDate);
    }

    public boolean isReturned() {
        return returned;
    }

    public int returnBook(String date) {
        // Return -1 if there is something wrong
        // Return 0 if good
        // Return positive integer if there is penalty

        LocalDate dateReturn;
        if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2}", date)) {
            return -1;
        }

        try {
            dateReturn = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return -1;
        }

        if (dateReturn.isBefore(returnDate) || dateReturn.isEqual(returnDate)) {
            this.returned = true;
            return 0;
        }

        this.returned = false;
        return (int) returnDate.until(dateReturn, ChronoUnit.DAYS);
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public String listBook() {
        StringBuilder books = new StringBuilder();
        for (Book book : this.books)
            books.append(book.getTitle()).append("(%d), ".formatted(book.getId()));
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
                Returned    : %s
                """.formatted(
                borrower.getName(), borrower.getId(), listBook(),
                borrowDate, returnDate, returned? "Yes" : "No");
    }
}
