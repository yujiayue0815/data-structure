package test;

import queue.Queue;
import queue.impl.LinkedQueue;

public class QueueTest {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
