// Author: Muhammad Akbar Reishandy
package gui.borrow;

import gui.Menu;
import logic.Book;
import logic.Bookshelf;
import logic.books.Novel;
import logic.books.TextBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SearchBook extends Component {
    static Book borrowBook;
    static Bookshelf shelf = new Bookshelf();
    private JLabel title;
    private JTextField searchTextField;
    private JButton searchButton;
    private JTextArea listOfBook;
    private JButton ButtonContinue;
    private JButton ButtonBack;
    private JButton addBookButton;
    private JPanel searchBook;
    private JTextArea displayDetailBookArea;

    public SearchBook() {
        // init bookshelf and default book
        if (shelf.listBooks().length == 0) {
            TextBook textBook = new TextBook(1, "Matematika dasar", "Ayam", "Kuxinf.inc", 2001, 190, "Matematika");
            Novel novel = new Novel(2, "Ayam kucinta", "Kucing", "Kuxinf.inc", 2011, 320, "Romance");
            shelf.addBook(textBook);
            shelf.addBook(novel);
        }

        // Init forms
        Menu menu = new Menu();

        // Display books
        listOfBook.setText(null);
        String[] arrayBooks = shelf.listBooks();
        for (String book : arrayBooks) {
            listOfBook.append(book + "\n");
        }

        searchButton.addActionListener(e -> {
            int inputID = 0;
            String input = searchTextField.getText();

            try {
                inputID = Integer.parseInt(input);
            } catch (NumberFormatException ignored) {}

            if (inputID != 0) {
                borrowBook = shelf.search(inputID);
            } else {
                borrowBook = shelf.search(input);
            }

            if (borrowBook == null || searchTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Buku tidak ditemukan",
                        "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                displayDetailBookArea.setText(borrowBook.toString());
            }
        });

        ButtonBack.addActionListener(e -> {
            menu.change(PersonID.getPersonID());
        });

        ButtonContinue.addActionListener(e -> {
            if (borrowBook == null) {
                JOptionPane.showMessageDialog(this, "Pilih buku terlebih dahulu",
                        "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                menu.change(Receipt.getReceipt());
            }
        });

        addBookButton.addActionListener(e -> {
            menu.change(AddBook.getAddBook());
        });
        searchTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                searchTextField.setText(null);
            }
        });
    }

    public static JPanel getSearchBook() {
        return new SearchBook().searchBook;
    }
}
