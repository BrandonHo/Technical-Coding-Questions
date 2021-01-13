/*
    Given the current day, "day", and an integer "K",
    Write a function that returns the day of the week after K days.

    Example 1:

    Input
    day = "Monday"
    K = 3

    Output
    "Thursday"

    Example 2:

    Input
    day = "Tuesday"
    K = 101

    Output
    "Friday"
*/
public class DayOfWeekKDaysLater {

    public void Test()
    {
        System.out.println(Solve("Monday", 3));
        System.out.println(Solve("Tuesday", 101));
    }

    /*
        Could use a hash map to help match days with indices/numbers to have O(1) queries.
        However, O(7) is not significantly worse, so just used an array here.

        Time Complexity: O(7), or ~O(1).

        Space Complexity: O(7), or ~O(1).
    */
    public String Solve(String day, int K)
    {
        // Array to match days with indices
        String[] integerToDay = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        
        // Find index for the specified day
        int specifiedDayIndex = 0;
        for (int dayIndex = 0; dayIndex < 7; dayIndex++)
            if (integerToDay[dayIndex] == day)
                specifiedDayIndex = dayIndex;

        // Calculate the index for the specified day after k days
        int nextKDayIndex = (specifiedDayIndex + K) % 7;

        // Return the appropriate day using the calculated index
        return integerToDay[nextKDayIndex];
    }
}
