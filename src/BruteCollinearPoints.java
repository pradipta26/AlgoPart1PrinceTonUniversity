import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BruteCollinearPoints {
    private int numberOfSegments = 0;
    private List<Point[]> segmentList;
    public BruteCollinearPoints(Point[] points) {    // finds all line segments containing 4 points
        Arrays.sort(points);
        //this.points = points;
        segmentList = new LinkedList<>();
        for (int i = 0; i < points.length - 3; i++) {
            boolean status = false;
            List<Double> slopeList = new LinkedList<>();
            for (int j = i+1; j < points.length - 2; j++) {
                double baseSlope = points[i].slopeTo(points[j]);
                for (int k = j+1; k < points.length - 1 && !slopeList.contains(baseSlope); k++) {

                    double innerSlope = points[j].slopeTo(points[k]);
                    boolean slopeFound = false;
                    if (baseSlope == innerSlope){
                        slopeList.add(baseSlope);
                        for (int index = 0; index < i && !slopeFound; index++) {
                            //if (points[j].slopeOrder().compare(points[index], points[k]) == 0)
                            if (points[index].slopeTo(points[j]) == innerSlope)
                                slopeFound = true;
                        }
                        if (!slopeFound) {
                            Point[] segment = new Point[2];
                            for (int l = k+1; l < points.length; l++) {
                               if (baseSlope == points[k].slopeTo(points[l])) {
                                   segment[0] = points[i];
                                   segment[1] = points[l];
                                   status = true;
                               }
                            }
                            if (status == true) {
                                segmentList.add(segment);
                                StdOut.println("Line Segment Added : " + segment[0] + " " + segment[1]);
                                numberOfSegments++;
                                break;
                            }
                        }
                    }
                    if (status == true)
                        break;
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
            lineSegment[index++] = new LineSegment(segment[0], segment[1]);
        }
        return lineSegment;
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
    }
}
