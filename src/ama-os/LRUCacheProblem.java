import java.util.HashMap;
import java.util.LinkedList;

/*
    https://leetcode.com/problems/lru-cache/
*/
public class LRUCacheProblem {

    public void Test()
    {
        // LRUCache test = new LRUCache(2);
        // test.put(1, 1);
        // test.put(2, 2);
        // test.get(1);
        // test.put(3, 3);
        // test.get(2);
        // test.put(4, 4);
        // test.get(1);
        // test.get(3);
        // test.get(4);

        LRUCache test2 = new LRUCache(2);
        test2.put(2, 1);
        test2.put(2, 2);
        test2.get(2);
        test2.put(1, 1);
        test2.put(4, 1);
        test2.get(2);
    }

    public class LRUCache
    {
        /*
            Space Complexity: O(n) [queue for maintaining least-recent-used numbers]
            + O(n) [map for quick access to key-value pairs].
        */

        public LinkedList<Integer> LRUqueue;
        public HashMap<Integer, Integer> LRUmap;
        public int maxCapacity;
        
        public LRUCache(int capacity) {
            
            LRUqueue = new LinkedList<Integer>();
            LRUmap = new HashMap<Integer, Integer>();
            maxCapacity = capacity;
        }
        
        /*
            Time Complexity: O(n) [remove first occurrence] + O(n) [add last],
            where n refers to the max capacity of the LRU cache.
        */
        public int get(int key) {
            
            if (LRUmap.containsKey(key))
            {
                // Refresh usage for key in the LRU queue 
                LRUqueue.removeFirstOccurrence(key);
                LRUqueue.addLast(key);

                return LRUmap.get(key);
            }
            else
                return -1;
        }
        
        /*
            Time Complexity: O(n) [remove first occurrence] + O(n) [add last],
            where n refers to the max capacity of the LRU cache.
        */
        public void put(int key, int value) {
            
            // If key already exists, remove it from queue
            if (LRUmap.containsKey(key))
                LRUqueue.removeFirstOccurrence(key);
            
            // ... regardless, add key to queue to refresh its usage and put key-value pair in map
            LRUqueue.addLast(key);
            LRUmap.put(key, value);
            
            // Check if map has exceeded max capacity
            if (LRUmap.size() > maxCapacity)
            {
                // Remove least-recent-used number from queue + map
                int lruNum = LRUqueue.removeFirst();
                LRUmap.remove(lruNum);
            }
        }
    }
    
}
