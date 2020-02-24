package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

/** Tests the Clorus class
 *  @authr lovelyfrog
 */


public class TestClorus {
    @Test
    public void testChoose() {
        //No empty squares, STAY;
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action expected = new Action(Action.ActionType.STAY);
        Action actual = c.chooseAction(surrounded);

        assertEquals(expected, actual);
        //Otherwise, any Plips are seen, ATTACK;
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topPlip = new HashMap<Direction, Occupant>();
        topPlip.put(Direction.TOP, new Plip());
        topPlip.put(Direction.BOTTOM, new Empty());
        topPlip.put(Direction.LEFT, new Impassible());
        topPlip.put(Direction.RIGHT, new Impassible());

        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);
        actual = c.chooseAction(topPlip);

        assertEquals(expected, actual);

        //Otherwise, energy >= 1, RELICATE;
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);
        actual = c.chooseAction(topEmpty);

        assertEquals(expected, actual);

        //Otherwise, Move to a random empty square;
        c = new Clorus(0.99);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        Action unexpected = new Action(Action.ActionType.STAY);
        actual = c.chooseAction(allEmpty);

        assertNotEquals(unexpected, actual);

    }

}
