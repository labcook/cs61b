import org.junit.
import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOne() {
        assertTrue(offByOne.equalChars('x', 'x'));
        assertTrue(offByOne.equalChars('0', '0'));
        assertFalse(offByOne.equalChars('x', 'y'));
        assertFalse(offByOne.equalChars('x', 'X'));
        assertFalse(offByOne.equalChars('1', '2'));
    }
}
