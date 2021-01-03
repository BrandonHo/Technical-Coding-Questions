
/*
    As part of an assignment, a student is required to find whether a given string s is divisible by string t.
    If it is divisible, the student needs to find the length of the smallest string x such that if x is concatenated any number
    of times, we get both s and t. If this is not possible, the student needs to print -1.

    Help find the length of the smallest string x.

    Example:
    s = bcdbcdbcd
    t = bcdbcd

    If string t is concatenated twice, the result is bcdbcdbcdbcd > s. String s is not divisible by string t, so the result is -1.

    Example:
    s = bcdbcdbcdbcd
    t = bcdbcd

    If the string t is concatenated twice, the result is bcdbcdbcdbcd = s. String s is divisible by string t. The smallest string
    that can be concatenated to create both strings s and t is bcd, whose length is 3.

    Constraints:
    1 <= s.length <= 2 x 10^5
    1 <= t.length <= 2 x 10^5
    t.length <= s.length
*/
public class DivisibilityOfStrings {

    public void Test() {

        String s1 = "bcdbcdbcd", t1 = "bcdbcd";
        String s2 = "bcdbcdbcdbcd", t2 = "bcdbcd";
        String s3 = "aaaaaa", t3 = "a";
        String s4 = "wqzpuogsqcxpqizenbrhcbijieufuxgqpfijuobkqacjkdnzggijhqurwqyrnejckrnghqsyswhczwdicltjdndaebrtgcysulydcsbupkzogewkqpwtfzvjameircaloaqstsoiepynuvxmmthrsdcvrhdijgvzgmtzeijkzixtfxhrqpllspijwnsitnjazdwqzpuogsqcxpqizenbrhcbijieufuxgqpfijuobkqacjkdnzggijhqurwqyrnejckrnghqsyswhczwdicltjdndaebrtgcysulydcsbupkzogewkqpwtfzvjameircaloaqstsoiepynuvxmmthrsdcvrhdijgvzgmtzeijkzixtfxhrqpllspijwnsitnjazdwqzpuogsqcxpqizenbrhcbijieufuxgqpfijuobkqacjkdnzggijhqurwqyrnejckrnghqsyswhczwdicltjdndaebrtgcysulydcsbupkzogewkqpwtfzvjameircaloaqstsoiepynuvxmmthrsdcvrhdijgvzgmtzeijkzixtfxhrqpllspijwnsitnjazdwqzpuogsqcxpqizenbrhcbijieufuxgqpfijuobkqacjkdnzggijhqurwqyrnejckrnghqsyswhczwdicltjdndaebrtgcysulydcsbupkzogewkqpwtfzvjameircaloaqstsoiepynuvxmmthrsdcvrhdijgvzgmtzeijkzixtfxhrqpllspijwnsitnjazd";
        String t4 = "wqzpuogsqcxpqizenbrhcbijieufuxgqpfijuobkqacjkdnzggijhqurwqyrnejckrnghqsyswhczwdicltjdndaebrtgcysulydcsbupkzogewkqpwtfzvjameircaloaqstsoiepynuvxmmthrsdcvrhdijgvzgmtzeijkzixtfxhrqpllspijwnsitnjazdwqzpuogsqcxpqizenbrhcbijieufuxgqpfijuobkqacjkdnzggijhqurwqyrnejckrnghqsyswhczwdicltjdndaebrtgcysulydcsbupkzogewkqpwtfzvjameircaloaqstsoiepynuvxmmthrsdcvrhdijgvzgmtzeijkzixtfxhrqpllspijwnsitnjazd";

        System.out.println(Solve(s1, t1));
        System.out.println(Solve(s2, t2));
        System.out.println(Solve(s3, t3));
        System.out.println(Solve(s4, t4));
    }

    /*
     * Time Complexity: O(s) + O(t.[t + t]), where s refers to size of string s and
     * t refers to size of string t.
     * 
     * - O(s) refers to the initial check whether s is divisible by t
     * 
     * - O(t.[t + t]) or ~O(t^2) refers to checkinf for smallest denominator for t.
     * 
     */
    public int Solve(String s, String t) {

        if (s.length() % t.length() != 0)
            return -1;

        // Check if s divisible by t
        for (int sIndex = 0; sIndex < s.length(); sIndex++)
            if (s.charAt(sIndex) != t.charAt(sIndex % t.length()))
                return -1;

        // NB: since s is divisible by t, we need to find smallest denominator for t
        int smallestSize = 1;
        boolean validSmall = true;

        // Worst case, the denominator will just be t itself
        while (smallestSize != t.length()) {

            String potentialSmallDivisor = t.substring(0, smallestSize);

            // Check if the divisor is valid for t
            for (int tIndex = 0; tIndex < t.length(); tIndex++) {

                // If at any point it fails, stop
                if (t.charAt(tIndex) != potentialSmallDivisor.charAt(tIndex % smallestSize)) {
                    validSmall = false;
                    break;
                }
            }

            // If managed to get through without failing -> return smallest size
            if (validSmall)
                return smallestSize;
            // ... Update size + reset boolean for next iteration
            else {
                smallestSize += 1;
                validSmall = true;
            }
        }

        return smallestSize;
    }
}
