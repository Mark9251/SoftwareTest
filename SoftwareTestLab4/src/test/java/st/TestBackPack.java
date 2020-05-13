package st;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBackPack {
    private int m;
    private int n;
    private int[] w = null;
    private int[] p = null;
    private int[][] expectedArray = null;

    @Before
    public void setUp() {
        m = 10;
        n = 3;
        w = new int[]{3, 4, 5};
        p = new int[]{4, 5, 6};
        expectedArray = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
                {0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4,},
                {0, 0, 0, 4, 5, 5, 5, 9, 9, 9, 9,},
                {0, 0, 0, 4, 5, 6, 6, 9, 10, 11, 11,}};
    }

    @Test
    public void verify() {
        assertArrayEquals(expectedArray, BackPack.BackPack_Solution(m, n, w, p));
    }
}
