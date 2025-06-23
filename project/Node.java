package project;

public class Node {
    int id;
    String name;
    int price;
    int quantity;
    int priority;
    Node left;
    Node right;
    int height;

    Node(int id, String name, int price, int quantity, int priority) {
        if (price < 0)
            throw new IllegalArgumentException("you have entered negative number");
        if (quantity > 1000)
            throw new IllegalArgumentException("Quantity exceeds warehouse capacity");

        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.priority = priority;

        left = null;
        right = null;
        height = 1;
    }
}
