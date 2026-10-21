package project2;

// Linked queue implementation
class LQueue<E> implements Queue<E> {

    private Link<E> front; // Pointer to front queue node
    private Link<E> rear; // Pointer to rear queuenode
    int size; // Number of elements in queue
// Constructors

    public LQueue() {
        init();
    }

    public LQueue(int size) {
        init();
    } // Ignore size

    private void init() { // Initialize queue
        front = rear = null;
        size = 0;
    }

    public void clear() {
        init();
    } // Reinitialize queue

    public void enqueue(E it) { // Put element on rear
        if (rear == null) {
            front = rear = Link.get(it, null);
        } else {
            rear.setNext(Link.get(it, null));
            rear = rear.next();
        }
        size++;
    }

    public E dequeue() { // remove element from front
        assert size != 0 : "Queue is empty";
        E it = front.element(); // Store dequeued value
        Link<E> temp = front; // Remember link to return to freelist
        front = front.next(); // Advance front
        if (front == null) {
            rear = null; // Queue is empty
        }
        temp.release(); // Return to freelist
        size--;
        return it; // Return Object
    }

    public E frontValue() { // Get front element
        assert size != 0 : "Queue is empty";
        return front.element();
    }

    public int length() {
        return size;
    } // Return length

    public int freeListCount() {
        int freeListCount = 0;
        Link<E> temp = Link.freelist;
        while (temp != null) {
            freeListCount++;
            temp = temp.next();
        }
        return freeListCount;
    }
}
