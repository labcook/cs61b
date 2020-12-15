import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOne() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('0', '1'));
        assertFalse(offByOne.equalChars('x', 'x'));
        assertTrue(offByOne.equalChars('M', 'N'));
        assertTrue(offByOne.equalChars('&', '%'));
    }
}
