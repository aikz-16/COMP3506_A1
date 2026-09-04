/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2026.
 */

import uq.comp3506.a1.structures.GenomeEditor;

public class TestGenomeEditor {

    public static void main(String[] args) {
        /*System.out.println("Testing GenomeEditor Class...");

        GenomeEditor genome = new GenomeEditor();

        // Test 1: empty genome
        if (genome.length() != 0) {
            throw new RuntimeException("Test 1 failed: empty genome length should be 0");
        }

        // Test 2: insert into empty genome
        genome.insert(0, "ACGT");

        if (!genome.toString().equals("ACGT")) {
            throw new RuntimeException(
                    "Test 2 failed: expected ACGT, got " + genome.toString()
            );
        }

        if (genome.length() != 4) {
            throw new RuntimeException("Test 2 failed: length should be 4");
        }

        // Test 3: charAt
        if (genome.charAt(0) != 'A') {
            throw new RuntimeException("Test 3 failed at index 0");
        }

        if (genome.charAt(2) != 'G') {
            throw new RuntimeException("Test 3 failed at index 2");
        }

        // Test 4: insert in middle
        genome.insert(2, "TT");

        if (!genome.toString().equals("ACTTGT")) {
            throw new RuntimeException(
                    "Test 4 failed: expected ACTTGT, got " + genome.toString()
            );
        }

        // Test 5: insert at beginning
        genome.insert(0, "GG");

        if (!genome.toString().equals("GGACTTGT")) {
            throw new RuntimeException(
                    "Test 5 failed: expected GGACTTGT, got " + genome.toString()
            );
        }

        // Test 6: insert at end
        genome.insert(genome.length(), "AA");

        if (!genome.toString().equals("GGACTTGTAA")) {
            throw new RuntimeException(
                    "Test 6 failed: expected GGACTTGTAA, got " + genome.toString()
            );
        }

        // Test 7: mutate
        genome.mutate(2, 'T');

        if (!genome.toString().equals("GGTCTTGTAA")) {
            throw new RuntimeException(
                    "Test 7 failed: expected GGTCTTGTAA, got " + genome.toString()
            );
        }

        // Test 8: substring
        String sub = genome.substring(2, 6);

        if (!sub.equals("TCTT")) {
            throw new RuntimeException(
                    "Test 8 failed: expected TCTT, got " + sub
            );
        }

        // Test 9: delete
        String deleted = genome.delete(2, 3);

        if (!deleted.equals("TCT")) {
            throw new RuntimeException(
                    "Test 9 failed: expected deleted TCT, got " + deleted
            );
        }

        if (!genome.toString().equals("GGTGTAA")) {
            throw new RuntimeException(
                    "Test 9 failed: expected remaining GGTGTAA, got "
                            + genome.toString()
            );
        }

        // Test 10: delete nothing
        String emptyDelete = genome.delete(2, 0);

        if (!emptyDelete.equals("")) {
            throw new RuntimeException("Test 10 failed: deleting 0 bases should return empty string");
        }

        // Test 11: invalid charAt
        try {
            genome.charAt(-1);
            throw new RuntimeException("Test 11 failed: expected exception");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }

        try {
            genome.charAt(genome.length());
            throw new RuntimeException("Test 11 failed: expected exception");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }

        // Test 12: invalid mutation base
        try {
            genome.mutate(0, 'X');
            throw new RuntimeException("Test 12 failed: expected exception");
        } catch (IllegalArgumentException e) {
            // correct
        }

        // Test 13: invalid insert position
        try {
            genome.insert(genome.length() + 1, "A");
            throw new RuntimeException("Test 13 failed: expected exception");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }

        // Test 14: invalid delete
        try {
            genome.delete(0, genome.length() + 1);
            throw new RuntimeException("Test 14 failed: expected exception");
        } catch (IndexOutOfBoundsException e) {
            // correct
        }

        System.out.println("Final genome: " + genome);
        System.out.println("All GenomeEditor tests passed!");
        // Performance Test 1: many appends
        GenomeEditor perf1 = new GenomeEditor();

        long start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            perf1.insert(perf1.length(), "A");
        }

        long end = System.currentTimeMillis();

        System.out.println(
                "100000 appends: " + (end - start) + " ms"
        );

        // Performance Test 2: many prepends
        GenomeEditor perf2 = new GenomeEditor();

        start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            perf2.insert(0, "C");
        }

        end = System.currentTimeMillis();

        System.out.println(
                "100000 prepends: " + (end - start) + " ms"
        );

        // Performance Test 3: many middle inserts
        GenomeEditor perf3 = new GenomeEditor();

        perf3.insert(0, "A");

        start = System.currentTimeMillis();

        for (int i = 0; i < 50000; i++) {
            perf3.insert(perf3.length() / 2, "G");
        }

        end = System.currentTimeMillis();

        System.out.println(
                "50000 middle inserts: " + (end - start) + " ms"
        );

        // Performance Test 4: charAt
        GenomeEditor perf4 = new GenomeEditor();

        for (int i = 0; i < 100000; i++) {
            perf4.insert(perf4.length(), "A");
        }

        start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            perf4.charAt(i % perf4.length());
        }

        end = System.currentTimeMillis();

        System.out.println(
                "100000 charAt calls: " + (end - start) + " ms"
        );

        // Performance Test 5: mutate
        start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            perf4.mutate(i % perf4.length(), 'T');
        }

        end = System.currentTimeMillis();

        System.out.println(
                "100000 mutate calls: " + (end - start) + " ms"
        );

        // Performance Test 6: large fragment
        GenomeEditor perf5 = new GenomeEditor();

        StringBuilder big = new StringBuilder();

        for (int i = 0; i < 100000; i++) {
            big.append("ACGT".charAt(i % 4));
        }

        start = System.currentTimeMillis();

        perf5.insert(0, big.toString());

        end = System.currentTimeMillis();

        System.out.println(
                "Insert 100000-base fragment: "
                        + (end - start) + " ms"
        );

        System.out.println("Performance tests finished!");*/
        GenomeEditor big = new GenomeEditor();

        long start = System.currentTimeMillis();

        for (int i = 0; i < 1_000_000; i++) {
            big.insert(big.length(), "A");
        }

        long end = System.currentTimeMillis();

        System.out.println("1M appends: " + (end - start) + " ms");
        System.out.println("Length: " + big.length());

        start = System.currentTimeMillis();

        String result = big.toString();

        end = System.currentTimeMillis();

        System.out.println("1M toString: " + (end - start) + " ms");
        System.out.println("String length: " + result.length());
    }
}
