package project2;

public class TestHarness {

    public static void main(String[] args) {
        try {
            assert false : "assertions work";
            System.out.println("\u001B[31mASSERTIONS NO WORKY");
            System.out.println("I ANGY");
            System.out.println("TURN ON ASSERTIONS\u001B[33m");
            System.exit(1);
        } catch (AssertionError e) {
            System.out.println("Assertions are enabled");
        }

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

        // Test circular increment
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        queue.dequeue();
        for (int i = 4; i < 13; i++) {
            queue.enqueue(i);
        }

        for (int i = 3; i < 13; i++) {
            int temp = queue.dequeue();
            assert temp == i : "Dequeued value should be " + i + " got " + temp;
        }

        System.out.println("Testing LQueue");
        LQueue<Integer> lQueue = new LQueue<>();
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
        assert lQueue.freeListCount() > 0 : "Freelist should not be empty";
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
            lList.append(2 * i);
        }

        lList.reverse();
        assert lList.toString().contains("18, 16, 14, 12, 10, 8, 6, 4, 2, 0") : "Error during reversal";
        

        lList.reverse();

        LList<Integer> lListB = new LList<>();
        for (int i = 0; i < 10; i++) {
            lListB.append(2 * i + 1);
        }
        lListB.moveToStart();

        LList<Integer> mergedList = lList.mergeList(lListB);
        mergedList.moveToStart();
        for (int i = 0; i < 20; i++) {
            assert mergedList.getValue() == i : "Merged list value should be " + i + ", got " + mergedList.getValue();
            mergedList.next();
        }

        SALdictionary<Integer, Integer> dict = new SALdictionary<>(10);
        for (int i = 9; i >= 0; i--) {
            dict.insert(i, i * 42);
        }
        System.out.println("Dictionary (keys should be in order): ");
        System.out.println(dict.toString());
        for (int i = 0; i < 10; i++) {
            assert dict.find(i) == i * 42 : "Error during find(). Expected " + (i * 42) + ", got " + dict.find(i);
        }
        AList<Integer> aList = new AList<>(10);
        for (int i = 0; i < 10; i++) {
            aList.append(i);
        }
        assert aList.toString().contains("0, 1, 2, 3, 4, 5, 6, 7, 8, 9") : "Error during AList toString()";

        System.out.println("Edge cases: ");
        aList.clear();
        lList.clear();
        lQueue.clear();
        queue.clear();
        dict.clear();

        assert dict.toString() == "" : "toString breaks on empty dictionary";
        assert aList.toString() == "" : "toString breaks on empty AList";
        assert lList.toString() == "" : "toString breaks on empty LList";
        assert lQueue.toString() == "" : "toString breaks on empty LQueue";
        assert queue.toString() == "" : "toString breaks on empty AQueue";

        try {
            aList.clear();
            assert aList.length() == 0;
            System.out.println("Handles clearing empty AList");
        } catch (Exception e) {
            assert false : "Error during clearing empty AList: " + e;
        }

        try {
            lList.clear();
            assert lList.length() == 0;
            System.out.println("Handles clearing empty LList");
        } catch (Exception e) {
            assert false : "Error during clearing empty LList: " + e;
        }

        try {
            lQueue.clear();
            assert lQueue.length() == 0;
            System.out.println("Handles clearing empty LQueue");
        } catch (Exception e) {
            assert false : "Error during clearing empty LQueue: " + e;
        }

        try {
            queue.clear();
            assert queue.length() == 0;
            System.out.println("Handles clearing empty AQueue");
        } catch (Exception e) {
            assert false : "Error during clearing empty AQueue: " + e;
        }

        try {
            dict.clear();
            assert dict.length() == 0;
            System.out.println("Handles clearing empty AQueue");
        } catch (Exception e) {
            assert false : "Error during clearing empty AQueue: " + e;
        }

        try {
            aList.append(0);
            lList.append(0);
            lQueue.enqueue(0);
            queue.enqueue(0);
            dict.insert(0, 42);
            assert aList.length() == 1;
            assert lList.length() == 1;
            assert lQueue.length() == 1;
            assert queue.length() == 1;
            assert dict.length() == 1;
        } catch (Exception e) {
            assert false : "Error during appending after clearing: " + e;
        }

        dict.insert(-1, 2);
        dict.insert(1, 2);

        System.out.println("Array overflow tests");
        for (int i = 1; i < 10; i++) {
            aList.append(i);
            queue.enqueue(i);
        }

        try {
            aList.append(67);
            assert false : "Expected exception for AList overflow";
        } catch (Throwable e) {
            System.out.println("Caught expected exception for AList overflow: " + e);
        }

        try {
            queue.enqueue(67);
            assert false : "Expected exception for AQueue overflow";
        } catch (Throwable e) {
            System.out.println("Caught expected exception for AQueue overflow: " + e);
        }

        queue.clear();
        try {
            queue.dequeue();
            assert false : "Dequeueing while empty should throw error";
        } catch (Throwable e) {
            System.out.println("Throws \"" + e.getMessage() + "\" when dequeueing from empty AQueue");
        }

        try {
            queue.frontValue();
            assert false : "frontValue() while empty should throw error";
        } catch (Throwable e) {
            System.out.println("Throws \"" + e.getMessage() + "\" when getting front from empty AQueue");
        }

        lQueue.clear();
        try {
            lQueue.dequeue();
            assert false : "Dequeueing while empty should throw error";
        } catch (Throwable e) {
            System.out.println("Throws \"" + e.getMessage() + "\" when dequeueing from empty LQueue");
        }

        try {
            lQueue.frontValue();
            assert false : "frontValue() while empty should throw error";
        } catch (Throwable e) {
            System.out.println("Throws \"" + e.getMessage() + "\" when getting front from empty LQueue");
        }

        assert dict.find(248195757) == null : "Trying to find something not in the dict should return null";

        TOH toh = new TOH(5);
        toh.solve();
        assert toh.end().toString().equals("[1, 2, 3, 4, 5]") : "Error during solving TOH";

        AQueue<Integer> test = new AQueue<>(1);

        test.enqueue(1);

        dict.clear();
        for (int i = 0; i < 10; i++) {
            dict.insert(i, i * 10);
        }

        int i = 9;
        while (dict.length() > 0) {
            assert dict.removeAny() == i * 10;
            i--;
        }

        lList.clear();
        lList.moveToEnd();
        lList.reverse();
        lList.append(0);

        assert lList.mergeList(new LList<>()).length() == 1 : "Error after merging LList with empty";

        assert lList.toString().equals("0") : "toString breaks on small LList";
        
        assert test.dequeue() == 1 : "Error during dequeueing from small aqueue";
        System.out.println(
                "Tests passed");

    }
}
