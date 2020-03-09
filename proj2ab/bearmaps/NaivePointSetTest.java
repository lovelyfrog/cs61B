package bearmaps;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class NaivePointSetTest {

    @Test
    public void test() {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3, 4);
        assertTrue( 3.3 == ret.getX());
        assertTrue(4.4 == ret.getY());

    }
}
