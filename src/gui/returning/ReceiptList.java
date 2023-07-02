package gui.returning;

import gui.Menu;
import gui.borrow.ReceiptDone;
import logic.Receipt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Objects;

public class ReceiptList extends Component {
    static Receipt selectedRecipt;
    static HashMap<String, Receipt> receipts = new HashMap<>();
    private JLabel title;
    private JLabel pilihLabel;
    private JComboBox<String> receiptsComboBox;
    private JPanel returnBook;
    private JButton selectButton;
    private JTextArea receiptDetail;
    private JButton BackButton;

    public ReceiptList() {
        // init form
        gui.Menu menu = new Menu();

        // Clear for each entering
        receiptsComboBox.removeAllItems();

        // add receipt from done to list
        receipts.put(ReceiptDone.receipt.getId(), ReceiptDone.receipt);

        // add list to combo box
        for (String id: receipts.keySet()) {
            receiptsComboBox.addItem(id);
        }

        // select combobox display with textarea
        receiptsComboBox.addActionListener(e -> {
            // search the correct receipt
            String selectedId = Objects.requireNonNull(receiptsComboBox.getSelectedItem()).toString();

            // and keep them for next step
            selectedRecipt = receipts.get(selectedId);

            // then display on text area
            receiptDetail.setText(selectedRecipt.toString());
        });

        // switch to input date when clicked
        selectButton.addActionListener(e -> {
            // check if already returned
            if (selectedRecipt.isReturned()) {
                JOptionPane.showMessageDialog(this, "Buku sudah dikembalikan",
                        "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                menu.change(ReturningDone.getReturningDone());
            }
        });

        BackButton.addActionListener(e -> {
            menu.change(menu.getMenu());
        });
    }

    public static JPanel getReturnBook() {
        return new ReceiptList().returnBook;
    }
}
