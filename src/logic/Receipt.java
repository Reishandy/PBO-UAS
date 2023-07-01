// Author: Muhammad Akbar Reishandy
package logic;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.regex.Pattern;

public class Receipt {
    private boolean returned;
    private Person borrower;
    private Book book;
    protected LocalDate borrowDate, returnDate;
    private final String id;

    public Receipt() {
        book = null;
        borrowDate = LocalDate.now();
        returnDate = LocalDate.now();
        id = UUID.randomUUID().toString().split("-")[0];
    }

    public void setBorrower(Person borrower) {
        this.borrower = borrower;
    }

    public void addBook(Book book) {
        this.book = book;
    }

    public int setReturnDate(String date) {
        // Limit on return date is 2 week
        LocalDate inputDate;

        // Check regex for inputted date, return 1 if wrong
        if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2}", date)) return 1;

        // check if legit date, return 2 if wrong
        try {
            inputDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return 2;
        }

        // Check if date is more than 4 week, if more return 3
        LocalDate twoWeeksFromNow = LocalDate.now().plusWeeks(4);
        if (inputDate.isAfter(twoWeeksFromNow)) return 3;

        // Check if valid date, if valid add then return 0
        if (!inputDate.isBefore(borrowDate) && !inputDate.isEqual(borrowDate)) {
            returnDate = inputDate;
            return 0;
        }

        return -1; // Just not good stuff
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
            this.returned = false;
            return -1;
        }

        try {
            dateReturn = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            this.returned = false;
            return -1;
        }

        if (dateReturn.isBefore(returnDate) || dateReturn.isEqual(returnDate)) {
            this.returned = true;
            return 0;
        }

        this.returned = true;
        return (int) returnDate.until(dateReturn, ChronoUnit.DAYS);
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public String getBook() {
        return "%s (%d)".formatted(book.getTitle(), book.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receipt receipt)) return false;

        if (returned != receipt.returned) return false;
        if (!borrower.equals(receipt.borrower)) return false;
        if (!book.equals(receipt.book)) return false;
        if (!borrowDate.equals(receipt.borrowDate)) return false;
        return returnDate.equals(receipt.returnDate);
    }

    @Override
    public int hashCode() {
        int result = (returned ? 1 : 0);
        result = 31 * result + borrower.hashCode();
        result = 31 * result + book.hashCode();
        result = 31 * result + borrowDate.hashCode();
        result = 31 * result + returnDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        if (returnDate == null) return null;
        return """
                --Borrowing receipt--
                ID          : %s
                Borrower    : %s (%d) at %s
                Book/s      : %s
                Date borrow : %s
                Date return : %s
                Returned    : %s
                """.formatted( getId(),
                borrower.getName(), borrower.getId(), borrower.getEmail(), getBook(),
                borrowDate, returnDate, returned? "Yes" : "No");
    }

    public String getId() {
        return id;
    }
}
