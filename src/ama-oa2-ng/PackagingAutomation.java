import java.util.Arrays;

/*
    Amazon's Fulfillment Center consists of a packaging bay where orders are automatically packaged in groups(n).
    The first group can only have 1 item and all the subsequent groups can have one item more than the previous group.
    Given a list of items on groups, perform certain operations in order to satisfy the constraints required by packaging automation.

    The conditions are as follows:
    -   The first group must contain 1 item only.
    -   For all other groups, the difference between the number of items in adjacent groups must not be greater than 1.
        In other words, for 1<=i<n, arr[i]-arr[i-1]<=1

    To accomplish this, the following operations are available:
    -   Rearrange the groups in any way.
    -   Reduce any group to any number that is at least 1

    Write an algorithm to find the maximum number of items that can be packaged in the last group with the conditions in place.
*/
public class PackagingAutomation {

    public void Test() {

        int[] test1 = new int[] { 1, 3, 2, 2 };
        int[] test2 = new int[] { 3, 2, 3, 5 };

        System.out.println(PerformPA(test1.length, test1));
        System.out.println(PerformPA(test2.length, test2));
    }

    /*
     * Time Complexity: O(n log n) + O(n), or ~O(n log n), where n refers to the
     * number of packages.
     * 
     * O(n log n) is from sorting the array of packages, while the O(n) refers to
     * the traversal of the array to reduce packages.
     * 
     * Space Complexity: O(1).
     */
    public int PerformPA(int numGroups, int[] arr) {

        Arrays.sort(arr);
        arr[0] = 1;

        for (int groupIndex = 1; groupIndex < arr.length; groupIndex++)
            arr[groupIndex] = Math.min(arr[groupIndex], arr[groupIndex - 1] + 1);

        return arr[arr.length - 1];
    }
}
