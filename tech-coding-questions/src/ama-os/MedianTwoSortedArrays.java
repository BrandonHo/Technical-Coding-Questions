/*
    https://leetcode.com/problems/median-of-two-sorted-arrays/
*/
public class MedianTwoSortedArrays {
    
    public void Test()
    {
        System.out.println(FindMedianFromSortedArrays(new int[] {1, 2}, new int[] {3, 4, 10, 12, 13}));
    }

    /*
        Time Complexity: 1/2.O(n + m), or ~O(n + m), where n refers to number of elements in arr1
        and m refers to number of elements in arr2.

        Space Complexity: 1/2.O(n + m) + 1, or ~O(n + m). This is used to store the result, merged array
        to quickly query the median of the two sorted arrays.
    */
    public double FindMedianFromSortedArrays(int[] arr1, int[] arr2)
    {
        // Build up result array with merged result up to half way (if they were combined)
        int[] resultArray = new int[(arr1.length + arr2.length) / 2 + 1];
        int arr1Index = 0;
        int arr2Index = 0;
        for (int resultIndex = 0; resultIndex < resultArray.length; resultIndex++)
        {
            if (arr1Index >= arr1.length)
            {
                resultArray[resultIndex] = arr2[arr2Index];
                arr2Index += 1;
            }
            else if (arr2Index >= arr2.length)
            {
                resultArray[resultIndex] = arr1[arr1Index];
                arr1Index += 1;
            }
            else if (arr1[arr1Index] <= arr2[arr2Index])
            {
                resultArray[resultIndex] = arr1[arr1Index];
                arr1Index += 1;
            }
            else
            {
                resultArray[resultIndex] = arr2[arr2Index];
                arr2Index += 1;
            }
        }

        // Check if the merged array of arr1 and arr2 has even size
        boolean isMergedArraySizeEven = (arr1.length + arr2.length) % 2 == 0? true : false;

        // If even, then we need to return an average of the two center-most elements
        if (isMergedArraySizeEven)
            return (resultArray[resultArray.length - 1] + resultArray[resultArray.length - 2]) / 2.0;

        // Otherwise just return the last element (median of the merged array)
        return resultArray[resultArray.length - 1];
    }
}
