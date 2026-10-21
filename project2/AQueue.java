package project2;
// Array-based queue implementation

class AQueue<E> implements Queue<E> {

    private static final int defaultSize = 10;
    private int maxSize; // Maximum size of queue
    private int front; // Index of front element
    private E[] listArray; // Array holding queue elements
    private int length; // Current length of queue
// Constructors

    AQueue() {
        this(defaultSize);
    }

    @SuppressWarnings("unchecked") // For generic array
    AQueue(int size) {
        maxSize = size;
        front = 1;
        length = 0;
        listArray = (E[]) new Object[maxSize]; // Create listArray
    }

    public void clear() // Reinitialize
    {
        front = 1;
        length = 0;
    }

    public void enqueue(E it) { // Put "it" in queue
        assert length < maxSize : "Queue is full";
        listArray[(front + length) % maxSize] = it;
        length++;
    }

    public E dequeue() { // Take element out of queue
        assert length() != 0 : "Queue is empty";
        E it = listArray[front];
        front = (front + 1) % maxSize; // Circular increment
        length--;
        return it;
    }

    public E frontValue() { // Get front value
        assert length() != 0 : "Queue is empty";        
        return listArray[front];
    }

    public int length() // Return length
    {
        return length;

    }
}
