import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public class NearestCities {

    /*
     * Amazon has Fulfillment Centers in multiple cities within a large geographic
     * region. The cities are arranged on a graph that has been divided up like an
     * ordinary Cartesian plane. Each city is located at an integral (x, y)
     * coordinate intersection. City names and locations are given in the form of
     * three arrays: c, x, and y, which are aligned by the index to provide the city
     * name (c[i]), and its coordinates, (x[i], y[i]).
     * 
     * Write an algorithm to determine the name of the nearest city that shares
     * either an x or a y coordinate with the queried city. If no other cities share
     * an xory coordinate, return NONE. If two cities have the same distance to the
     * queried city, q[i], consider the one with an alphabetically smaller name
     * (i.e. 'ab' < 'aba' < 'abb') as the closest choice.
     * 
     * The distance is denoted on a Euclidean plane: the difference in x plus the
     * difference in y.
     * 
     * Example 1:
     * 
     * Input:
     * 
     * numOfCities = 3
     * 
     * cities = ["c1", "c2", "c3"]
     * 
     * xCoordinates = [3, 2, 1]
     * 
     * yCoordinates = [3, 2, 3]
     * 
     * numOfQueries = 3
     * 
     * queries = ["c1", "c2", "c3"]
     * 
     * Output:
     * 
     * ["c3", NONE, "c1"]
     * 
     * Explanation:
     * 
     * There are three points(3,3), (2,2) and (1,3) represent the coordinates of the
     * cities on the graph. The nearest city to c1 is c3, which shares a y value
     * (distance = (3-1) + (3-3) = 2).
     * 
     * City c2 does not have a nearest city as none share an x or y with c2, so this
     * query returns NONE. A query of c3 returns c1 based on the first calculation.
     * The return array after all queries are complete is [c3, NONE, c1].
     */
    public void Test() {

        String[] cities = new String[] { "C1", "C2", "C3" };
        int[] xCoordinates = new int[] { 3, 2, 1 };
        int[] yCoordinates = new int[] { 3, 2, 3 };
        String[] queries = new String[] { "C1", "C2", "C3" };

        String[] cities2 = new String[] { "green", "red", "blue", "yellow", "pink" };
        int[] xCoordinates2 = new int[] { 100, 200, 300, 400, 500 };
        int[] yCoordinates2 = new int[] { 100, 200, 300, 400, 500 };
        String[] queries2 = new String[] { "green", "red", "blue", "yellow", "pink" };

        String[] cities3 = new String[] { "C1", "C2", "C3" };
        int[] xCoordinates3 = new int[] { 3, 3, 1 };
        int[] yCoordinates3 = new int[] { 3, 3, 3 };
        String[] queries3 = new String[] { "C1", "C2", "C3" };

        DisplayResult(PerformNearestCities(cities.length, cities, xCoordinates, yCoordinates, queries.length, queries));
        DisplayResult(
                PerformNearestCities(cities2.length, cities2, xCoordinates2, yCoordinates2, queries2.length, queries2));
        DisplayResult(
                PerformNearestCities(cities3.length, cities3, xCoordinates3, yCoordinates3, queries3.length, queries3));
    }

    public void DisplayResult(String[] result) {
        for (String resultQuery : result)
            System.out.println(resultQuery);
    }

    public class City {
        int x, y;
        String name;
    }

    public static Comparator<? super City> ComparatorXCoord = (cityA, cityB) -> {
        int xCoordCompareResult = Integer.compare(cityA.x, cityB.x);
        if (xCoordCompareResult == 0)
            xCoordCompareResult = cityA.name.compareTo(cityB.name);
        return xCoordCompareResult;
    };

    public static Comparator<? super City> ComparatorYCoord = (cityA, cityB) -> {
        int xCoordCompareResult = Integer.compare(cityA.y, cityB.y);
        if (xCoordCompareResult == 0)
            xCoordCompareResult = cityA.name.compareTo(cityB.name);
        return xCoordCompareResult;
    };

    /*
     * This solution extensively uses hash maps to support quick queries to cities.
     * More specifically, tree sets are used to store cities. Tree sets allow an
     * ordered collection of cities based on the requirements of the solution.
     * 
     * Time Complexity: O(n.[log n + log n]) + O(m log n), where n refers number of
     * cities and m refers to number of queries. The O(log n) generally refers to
     * the insertion or retrieval of items from the tree set.
     * 
     * Space Complexity: O(n.n) + O(n.n) + O(n.n) + O(m), where n refers to the
     * number of cities and m refers to the number of queries.
     * 
     * O(n.n) refers to storage of n keys that may each have n possible city objects
     * 
     * O(m) refers to storage of m results from queries.
     */
    public String[] PerformNearestCities(int numOfCities, String[] cities, int[] xCoordinates, int[] yCoordinates,
            int numOfQueries, String[] queries) {

        // Create hash maps to quickly query from xCoord/yCoord/name to cities
        HashMap<Integer, TreeSet<City>> xCoordToCities = new HashMap<>();
        HashMap<Integer, TreeSet<City>> yCoordToCities = new HashMap<>();
        HashMap<String, City> nameToCity = new HashMap<>();

        // Add cities to the hash maps
        for (int cityIndex = 0; cityIndex < numOfCities; cityIndex++) {

            City newCity = new City();
            newCity.x = xCoordinates[cityIndex];
            newCity.y = yCoordinates[cityIndex];
            newCity.name = cities[cityIndex];

            xCoordToCities.putIfAbsent(xCoordinates[cityIndex], new TreeSet<City>(ComparatorXCoord));
            yCoordToCities.putIfAbsent(yCoordinates[cityIndex], new TreeSet<City>(ComparatorXCoord));
            xCoordToCities.get(xCoordinates[cityIndex]).add(newCity);
            yCoordToCities.get(yCoordinates[cityIndex]).add(newCity);
            nameToCity.putIfAbsent(cities[cityIndex], newCity);
        }

        // Prepare result string array
        String[] result = new String[numOfQueries];

        // Iterate through queries
        for (int queryIndex = 0; queryIndex < numOfQueries; queryIndex++) {

            String queryCityName = queries[queryIndex];
            City queryCity = nameToCity.get(queryCityName);

            if (queryCity == null) {
                result[queryIndex] = "NONE";
                continue;
            }

            /*
             * Get the closest neighbours to the city from the query
             * 
             * The lower/higher methods run in O(log n) time. These methods strictly return
             * a value that is lower/higher than them, and therefore cannot return
             * themselves. Because of this, there are only a total of four possible
             * neighbours, which makes this constant time.
             * 
             * It is possible that these methods return null if they cannot find a
             * neighbour.
             */
            City[] neighbours = new City[4];
            neighbours[0] = xCoordToCities.get(queryCity.x).lower(queryCity);
            neighbours[1] = xCoordToCities.get(queryCity.x).higher(queryCity);
            neighbours[2] = yCoordToCities.get(queryCity.y).lower(queryCity);
            neighbours[3] = yCoordToCities.get(queryCity.y).higher(queryCity);

            // Now we check which of the neighbours has the smallest distance
            int minDistance = Integer.MAX_VALUE;
            City minDistanceCity = null;
            for (int neighbourIndex = 0; neighbourIndex < 4; neighbourIndex++) {
                City neighbourCity = neighbours[neighbourIndex];

                // Avoid checking null neighbours
                if (neighbourCity != null) {
                    int distance = Math.abs((queryCity.x - neighbourCity.x) + (queryCity.y - neighbourCity.y));
                    if (distance < minDistance) {
                        minDistance = distance;
                        minDistanceCity = neighbourCity;
                    }
                }
            }

            // If none of the neighbours valid - specify NONE
            if (minDistanceCity == null)
                result[queryIndex] = "NONE";

            // Otherwise specify the nearest city name
            else
                result[queryIndex] = minDistanceCity.name;
        }

        return result;
    }
}
