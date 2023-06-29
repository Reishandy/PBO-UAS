// Author: Muhammad Akbar Reishandy
package logic;

public abstract class Book {
    // For identification
    private final String title;
    private final int id;

    // For properties (to ber overwritten
    private final String author, publisher;
    private final int year, pages;

    public Book(int id, String title, String author, String publisher, int year, int pages) {
        this.title = title;
        this.id = id;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;

        if (id != book.id) return false;
        return title.equals(book.title);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + id;
        return result;
    }

    // Will be overwritten again
    @Override
    public String toString() {
        return """
               Book
               title    : %s
               id       : %d
               author   : %s
               publisher: %s
               year     : %d
               pages    : %d
               """.formatted(title, id, author, publisher, year, pages);
    }
}
