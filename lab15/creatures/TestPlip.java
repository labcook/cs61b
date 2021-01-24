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

/**
 * Tests the plip class
 *
 * @authr FIXME
 */

public class TestPlip {

    /* Replace with the magic word given in lab.
     * If you are submitting early, just put in "early" */
    public static final String MAGIC_WORD = "early";

    @Test
    public void testBasics() {
        Plip p = new Plip(2);
        assertEquals(2, p.energy(), 0.01);
        assertEquals(new Color(99, 255, 76), p.color());
        p.move();
        assertEquals(1.85, p.energy(), 0.01);
        p.move();
        assertEquals(1.70, p.energy(), 0.01);
        p.stay();
        assertEquals(1.90, p.energy(), 0.01);
        p.stay();
        assertEquals(2.00, p.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Plip p = new Plip(2);
        Plip son = p.replicate();
        assertNotSame(p, son);
        assertEquals(1, p.energy(), 0.01);
        assertEquals(1, son.energy(), 0.01);
    }

    @Test
    public void testChoose() {
        Plip p = new Plip(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        //You can create new empties with new Empty();
        //Despite what the spec says, you cannot test for Cloruses nearby yet.
        //Sorry!  

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

        surrounded.put(Direction.BOTTOM, new Empty());
        Action actual2 = p.chooseAction(surrounded);
        Action expected2 = new Action(Action.ActionType.REPLICATE, Direction.BOTTOM);
        assertEquals(expected2, actual2);

        Plip pp = new Plip(0.8);
        HashMap<Direction, Occupant> surrounded2 = new HashMap<Direction, Occupant>();
        surrounded2.put(Direction.TOP, new Impassible());
        surrounded2.put(Direction.LEFT, new Impassible());
        surrounded2.put(Direction.RIGHT, new Impassible());
        surrounded2.put(Direction.BOTTOM, new Empty());
        Action actual3 = pp.chooseAction(surrounded2);
        Action expected3 = new Action(Action.ActionType.STAY);
        assertEquals(expected3, actual3);

        //test with nearby clorus

        Plip ppp = new Plip(0.8);
        HashMap<Direction, Occupant> surrounded3 = new HashMap<Direction, Occupant>();
        surrounded3.put(Direction.TOP, new Clorus());
        surrounded3.put(Direction.BOTTOM, new Clorus());
        surrounded3.put(Direction.LEFT, new Clorus());
        surrounded3.put(Direction.RIGHT, new Clorus());

        Action actual4 = ppp.chooseAction(surrounded3);
        Action expected4 = new Action(Action.ActionType.STAY);
        assertEquals(expected4, actual4);

    }

    public static void main(String[] args) {
        System.exit(jh61b.junit.textui.runClasses(TestPlip.class));
    }
} 
