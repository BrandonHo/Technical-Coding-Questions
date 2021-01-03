import java.util.Arrays;

public class CutOffRank {

    /*
     * Parameters: scores : List of int cutOffRank : int num: int (denoting amount
     * of scores)
     * 
     * You are given a list of integers representing scores of players in a video
     * game. Players can 'level-up' if by the end of the game they have a rank that
     * is at least the cutOffRank. A player's rank is solely determined by their
     * score relative to the other players' scores. For example:
     * 
     * Score : 10 | Rank 1 Score : 5 | Rank 2 Score : 3 | Rank 3 etc.
     * 
     * If multiple players happen to have the same score, then they will all receive
     * the same rank. However, the next player with a score lower than theirs will
     * receive a rank that is offset by this. For example:
     * 
     * Score: 10 | Rank 1 Score: 10 | Rank 1 Score: 10 | Rank 1 Score : 5 | Rank 4
     * 
     * Finally, any player with a score of 0 is automatically ineligible for
     * leveling-up, regardless of their rank.
     * 
     * Return the number of players who are eligible for leveling-up
     */

    public void Test() {

        int[] test = new int[] { 100, 50, 50, 25 };
        int[] test2 = new int[] { 2, 2, 3, 4, 5 };
        int[] test3 = new int[] { 10, 10, 10, 4 };

        int testCount = CountLevelUpRank(test, 3);
        int testCount2 = CountLevelUpRank(test2, 4);
        int testCount3 = CountLevelUpRank(test3, 1);

        int testCountSort = CountLevelUpRankSort(test, 3);
        int testCountSort2 = CountLevelUpRankSort(test2, 4);
        int testCountSort3 = CountLevelUpRankSort(test3, 1);

        System.out.println(testCount);
        System.out.println(testCount2);
        System.out.println(testCount3);

        System.out.println("");

        System.out.println(testCountSort);
        System.out.println(testCountSort2);
        System.out.println(testCountSort3);
    }

    /*
     * Space: O(1), constant due to using a int array (map-like) with 101 spaces.
     * Restriction mentioned 0 <= playerScore <= 100
     * 
     * Time: O(n) + O(n) or ~O(n), no sort is required, and therefore we can just
     * add items to the array and work from there.
     */
    public int CountLevelUpRank(int[] playerScores, int cutOffRank) {

        int[] scoreMap = new int[101];
        for (int playerScore : playerScores)
            scoreMap[playerScore] += 1;

        int rankUpCounter = 0;
        int currRank = 1;

        for (int playerScoreIndex = scoreMap.length - 1; playerScoreIndex >= 0; playerScoreIndex--) {

            // Have we exceeded cutoff rank? Stop
            if (currRank > cutOffRank)
                break;

            // Ignore player scores with 0
            if (scoreMap[playerScoreIndex] != 0) {

                rankUpCounter += scoreMap[playerScoreIndex];
                currRank += scoreMap[playerScoreIndex];
            }
        }

        return rankUpCounter;
    }

    /*
     * Space: O(1), constant since you do not use additional memory for storage or
     * anything.
     * 
     * Time: O(n log n), sort is used in this solution which dominates the time
     * complexity for this solution.
     * 
     * It is important to mention that this solution is faster for smaller arrays.
     * The previous solution must iterate through the entire score map (of size 101)
     * regardless of the size for the scores array, while the sort solution only
     * iterates over the scores array.
     */
    public int CountLevelUpRankSort(int[] playerScores, int cutOffRank) {

        Arrays.sort(playerScores);

        int rankUpCounter = 0;
        int currentRank = 0;

        int prevScore = -1;
        int prevScoreCount = 1;

        for (int x = playerScores.length - 1; x >= 0; x--) {

            if (playerScores[x] != 0) {

                if (playerScores[x] != prevScore) {

                    currentRank += prevScoreCount;

                    if (currentRank > cutOffRank)
                        break;

                    rankUpCounter += 1;
                    prevScore = playerScores[x];
                    prevScoreCount = 1;

                } else {
                    rankUpCounter += 1;
                    prevScoreCount += 1;
                }
            }
        }

        return rankUpCounter;
    }
}
