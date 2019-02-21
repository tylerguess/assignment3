import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

class Main {


            public static void main(String[] args) throws FileNotFoundException {

                Point pt = new Point(17, 17);
                Point pt2 = new Point(20, 19);
                Point pt3 = new Point(30, 18);
                Point pt4 = new Point(25, 40);
                Line ln1 = new Line();
                ln1.add(pt);
                ln1.add(pt2);
                Line ln2 = new Line();
                ln2.add(pt3);
                ln2.add(pt4);

                Collection<Point> c = new ArrayList<Point>();
                c.add(pt2);
                c.add(pt3);
                Line ln3 = new Line(c);
                Line ln4 = new Line();
                System.out.println(ln4.first());

                Iterator<Point> itr = ln2.iterator();
                while (itr.hasNext()) {
                    itr.next();
                    System.out.println("Success");
                }

                Extractor ext = new Extractor("data");
                System.out.println(ext.getLinesBrute());


            }
}