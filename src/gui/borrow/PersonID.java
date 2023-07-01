// Author: Muhammad Akbar Reishandy
package gui.borrow;

import gui.Menu;

import javax.swing.*;

public class PersonID {
    private JPanel personID;
    private JButton button1;

    public PersonID() {
        button1.addActionListener(e -> {
            Menu.getBack();
        });
    }

    public static JPanel getPanel() {
        return new PersonID().personID;
    }
}
