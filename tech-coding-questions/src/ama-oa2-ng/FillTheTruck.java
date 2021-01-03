import java.util.Comparator;
import java.util.PriorityQueue;

/*
    An Amazon Warehouse manager needs to create a shipment to fill a truck. All
    of the products in the warehouse are in boxes of the same size. Each product
    is packed in some number of units per box.

    Suppose that you are given the number of boxes the truck can hold.
    Write an algorithm to determine the maximum number of units of any mix of
    products that can be shipped.

    The input to the function method consists of five arguments:
    -   numProducts: an integer representing number of products
    -   boxes: a list of integers representing the number of available boxes for
        products
    -   unitSize: an integer representing size of unitsPerBox
    -   unitsPerBox: a list of integers representing the number of units packed in
        each box
    -   truckSize: an integer representing the number of boxes the truck can carry.

    Output:
    Return an integer representing the maximum units that can be carried
    by the truck.

    Example

    Input:
    num=3
    boxes=[1,2,3]
    unitSize=3
    unitsPerBox= [3,2,1]
    truckSize = 3

    Output
    7

    Explanation:

    Product 0: because boxes[0] = 1, we know there is 1 box in product 0. And
    because unitsPerBox[0] = 3, we know there is 1 box with 3 units in product 0.

    Product 1: 2 boxes with 2 units each

    Product 2: 3 boxes with 1 unit each

    Finally we have the packed products like a list : [3, 2, 2, 1, 1, 1]

    The truckSize is 3, so we pick the top 3 from the above list, which is [3, 2,
    2], and return the sum 7.

    The maximum number of units that can be shipped = 3 + 2 + 2 = 7 units
*/
public class FillTheTruck {

    public void Test() {

        int[] boxes = new int[] { 1, 2, 3 };
        int[] unitsPerBox = new int[] { 3, 2, 1 };

        int result = PerformFillTheTruck(boxes.length, boxes, unitsPerBox.length, unitsPerBox, 3);

        System.out.println(result);
    }

    /*
     * Space Complexity: O(n.c), where n refers to the number of products and c
     * refers to the maximum units per box. This space complexity is provided by the
     * PQ that stores the units per box per each product. Note, c is a constant,
     * therefore the space complexity ~O(n).
     * 
     * Time Complexity: O(n.c log n.c) + O(t), where n refers to number of products,
     * c refers to maximum units per box, and t refers to truck size.
     * 
     * - O(n.c log n.c) refers to inserting n products for c boxes in the PQ.
     * 
     * - O(t) refers to summing the units per box of the top values in the PQ.
     * 
     * Both c and t are constants, therefore time complexity can be generalised as
     * ~O(n log n).
     */
    public int PerformFillTheTruck(int numProducts, int[] boxes, int unitSize, int[] unitsPerbox, int truckSize) {

        // Create PQ that is ordered in descending order
        PriorityQueue<Integer> packedProductsQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 < o2)
                    return 1;
                else if (o2 < o1)
                    return -1;
                else
                    return 0;
            }
        });

        // Add units per box to PQ
        for (int boxIndex = 0; boxIndex < numProducts; boxIndex++) {

            int numBoxesForProduct = boxes[boxIndex];
            int unitsPerBoxForProduct = unitsPerbox[boxIndex];

            while (numBoxesForProduct != 0) {
                packedProductsQueue.add(unitsPerBoxForProduct);
                numBoxesForProduct -= 1;
            }
        }

        // Then sum the top units per box based on truck size
        int maxProductsToShip = 0;
        while (truckSize != 0) {
            maxProductsToShip += packedProductsQueue.poll();
            truckSize -= 1;
        }

        return maxProductsToShip;
    }
}
