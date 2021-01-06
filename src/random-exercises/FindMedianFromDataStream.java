import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/*
    https://leetcode.com/problems/find-median-from-data-stream/
*/
public class FindMedianFromDataStream {

    public void Test() {

        MedianFinderHashMap test = new MedianFinderHashMap();
        test.addNum(6);
        test.displayMedian();
        test.addNum(10);
        test.displayMedian();
        test.addNum(2);
        test.displayMedian();
        test.addNum(6);
        test.displayMedian();
        test.addNum(5);
        test.displayMedian();
        test.addNum(0);
        test.displayMedian();
        test.addNum(6);
        test.displayMedian();
        test.addNum(3);
        test.displayMedian();
        test.addNum(1);
        test.displayMedian();
        test.addNum(0);
        test.displayMedian();
        test.addNum(0);
        test.displayMedian();

        // MedianFinder test2 = new MedianFinder();
        // test2.addNum(40);
        // test2.displayMedian();
        // test2.addNum(12);
        // test2.displayMedian();
        // test2.addNum(16);
        // test2.displayMedian();
        // test2.addNum(14);
        // test2.displayMedian();
        // test2.addNum(35);
        // test2.displayMedian();
        // test2.addNum(19);
        // test2.displayMedian();
        // test2.addNum(34);
        // test2.displayMedian();
        // test2.addNum(35);
        // test2.displayMedian();
        // test2.addNum(28);
        // test2.displayMedian();
        // test2.addNum(35);
        // test2.displayMedian();
        // test2.addNum(26);
        // test2.displayMedian();
        // test2.addNum(6);
        // test2.displayMedian();
        // test2.addNum(8);
        // test2.displayMedian();
        // test2.addNum(2);
        // test2.displayMedian();
        // test2.addNum(14);
        // test2.displayMedian();
        // test2.addNum(25);
        // test2.displayMedian();
        // test2.addNum(25);
        // test2.displayMedian();
        // test2.addNum(4);
        // test2.displayMedian();
        // test2.addNum(33);
        // test2.displayMedian();
        // test2.addNum(18);
        // test2.displayMedian();
        // test2.addNum(10);
        // test2.displayMedian();
        // test2.addNum(14);
        // test2.displayMedian();
        // test2.addNum(27);
        // test2.displayMedian();
        // test2.addNum(3);
        // test2.displayMedian();
        // test2.addNum(35);
        // test2.displayMedian();
        // test2.addNum(13);
        // test2.displayMedian();
        // test2.addNum(24);
        // test2.displayMedian();
        // test2.addNum(27);
        // test2.displayMedian();
        // test2.addNum(14);
        // test2.displayMedian();
        // test2.addNum(5);
        // test2.displayMedian();
        // test2.addNum(0);
        // test2.displayMedian();
        // test2.addNum(38);
        // test2.displayMedian();
        // test2.addNum(19);
        // test2.displayMedian();
        // test2.addNum(25);
        // test2.displayMedian();
        // test2.addNum(11);
        // test2.displayMedian();
        // test2.addNum(14);
        // test2.displayMedian();
        // test2.addNum(31);
        // test2.displayMedian();
        // test2.addNum(30);
        // test2.displayMedian();
        // test2.addNum(11);
        // test2.displayMedian();
        // test2.addNum(31);
        // test2.displayMedian();
        // test2.addNum(0);
        // test2.displayMedian();
    }
    
    /*
        Standard solution that uses a list + binary search.

        Overall Space Complexity: O(n)
    
        For inserting n elements in the data structure...

        Time Complexity: O(n).O(log n) [n elements with O(log n) insertions]
        Space Complexity: O(1)

        For finding the median in the data structure...

        Time Complexity: O(1)
        Space Complexity: O(1)
    */
    public class MedianFinder {

        List<Integer> nums;
    
        public MedianFinder() {
            nums = new ArrayList<Integer>();
        }
        
        public void addNum(int num) {
            
            if (nums.size() == 0)
            {
                nums.add(num);
                return;
            }
            
            if (nums.get(0) >= num)
            {
                nums.add(0, num);
                return;
            }
            
            int addIndex = BinarySearch(num);
            if (num > nums.get(addIndex))
                nums.add(addIndex + 1, num);
            else
                nums.add(addIndex, num);
                
        }
        
        public int BinarySearch(int target)
        {
            int startIndex = 0;
            int endIndex = nums.size() - 1;
            
            while (startIndex < endIndex)
            {
                int midIndex = (startIndex + endIndex) / 2;
                int midNum = nums.get(midIndex);
                
                if (midNum == target)
                    return midIndex;
                else if (target < midNum)
                    endIndex = midIndex - 1;
                else
                    startIndex = midIndex + 1;
            }
            
            return startIndex;
        }
        
        public double findMedian() {
            
            if (nums.size() % 2 != 0)
            {
                int midIndex = nums.size() / 2;
                return nums.get(midIndex) * 1.0;
            }
            else
            {
                int midIndexRight = nums.size() / 2;
                int midIndexLeft = midIndexRight - 1;
                
                return (nums.get(midIndexLeft) + nums.get(midIndexRight)) / 2.0;
            }
        }

        public void displayMedian()
        {
            System.out.println(findMedian());
        }
    }

    /*
        Alternative solution where you use two heads to manage the upper and lower bounds
        of the median.

        Overall Space Complexity: O(n)

        Inserting n elements into the data structure...

        Time Complexity: O(n).O(log n) [inserting n elements into heap]. Since two heaps are
        used + constantly balanced, this is actually closer towards O(n).O(log n/2).
        Space Complexity: O(1)

        Finding median in the data structure...

        Time Complexity: O(1)
        Space Complexity: O(1)
    */
    public class MedianFinderHeap {

        PriorityQueue<Integer> lowerBoundMaxHeap;
        PriorityQueue<Integer> upperBoundMinHeap;

        public MedianFinderHeap()
        {
            lowerBoundMaxHeap = new PriorityQueue<Integer>((a, b) -> (b - a));
            upperBoundMinHeap = new PriorityQueue<Integer>();
        }

        public void addNum(int num)
        {
            if (lowerBoundMaxHeap.isEmpty())
            {
                lowerBoundMaxHeap.add(num);
                return;
            }
            
            if (num > lowerBoundMaxHeap.peek())
            {
                upperBoundMinHeap.add(num);
                if (upperBoundMinHeap.size() > lowerBoundMaxHeap.size())
                    lowerBoundMaxHeap.add(upperBoundMinHeap.poll());
            }
            else
            {
                lowerBoundMaxHeap.add(num);
                if (lowerBoundMaxHeap.size() > upperBoundMinHeap.size() + 1)
                    upperBoundMinHeap.add(lowerBoundMaxHeap.poll());
            }
        }

        public double findMedian()
        {
            if (lowerBoundMaxHeap.size() == upperBoundMinHeap.size())
                return (lowerBoundMaxHeap.peek() + upperBoundMinHeap.peek()) / 2.0;
            else
                return lowerBoundMaxHeap.peek() * 1.0;
        }
    }

    /*
        Alternative solution to follow up - if the numbers are restricted between 0 and 100.
        This constraint allows us to use a hash map to store the numbers.

        Overall Space Complexity: O(n).O(1) [n keys and their counts in the the map]

        For inserting n elements in the data structure...

        Time Complexity: O(n).O(1) [n elements with O(1) insertions]
        Space Complexity: O(1)

        For finding the median in the data structure...

        Time Complexity: O(101), or ~O(1).
        Space Complexity: O(1)
    */
    public class MedianFinderHashMap {

        HashMap<Integer, Integer> numsMap;
        int size;
    
        public MedianFinderHashMap() {
            numsMap = new HashMap<Integer, Integer>();
            size = 0;
        }
        
        public void addNum(int num) {
            
            numsMap.put(num, numsMap.getOrDefault(num, 0) + 1);
            size += 1;
        }
        
        public double findMedian() {
            
            boolean isSizeEven = size % 2 == 0? true : false;

            if (isSizeEven)
            {
                int medianEvenCountRight = size / 2 + 1;
                int medianEvenCountLeft = medianEvenCountRight - 1;
                return (FindNumAssociatedWithCount(medianEvenCountLeft) + FindNumAssociatedWithCount(medianEvenCountRight)) / 2.0;
            }
            else
            {
                int medianOddCount = (size / 2) + 1;
                return FindNumAssociatedWithCount(medianOddCount);
            }
        }

        public int FindNumAssociatedWithCount(int count)
        {
            for (int numKey = 0; numKey < 101; numKey++)
            {
                int numKeyCount = numsMap.getOrDefault(numKey, 0);
                if (count - numKeyCount <= 0)
                    return numKey;
                else
                    count -= numKeyCount;
            }

            return -1;
        }

        public void displayMedian()
        {
            System.out.println(findMedian());
        }
    }
}


