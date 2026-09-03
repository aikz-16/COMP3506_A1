// @edu:student-assignment

package uq.comp3506.a1;

/**
 * Represents a data structure for storing and processing a set of points, 
 */
public class ProcessedPoints {
    private long[] points;

    public ProcessedPoints(long[] points) {
        this.points = points;
    }

    /**
     * Determines the number of points that lie within a specified distance from a given point.
     *
     * @param x The reference point (center) for the query.
     * @param r The maximum distance (radius) to search around the reference point.
     * @return The number of points that are within distance {@code r} of point {@code x}.
     */
    public long query(long x, long r) {
        long point1 = x - r;
        long point2 = x + r;
        int low = 0;
        int high = points.length;

        while (low < high) {
            int mid = (low + high) / 2;
            if (points[mid] < point1) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        int firstLocation = low;

        low = 0;
        high = points.length;

        while (low < high) {
            int mid = (low + high) / 2;
            if (points[mid] <= point2) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        int afterLastLocation = low;

        return afterLastLocation - firstLocation;
    }
}

