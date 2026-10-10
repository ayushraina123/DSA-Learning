package com.dsa.phase3.stack;

import java.util.Stack;

public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        int maxx = 0;
        Stack<KeyValue> stack = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            /*
             * The stack stores (height, startIndex) pairs.
             * For each pair, startIndex is the leftmost index where that
             * height can still form a rectangle.
             *
             * Heights in the stack are in non-decreasing order. When the
             * current height is smaller, taller bars can no longer extend
             * past index i, so we calculate their areas and remove them.
             */
            int start = i;

            while (!stack.isEmpty() && stack.peek().key > heights[i]) {
                KeyValue popped = stack.pop();

                /*
                 * The popped bar extends from popped.value through i - 1.
                 * Its width is therefore i - popped.value.
                 */
                maxx = Math.max(maxx, popped.key * (i - popped.value));

                /*
                 * The current, shorter bar can extend left across the
                 * popped bar's range. Carry forward its earliest start.
                 */
                start = popped.value;
            }

            /*
             * Store the current height with the earliest index from which
             * it can extend. The stack remains in non-decreasing height order.
             */
            stack.push(new KeyValue(heights[i], start));
        }

        /*
         * Any bars still on the stack were never followed by a shorter bar.
         * They can extend to the end of the histogram.
         */
        while (!stack.isEmpty()) {
            KeyValue current = stack.peek();

            /*
             * The width reaches from current.value through the last index:
             * heights.length - current.value.
             */
            maxx = Math.max(
                    maxx,
                    current.key * (heights.length - current.value)
            );
            stack.pop();
        }

        return maxx;
    }

    static class KeyValue {
        int key;
        int value;

        public KeyValue(int key, int value) {
            this.key = key;     // Histogram bar height
            this.value = value; // Leftmost index where this height can extend
        }
    }
}