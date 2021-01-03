import java.util.HashMap;

/*
    Question:

    You are given a target word, and an array of words.
    Return the number of ways that the target word can be
    constructed using the provided array of words.
    Words in the array can be used multiple times.
*/
public class CountConstruct {
    public void Test() {

        System.out.println(PerformDP("purple", new String[] { "purp", "p", "ur", "le", "purpl" }));
        System.out.println(PerformDP("abcdef", new String[] { "ab", "abc", "cd", "def", "abcd" }));
        System.out.println(PerformDP("skateboard", new String[] { "bo", "rd", "ate", "t", "ska", "sk", "boar" }));
        System.out.println(PerformDP("enterapotentpot", new String[] { "a", "p", "ent", "enter", "ot", "o", "t" }));
        System.out.println(PerformDP("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                new String[] { "e", "ee", "eee", "eeee", "eeeee", "eeeeee" }));

        System.out.println("");

        System.out.println(DPTabulation("purple", new String[] { "purp", "p", "ur", "le", "purpl" }));
        System.out.println(DPTabulation("abcdef", new String[] { "ab", "abc", "cd", "def", "abcd" }));
        System.out.println(DPTabulation("skateboard", new String[] { "bo", "rd", "ate", "t", "ska", "sk", "boar" }));
        System.out.println(DPTabulation("enterapotentpot", new String[] { "a", "p", "ent", "enter", "ot", "o", "t" }));
        System.out.println(DPTabulation("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
                new String[] { "e", "ee", "eee", "eeee", "eeeee", "eeeeee" }));
    }

    /*
     * Time Complexity: O(n.m.m), where n refers to size of word bank and m refers
     * to size of the target word.
     * 
     * Space Complexity: O(m), results array.
     */
    public int DPTabulation(String targetWord, String[] wordBank) {
        int[] resultsArray = new int[targetWord.length() + 1];
        resultsArray[0] = 1;

        for (int x = 0; x <= targetWord.length(); x++) {
            if (resultsArray[x] > 0) {
                for (String word : wordBank) {
                    if (x + word.length() <= targetWord.length()) {
                        if (targetWord.substring(x, x + word.length()).equals(word)) {
                            resultsArray[x + word.length()] += resultsArray[x];
                        }
                    }
                }
            }
        }

        return resultsArray[targetWord.length()];
    }

    public int PerformDP(String targetWord, String[] wordBank) {
        HashMap<String, Integer> helperMemoMap = new HashMap<>();
        return DP(targetWord, wordBank, helperMemoMap);
    }

    /*
     * Time Complexity: O(m.n).O(m), where m refers to the size of the word bank,
     * and n refers to the size of the target word.
     * 
     * Space Complexity: O(m.m) + O(m), where m refers to the size of the word bank.
     * O(m.m) due to max recursion stack length + managing substring. O(m) from
     * storing target word keys with valid combination counters.
     */
    public int DP(String targetWord, String[] wordBank, HashMap<String, Integer> memoMap) {

        if (memoMap.containsKey(targetWord))
            return memoMap.get(targetWord);
        if (targetWord == "")
            return 1;

        int validCounter = 0;

        for (String prefix : wordBank)
            if (targetWord.indexOf(prefix) == 0)
                validCounter += DP(targetWord.substring(prefix.length()), wordBank, memoMap);

        memoMap.put(targetWord, validCounter);
        return memoMap.get(targetWord);
    }

    /*
     * Time Complexity: O(m^n).O(m), where m refers to the word bank size and the n
     * refers to the target word size. The O(m) refers to the usage of substring
     * method during each recursion call.
     * 
     * Space Complexity: O(n.n), where m refers to the target word size. n
     * substrings are maintained during n recursion calls.
     */
    public int BruteForce(String targetWord, String[] wordBank) {

        if (targetWord == "")
            return 1;

        int validCounter = 0;

        for (String prefix : wordBank)
            if (targetWord.indexOf(prefix) == 0)
                validCounter += BruteForce(targetWord.substring(prefix.length()), wordBank);

        return validCounter;
    }
}
