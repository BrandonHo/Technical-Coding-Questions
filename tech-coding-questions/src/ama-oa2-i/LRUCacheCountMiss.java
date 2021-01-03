import java.util.LinkedList;
import java.util.List;

/*
    A virtual memory management system in an operating system at Amazon can use LeastRecently-Used (LRU) cache.
    When a requested memory page is not in the cache and the cache is full, the page that was least-recently-used
    should be removed from the cache to make room for the requested page.
    If the cache is not full, the requested page can simply be added to the cache and considered the most-recently-used page in the cache.
    A given page should occur at most once in the cache.

    Given the maximum size of the cache and a list of page requests, write an algorithm to calculate the number of cache misses.
    A cache miss occurs when a page is requested and isn't found in the cache.

    Inputs:
    num, an integer representing the number of pages;
    pages, a list of integers representing the pages requested;
    maxCacheSize, an integer representing the size of the cache.

    Output
    Return an integer representing the number of cache misses.

    Note
    The cache is initially empty and the list contains pages numbered in the range 1-50.
    A page at index "i" in the list is requested before a page at index "i+1".

    Constraints
    0 <= i < num

    Example

    Input:
    num = 6
    pages = [1,2,1,3,1,2]
    maxCacheSize = 2

    Output: 
    4 

    Explanation:
    Cache state as requests come in ordered longest-time-in-cache to shortest-time-in-cache:
    1*
    1,2*
    2,1
    1,3*
    3,1
    1,2*

    Asterisk (*) represents a cache miss. Hence, the total number of a cache miss is 4.
*/
public class LRUCacheCountMiss {

    public void Test() {

        int[] test = new int[] { 1, 2, 1, 3, 1, 2 };
        int testResult = PerformCountMiss(test, 2);
        System.out.println(testResult);
    }

    /*
     * Space Complexity: O(c), where c refers to the max cache size.
     * 
     * Time Complexity: O(n) [go through entire pages array]
     */
    public int PerformCountMiss(int[] pages, int maxCacheSize) {
        List<Integer> cache = new LinkedList<Integer>();
        int missCount = 0;

        for (int pageIndex = 0; pageIndex < pages.length; pageIndex++) {

            // Check if page in the cache -> remove page
            if (cache.contains(pages[pageIndex]))
                cache.remove(pages[pageIndex]);
            else {

                // Since page not in cache, increment miss count
                missCount++;

                // If at max cache size, remove first page
                if (cache.size() == maxCacheSize)
                    cache.remove(0);
            }

            // Regardless, add the new page into the cache
            cache.add(pages[pageIndex]);
        }

        return missCount;
    }
}
