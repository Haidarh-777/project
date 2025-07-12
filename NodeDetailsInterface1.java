import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NodeDetailsInterface1 extends JFrame implements ActionListener {
    Node node;
    AVLTree tree;
    BS_Tree bs_Tree;
    JFrame parentFrame;
    JLabel infoLabel;
    JLabel label;
    JButton editPriceButton, editQuantityButton, deleteButton;
    ImageIcon backImage;

    NodeDetailsInterface1(Node node, AVLTree tree, BS_Tree bs_Tree, JFrame parentFrame) {
        this.node = node;
        this.tree = tree;
        this.bs_Tree = bs_Tree;
        this.parentFrame = parentFrame;

        backImage = new ImageIcon("images/first.png");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 300);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        label = new JLabel();
        label.setBounds(0, 0, 400, 300);
        label.setIcon(backImage);
        label.setLayout(null);

        infoLabel = new JLabel(getNodeInfo());
        infoLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
        infoLabel.setForeground(Color.orange);
        infoLabel.setBounds(50, 30, 300, 100);

        editPriceButton = new JButton("Edit Price");
        editQuantityButton = new JButton("Edit Quantity");
        deleteButton = new JButton("Delete");

        JButton[] buttons = { editPriceButton, editQuantityButton, deleteButton };
        for (JButton btn : buttons) {
            btn.setFocusable(false);
            btn.setBackground(Color.ORANGE);
            btn.setForeground(Color.white);
        }

        editPriceButton.setBounds(50, 150, 120, 30);
        editQuantityButton.setBounds(230, 150, 120, 30);
        deleteButton.setBounds(140, 200, 120, 30);

        editPriceButton.addActionListener(this);
        editQuantityButton.addActionListener(this);
        deleteButton.addActionListener(this);

        label.add(infoLabel);
        label.add(editPriceButton);
        label.add(editQuantityButton);
        label.add(deleteButton);

        this.add(label);
        this.setResizable(false);
        this.setVisible(true);
    }

    private String getNodeInfo() {
        return "<html>ID: " + node.id +
                "<br>Name: " + node.name +
                "<br>Price: " + node.price +
                "<br>Quantity: " + node.quantity + "</html>";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editPriceButton) {
            String newPriceStr = JOptionPane.showInputDialog(this, "Enter new price:", node.price);
            if (newPriceStr != null) {
                try {
                    int newPrice = Integer.parseInt(newPriceStr);
                    if (newPrice < 0)
                        throw new Exception();

                    node.price = newPrice;

                    Node bstNode = bs_Tree.search(node.id);
                    if (bstNode != null) {
                        bstNode.price = newPrice;
                    }

                    infoLabel.setText(getNodeInfo());
                    JOptionPane.showMessageDialog(this, "Price updated in both trees!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price entered!");
                }
            }
        } else if (e.getSource() == editQuantityButton) {
            String newQtyStr = JOptionPane.showInputDialog(this, "Enter new quantity:", node.quantity);
            if (newQtyStr != null) {
                try {
                    int newQty = Integer.parseInt(newQtyStr);
                    if (newQty < 0 || newQty > 1000)
                        throw new Exception();

                    node.quantity = newQty;

                    Node bstNode = bs_Tree.search(node.id);
                    if (bstNode != null) {
                        bstNode.quantity = newQty;
                    }

                    infoLabel.setText(getNodeInfo());
                    JOptionPane.showMessageDialog(this, "Quantity updated in both trees!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid quantity entered!");
                }
            }
        } else if (e.getSource() == deleteButton) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to delete this node?", "Confirm",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tree.root = tree.delete(tree.root, node.id);

                bs_Tree.root = bs_Tree.deleteRec(bs_Tree.root, node.id);

                JOptionPane.showMessageDialog(this, "Node is deleted");
                this.dispose();
                parentFrame.dispose();
                new printInterface();
            }
        }
    }
}
