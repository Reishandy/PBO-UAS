// Author: Muhammad Akbar Reishandy
package gui;

import javax.swing.*;
import gui.borrow.*;

import java.awt.*;

public class Menu {
    private static JFrame frame;
    private JButton pinjamButton;
    private JButton kembaliButton;
    private JLabel title;
    private JPanel menu;

    public Menu() {
        // For changing the display (JPanel) to each corresponding gui (borrow or return)
        pinjamButton.addActionListener(e -> {
            frame.setContentPane(PersonID.getPanel());
            frame.repaint();
            frame.revalidate();
        });
        kembaliButton.addActionListener(e -> {
            getData("aaaa");
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Aplikasi peminjaman buku");
        frame.setContentPane(new Menu().menu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(460, 400));
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

    // TODO: remove temporary method
    public void getBack() {
        frame.setContentPane(menu);
        frame.repaint();
        frame.revalidate();
    }

    public void getData(String data) {
        frame.setContentPane(menu);
        title.setText(data);
        frame.repaint();
        frame.revalidate();
    }
}
