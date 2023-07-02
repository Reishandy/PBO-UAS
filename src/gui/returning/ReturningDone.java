package gui.returning;

import gui.Menu;
import gui.borrow.PersonID;
import gui.borrow.SearchBook;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class ReturningDone extends Component {
    private JLabel title;
    private JPanel dateInputPanel;
    private JTextField dateTextField;
    private JLabel backLabel;
    private JButton addButton;
    private JPanel showReceiptPanel;
    private JButton doneButton;
    private JTextArea conditionTextArea;
    private JPanel returningDone;

    public ReturningDone() {
        // init form
        Menu menu = new Menu();

        // Set current date for easy access
        dateTextField.setText(String.valueOf(LocalDate.now()));

        // Hide stuff
        conditionTextArea.setVisible(false);
        doneButton.setVisible(false);

        addButton.addActionListener(e -> {
            int resultReturn = ReceiptList.selectedRecipt.returnBook(dateTextField.getText());
            if (resultReturn == 0) {
                conditionTextArea.setText("Berhasil dikembalikan");
            } else if (resultReturn >= 1) {
                // Penalty, 1 day = Rp 10,000
                int penalty = resultReturn * 10_000;

                // Set textArea
                conditionTextArea.setText("Dikembalikan dengan denda sebesar \nRp. %,d (%d hari)"
                        .formatted(penalty, resultReturn) );
            } else {
                JOptionPane.showMessageDialog(this, "Something is not right",
                        "Error", JOptionPane.WARNING_MESSAGE);
                menu.change(menu.getMenu());
            }

            dateInputPanel.setVisible(false);
            conditionTextArea.setVisible(true);
            doneButton.setVisible(true);
        });

        doneButton.addActionListener(e -> {
            menu.change(menu.getMenu());
        });
    }

    public static JPanel getReturningDone() {
        return new ReturningDone().returningDone;
    }
}
