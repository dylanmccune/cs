package project2;

// Container for a key-value pair
class KVpair<K, E> {
    private K k;
    private E e;

    // Constructors
    KVpair() {
        k = null;
        e = null;
    }

    KVpair(K kval, E eval) {
        k = kval;
        e = eval;
    }

    // Data member access functions
    public K key() {
        return k;
    }

    public E value() {
        return e;
    }

    public String toString() {
        return k + ": " + e;
    }
}