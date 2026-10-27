package project2;

import java.util.ArrayDeque;
import java.util.Deque;

class Pole {
    Deque<Integer> rings = new ArrayDeque<>();
    Pole(){}

    void add(int ring) {
        assert  rings.isEmpty() || ring < rings.getFirst() : "Cannot add a bigger ring to a smaller ring";
        rings.push(ring);
    }

    int remove() {
        return rings.pop();
    }

    public String toString() {
        return rings.toString();
    }
}
