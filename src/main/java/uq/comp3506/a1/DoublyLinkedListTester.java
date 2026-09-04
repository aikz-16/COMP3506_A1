// @edu:student-assignment

package uq.comp3506.a1;

import java.util.Random;

import uq.comp3506.a1.structures.ListInterface;
import uq.comp3506.a1.structures.Oracle;

// You ARE allowed to import the Java linked list to help with your testing

/**
 * A tester for detecting incorrect behaviour in implementations of
 * {@link ListInterface} that are intended to behave as doubly linked lists.
 *
 * <p>Each method should exercise the operations named in the method's
 * documentation and report whether those checks reveal a bug. The supplied
 * {@code testList} is the implementation under test.
 *
 * @param <T> the type of elements stored in the list being tested
 */
public class DoublyLinkedListTester<T> {

    /**
     * This oracle will generate random elements of type T.
     * Use it in your tests. Call oracle.nextT() to generate a random element T.
     */
    private Oracle<T> oracle;

    /**
     * You may use this random generator to generate integers, strings, etc. Anything but the generic type T
     */
    private Random random;

    /**
     * Checks whether two values are equal, including when one or both values are null.
     *
     * @param a the first value to compare
     * @param b the second value to compare
     * @return {@code true} if both values are equal or both are {@code null};
     *         {@code false} otherwise
     */
    private boolean equals(T a, T b) {
        if (a == null && b == null) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }
        return a.equals(b);
    }

    /**
     * Construct with an oracle
     */
    public DoublyLinkedListTester(Oracle<T> oracle) {
        this.oracle = oracle;
        this.random = new Random();
    }

    /**
     * Checks whether {@link ListInterface#append(Object)}, {@link ListInterface#size()},
     * and {@link ListInterface#isEmpty()} behave incorrectly when used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsAppend(ListInterface<T> testList) {
        //1. when list has no value
        if (!testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 0) {
            return true;
        }

        T value1 = oracle.nextT();
        testList.append(value1);

        if (testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 1) {
            return true;
        }

        //2. Append 2 values inside the list

        T value2 = oracle.nextT();
        testList.append(value2);
        T value3 = oracle.nextT();
        testList.append(value3);

        if (testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 3) {
            return true;
        }

        return false;
    }

    /**
     * Checks whether {@link ListInterface#prepend(Object)}, {@link ListInterface#size()},
     * and {@link ListInterface#isEmpty()} behave incorrectly when used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsPrepend(ListInterface<T> testList) {
        //1. When the list has no value
        if (!testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 0) {
            return true;
        }

        T value1 = oracle.nextT();
        testList.prepend(value1);

        if (testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 1) {
            return true;
        }

        //2. Prepend 2 values inside the list
        T value2 = oracle.nextT();
        testList.prepend(value2);
        T value3 = oracle.nextT();
        testList.prepend(value3);

        if (testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 3) {
            return true;
        }

        return false;
    }

    /**
     * Checks whether {@link ListInterface#add(int, Object)}, {@link ListInterface#size()},
     * and {@link ListInterface#isEmpty()} behave incorrectly when used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsAdd(ListInterface<T> testList) {
        //1. When the list has no value
        if (!testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 0) {
            return true;
        }

        //2. Add a value inside the first index of the list
        T value1 = oracle.nextT();
        testList.add(0, value1);

        if (testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 1) {
            return true;
        }

        //3. Add a value at valid random index
        T value2 = oracle.nextT();
        int index2 = random.nextInt(testList.size() + 1);
        testList.add(index2, value2);

        if (testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 2) {
            return true;
        }

        //4. Test negative index
        int sizeBefore = testList.size();
        T value3 = oracle.nextT();
        try {
            testList.add(-1, value3);
            return true;

        } catch (IndexOutOfBoundsException e) {
            //Do nothing
        }
        // Failed add should NOT change size
        if (testList.size() != sizeBefore) {
            return true;
        }

        //5. Test too large index
        sizeBefore = testList.size();
        T value4 = oracle.nextT();
        try {
            testList.add(testList.size() + 1, value4);
            return true;

        } catch (IndexOutOfBoundsException e) {
            //Do nothing
        }
        // Failed add should NOT change size
        if (testList.size() != sizeBefore) {
            return true;
        }

        return false;
    }

    /**
     * Checks whether {@link ListInterface#append(Object)}, {@link ListInterface#prepend(Object)},
     * {@link ListInterface#add(int, Object)}, {@link ListInterface#get(int)},
     * {@link ListInterface#size()}, and {@link ListInterface#isEmpty()} behave incorrectly when
     * used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsAppendPrependAddGet(ListInterface<T> testList) {
        //1. When the list has no value
        if (!testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 0) {
            return true;
        }

        //2. Append one element
        T value1 = oracle.nextT();
        testList.append(value1);

        if (testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 1) {
            return true;
        }

        //3. Append another element
        T value2 = oracle.nextT();
        testList.append(value2);

        if (testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 2) {
            return true;
        }

        //4. Prepend one element
        T value3 = oracle.nextT();
        testList.prepend(value3);

        if (testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 3) {
            return true;
        }

        //5. Prepend another element
        T value4 = oracle.nextT();
        testList.prepend(value4);

        if (testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 4) {
            return true;
        }

        //6. Add a value in the middle of the list
        T value5 = oracle.nextT();
        int index1 = testList.size() / 2;
        testList.add(index1, value5);

        if (testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 5) {
            return true;
        }

        //7. Add a value at index 0
        T value6 = oracle.nextT();
        testList.add(0, value6);

        if (testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 6) {
            return true;
        }

        //8. Add a value at the end
        T value7 = oracle.nextT();
        testList.add(testList.size(), value7);

        if (testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 7) {
            return true;
        }

        //9. Check every stored element using get()
        if (!equals(testList.get(0), value6)) {
            return true;
        }
        if (!equals(testList.get(1), value4)) {
            return true;
        }
        if (!equals(testList.get(2), value3)) {
            return true;
        }
        if (!equals(testList.get(3), value5)) {
            return true;
        }
        if (!equals(testList.get(4), value1)) {
            return true;
        }
        if (!equals(testList.get(5), value2)) {
            return true;
        }
        if (!equals(testList.get(6), value7)) {
            return true;
        }

        //10. Test invalid get() indexes
        try {
            testList.get(-1);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // No bug
        }

        //11. Test invalid add() indexes
        T value8 = oracle.nextT();
        int sizeBefore = testList.size();
        try {
            testList.add(-1, value8);
            return true;

        } catch (IndexOutOfBoundsException e) {
            //Do nothing
        }

        try {
            testList.add(testList.size() + 1, value8);
            return true;

        } catch (IndexOutOfBoundsException e) {
            //Do nothing
        }

        // Failed add should NOT change size
        if (testList.size() != sizeBefore) {
            return true;
        }

        //12. Do many mixed operations
        for (int i = 0; i < 700; i++) {

            // APPEND
            T appendValue = oracle.nextT();
            int oldSize = testList.size();

            testList.append(appendValue);

            if (testList.size() != oldSize + 1) {
                return true;
            }

            if (testList.isEmpty()) {
                return true;
            }

            if (!java.util.Objects.equals(
                    testList.get(testList.size() - 1),
                    appendValue)) {
                return true;
            }


            // PREPEND
            T prependValue = oracle.nextT();
            oldSize = testList.size();

            testList.prepend(prependValue);

            if (testList.size() != oldSize + 1) {
                return true;
            }

            if (testList.isEmpty()) {
                return true;
            }

            if (!equals(
                    testList.get(0),
                    prependValue)) {
                return true;
            }


            // ADD IN THE MIDDLE
            T addValue = oracle.nextT();
            oldSize = testList.size();

            int middleIndex = oldSize / 2;

            testList.add(middleIndex, addValue);

            if (testList.size() != oldSize + 1) {
                return true;
            }

            if (testList.isEmpty()) {
                return true;
            }

            if (!java.util.Objects.equals(
                    testList.get(middleIndex),
                    addValue)) {
                return true;
         }
        }
        return false;
    }

    /**
     * Checks whether {@link ListInterface#append(Object)}, {@link ListInterface#get(int)},
     * {@link ListInterface#set(int, Object)}, {@link ListInterface#size()}, and
     * {@link ListInterface#isEmpty()} behave incorrectly when used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsAppendGetSet(ListInterface<T> testList) {
        //1. When the list has no value
        if (!testList.isEmpty()) {
            return true;
        }
        if (testList.size() != 0) {
            return true;
        }
        //2. Append one element
        T value1 = oracle.nextT();
        testList.append(value1);
        if (testList.isEmpty()) {
            return true;
        }
        if (testList.size() != 1) {
            return true;
        }

        //3. Append 2 elements
        T value2 = oracle.nextT();
        T value3 = oracle.nextT();
        testList.append(value2);
        testList.append(value3);
        if (testList.isEmpty()) {
            return true;
        }
        if (testList.size() != 3) {
            return true;
        }

        //4. Set the middle element
        T value4 = oracle.nextT();
        T oldValue = testList.set(testList.size() / 2, value4);
        if (!equals(oldValue, value2)) {
            return true;
        }
        if (!equals(testList.get(testList.size() / 2), value4)) {
            return true;
        }
        if (testList.size() != 3) {
            return true;
        }
        if (!equals(testList.get(0), value1)) {
            return true;
        }
        if (!equals(testList.get(testList.size() - 1), value3)) {
            return true;
        }

        //5. Set the first element
        T value5 = oracle.nextT();
        oldValue = testList.set(0, value5);
        if (!equals(oldValue, value1)) {
            return true;
        }
        if (!equals(testList.get(0), value5)) {
            return true;
        }
        if (testList.size() != 3) {
            return true;
        }
        if (!equals(testList.get(testList.size() - 1), value3)) {
            return true;
        }
        if (!equals(testList.get(testList.size() / 2), value4)) {
            return true;
        }

        //6. Set the last element
        T value6 = oracle.nextT();
        oldValue = testList.set(testList.size() - 1, value6);
        if (!equals(oldValue, value3)) {
            return true;
        }
        if (!equals(testList.get(testList.size() - 1), value6)) {
            return true;
        }
        if (testList.size() != 3) {
            return true;
        }
        if (!equals(testList.get(0), value5)) {
            return true;
        }
        if (!equals(testList.get(testList.size() / 2), value4)) {
            return true;
        }

        //7. Set the same index multiple times
        int index = 1;
        T value7 = oracle.nextT();
        T oldValue1 = testList.set(index, value7);
        if (!equals(oldValue1, value4)) {
            return true;
        }
        if (!equals(testList.get(index), value7)) {
            return true;
        }
        T value8 = oracle.nextT();
        T oldValue2 = testList.set(index, value8);
        if (!equals(oldValue2, value7)) {
            return true;
        }
        if (!equals(testList.get(index), value8)) {
            return true;
        }
        T value9 = oracle.nextT();
        T oldValue3 = testList.set(index, value9);
        if (!equals(oldValue3, value8)) {
            return true;
        }
        if (!equals(testList.get(index), value9)) {
            return true;
        }
        if (testList.size() != 3) {
            return true;
        }

        //8. Check all elements
        if (!equals(testList.get(0), value5)) {
            return true;
        }
        if (!equals(testList.get(1), value9)) {
            return true;
        }
        if (!equals(testList.get(2), value6)) {
            return true;
        }
        if (testList.size() != 3) {
            return true;
        }

        //9. Invalid get() indexes
        try {
            testList.get(-1);
            return true; // bug: should have thrown exception
        } catch (IndexOutOfBoundsException e) { // correct
        }
        try {
            testList.get(testList.size());
            return true;
        } catch (IndexOutOfBoundsException e) { // correct
        }
        try {
            testList.get(testList.size() + 1);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // correct
        }

        //10. Invalid set() indexes
        T value10 = oracle.nextT();
        try {
            testList.set(-1, value10);
            return true; // bug: should have thrown exception
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        try {
            testList.set(testList.size(), value10);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        try {
            testList.set(testList.size() + 1, value10);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        if (testList.size() != 3) {
            return true;
        }

        //11. Repeated append + set + get
        for (int i = 0; i < 700; i++) {
            T appendValue = oracle.nextT();
            int oldSize = testList.size();
            testList.append(appendValue);
            // append should increase size
            if (testList.size() != oldSize + 1) {
                return true;
            }
            // newest appended value should be at the last index
            int lastIndex = testList.size() - 1;
            if (!equals(testList.get(lastIndex), appendValue)) {
                return true;
            }
            // now replace that same value using set
            T newValue = oracle.nextT();
            T oldValue4 = testList.set(lastIndex, newValue);
            // set should return the value that was there before
            if (!equals(oldValue4, appendValue)) {
                return true;
            }
            // get should now return the new value
            if (!equals(testList.get(lastIndex), newValue)) {
                return true;
            }
            // set should NOT change size
            if (testList.size() != oldSize + 1) {
                return true;
            }
            if (testList.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether {@link ListInterface#append(Object)}, {@link ListInterface#get(int)},
     * {@link ListInterface#set(int, Object)}, {@link ListInterface#remove(int)},
     * {@link ListInterface#size()}, and {@link ListInterface#isEmpty()} behave incorrectly when
     * used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsAppendGetSetRemove(ListInterface<T> testList) {
        //1. When the list has no value
        if (!testList.isEmpty()) {
            return true;
        }
        if (testList.size() != 0) {
            return true;
        }
        //2. Append several element
        T value1 = oracle.nextT();
        testList.append(value1);

        if (testList.isEmpty()) {
            return true;
        }
        if (testList.size() != 1) {
            return true;
        }

        T value2 = oracle.nextT();
        T value3 = oracle.nextT();
        testList.append(value2);
        testList.append(value3);

        if (testList.isEmpty()) {
            return true;
        }
        if (testList.size() != 3) {
            return true;
        }

        //3. Set a middle element
        T value4 = oracle.nextT();
        T oldValue = testList.set(testList.size() / 2, value4);
        if (!equals(oldValue, value2)) {
            return true;
        }
        if (!equals(testList.get(testList.size() / 2), value4)) {
            return true;
        }
        if (testList.size() != 3) {
            return true;
        }
        if (!equals(testList.get(0), value1)) {
            return true;
        }
        if (!equals(testList.get(testList.size() - 1), value3)) {
            return true;
        }

        //4. Remove the first element
        T removedValue1 = testList.remove(0);
        if (!equals(removedValue1, value1)) {
            return true;
        }
        if (testList.size() != 2) {
            return true;
        }
        if (testList.isEmpty()) {
            return true;
        }
        if (!equals(testList.get(0), value4)) {
            return true;
        }
        if (!equals(testList.get(1), value3)) {
            return true;
        }

        //5. Remove middle element
        T removedValue2 = testList.remove(1);
        if (!equals(removedValue2, value3)) {
            return true;
        }
        if (testList.size() != 1) {
            return true;
        }
        if (testList.isEmpty()) {
            return true;
        }
        if (!equals(testList.get(0), value4)) {
            return true;
        }

        //6. Remove last element
        T removedValue3 = testList.remove(testList.size() - 1);
        if (!equals(removedValue3, value4)) {
            return true;
        }
        if (testList.size() != 0) {
            return true;
        }
        if (!testList.isEmpty()) {
            return true;
        }

        //7. Invalid get() indexes
        try {
            testList.get(-1);
            return true; // bug: should have thrown exception
        } catch (IndexOutOfBoundsException e) { // correct
        }
        try {
            testList.get(testList.size());
            return true;
        } catch (IndexOutOfBoundsException e) { // correct
        }
        try {
            testList.get(testList.size() + 1);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // correct
        }

        //8. Invalid set() indexes
        T value5 = oracle.nextT();
        try {
            testList.set(-1, value5);
            return true; // bug: should have thrown exception
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        try {
            testList.set(testList.size(), value5);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        try {
            testList.set(testList.size() + 1, value5);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        if (testList.size() != 0) {
            return true;
        }

        //9. Invalid remove() indexes
        T value6 = oracle.nextT();
        try {
            testList.remove(-1);
            return true; // bug: should have thrown exception
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        try {
            testList.remove(testList.size());
            return true;
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        try {
            testList.remove(testList.size() + 1);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // correct
        }
        if (testList.size() != 0) {
            return true;
        }

        //11. Repeated append + set + remove + get
        for (int i = 0; i < 500; i++) {
            int oldSize = testList.size();
            // 1. Append a value
            T appendValue = oracle.nextT();
            testList.append(appendValue);
            if (testList.size() != oldSize + 1) {
                return true;
            }
            int lastIndex = testList.size() - 1;
            // 2. Check appended value using get()
            if (!equals(testList.get(lastIndex), appendValue)) {
                return true;
            }
            // 3. Set that same index to a new value
            T setValue = oracle.nextT();
            T oldValue1 = testList.set(lastIndex, setValue);
            // 4. Check set returned old value
            if (!equals(oldValue1, appendValue)) {
                return true;
            }
            // 5. Check get() now returns new value
            if (!equals(testList.get(lastIndex), setValue)) {
                return true;
            }
            // set should not change size
            if (testList.size() != oldSize + 1) {
                return true;
            }
            // 6. Remove the same element
            T removedValue = testList.remove(lastIndex);
            // 7. Check removed value
            if (!equals(removedValue, setValue)) {
                return true;
            }
            // 8. Size should go back to old size
            if (testList.size() != oldSize) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether {@link ListInterface#append(Object)}, {@link ListInterface#get(int)},
     * {@link ListInterface#set(int, Object)}, {@link ListInterface#removeFirst(Object)},
     * {@link ListInterface#size()}, and {@link ListInterface#isEmpty()} behave incorrectly when
     * used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsAppendGetSetRemoveFirst(ListInterface<T> testList) {
        //1. When the list has no value
        if (!testList.isEmpty()) {
            return true;
        }
        if (testList.size() != 0) {
            return true;
        }
        //2. Append several element
        T value1 = oracle.nextT();
        testList.append(value1);

        if (testList.isEmpty()) {
            return true;
        }

        if (testList.size() != 1) {
            return true;
        }

        T value2 = oracle.nextT();
        T value3 = oracle.nextT();
        testList.append(value1);
        testList.append(value2);
        testList.append(value3);

        if (testList.isEmpty()) {
            return true;
        }
        if (testList.size() != 4) {
            return true;
        }

        //3. Set a middle element
        T value4 = oracle.nextT();
        T oldValue = testList.set(testList.size() / 2, value4);
        if (!equals(oldValue, value2)) {
            return true;
        }
        if (!equals(testList.get(testList.size() / 2), value4)) {
            return true;
        }
        if (testList.size() != 4) {
            return true;
        }
        if (!equals(testList.get(0), value1)) {
            return true;
        }
        if (!equals(testList.get(testList.size() - 1), value3)) {
            return true;
        }

        //4. Remove the first matching value
        boolean removed = testList.removeFirst(value1);
        if (!removed) {
            return true;
        }
        if (testList.size() != 3) {
            return true;
        }
        if (!equals(testList.get(0), value1)) {
            return true;
        }
        if (!equals(testList.get(1), value4)) {
            return true;
        }
        if (!equals(testList.get(2), value3)) {
            return true;
        }

        //5. removeFirst() on a value at the front
        boolean removedFront = testList.removeFirst(value1);
        if (!removedFront) {
            return true;
        }
        if (testList.size() != 2) {
            return true;
        }
        if (!equals(testList.get(0), value4)) {
            return true;
        }
        if (!equals(testList.get(1), value3)) {
            return true;
        }

        // 6. removeFirst() on a value at the end
        boolean removedEnd = testList.removeFirst(value3);
        if (!removedEnd) {
            return true;
        }
        if (testList.size() != 1) {
            return true;
        }
        if (!equals(testList.get(0), value4)) {
            return true;
        }

        // 7. removeFirst() when the value does not exist
        T missingValue = oracle.nextT();
        int sizeBefore = testList.size();
        boolean removedMissing = testList.removeFirst(missingValue);
        if (removedMissing) {
            return true;
        }
        if (testList.size() != sizeBefore) {
            return true;
        }
        if (!equals(testList.get(0), value4)) {
            return true;
        }

        // 8. Repeated append + set + get + removeFirst
        for (int i = 0; i < 500; i++) {
            int oldSize = testList.size();
            T appendValue = oracle.nextT();
            testList.append(appendValue);
            if (testList.size() != oldSize + 1) {
                return true;
            }
            int lastIndex = testList.size() - 1;
            if (!equals(testList.get(lastIndex), appendValue)) {
                return true;
            }
            T setValue = oracle.nextT();
            T oldValue1 = testList.set(lastIndex, setValue);
            if (!equals(oldValue1, appendValue)) {
                return true;
            }
            if (!equals(testList.get(lastIndex), setValue)) {
                return true;
            }
            if (testList.size() != oldSize + 1) {
                return true;
            }
            boolean removedAgain = testList.removeFirst(setValue);
            if (!removedAgain) {
                return true;
            }
            if (testList.size() != oldSize) {
                return true;
            }
        }

        // 9. Remove everything and check empty
        boolean removedLast = testList.removeFirst(value4);
        if (!removedLast) {
            return true;
        }
        if (testList.size() != 0) {
            return true;
        }
        if (!testList.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Checks whether the full set of list operations behaves incorrectly when used together.
     *
     * @param testList the list implementation to test
     * @return {@code true} if a bug is detected; {@code false} otherwise
     */
    public boolean hasBugsAllMethods(ListInterface<T> testList) {
        // 1. Check initial empty state
        if (!testList.isEmpty()) {
            return true;
        }
        if (testList.size() != 0) {
            return true;
        }

        // Append a single value
        T value1 = oracle.nextT();
        testList.append(value1);
        if (testList.isEmpty()) {
            return true;
        }
        if (testList.size() != 1) {
            return true;
        }

        // 2. Append values
        T value2 = oracle.nextT();
        T value3 = oracle.nextT();
        testList.append(value2);
        testList.append(value3);
        if (testList.size() != 3) {
            return true;
        }
        if (testList.isEmpty()) {
            return true;
        }
        if (!equals(testList.get(0), value1)) {
            return true;
        }
        if (!equals(testList.get(1), value2)) {
            return true;
        }
        if (!equals(testList.get(2), value3)) {
            return true;
        }

        // 3. Prepend a value
        T value4 = oracle.nextT();
        testList.prepend(value4);
        if (testList.size() != 4) {
            return true;
        }
        if (!equals(testList.get(0), value4)) {
            return true;
        }
        if (!equals(testList.get(1), value1)) {
            return true;
        }

        // 4. Add in the middle
        T value5 = oracle.nextT();
        int middleIndex = testList.size() / 2;
        testList.add(middleIndex, value5);
        if (testList.size() != 5) {
            return true;
        }
        if (!equals(testList.get(2), value5)) {
            return true;
        }

        // 5. Add at the front
        T value6 = oracle.nextT();
        testList.add(0, value6);
        if (testList.size() != 6) {
            return true;
        }
        if (!equals(testList.get(0), value6)) {
            return true;
        }

        // 6. Add at the end
        T value7 = oracle.nextT();
        testList.add(testList.size(), value7);
        if (testList.size() != 7) {
            return true;
        }
        if (!equals(testList.get(testList.size() - 1), value7)) {
            return true;
        }

        // 7. Set middle element
        T value8 = oracle.nextT();
        T oldValue = testList.set(3, value8);
        if (!equals(oldValue, value5)) {
            return true;
        }
        if (!equals(testList.get(3), value8)) {
            return true;
        }
        // set should not change size
        if (testList.size() != 7) {
            return true;
        }

        // 8. Remove first element by index
        T removed1 = testList.remove(0);
        if (!equals(removed1, value6)) {
            return true;
        }
        if (testList.size() != 6) {
            return true;
        }
        if (!equals(testList.get(0), value4)) {
            return true;
        }
        if (!equals(testList.get(1), value1)) {
            return true;
        }


        // 9. Remove middle element
        int removeMiddle = testList.size() / 2;
        T removed2 = testList.remove(removeMiddle);
        if (!equals(removed2, value2)) {
            return true;
        }
        if (testList.size() != 5) {
            return true;
        }
        if (!equals(testList.get(3), value3)) {
            return true;
        }

        // 10. Remove last element
        T removed3 = testList.remove(testList.size() - 1);
        if (!equals(removed3, value7)) {
            return true;
        }
        if (testList.size() != 4) {
            return true;
        }
        if (!equals(testList.get(testList.size() - 1), value3)) {
            return true;
        }

        // 11. Test removeFirst with duplicates
        testList.append(value1);
        boolean removedFirst = testList.removeFirst(value1);
        if (!removedFirst) {
            return true;
        }
        if (testList.size() != 4) {
            return true;
        }
        if (!equals(testList.get(0), value4)) {
            return true;
        }
        if (!equals(testList.get(1), value8)) {
            return true;
        }
        if (!equals(testList.get(2), value3)) {
            return true;
        }
        if (!equals(testList.get(3), value1)) {
            return true;
        }

        // 12. removeFirst when value does not exist
        T missingValue = oracle.nextT();
        int sizeBefore = testList.size();
        boolean removedMissing = testList.removeFirst(missingValue);
        if (removedMissing) {
            return true;
        }
        if (testList.size() != sizeBefore) {
            return true;
        }

        // 13. Invalid get indexes
        try {
            testList.get(-1);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // Correct
        }
        try {
            testList.get(testList.size());
            return true;
        } catch (IndexOutOfBoundsException e) {
            // Correct
        }

        // 14. Invalid set indexes
        T invalidValue = oracle.nextT();
        try {
            testList.set(-1, invalidValue);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // Correct
        }
        try {
            testList.set(testList.size(), invalidValue);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // Correct
        }

        // 15. Invalid add indexes
        try {
            testList.add(-1, invalidValue);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // Correct
        }
        try {
            testList.add(testList.size() + 1, invalidValue);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // Correct
        }

        // 16. Invalid remove indexes
        try {
            testList.remove(-1);
            return true;
        } catch (IndexOutOfBoundsException e) {
            // Correct
        }
        try {
            testList.remove(testList.size());
            return true;
        } catch (IndexOutOfBoundsException e) {
            // Correct
        }

        // 17. Clear
        testList.clear();
        if (testList.size() != 0) {
            return true;
        }
        if (!testList.isEmpty()) {
            return true;
        }

        // 18. Reuse list after clear
        T reuse1 = oracle.nextT();
        T reuse2 = oracle.nextT();
        T reuse3 = oracle.nextT();
        testList.append(reuse1);
        testList.prepend(reuse2);
        testList.add(1, reuse3);

        if (testList.size() != 3) {
            return true;
        }
        if (!equals(testList.get(0), reuse2)) {
            return true;
        }
        if (!equals(testList.get(1), reuse3)) {
            return true;
        }
        if (!equals(testList.get(2), reuse1)) {
            return true;
        }

        // 19. Lots of interleaved operations
        for (int i = 0; i < 500; i++) {
            // APPEND
            int oldSize = testList.size();
            T appendValue = oracle.nextT();
            testList.append(appendValue);

            if (testList.size() != oldSize + 1) {
                return true;
            }

            int lastIndex = testList.size() - 1;

            if (!equals(testList.get(lastIndex), appendValue)) {
                return true;
            }


            // SET
            T setValue = oracle.nextT();
            T previous = testList.set(lastIndex, setValue);

            if (!equals(previous, appendValue)) {
                return true;
            }
            if (!equals(testList.get(lastIndex), setValue)) {
                return true;
            }

            // PREPEND
            T prependValue = oracle.nextT();
            oldSize = testList.size();
            testList.prepend(prependValue);

            if (testList.size() != oldSize + 1) {
                return true;
            }
            if (!equals(testList.get(0), prependValue)) {
                return true;
            }

            // ADD IN MIDDLE
            T addValue = oracle.nextT();
            oldSize = testList.size();
            int middle = oldSize / 2;
            testList.add(middle, addValue);

            if (testList.size() != oldSize + 1) {
                return true;
            }
            if (!equals(testList.get(middle), addValue)) {
                return true;
            }

            // REMOVE THAT MIDDLE VALUE
            T removedMiddle = testList.remove(middle);

            if (!equals(removedMiddle, addValue)) {
                return true;
            }
            if (testList.size() != oldSize) {
                return true;
            }


            // REMOVE FIRST PREPENDED VALUE
            boolean removedPrepend = testList.removeFirst(prependValue);

            if (!removedPrepend) {
                return true;
            }
            if (testList.size() != oldSize - 1) {
                return true;
            }
            if (testList.isEmpty()) {
                return true;
            }
        }


        // 20. Final clear
        testList.clear();

        if (testList.size() != 0) {
            return true;
        }
        if (!testList.isEmpty()) {
            return true;
        }
        // No bug detected
        return false;
    }

}


