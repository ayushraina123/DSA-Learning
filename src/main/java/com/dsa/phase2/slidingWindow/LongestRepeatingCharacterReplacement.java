package com.dsa.phase2.slidingWindow;

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int max = 0;
        int freq = 0;
        int left = 0;
        int right = 0;
        int last = 0;
        int count = k;

        while (right < s.length()) {
            if (s.charAt(right) == s.charAt(left)) {
                freq++;
                right++;
                last = right + 1;
            } else {
                if (count > 0) {
                    freq++;
                    right++;
                    count--;
                } else {
                    count = k;
                    left = last;
                    right = last;
                    freq = 0;
                }
            }
            max = Math.max(max, freq);
        }

        return max;
    }
}
