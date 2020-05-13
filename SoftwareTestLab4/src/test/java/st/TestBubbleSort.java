package st;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class TestBubbleSort {
    private int testArray[] = null;
    private int expectedAns[] = null;
    @Before
    public void setUp() {
        testArray = new int[]{1, 6, 2, 2, 5};
        expectedAns = new int[]{1, 2, 2, 5, 6};
    }
    @Test
    public void verify() {
        assertArrayEquals(expectedAns, BubbleSort.BubbleSort(testArray));
    }
}
