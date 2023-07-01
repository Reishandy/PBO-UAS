// Author: Muhammad Akbar Reishandy
package gui.borrow;

import gui.Menu;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class Receipt extends Component {
    logic.Receipt receipt = new logic.Receipt();
    private JLabel title;
    private JTextField dateTextField;
    private JButton addButton;
    private JLabel name;
    private JPanel dateInputPanel;
    private JPanel receiptPanel;
    private JPanel showReceiptPanel;
    private JTextArea receiptTextArea;
    private JButton doneButton;


    public Receipt() {
        // init form
        Menu menu = new Menu();

        // Set current date + 4 weeks for the tip on date input
        dateTextField.setText(String.valueOf(LocalDate.now().plusWeeks(4)));

        // Hide showReceiptPanel
        showReceiptPanel.setVisible(false);

        // Do receipt
        receipt.setBorrower(PersonID.borrower);
        receipt.addBook(SearchBook.borrowBook);

        addButton.addActionListener(e -> {
            int condition = receipt.setReturnDate(dateTextField.getText());
            switch (condition) {
                case 1 -> JOptionPane.showMessageDialog(this, "Tidak sesuai format 'yyyy-MM-dd'",
                        "Error", JOptionPane.WARNING_MESSAGE);
                case 2 -> JOptionPane.showMessageDialog(this, "Bukan tanggal valid",
                        "Error", JOptionPane.WARNING_MESSAGE);
                case 3 -> JOptionPane.showMessageDialog(this,
                        "Maksimal waktu pengembalian adalah 4 minggu", "Error", JOptionPane.WARNING_MESSAGE);
                case -1 -> JOptionPane.showMessageDialog(this, "Tanggal harus lebih dari sekarang",
                        "Error", JOptionPane.WARNING_MESSAGE);
                case 0 -> {
                    // Hide the date input and show the receipt
                    dateInputPanel.setVisible(false);
                    showReceiptPanel.setVisible(true);
                    receiptTextArea.setText(receipt.toString());
                }
            }
        });

        doneButton.addActionListener(e -> {
            menu.change(menu.getMenu());
        });
    }

    public static JPanel getReceipt() {
        return new Receipt().receiptPanel;
    }
}
