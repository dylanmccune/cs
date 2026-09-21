package project1_DMcCune;

// Linked list implementation
class LList<E> implements List<E> {
    private Link<E> head; // Pointer to list header
    private Link<E> tail; // Pointer to last element
    protected Link<E> curr; // Access to current element
    int count; // Size of list
    int index; // Current list index
    // Constructors

    LList(int size) {
        this();
    } // Constructor -- Ignore size

    LList() {
        curr = tail = head = new Link<E>(null); // Create header
        count = 0;
        index = 0;
    }

    public void clear() { // Remove all elements
        head.setNext(null); // Drop access to links
        curr = tail = head = new Link<E>(null); // Create header
        count = 0;
        index = 0;
    }

    // Insert "it" at current position
    public void insert(E it) {
        curr.setNext(Link.get(it, curr.next()));
        if (tail == curr)
            tail = curr.next(); // New tail
        count++;
    }

    public void append(E it) { // Append "it" to list
        tail = tail.setNext(Link.get(it, null));
        count++;
    }

    public void moveToStart() // Set curr at list start
    {
        curr = head;
        index = 0;
    }

    // Remove and return current element
    public E remove() {
        if (curr.next() == null)
            return null; // Nothing to remove
        Link<E> it = curr.next(); // Remember value
        if (tail == curr.next())
            tail = curr; // Removed last
        curr.setNext(curr.next().next()); // Remove from list
        E value = it.element(); // Remember value before releasing so it can be returned
        it.release(); // Return to freelist
        count--; // Decrement count
        return value; // Return value
    }

    public void moveToEnd() // Set curr at list end
    {
        curr = tail;
        prev();
        index = count - 1;
    }

    // Move curr one step left; no change if already at front
    public void prev() {
        if (curr == head)
            return; // No previous element
        Link<E> temp = head;
        // March down list until we find the previous element
        while (temp.next() != curr)
            temp = temp.next();
        curr = temp;
        index--;
    }

    // Move curr one step right; no change if already at end
    public void next() {
        if (curr != tail) {
            curr = curr.next();
            index++;
        }
    }

    public int length() {
        return count;
    }

    // Return the position of the current element
    public int currPos() {
        return index;
    }

    // Move down list to "pos" position
    public void moveToPos(int pos) {
        assert (pos >= 0) && (pos < count) : "Position out of range";
        curr = head;
        index = 0;
        while (index < pos) {
            next();
        }
    }

    public E getValue() { // Return current element
        if (curr.next() == null)
            return null;
        return curr.next().element();
    }

}