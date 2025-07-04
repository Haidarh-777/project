
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class firstInterface extends JFrame implements ActionListener {
    static AVLTree tree = new AVLTree();

    ImageIcon backImage;
    JLabel label;
    JButton InsertButton;
    JButton serachButton;
    JButton printButton;
    JButton exitButton;
    JButton addButton;

    firstInterface() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 750);
        this.setLayout(null);

        backImage = new ImageIcon("C:\\Users\\ASUS\\Desktop\\project\\images\\first.png");

        label = new JLabel();
        label.setBounds(0, 0, 1200, 830);
        label.setIcon(backImage);

        addButton = new JButton("Add Shipment");
        addButton.setFont(new Font("consolas", Font.PLAIN, 22));
        addButton.setBounds(525, 50, 195, 80);
        addButton.setBackground(Color.ORANGE);
        addButton.setForeground(Color.white);
        addButton.setFocusable(false);
        addButton.setEnabled(true);
        addButton.addActionListener(this);

        InsertButton = new JButton("Insert Product");
        InsertButton.setFont(new Font("consolas", Font.PLAIN, 20));
        InsertButton.setBounds(525, 150, 195, 80);
        InsertButton.setBackground(Color.ORANGE);
        InsertButton.setForeground(Color.white);
        InsertButton.setFocusable(false);
        InsertButton.setEnabled(true);
        InsertButton.addActionListener(this);

        serachButton = new JButton("Search");
        serachButton.setFont(new Font("consolas", Font.PLAIN, 25));
        serachButton.setBounds(525, 250, 195, 80);
        serachButton.setBackground(Color.ORANGE);
        serachButton.setForeground(Color.white);
        serachButton.setFocusable(false);
        serachButton.setEnabled(true);
        serachButton.addActionListener(this);

        printButton = new JButton("Print Tree");
        printButton.setFont(new Font("consolas", Font.PLAIN, 25));
        printButton.setBounds(525, 350, 195, 80);
        printButton.setBackground(Color.ORANGE);
        printButton.setForeground(Color.white);
        printButton.setFocusable(false);
        printButton.setEnabled(true);
        printButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("consolas", Font.PLAIN, 25));
        exitButton.setBounds(525, 450, 195, 80);
        exitButton.setBackground(Color.ORANGE);
        exitButton.setForeground(Color.white);
        exitButton.setFocusable(false);
        exitButton.setEnabled(true);
        exitButton.addActionListener(this);

        label.add(addButton);
        label.add(InsertButton);
        label.add(serachButton);
        label.add(printButton);
        label.add(exitButton);

        this.add(label);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            new shipmentInterface();
            this.dispose();
        }
        if (e.getSource() == InsertButton) {
            new insertInterface();
            this.dispose();
        }

        if (e.getSource() == serachButton) {
            new searchInterface();
            this.dispose();
        }

        if (e.getSource() == printButton) {
            new printInterface();
            this.dispose();
        }

        if (e.getSource() == exitButton) {
            this.dispose();
        }

    }

}
