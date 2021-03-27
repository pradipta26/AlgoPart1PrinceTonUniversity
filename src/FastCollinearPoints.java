import java.util.List;

public class FastCollinearPoints {
    private int numberOfSegments = 0;
    private List<Point[]> segmentList;
    public FastCollinearPoints(Point[] points) {   // finds all line segments containing 4 or more points
    }
    public           int numberOfSegments() {       // the number of line segments
        return 0;
    }
    public LineSegment[] segments() {                // the line segments
        LineSegment[] lineSegment = new LineSegment[segmentList.size()];
        int index = 0;
        for (Point[] segment : segmentList) {
            lineSegment[index++] = new LineSegment(segment[0], segment[1]);
        }
        return lineSegment;
    }
}
