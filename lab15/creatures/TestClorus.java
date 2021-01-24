package creatures;

import huglife.Action;
import huglife.Direction;
import huglife.Impassible;
import huglife.Occupant;
import huglife.Empty;
import org.junit.Test;

import java.awt.Color;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        Plip p = new Plip(1);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.stay();
        assertEquals(1.96, c.energy(), 0.01);
        c.attack(p);
        assertEquals(2.96, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        Clorus son = c.replicate();
        assertNotSame(c, son);
        assertEquals(1, c.energy(), 0.01);
        assertEquals(1, son.energy(), 0.01);
    }

    @Test
    public void testChoose() {
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Plip());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        //You can create new empties with new Empty();
        //Despite what the spec says, you cannot test for Cloruses nearby yet.
        //Sorry!

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

        surrounded.put(Direction.LEFT, new Empty());
        Action actual2 = c.chooseAction(surrounded);
        Action expected2 = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);
        assertEquals(expected2, actual2);

        surrounded.put(Direction.BOTTOM, new Impassible());
        Action actual3 = c.chooseAction(surrounded);
        Action expected3 = new Action(Action.ActionType.REPLICATE, Direction.LEFT);
        assertEquals(expected3, actual3);

        Clorus cc = new Clorus(0.2);
        HashMap<Direction, Occupant> surrounded2 = new HashMap<Direction, Occupant>();
        surrounded2.put(Direction.TOP, new Impassible());
        surrounded2.put(Direction.BOTTOM, new Empty());
        surrounded2.put(Direction.LEFT, new Impassible());
        surrounded2.put(Direction.RIGHT, new Impassible());
        Action actual4 = cc.chooseAction(surrounded2);
        Action expected4 = new Action(Action.ActionType.MOVE, Direction.BOTTOM);
        assertEquals(expected4, actual4);

    }
}
