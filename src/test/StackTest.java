package test;

import stack.Stack;
import stack.impl.ArrayStack;
import stack.impl.LinkedStack;

public class StackTest {

    public static void main(String[] args) {
//        array();

        linked();
    }

    private static void linked() {
        Stack<Integer> stack = new LinkedStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }


    private static void array() {
        Stack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }
}
