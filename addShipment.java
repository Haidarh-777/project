import java.util.ArrayList;

public class addShipment {
    static ArrayList<addShipment> list = new ArrayList<>();
    static ArrayList<addShipment> heap = new ArrayList<>();

    String destination;
    String date;
    int id;
    int product_id;
    int quantity;
    int priority;

    public addShipment(String destination, int id, int product_id, int quantity, int priority, String date) {
        this.destination = destination;
        this.date = date;
        this.id = id;
        this.quantity = quantity;
        this.priority = priority;
        this.product_id = product_id;

        list.add(this);
        insert(this);
    }

    @Override
    public String toString() {
        return "{ Destination : " + destination + "  , ID : " + id + ", Product ID : " + product_id
                + " , Quantity : " + quantity + " , Priority : " + priority + " , Date : " + date + "}";
    }

    static void insert(addShipment shipment) {
        heap.add(shipment);
        heapifyUp(heap.size() - 1);
    }

    static void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            if (compare(heap.get(index), heap.get(parentIndex)) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    static void swap(int i, int j) {
        addShipment temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    static int compare(addShipment a, addShipment b) {
        if (a.priority != b.priority) {
            return Integer.compare(a.priority, b.priority);
        } else {
            return Integer.compare(list.indexOf(a), list.indexOf(b)) * -1;
        }
    }
}
