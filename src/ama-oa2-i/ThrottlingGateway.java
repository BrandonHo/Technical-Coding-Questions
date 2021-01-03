/*
    A planetarium has multiple entrances.
    Only the special Entrance X has cable cars carrying visitors into the planetarium while other entrances have walking tunnels.
    Everyone visiting the planetarium prioritizes entering from Entrance X.
    An empty cable car arrives every minute at Entrance X and takes at most 3 passengers.

    For safety and better user experience, Entrance X has the following constraints:
    The number of visitors on a cable car in any given minute cannot exceed 3.
    The number of visitors going though Entrance X in any given 10-minute period cannot exceed 20.
    A ten-minute period includes all visitors arriving from any time max(1, T-9) to T (inclusive of both) for any valid time T.
    The number of visitors in any given hour cannot exceed 60. Similar to above, 1 hour is from max(1, T-59) to T.
    Any visitor that exceeds any of the above limits will be assigned to other entrances instantly.

    Given the times at which different visitors arrive sorted ascending,
    write an algorithm to find how many people will be assigned to other entrances.

    Input:
    -   num, an integer representing the total number of visitors at X;
    -   arriveTime, a list of integers representing the times of various visitor arrivals.

    Output:
    Return an integer representing the total number of visitors NOT entering through Entrance X.

    Constraints
    1 <= num <= 10^6
    1 <= arriveTime[i] <= 10^9
    0 <= i< num

    Note
    Even if a visitor is assigned to other entrances, he/she is still considered for future calculations.
    Although, if a visitor is to be re-assign due to multiple constraints, he/she is still counted only once.

    Example

    Input:
    num = 27
    arriveTime = [1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7,7,7,7, 11, 11, 11, 11]

    Output:
    7

    Explanation:
    Visitor at 1 - Taken.
    Visitor at 1 - Taken.
    Visitor at 1 - Taken.

    Visitor at 1 - Re-assigned. At most 3 visitors are allowed in one minute.

    No visitor will be re-assigned till 6 as all comes at an allowed rate of 3 visitors per minute and the 10-minute clause is also not violated.
    Visitor at 7 - Taken. The total number of visitors has reached 20 now.

    Visitor at 7 - Re-assigned. At most 20 visitors are allowed in ten minutes.
    Visitor at 7 - Re-assigned. At most 20 visitors are allowed in ten minutes.
    Visitor at 7 - Re-assigned. At most 20 visitors are allowed in ten minutes. Note that the 1-minute limit is also violated here.

    Visitor at 11 - Taken. The 10-minute window has now become 2 to 11. Hence the total number of visitors in this window is 20 now.

    Visitor at 11 - Re-assigned. At most 20 visitors are allowed in ten minute s.
    Visitor at 11 - Re-assigned. At most 20 visitors are allowed in ten minutes.
    Visitor at 11 - Re-assigned. At most 20 visitors are allowed in ten minute s. Also, at most 3 visitors are allowed per minute.

    Hence, a total of 7 visitors are re-assigned.
*/
public class ThrottlingGateway {

    public void Test() {
        int[] testArriveTimes = new int[] { 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 11, 11,
                11, 11 };
        System.out.println(Perform(testArriveTimes.length, testArriveTimes));
    }

    /*
     * Time Complexity: O(n), where n refers to size of arriveTimes array.
     * 
     * Space Complexity: O(1).
     */
    public int Perform(int num, int[] arriveTimes) {
        int reassigned = 0;

        for (int i = 0; i < arriveTimes.length; i++) {
            if (i > 2 && arriveTimes[i] == arriveTimes[i - 3])
                reassigned++;
            else if (i > 19 && (arriveTimes[i] - arriveTimes[i - 20] < 10))
                reassigned++;
            else if (i > 59 && (arriveTimes[i] - arriveTimes[i - 60] < 60))
                reassigned++;
        }

        return reassigned;
    }
}
