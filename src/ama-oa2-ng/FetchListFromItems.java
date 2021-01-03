import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/*
    Amazon's website contains one to many items in each page.
    To mimic the logic of the website, an Amazon programmer has a list of items and each item has its name, relevance and price.
    After sorting the items by (name: 0, relevance: 1, price: 2), the programmer is trying to find out a list of items displayed in a chosen page.

    Suppose that you are given a list of items, the sort column, the sort order (0: ascending, 1: descending),
    the number of items to be displayed in each page and a page number
     
    Write an algorithm to determine the list of item names in the specified page while respecting the item's order (Page number starts at 0).

    Input
    -   numOfltems, an integer representing the number of items;
    -   items, a map of string as key representing the name and pair of integers as values representing the relevance, price.
    -   sortParameter, an integer representing the value used for sorting (0 for name, 1 for relevance, 2 for price)
    -   sortOrder, an integer representing the order of sorting (0 for ascending order and 1 descending order);
    -   itemsPerPage, an integer representing the number of items per page;
    -   pageNumber, an integer representing the page number.

    Output
    Return a list of strings representing the item names on the requested page in the order they are displayed.

    Constraints
    1 <= numOfitems < 10^5
    0 <= relevance, price < 10^8
    0 <= pageNumber < 10

    Note
    itemsPerPage is always greater than 0, and is always less than the minimum of numOfltems and 20.

    Example

    Input:
    numOfltems = 3
    items = [["item1", 10,15], ["item2",3,4]. ["item3", 17, 8]]
    sortParameter=1
    sortOrder = 0
    itemsPerPage=2
    pageNumber=1

    Output:
    ["item3"]

    Explanation:

    There are 3 items.
    Sort them by relevance(sortParameter = 1) in ascending order ( items = [["item2", 3, 4], ["item1", 10,15], ["item3", 17, 8]]).
    Display up to 2 items on each page.
    The page 0 contains 2 item names ["item2", "item1"] and page 1 contains only 1 item name ["item3"].

    So, the output is "item3".
*/
public class FetchListFromItems {

    public void Test() {

        HashMap<String, int[]> map = new HashMap<String, int[]>();
        map.put("item1", new int[] { 10, 15 });
        map.put("item2", new int[] { 3, 4 });
        map.put("item3", new int[] { 17, 8 });

        List<String> test1 = FetchList(3, map, 1, 1, 3, 0);

        System.out.println(test1);
    }

    public List<String> FetchList(int numOfItems, Map<String, int[]> items, int sortParameter, int sortOrder,
            int itemsPerPage, int pageNumber) {

        SortedSet<Map.Entry<String, int[]>> set = new TreeSet<>(new Comparator<Map.Entry<String, int[]>>() {

            @Override
            public int compare(Map.Entry<String, int[]> o1, Map.Entry<String, int[]> o2) {

                if (sortParameter == 0) {

                    if (sortOrder == 0)
                        return o1.getKey().compareTo(o2.getKey());
                    return o2.getKey().compareTo(o1.getKey());

                } else if (sortParameter == 1) {

                    int[] o1Array = o1.getValue();
                    int[] o2Array = o2.getValue();

                    if (sortOrder == 0) {
                        if (o1Array[0] > o2Array[0])
                            return 1;
                        else if (o1Array[0] == o2Array[0])
                            return 0;
                        else
                            return -1;
                    }

                    if (o1Array[0] < o2Array[0])
                        return 1;
                    else if (o1Array[0] == o2Array[0])
                        return 0;
                    else
                        return -1;

                } else if (sortParameter == 2) {

                    int[] o1Array = o1.getValue();
                    int[] o2Array = o2.getValue();

                    if (sortOrder == 0) {
                        if (o1Array[1] > o2Array[1])
                            return 1;
                        else if (o1Array[1] == o2Array[1])
                            return 0;
                        else
                            return -1;
                    }

                    if (o1Array[1] < o2Array[1])
                        return 1;
                    else if (o1Array[1] == o2Array[1])
                        return 0;
                    else
                        return -1;
                }

                return 0;
            }
        });

        set.addAll(items.entrySet());

        List<String> result = new ArrayList<String>();
        for (Map.Entry<String, int[]> entry : set)
            result.add(entry.getKey());

        int startIndex = pageNumber * itemsPerPage;
        int endIndex = startIndex + itemsPerPage > result.size() ? result.size() : startIndex + itemsPerPage;

        return result.subList(startIndex, endIndex);
    }
}
