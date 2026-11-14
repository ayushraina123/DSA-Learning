package com.dsa.phase4.binaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<Interval> intervalList = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (end < intervals[i][0]) {
                intervalList.add(new Interval(start, end));
                start = intervals[i][0];
            }
            end = Math.max(end, intervals[i][1]);
        }

        intervalList.add(new Interval(start, end));
        int[][] ans = new int[intervalList.size()][2];

        for (int i = 0; i < intervalList.size(); i++) {
            ans[i][0] = intervalList.get(i).start;
            ans[i][1] = intervalList.get(i).end;
        }

        return ans;
    }

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
