import java.util.Arrays;
import java.util.HashMap;

/*
    Amazon is looking to develop a new labeling system in the fulfillment centers.
    New labels will be devised from the original string labels.
    Given the original string label, construct a new string by rearranging the original string and deleting characters as needed.
    Return the alphabetically-largest string that can be constructed respecting the limit 
    as to how many consecutive characters can be the same (represented by k).
    "Alphabetically-largest" is defined in reverse alphabetical order (e.g., b is "larger" than a, c is "larger" than b, etc.) 
    from left to right (e.g., "ba" is larger than "ab").
    
    Write an algorithm to return the alphabetically-largest string that can be constructed respecting the above limits.

    Input
    The input to the function/method consists of two arguments:
    originalLabel, a string representing the original string label;
    charlimit, an integer representing the maximum number of identical consecutive characters the new string can have (k).

    Output
    Return a string representing the alphabetically largest string that can be constructed that has no more than k identical consecutive characters.

    Constraints
    1 <= len <= 10^5; where len represents the length of originalLabel
    1 <= charlimit <= 10^3

    Note
    The string originalLabel contains only lowercase English letters.

    Example
    Input:
    originalLabel = baccc
    charLimit = 2

    Output:
    ccbca

    Explanation:
    The largest string, alphabetically, is 'cccba' but it is not allowed because it uses the character's more than 2 times consecutively.
    Therefore, the answer is 'ccbca'.

    NB: I have assumed that if an invalid string is sent (no possible combination), then I return a blank string.
*/
public class LabelingSystem {

    public void Test() {

        System.out.println(HashMapMethod("baccc", 2));
        System.out.println(HashMapMethod("bbddbbbaa", 2));
    }

    /*
     * NB: I did think of a sorting method that does limit checks afterwards, but
     * the hash map method is a better option as it doesn't require sorting and thus
     * avoids the O(n log n) time complexity.
     * 
     * --- Hash Map Method ---
     * 
     * Time Complexity: O(n) + O(n), where n refers to size of label.
     * 
     * - The first O(n) refers to building up the hash map.
     * 
     * - The second O(n) refers to building up the result string using the hash map.
     * 
     * Space Complexity: O(1) + O(n), where n refers to size of label.
     * 
     * - O(1) refers to the hash map. This would usually be O(n), but it has a
     * constant size (lowercase alphabet)
     * 
     * - O(n) refers to the returned result string, which is built up in the method.
     */
    public String HashMapMethod(String label, int charLimit) {

        // Add chars from label to a hash map
        HashMap<Character, Integer> helperMap = new HashMap<Character, Integer>();
        for (int charLabelIndex = 0; charLabelIndex < label.length(); charLabelIndex++) {
            if (!helperMap.containsKey(label.charAt(charLabelIndex)))
                helperMap.put(label.charAt(charLabelIndex), 0);
            helperMap.put(label.charAt(charLabelIndex), helperMap.get(label.charAt(charLabelIndex)) + 1);
        }

        // Build string from back
        char nextChar = 'z';
        String result = "";
        while (result.length() != label.length()) {
            if (helperMap.containsKey(nextChar)) {

                // If within char limit...
                if (helperMap.get(nextChar) <= charLimit) {

                    // Add chars to result, update nextChar
                    char[] addedChars = new char[helperMap.get(nextChar)];
                    Arrays.fill(addedChars, nextChar);
                    result += new String(addedChars);

                    helperMap.put(nextChar, 0);
                    nextChar -= 1;
                }
                // If not within char limit
                else {

                    // Add chars to result (up to char limit), update map appropriately
                    char[] addedChars = new char[charLimit];
                    Arrays.fill(addedChars, nextChar);
                    result += new String(addedChars);
                    helperMap.put(nextChar, helperMap.get(nextChar) - charLimit);

                    // Then add one of the next biggest char to result + update map appropriately
                    char tempNextBiggestChar = nextChar;
                    tempNextBiggestChar -= 1;
                    while (!helperMap.containsKey(tempNextBiggestChar)) {

                        // If not possible - return blank string
                        if (tempNextBiggestChar == '`')
                            return "";

                        tempNextBiggestChar -= 1;
                    }

                    result += tempNextBiggestChar;
                    helperMap.put(tempNextBiggestChar, helperMap.get(tempNextBiggestChar) - 1);

                    // Do not update nextChar - will be handled in next iteration
                }
            } else {
                nextChar -= 1;
            }
        }

        return result;
    }
}
