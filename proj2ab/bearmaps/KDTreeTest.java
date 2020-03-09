package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import edu.princeton.cs.algs4.StdRandom;

public class KDTreeTest {
    @Test
    public void test1() {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree nn = new KDTree(List.of(p1, p2, p3));
        Point ret = nn.nearest(3, 4);
        assertEquals( 3.3,  ret.getX(), 0.0);
        assertEquals(4.4, ret.getY(), 0.0);
    }

    @Test
    public void testRandom() {
        ArrayList<Point> points = new ArrayList<>();
        int N = 1000000;

        for (int i=0; i < N; i++) {
            Point tmp = new Point(StdRandom.uniform(N), StdRandom.uniform(N));
            points.add(tmp);
        }
        Point target = new Point(StdRandom.uniform(N), StdRandom.uniform(N));
        // kDTree
        KDTree kd = new KDTree(points);
        long startTime1 = System.currentTimeMillis();
        Point retKD = kd.nearest(target.getX(), target.getY());
        long endTime1 = System.currentTimeMillis();
        long kdTime = endTime1 - startTime1;

        //NaivePointSet
        NaivePointSet nn = new NaivePointSet(points);
        long startTime2 = System.currentTimeMillis();
        Point retNN = nn.nearest(target.getX(), target.getY());
        long endTime2 = System.currentTimeMillis();
        long nnTime = endTime2 - startTime2;

        assertEquals(retNN.getX(), retKD.getX(), 0.0);
        assertEquals(retNN.getY(), retKD.getY(), 0.0);
        System.out.println(kdTime);
        System.out.println(nnTime);
    }
}
