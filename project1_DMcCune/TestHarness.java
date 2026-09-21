package project1_DMcCune;

public class TestHarness {
    public static void main(String[] args) {
        List<Integer> list = new AList<Integer>(10);
        List<Integer> emptyList = new AList<Integer>(0);
        List<Integer> smallList = new AList<Integer>(1);
        List<Integer> fullList = new AList<Integer>(10);

        // Test appending and getting from list

        for (int i = 0; i < 10; i++) {
            list.append(i);
        }
        for (int i = 0; i < 10; i++) {
            list.moveToPos(i);
            assert list.getValue() == i : "wrong value at" + i;
        }

        System.out.println("Passed appending and getting");

        list.clear();

        for (int i = 0; i < 10; i++) {
            list.append(i);
            list.moveToEnd();
            assert list.currPos() == i : "couldn't move to end";
            assert list.currPos() == list.length() - 1 : "couldn't move to end";
            list.moveToStart();
            assert list.currPos() == 0 : "couldn't move to start";
        }

        System.out.println("Passed moving to start and end");

        list.clear();


        for (int i = 0; i < 10; i++) {
            list.insert(i);
        }

        list.moveToEnd();
        for (int i = 0; i < 10; i++) {
            assert list.getValue() == i : "wrong value when checking insertions";
            list.prev();
        }

        System.out.println("Passed insertions");

        list.moveToStart();

        for (int i = 0; i < 10; i++) {
            assert list.remove() == 9 - i : "wrong return from removal";
        }

        assert list.length() == 0 : "list not empty after removing all elements";

        System.out.println("Passed removals");

        // Test exceeding list capacity
        for (int i = 0; i < 10; i++) {
            fullList.insert(i);
        }

        try {
            fullList.insert(0);
        } catch (AssertionError e) {
            if (e.getMessage().contains("capacity exceeded")) {
                System.out.println("Catches error when exceeding list capacity");
            } else {
                System.out.println("Wrong error when inserting into full list");
                assert false;
            }
        } catch (Exception e) {
            System.out.println("Wrong error when inserting into full list");
            assert false;
        }
        
        System.out.println("Passed capacity test");

        // Test getting the current position
        System.out.println("Testing currPos()...");
        list.clear();
        for (int i = 0; i < 10; i++) {
            list.append(i);
        }
        list.moveToStart();
        for (int i = 0; i < 10; i++) {
            list.moveToPos(i);
            assert list.currPos() == i : "mismatch at index " + i;
        }
        System.out.println("Passed currPos() test");

        System.out.println("Testing empty lists...");

        System.out.println("Removal from empty list: " + emptyList.remove());
        assert emptyList.remove() == null : "Removing from empty list should return null";

        try {
            emptyList.insert(0);
        } catch (AssertionError e) {
            if (e.getMessage().contains("capacity exceeded")) {
                System.out.println("Catches error when inserting into empty list");
            } else {
                System.out.println("Wrong error when inserting into empty list");
                assert false;
            }
        } catch (Exception e) {
            System.out.println("Wrong error when inserting into empty list");
            assert false;
        }

        try {
            emptyList.next();
        } catch (AssertionError e) {
            System.out.println("Errors when getting next() in empty list");
            assert false;
        }

        try {
            emptyList.prev();
        } catch (AssertionError e) {
            System.out.println("Errors when getting prev() in empty list");
            assert false;
        }
        try {
            emptyList.append(0);
        } catch (AssertionError e) {
            if (e.getMessage().contains("capacity exceeded")) {
                System.out.println("Catches error when appending to empty list");
            } else {
                System.out.println("Wrong error when appending to empty list");
                System.out.println("Expected assertionError Capacity exceeded, got " + e.getMessage());
                assert false;
            }
        } catch (Exception e) {
            System.out.println("Wrong error when appending to empty list");
            System.out.println("Expected assertionError Capacity exceeded, got " + e.getMessage());
            assert false;
        }

        try {
            emptyList.clear();
        } catch (AssertionError e) {
            System.out.println("Errors when clearing empty list");
            System.out.println("Clearing an empty list should just do nothing");
            assert false;
        }
        
        smallList.append(53);
        assert smallList.getValue() == 53 : "Appending to a 1 element list failed";

        assert smallList.remove() == 53 : "Removing from a 1 element list failed";

        smallList.insert(85);

        assert smallList.getValue() == 85 : "Inserting into a 1 element list failed";

        // Test moveToPos();

        list.clear();
        for (int i = 0; i < 10; i++) {
            list.append(i);
        }

        try {
            list.moveToPos(-1);
        } catch (AssertionError e) {
            System.out.println("Catches error when moving to negative position");
        } catch (Exception e) {
            System.out.println("Wrong error when moving to negative");
            assert false;
        }

        try {
            list.moveToPos(100000);
        } catch (AssertionError e) {
            assert e.getMessage().contains("out of range") : "Expected out of range, got " + e.getMessage();
        } catch (Exception e) {
            System.out.println("Expected out of range, got " + e.getMessage());
            assert false;
        }

        // Test next() and prev() at list boundaries

        list.moveToStart();
        list.prev();
        assert list.currPos() == 0 : "calling prev at start should do nothing";
        list.moveToEnd();
        list.next();
        assert list.currPos() == list.length() - 1 : "calling next at end should do nothing";

        List<Integer> llist = new LList<Integer>();
        List<Integer> emptyLList = new LList<Integer>();
        List<Integer> smallLList = new LList<Integer>();
        List<Integer> fullLList = new LList<Integer>();

        // Test appending and getting from list

        for (int i = 0; i < 10; i++) {
            llist.append(i);
        }
        for (int i = 0; i < 10; i++) {
            llist.moveToPos(i);
            assert llist.getValue() == i : "wrong value at" + i;
        }

        System.out.println("Passed appending and getting");

        llist.clear();

        for (int i = 0; i < 10; i++) {
            llist.append(i);
            llist.moveToEnd();
            assert llist.currPos() == i : "couldn't move to end";
            assert llist.currPos() == llist.length() - 1 : "couldn't move to end";
            llist.moveToStart();
            assert llist.currPos() == 0 : "couldn't move to start";
        }

        System.out.println("Passed moving to start and end");

        llist.clear();


        for (int i = 0; i < 10; i++) {
            llist.insert(i);
        }

        llist.moveToEnd();
        for (int i = 0; i < 10; i++) {
            assert llist.getValue() == i : "wrong value when checking insertions";
            llist.prev();
        }

        System.out.println("Passed insertions");

        llist.moveToStart();

        for (int i = 0; i < 10; i++) {
            assert llist.remove() == 9 - i : "wrong return from removal";
        }

        assert llist.length() == 0 : "list not empty after removing all elements";

        System.out.println("Passed removals");

        // Test exceeding list capacity
        for (int i = 0; i < 100000; i++) {
            fullLList.insert(i);
        }

        for (int i = 0; i < 100000; i++) {
            fullLList.remove();
        }
        
        System.out.println("Passed capacity test");

        // Test getting the current position
        System.out.println("Testing currPos()...");
        llist.clear();
        for (int i = 0; i < 10; i++) {
            llist.append(i);
        }
        llist.moveToStart();
        for (int i = 0; i < 10; i++) {
            llist.moveToPos(i);
            assert llist.currPos() == i : "mismatch at index " + i;
        }
        System.out.println("Passed currPos() test");

        System.out.println("Testing empty lists...");

        System.out.println("Removal from empty list: " + emptyLList.remove());
        assert emptyLList.remove() == null : "Removing from empty list should return null";

        try {
            emptyLList.next();
        } catch (AssertionError e) {
            System.out.println("Errors when getting next() in empty list");
            assert false;
        }

        try {
            emptyLList.prev();
        } catch (AssertionError e) {
            System.out.println("Errors when getting prev() in empty list");
            assert false;
        }
        try {
            emptyLList.append(0);
        
        } catch (Exception e) {
            System.out.println("Errors when appending to empty list");
            System.out.println("Got " + e.getMessage());
            assert false;
        }

        try {
            emptyLList.clear();
        } catch (AssertionError e) {
            System.out.println("Errors when clearing empty list");
            System.out.println("Clearing an empty list should just do nothing");
            assert false;
        }
        
        smallLList.append(53);
        assert smallLList.getValue() == 53 : "Appending to a 1 element list failed";

        assert smallLList.remove() == 53 : "Removing from a 1 element list failed";

        smallLList.insert(85);

        assert smallLList.getValue() == 85 : "Inserting into a 1 element list failed";

        smallLList.clear();
        smallLList.append(0);
        smallLList.prev();
        smallLList.moveToEnd();
        assert smallLList.currPos() == 0 : "Mismatch after moving around 1 element list";
        smallLList.next();
        smallLList.append(1);
        assert smallLList.getValue() == 1 : "Mismatch after appending at tail";

        smallLList.insert(2);
        assert smallLList.getValue() == 2 : "Mismatch after insertion at tail";

        // Test moveToPos();

        llist.clear();
        for (int i = 0; i < 10; i++) {
            llist.append(i);
        }

        try {
            llist.moveToPos(-1);
        } catch (AssertionError e) {
            System.out.println("Catches error when moving to negative position");
        } catch (Exception e) {
            System.out.println("Wrong error when moving to negative");
            assert false;
        }

        try {
            llist.moveToPos(100000);
        } catch (AssertionError e) {
            assert e.getMessage().contains("out of range") : "Expected out of range, got " + e.getMessage();
        } catch (Exception e) {
            System.out.println("Expected out of range, got " + e.getMessage());
            assert false;
        }

        // Test next() and prev() at list boundaries

        llist.moveToStart();
        llist.prev();
        assert llist.currPos() == 0 : "calling prev at start should do nothing";
        llist.moveToEnd();
        llist.next();
        assert llist.currPos() == llist.length() : "calling next at end should do nothing";


        // Just some more random tests to make sure

        llist.moveToPos(5);
        llist.remove();
        assert llist.currPos() == 5 : "mismatch after removal";
        llist.moveToEnd();
        assert llist.currPos() == 8 : "mismatch after moving to end";
        llist.moveToStart();
        assert llist.currPos() == 0 : "mismatch after moving to start";
        llist.insert(3);
        assert llist.currPos() == 0 : "mismatch after insertion";
        llist.next();
        assert llist.currPos() == 1 : "mismatch after moving next";
        llist.prev();
        assert llist.currPos() == 0 : "mismatch after moving back";
        llist.moveToStart();
        llist.remove();
        llist.moveToEnd();
        llist.append(10);
        llist.moveToPos(5);
        llist.insert(5);

        llist.moveToStart();
        for (int i = 0; i < 10; i++) {
            assert llist.getValue() == i : "Mismatch at index " + i;
            llist.next();
        }

        System.out.println("All correct values after lots of insertion/removal operations");

    }
}
