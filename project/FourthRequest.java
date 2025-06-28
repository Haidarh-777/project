import java.util.ArrayList;
import java.util.Scanner;

public class Fourth {

    private ArrayList<Order> orders = new ArrayList<>();
    private int parent(int i) {return (i-1)/2;}

    private void heapifyUp(Order order){
        int index = order.id-1;
        if(index==0) return;
        if(orders.get(index).priority > orders.get(parent(index)).priority){
            Order temp = orders.get(index);
            orders.set(index,orders.get(parent(index)));
            orders.set(parent(index),temp);
            heapifyUp(orders.get(parent(index)));

        }
    }

    public void insert(Order order){
        orders.add(order);
        heapifyUp(order);
    }

    public void printHeap(){
        int count = 0;
        for(Order o : orders){
            count++;
            System.out.println("Order "+ count + " : ");
            printOrder(o);
        }
    }

    public Order getTheMostUrgent(){
        return orders.getFirst();
    }

    public void updatePriority(int id,int newPriority){
        orders.get(id).priority=newPriority;
    }

    public void printOrder(Order order){
        System.out.println("Order id : " + order.id);
        System.out.println("Order priority : " + order.priority);
        System.out.println("Order product list : " + order.order);
    }

}

class Node {

    int id;
    String name;
    double price;
    int quantity;
    Node left;
    Node right;

    Node(int id, String name,double price, int quantity){

        if(price<0) throw new IllegalArgumentException("You have entered negative number");
        if(quantity>1000) throw new IllegalArgumentException("Quantity exceeds warehouse capacity");

        this.id=id;
        this.name=name;
        this.price= price;
        this.quantity= quantity;

        left=null;
        right=null;
    }
}

class Order{
    ArrayList<Node> order;
    int priority;
    int id;
    private static int nextId = 1;

    Order(ArrayList<Node> order, int priority){
        this.order = order;
        this.priority = priority;
        this.id = nextId++;
    }
    Order(ArrayList<Node> order){
        this.order = order;
        this.id = nextId++;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Fourth f = new Fourth();
        ArrayList<Node> products = new ArrayList<>();
        products.add(new Node(1,"Ketchup",10,20));
        products.add(new Node(2,"Fried Chicken",200,10));
        products.add(new Node(3,"Tea",50,30));
        int num = 0;

        while(num!=-1) {
            System.out.println("Choose a number:");
            System.out.println("1 : Add an order with a priority");
            System.out.println("2 : Add an order without priority");
            System.out.println("3 : Get the highest priority order");
            System.out.println("4 : Update a priority for a order");
            System.out.println("-1 : Exit");

            num = scan.nextInt();

            switch (num) {
                case 1:
                    System.out.println("Enter priority");
                    int priority1 = scan.nextInt();
                    System.out.println("Enter the number of products in the order");
                    int size1 = scan.nextInt();
                    ArrayList<Node> order1 = new ArrayList<>();
                    System.out.println("Products : ");
                    for (Node n : products){
                        System.out.println("    Product id : "+ n.id);
                        System.out.println("    Product name : " + n.name);
                        System.out.println("    Product price : "+n.price);
                    }
                    for (int i = 0; i < size1; i++) {
                        System.out.println("Enter the product id");
                        int productId = scan.nextInt();
                        String productName = products.get(productId).name;
                        double productPrice = products.get(productId).price;
                        System.out.println("Enter the quantity u want");
                        int productQuantity = scan.nextInt();
                        order1.add(new Node(productId,productName,productPrice,productQuantity));
                    }
                    Order o1 = new Order(order1, priority1);
                    f.insert(o1);
                    break;

                case 2:
                    System.out.println("Enter the number of products in the order");
                    int size2 = scan.nextInt();
                    ArrayList<Node> order2 = new ArrayList<>();
                    System.out.println("Products : ");
                    for (Node n : products){
                        System.out.println("    Product id : "+ n.id);
                        System.out.println("    Product name : " + n.name);
                        System.out.println("    Product price : "+n.price);
                    }
                    for (int i = 0; i < size2; i++) {
                        System.out.println("Enter the product id");
                        int productId = scan.nextInt();
                        String productName = products.get(productId).name;
                        double productPrice = products.get(productId).price;
                        System.out.println("Enter the quantity u want");
                        int productQuantity = scan.nextInt();
                        order2.add(new Node(productId,productName,productPrice,productQuantity));
                    }
                    Order o2 = new Order(order2, 0);
                    f.insert(o2);
                    break;

                case 3:
                    f.printOrder(f.getTheMostUrgent());
                    break;

                case 4:
                    System.out.println("Enter the id of the order");
                    int id3 = scan.nextInt();
                    System.out.println("Enter the new priority");
                    int newPriority3 = scan.nextInt();
                    f.updatePriority(id3, newPriority3);
                    System.out.println("Orders after updating the priority : ");
                    f.printHeap();
                    break;
            }
            System.out.println("Operation Completed.");
        }



    }
}
