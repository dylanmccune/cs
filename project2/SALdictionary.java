package project2;

class SALdictionary<K extends Comparable<? super K>, E>
        implements Dictionary<K, E> {
    private static final int defaultSize = 10; // Default size
    private AList<KVpair<K, E>> list; // To store dictionary
    // Constructors

    SALdictionary() {
        this(defaultSize);
    }

    SALdictionary(int sz) {
        list = new AList<KVpair<K, E>>(sz);
    }

    public void clear() {
        list.clear();
    } // Reinitialize

    /** Insert an element: append to list */
    public void insert(K k, E e) {
        KVpair<K, E> temp = new KVpair<K, E>(k, e);
        list.moveToStart();

        if (list.length() == 0) {
            list.append(temp);
            return;
        }
        while (list.getValue().key().compareTo(temp.key()) < 0 && list.currPos() <
        list.length() - 1) {
        list.next();
        }
        list.insert(temp);
    }

    /** Use sequential search to find the element to remove */
    public E remove(K k) {
        E temp = find(k);
        if (temp != null)
            list.remove();
        return temp;
    }

    /** Remove the last element */
    public E removeAny() {
        if (size() != 0) {
            list.moveToEnd();
            list.prev();
            KVpair<K, E> e = list.remove();
            return e.value();
        } else
            return null;
    }

    // Find "k" using sequential search
    public E find(K k) {
        for (list.moveToStart(); list.currPos() < list.length(); list.next()) {
            KVpair<K, E> temp = list.getValue();
            if (k == temp.key())
                return temp.value();
        }
        return null; // "k" does not appear in dictionary
    }

    public int size() // Return list size
    {
        return list.length();
    }

    public String toString() {
        String string = "";
        for (list.moveToStart(); list.currPos() + 1 < list.length(); list.next()) {
            KVpair<K, E> temp = list.getValue();
            string += temp.toString() + "\n";
        }
        KVpair<K, E> temp = list.getValue();
        string += temp.toString();
        return string;
    }
}
