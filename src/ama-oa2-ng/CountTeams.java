
/*
    An Amazon Area Manager is trying to assemble a specialized team from a roster of available associates.
    There is a minimum number of associates to be involved, and each associate needs to have a skill rating within a certain range.
    Given a list of associates' skill levels with desired upper and lower bounds, determine how many teams can be created from the list.

    Write an algorithm to find the number of teams that can be created fulfilling the criteria.

    Input
    The input to the function/method consists of five arguments:
    num, an integer representing the number of associates;
    skills, a list of integers representing the skill levels of associates;
    minAssociates, an integer representing the minimum number of team members required;
    minLevel, an integer representing the lower limit for skill level, inclusive;
    maxLevel, an integer representing the upper limit for skill level, inclusive.

    Output
    Return an integer representing the total number of teams that can be formed per the criteria.

    Constraints
    1 <= num <= 20
    1 <= minAssociates <= num
    1 <= minLevel <= maxLevel <= 1000
    1 <= skills[i] <= 1000
    0 <= i < num

    Example
    Input:
    num = 6
    skills = [12, 4, 6, 13, 5, 10]
    minAssociates = 3
    minLevel = 4
    maxLevel = 10

    Output:
    5

    Explanation:
    The list includes associates with skill levels [12, 4, 6, 13,5, 10].
    They want to hire at least 3 associates with skill levels between 4 and 10, inclusive.
    Four of the associates with the following skill levels {4, 6,5,10} meet the criteria.
    There are 5 ways to form a team of 3 associates: {4,5,6}, {4, 6, 10}, {4,5,10}, {5, 6, 10}, and {4, 5, 6, 10}.
    So the output is 5.
*/
public class CountTeams {

    public void Test() {

        int[] skillsTest = new int[] { 12, 4, 6, 13, 5, 10 };
        System.out.println(PerformCountTeams(skillsTest.length, skillsTest, 3, 4, 10));
    }

    /*
     * Brute force, recursion method. This goes through the entire tree to count the
     * number of valid combinations. Immediately thought of memoisation here, but
     * can't seem to use it as the skills could mean different things at different
     * times.
     * 
     * Time Complexity: O(n^n), where n is the size of the skills array. There are n
     * skills to go through and n branching factor.
     * 
     * Space complexity: O(n), max recursion stack size.
     */
    public int PerformCountTeams(int num, int[] skills, int minAssociates, int minLevel, int maxLevel) {

        return RecursiveHelper(0, 0, skills, minAssociates, minLevel, maxLevel);
    }

    public int RecursiveHelper(int i, int curAssociates, int[] skills, int minAssociates, int minLevel, int maxLevel) {

        if (i >= skills.length)
            return 0;

        int count = 0;
        for (int j = i; j < skills.length; j++) {

            // Does associate skill satisfy requirement?
            if (skills[j] >= minLevel && skills[j] <= maxLevel) {

                // Update current associates for the recursive call
                curAssociates += 1;

                // If enough associates -> counts as a combination
                if (curAssociates >= minAssociates)
                    count += 1 + RecursiveHelper(j + 1, curAssociates, skills, minAssociates, minLevel, maxLevel);
                else
                    count += RecursiveHelper(j + 1, curAssociates, skills, minAssociates, minLevel, maxLevel);

                // ... Make sure to decrement current associates for other skill combinations
                curAssociates -= 1;
            }
        }

        return count;
    }

    /*
     * Method that uses the combination formula for calculating the number
     * combinations to be made. The formula is as follows:
     * 
     * nCr = n! / [(n - r)! * r!]
     * 
     * We already know the value of 'r' (minAssociates), so this method calculates
     * the value of 'n' and then sums up the combinations from 'r' to 'n'.
     * 
     * Time Complexity: O(n) + O(n) + O(n), where n refers to the size of the skills
     * array.
     * 
     * Space Complexity: O(n), where n refers to the size of the skills array
     * (factorial array created).
     */
    public int ChooseCombinationMethod(int num, int[] skills, int minAssociates, int minLevel, int maxLevel) {

        // First find out how many associates have the required
        int satisfied = 0;
        for (int skill : skills)
            if (skill >= minLevel && skill <= maxLevel)
                satisfied++;

        // If insufficient valid associates, stop
        if (satisfied < minAssociates)
            return 0;

        // Calculate the factorial array for
        int[] factorial = new int[satisfied + 1];
        factorial[0] = 1;
        for (int i = 1; i <= satisfied; i++)
            factorial[i] = factorial[i - 1] * i;

        int total = 0;
        for (int i = minAssociates; i <= satisfied; i++)
            total += factorial[satisfied] / factorial[i] * factorial[satisfied - i];

        return total;
    }
}
