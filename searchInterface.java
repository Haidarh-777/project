
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class searchInterface extends JFrame implements ActionListener {
    ArrayList<Integer> IDs = new ArrayList<>();
    AVLTree tree = firstInterface.tree;

    Border border = BorderFactory.createLineBorder(Color.red, 3);
    ImageIcon backImage;
    JLabel label;

    JTextField nameTextField;
    JTextField IDTextField;
    JTextField priorityTextField;
    JTextField quantityTextField;

    JButton submitButton;
    JButton backButton;

    String Name;
    int ID;
    int Priority;
    int Quantity;
    static boolean existed = false;

    JLabel textLabel;
    JLabel IDLabel;
    JLabel quantityLabel;
    JLabel priorityLabel;
    JLabel existanceLabel;
    JLabel nodeInfoLabel;

    searchInterface() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 750);
        this.setLayout(null);

        backImage = new ImageIcon("images/first.png");

        label = new JLabel();
        label.setBounds(0, 0, 1200, 830);
        label.setIcon(backImage);
        label.setLayout(null);

        textLabel = new JLabel("Enter the ID to search about your shipping parcel");
        textLabel.setBounds(330, 170, 600, 40);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("consolas", Font.PLAIN, 20));

        submitButton = new JButton("Search");
        submitButton.setBackground(Color.white);
        submitButton.setForeground(Color.orange);
        submitButton.setFocusable(false);
        submitButton.setEnabled(true);
        submitButton.addActionListener(this);
        submitButton.setBounds(750, 272, 80, 30);

        backButton = new JButton("Back");
        backButton.setBounds(350, 622, 80, 30);
        backButton.setBackground(Color.white);
        backButton.setForeground(Color.orange);
        backButton.setFocusable(false);
        backButton.setEnabled(true);
        backButton.addActionListener(this);

        IDTextField = new JTextField();
        IDTextField.setBounds(520, 216, 170, 50);
        IDTextField.setFont(new Font("consolas", Font.PLAIN, 15));
        IDTextField.setBackground(Color.white);
        IDTextField.setCaretColor(Color.black);

        IDLabel = new JLabel("ID");
        IDLabel.setBounds(430, 220, 70, 40);
        IDLabel.setForeground(Color.white);
        IDLabel.setFont(new Font("consolas", Font.PLAIN, 25));

        nodeInfoLabel = new JLabel("");
        nodeInfoLabel.setBounds(400, 250, 600, 200);
        nodeInfoLabel.setForeground(Color.white);
        nodeInfoLabel.setFont(new Font("consolas", Font.PLAIN, 18));

        label.add(IDTextField);
        label.add(submitButton);
        label.add(backButton);
        label.add(IDLabel);
        label.add(textLabel);
        label.add(nodeInfoLabel);

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
        if (e.getSource() == submitButton) {
            ID = convertToInteger(IDTextField.getText().strip());
            Node node = tree.search(ID);
            if (node != null) {
                existed = true;
            } else {
                existed = false;
            }
            if (existed) {
                JOptionPane.showMessageDialog(null,
                        "The shipping parcel is existed \n Do you want the parcel's information?",
                        " ", JOptionPane.YES_NO_OPTION);

                nodeInfoLabel.setText(
                        "<html>ID: " + node.id + "<br>" +
                                "Name: " + node.name + "<br>" +
                                "Price: " + node.price + "<br>" +
                                "Quantity: " + node.quantity + "</html>");

            } else {
                JOptionPane.showMessageDialog(null, "The shipping parcel is not existed ",
                        " ", JOptionPane.OK_OPTION);

                nodeInfoLabel.setText("");
            }
        }
        if (e.getSource() == backButton) {
            new firstInterface();
            this.dispose();
        }
    }
}
