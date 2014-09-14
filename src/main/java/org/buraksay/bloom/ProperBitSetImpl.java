package org.buraksay.bloom;

import java.util.HashMap;
import java.util.Map;

public class ProperBitSetImpl implements IBitSet {

    static class BitMask {
	private static Map<Short,Short> masks = new HashMap<>();
	static {
	    short mask = (short)1;
	    for(short i=0;i<16;i++) {
		masks.put(i, mask);
		mask <<= 1;
	    }
	}
	
	static boolean isSet(short bitArray, short bitOrdinal) {
	    return Math.abs(masks.get((short)bitOrdinal).shortValue() & bitArray) > 0;
	}
	
	static short set(short bitArray, short bitOrdinal) {
	    return (short)(masks.get((short)bitOrdinal).shortValue() | bitArray);
	}
    };

    
    private short[] model;
    
    public long getCapacity() {
	// TODO Auto-generated method stub
	return 0;
    }

    public boolean get(long index) {
	// TODO Auto-generated method stub
	return false;
    }

    public void set(long index) {
	// TODO Auto-generated method stub

    }

    public void clear(long index) {
	// TODO Auto-generated method stub

    }

    public void clear() {
	// TODO Auto-generated method stub

    }

}
