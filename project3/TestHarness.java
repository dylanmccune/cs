package project3;

import java.io.*;
import java.util.*;
import java.math.*;

public class TestHarness {
    static private Random rndvalue = new Random(); // Hold the Random class object

    static int random(int n) {
        return Math.abs(rndvalue.nextInt()) % n;
    }

    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        int temp;
        for (int i = 1; i <= 30; i++) {
            temp = random(300);
            bst.insert(i, temp);
        }
        System.out.println("Tree: " + bst);

        System.out.print("printHighToLow: ");
        bst.printHighToLow();
        System.out.println();

        System.out.print("printRange: ");
        printRange(bst.root(), 5, 15);
        System.out.println();
    }

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
}
