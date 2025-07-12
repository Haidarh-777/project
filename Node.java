
public class Node {
    int id;
    String name;
    int price;
    int quantity;
    Node left;
    Node right;
    int height;

    Node(int id, String name, int price, int quantity) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;

        left = null;
        right = null;
        height = 1;
    }
}
