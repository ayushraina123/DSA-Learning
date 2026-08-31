package com.dsa.phase2.slidingWindow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();

        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(nums[0]);
        int left = 0;
        int right = 0;

        while (right < nums.length) {
            System.out.println(deque);
            if (nums[right] > deque.peek()) {
                deque.poll();
                deque.add(nums[right]);
            }

            if (right - left + 1 == k) {
                list.add(deque.peek());
                if (nums[left] == deque.peek()) {
                    deque.poll();
                }
                left++;
            }
            right++;
        }

        return list.stream().mapToInt(i -> i).toArray();
    }
}
