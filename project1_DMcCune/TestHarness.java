package project1_DMcCune;

public class TestHarness {
    public static void main(String[] args) {
        List<Integer> list = new AList<Integer>(5);
        List<Integer> emptyList = new AList<Integer>(0);
        List<Integer> smallList = new AList<Integer>(1);


        list.append(4);
        list.append(5);
        System.out.print("Current list value at index: ");
        System.out.println(list.currPos());
        System.out.println(list.getValue());

        list.moveToEnd();
        System.out.println("Value at end of list: ");
        System.out.println(list.getValue());

        list.remove();
        list.getValue();

        // Removing from an empty list
        emptyList.remove();

        smallList.append(0);
        
        smallList.remove();


        System.out.println("Testing LLists...");

        List<Integer> llist = new LList<Integer>();

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

    }
}
