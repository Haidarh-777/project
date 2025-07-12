import java.util.ArrayList;

public class BS_Tree {
    static Node root;
    ArrayList<Integer> IDs = insertInterface.IDs;

    public void insert(Node newNode) {
        root = insertRec(root, newNode);
    }

    private Node insertRec(Node root, Node newNode) {
        if (root == null) {
            root = newNode;
            return root;
        }
        if (newNode.id < root.id)
            root.left = insertRec(root.left, newNode);
        else if (newNode.id > root.id)
            root.right = insertRec(root.right, newNode);
        return root;
    }

    public Node search(int id) {
        return searchRec(root, id);
    }

    private Node searchRec(Node root, int id) {
        if (root == null || root.id == id)
            return root;
        return id < root.id ? searchRec(root.left, id) : searchRec(root.right, id);
    }

    public Node deleteRec(Node root, int id) {
        if (root == null)
            return root;

        if (id < root.id)
            root.left = deleteRec(root.left, id);
        else if (id > root.id)
            root.right = deleteRec(root.right, id);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.id = minValue(root.right);

            root.right = deleteRec(root.right, root.id);
        }

        return root;
    }

    private int minValue(Node root) {
        int minv = root.id;
        while (root.left != null) {
            minv = root.left.id;
            root = root.left;
        }
        return minv;
    }
}
