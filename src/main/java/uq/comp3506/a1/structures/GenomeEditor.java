// @edu:student-assignment

package uq.comp3506.a1.structures;


// This is part of COMP3506 Assignment 1. Students must implement their own solutions.


/**
 * Maintains a mutable genome sequence containing only the bases {@code A},
 * {@code T}, {@code C}, and {@code G}.
 *
 * <p> For full marks, {@link #length()}, {@link #charAt(int)}, {@link #insert(int, String)},
 * {@link #delete(int, int)}, and {@link #mutate(int, char)} should run faster
 * than {@code O(N)} (amortized) for a genome of length {@code N}.
 * {@link #substring(int, int)} and {@link #toString()} should run in
 * {@code O(N)} in the worst case.
 * As a general approach, you might like to implement a more simple ``everything is
 * O(N)'' approach and then optimize it further if you can.
 * 
 * <p> Your implementation should expect that {@link #length()}, {@link #charAt(int)}, {@link #insert(int, String)},
 * {@link #delete(int, int)}, and {@link #mutate(int, char)} will be called in arbitrary order
 * approximately the same number of times.
 */
public final class GenomeEditor {
    /**
     * Root of the genome treap.
     */
    private GenomeNode root;

    /**
     * State used to generate priorities for new treap nodes.
     */
    private int randomState;

    /**
     * Stores the result of splitting a treap into two parts.
     */
    private static class SplitResult {
        private GenomeNode left;
        private GenomeNode right;

        SplitResult(GenomeNode left, GenomeNode right) {
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Creates an empty genome editor.
     */
    public GenomeEditor() {
        // Do whatever you need to initialise the editor
        root = null;
        randomState = 123456789;
    }

    /**
     * Returns the length of the subtree rooted at the given node.
     *
     * @param node the subtree root
     * @return the number of nodes in the subtree
     */
    private int nodeLength(GenomeNode node) {
        if (node == null) {
            return 0;
        }

        return node.getLength();
    }

    /**
     * Recalculates the subtree length stored in a node.
     *
     * @param node the node to update
     */
    private void updateLength(GenomeNode node) {
        if (node != null) {
            int leftLength = nodeLength(node.getLeft());
            int rightLength = nodeLength(node.getRight());

            node.setLength(1 + leftLength + rightLength);
        }

    }

    /**
     * Generates the next priority for a treap node.
     *
     * @return a pseudo-random priority
     */
    private int nextPriority() {
        randomState ^= randomState << 13;
        randomState ^= randomState >>> 17;
        randomState ^= randomState << 5;

        return randomState;
    }

    /**
     * Checks whether a character is a valid DNA base.
     *
     * @param base the character to check
     * @return {@code true} if the base is A, C, G, or T
     */
    private boolean isValidBase(char base) {
        return base == 'A' || base == 'C' || base == 'G' || base == 'T';
    }

    /**
     * Merges two treaps while preserving their genome order.
     *
     * @param left the left treap
     * @param right the right treap
     * @return the root of the merged treap
     */
    private GenomeNode merge(GenomeNode left, GenomeNode right) {
        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        if (left.getPriority() > right.getPriority()) {

            left.setRight(
                    merge(left.getRight(), right)
            );

            updateLength(left);

            return left;
        }

        right.setLeft(
                merge(left, right.getLeft())
        );

        updateLength(right);

        return right;
    }

    /**
     * Splits a treap at the specified position.
     *
     * <p>The left result contains the first {@code position} bases,
     * while the right result contains the remaining bases.
     *
     * @param node the treap to split
     * @param position the number of bases to place in the left result
     * @return the two resulting treaps
     */
    private SplitResult split(GenomeNode node, int position) {
        if (node == null) {
            return new SplitResult(null, null);
        }

        int leftLength = nodeLength(node.getLeft());

        if (position <= leftLength) {

            SplitResult result =
                    split(node.getLeft(), position);

            node.setLeft(result.right);
            updateLength(node);

            return new SplitResult(
                    result.left,
                    node
            );
        }

        SplitResult result =
                split(
                        node.getRight(),
                        position - leftLength - 1
                );

        node.setRight(result.left);
        updateLength(node);

        return new SplitResult(
                node,
                result.right
        );
    }

    /**
     * Builds a treap containing all bases in the supplied fragment.
     *
     * @param fragment the genome fragment
     * @return the root of the new treap
     */
    private GenomeNode buildFragment(String fragment) {
        GenomeNode fragmentRoot = null;

        for (int i = 0; i < fragment.length(); i++) {
            char base = fragment.charAt(i);

            if (!isValidBase(base)) {
                throw new IllegalArgumentException();
            }

            GenomeNode newNode =
                    new GenomeNode(base, nextPriority());

            fragmentRoot = merge(fragmentRoot, newNode);
        }

        return fragmentRoot;
    }

    /**
     * Finds the node containing the base at the specified position.
     *
     * @param node the current subtree
     * @param index the index relative to the current subtree
     * @return the node at the specified index
     */
    private GenomeNode findNode(GenomeNode node, int index) {
        GenomeNode current = node;
        int currentIndex = index;

        while (current != null) {

            int leftLength =
                    nodeLength(current.getLeft());

            if (currentIndex < leftLength) {

                current = current.getLeft();

            } else if (currentIndex == leftLength) {

                return current;

            } else {

                currentIndex =
                        currentIndex - leftLength - 1;

                current = current.getRight();
            }
        }

        return null;
    }

    /**
     * Adds all bases in the subtree to the StringBuilder in genome order.
     *
     * @param node the current subtree root
     * @param builder the StringBuilder receiving the bases
     */
    private void appendToString(
            GenomeNode node,
            StringBuilder builder) {

        if (node == null) {
            return;
        }

        appendToString(node.getLeft(), builder);

        builder.append(node.getBase());

        appendToString(node.getRight(), builder);
    }

    /**
     * Returns the current length of the genome.
     *
     * @return the number of bases in the genome
     */
    public int length() {
        return nodeLength(root);
    }

    /**
     * Returns the base at the specified zero-based index.
     *
     * @param index the index of the base; must satisfy
     *              {@code 0 <= index < length()}
     * @return the base at {@code index}
     * @throws IndexOutOfBoundsException if {@code index} is outside the genome
     */
    public char charAt(int index) {
        if (index < 0 || index >= length()) {
            throw new IndexOutOfBoundsException();
        }

        GenomeNode node = findNode(root, index);

        return node.getBase();
    }

    /**
     * Appends the bases within the requested index range to the provided
     * {@link StringBuilder}.
     *
     * <p>The tree is traversed in genome order, and only nodes whose indexes fall
     * within the half-open interval {@code [left, right)} are added.
     *
     * @param node the root of the current subtree
     * @param subtreeStart the global index of the first base in this subtree
     * @param left the inclusive start index of the requested substring
     * @param right the exclusive end index of the requested substring
     * @param builder the {@code StringBuilder} used to store the resulting bases
     */
    private void appendSubstring(GenomeNode node, int subtreeStart, int left, int right, StringBuilder builder) {
        if (node == null) {
            return;
        }

        int leftLength = nodeLength(node.getLeft());
        int nodeIndex = subtreeStart + leftLength;

        // Only visit the left subtree if it overlaps the requested range
        if (left < nodeIndex) {
            appendSubstring(
                    node.getLeft(),
                    subtreeStart,
                    left,
                    right,
                    builder
            );
        }

        // Add this node if its index is inside [left, right)
        if (nodeIndex >= left && nodeIndex < right) {
            builder.append(node.getBase());
        }

        // Only visit the right subtree if it overlaps the requested range
        if (nodeIndex + 1 < right) {
            appendSubstring(
                    node.getRight(),
                    nodeIndex + 1,
                    left,
                    right,
                    builder
            );
        }
    }

    /**
     * Returns the genome subsequence in the half-open interval
     * {@code [left, right)}.
     *
     * <p>For example, {@code substring(2, 6)} returns {@code "GTTG"} for the
     * genome {@code "ACGTTGCA"}.
     *
     * @param left the inclusive start index
     * @param right the exclusive end index
     * @return the requested genome subsequence
     * @throws IndexOutOfBoundsException if the range does not satisfy
     *         {@code 0 <= left <= right <= length()}
     */
    public String substring(int left, int right) {
        if (left < 0 || right < left || right > length()) {
            throw new IndexOutOfBoundsException();
        }

        StringBuilder builder = new StringBuilder(right - left);

        appendSubstring(root, 0, left, right, builder);

        return builder.toString();
    }

    /**
     * Inserts a genome fragment immediately before the specified position.
     * Position {@code 0} inserts at the beginning, while {@code length()}
     * appends to the genome.
     *
     * <p>For example, inserting {@code "TT"} at position {@code 2} in
     * {@code "ACGT"} produces {@code "ACTTGT"}.
     *
     * @param position the insertion position; must satisfy
     *                 {@code 0 <= position <= length()}
     * @param fragment the fragment to insert
     * @throws IndexOutOfBoundsException if {@code position} is invalid
     */
    public void insert(int position, String fragment) {
        if (position < 0 || position > length()) {
            throw new IndexOutOfBoundsException();
        }

        if (fragment.length() == 0) {
            return;
        }

        GenomeNode newFragment =
                buildFragment(fragment);

        SplitResult result =
                split(root, position);

        root = merge(
                merge(result.left, newFragment),
                result.right
        );

    }

    /**
     * Deletes a sequence of bases starting at the specified position.
     *
     * <p>For example, deleting {@code 3} bases at position {@code 2} from
     * {@code "ACGTTGCA"} returns {@code "GTT"} and leaves {@code "ACGCA"}.
     *
     * @param position the index of the first base to delete
     * @param length the number of bases to delete
     * @return the deleted sequence
     * @throws IndexOutOfBoundsException if {@code position < 0},
     *         {@code length < 0}, or {@code position + length} exceeds the
     *         current genome length
     */
    public String delete(int position, int length) {
        if (position < 0 || length < 0 || position > length() || length > length() - position) {
            throw new IndexOutOfBoundsException();
        }

        if (length == 0) {
            return "";
        }

        SplitResult first = split(root, position);

        SplitResult second = split(first.right, length);

        StringBuilder deleted = new StringBuilder(length);

        appendToString(second.left, deleted);

        root = merge(
                first.left,
                second.right
        );

        return deleted.toString();
    }

    /**
     * Replaces the base at the specified position.
     *
     * <p>For example, replacing the base at position {@code 1} in
     * {@code "ACGT"} with {@code 'T'} produces {@code "ATGT"}.
     *
     * @param position the index of the base to replace
     * @param newBase the replacement base; one of {@code A}, {@code C},
     *                {@code G}, or {@code T}
     * @throws IndexOutOfBoundsException if {@code position} is outside the
     *         genome
     * @throws IllegalArgumentException if {@code newBase} is invalid
     */
    public void mutate(int position, char newBase) {
        if (position < 0 || position >= length()) {
            throw new IndexOutOfBoundsException();
        }

        if (!isValidBase(newBase)) {
            throw new IllegalArgumentException();
        }

        GenomeNode node =
                findNode(root, position);

        node.setBase(newBase);

    }

    /**
     * Returns the current genome sequence.
     *
     * @return the genome as a string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(length());

        appendToString(root, builder);

        return builder.toString();
    }
}
