// package project;

// import javax.swing.JLabel;
// import javax.swing.border.LineBorder;

// public class edit {
// private void printMaxHeapTree() {
// if (!treeTypeLabel.getText().equals("Max Heap Tree")) return;
// displayMaxHeapTree(maxHeapRoot, 600, 150, 200);
// }

// private void displayMaxHeapTree(HeapNode node, int x, int y, int xOffset) {
// if (node == null) return;

// JLabel nodeLabel = new JLabel(node.name, JLabel.CENTER);
// nodeLabel.setOpaque(true);
// nodeLabel.setBackground(Color.cyan);
// nodeLabel.setBorder(new LineBorder(Color.white));
// nodeLabel.setBounds(x, y, nodeSize, nodeSize);

// label.add(nodeLabel);

// if (node.left != null) {
// displayMaxHeapTree(node.left, x - xOffset, y + 80, xOffset / 2);
// }
// if (node.right != null) {
// displayMaxHeapTree(node.right, x + xOffset, y + 80, xOffset / 2);
// }
// }

// // طباعة B-Tree
// private void printBTree() {
// if (!treeTypeLabel.getText().equals("B-Tree")) return;
// displayBTree(bTreeRoot, 600, 150, 200);
// }

// private void displayBTree(BTreeNode node, int x, int y, int xOffset) {
// if (node == null) return;

// // لكل مفتاح داخل عقدة B-Tree
// for (int i = 0; i < node.keys.size(); i++) {
// JLabel keyLabel = new JLabel(node.keys.get(i) + "", JLabel.CENTER);
// keyLabel.setOpaque(true);
// keyLabel.setBackground(Color.green);
// keyLabel.setBorder(new LineBorder(Color.white));
// keyLabel.setBounds(x + i * (nodeSize + 5), y, nodeSize, nodeSize);
// label.add(keyLabel);
// }

// // الأبناء (children)
// if (!node.isLeaf) {
// for (int i = 0; i < node.children.size(); i++) {
// displayBTree(node.children.get(i), x - xOffset + i * (xOffset /
// node.children.size()), y + 80, xOffset / 2);
// }
// }
// }

// // طباعة Binary Search Tree
// private void printBinarySearchTree() {
// if (!treeTypeLabel.getText().equals("Binary Search Tree")) return;
// displayBinarySearchTree(bstRoot, 600, 150, 200);
// }

// private void displayBinarySearchTree(BSTNode node, int x, int y, int xOffset)
// {
// if (node == null) return;

// JLabel nodeLabel = new JLabel(node.name, JLabel.CENTER);
// nodeLabel.setOpaque(true);
// nodeLabel.setBackground(Color.pink);
// nodeLabel.setBorder(new LineBorder(Color.white));
// nodeLabel.setBounds(x, y, nodeSize, nodeSize);

// label.add(nodeLabel);

// if (node.left != null) {
// displayBinarySearchTree(node.left, x - xOffset, y + 80, xOffset / 2);
// }
// if (node.right != null) {
// displayBinarySearchTree(node.right, x + xOffset, y + 80, xOffset / 2);
// }
// }

// }
