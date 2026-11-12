package project3;

import java.io.*;
import java.util.*;
import java.math.*;

public class TestHarness {
    static private Random rndvalue = new Random(); // Hold the Random class object

    static int random(int n) {
        return Math.abs(rndvalue.nextInt()) % n;
    }

    public void main(String[] args) {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        int temp;

        for (Integer i = 0; i < 10; i++) {
            temp = random(100);
            bst.insert(temp, temp);
        }

        try {
            BSTImage.drawTree(bst.root(), "bst.png");
        } catch (IOException e) {
            System.out.println("Oopsie!");
        }
        System.out.println("Tree: " + bst);

        System.out.print("printHighToLow: ");
        bst.printHighToLow();
        System.out.println();

        System.out.print("printRange: ");
        printRange(bst.root(), 5, 15);
        System.out.println();

        System.out.println("sumNodes: " + sumNodes(bst.root()));

        System.out.println("smallCount: " + smallCount(bst.root(), 100));
        System.out.println("Count: " + bst.countNodes());

        System.out.println("evenNodes: " + evenNodes(bst.root()));
        System.out.println("countLeaves: " + bst.countLeaves());
        System.out.println("countParents: " + bst.countParents());

        assert bst.countLeaves() + bst.countParents() == bst.countNodes() : "Leaves + parents should equal the # of nodes";

        BST<Integer, Integer> bst2 = new BST<Integer, Integer>();
        System.out.println("Empty tree");
        System.out.println("Tree: " + bst2);

        System.out.print("printHighToLow: ");
        bst2.printHighToLow();
        System.out.println();

        System.out.print("printRange: ");
        printRange(bst2.root(), 5, 15);
        System.out.println();

        System.out.println("sumNodes: " + sumNodes(bst2.root()));

        System.out.println("smallCount: " + smallCount(bst2.root(), 10));

    }

    /**
     * Print all the nodes in the tree with keys between two values
     * 
     * @param rt   The root node
     * @param low  The low bound
     * @param high The high bound
     * @return The string of the elements in the range
     */

    public static void printRange(BSTNode<Integer, Integer> rt, int low, int high) {
        String output = new String();
        output += printRangeHelp(rt, low, high);
        System.out.println(output);
    }

    public static String printRangeHelp(BSTNode<Integer, Integer> rt, int low, int high) {
        String output = new String();

        if (rt == null) {
            return output;
        }

        if (rt.isLeaf()) {
            if (rt.key() >= low && rt.key() <= high) {
                output += rt.element() + " ";
                return output;
            } else {
                return output;
            }
        }

        if (rt.key() >= low && rt.key() <= high) {
            output += rt.element() + " ";
            output += printRangeHelp(rt.left(), low, high);
            output += printRangeHelp(rt.right(), low, high);
        } else if (rt.key() < low) {
            output += printRangeHelp(rt.right(), low, high);
        } else if (rt.key() > high) {
            output += printRangeHelp(rt.left(), low, high);
        }

        return output;
    }

    public int sumNodes(BSTNode<Integer, Integer> rt) {
        return sumNodesHelp(rt);
    }

    int out = 0;

    public int sumNodesHelp(BSTNode<Integer, Integer> rt) {
        if (rt == null) {
            return 0;
        }

        sumNodesHelp(rt.left());
        sumVisit(rt.element());
        sumNodesHelp(rt.right());
        return out;

    }
    public void sumVisit(Integer element) {
        out += element;
    }

    public int smallCount(BSTNode<Integer, Integer> rt, int K) {
        int count = 0;
        if (rt == null) {
            return 0;
        }
        if (rt.key() <= K) {
            count += 1;
            count += smallCount(rt.left(), K);
            count += smallCount(rt.right(), K);
        } else {
            return smallCount(rt.left(), K);
        }
        return count;
    }

    public int evenNodes(BSTNode<Integer, Integer> rt) {
        int count = 0;

        if (rt == null) {
            return 0;
        }

        if (rt.key() % 2 == 0) {
            count += 1;
        }

        count += evenNodes(rt.left());
        count += evenNodes(rt.right());

        return count;
    }
}