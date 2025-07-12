import java.util.ArrayList;

class BTreeNode {
    ArrayList<addShipment> keys = new ArrayList<>();
    ArrayList<BTreeNode> children = new ArrayList<>();
    boolean leaf;

    public BTreeNode(boolean leaf) {
        this.leaf = leaf;
    }

    addShipment search(int id) {
        int i = 0;
        while (i < keys.size() && id > keys.get(i).id)
            i++;

        if (i < keys.size() && keys.get(i).id == id)
            return keys.get(i);

        if (leaf)
            return null;

        return children.get(i).search(id);
    }

    void insertNonFull(addShipment shipment, int t) {
        int i = keys.size() - 1;

        if (leaf) {
            while (i >= 0 && shipment.id < keys.get(i).id)
                i--;
            keys.add(i + 1, shipment);
        } else {
            while (i >= 0 && shipment.id < keys.get(i).id)
                i--;

            if (children.get(i + 1).keys.size() == 2 * t - 1) {
                splitChild(i + 1, t);
                if (shipment.id > keys.get(i + 1).id)
                    i++;
            }
            children.get(i + 1).insertNonFull(shipment, t);
        }
    }

    void splitChild(int i, int t) {
        BTreeNode y = children.get(i);
        BTreeNode z = new BTreeNode(y.leaf);

        // انقل t-1 مفاتيح من y إلى z
        for (int j = 0; j < t - 1; j++)
            z.keys.add(y.keys.remove(t));

        if (!y.leaf) {
            for (int j = 0; j < t; j++)
                z.children.add(y.children.remove(t));
        }

        children.add(i + 1, z);
        keys.add(i, y.keys.remove(t - 1));
    }

    void remove(int key, int t) {
        int idx = findKey(key);

        if (idx < keys.size() && keys.get(idx).id == key) {
            if (leaf)
                removeFromLeaf(idx);
            else
                removeFromNonLeaf(idx, t);
        } else {
            if (leaf) {
                System.out.println("The key " + key + " is not present in the tree");
                return;
            }

            boolean flag = (idx == keys.size());

            if (children.get(idx).keys.size() < t)
                fill(idx, t);

            if (flag && idx > keys.size())
                children.get(idx - 1).remove(key, t);
            else
                children.get(idx).remove(key, t);
        }
    }

    void removeFromLeaf(int idx) {
        keys.remove(idx);
    }

    void removeFromNonLeaf(int idx, int t) {
        int k = keys.get(idx).id;

        if (children.get(idx).keys.size() >= t) {
            addShipment pred = getPredecessor(idx);
            keys.set(idx, pred);
            children.get(idx).remove(pred.id, t);
        } else if (children.get(idx + 1).keys.size() >= t) {
            addShipment succ = getSuccessor(idx);
            keys.set(idx, succ);
            children.get(idx + 1).remove(succ.id, t);
        } else {
            merge(idx, t);
            children.get(idx).remove(k, t);
        }
    }

    addShipment getPredecessor(int idx) {
        BTreeNode cur = children.get(idx);
        while (!cur.leaf)
            cur = cur.children.get(cur.keys.size());
        return cur.keys.get(cur.keys.size() - 1);
    }

    addShipment getSuccessor(int idx) {
        BTreeNode cur = children.get(idx + 1);
        while (!cur.leaf)
            cur = cur.children.get(0);
        return cur.keys.get(0);
    }

    void fill(int idx, int t) {
        if (idx != 0 && children.get(idx - 1).keys.size() >= t)
            borrowFromPrev(idx);
        else if (idx != keys.size() && children.get(idx + 1).keys.size() >= t)
            borrowFromNext(idx);
        else {
            if (idx != keys.size())
                merge(idx, t);
            else
                merge(idx - 1, t);
        }
    }

    void borrowFromPrev(int idx) {
        BTreeNode child = children.get(idx);
        BTreeNode sibling = children.get(idx - 1);

        child.keys.add(0, keys.get(idx - 1));

        if (!child.leaf)
            child.children.add(0, sibling.children.remove(sibling.children.size() - 1));

        keys.set(idx - 1, sibling.keys.remove(sibling.keys.size() - 1));
    }

    void borrowFromNext(int idx) {
        BTreeNode child = children.get(idx);
        BTreeNode sibling = children.get(idx + 1);

        child.keys.add(keys.get(idx));

        if (!child.leaf)
            child.children.add(sibling.children.remove(0));

        keys.set(idx, sibling.keys.remove(0));
    }

    void merge(int idx, int t) {
        BTreeNode child = children.get(idx);
        BTreeNode sibling = children.get(idx + 1);

        child.keys.add(keys.remove(idx));
        child.keys.addAll(sibling.keys);

        if (!child.leaf)
            child.children.addAll(sibling.children);

        children.remove(idx + 1);
    }

    int findKey(int key) {
        int idx = 0;
        while (idx < keys.size() && keys.get(idx).id < key)
            ++idx;
        return idx;
    }
}
