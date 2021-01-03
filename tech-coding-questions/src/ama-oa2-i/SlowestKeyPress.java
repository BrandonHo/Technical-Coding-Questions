/*
    https://leetcode.com/problems/slowest-key/
*/
public class SlowestKeyPress {

    public void Test() {

        System.out.println(Solve("cbcd", new int[] { 9, 29, 49, 50 }));
        System.out.println(Solve("spuda", new int[] { 12, 23, 36, 46, 62 }));
    }

    /*
     * Time Complexity: O(n), where n refers to the number of key presses or release
     * times.
     * 
     * Space Complexity: O(1).
     */
    public char Solve(String keysPressed, int[] releaseTimes) {

        // Edge case
        if (keysPressed.length() == 0)
            return ' ';

        // Base case
        int maxDuration = releaseTimes[0];
        char maxDurationChar = keysPressed.charAt(0);
        int prevReleaseTime = releaseTimes[0];

        for (int keyIndex = 1; keyIndex < releaseTimes.length; keyIndex++) {

            int durationBetweenKeys = releaseTimes[keyIndex] - prevReleaseTime;

            // If beats max duration, store the data
            if (durationBetweenKeys > maxDuration) {
                maxDuration = releaseTimes[keyIndex] - prevReleaseTime;
                maxDurationChar = keysPressed.charAt(keyIndex);
            }
            // Could potentially encounter same duration but lexicographlly better char
            else if (durationBetweenKeys == maxDuration) {
                if (Character.compare(maxDurationChar, keysPressed.charAt(keyIndex)) < 0)
                    maxDurationChar = keysPressed.charAt(keyIndex);
            }

            prevReleaseTime = releaseTimes[keyIndex];
        }

        return maxDurationChar;
    }
}
