import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
    https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
*/
public class MaxLengthOfStringWithUniqueChars {
    
    public void Test()
    {
        List<String> testStrings = new ArrayList<String>();
        testStrings.addAll(Arrays.asList(new String[] {"un","iq","ue"}));

        List<String> testStrings1 = new ArrayList<String>();
        testStrings1.addAll(Arrays.asList(new String[] {"cha","r","act","ers"}));

        List<String> testStrings2 = new ArrayList<String>();
        testStrings2.addAll(Arrays.asList(new String[] {"abcdefghijklmnopqrstuvwxyz"}));


        System.out.println(Solve(testStrings));
        System.out.println(Solve(testStrings1));
        System.out.println(Solve(testStrings2));
    }

    HashMap<String, boolean[]> stringToCharCheck;

    /*
        Looking back at my solution, you can probably do without the boolean arrays.

        Time Complexity: O(n) + O(!n), or ~O(!n), where n refers to the number of strings in arr.
        O(n) is spent building the hash map, while O(!n) refers to the time complexity of using the
        DFSHelper function.

        Space Complexity: O(n.26) + O(n), or ~O(n), where n refers to the number of strings in arr.
        The O(n.26) refers to storing n string keys with 26 values in hash map, while the O(n) refers to
        the recursion stack height.
    */
    public int Solve(List<String> arr)
    {
        // For each of the strings, we create a boolean array that indicates what chars are in the strings
        stringToCharCheck = new HashMap<>();
        for (String string : arr)
        {
            if (stringToCharCheck.containsKey(string))
                continue;

            boolean[] charCheckArr = new boolean[26];
            boolean isStringValid = true;

            // ... Update boolean array to indicate all included chars
            for (char stringChar : string.toCharArray())
            {
                if (!charCheckArr[stringChar - 'a'])
                    charCheckArr[stringChar - 'a'] = true;
                else
                {
                    isStringValid = false;
                    break;
                }
            }

            // Only add string to the map if the string has only unique chars/valid
            if (isStringValid)
                stringToCharCheck.put(string, charCheckArr);
        }

        return DFSHelper(arr, 0, 0, "", new boolean[26]);
    }

    /*
        Time Complexity: O(!n), where n refers to the number of strings in arr.

        Space Complexity: O(26) + O(n), or ~O(n), where n refers to the number of strings in arr.
        This occurs due to the recursion stack heap size.
    */
    public int DFSHelper(List<String> arr, int currIndex, int maxLength, String currString, boolean[] currCharCheck)
    {
        for (int arrIndex = currIndex; arrIndex < arr.size(); arrIndex++)
        {
            String arrString = arr.get(arrIndex);

            // Only bother processing if the arrString has unique chars
            if (stringToCharCheck.containsKey(arrString))
            {
                // Only bother processing if there are no conflicts between currString and arrString
                if (!AreConflictsWithStrings(currCharCheck, stringToCharCheck.get(arrString)))
                {
                    // Calculate new result + it's boolean array + update max length
                    String newResult = currString + arrString;
                    boolean[] newResultCharCheck = UpdateCharCheck(currCharCheck, stringToCharCheck.get(arrString));
                    maxLength = Math.max(maxLength, newResult.length());

                    // ... Then continue search deeper with the new result
                    maxLength = Math.max(maxLength, DFSHelper(arr, arrIndex + 1, maxLength, newResult, newResultCharCheck));
                }
            }  
        }

        return maxLength;
    }

    /*
        We compare boolean arrays to see if there is a conflict between the strings.

        Time Complexity: O(26), or O(1).

        Space Complexity: O(1).
    */
    public boolean AreConflictsWithStrings(boolean[] stringACharCheck, boolean[] stringBCharCheck)
    {
        for (int charCheckIndex = 0; charCheckIndex < 26; charCheckIndex++)
            if (stringACharCheck[charCheckIndex] && stringBCharCheck[charCheckIndex])
                return true;
        return false;
    }

    /*
        We create a new boolean array to reflect the boolean array of the newly combined string.

        Time Complexity: O(26), or O(1).

        Space Complexity: O(26), or O(1).
    */
    public boolean[] UpdateCharCheck(boolean[] stringACharCheck, boolean[] stringBCharCheck)
    {
        boolean[] newStringACharCheck = new boolean[26];
        for (int charCheckIndex = 0; charCheckIndex < 26; charCheckIndex++)
            newStringACharCheck[charCheckIndex] = stringACharCheck[charCheckIndex] || stringBCharCheck[charCheckIndex];
        return newStringACharCheck;
    }
}
