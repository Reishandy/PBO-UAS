// Author: Muhammad Akbar Reishandy
package gui.borrow;

import gui.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonID {
    private JPanel personID;
    private JLabel title;
    private JTextField nameTextField;
    private JTextField emailTextField;
    private JLabel email;
    private JLabel name;
    private JTextField idTextField;
    private JLabel id;
    private JButton ButtonContinue;
    private JButton ButtonBack;
    private JPanel inputFormPerson;

    public PersonID() {
        // To access menu with non static
        Menu menu = new Menu();
        // TODO: remove temporary stuff
        ButtonContinue.addActionListener(e -> {
            String data = nameTextField.getText() + " " + emailTextField.getText() + " " + idTextField.getText();
            System.out.println(data);
            menu.getData(data);
        });
        ButtonBack.addActionListener(e -> {
            menu.getBack();
        });
    }

    public static JPanel getPanel() {
        return new PersonID().personID;
    }
}
