
import java.util.ArrayList;
import java.util.Scanner;

public class maxHeapTree {

    private ArrayList<Order> orders = new ArrayList<>();

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void heapifyUp(Order order) {
        int index = order.id - 1;
        if (index == 0)
            return;
        if (orders.get(index).priority > orders.get(parent(index)).priority) {
            Order temp = orders.get(index);
            orders.set(index, orders.get(parent(index)));
            orders.set(parent(index), temp);
            heapifyUp(orders.get(parent(index)));

        }
    }

    static void heapify(ArrayList<addShipment> arr,int i,int size) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left<size && arr.get(left).priority > arr.get(largest).priority){
            largest = left;
        }
        if (right < size && arr.get(right).priority > arr.get(largest).priority) {
            largest = right;
        }
        if (largest != i) {
            addShipment temp = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, temp);
            heapify(arr, largest, size);
        }
    }

    public void insert(Order order) {
        orders.add(order);
        heapifyUp(order);
    }

    public void printHeap() {
        int count = 0;
        for (Order o : orders) {
            count++;
            System.out.println("Order " + count + " : ");
            printOrder(o);
        }
    }

    public Order getTheMostUrgent() {
        return orders.get(0);
    }

    public void updatePriority(int id, int newPriority) {
        orders.get(id).priority = newPriority;
    }

    public void printOrder(Order order) {
        System.out.println("Order id : " + order.id);
        System.out.println("Order priority : " + order.priority);
        System.out.println("Order product list : " + order.order);
    }

}

class Order {
    ArrayList<Node> order;
    int priority;
    int id;
    private static int nextId = 1;

    Order(ArrayList<Node> order, int priority) {
        this.order = order;
        this.priority = priority;
        this.id = nextId++;
    }

    Order(ArrayList<Node> order) {
        this.order = order;
        this.id = nextId++;
    }
}

