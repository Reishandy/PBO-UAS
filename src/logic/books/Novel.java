// Author: Muhammad Akbar Reishandy
// Author: Silvi Kusuma Wardhani G.
package logic.books;

import logic.Book;

public class Novel extends Book implements IType {
    private final String genre;

    public Novel(int id, String title, String author, String publisher, int year, int pages, String genre) {
        super(id, title, author, publisher, year, pages);
        this.genre = genre;
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getAuthor() {
        return super.getAuthor();
    }

    @Override
    public String getPublisher() {
        return super.getPublisher();
    }

    @Override
    public int getYear() {
        return super.getYear();
    }

    @Override
    public int getPages() {
        return super.getPages();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString() +
               "type     : Novel\n" +
               "category : Fiction\n" +
               "genre    : " + genre;
    }

    @Override
    public String getDetail() {
        return genre;
    }

    @Override
    public String getType() {
        return "Novel";
    }
}
