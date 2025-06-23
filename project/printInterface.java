package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class printInterface extends JFrame implements ActionListener {
    AVLTree tree = new AVLTree();
    Node node = firstInterface.tree.root;
    ImageIcon backImage;
    JLabel label;
    JLabel headerLabel;
    JLabel treeTypeLabel;
    int size = insertInterface.IDs.size();
    int nodeSize = 40;
    static int width = 480;
    static int height = 50;

    JButton backButton;
    JButton rightButton;
    JButton leftButton;

    JPanel panelScroll = new JPanel();
    JScrollPane scrollPane = new JScrollPane(panelScroll);

    String[] treeTypes = { "Max Heap Tree", "B-Tree", "Binary Search Tree", "AVL Tree" };
    int currentTreeTypeIndex = 3;

    printInterface() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 750);
        this.setLayout(null);

        backImage = new ImageIcon("C:\\Users\\ASUS\\Desktop\\project\\project\\images\\first.png");

        label = new JLabel();
        label.setBounds(0, 201, 1200, 830);
        label.setIcon(backImage);
        label.setLayout(null);

        headerLabel = new JLabel();
        headerLabel.setBounds(0, 0, 1200, 80);
        headerLabel.setBackground(Color.ORANGE);
        headerLabel.setOpaque(true);
        headerLabel.setLayout(null);

        scrollPane.setBounds(0, 0, 1190, 700);
        panelScroll.setLayout(new BoxLayout(panelScroll, BoxLayout.Y_AXIS));
        panelScroll.setBorder(new LineBorder(Color.white, 2));

        backButton = new JButton("Back");
        backButton.setBounds(350, 632, 80, 30);
        backButton.setBackground(Color.white);
        backButton.setForeground(Color.orange);
        backButton.setFocusable(false);
        backButton.setEnabled(true);
        backButton.addActionListener(this);

        rightButton = new JButton(">");
        rightButton.setBounds(800, 20, 80, 30);
        rightButton.setBackground(Color.white);
        rightButton.setForeground(Color.orange);
        rightButton.setFont(new Font("consolas", Font.PLAIN, 25));
        rightButton.setFocusable(false);
        rightButton.setEnabled(true);
        rightButton.addActionListener(this);

        leftButton = new JButton("<");
        leftButton.setBounds(300, 20, 80, 30);
        leftButton.setBackground(Color.white);
        leftButton.setForeground(Color.orange);
        leftButton.setFont(new Font("consolas", Font.PLAIN, 25));
        leftButton.setFocusable(false);
        leftButton.setEnabled(true);
        leftButton.addActionListener(this);

        treeTypeLabel = new JLabel(treeTypes[currentTreeTypeIndex], JLabel.CENTER);
        treeTypeLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        treeTypeLabel.setForeground(Color.WHITE);
        treeTypeLabel.setBounds(400, 20, 350, 30);

        headerLabel.add(rightButton);
        headerLabel.add(leftButton);
        headerLabel.add(treeTypeLabel);

        label.add(backButton);
        label.add(headerLabel);

        if (treeTypeLabel.getText().equals("AVL Tree")) {
            displayAVLTree(node, 600, 150, 200);
        }

        panelScroll.add(label);
        this.add(scrollPane);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void displayAVLTree(Node node, int x, int y, int xOffset) {
        if (node == null)
            return;

        JLabel nodeLabel = new JLabel(String.valueOf(node.id), JLabel.CENTER);
        nodeLabel.setOpaque(true);
        nodeLabel.setBackground(Color.orange);
        nodeLabel.setBorder(new LineBorder(Color.white));
        nodeLabel.setBounds(x, y, nodeSize, nodeSize);

        nodeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new NodeDetailsInterface(node, tree, printInterface.this);
            }
        });

        label.add(nodeLabel);

        if (node.left != null) {
            displayAVLTree(node.left, x - xOffset, y + 80, xOffset / 2);
        }
        if (node.right != null) {
            displayAVLTree(node.right, x + xOffset, y + 80, xOffset / 2);
        }
    }

    private void printMaxHeapTree() {
        System.out.println("Max Heap Tree printing");

    }

    private void printBTree() {
        System.out.println("B-Tree printing");

    }

    private void printBinarySearchTree() {
        System.out.println("Binary Search Tree printing");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new firstInterface();
            this.dispose();
        } else if (e.getSource() == leftButton) {
            if (currentTreeTypeIndex > 0) {
                currentTreeTypeIndex--;
                treeTypeLabel.setText(treeTypes[currentTreeTypeIndex]);
                refreshTreeDisplay();
            }
        } else if (e.getSource() == rightButton) {
            if (currentTreeTypeIndex < treeTypes.length - 1) {
                currentTreeTypeIndex++;
                treeTypeLabel.setText(treeTypes[currentTreeTypeIndex]);
                refreshTreeDisplay();
            }
        }
    }

    private void refreshTreeDisplay() {
        label.removeAll();
        label.add(backButton);
        label.add(headerLabel);

        if (treeTypeLabel.getText().equals("AVL Tree")) {
            displayAVLTree(node, 600, 150, 200);
        } else if (treeTypeLabel.getText().equals("Max Heap Tree")) {
            printMaxHeapTree();
        } else if (treeTypeLabel.getText().equals("B-Tree")) {
            printBTree();
        } else if (treeTypeLabel.getText().equals("Binary Search Tree")) {
            printBinarySearchTree();
        }

        label.repaint();
        label.revalidate();
    }
}
