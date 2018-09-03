package jpl.ch01.ex01;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class L1_1Test {

    @Test
    void main() {
        assertEquals(L1_1.makeMessage("world"), ("Hello, world"));
    }
}