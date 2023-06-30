// Author: Muhammad Akbar Reishandy
package logic;

import java.util.ArrayList;

public class Bookshelf {
    private ArrayList<Book> books;

    public Bookshelf() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book search(String title) {
        for (Book book: books)
            if (book.getTitle() != null && book.getTitle().contains(title)) return book;
        return null;
    }

    public Book search(int id) {
        for (Book book: books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    // TODO: Add these function on main
    public String[] listTitle() {
        String[] list = new String[this.books.size()];
        for (int i = 0; i < list.length; i++) {
            list[i] = this.books.get(i).getTitle();
        }
        return list;
    }

    public int[] listId() {
        int[] list = new int[this.books.size()];
        for (int i = 0; i < list.length; i++) {
            list[i] = this.books.get(i).getId();
        }
        return list;
    }
}
