import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/*
    https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
*/
public class MinDeletionsToGoodString {
    
    public void Test()
    {
        // System.out.println(SolveSortMethod("bbcebab"));
        System.out.println(SolveHashMapMethod("bbcebab"));
        // System.out.println();
        // System.out.println(SolveSortMethod("aab"));
        // System.out.println(SolveHashMapMethod("aab"));
        // System.out.println();
        // System.out.println(SolveSortMethod("aaabbbcc"));
        // System.out.println(SolveHashMapMethod("aaabbbcc"));
    }

    /*
        Time Complexity: O(n log n) + O(n), where n refers to size of the string

        Space Complexity: O(n), where n refers to size of the string. Generally
        small in the average case though.
    */
    public int SolveSortMethod(String s)
    {
        // Edge case - if length of 1, return 0
        if (s.length() == 1)
            return 0;
    
        // Convert to char array + sort it
        char[] sCharArray = s.toCharArray();
        Arrays.sort(sCharArray);
        
        // Set to maintain counts (for chars, but not explicitly)
        HashSet<Integer> helperSet = new HashSet<Integer>();
    
        char prevChar = sCharArray[0];
        int deletionCount = 0;
        int charCount = 1;

        // Iterate through chars of the string
        for (int x = 1; x < s.length(); x++)
        {
            char nextChar = sCharArray[x];

            // If same, then increment counter
            if (prevChar == nextChar)
                charCount += 1;
            else
            {
                // Increment deletion counter as long as the char count exists in map
                while (helperSet.contains(charCount))
                {
                    charCount -= 1;
                    deletionCount += 1;
                }
                
                // Only add to set if char count greater than 0
                if (charCount > 0)
                    helperSet.add(charCount);
                
                // Update char + reset counter
                prevChar = nextChar;
                charCount = 1;
            }
        }
        
        // For final char - increment deletion counter as long as char count exists in map (and greater than 0)
        while (helperSet.contains(charCount) && charCount > 0)
        {
            charCount -= 1;
            deletionCount += 1;
        }
        
        return deletionCount;
    }

    /*
        This solution avoids sorting and therefore should theoretically be faster
        than the sorting method. Funny enough, leetcode recorded a slower runtime.

        Time Complexity: O(n) + O(n), where n refers to size of the string.

        Space Complexity: O(n) + O(n) + O(1), where n refers to size of the string.
    */
    public int SolveHashMapMethod(String s)
    {
        // Base case
        if (s.length() == 1)
            return 0;

        // Track counts of all letters in the string
        char[] sCharArray = s.toCharArray();
        HashMap<Character, Integer> helperMap = new HashMap<Character, Integer>();
        for (int x = 0; x < s.length(); x++)
        {
            helperMap.putIfAbsent(sCharArray[x], 0);
            helperMap.put(sCharArray[x], helperMap.get(sCharArray[x]) + 1);
        }

        // Set to track unique counts we have dealt with before
        HashSet<Integer> helperSet = new HashSet<Integer>();

        // Char map used to track what letters we have dealt with before
        boolean[] charMap = new boolean[26];

        int deletionCounter = 0;
        for (int x = 0; x < s.length(); x++)
        {
            // Continue to next iteration if dealt with character before
            if (charMap[sCharArray[x] - 'a'])
                continue;
            else
                charMap[sCharArray[x] - 'a'] = true;

            int charCount = helperMap.get(sCharArray[x]);
            if (!helperSet.contains(charCount))
                helperSet.add(charCount);
            else
            {
                // Increment deletion counter until char count is unique & greater than zero
                while (helperSet.contains(charCount) && charCount > 0)
                {
                    charCount -= 1;
                    deletionCounter += 1;
                }

                // Add to set if greater than zero
                if (charCount > 0)
                    helperSet.add(charCount);
            }
        }

        // Return result
        return deletionCounter;
    }
}
