import java.util.Arrays;

public class SieveTest {

    public void Test() {

        BuiidSieve(100);
    }

    public boolean BuiidSieve(int num) {
        boolean[] sieve = new boolean[num + 1];
        Arrays.fill(sieve, true);
        sieve[0] = false;
        sieve[1] = false;

        for (int x = 2; x < num; x++) {
            sieve[x] = IsPrime(x);
            for (int y = x + x; y < num; y += x) {
                sieve[y] = false;
            }
        }

        return false;
    }

    public boolean IsPrime(int num) {
        for (int x = 2; x <= Math.sqrt(num); x++)
            if (num % x == 0)
                return false;
        return true;
    }
}
