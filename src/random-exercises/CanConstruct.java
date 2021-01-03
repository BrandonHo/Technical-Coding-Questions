import java.util.Arrays;
import java.util.HashMap;

/*
    Question:

    You are given a target word, and an array of words.
    Return whether you can construct the target word using the provided array of words.
    Words in the array can be used multiple times.
*/
public class CanConstruct {

    public void Test() {

        // System.out.println(BruteForce("abcdef", new String[] { "ab", "cd", "ef" }));
        // System.out.println(BruteForce("abcdef", new String[] { "ab", "abc", "cd",
        // "def", "abcd" }));
        // System.out.println(BruteForce("skateboard", new String[] { "bo", "rd", "ate",
        // "t", "ska", "sk", "boar" }));
        // System.out.println(BruteForce("enterapotentpot", new String[] { "a", "p",
        // "ent", "enter", "ot", "o", "t" }));
        // System.out.println(BruteForce("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
        // new String[] { "e", "ee", "eee", "eeee", "eeeee", "eeeeee" }));

        System.out.println(PerformDP("abcdef", new String[] { "ab", "cd", "ef" }));
        System.out.println(PerformDP("abcdef", new String[] { "ab", "abc", "cd", "def", "abcd" }));
        System.out.println(PerformDP("skateboard", new String[] { "bo", "rd", "ate", "t", "ska", "sk", "boar" }));
        System.out.println(PerformDP("enterapotentpot", new String[] { "a", "p", "ent", "enter", "ot", "o", "t" }));
        System.out.println(PerformDP("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                new String[] { "e", "ee", "eee", "eeee", "eeeee", "eeeeee" }));

        System.out.println("");

        System.out.println(DPTabulation("abcdef", new String[] { "ab", "cd", "ef" }));
        System.out.println(DPTabulation("abcdef", new String[] { "ab", "abc", "cd", "def", "abcd" }));
        System.out.println(DPTabulation("skateboard", new String[] { "bo", "rd", "ate", "t", "ska", "sk", "boar" }));
        System.out.println(DPTabulation("enterapotentpot", new String[] { "a", "p", "ent", "enter", "ot", "o", "t" }));
        System.out.println(DPTabulation("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                new String[] { "e", "ee", "eee", "eeee", "eeeee", "eeeeee" }));
    }

    /*
     * Time Complexity: O(n.m).O(m), where m refers to the target word size and n
     * refers to the word bank size.
     * 
     * Space Complexity: O(m), which comes from the results array.
     */
    public boolean DPTabulation(String targetWord, String[] wordBank) {
        Boolean[] resultArray = new Boolean[targetWord.length() + 1];
        Arrays.fill(resultArray, false);
        resultArray[0] = true;

        for (int x = 0; x <= targetWord.length(); x++)
            if (resultArray[x])
                for (String word : wordBank)
                    if (x + word.length() <= targetWord.length())
                        if (targetWord.substring(x, x + word.length()).equals(word))
                            resultArray[x + word.length()] = true;

        return resultArray[targetWord.length()];
    }

    public boolean PerformDP(String targetWord, String[] wordBank) {
        HashMap<String, Boolean> helperMap = new HashMap<>();
        return DP(targetWord, wordBank, helperMap);
    }

    /*
     * Time Complexity: O(n.m).O(m), where n refers to size of word bank and m
     * refers to size of target word. No longer looking at duplicate recursion
     * calls. O(m) refers to usage of substring method.
     * 
     * Space Complexity: O(m.m) + O(m), where m refers to size of target word. Same
     * as brute force, however keep in mind the maintainance of the hash map.
     */
    public boolean DP(String targetWord, String[] wordBank, HashMap<String, Boolean> memoMap) {
        if (memoMap.containsKey(targetWord))
            return memoMap.get(targetWord);
        if (targetWord == "")
            return true;

        for (String prefix : wordBank) {
            if (targetWord.indexOf(prefix) == 0) {
                if (DP(targetWord.substring(prefix.length()), wordBank, memoMap)) {
                    memoMap.put(targetWord, true);
                    return true;
                }
            }
        }

        memoMap.put(targetWord, false);
        return memoMap.get(targetWord);
    }

    /*
     * Time Complexity: O(n^m).O(m), where n refers to size of word bank (branching
     * factor) and m refers to the size of the target word. O(m) refers to the usage
     * of the substring method.
     * 
     * Space Complexity: O(m.m), where m refers to the size of the target word
     * (recursion stack max height, tree height). Each recursion call also maintains
     * a substring of max length m.
     */
    public boolean BruteForce(String targetWord, String[] wordBank) {

        if (targetWord == "")
            return true;

        for (String prefix : wordBank)
            if (targetWord.indexOf(prefix) == 0)
                if (BruteForce(targetWord.substring(prefix.length()), wordBank))
                    return true;

        return false;
    }
}
