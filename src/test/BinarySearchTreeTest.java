package test;

import tree.AVLTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        AVLTree<Integer,Integer> binarySearchTree = new AVLTree<>();

        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int key = random.nextInt(10000);
            int value = random.nextInt(10000);
            binarySearchTree.add(key,value);
        }
        List<Integer> list = new ArrayList<>();
        while (!binarySearchTree.isEmpty()) {
            list.add(binarySearchTree.removeMin());
        }
        System.out.println(list);
    }

}
