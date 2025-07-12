
public class BTree {
    BTreeNode root;
    int t;

    public BTree(int t) {
        this.root = new BTreeNode(true);
        this.t = t;
    }

    public addShipment search(int id) {
        return (root == null) ? null : root.search(id);
    }

    public void insert(addShipment shipment) {
        if (root.keys.size() == 2 * t - 1) {
            BTreeNode s = new BTreeNode(false);
            s.children.add(root);
            s.splitChild(0, t);

            int i = (shipment.id > s.keys.get(0).id) ? 1 : 0;
            s.children.get(i).insertNonFull(shipment, t);
            root = s;
        } else {
            root.insertNonFull(shipment, t);
        }
    }

    public void remove(int key) {
        if (root == null) {
            return;
        }

        root.remove(key, t);

        if (root.keys.size() == 0) {
            if (root.leaf) {
                root = null;
            } else {
                root = root.children.get(0);
            }
        }
    }

}