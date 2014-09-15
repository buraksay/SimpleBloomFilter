package org.buraksay.bloom;

import static org.junit.Assert.*;

import org.junit.Test;

public class BitMaskTest {

    @Test
    public void testSet() {
	int[] expectedValues = { 0x0001, 0x0002, 0x0004, 0x0008, 0x00010, 0x00020, 0x00040, 0x00080, 0x000100, 0x000200, 0x000400, 0x000800, 0x0001000,
		0x0002000, 0x0004000, 0x0008000 };
	int ordinal = 0;
	for (int expectedValue : expectedValues) {
	    int value = Math.abs(ProperBitSetImpl.BitMask.set((short) 0, (short) ordinal));
	    assertEquals(expectedValue, value);
	    boolean isset = ProperBitSetImpl.BitMask.isSet((short)value, (short)ordinal++);
	    assertTrue(isset);
	}
    }
}
