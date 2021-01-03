import java.util.ArrayList;
import java.util.List;

/*
    A challenge in an Amazon Hackathon programming competition requires the construction of a sequence 
    using a specified number of integers within a range.
    The sequence must be strictly increasing at first and then strictly decreasing.
    The goal is to maximize the sequence array elements starting from the beginning.
    For example:
    - [4,5,4,3,2] beats [3,4,5,4,3] because its first element is larger,
    - [4,5,6,5,4] beats [4,5,4,3,2] because its third element is larger.
    
    Given the length of the sequence and the range of integers, return the winning sequence. 
    If it is not possible to construct such a sequence, return -1.

    Write an algorithm that returns a winning sequence and -1 if the sequence is not possible.

    Input
    - num, an integer representing the size of sequence to create;
    - lowerEnd, an integer representing the lower end of integer range;
    - upperEnd, an integer representing the upper end of integer range.

    Output
    Return a list of integers representing the winning sequence and if the sequence is not possible then return a list with an integer -1.

    Constraints
    3 <= num <= 10^5
    1 <= lowerEnds <= upperEnds <= 10^5

    Example
    Input:
    num = 5
    lowerEnd = 3
    upperEnd = 10

    Output:
    [9,10,9,8,7]

    Explanation:
    In this case, [9, 10, 9, 8, 7] is the winning sequence.
    It maintains the constraints of being first strictly increasing and then strictly decreasing
    There is no way to have integers in the sequence that are greater than [9, 10, 9, 8, 7].

    So the output is [9, 10, 9, 8, 7].
*/
public class WinningSequence {

    public void Test() {

        DisplayResult(Solve(5, 2, 5));
        DisplayResult(Solve(7, 2, 5));
        DisplayResult(Solve(8, 2, 5));
        DisplayResult(Solve(5, 3, 10));
    }

    public void DisplayResult(List<Integer> result) {
        for (int number : result)
            System.out.print(number + " ");
        System.out.println();
    }

    /*
     * Time Complexity: O(n), where n refers to num size.
     * 
     * Space Complexity: O(n), where n refers to num size. Array list used to store
     * the result.
     */
    public List<Integer> Solve(int num, int lowerEnd, int upperEnd) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        int numberRange = upperEnd - lowerEnd;

        if (num >= 2 * (numberRange + 1)) {
            result.add(-1);
            return result;
        }

        // Highest number, base case (since num guaranteed to be 3 at least)
        result.add(upperEnd - 1);
        result.add(upperEnd);
        result.add(upperEnd - 1);
        num -= 3;

        // Add numbers to right side until we hit max threshold of numbers available
        int counter = 2;
        while (num != 0) {
            if (counter <= numberRange) {
                result.add(upperEnd - counter);
                counter += 1;
                num -= 1;
            } else
                break;
        }

        // Reset counter + add numbers to left side if num > 0
        counter = 2;
        while (num != 0) {
            result.add(0, upperEnd - counter);
            counter += 1;
            num -= 1;
        }

        return result;
    }
}
