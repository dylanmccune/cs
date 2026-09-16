package project1_DMcCune;

public class TestHarness {
    public static void main(String[] args) {
        List<Integer> list = new AList<Integer>(5);
        List<Integer> emptyList = new AList<Integer>(1);
        List<Integer> smallList = new AList<Integer>(1);

        list.append(4);
        list.append(5);
        System.out.print("Current list value at index: ");
        System.out.println(list.currPos());
        System.out.println(list.getValue());

        list.moveToEnd();
        System.out.println("Value at end of list: ");
        System.out.println(list.getValue());

        // Test insertion
        list.moveToStart();
        list.insert(0);
        list.moveToEnd();
        list.insert(0);
        list.moveToPos(1);
        list.insert(0);
        try {
            list.insert(0);
        } catch (AssertionError e) {
            if (e.getMessage().contains("capacity exceeded")) {
                System.out.println("Catches error when exceeding list capacity");
            } else {
                System.out.println("Wrong error when inserting into full list");
            }
        } catch (Exception e) {
            System.out.println("Wrong error when inserting into full list");
        }

        list.remove();
        list.getValue();

        emptyList.remove();
        emptyList.insert(0);
        emptyList.clear();

        smallList.append(0);

        smallList.remove();

        smallList.insert(0);

        System.out.println("Testing LLists...");

        LList<Integer> llist = new LList<Integer>();

        llist.append(1);
        llist.append(2);
        llist.append(3);
        llist.append(4);
        System.out.println(llist.getValue());
        llist.moveToEnd();
        System.out.println(llist.getValue());
        llist.remove();
        System.out.println(llist.getValue());
        System.out.println(llist.currPos());

        List<Integer> smallLList = new LList<Integer>();
        smallLList.append(0);
        smallLList.remove();
        System.out.println(smallLList.getValue());
        smallLList.append(0);

        List<Integer> emptyLList = new LList<Integer>();
        emptyLList.remove();

        // Test getting the current position

        llist.clear();
        for (int i = 0; i < 10; i++) {
            llist.append(i);
        }
        llist.moveToStart();
        for (int i = 0; i < 10; i++) {
            llist.moveToPos(i);
            assert llist.currPos() == i : "mismatch at index " + i;
        }
        llist.moveToPos(5);
        llist.remove();
        assert llist.currPos() == 4 : "mismatch after removal";
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

    }
}
