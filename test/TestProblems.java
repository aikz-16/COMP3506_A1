/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */

import uq.comp3506.a1.Interval;
import uq.comp3506.a1.Problems;
import uq.comp3506.a1.ProcessedPoints;
import uq.comp3506.a1.XorPair;

public class TestProblems {

    // The series of tests that need to be implemented
    public static void testSumOdd() {
        System.out.println("Testing 'Sum Odd Numbers'");

        long[] L = {10, 1, 7, 17};

        long result = Problems.sumOddNumbers(L);

        System.out.println("Result: " + result);
    }

    public static void testToBeXOR() {
        System.out.println("Testing 'To be XOR NOT to be'");
        long[] L = {5, 1, 7, 3};
        XorPair result = Problems.xor(L);
        System.out.println("Answer: " + result.answer());
        System.out.println("x: " + result.x());
        System.out.println("y: " + result.y());
    }

    public static void testStopStalling() {
        System.out.println("Testing 'Stop Stalling'");
        Interval[] intervals = {
                new Interval(1, 4),
                new Interval(2, 6),
                new Interval(3, 5),
                new Interval(7, 10),
                new Interval(8, 12)
        };

        long result = Problems.stalls(intervals);

        System.out.println("Result: " + result);
    }

    public static void testRivalDealer() {
        System.out.println("Testing 'Rival Dealer Revealer'");
        long[] points = {4, 1, 7, 8, 3};

        ProcessedPoints processed = Problems.rivalDealer(points);

        long result = processed.query(3, 1);

        System.out.println("Result: " + result);
    }

    // Try to call the given test based on the input
    public static void dispatch(String str) {
        switch (str.toLowerCase()) {
            case "sumodd": 
                testSumOdd();
                return;
            case "xor":
                testToBeXOR();
                return;
            case "stalls":
                testStopStalling();
                return;
            case "rival":
                testRivalDealer();
                return;
            default:
                throw new IllegalArgumentException("Unknown command: " + str);
        }
    }

    // Does what it says on the tin 
    private static void usage() {
        System.out.println("Usage: java TestProblems <commands>");
        System.out.println("Commands:");
        System.out.println("sumodd");
        System.out.println("xor");
        System.out.println("stalls");
        System.out.println("rival");
    }

    public static void main(String[] args) {
        testSumOdd();
        testToBeXOR();
        testStopStalling();
        testRivalDealer();
        // Basic checking - make sure a command is provided
        if (args.length == 0) {
            usage();
            return;
        }

        // Walk the commands and try to dispatch them
        for (int i = 0; i < args.length; ++i) {
            dispatch(args[i]);
        }

        // profit??
    }

}
