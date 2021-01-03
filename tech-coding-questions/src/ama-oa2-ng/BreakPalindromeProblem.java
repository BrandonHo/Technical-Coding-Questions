/*
    https://leetcode.com/problems/break-a-palindrome/
*/
public class BreakPalindromeProblem {

    public void Test() {

        String palindrome1 = "acddca";
        String palindrome2 = "aaaaaa";
        String palindrome3 = "a";
        String palindrome4 = "";

        System.out.println(BreakPalindrome(palindrome1));
        System.out.println(BreakPalindrome(palindrome2));
        System.out.println(BreakPalindrome(palindrome3));
        System.out.println(BreakPalindrome(palindrome4));
    }

    /*
     * Space complexity: O(n), since we need to store/manage the char array of the
     * palindrome
     * 
     * Time complexity: O(n), worst case to run through a palindrome full of a's.
     */
    public String BreakPalindrome(String palindrome) {

        if (palindrome.length() <= 1)
            return "";

        char[] charArray = palindrome.toCharArray();
        for (int charIndex = 0; charIndex < charArray.length; charIndex++) {
            if (charArray[charIndex] != 'a') {
                charArray[charIndex] = 'a';
                return String.valueOf(charArray);
            }
        }

        charArray[charArray.length - 1] = 'b';
        return String.valueOf(charArray);
    }
}
