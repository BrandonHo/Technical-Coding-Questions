import java.util.Comparator;
import java.util.PriorityQueue;

/*
    Multiprocessor systems at Amazon involve multiple CPUs for performing various tasks, which increases throughput and reduces response time.
    A multiprocessor system has a certain number of processors.
    Each processor has the ability to schedule a limited number of processes in one second.
    However, after each scheduling, the processor's ability is reduced to ability/2 rounded down to the nearest number.
    Only one processor can work to schedule processes each second.
    As an Amazonian you are tasked to find the minimum time required to schedule all the processes in the system given the processor's
    abilities and the number of processes.

    Write an algorithm that returns minimum time required to schedule the processes.

    Input
    - num, an integer representing the number of processors
    - ability, a list of integers representing the ability of the processors
    - processes, a long integer representing the number of processes to be scheduled.

    Output
    Return an integer representing the minimum time required to schedule the processes.

    Constraints
    1 <= num <= 10^5
    1 <= ability[i] <= 10^6
    1 <= processes <= 10^12
    0 <= i < num

    Note
    It is guaranteed that the processes can be scheduled using the given multiprocessor system.

    Example
    Input:
    num = 5
    ability = [3,1,7,2,4]
    processes = 15

    Output:
    4

    Explanation:
    This can be solved optimally in the following way:
    First, the processor with ability = 7 schedules 7 processes in one second. Now, ability = [3, 1, 3, 2, 4] because 7 was reduced to floor(7/2).
    Second, the processor with ability = 4 is used. After that, ability = [3,1,3,2,2].
    Third, the processor with ability = 3 is used. Now, ability = [1, 1, 3, 2, 2].
    Finally, the processor with ability - 1 is used to schedule the final process.
    Each step requires one second of processing time, which adds up to 4.
    So, the output is 4.
*/
public class MultiprocessorSystem {

    public void Test() {

        System.out.println(Solve(5, new int[] { 3, 1, 7, 2, 4 }, 15));
    }

    /*
     * Code assumes that the provided inputs always has a solution. Instructions
     * also did not clarify whether you could use an overpowered processor for
     * remaining processes.
     * 
     * Time Complexity: O(n log n) + O(p), where n refers to size of abilities array
     * and p refers to size of processes.
     * 
     * - O(n log n) refers to storing n abilities in the PQ (which uses a heap,
     * O[log n] for insertions).
     * 
     * - O(p) refers to the iterations for counting the minimum time required to
     * schedule the specified processes.
     * 
     * Space Complexity: O(n), where n refers to size of abilities array. Storage
     * from PQ.
     */
    public int Solve(int num, int[] abilities, int processes) {

        // Create PQ
        PriorityQueue<Integer> abilityPQ = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });

        // Add abilities to PQ
        for (int ability : abilities)
            abilityPQ.add(ability);

        int counter = 0;
        while (processes > 0) {

            // Subtract highgest ability for processes + update PQ
            int nextHighestAbility = abilityPQ.poll();
            processes -= nextHighestAbility;
            abilityPQ.add(nextHighestAbility / 2);

            // Update counter appropriately
            counter += 1;
        }

        return counter;
    }
}
