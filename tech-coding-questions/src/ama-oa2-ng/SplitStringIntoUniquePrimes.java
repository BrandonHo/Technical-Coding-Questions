import java.util.Arrays;
import java.util.HashSet;

/*
    Amazon's operations team needs an algorithm that can break out a list of products for a given order.
    The products in the order are listed as a string and the order items are represented as prime numbers.
    Given a string consisting of digits [0-9], count the number of ways the given string can be split into prime numbers,
    which represent unique items in the order. The digits must remain in the order given and the entire string must be used.

    Write an algorithm to find the number of ways the given string can be split into unique prime numbers using the entire string.

    Input:
    inputString, a string representing the input string.

    Output:
    Return an integer representing the number of ways the given string can be split into unique primes using the entire string.

    Note:
    The inputString does not contain leading zeros.
    Each number split of the given number must be in the range 2 to 10 inclusive.
    Since the answer can be large, return the answer modulo (1000000007)

    Constraints:
    1 <= length of inputStrings <= 10^5

    Example

    Input:
    inputstring = 11373

    Output:
    6

    Explanation:
    This string can be split into prime numbers in 6 different ways: 
    [11,3,7,3], [113, 7,3], [11,3,73], [11, 37, 3], [113,73] and [11, 373].
    So the output is 6.
*/
public class SplitStringIntoUniquePrimes {

    public void Test() {

        int test1 = CountPrimeStrings("3175");
        int test2 = CountPrimeStrings("43");
        int test3 = CountPrimeStrings("11373");

        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);
    }

    private HashSet<String> primeStrings;

    private static int MOD = 1000000007;

    /*
     * Space Complexity: O(n) [integer DP array]
     * 
     * Time Complexity: O(n) [initialise DP array] + O(n log n) [recursive checking
     * of prime numbers in string]. This is my personal opinion.
     * 
     * Geeks4Geeks, however, mentioned time complexity is: O(n + n.log[log n]).
     */
    public int CountPrimeStrings(String inputString) {

        if (inputString.length() == 0)
            return 0;

        // Create hash map to allow quick querying of whether a number is a prime number
        primeStrings = GeneratePrimesUsingSieveAlgorithm(1000000);

        /*
         * This integer array is used for dynamic programming (DP). Each index refers to
         * the number of ways that prime strings can be made up for a specific size of
         * the string (from beginning of string).
         * 
         * For example, with the number 3147:
         * 
         * Index 1 = The number of ways that prime strings can be made with the number
         * '3'
         * 
         * Index 2 = The number of ways that prime strings can be made with the number
         * '31'
         * 
         * Index 3 = The number of ways that prime strings can be made with the number
         * '314'
         * 
         * ...etc
         */

        int[] splitDP = new int[inputString.length() + 1];
        Arrays.fill(splitDP, -1);
        splitDP[0] = 1;

        int answer = RecursiveHelper(inputString, inputString.length(), splitDP);

        return answer;
    }

    /*
     * This helper method generates a hash set of primes using the Sieve of
     * Eratosthenes algorithm. The time complexity of this method is: O(n) [making
     * initial prime array] + O(n log n) [updating/marking values in prime array] +
     * O(n) [adding primes into hash set]. Therefore ~O(n log n)
     * 
     * However, this is run once regardless of the string specified in the coding
     * question. Therefore this time complexity is not considered as it is constant
     * [O(1)].
     */
    private HashSet<String> GeneratePrimesUsingSieveAlgorithm(int upperBound) {

        boolean[] markedPrimes = new boolean[upperBound];
        for (int markedPrimeIndex = 0; markedPrimeIndex < markedPrimes.length; markedPrimeIndex++)
            markedPrimes[markedPrimeIndex] = true;
        markedPrimes[0] = markedPrimes[1] = false;

        for (int markedPrimeIndex = 2; markedPrimeIndex * markedPrimeIndex < markedPrimes.length; markedPrimeIndex++)
            if (markedPrimes[markedPrimeIndex])
                for (int sieveIndex = markedPrimeIndex
                        * markedPrimeIndex; sieveIndex < markedPrimes.length; sieveIndex += markedPrimeIndex)
                    markedPrimes[sieveIndex] = false;

        HashSet<String> primeStrings = new HashSet<String>();
        for (int markedPrimeIndex = 2; markedPrimeIndex < markedPrimes.length; markedPrimeIndex++)
            if (markedPrimes[markedPrimeIndex])
                primeStrings.add(Integer.toString(markedPrimeIndex));

        return primeStrings;
    }

    public int RecursiveHelper(String number, int i, int[] dp) {

        // If we already calculated the count for ith index - return it.
        if (dp[i] != -1)
            return dp[i];
        int count = 0;

        /*
         * Iterate from 1 since we want integer strings of at least length 1
         * 
         * Iterate to 6 since the largest number possible is 6 digits long (as per
         * constraints)
         */
        for (int j = 1; j <= 6; j++) {

            // Debugging purposes
            // String test = "";
            // if (i - j >= 0)
            // test = number.substring(i - j, i);

            /*
             * Checking the following conditions: 1.) A valid index in the string 2.) Not a
             * leading zero in the number string 3.) The number is a prime
             */
            if (i - j >= 0 && number.charAt(i - j) != '0' && primeStrings.contains(number.substring(i - j, i))) {

                // Add to the counter
                count += RecursiveHelper(number, i - j, dp);

                /*
                 * The primary reason why the count is modulated by this number is to prevent
                 * integer overflows.
                 * 
                 * See here: https://www.geeksforgeeks.org/modulo-1097-1000000007/
                 */
                count %= MOD;
            }
        }

        // Update counter in the dp array, then return it
        dp[i] = count;
        return count;
    }
}
