
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class printShipmesnts extends JFrame implements ActionListener {

    ArrayList<addShipment> list = addShipment.heap;
    ImageIcon backImage;
    JLabel label;
    JButton backButton;

    printShipmesnts() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 750);
        this.setLayout(null);

        backImage = new ImageIcon("images/first.png");

        label = new JLabel();
        label.setBounds(0, 0, 1200, 830);
        label.setIcon(backImage);
        label.setLayout(null);

        backButton = new JButton("Back");
        backButton.setBounds(350, 632, 80, 30);
        backButton.setBackground(Color.white);
        backButton.setForeground(Color.orange);
        backButton.setFocusable(false);
        backButton.setEnabled(true);
        backButton.addActionListener(this);

        StringBuilder sb = new StringBuilder();
        for (addShipment shipment : list) {
            sb.append(shipment.toString()).append("\n");
        }

        JTextArea displayArea = new JTextArea();
        displayArea.setText(sb.toString());
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Arial", Font.PLAIN, 14));
        displayArea.setForeground(Color.BLACK);
        displayArea.setBackground(new Color(231, 200, 153));
        displayArea.setOpaque(true);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(200, 40, 810, 410);

        label.add(backButton);
        label.add(scrollPane);
        this.add(label);

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new printInterface();
            this.dispose();
        }
    }
}
