package project2;

// Linked list implementation
class LList<E extends Comparable<E>> implements List<E> {

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
        if (tail == curr) {
            tail = curr.next(); // New tail

        }
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
        if (curr.next() == null) {
            return null; // Nothing to remove

        }
        Link<E> it = curr.next(); // Remember value
        if (tail == curr.next()) {
            tail = curr; // Removed last

        }
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
        if (curr == head) {
            return; // No previous element

        }
        Link<E> temp = head;
        // March down list until we find the previous element
        while (temp.next() != curr) {
            temp = temp.next();
        }
        curr = temp;
        index--;
    }

    // Move curr one step right; no change if already at end
    public void next() {
        if (index != length() - 1) {
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
        if (curr.next() == null) {
            return null;
        }
        return curr.next().element();
    }

    public void reverse() {
        Link<E> prev = null;
        Link<E> current = head.next();
        Link<E> next;

        tail = current;

        while (current != null) {
            next = current.next();
            current.setNext(prev);
            prev = current;
            current = next;
        }

        head.setNext(prev);
    }

    public LList<E> mergeList(LList<E> b) {
        LList<E> mergedList = new LList<>();
        b.moveToStart();
        this.moveToStart();

        while (this.curr.next() != null && b.curr.next() != null) {
            if (this.getValue().compareTo(b.getValue()) <= 0) {
                mergedList.append(this.getValue());
                this.curr = this.curr.next();
            } else {
                mergedList.append(b.getValue());
                b.curr = b.curr.next();
            }
        }

        while (this.curr.next() != null) {
            mergedList.append(this.getValue());
            this.curr = this.curr.next();
        }
        while (b.curr.next() != null) {
            mergedList.append(b.getValue());
            b.curr = b.curr.next();
        }
        return mergedList;
    }

}
