package project2;

public class TOH {
    Pole start = new Pole();
    Pole goal = new Pole();
    Pole temp = new Pole();
    int count;

    TOH(int size) {
        count = size;
        for (int i = size; i >= 1; i--) {
            start.add(i);
        }
    }

    /**
     * Solve the towers of hanoi
     */
    void solve() {
        solveHelp(count, start, goal, temp);
    }

    /**
     * Satirical fast solution
     */
    void solveFast() {
        // The towers of hanoi problem is fun to think about, but it's also kinda stupid
        // because it mandates the method of solving it.
        // That would be like saying "implement AQueues, but you can't use circular
        // increment".
        // Like there's a faster way, why not use it?
        // So, this is the fast way.
        goal = start;
        start = new Pole();
    }

    static void solveHelp(int n, Pole start, Pole goal, Pole temp) {
        if (n == 0)
            return; // Base case
        solveHelp(n - 1, start, temp, goal);
        move(start, goal); // Move bottom disk to goal
        System.out.println("Start pole: " + start.toString() + "\nEnd pole: " + goal.toString() + "\nMiddle pole: "
                + temp.toString());
        solveHelp(n - 1, temp, goal, start);
    }

    /**
     * Move the top ring from one pole to another
     * @param start
     * @param end
     */
    static void move(Pole start, Pole end) {
        int ring = start.remove();
        end.add(ring);
    }

    /**
     * The first pole
     * @return
     */
    Pole start() {
        return start;
    }

    /**
     * The last pole
     * @return
     */
    Pole end() {
        return goal;
    }

    public String toString() {
        return "Start pole: " + start.toString() + "\nGoal pole: " + goal.toString() + "\nTemp pole: "
                + temp.toString();
    }
}
