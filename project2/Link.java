package project2;

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

    /**
     * @return The next link in the list.
     */
    Link<E> next() {
        return next;
    }

    /**
     * Set the current link's next pointer
     * @param nextval The link to set as next
     */
    Link<E> setNext(Link<E> nextval) {
        return next = nextval;
    }

    /**
     * @return The stored element
     */
    E element() {
        return element;
    }

    /**
     * Set the current link's element
     * @param it The element to be set
     * @return The set element
     */
    E setElement(E it) {
        return element = it;
    }

    // Extensions to support freelists
    /**
     * Freelist for the class
     */
    static Link freelist = null;
    /**
     * Get new link
     * @param <E>
     * @param it The element to be stored in the link
     * @param nextval The pointer to the next link
     * @return
     */

    static <E> Link<E> get(E it, Link<E> nextval) {
        if (freelist == null) {
            return new Link<E>(it, nextval); // Get a new link because none exist in freelist
        }
        Link<E> temp = freelist; // Get from freelist
        freelist = freelist.next();
        temp.setElement(it);
        temp.setNext(nextval);
        return temp;
    }

    /**
     * Return Link to freelist
     */
    void release() {
        element = null; // Drop reference to the element
        next = freelist;
        freelist = this;
    }
} // class Link