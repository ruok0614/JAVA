package jpl.ch01.ex08;

import jpl.ch01.text.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class L1_8Test {
    Point p = new Point();

    {
        //　初期化ブロック
        p.setPoint(12,55);
    }

    @Test
    void testx(){
        assertEquals(p.x,12);
    }

    @Test
    void testy(){
        assertEquals(p.y,55);
    }

}