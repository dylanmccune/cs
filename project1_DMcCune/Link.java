package project1_DMcCune;

// Singly linked list node with freelist support
class Link<E> {
    private E element; // Value for this node
    private Link<E> next; // Pointer to next node in list
    // Constructors

    Link(E it, Link<E> nextval) {
        element = it;
        next = nextval;
    }

    Link(Link<E> nextval) {
        next = nextval;
    }

    Link<E> next() {
        return next;
    }

    Link<E> setNext(Link<E> nextval) {
        return next = nextval;
    }

    E element() {
        return element;
    }

    E setElement(E it) {
        return element = it;
    }

    // Extensions to support freelists
    static Link freelist = null; // Freelist for the class
    // Get new link

    static <E> Link<E> get(E it, Link<E> nextval) {
        if (freelist == null)
            return new Link<E>(it, nextval); // Get a new link because none exist in freelist
        Link<E> temp = freelist; // Get from freelist
        freelist = freelist.next();
        temp.setElement(it);
        temp.setNext(nextval);
        return temp;
    }

    void release() { // Return Link to freelist
        element = null; // Drop reference to the element
        next = freelist;
        freelist = this;
    }
} // class Link