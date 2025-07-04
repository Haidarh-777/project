
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NodeDetailsInterface extends JFrame implements ActionListener {
    Node node;
    AVLTree tree;
    JFrame parentFrame;
    JLabel infoLabel;
    JLabel label;
    JButton editPriceButton, editQuantityButton, deleteButton;
    ImageIcon backImage;

    NodeDetailsInterface(Node node, AVLTree tree, JFrame parentFrame) {
        this.node = node;
        this.tree = tree;
        this.parentFrame = parentFrame;

        backImage = new ImageIcon("C:\\Users\\ASUS\\Desktop\\project\\project\\images\\first.png");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 300);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);

        label = new JLabel();
        label.setBounds(0, 0, 400, 300);
        label.setIcon(backImage);

        infoLabel = new JLabel(getNodeInfo());
        infoLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
        infoLabel.setForeground(Color.orange);
        infoLabel.setBackground(Color.white);

        editPriceButton = new JButton("Edit Price");
        editQuantityButton = new JButton("Edit Quantity");
        deleteButton = new JButton("Delete");

        editPriceButton.setFocusable(false);
        editQuantityButton.setFocusable(false);
        deleteButton.setFocusable(false);

        editPriceButton.setBackground(Color.ORANGE);
        editQuantityButton.setBackground(Color.ORANGE);
        deleteButton.setBackground(Color.ORANGE);

        editPriceButton.setForeground(Color.white);
        editQuantityButton.setForeground(Color.white);
        deleteButton.setForeground(Color.white);

        editPriceButton.addActionListener(this);
        editQuantityButton.addActionListener(this);
        deleteButton.addActionListener(this);

        this.add(infoLabel);
        this.add(editPriceButton);
        this.add(editQuantityButton);
        this.add(deleteButton);
        this.setBackground(Color.ORANGE);
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
                    infoLabel.setText(getNodeInfo());
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
                    infoLabel.setText(getNodeInfo());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid quantity entered!");
                }
            }
        } else if (e.getSource() == deleteButton) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to delete this node?", "Confirm",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tree.delete(tree.root, node.id);
                JOptionPane.showMessageDialog(this, "Node Deleted");
                this.dispose();
                parentFrame.dispose();
                new printInterface();
            }
        }
    }
}
