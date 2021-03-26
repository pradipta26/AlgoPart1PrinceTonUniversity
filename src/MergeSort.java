import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MergeSort<T> {
    public static LinkedList<Point> sort(LinkedList<Point> input) {
        sort(input, 0, input.size() - 1);
        return input;
    }
    private static void sort(LinkedList<Point> input, int startIndex, int endIndex) {
        int midIndex = (startIndex + endIndex) / 2;
        if (input.getNode(midIndex) == input.getNode(endIndex)|| input.size() == 0)
            return;
        sort(input,  startIndex, midIndex);
        sort(input,  midIndex + 1, endIndex);
        merge(input, startIndex, midIndex, endIndex);
        return;
    }
    private static void merge(LinkedList<Point> input, int startIndex, int midIndex, int endIndex) {
        int tempMidIndex = midIndex + 1;
        int tempStartIndex = startIndex;

        LinkedList<Point> tempList = new LinkedList<>();
        while (tempStartIndex <= midIndex && tempMidIndex <= endIndex) {
            if (input.getNodeValue(tempStartIndex).compareTo(input.getNodeValue(tempMidIndex)) <= 0)
                tempList.insert(input.getNodeValue(tempStartIndex++));
            else if (input.getNodeValue(tempStartIndex).compareTo(input.getNodeValue(tempMidIndex)) >= 0)
                tempList.insert(input.getNodeValue(tempMidIndex++));
            while (tempStartIndex > midIndex && tempMidIndex <= endIndex)
                tempList.insert(input.getNodeValue(tempMidIndex++));
            while (tempMidIndex > endIndex && tempStartIndex <= midIndex)
                tempList.insert(input.getNodeValue(tempStartIndex++));
        }
        if (tempList.size() != endIndex - startIndex + 1)
            throw new IllegalStateException("temp List size = " + tempList.size() + " is not equal to " + (endIndex - startIndex));
        else {
            tempStartIndex = startIndex;
            int tempEndIndex = endIndex - startIndex;
            while ( tempStartIndex <= endIndex && tempEndIndex >= 0) {
                input.updateNodeValue(tempStartIndex, tempList.getNodeValue(tempEndIndex--));
                tempStartIndex++;
            }
        }
        return;
    }
    public static void main(String[] args) {
        /*int x;
        int y;
        int arraySize = 2;
        int arrayCounter = 0;
        Point[] pointArray = new Point[arraySize];
        while (!StdIn.isEmpty()) {
            x = StdIn.readInt();
            y = StdIn.readInt();
            if( x <0 || y < 0)
                throw new IllegalArgumentException("invalid coordinate - Out of Range!!!");
            Point point = new Point(x, y);
            pointArray[arrayCounter++] = point;
            if (arrayCounter == arraySize) {
                Point[] tempPointArray = new Point[arraySize * 2];
                for (int i = 0; i < arraySize; i++)
                    tempPointArray[i] = pointArray[i];
                pointArray = tempPointArray;
                arraySize *= 2;
            }
        }
        int actualSize = 0;
        for (int i = 0; i < pointArray.length && pointArray[i] != null; i++)
            actualSize++;
        StdOut.println("Actual Array Size = " + actualSize);
        Point[] tempArray = new Point[actualSize];
        for (int i = 0; i < actualSize; i++)
            tempArray[i] = pointArray[i];
        StdOut.println("Array Size = " + pointArray.length + " tempArray size = " + tempArray.length +"\n");
        Point[] sortedArray = MergeSort.sort(tempArray);
        StdOut.println("sortedArray Size = " + sortedArray.length + " Elements : \n");
        for(int i = 0; i < sortedArray.length; i++)
            StdOut.print(sortedArray[i] + " -> ");

         */
        int x;
        int y;
        LinkedList<Point> pointList = new LinkedList<>();
        while (!StdIn.isEmpty()) {
            x = StdIn.readInt();
            y = StdIn.readInt();
            if (x < 0 || y < 0)
                throw new IllegalArgumentException("invalid coordinate - Out of Range!!!");
            pointList.insert(new Point(x, y));
        }
        pointList.print();
        MergeSort.sort(pointList).print();

    }
}
