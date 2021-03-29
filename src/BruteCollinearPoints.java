import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BruteCollinearPoints {
    private int numberOfSegments = 0;
    private final List<Point[]> segmentList;

    public BruteCollinearPoints(final Point[] points) {    // finds all line segments containing 4 points
        validateInput(points);
        Arrays.sort(points);
        segmentList = new LinkedList<>();
        for (int i = 0; i < points.length - 3; i++) {
            boolean status = false;
            for (int j = i+1; j < points.length - 2; j++) {
                double baseSlope = points[i].slopeTo(points[j]);
                for (int k = j+1; k < points.length - 1; k++) {
                   boolean multiplePointFound = false;
                    if (baseSlope == points[j].slopeTo(points[k])) {
                        for (int l = k + 1; l < points.length; l++) {
                            if (baseSlope == points[k].slopeTo(points[l])) {
                                if (multiplePointFound)
                                    throw new IllegalArgumentException("5 or more collinear points found");
                                Point[] segment = new Point[2];
                                segment[0] = points[i];
                                segment[1] = points[l];
                                multiplePointFound = true;
                                segmentList.add(segment);
                                numberOfSegments++;
                                status = true;
                            }
                        }
                        if (status)
                            break;
                    }
                }
            }
        }
    }

    public int numberOfSegments() {      // the number of line segments
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        LineSegment[] lineSegment = new LineSegment[segmentList.size()];
        int index = 0;
        for (Point[] segment : segmentList) {
            if (segment != null && segment[0] != null && segment[1] != null)
                lineSegment[index++] = new LineSegment(segment[0], segment[1]);
        }
        return lineSegment;
    }
    private void validateInput(Point[] pointsArray) {
        if (pointsArray == null)
            throw new IllegalArgumentException("Input is null");
        for (Point point : pointsArray) {
            if (point == null)
                throw new IllegalArgumentException("Input is null");
        }
        for (int i = 0; i < pointsArray.length; i++) {
            for (int j = i + 1; j < pointsArray.length; j++)
                if (pointsArray[i].compareTo(pointsArray[j]) == 0)
                    throw new IllegalArgumentException("Points are repeated in input");
        }
    }

    public static void main(String[] args) {
       // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        StdOut.println(collinear.numberOfSegments());
    }
}
