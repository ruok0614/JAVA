package jpl.ch01.ex03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class L1_3Test {

    @Test
    public void test(){

        int[] expected = new int[]{0,1,1,2,3,5,8,13,21,34,55};

        for (int i = 0 ; i < expected.length ; i++) {
            //assertEquals(期待される値、実際の値)
            assertEquals(expected[i],L1_3.fibonacci(i));
        }
    }


}