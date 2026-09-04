// @edu:student-assignment

package uq.comp3506.a1.structures;


// This is part of COMP3506 Assignment 1. Students must implement their own solutions.

/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2026.
 * <p>
 * NOTE: You should go and carefully read the documentation provided in the
 * ListInterface.java file - this explains some of the required functionality.
 */
public class DynamicArray<T extends Comparable<T>> implements ListInterface<T> {

    /**
     * size tracks the total number of slots being used in the data array
     */
    private int size = 0;

    /**
     * capacity tracks the total number of slots (used or unused) in the data array
     */
    private int capacity = 0;

    /**
     * data stores the raw objects
     */
    private T[] data;

    private void resize() {
        int newCapacity;
        if (data.length == 0) {
            newCapacity = 1;
        } else {
            newCapacity = data.length * 2;
        }

        T[] newData = (T[]) new Comparable[newCapacity];

        System.arraycopy(data, 0, newData, 0, size);

        data = newData;

    }

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
     * Constructs an empty Dynamic Array
     */
    public DynamicArray() {
        this.data = (T[])new Comparable[capacity];
        this.size = 0;
        // XXX todo
        // Confused about how to resize? Check the Ed lessons...
    }

    // See ListInterface
    @Override
    public int size() {
        return size;
    }

    // See ListInterface
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Has the size reached the current capacity?
     * Return true if so, false otherwise.
     * This is merely a convenience function for you. We will not be
     * testing it explicitly.
     */
    public boolean isFull() {
        return size == data.length;
    }

    /**
     * Get current capacity.
     * Again, this is merely a convenience function for you. We will not
     * be testing it explicitly.
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * Add an element to the end of the array. Returns true if successful,
     * false otherwise. [See the note in the ListInterface class about when
     * false would be returned.]
     * Time complexity for full marks: O(1*)
     * That is, O(1) *amortized*.
     */
    @Override
    public boolean append(T element) {
        if (size == data.length) {
            resize();
        }
        data[size] = element;
        size += 1;

        return true;
    }


    /**
     * Add an element to the beginning of the array. Returns true if successful,
     * false otherwise. 
     * Time complexity for full marks: O(N)
     * See: "add" below for more information
     */
    @Override
    public boolean prepend(T element) {
        if (size == data.length) {
            resize();
        }

        System.arraycopy(data, 0, data, 1, size);

        data[0] = element;
        size++;

        return true;
    }

    /**
     * Add element to index ix.
     * Note: This does not overwrite the element at index ix - that is what
     * the set() method is for, see below. Instead, this function is similar
     * to append or prepend, but it adds the element at a desired index.
     * If ix is out of bounds, throw an IndexOutOfBoundsException.
     * Acceptable bounds are [0, size] where 0 will be prepend, size will
     * be append, and anything in between will need to shuffle elements around.
     * Time complexity for full marks: O(N)
     */
    @Override
    public boolean add(int ix, T element) {
        if (ix < 0 || ix > size) {
            throw new IndexOutOfBoundsException();
        }

        if (size == data.length) {
            resize();
        }

        System.arraycopy(data, ix, data, ix + 1, size - ix);

        data[ix] = element;
        size += 1;

        return true;
    }

    /**
     * Return the element at index ix.
     * If ix is out of bounds, throw an IndexOutOfBoundsException.
     * Time complexity for full marks: O(1)
     */
    @Override
    public T get(int ix) {
        if (ix < 0 || ix >= size) {
            throw new IndexOutOfBoundsException();
        }
        return data[ix];
    }

    /**
     * Overwrite the "old" value at ix with element, and return the old value.
     * If ix is out of bounds, throw an IndexOutOfBoundsException.
     * Time complexity for full marks: O(1)
     */
    @Override
    public T set(int ix, T element) {
        if (ix < 0 || ix >= size) {
            throw new IndexOutOfBoundsException();
        }
        T oldValue = data[ix];
        data[ix] = element;
        return oldValue;
    }

    /**
     * Remove and return the value at index ix
     * If ix is out of bounds, throw an IndexOutOfBoundsException.
     * Time complexity for full marks: O(N)
     */
    @Override
    public T remove(int ix) {
        if (ix < 0 || ix >= size) {
            throw new IndexOutOfBoundsException();
        }
        final T value = data[ix];
        System.arraycopy(data, ix + 1, data, ix, size - ix - 1);
        size -= 1;
        data[size] = null;

        return value;
    }

    /**
     * Find and remove the first value in the array that equals t (the one
     * with the smallest index).
     * Return true if successful, false otherwise.
     * Time complexity for full marks: O(N)
     */
    @Override
    public boolean removeFirst(T t) {
        int ix = -1;
        for (int i = 0; i < size; i++) {
            if (equals(data[i], t)) {
                ix = i;
                break;
            }
        }

        if (ix < 0) {
            return false;
        }

        System.arraycopy(data, ix + 1, data, ix, size - ix - 1);

        size -= 1;
        data[size] = null;
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;

    }

    /**
     * Sort all of the elements inside the array.
     * <p>
     * Time complexity for full marks: O(NlogN).
     * That is, we expect you to implement a sorting algorithm that runs in
     * "n log n" time. This may be in expectation, or guaranteed worst case.
     * <p>
     * A note on comparisons:
     * <p>
     * You may assume that any type stored inside the DynamicArray already
     * implements Comparable<T> which means you can just use compareTo()
     * in order to sort elements.
     * <p>
     * We will assume sorting in ascending, so you will want to do something
     * like: if (data[i].compareTo(data[j]) < 0) { // data[i] < data[j] }
     */
    public void sort() {
        if (size > 1) {
            quickSort(0, size - 1);
        }

    }

    private void quickSort(int low, int high) {
        int i = low;
        int j = high;
        T pivot = data[(low + high) / 2];

        while (i <= j) {
            while (data[i].compareTo(pivot) < 0) {
                i++;
            }

            while (data[j].compareTo(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                T temp = data[i];
                data[i] = data[j];
                data[j] = temp;

                i++;
                j--;
            }
        }

        if (low < j) {
            quickSort(low, j);
        }
        if (i < high) {
            quickSort(i, high);
        }
    }


}
