package project2;

import java.util.ArrayDeque;
import java.util.Deque;

class Pole {
    Deque<Integer> rings = new ArrayDeque<>();
    Pole(){}

    /**
     * Add a ring to the pole. The ring must be smaller than the current top ring.
     * @param ring
     */
    void add(int ring) {
        assert  rings.isEmpty() || ring < rings.getFirst() : "Cannot add a bigger ring to a smaller ring";
        rings.push(ring);
    }

    /**
     * Remove the top ring from the pole.
     * @return The removed ring
     */
    int remove() {
        return rings.pop();
    }

    public String toString() {
        return rings.toString();
    }
}
