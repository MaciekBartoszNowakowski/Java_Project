package org.example.Logic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorldDirectionTest {
    @Test
    void testRight() {
        assertEquals(WorldDirection.WEST, WorldDirection.NORTH.right());
        assertEquals(WorldDirection.SOUTH, WorldDirection.WEST.right());
        assertEquals(WorldDirection.EAST, WorldDirection.SOUTH.right());
        assertEquals(WorldDirection.NORTH, WorldDirection.EAST.right());
    }

    @Test
    void testAcross() {
        assertEquals(WorldDirection.SOUTH, WorldDirection.NORTH.across());
        assertEquals(WorldDirection.EAST, WorldDirection.WEST.across());
        assertEquals(WorldDirection.NORTH, WorldDirection.SOUTH.across());
        assertEquals(WorldDirection.WEST, WorldDirection.EAST.across());
    }

    @Test
    void testLeft() {
        assertEquals(WorldDirection.EAST, WorldDirection.NORTH.left());
        assertEquals(WorldDirection.NORTH, WorldDirection.WEST.left());
        assertEquals(WorldDirection.WEST, WorldDirection.SOUTH.left());
        assertEquals(WorldDirection.SOUTH, WorldDirection.EAST.left());
    }
}