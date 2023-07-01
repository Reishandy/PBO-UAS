// Author: Muhammad Akbar Reishandy
package gui.borrow;

import gui.Menu;
import logic.Person;

import javax.swing.*;
import java.awt.*;

public class PersonID extends Component {
    static Person borrower = null;
    JPanel personID;
    private JLabel title;
    private JTextField nameTextField;
    private JTextField emailTextField;
    private JLabel email;
    private JLabel name;
    private JTextField idTextField;
    private JLabel id;
    private JButton ButtonContinue;
    private JButton ButtonBack;

    public PersonID() {
        // To access menu with non static
        Menu menu = new Menu();

        ButtonContinue.addActionListener(e -> {
            String name = nameTextField.getText();
            String email = emailTextField.getText();
            int idPerson = 0;
            try {
                idPerson = Integer.parseInt(idTextField.getText());
            } catch (NumberFormatException idError) {
                JOptionPane.showMessageDialog(this, "ID harus berupa angka", "Error",
                        JOptionPane.WARNING_MESSAGE);
            }

            if (name.equals("") || email.equals("")) {
                JOptionPane.showMessageDialog(this, "Field tidak boleh kosong", "Error",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                borrower = new Person(name, email, idPerson);
                menu.change(SearchBook.getSearchBook());
            }
        });

        ButtonBack.addActionListener(e -> {
            menu.change(menu.getMenu());
        });
    }

    public static JPanel getPersonID() {
        return new PersonID().personID;
    }
}
