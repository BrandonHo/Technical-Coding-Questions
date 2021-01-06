public class BinarySearch {
    
    public void Test()
    {
        int targetIndex = PerformBinarySearch(5, new int[]{ 5, 6, 7, 8, 9});
        int targetIndex2 = PerformBinarySearch(5, new int[]{ 1, 1, 2, 3, 4, 4, 4, 5});
        int targetIndex3 = PerformBinarySearch(3, new int[]{ 1, 1, 2, 4, 5, 7, 8});
        int targetIndex4 = PerformBinarySearch(6, new int[]{ 1, 1, 2, 4, 5, 7, 8});

        System.out.println(targetIndex);
        System.out.println(targetIndex2);
        System.out.println(targetIndex3);
        System.out.println(targetIndex4);
    }

    public int PerformBinarySearch(int target, int[] nums)
    {
        int startIndex = 0;
        int endIndex = nums.length - 1;
        
        while (startIndex < endIndex)
        {
            int midIndex = (startIndex + endIndex) / 2;
            int midNum = nums[midIndex];
            
            if (midNum == target)
                return midIndex;
            else if (target < midNum)
                endIndex = midIndex - 1;
            else
                startIndex = midIndex + 1;
        }
        
        return startIndex;
    }
}
