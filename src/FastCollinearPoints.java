import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {
    private int numberOfSegments = 0;
    private final List<Point[]> segmentList;

    public FastCollinearPoints(final Point[] inputPoints) {
        validateInput(inputPoints);
        Arrays.sort(inputPoints);
        segmentList = new LinkedList<>();
        for (int i = 0; i < inputPoints.length - 3; i++) {
            int index = 0;
            double[] slopeArray = new double[inputPoints.length - 1];
            for (int j = 0; j < inputPoints.length; j++) {
                if (j != i)
                    slopeArray[index++] = inputPoints[i].slopeTo(inputPoints[j]);
            }
            Arrays.sort(slopeArray);
            for (int j = 0; j < slopeArray.length; j++) {
                int slopeCounter = 0;
                for (int k = j+1; k < slopeArray.length; k++) {
                    if (slopeArray[j] == slopeArray[k])
                        slopeCounter++;
                    else break;
                }
                if (slopeCounter >= 2) {
                    Point startPoint = inputPoints[i];
                    Point endPoint = inputPoints[i];
                    for (Point point : inputPoints) {
                        if (slopeArray[j] == inputPoints[i].slopeTo(point)) {
                            if (startPoint.compareTo(point) > 0)
                                startPoint = point;
                            else if (endPoint.compareTo(point) < 0)
                                endPoint = point;
                        }
                    }
                    boolean isSegmentExist = false;
                    for (Point[] segment : segmentList) {
                        if (segment[0].compareTo(startPoint) == 0 && segment[1].compareTo(endPoint) == 0) {
                            isSegmentExist = true;
                            break;
                        }
                    }
                    if (!isSegmentExist) {
                        Point[] segment = {startPoint,endPoint};
                        segmentList.add(segment);
                        numberOfSegments++;
                    }
                }

            }
        }
    }

    public int numberOfSegments() {       // the number of line segments
        return numberOfSegments;
    }

    public LineSegment[] segments() {                // the line segments
        LineSegment[] lineSegment = new LineSegment[segmentList.size()];
        int index = 0;
        for (Point[] segment : segmentList) {
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        StdOut.println(collinear.numberOfSegments());
    }
}
