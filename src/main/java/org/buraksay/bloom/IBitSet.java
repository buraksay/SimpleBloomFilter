package org.buraksay.bloom;


public interface IBitSet {
    
    public long getCapacity();
    /**
     * Returns true or false for the specified bit index. The index should be
     * less than the capacity.
     */
    public boolean get(long index);

    /**
     * Sets the bit at the specified index. The index should be less than the
     * capacity.
     */
    public void set(long index);

    /**
     * clears the bit. The index should be less than the capacity.
     */
    public void clear(long index);

    public void clear();
}
