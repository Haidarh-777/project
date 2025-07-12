
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class shipmentInterface extends JFrame implements ActionListener {
    ArrayList<Integer> IDs = insertInterface.IDs;
    AVLTree tree = firstInterface.tree;
    BTree bTree = new BTree(3);

    ImageIcon backImage;
    JLabel label;

    JTextField nameTextField;
    JTextField IDTextField;
    JTextField ProductIDTextField;
    JTextField priorityTextField;
    JTextField quantityTextField;
    JTextField DateTextField;

    JButton submitButton;
    JButton backButton;

    String Name;
    int Product_ID;
    int ID;
    String Date;
    int Quantity;
    int priority;

    JLabel nameLabel;
    JLabel ProductIDLabel;
    JLabel IDLabel;
    JLabel quantityLabel;
    JLabel priorityLabel;
    JLabel dateLabel;

    shipmentInterface() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 750);
        this.setLayout(null);

        backImage = new ImageIcon("images/first.png");

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
        nameTextField.setBounds(520, 105, 170, 50);
        nameTextField.setFont(new Font("consolas", Font.PLAIN, 15));
        nameTextField.setBackground(Color.white);
        nameTextField.setCaretColor(Color.black);

        nameLabel = new JLabel("Destination");
        nameLabel.setBounds(350, 110, 150, 40);
        nameLabel.setForeground(Color.white);
        nameLabel.setFont(new Font("consolas", Font.PLAIN, 25));

        ProductIDTextField = new JTextField();
        ProductIDTextField.setBounds(520, 165, 170, 50);
        ProductIDTextField.setFont(new Font("consolas", Font.PLAIN, 15));
        ProductIDTextField.setBackground(Color.white);
        ProductIDTextField.setCaretColor(Color.black);

        ProductIDLabel = new JLabel("Product ID");
        ProductIDLabel.setBounds(350, 168, 150, 40);
        ProductIDLabel.setForeground(Color.white);
        ProductIDLabel.setFont(new Font("consolas", Font.PLAIN, 25));

        IDTextField = new JTextField();
        IDTextField.setBounds(520, 230, 170, 50);
        IDTextField.setFont(new Font("consolas", Font.PLAIN, 15));
        IDTextField.setBackground(Color.white);
        IDTextField.setCaretColor(Color.black);

        IDLabel = new JLabel("Shipment ID");
        IDLabel.setBounds(350, 235, 150, 40);
        IDLabel.setForeground(Color.white);
        IDLabel.setFont(new Font("consolas", Font.PLAIN, 25));

        quantityTextField = new JTextField();
        quantityTextField.setBounds(520, 285, 170, 50);
        quantityTextField.setFont(new Font("consolas", Font.PLAIN, 15));
        quantityTextField.setBackground(Color.white);
        quantityTextField.setCaretColor(Color.black);

        quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(350, 289, 150, 40);
        quantityLabel.setForeground(Color.white);
        quantityLabel.setFont(new Font("consolas", Font.PLAIN, 25));

        priorityTextField = new JTextField();
        priorityTextField.setBounds(520, 345, 170, 50);
        priorityTextField.setFont(new Font("consolas", Font.PLAIN, 15));
        priorityTextField.setBackground(Color.white);
        priorityTextField.setCaretColor(Color.black);

        priorityLabel = new JLabel("Priority");
        priorityLabel.setBounds(350, 348, 150, 40);
        priorityLabel.setForeground(Color.white);
        priorityLabel.setFont(new Font("consolas", Font.PLAIN, 25));

        DateTextField = new JTextField();
        DateTextField.setBounds(520, 400, 170, 50);
        DateTextField.setFont(new Font("consolas", Font.PLAIN, 15));
        DateTextField.setBackground(Color.white);
        DateTextField.setCaretColor(Color.black);

        dateLabel = new JLabel("Date");
        dateLabel.setBounds(350, 400, 150, 40);
        dateLabel.setForeground(Color.white);
        dateLabel.setFont(new Font("consolas", Font.PLAIN, 25));

        label.add(nameTextField);
        label.add(IDTextField);
        label.add(priorityTextField);
        label.add(quantityTextField);
        label.add(DateTextField);
        label.add(ProductIDTextField);
        label.add(submitButton);
        label.add(backButton);
        label.add(nameLabel);
        label.add(IDLabel);
        label.add(quantityLabel);
        label.add(priorityLabel);
        label.add(dateLabel);
        label.add(ProductIDLabel);

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

    public boolean isFutureDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            LocalDate inputDate = LocalDate.parse(dateStr, formatter);

            LocalDate today = LocalDate.now();

            return inputDate.isAfter(today);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy/MM/dd");
            return false;
        }
    }

    public LocalDate parseToDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Expected yyyy/MM/dd");
            return null;
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
            Product_ID = convertToInteger(ProductIDTextField.getText().strip());
            Date = DateTextField.getText().strip();
            Quantity = convertToInteger(quantityTextField.getText().strip());
            priority = convertToInteger(priorityTextField.getText().strip());
            ID = convertToInteger(IDTextField.getText().strip());
            LocalDate ld = parseToDate(Date);
            boolean checkDate = ld.isBefore(LocalDate.now());

            if (Name.isEmpty() || IDTextField.getText().isEmpty() || Date.isEmpty()
                    || quantityTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter all the text fields please",
                        " ", JOptionPane.ERROR_MESSAGE);

            } else if (checkDate) {
                JOptionPane.showMessageDialog(null, "Invalid date format or Old date , Please check :)",
                        " ", JOptionPane.ERROR_MESSAGE);

            } else {

                if (!IDs.contains(Product_ID)) {
                    JOptionPane.showMessageDialog(null, "There is no product with this ID",
                            " ", JOptionPane.ERROR_MESSAGE);
                } else {
                    Node product = tree.search(Product_ID);
                    if (product.quantity < Quantity || Quantity < 0) {
                        JOptionPane.showMessageDialog(null, "We don't have that much quantity",
                                " ", JOptionPane.ERROR_MESSAGE);
                    } else {
                        tree.search(Product_ID).quantity -= Quantity;
                        addShipment shipment = new addShipment(Name, ID, Product_ID, Quantity, priority, Date);
                        // addShipment.list.add(new addShipment(Name, ID, Product_ID, Quantity,
                        // priority, Date));
                        bTree.insert(shipment);
                        JOptionPane.showMessageDialog(null, "The shipment is done.",
                                " ", JOptionPane.INFORMATION_MESSAGE);
                    }

                }
            }
        }

    }

}
