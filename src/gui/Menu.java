// Author: Muhammad Akbar Reishandy
package gui;

import javax.swing.*;
import gui.borrow.*;
import gui.returning.ReceiptList;

import java.awt.*;

public class Menu extends Component {
    private static JFrame frame;
    private JButton pinjamButton;
    private JButton kembaliButton;
    private JLabel title;
    private JPanel menu;

    public JPanel getMenu() {
        return menu;
    }

    public Menu() {
        // For changing the display (JPanel) to each corresponding gui (borrow or return)
        pinjamButton.addActionListener(e -> {
            change(PersonID.getPersonID());
        });
        kembaliButton.addActionListener(e -> {
            // Check if borrowed
            if (!ReceiptDone.done) {
                JOptionPane.showMessageDialog(this, "Belum meminjam",
                        "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                change(ReceiptList.getReturnBook());
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Aplikasi peminjaman buku");
        frame.setContentPane(new Menu().menu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(460, 460));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException |
                 UnsupportedLookAndFeelException ignored) {}
    }

    public void change(JPanel panel) {
        frame.setContentPane(panel);
        frame.repaint();
        frame.revalidate();
    }
}
