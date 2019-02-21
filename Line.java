import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Line.java
 * Models a line segment as a sorted set of points.
 *
 * @author  YOUR NAME (you@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version TODAY
 *
 */
public class Line implements Comparable<Line>, Iterable<Point> {
 
   SortedSet<Point> line;
   
   /** 
    * Creates a new line containing no points.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Line() {
      line = new TreeSet<Point>();
   }
   
   /** 
    * Creates a new line with containing all distinct collinear points in the
    * Collection c.
    */
   public Line(Collection<Point> c) {
      line = new TreeSet<Point>();
      Iterator<Point> itr = c.iterator();
      Point firstPoint = itr.next();
      Point secondPoint = itr.next();
      double slope = firstPoint.slopeTo(secondPoint);
      this.line.add(firstPoint);
      this.line.add(secondPoint);
      while (itr.hasNext()) {
         Point nextPoint = itr.next();
         if (firstPoint.slopeTo(nextPoint) == slope) {
            this.line.add(nextPoint);
         }
      }
   }
 
   /** 
    * Adds the point p to this line if p is collinear with all points already
    * in the line and p itself is not already in the line. Returns true if this
    * line is changed as a result, false otherwise.
    */
   public boolean add(Point p) {
      if (line.size() < 2) {
         line.add(p);
         return true;
      }
      else {
         double slope = line.first().slopeTo(line.last());
         if (line.first().slopeTo(p) == slope) {
            line.add(p);
            return true;
         }
         else {
            return false;
         }
      }
   }
   
   /** 
    * Returns the first (minimum) point in this line or null if this line
    * contains no points.
    */
   public Point first() {
      Iterator<Point> itr = line.iterator();
      Point min = null;
      if (itr.hasNext()) {
         min = itr.next();
      }
      while (itr.hasNext()) {
         Point candidate = itr.next();
         if (candidate.compareTo(min) < 0) {
            min = candidate;
         }
      }
      return min;

   }
   
   /** 
    * Returns the last (maximum) point in this line or null if this line
    * contains no points.
    */
   public Point last() {
      Iterator<Point> itr = line.iterator();
      Point max = null;
      if (itr.hasNext()) {
         max = itr.next();
      }
      while (itr.hasNext()) {
         Point candidate = itr.next();
         if (candidate.compareTo(max) > 0) {
            max = candidate;
         }
      }
      return max;
   }
   
   /** 
    * Returns the number of points in this line.
    */
   public int length() {
      int length = 0;
      for (Point pt : line) {
         length++;
      }
      return length;
   }

   /**
    * Compares this line with the specified line for order. Returns a negative
    * integer, zero, or a positive integer if this line is less than, equal to,
    * or greater than the specified line. Lines are ordered first by their
    * first point then by their last point. An empty line is less than any
    * non-empty line, and all empty lines are equal. All three properties of
    * compareTo as specified in the Comparable interface are met, and this
    * implementation is consistent with equals.
    */
   @Override
   public int compareTo(Line that) {
      if (this.length() == 0 && that.length() == 0) {
         return 0;
      }
      else if (this.length() == 0) {
         return -1;
      }
      else if (that.length() == 0) {
         return 1;
      }
      else {
         int result = this.first().compareTo(that.first());
         if (result != 0) {
            return this.last().compareTo(that.last());
         }
         return result;
      }
   }

   /** 
    * Provide an iterator over all the points in this line. The order in which
    * points are returned must be ascending natural order.
    */
   @Override
   public Iterator<Point> iterator() {
      Iterator<Point> it = new Iterator<Point>() {

         Iterator<Point> itr = line.iterator();

         @Override
         public boolean hasNext() {
            return itr.hasNext();
         }

         @Override
         public Point next() {
            Iterator<Point> itr2 = line.iterator();
            Point max = null;
            if (itr2.hasNext()) {
               max = itr2.next();
            }
            while (itr2.hasNext()) {
               Point candidate = itr2.next();
               if (candidate.compareTo(max) > 0) {
                  max = candidate;
               }
            }
            itr.next();
            return max;
         }


      };
      return it;
   }
   
   /** 
    * Return true if this line's first and last points are equal to the
    * parameter's first and last points. Empty lines are equal to each other
    * but are not equal to any non-empty line.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   @Override 
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (obj == this) {
         return true;
      }
      if (!(obj instanceof Line)) {
         return false;
      }
      Line that = (Line) obj;
      if ((this.length() == 0) && (that.length() == 0)) {
         return true;
      }
      if ((this.length() == 0) && (that.length() != 0)) {
         return false;
      }
      if ((this.length() != 0) && (that.length() == 0)) {
         return false;
      }
      return (this.first().equals(that.first())) && (this.last().equals(that.last()));
   }
 
   /** 
    * Return a string representation of this line.
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   @Override
   public String toString() {
      if (length() == 0) {
         return "";
      }
      StringBuilder s = new StringBuilder();
      for (Point p : line) {
         s.append(p + " -> ");
      }
      s = s.delete(s.length() - 4, s.length());
      return s.toString();
   }
 
}
