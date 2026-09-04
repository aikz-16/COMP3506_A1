/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2026.
 */

import uq.comp3506.a1.structures.DynamicArray;

public class TestDynamicArray {

    public static void main(String[] args) {
        DynamicArray<Integer> list = new DynamicArray<>();

        int n = 1_000_000;

        // Build the list using append
        for (int i = 0; i < n; i++) {
            list.append(i);
        }

        long start = System.nanoTime();

        list.prepend(-1);

        long end = System.nanoTime();

        double timeMs = (end - start) / 1_000_000.0;

        System.out.println("Size: " + list.size());
        System.out.println("First: " + list.get(0));
        System.out.println("Second: " + list.get(1));
        System.out.println("Last: " + list.get(list.size() - 1));
        System.out.println("One prepend took: " + timeMs + " ms");

        /*DynamicArray<Integer> list = new DynamicArray<>();

        System.out.println("=== START ===");
        printState(list);

        System.out.println("\n=== APPEND 10 ===");
        list.append(10);
        printState(list);

        System.out.println("\n=== APPEND 20 ===");
        list.append(20);
        printState(list);

        System.out.println("\n=== PREPEND 5 ===");
        list.prepend(5);
        printState(list);

        System.out.println("\n=== PREPEND 1 ===");
        list.prepend(1);
        printState(list);

        System.out.println("\n=== APPEND 30 ===");
        list.append(30);
        printState(list);

        System.out.println("\n=== CHECK GET ===");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(
                    "get(" + i + ") = " + list.get(i)
            );
        }

        System.out.println("\nExpected final list:");
        System.out.println("[1, 5, 10, 20, 30]");

        System.out.println("\n=== MANY PREPENDS ===");

        DynamicArray<Integer> bigList = new DynamicArray<>();

        for (int i = 0; i < 1000; i++) {
            bigList.prepend(i);
        }

        System.out.println("size = " + bigList.size());
        System.out.println("isEmpty = " + bigList.isEmpty());
        System.out.println("capacity = " + bigList.getCapacity());

        System.out.println("get(0) = " + bigList.get(0));
        System.out.println("Expected get(0) = 999");

        System.out.println(
                "get(last) = " + bigList.get(bigList.size() - 1)
        );
        System.out.println("Expected last = 0");

        System.out.println("\n=== DONE ===");
    }

    private static void printState(DynamicArray<Integer> list) {

        System.out.println("size = " + list.size());
        System.out.println("isEmpty = " + list.isEmpty());
        System.out.println("capacity = " + list.getCapacity());

        System.out.print("values = [");

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));

            if (i < list.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");*/
    }

}
