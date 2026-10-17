package com.dsa.phase3.queue;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {

    private final Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        int size = queue.size();

        queue.add(x);

        for (int i = 0; i < size; i++) {
            queue.add(queue.poll());
        }
    }

    public int pop() {
        return queue.remove();
    }

    public int top() {
        return queue.element();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
