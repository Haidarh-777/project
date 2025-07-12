
import java.util.ArrayList;

public class AVLTree extends BS_Tree {
    static Node root;
    ArrayList<Integer> IDs = insertInterface.IDs;

    int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    Node insert(Node node, Node newNode) {
        if (node == null)
            return newNode;

        if (newNode.id < node.id)
            node.left = insert(node.left, newNode);
        else if (newNode.id > node.id)
            node.right = insert(node.right, newNode);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && newNode.id < node.left.id)
            return rightRotate(node);

        if (balance < -1 && newNode.id > node.right.id)
            return leftRotate(node);

        if (balance > 1 && newNode.id > node.left.id) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && newNode.id < node.right.id) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public void insert(Node newNode) {
        root = insert(root, newNode);
    }

    public Node search(Node node, int id) {
        if (node == null || node.id == id)
            return node;

        if (id < node.id)
            return search(node.left, id);
        else
            return search(node.right, id);
    }

    public Node search(int id) {
        return search(root, id);
    }

    public void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println("ID: " + node.id + ", Name: " + node.name);
            inorder(node.right);
        }
    }

    public void inorder() {
        inorder(root);
    }

    public Node delete(Node root, int key) {
        if (root == null)
            return root;

        if (key < root.id)
            root.left = delete(root.left, key);

        else if (key > root.id)
            root.right = delete(root.right, key);

        else {
            IDs.remove(IDs.indexOf(root.id));

            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (root.left != null)
                    temp = root.left;
                else if (root.right != null)
                    temp = root.right;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node temp = minValueNode(root.right);

                root.id = temp.id;
                root.name = temp.name;
                root.price = temp.price;
                root.quantity = temp.quantity;

                root.right = delete(root.right, temp.id);
            }
        }

        if (root == null)
            return root;

        root.height = 1 + Math.max(height(root.left), height(root.right));

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private Node minValueNode(Node node) {
        Node current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

}
