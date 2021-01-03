import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
    Amazon Basics has several suppliers for its products.
    For each of the products, the stock is represented by a list of a number of items for each supplier.
    As items are purchased, the supplier raises the price by 1 per item purchased.
    Let's assume Amazon's profit on any single item is the same as the number of items the supplier has left.
    For example, if a supplier has 4 items, Amazon's profit on the first item sold is 4, then 3, then 2 and the profit of the last one is 1.
    Given a list where each value in the list is the number of the item at a given supplier and also given the number of items to be ordered.
    Write an algorithm to find the highest profit that can be generated for the given product.

    ---

    Input
    The input to the function/method consists on three arguments:
    numSuppliers, an integer representing the number of suppliers;
    inventory, a list of long integers representing the value of the item at a given supplier;
    order, a long integer representing the number of items to be ordered.

    Output
    Return a long integer representing the highest profit that can be generated for the given product.

    Constraints

    1 <= numSuppliers <= 10^5
    1 <= inventory[i] <= 10 ^ 5
    0 <= i < numSuppliers
    1 <= orders <= sum of inventory

    ---

    Example 1

    Input:
    numSuppliers = 2
    inventory = [3,5]
    order = 6

    Output:
    19

    Explanation:
    There are two suppliers, one with inventory 3 and the other with inventory 5, and 6 items were ordered.
    The maximum profit is made by selling 1 for 5, 1 for 4, and 2 at 3 and 2 at 2 units profit.
    The two suppliers are left with a unit of product each. The maximum profit generated is 5 + 4 + 2*3 + 2*2 = 19.
*/
public class FindTheHighestProfit {

    public void Test() {

        Long testResult = FindHighestProfit2(2, new Long[] { 3L, 5L }, 6L);
        System.out.println(testResult);
    }

    /*
     * Space Complexity: O(n) [items in priority queue]
     * 
     * Time Complexity: O(n log n) [adding n items via priority queue] + O(c log n)
     * [adding items back to priority queue, where c is order]
     */
    public Long FindHighestProfit(int numSuppliers, Long[] inventory, Long order) {

        // Create PQ with descending order
        PriorityQueue<Long> pricePerInventory = new PriorityQueue<Long>(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                if (o1 < o2)
                    return 1;
                else if (o1 == o2)
                    return 0;
                else
                    return -1;
            }
        });

        // Add profit-per-products in PQ
        for (int inventoryIndex = 0; inventoryIndex < inventory.length; inventoryIndex++)
            pricePerInventory.add(inventory[inventoryIndex]);

        // Calculate highest profits based on order using the PQ
        Long result = 0L;
        while (order != 0) {
            Long nextHighestProfit = pricePerInventory.poll();
            result += nextHighestProfit;
            pricePerInventory.add(nextHighestProfit - 1);
            order--;
        }
        return result;
    }

    /*
     * Better solution.
     * 
     * Space Complexity: O(n) [inventory stored in hash map].
     * 
     * Time Complexity: O(n) [adding n items to hash map] + O(c) [update result with
     * iterations of specified order]. Therefore ~O(n).
     */
    public Long FindHighestProfit2(int numSuppliers, Long[] inventory, Long order) {

        // Add profits to hash map, where key is profit and value is inventory count
        HashMap<Long, Integer> inventoryProfitMap = new HashMap<Long, Integer>();
        Long currMaxInventoryProfitInMap = Long.MIN_VALUE;
        for (int inventoryIndex = 0; inventoryIndex < inventory.length; inventoryIndex++) {

            currMaxInventoryProfitInMap = Math.max(currMaxInventoryProfitInMap, inventory[inventoryIndex]);
            if (!inventoryProfitMap.containsKey(inventory[inventoryIndex]))
                inventoryProfitMap.put(inventory[inventoryIndex], 1);
            else
                inventoryProfitMap.put(inventory[inventoryIndex],
                        inventoryProfitMap.get(inventory[inventoryIndex]) + 1);
        }

        // Iterate until orders fulfilled
        Long maxInventoryProfit = 0L;
        while (order != 0) {
            if (inventoryProfitMap.containsKey(currMaxInventoryProfitInMap)) {

                // Update max profit, then update hash map appropriately
                maxInventoryProfit += currMaxInventoryProfitInMap;

                inventoryProfitMap.put(currMaxInventoryProfitInMap,
                        inventoryProfitMap.get(currMaxInventoryProfitInMap) - 1);
                inventoryProfitMap.put(currMaxInventoryProfitInMap - 1,
                        inventoryProfitMap.getOrDefault(currMaxInventoryProfitInMap - 1, 0) + 1);
                if (inventoryProfitMap.get(currMaxInventoryProfitInMap) == 0)
                    currMaxInventoryProfitInMap -= 1;
                order -= 1;
            } else
                currMaxInventoryProfitInMap -= 1;
        }
        return maxInventoryProfit;
    }
}
