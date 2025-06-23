package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class insertInterface extends JFrame implements ActionListener {
    static ArrayList<Integer> IDs = new ArrayList<>();
    AVLTree tree = firstInterface.tree;

    Border border = BorderFactory.createLineBorder(Color.red, 3); // will be added to the empty text field
    ImageIcon backImage;
    JLabel label;

    JTextField nameTextField;
    JTextField IDTextField;
    JTextField priceTextField;
    JTextField priorityTextField;
    JTextField quantityTextField;

    JButton submitButton;
    JButton backButton;

    String Name;
    int ID;
    int Priority;
    int Quantity;
    int Price;

    JLabel nameLabel;
    JLabel IDLabel;
    JLabel priceLabel;
    JLabel quantityLabel;
    JLabel priorityLabel;

    insertInterface() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 750);
        this.setLayout(null);

        backImage = new ImageIcon("C:\\Users\\ASUS\\Desktop\\project\\project\\images\\first.png");

        label = new JLabel();
        label.setBounds(0, 0, 1200, 830);
        label.setIcon(backImage);

        submitButton = new JButton("Submit");
        submitButton.setBackground(Color.white);
        submitButton.setForeground(Color.orange);
        submitButton.setFocusable(false);
        submitButton.setEnabled(true);
        submitButton.addActionListener(this);
        submitButton.setBounds(750, 422, 80, 30);

        backButton = new JButton("Back");
        backButton.setBounds(350, 622, 80, 30);
        backButton.setBackground(Color.white);
        backButton.setForeground(Color.orange);
        backButton.setFocusable(false);
        backButton.setEnabled(true);
        backButton.addActionListener(this);

        nameTextField = new JTextField();
        nameTextField.setBounds(520, 155, 170, 50);
        nameTextField.setFont(new Font("consolas", Font.PLAIN, 15));
        nameTextField.setBackground(Color.white);
        nameTextField.setCaretColor(Color.black);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(430, 160, 70, 40);
        nameLabel.setForeground(Color.white);
        nameLabel.setFont(new Font("consolas", Font.PLAIN, 25));

        IDTextField = new JTextField();
        IDTextField.setBounds(520, 216, 170, 50);
        IDTextField.setFont(new Font("consolas", Font.PLAIN, 15));
        IDTextField.setBackground(Color.white);
        IDTextField.setCaretColor(Color.black);

        IDLabel = new JLabel("ID");
        IDLabel.setBounds(430, 220, 70, 40);
        IDLabel.setForeground(Color.white);
        IDLabel.setFont(new Font("consolas", Font.PLAIN, 25));

        quantityTextField = new JTextField();
        quantityTextField.setBounds(520, 273, 170, 50);
        quantityTextField.setFont(new Font("consolas", Font.PLAIN, 15));
        quantityTextField.setBackground(Color.white);
        quantityTextField.setCaretColor(Color.black);

        quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(420, 275, 100, 40);
        quantityLabel.setForeground(Color.white);
        quantityLabel.setFont(new Font("consolas", Font.PLAIN, 20));

        priorityTextField = new JTextField();
        priorityTextField.setBounds(520, 328, 170, 50);
        priorityTextField.setFont(new Font("consolas", Font.PLAIN, 15));
        priorityTextField.setBackground(Color.white);
        priorityTextField.setCaretColor(Color.black);

        priorityLabel = new JLabel("Priority");
        priorityLabel.setBounds(420, 330, 100, 40);
        priorityLabel.setForeground(Color.white);
        priorityLabel.setFont(new Font("consolas", Font.PLAIN, 20));

        priceTextField = new JTextField();
        priceTextField.setBounds(520, 384, 170, 50);
        priceTextField.setFont(new Font("consolas", Font.PLAIN, 15));
        priceTextField.setBackground(Color.white);
        priceTextField.setCaretColor(Color.black);

        priceLabel = new JLabel("Price");
        priceLabel.setBounds(420, 386, 100, 40);
        priceLabel.setForeground(Color.white);
        priceLabel.setFont(new Font("consolas", Font.PLAIN, 20));

        label.add(nameTextField);
        label.add(IDTextField);
        label.add(priorityTextField);
        label.add(quantityTextField);
        label.add(priceTextField);
        label.add(submitButton);
        label.add(backButton);
        label.add(nameLabel);
        label.add(IDLabel);
        label.add(priceLabel);
        label.add(quantityLabel);
        label.add(priorityLabel);

        this.add(label);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

    }

    int convertToInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new firstInterface();
            this.dispose();
        }

        if (e.getSource() == submitButton) {
            Name = nameTextField.getText().strip();
            ID = convertToInteger(IDTextField.getText().strip());
            Price = convertToInteger(priceTextField.getText().strip());
            Priority = convertToInteger(priorityTextField.getText().strip());
            Quantity = convertToInteger(quantityTextField.getText().strip());

            if (Name.isEmpty() || IDTextField.getText().isEmpty() || priorityTextField.getText().isEmpty()
                    || quantityTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter all the text fields please",
                        " ", JOptionPane.ERROR_MESSAGE);
            } else {

                boolean check = IDs.contains(ID);
                if (check) {
                    JOptionPane.showMessageDialog(null, "Change the ID",
                            " ", JOptionPane.ERROR_MESSAGE);
                } else {
                    tree.insert(new Node(ID, Name, Price, Quantity, Priority));

                }
            }
        }
    }
}
