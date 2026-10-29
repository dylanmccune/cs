/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        for (int i=1; i<=30; i++) {
            temp = random(300);
            bst.insert(temp, temp);
        }
        System.out.println("Tree: " + bst);
        
        System.out.print("printHighToLow: ");
        bst.printHighToLow();
        System.out.println();
        
        System.out.print("printRange: ");
        printRange(bst.root(), 100, 150);
        System.out.println();
    }

    public static void printRange(BSTNode<Integer, Integer> rt, int low, int high) {
        // YOUR CODE HERE
    }
}
