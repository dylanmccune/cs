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
        int high = list.length() - 1;
        int low = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            list.moveToPos(mid);
            KVpair<K, E> midPair = list.getValue();
            if (midPair.key().compareTo(temp.key()) == -1) {
                low = mid + 1;
            } else if (midPair.key().compareTo(temp.key()) == 1) {
                high = mid - 1;
            } else {
                list.remove();
                list.insert(temp);
                return;
            }
        }
        list.moveToPos(low);
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

    // Find "k" using binary search
    public E find(K k) {
        int low = 0;
        int high = list.length() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            list.moveToPos(mid);
            if (list.getValue().key().compareTo(k) == 0) {
                return list.getValue().value();
            } else if (list.getValue().key().compareTo(k) == -1) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null; // "k" does not appear in dictionary
    }

    /** Move to a position in the queue
     * @param pos The position to be moved to
     */
    public void moveToPos(int pos) {
        list.moveToPos(pos);
    }

    public int size() // Return list size
    {
        return list.length();
    }

    public int length() {
        return size(); // Make the method name consistent with lists
    }
    /**
     * @return A string representation of the items in the dictionary
     */
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
