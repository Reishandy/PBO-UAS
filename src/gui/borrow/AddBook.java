// Author: Muhammad Akbar Reishandy
package gui.borrow;

import gui.Menu;
import logic.Book;
import logic.books.Dictionary;
import logic.books.Novel;
import logic.books.TextBook;

import javax.swing.*;
import java.awt.*;

public class AddBook extends Component {
    private JPanel addBook;
    private JLabel title;
    private JLabel penerbitLabel;
    private JTextField penerbitTextField;
    private JTextField judulTextField;
    private JLabel judulLabel;
    private JLabel pengarangLabel;
    private JTextField pengarangTextField;
    private JLabel tahunLabel;
    private JLabel halamanLabel;
    private JLabel IDLabel;
    private JTextField tahunTextField;
    private JTextField halamanTextField;
    private JTextField idTextField;
    private JButton ButtonContinue;
    private JButton ButtonBack;
    private JRadioButton textbookradioButton;
    private JRadioButton dictionaryRadioButton;
    private JRadioButton novelRadioButton;
    private JLabel detailLabel;
    private JTextField detailTextField;

    public AddBook() {
    // init form
    Menu menu = new Menu();

    ButtonBack.addActionListener(e -> {
        menu.change(SearchBook.getSearchBook());
    });

    ButtonContinue.addActionListener(e -> {
        Book book = null;
        String title = judulTextField.getText();
        String publisher = penerbitTextField.getText();
        String author = pengarangTextField.getText();
        String detail = detailTextField.getText();
        int year = 0;
        int pages = 0;
        int id = 0;

        try {
            year = Integer.parseInt(tahunTextField.getText());
            pages = Integer.parseInt(halamanTextField.getText());
            id = Integer.parseInt(idTextField.getText());
        } catch (NumberFormatException idError) {
            JOptionPane.showMessageDialog(this, "Tahun / Halaman / ID harus berupa angka",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }

        if (title.equals("") || publisher.equals("") || author.equals("") || detail.equals("")) {
            JOptionPane.showMessageDialog(this, "Field tidak boleh kosong", "Error",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            if (textbookradioButton.isSelected()) {
                book = new TextBook(id, title, author, publisher, year, pages, detail);
            } else if (novelRadioButton.isSelected()) {
                book = new Novel(id, title, author, publisher, year, pages, detail);
            } else if (dictionaryRadioButton.isSelected()){
                book = new Dictionary(id, title, author, publisher, year, pages, detail);
            }

            if (book != null) {
                SearchBook.shelf.addBook(book);
                menu.change(SearchBook.getSearchBook());
            }
        }
    });

    // Change detail label
    textbookradioButton.addActionListener(e -> {
        detailLabel.setText("Mapel");
    });
    novelRadioButton.addActionListener(e -> {
        detailLabel.setText("Genre");
    });
    dictionaryRadioButton.addActionListener(e -> {
        detailLabel.setText("Bahasa");
    });
    }

    public static JPanel getAddBook() {
        return new AddBook().addBook;
    }
}
