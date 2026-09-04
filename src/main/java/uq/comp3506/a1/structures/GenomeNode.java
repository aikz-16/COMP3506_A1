package uq.comp3506.a1.structures;

/**
 * Represents a node in the genome tree structure.
 *
 * <p>Each node stores a single DNA base and references to its left and right
 * child nodes. The node also stores the total number of bases in the subtree
 * rooted at this node and a priority value used to help maintain the treap
 * structure.
 *
 * <p>The subtree length is updated whenever the structure of the tree changes.
 * The priority is used when merging or splitting nodes so that the tree remains
 * approximately balanced.
 */
public class GenomeNode {
    private char base;
    private GenomeNode left;
    private GenomeNode right;

    private int length;
    private int priority;

    /**
     * Constructs a new genome node containing the specified DNA base and priority.
     *
     * @param base the DNA base stored in this node
     * @param priority the priority used to maintain the treap structure
     */
    public GenomeNode(char base, int priority) {
        this.base = base;
        this.priority = priority;
        this.length = 1;
        this.left = null;
        this.right = null;
    }

    /**
     * Returns the DNA base stored in this node.
     *
     * @return the DNA base stored in this node
     */
    public char getBase() {
        return base;
    }

    /**
     * Updates the DNA base stored in this node.
     *
     * @param base the new DNA base
     */
    public void setBase(char base) {
        this.base = base;
    }

    /**
     * Returns the left child of this node.
     *
     * @return the left child, or {@code null} if there is no left child
     */
    public GenomeNode getLeft() {
        return left;
    }

    /**
     * Updates the left child of this node.
     *
     * @param left the new left child
     */
    public void setLeft(GenomeNode left) {
        this.left = left;
    }

    /**
     * Returns the right child of this node.
     *
     * @return the right child, or {@code null} if there is no right child
     */
    public GenomeNode getRight() {
        return right;
    }

    /**
     * Updates the right child of this node.
     *
     * @param right the new right child
     */
    public void setRight(GenomeNode right) {
        this.right = right;
    }

    /**
     * Returns the number of genome bases contained in the subtree rooted at this node.
     *
     * @return the subtree length
     */
    public int getLength() {
        return length;
    }

    /**
     * Updates the number of genome bases contained in the subtree rooted at this node.
     *
     * @param length the new subtree length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Returns the priority of this node.
     *
     * <p>The priority is used to help maintain the balanced structure of the treap.
     *
     * @return the priority of this node
     */
    public int getPriority() {
        return priority;
    }
}
