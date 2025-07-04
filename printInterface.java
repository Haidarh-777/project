import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class printInterface extends JFrame implements ActionListener {
    AVLTree tree = new AVLTree();
    Node node = firstInterface.tree.root;
    ArrayList<addShipment> heapList = addShipment.heap;

    ImageIcon backImage;

    JLabel label;
    JLabel headerLabel;
    JLabel treeTypeLabel;

    int nodeSize = 40;
    static int width = 480;
    static int height = 50;

    JButton backButton;
    JButton rightButton;
    JButton leftButton;

    JMenuBar menuBar;
    JMenu menu;
    JMenuItem displayItem;

    JPanel panelScroll = new JPanel();
    JScrollPane scrollPane = new JScrollPane(panelScroll);

    String[] treeTypes = { "Max Heap Tree", "B-Tree", "Binary Search Tree", "AVL Tree" };
    int currentTreeTypeIndex = 3;

    printInterface() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 750);
        this.setLayout(null);

        backImage = new ImageIcon("C:\\Users\\ASUS\\Desktop\\project\\images\\first.png");

        label = new JLabel();
        label.setBounds(0, 80, 1200, 670);
        label.setIcon(backImage);
        label.setLayout(null);

        headerLabel = new JLabel();
        headerLabel.setBounds(0, 0, 1200, 80);
        headerLabel.setBackground(Color.ORANGE);
        headerLabel.setOpaque(true);

        menuBar = new JMenuBar();
        menu = new JMenu("||");
        displayItem = new JMenuItem("Display Shipments");

        scrollPane.setBounds(0, 0, 1190, 700);

        backButton = new JButton("Back");
        backButton.setBounds(350, 632, 80, 30);
        backButton.setBackground(Color.white);
        backButton.setForeground(Color.orange);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        rightButton = new JButton(">");
        rightButton.setBounds(800, 30, 80, 30);
        rightButton.setBackground(Color.white);
        rightButton.setForeground(Color.orange);
        rightButton.setFont(new Font("consolas", Font.PLAIN, 25));
        rightButton.setFocusable(false);
        rightButton.addActionListener(this);

        leftButton = new JButton("<");
        leftButton.setBounds(300, 30, 80, 30);
        leftButton.setBackground(Color.white);
        leftButton.setForeground(Color.orange);
        leftButton.setFont(new Font("consolas", Font.PLAIN, 25));
        leftButton.setFocusable(false);
        leftButton.addActionListener(this);

        treeTypeLabel = new JLabel(treeTypes[currentTreeTypeIndex], JLabel.CENTER);
        treeTypeLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        treeTypeLabel.setForeground(Color.WHITE);
        treeTypeLabel.setBounds(400, 30, 350, 30);

        headerLabel.add(rightButton);
        headerLabel.add(leftButton);
        headerLabel.add(treeTypeLabel);

        label.add(backButton);
        label.add(headerLabel);

        menuBar.setBounds(0, 0, 1200, 15);
        menuBar.add(menu);
        menu.add(displayItem);
        displayItem.addActionListener(this);

        headerLabel.add(menuBar);
        panelScroll.add(label);
        this.add(scrollPane);

        refreshTreeDisplay();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void displayAVLTree(Node node, int x, int y, int xOffset) {
        if (node == null)
            return;

        JLabel nodeLabel = createNodeLabel(String.valueOf(node.id), x, y);
        nodeLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
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

    private void displayMaxHeapTree() {
        drawHeapNode(0, 600, 150, 200);
    }

    private void drawHeapNode(int index, int x, int y, int xOffset) {
        if (index >= heapList.size())
            return;

        addShipment shipment = heapList.get(index);
        JLabel nodeLabel = createNodeLabel("" + shipment.id, x, y);

        nodeLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showShipmentDetails(shipment);
            }
        });

        label.add(nodeLabel);
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < heapList.size()) {
            drawHeapNode(left, x - xOffset, y + 80, xOffset / 2);
        }
        if (right < heapList.size()) {
            drawHeapNode(right, x + xOffset, y + 80, xOffset / 2);
        }
    }

    private JLabel createNodeLabel(String text, int x, int y) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.orange);
        label.setBorder(new LineBorder(Color.white));
        label.setBounds(x, y, nodeSize, nodeSize);
        return label;
    }

    private void showShipmentDetails(addShipment shipment) {
        JFrame detailsFrame = new JFrame("Shipment Details");
        detailsFrame.setSize(350, 250);
        detailsFrame.setLayout(new BorderLayout());
        JTextArea text = new JTextArea(shipment.toString());
        text.setEditable(false);
        text.setFont(new Font("Consolas", Font.PLAIN, 14));
        detailsFrame.add(new JScrollPane(text), BorderLayout.CENTER);
        detailsFrame.setLocationRelativeTo(this);
        detailsFrame.setVisible(true);
    }

    private void refreshTreeDisplay() {
        label.removeAll();
        label.add(backButton);
        label.add(headerLabel);

        if (treeTypeLabel.getText().equals("AVL Tree")) {
            displayAVLTree(node, 600, 150, 200);
        } else if (treeTypeLabel.getText().equals("Max Heap Tree")) {
            displayMaxHeapTree();
        }
        label.repaint();
        label.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new firstInterface();
            this.dispose();
        }
        if (e.getSource() == displayItem) {
            new printShipmesnts();
            this.dispose();
        }
        if (e.getSource() == leftButton) {
            if (currentTreeTypeIndex > 0) {
                currentTreeTypeIndex--;
                treeTypeLabel.setText(treeTypes[currentTreeTypeIndex]);
                refreshTreeDisplay();
            }
        }
        if (e.getSource() == rightButton) {
            if (currentTreeTypeIndex < treeTypes.length - 1) {
                currentTreeTypeIndex++;
                treeTypeLabel.setText(treeTypes[currentTreeTypeIndex]);
                refreshTreeDisplay();
            }
        }
    }
}
