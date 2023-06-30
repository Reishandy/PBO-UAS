package logic.books;

import logic.Book;

public class Dictionary extends Book implements IType {
    // TODO: tambahkan kamus bahasa apa dan juga di tostring
    public Dictionary(int id, String title, String author, String publisher, int year, int pages) {
        super(id, title, author, publisher, year, pages);
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
        return super.toString()  + "type     : Dictionary\ncategory : Non-Fiction";
    }

    @Override
    public String getType() {
        return "Dictionary";
    }
}
