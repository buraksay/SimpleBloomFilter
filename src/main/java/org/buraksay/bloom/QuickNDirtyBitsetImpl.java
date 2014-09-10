package org.buraksay.bloom;


public class QuickNDirtyBitsetImpl implements IBitSet {
    
    private boolean[] model;
    
    QuickNDirtyBitsetImpl(long capacity) {
	if(capacity > Math.abs((long)Integer.MAX_VALUE)) 
	    throw new IllegalArgumentException("largest size supported is "+Integer.MAX_VALUE);

	model = new boolean[(int)capacity];
    }

    public boolean get(long index) {
	return model[(int)index];
    }

    public void set(long index) {
	if(index >=0 && index < model.length)
	    model[(int)index] = true;
	else
	    throw new IllegalArgumentException("index was out of bounds: "+index);
    }

    public void clear(long index) {
	model[(int)index] = false;
    }

    public void clear() {
	for (int i = 0; i < model.length; i++) {
	    model[i] = false;
	}
    }

    public long getCapacity() {
	// TODO Auto-generated method stub
	return model.length;
    }

}
