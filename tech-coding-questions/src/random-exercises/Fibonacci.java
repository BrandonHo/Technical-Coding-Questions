public class Fibonacci {

    public void Test() {

        System.out.println(DPTabulation(5));
        System.out.println(DPTabulation(7));
        System.out.println(DPTabulation(8));
        System.out.println(DPTabulation(50));
    }

    /*
     * Time Complexity: O(n), iterate up to n fibonacci numbers.
     * 
     * Space Complexity: O(n), array storing n fibonacci numbers.
     */
    public long DPTabulation(int n) {

        long[] result = new long[n + 1];
        result[1] = 1;

        for (int x = 0; x < result.length; x++) {
            if (x + 1 < result.length)
                result[x + 1] += result[x];
            if (x + 2 < result.length)
                result[x + 2] += result[x];
        }

        return result[n];
    }
}
