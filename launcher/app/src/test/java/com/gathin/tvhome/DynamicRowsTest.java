package com.gathin.tvhome;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Plain JVM tests for auto rows_below_dock sizing. */
public final class DynamicRowsTest {
    @Test public void noAppsMeansZeroRows() {
        assertEquals(0, DynamicRows.needed(0, 4));
        assertEquals(0, DynamicRows.needed(-1, 4));
    }

    @Test public void oneFullRowNeedsOne() {
        assertEquals(1, DynamicRows.needed(1, 4));
        assertEquals(1, DynamicRows.needed(4, 4));
    }

    @Test public void spilloverOpensTheNextRow() {
        assertEquals(2, DynamicRows.needed(5, 4));
        assertEquals(3, DynamicRows.needed(9, 4));
    }

    @Test public void capsAtThreeEvenWhenMoreWouldFit() {
        assertEquals(3, DynamicRows.needed(20, 4));
        assertEquals(3, DynamicRows.needed(100, 6));
    }

    @Test public void badColumnsDoNotDivide() {
        assertEquals(0, DynamicRows.needed(8, 0));
        assertEquals(0, DynamicRows.needed(8, -2));
    }

    @Test public void updateOnlyWhenTheNeededCountDiffers() {
        assertTrue(DynamicRows.shouldUpdate(0, 1));
        assertTrue(DynamicRows.shouldUpdate(3, 2));
        assertFalse(DynamicRows.shouldUpdate(2, 2));
    }
}
