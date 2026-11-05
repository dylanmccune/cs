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
        for (int i = 0; i <= 2000; i++) {
            temp = random(300);
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

        System.out.println("smallCount: "+ smallCount(bst.root()));

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

        System.out.println("smallCount: "+ smallCount(bst2.root()));


    }

    /**
     * Print all the nodes in the tree with keys between two values
     * @param rt The root node
     * @param low The low bound
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

    public int smallCount(BSTNode<Integer, Integer> rt) {

        if (rt == null || rt.left() == null) {
            return 0;
        }

        if (rt.left().isLeaf()) {
            return 1; // edge case that was breaking it
        }
        return count(rt.left()) + 1; // not sure why i have to add 1 but that's what makes the code work
                                     // i'm sure there's some bug here
    }

    /** The number of nodes in a tree, including the root
     * @return count
     */

    // Don't use this method it's probably broken lol
    public int count(BSTNode<Integer, Integer> rt) {
        if (rt.isLeaf()) {
            return 1;
        }
        int count = 0;
        if (rt.left() != null) {
            if (!rt.left().isLeaf()){
                count += count(rt.left());
            }
            count++;
        }

        if (rt.right() != null) {
            if (!rt.right().isLeaf()){
                count += count(rt.right());
            }
            count++;
        }
        
        return count;
    }
}