// Author: Muhammad Akbar Reishandy
// Author: Silvi Kusuma Wardhani G.
package logic.books;

import logic.Book;

public class Dictionary extends Book implements IType {
    private final String language;

    public Dictionary(int id, String title, String author, String publisher, int year, int pages, String language) {
        super(id, title, author, publisher, year, pages);
        this.language = language;
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
                "type     : Dictionary" +
                "\ncategory : Non-Fiction" +
                "\nlanguage : " + language;
    }

    @Override
    public String getType() {
        return "Dictionary";
    }

    @Override
    public String getDetail() {
        return language;
    }
}
