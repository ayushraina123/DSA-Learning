package com.dsa.phase1.hashing;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    public boolean isAnagram(String s, String t) {

        // Strings with different lengths can never be anagrams.
        if (s.length() != t.length()) {
            return false;
        }

        // Stores the net frequency of each character.
        //
        // Characters from 's' increment the count.
        // Characters from 't' decrement the count.
        //
        // If both strings are anagrams, every increment
        // will eventually be cancelled by a corresponding decrement,
        // leaving every character with a frequency of zero.
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {

            // Increase the frequency of the current character from 's'.
            frequencyMap.put(
                    s.charAt(i),
                    frequencyMap.getOrDefault(s.charAt(i), 0) + 1
            );

            // Decrease the frequency of the current character from 't'.
            frequencyMap.put(
                    t.charAt(i),
                    frequencyMap.getOrDefault(t.charAt(i), 0) - 1
            );
        }

        // Every character should have a net frequency of zero.
        // A non-zero value indicates that one string contains
        // more occurrences of that character than the other.
        for (int frequency : frequencyMap.values()) {
            if (frequency != 0) {
                return false;
            }
        }

        // All character frequencies matched.
        return true;
    }
}