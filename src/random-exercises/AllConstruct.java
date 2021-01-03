import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
    Question:

    You are given a target word, and an array of words.
    Return all possible combinations that make up the target word.
    Words from the array can be used multiple times.
*/
public class AllConstruct {

    public void Test() {

        // System.out.println(BruteForce("purple", new String[] { "purp", "p", "ur",
        // "le", "purpl" }));
        // System.out.println(BruteForce("abcdef", new String[] { "ab", "abc", "cd",
        // "def", "abcd", "ef", "c" }));
        // System.out.println(BruteForce("skateboard", new String[] { "bo", "rd", "ate",
        // "t", "ska", "sk", "boar" }));
        // System.out.println(BruteForce("aaaaaaaaaaaaaaaaaaaaaaaz", new String[] { "a",
        // "aa", "aaa", "aaaa", "aaaaa" }));

        System.out.println(PerformDP("purple", new String[] { "purp", "p", "ur", "le", "purpl" }));
        System.out.println(PerformDP("abcdef", new String[] { "ab", "abc", "cd", "def", "abcd", "ef", "c" }));
        System.out.println(PerformDP("skateboard", new String[] { "bo", "rd", "ate", "t", "ska", "sk", "boar" }));
        System.out.println(PerformDP("aaaaaaaaaaaaaaaaaaaaaaaz", new String[] { "a", "aa", "aaa", "aaaa", "aaaaa" }));

        System.out.println("");

        System.out.println(DPTabulation("purple", new String[] { "purp", "p", "ur", "le", "purpl" }));
        System.out.println(DPTabulation("abcdef", new String[] { "ab", "abc", "cd", "def", "abcd", "ef", "c" }));
        System.out.println(DPTabulation("skateboard", new String[] { "bo", "rd", "ate", "t", "ska", "sk", "boar" }));
        System.out
                .println(DPTabulation("aaaaaaaaaaaaaaaaaaaaaaaz", new String[] { "a", "aa", "aaa", "aaaa", "aaaaa" }));
    }

    public List<List<String>> DPTabulation(String targetWord, String[] wordBank) {

        // Initialisation, adding blank lists up to length of target word + 1
        List<List<List<String>>> results = new ArrayList<List<List<String>>>();
        for (int x = 0; x <= targetWord.length(); x++)
            results.add(new ArrayList<List<String>>());
        results.get(0).add(new ArrayList<String>());

        // Initial adding of words at x = 0 (any prefixes that match target word)
        for (String word : wordBank) {
            if (word.length() <= targetWord.length()) {
                String targetSub = targetWord.substring(0, word.length());
                if (targetSub.equals(word)) {
                    ArrayList<String> newCombination = new ArrayList<>();
                    newCombination.add(word);
                    results.get(word.length()).add(newCombination);
                }
            }
        }

        // Now checking from x = 1 for matching prefixes to match target word
        for (int x = 1; x <= targetWord.length(); x++) {

            // Ignore lists without any matching words (invalid)
            if (results.get(x).size() != 0) {
                for (String word : wordBank) {
                    if (x + word.length() <= targetWord.length()) {
                        String targetSub = targetWord.substring(x, x + word.length());
                        if (targetSub.equals(word)) {

                            // Add blank list with word as a combination
                            if (results.get(x).size() == 0) {
                                ArrayList<String> newCombination = new ArrayList<>();
                                newCombination.add(word);
                                results.get(x + word.length()).add(newCombination);
                            }
                            // ... else add word + each of existing combinations
                            else {
                                for (List<String> combination : results.get(x)) {
                                    ArrayList<String> newCombination = new ArrayList<>();
                                    newCombination.addAll(combination);
                                    newCombination.add(word);
                                    results.get(x + word.length()).add(newCombination);
                                }
                            }
                        }
                    }
                }
            }
        }

        return results.get(targetWord.length());
    }

    public List<List<String>> PerformDP(String targetWord, String[] wordBank) {
        HashMap<String, ArrayList<List<String>>> helperMap = new HashMap<>();
        return DP(targetWord, wordBank, helperMap);
    }

    /*
     * Time Complexity: ~O(m^n), where m refers to size of word bank and n refers to
     * the target word size. Still exponential, since need to return O(m^n)
     * combinations.
     * 
     * Other important time complexities:
     * 
     * -> O(n), usage of substring method during each recursion call
     * 
     * -> O(n), adding prefix to each suffix combination per recursion call
     * 
     * Space Complexity: O(n).O(m^n) + O(n.m^n), where n refers to the target word
     * size (tree height).
     * 
     * -> O(n), refers to max recursion stack height
     * 
     * -> O(m^n), worst case, refers to storage of target word combinations per
     * recursion call
     * 
     * -> O(n.m^n), storage of hash map keys + target word combinations
     * 
     * Important: This problem does not benefit much from using DP, since the
     * problem can return m^n combinations worst case (same as brute force).
     */
    public List<List<String>> DP(String targetWord, String[] wordBank,
            HashMap<String, ArrayList<List<String>>> memoMap) {

        if (memoMap.containsKey(targetWord))
            return memoMap.get(targetWord);
        if (targetWord == "")
            return new ArrayList<List<String>>();

        ArrayList<List<String>> result = new ArrayList<List<String>>();

        for (String prefix : wordBank) {

            if (targetWord.indexOf(prefix) == 0) {

                List<List<String>> suffixCombinations = DP(targetWord.substring(prefix.length()), wordBank, memoMap);

                if (suffixCombinations != null) {
                    if (suffixCombinations.size() == 0) {
                        ArrayList<String> onlyPrefixCombination = new ArrayList<>();
                        onlyPrefixCombination.add(prefix);
                        result.add(onlyPrefixCombination);
                    } else {

                        for (List<String> aSuffixCombination : suffixCombinations) {
                            ArrayList<String> newCombination = new ArrayList<>();
                            newCombination.addAll(aSuffixCombination);
                            newCombination.add(0, prefix);
                            result.add(newCombination);
                        }
                    }
                }
            }
        }

        if (result.size() == 0)
            memoMap.put(targetWord, null);
        else
            memoMap.put(targetWord, result);
        return memoMap.get(targetWord);
    }

    /*
     * Time Complexity: ~O(m^n), where m refers to size of word bank and n refers to
     * the target word size. Exponential time, so just mentioning general case.
     * 
     * Note additional computation:
     * 
     * -> O(n), from substring method usage during each recursion call
     * 
     * -> O(n), adding prefix to each suffix combination per recursion call
     * 
     * Space Complexity: O(n).O(m^n), where n refers to the target word size (tree
     * height and max recursion stack height). Each recursion call is also required,
     * at worst case, to store O(m^n) combinations.
     */
    public List<List<String>> BruteForce(String targetWord, String[] wordBank) {

        if (targetWord == "")
            return new ArrayList<List<String>>();

        List<List<String>> result = new ArrayList<List<String>>();

        for (String prefix : wordBank) {

            if (targetWord.indexOf(prefix) == 0) {

                List<List<String>> suffixCombinations = BruteForce(targetWord.substring(prefix.length()), wordBank);

                if (suffixCombinations != null) {
                    if (suffixCombinations.size() == 0) {
                        ArrayList<String> onlyPrefixCombination = new ArrayList<>();
                        onlyPrefixCombination.add(prefix);
                        result.add(onlyPrefixCombination);
                    } else {
                        for (List<String> combination : suffixCombinations)
                            combination.add(0, prefix);
                        result.addAll(suffixCombinations);
                    }
                }
            }
        }

        if (result.size() == 0)
            return null;
        return result;
    }
}
