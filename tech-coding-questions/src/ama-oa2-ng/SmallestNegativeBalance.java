import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;

/*
    Amazon is working on a new application for recording internal debts across teams.
    This program can be used to create groups that show all records of debts between the group members. Given the group debt records observed for this team (including the borrower name, lender name, and debt amount), who in the group has the smallest negative balance?

    Notes: -10 is smaller than -1. If multiple people have the smallest negative balance, return the list in alphabetical order. If nobody has a negative balance, return the list consisting of string "Nobody has a negative balance".

    Write an algorithm to find who in the group has the smallest negative balance.

    Input:
    The input to the function/method consists of three arguments:
    numRows, an integer representing the number of debt records.
    numCols, an integer representing th enumber of elements in debt records. It is always 3.
    debts, a list of triplet representing debtRecord consisting of a string borrower, a string lender, and an integer amount, representing the debt record.

    Output:
    Return a list of strings representing an alphabetically ordered list of members with the smallest negative balance. If no team member has a negative balance then return a list containing the string "Nobody has a negative balance".

    Constraints: 
    1 ≤ numRows ≤ 2*10^5
    1 ≤ amount in debts ≤ 1000
    1 ≤ length of borrower and lender in debts ≤ 20

    Example: 
    borrower	lender	amount
    Alex	    Blake	2
    Blake	    Alex	2
    Casey	    Alex	5
    Blake	    Casey	7
    Alex	    Blake	4
    Alex	    Casey	4

    The first, fifth, and sixth entries decrease Alex's balance because Alex is a borrower.
    The second and third entries increase because Alex is a lender.
    So, Alex's balance is (2+5) - (2+4+4) = 7 - 10 = -3.
    
    Blake is lender in first and fifth entries and a borrower in the second and fourth entries.
    Thus, Blake's balance is (2+4) - (2+7) = 6 - 9 = -3.
    
    Casey is a borrower in the third entry and a lender in the fourth and sixth entries.
    Thus, Casey's balance is (7 + 4) - 5 = 11 - 6 = 5.
    
    Here Alex and Blake both have the balance of -3, which is the minimum amoung all members. 
*/
public class SmallestNegativeBalance {

    public void Test() {

        DebtRecord[] testDebts = new DebtRecord[] { new DebtRecord("Alex", "Blake", 2),
                new DebtRecord("Blake", "Alex", 2), new DebtRecord("Casey", "Alex", 5),
                new DebtRecord("Blake", "Casey", 7), new DebtRecord("Alex", "Blake", 4),
                new DebtRecord("Alex", "Casey", 4), };
        int numCols = 3;
        int numRows = testDebts.length;

        List<String> testResult = AmazonDebtReview(numRows, numCols, testDebts);
        System.out.println(testResult);
    }

    public class DebtRecord {

        String borrower = "";
        String lender = "";
        int amount = 0;

        public DebtRecord() {

        }

        public DebtRecord(String borrower, String lender, int amount) {
            this.borrower = borrower;
            this.lender = lender;
            this.amount = amount;
        }
    }

    /*
     * Space complexity: O(n) [hash map to store debt records] + O(n) [priority
     * queue, ordering debt records] + O(n) [result array]. Therefore ~O(n)
     * 
     * Time complexity: O(n) [inserting n elements into hash map] + O(n log n)
     * [inserting n elements into the priority queue] + O(n) [adding elements to
     * result array]. Therefore, ~O(n log n).
     */
    public List<String> AmazonDebtReview(int numRows, int numCols, DebtRecord[] debts) {

        // Hash map for storing name and their balances
        HashMap<String, Integer> nameToBalanceMap = new HashMap<String, Integer>();
        for (DebtRecord debtRecord : debts) {

            // Subtract balance for borrower
            nameToBalanceMap.putIfAbsent(debtRecord.borrower, 0);
            nameToBalanceMap.put(debtRecord.borrower, nameToBalanceMap.get(debtRecord.borrower) - debtRecord.amount);

            // Add balance for lender
            nameToBalanceMap.putIfAbsent(debtRecord.lender, 0);
            nameToBalanceMap.put(debtRecord.lender, nameToBalanceMap.get(debtRecord.lender) + debtRecord.amount);
        }

        // Create PQ to store balances in ascending order + alphabetical
        PriorityQueue<Map.Entry<String, Integer>> minBalancePriorityQueue = new PriorityQueue<Map.Entry<String, Integer>>(
                new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {

                        int result = Integer.compare(o1.getValue(), o2.getValue());
                        if (result == 0)
                            result = o1.getKey().compareTo(o2.getKey());
                        return result;
                    }
                });

        minBalancePriorityQueue.addAll(nameToBalanceMap.entrySet());
        ArrayList<String> result = new ArrayList<String>();
        int minBalance = Integer.MAX_VALUE;

        // Pop out from the queue, giving the next minimum negative balance available
        while (minBalancePriorityQueue.size() != 0) {

            Map.Entry<String, Integer> nameToBalanceValue = minBalancePriorityQueue.poll();
            if (nameToBalanceValue.getValue() < 0) {

                if (nameToBalanceValue.getValue() <= minBalance) {
                    result.add(nameToBalanceValue.getKey());
                    minBalance = nameToBalanceValue.getValue();
                } else
                    break;
            } else
                break;
        }

        if (result.size() == 0)
            result.add("Nobody has a negative balance.");

        return result;
    }
}
