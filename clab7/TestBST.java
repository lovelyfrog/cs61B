import static org.junit.Assert.*;
import org.junit.Test;

public class TestBST {

    @Test
    public void optimalIPLTest() {
        assertEquals(0, ExperimentHelper.optimalIPL(1));
        assertEquals(1, ExperimentHelper.optimalIPL(2));
        assertEquals(13, ExperimentHelper.optimalIPL(8));
    }
}
