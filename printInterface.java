import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class printInterface extends JFrame implements ActionListener {
    AVLTree tree = new AVLTree();
    BS_Tree bs_Tree = new BS_Tree();
    BTree bTree = new BTree(2);
    Node node = firstInterface.tree.root;
    Node bs_Node = firstInterface.BS_tree.root;

    ArrayList<addShipment> heapList = addShipment.heap;

    JLabel label;
    JLabel headerLabel;
    JLabel treeTypeLabel;

    int nodeSize = 40;
    String[] treeTypes = { "Max Heap Tree", "B-Tree", "Binary Search Tree", "AVL Tree" };
    int currentTreeTypeIndex = 3;

    JButton backButton, rightButton, leftButton, updatePriorityButton;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem displayItem;
    ImageIcon backImage;

    JScrollPane scrollPane;

    printInterface() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 750);
        setLayout(null);

        backImage = new ImageIcon("images/first.png");
        if (bTree.root == null || bTree.root.keys.size() == 0) {
            for (addShipment s : addShipment.heap) {
                bTree.insert(s);
            }
        }

        label = new JLabel();
        label.setLayout(null);
        label.setBounds(0, 80, 1200, 670);

        headerLabel = new JLabel();
        headerLabel.setBounds(0, 0, 1200, 80);
        headerLabel.setBackground(Color.ORANGE);
        headerLabel.setOpaque(true);

        treeTypeLabel = new JLabel(treeTypes[currentTreeTypeIndex], JLabel.CENTER);
        treeTypeLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        treeTypeLabel.setForeground(Color.WHITE);
        treeTypeLabel.setBounds(400, 30, 350, 30);

        leftButton = createHeaderButton("<", 300, 30);
        rightButton = createHeaderButton(">", 800, 30);

        headerLabel.add(leftButton);
        headerLabel.add(rightButton);
        headerLabel.add(treeTypeLabel);

        backButton = new JButton("Back");
        backButton.setBounds(350, 632, 80, 30);
        backButton.setBackground(Color.white);
        backButton.setForeground(Color.orange);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 1200, 15);
        menu = new JMenu("||");
        displayItem = new JMenuItem("Display Shipments");
        displayItem.addActionListener(this);
        menu.add(displayItem);
        menuBar.add(menu);
        headerLabel.add(menuBar);

        label.add(backButton);
        label.add(headerLabel);
        label.setIcon(backImage);

        scrollPane = new JScrollPane(label);
        scrollPane.setBounds(0, 0, 1190, 700);
        add(scrollPane);

        refreshTreeDisplay();

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private JButton createHeaderButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 80, 30);
        button.setBackground(Color.white);
        button.setForeground(Color.orange);
        button.setFont(new Font("Consolas", Font.PLAIN, 25));
        button.setFocusable(false);
        button.addActionListener(this);
        return button;
    }

    private JLabel createNodeLabel(String text, int x, int y) {
        JLabel nodeLabel = new JLabel(text, JLabel.CENTER);
        nodeLabel.setOpaque(true);
        nodeLabel.setBackground(Color.orange);
        nodeLabel.setBorder(new LineBorder(Color.white));
        nodeLabel.setBounds(x, y, nodeSize, nodeSize);
        return nodeLabel;
    }

    private void refreshTreeDisplay() {
        label.removeAll();
        label.add(backButton);
        label.add(headerLabel);
        label.setIcon(backImage);

        String type = treeTypeLabel.getText();
        if (type.equals("AVL Tree")) {
            displayAVLTree(node, 600, 150, 200);
        } else if (type.equals("Max Heap Tree")) {
            displayMaxHeapTree();
        } else if (type.equals("Binary Search Tree")) {
            displayBSTree(bs_Node, 600, 150, 200);
        } else if (type.equals("B-Tree")) {
            displayBTree(bTree.root, 600, 150, 200);
        }

        label.repaint();
        label.revalidate();
    }

    private void displayAVLTree(Node node, int x, int y, int xOffset) {
        if (node == null)
            return;

        JLabel nodeLabel = createNodeLabel(String.valueOf(node.id), x, y);
        nodeLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new NodeDetailsInterface1(node, tree, bs_Tree, printInterface.this);
            }
        });
        label.add(nodeLabel);

        if (node.left != null)
            displayAVLTree(node.left, x - xOffset, y + 80, xOffset / 2);
        if (node.right != null)
            displayAVLTree(node.right, x + xOffset, y + 80, xOffset / 2);
    }

    private void displayBSTree(Node node, int x, int y, int xOffset) {
        if (node == null)
            return;

        JLabel nodeLabel = createNodeLabel(String.valueOf(node.id), x, y);
        nodeLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new NodeDetailsInterface1(node, tree, bs_Tree, printInterface.this);
            }
        });
        label.add(nodeLabel);

        if (node.left != null)
            displayBSTree(node.left, x - xOffset, y + 80, xOffset / 2);
        if (node.right != null)
            displayBSTree(node.right, x + xOffset, y + 80, xOffset / 2);
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
                showShipmentDetails(shipment, "Max Heap Tree");
            }
        });
        label.add(nodeLabel);

        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < heapList.size())
            drawHeapNode(left, x - xOffset, y + 80, xOffset / 2);
        if (right < heapList.size())
            drawHeapNode(right, x + xOffset, y + 80, xOffset / 2);
    }

    private void displayBTree(BTreeNode node, int x, int y, int xOffset) {
        if (node == null)
            return;

        int nodeWidth = node.keys.size() * (nodeSize + 10);
        int startX = x - nodeWidth / 2;

        for (int i = 0; i < node.keys.size(); i++) {
            addShipment shipment = node.keys.get(i);
            JLabel nodeLabel = createNodeLabel("" + shipment.id, startX + i * (nodeSize + 10), y);
            nodeLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    showShipmentDetails(shipment, "B-Tree");
                }
            });
            label.add(nodeLabel);
        }

        if (!node.leaf) {
            int numChildren = node.children.size();
            int totalWidth = numChildren * (xOffset / 2);

            for (int i = 0; i < numChildren; i++) {
                int childX = x - totalWidth / 2 + i * (xOffset);
                displayBTree(node.children.get(i), childX, y + 80, xOffset / 2);
            }
        }
    }

    private void showShipmentDetails(addShipment shipment, String Type) {
        JFrame detailsFrame = new JFrame("Shipment Details");
        detailsFrame.setSize(350, 250);
        detailsFrame.setLayout(new BorderLayout());
        JTextArea text = new JTextArea(shipment.toString1());
        text.setEditable(false);
        text.setFont(new Font("Consolas", Font.PLAIN, 20));
        text.setForeground(Color.ORANGE);
        detailsFrame.add(new JScrollPane(text), BorderLayout.CENTER);
        detailsFrame.setLocationRelativeTo(this);
        detailsFrame.setVisible(true);
        if (Type.equals("Max Heap Tree")) {
            updatePriorityButton = new JButton("Update Priority");
            updatePriorityButton.setBounds(20, 160, 170, 20);
            updatePriorityButton.setFont(new Font("Consolas", Font.PLAIN, 13));
            updatePriorityButton.setFocusable(false);
            text.add(updatePriorityButton);
            updatePriorityButton.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    JFrame updatePriorityFrame = new JFrame("Update Priority");
                    updatePriorityFrame.setLayout(new BorderLayout());
                    String newPriorityStr = JOptionPane.showInputDialog(
                            null, "Enter new priority:", shipment.priority);
                    if (newPriorityStr != null) {
                        try {
                            int newPriority = Integer.parseInt(newPriorityStr);
                            if (newPriority < 0 || newPriority > 2)

                                shipment.priority = newPriority;

                            JOptionPane.showMessageDialog(null, "Priority updated.");
                            detailsFrame.dispose();
                            maxHeapTree.heapify(heapList, 0, heapList.size());
                            refreshTreeDisplay();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Invalid priority entered!");
                        }
                    }
                }
            });
        } else if (Type.equals("B-Tree")) {
            JButton updateDateButton = new JButton("Update Date");
            updateDateButton.setBounds(20, 190, 170, 20);
            updateDateButton.setFont(new Font("Consolas", Font.PLAIN, 13));
            updateDateButton.setFocusable(false);
            text.add(updateDateButton);

            updateDateButton.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    String newDateStr = JOptionPane.showInputDialog(
                            null, "Enter new date (yyyy/MM/dd):", shipment.date);

                    if (newDateStr != null) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                            LocalDate newDate = LocalDate.parse(newDateStr, formatter);
                            if (newDate.isBefore(LocalDate.now())) {
                                JOptionPane.showMessageDialog(null, "Old date can not be added.");
                                detailsFrame.dispose();
                            } else {

                                shipment.date = newDate.format(formatter);

                                JOptionPane.showMessageDialog(null, "Date updated.");
                                detailsFrame.dispose();
                                refreshTreeDisplay();
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Invalid date format! Use yyyy/MM/dd");
                        }
                    }
                }
            });
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new firstInterface();
            dispose();
        } else if (e.getSource() == displayItem) {
            new printShipmesnts();
            dispose();
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
        } else if (e.getSource() == updatePriorityButton) {
        }
    }
}
