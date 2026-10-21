package project2;

public class TestHarness {

    public static void main(String[] args) {
        System.out.println("Testing AQueue");
        Queue<Integer> queue = new AQueue<>(10);
        for (int j = 0; j < 10; j++) {

            for (int i = 0; i < 9; i++) { // Use 9 elements to test circular increment
                queue.enqueue(i);
                assert queue.length() == i + 1 : "Size sholuld return " + (i + 1) + ", got " + queue.length();
            }
            for (int i = 0; i < 9; i++) {
                assert queue.frontValue() == i : "Front value should be " + i + ", got " + queue.frontValue();
                assert queue.dequeue() == i : "Dequeued value should be " + i;
            }
        }
        System.out.println("Testing LQueue");
        Queue<Integer> lQueue = new AQueue<>(10);
        for (int j = 0; j < 10; j++) {

            for (int i = 0; i < 9; i++) { // Use 9 elements to test circular increment
                lQueue.enqueue(i);
                assert lQueue.length() == i + 1 : "Size sholuld return " + (i + 1) + ", got " + lQueue.length();
            }
            for (int i = 0; i < 9; i++) {
                assert lQueue.frontValue() == i : "Front value should be " + i + ", got " + lQueue.frontValue();
                assert lQueue.dequeue() == i : "Dequeued value should be " + i;
            }
        }

        System.out.println(
                "Tests passed");
        assert false : "assertions work";
    }
}
