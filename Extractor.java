import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  YOUR NAME (you@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version TODAY
 *
 */
public class Extractor {
   
   /** raw data: all (x,y) points from source data. */
   private Point[] points;
   
   /** lines identified from raw data. */
   private SortedSet<Line> lines;
  
   /**
    * Builds an extractor based on the points in the file named by filename. 
    */
   public Extractor(String filename) throws FileNotFoundException {
      File file = new File(filename);
      Scanner scanFile = new Scanner(file);
      int numPoints = Integer.parseInt(scanFile.nextLine());
      points = new Point[numPoints];
      for (int i = 1; i <= numPoints; i++) {
         Point point = new Point(Integer.parseInt(scanFile.next()), Integer.parseInt(scanFile.next()));
         points[i - 1] = point;
      }
   }
  
   /**
    * Builds an extractor based on the points in the Collection named by pcoll. 
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesBrute() {
      lines = new TreeSet<Line>();
      for (int a = 0; a < points.length; a++) {
         for (int b = 0; b < points.length; b++) {
            for (int c = 0; c < points.length; c++) {
               for (int d = 0; d < points.length; d++) {
                  if (a != b && a != c && a != d && b != c && b != d && c != d) {
                     if (points[a].slopeTo(points[b]) == points[c].slopeTo(points[d])) {
                        Line line = new Line();
                        line.add(points[a]);
                        line.add(points[b]);
                        line.add(points[c]);
                        line.add(points[d]);
                        lines.add(line);
                     }
                  }
               }
            }
         }
      }
      return lines;
   }
  
   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesFast() {
      lines = new TreeSet<Line>();
      return lines;
   }
   
}
