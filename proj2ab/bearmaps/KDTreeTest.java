package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import java.util.Random;

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
        long seed = 1;
        Random r = new Random(seed);
        for (int i=0; i < 100; i++) {
            Point tmp = new Point(r.nextDouble(), r.nextDouble());
            points.add(tmp);
        }
        KDTree kd = new KDTree(points);
        NaivePointSet nn = new NaivePointSet(points);
        Point retKD = kd.nearest(3, 4);
        Point retNN = nn.nearest(3, 4);
        assertEquals(retNN.getX(), retKD.getX(), 0.0);
        assertEquals(retNN.getY(), retKD.getY(), 0.0);
    }
}
