package test;

import tree.BinarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();

        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            binarySearchTree.add(random.nextInt(10000));
        }
        List<Integer> list = new ArrayList<>();
        while (!binarySearchTree.isEmpty()) {
            list.add(binarySearchTree.removeMin());
        }
        System.out.println(list);
    }

}
