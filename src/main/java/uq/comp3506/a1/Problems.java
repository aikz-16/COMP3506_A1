// @edu:student-assignment

package uq.comp3506.a1;


// This is part of COMP3506 Assignment 1. Students must implement their own solutions.

import java.util.Arrays;

/**
 * The class containing all problem stubs. Refer to the spec for formal definitions and explanations.
 */
public class Problems {
    /**
     * Problem 1: Return the sum of the missing odd numbers in the range of interest.
     *
     * <p>Expected test sizes:
     * <ul>
     *   <li>Basic: up to {@code n = 10,000} numbers, with generated values up to
     *       {@code 10,000}.</li>
     *   <li>Exhaustive: {@code n = 10,000} numbers, with generated values up to
     *       {@code 1,000,000}.</li>
     *   <li>Welcome to COMP3506: {@code n = 5,000,000} numbers, with generated values up to
     *       {@code 1,000,000,000}.</li>
     * </ul>
     */
    public static long sumOddNumbers(long[] L) {
        long min = L[0];
        long max = L[0];
        long existingOddSums = 0;

        for (int i = 1; i < L.length; i++){
            if (L[i] < min){
                min = L[i];
            } else if (L[i] > max){
                max = L[i];
            }
            if (L[i] % 2 != 0){
                existingOddSums += L[i];
            }
        }

        long numOfOdds = (max + 1) / 2;
        long sumOfOdds = numOfOdds * numOfOdds;

        return sumOfOdds - existingOddSums;
    }

    /**
     * Problem 2: Find the pair of distinct numbers that minimizes their XOR; return them and their XOR'd value.
     *
     * <p>Expected test sizes:
     * <ul>
     *   <li>Basic: {@code n = 1,000} numbers.</li>
     *   <li>Exhaustive: {@code n = 10,000} numbers.</li>
     *   <li>Welcome to COMP3506: {@code n = 1,000,000} numbers.</li>
     * </ul>
     */
    public static XorPair xor(long[] L) {
        Arrays.sort(L);
        long smallest_XOR = L[0] ^ L[1];
        long x = L[0];
        long y = L[1];

        for (int i = 0; i < L.length - 1; i++){
            long XOR = L[i] ^ L[i + 1];
            if (XOR < smallest_XOR){
                smallest_XOR = XOR;
                x = L[i];
                y = L[i + 1];
            }
        }

        return new XorPair(smallest_XOR, x, y);
    }
    
    /**
     * Problem 3: Find and return the maximum number of vendors serving any single point.
     *
     * <p>Expected test sizes:
     * <ul>
     *   <li>Basic: up to {@code n = 5,000} intervals.</li>
     *   <li>Exhaustive: up to {@code n = 250,000} intervals.</li>
     *   <li>Welcome to COMP3506: {@code n = 2,000,000} intervals.</li>
     * </ul>
     */
    public static long stalls(Interval[] intervals) {
        long active_vendors = 0;
        long max_active_vendors = 0;
        int n = intervals.length;
        long[] starts = new long[n];
        long[] ends = new long[n];
        int i = 0;
        int j = 0;

        for (int k = 0; k < n; k++){
            starts[k] = intervals[k].start();
            ends[k] = intervals[k].end();
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        while (i < n && j < n){
            if (starts[i] <= ends[j]){
                active_vendors++;
                i++;
                if (active_vendors > max_active_vendors){
                    max_active_vendors = active_vendors;
                }
            } else {
                active_vendors--;
                j++;
            }

        }
        return max_active_vendors;
    }

    /**
     * Problem 4: Return a ProcessedPoints object that can answer arbitrary RivalDealer queries.
     *
     * <p>Expected test sizes, where {@code n} is the number of points and {@code q} is the
     * number of queries made against the returned object:
     * <ul>
     *   <li>Basic: {@code n = 1,000}, {@code q = 2,000}.</li>
     *   <li>Exhaustive: up to {@code n = 250,000}, {@code q = 250,000}.</li>
     *   <li>Welcome to COMP3506: {@code n = 5,000,000}, up to {@code q = 3,000,000}.</li>
     * </ul>
     */
    public static ProcessedPoints rivalDealer(long[] points) {
        Arrays.sort(points);
        return new ProcessedPoints(points);
    }

}
