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
        LQueue<Integer> lQueue = new LQueue<>(10);
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
        System.out.println("Length of freelist: " + lQueue.freeListCount());
        assert lQueue.freeListCount() == 9 : "Freelist should be 9, got " + lQueue.freeListCount();

        LList<Integer> lList = new LList<>();
        for (int i = 0; i < 10; i++) {
            lList.append(i);
        }
        lList.reverse();
        lList.moveToStart();
        for (int i = 0; i < 10; i++) {
            assert lList.remove() == 9 - i : "Removed value should be " + (9 - i) + ", got " + lList.getValue();
        }

        for (int i = 0; i < 10; i++) {
            lList.append(2*i);
        }

        LList<Integer> lListB = new LList<>();
        for (int i = 0; i < 10; i++) {
            lListB.append(2*i + 1);
        }
        lListB.moveToStart();

        LList<Integer> mergedList = lList.mergeList(lListB);
        mergedList.moveToStart();
        for (int i = 0; i < 20; i++) {
            assert mergedList.getValue() == i : "Merged list value should be " + i + ", got " + mergedList.getValue();
            mergedList.next();
        }

        SALdictionary<Integer, Integer> dict = new SALdictionary<>();
        for (int i = 9; i >= 0; i--) {
            dict.insert(i, i * 42);
        }

        System.out.println(dict.toString());
        for (int i = 0; i < 10; i++) {
            assert dict.find(i) == i * 42 : "Error during find(). Expected " + (i * 42) + ", got " + dict.find(i);
        }
        AList<Integer> aList = new AList<>(10);
        for (int i = 0; i < 10; i++) {
            aList.append(i);
        }
        System.out.println(aList.toString());

        System.out.println(
                "Tests passed");

        try {
            assert false : "assertions work";
            System.out.println("\u001B[31mASSERTIONS NO WORKY");
            System.out.println("I ANGY");
            System.out.println("TURN ON ASSERTINS\u001B[33m");
        } catch (AssertionError e) {
            System.out.println("Assertions are enabled");
        }
    }
}
