package org.buraksay.bloom;

import java.util.List;

public class SimpleBloomFilter implements IBloomFilter {

    private IBitSet bitset = null;
    private List<IHashAlgorithm> hashList = null;
    
    public void insert(String input) {
	for(IHashAlgorithm hash: hashList) {
	    long hashIndex = Math.abs(hash.hash(input)) % this.bitset.getCapacity();
	    this.bitset.set(hashIndex);
	}
    }

    public boolean lookup(String input) {
	boolean found = true;
	for(IHashAlgorithm hash: hashList) {
	    long hashIndex = Math.abs(hash.hash(input)) % this.bitset.getCapacity();
	    found &= this.bitset.get(hashIndex);
	}
	return found;
    }

    public void init(long capacity,List<IHashAlgorithm> hashList) {
	if (bitset != null)
	    throw new IllegalStateException("already initialized");
	this.bitset = new QuickNDirtyBitsetImpl(capacity);
	this.hashList = hashList;
    }
    
    public String toString() {
	StringBuffer buf = new StringBuffer();
	buf.append(this.getClass().getName()).append(System.lineSeparator());
	buf.append("Filter size: "+bitset.getCapacity()).append(System.lineSeparator());
	buf.append("    ");
	for(int i = 0; i < 32; i++)
	    buf.append(' ').append(String.format("%02d", i));
	buf.append(System.lineSeparator());
	for (long i = 0; i<bitset.getCapacity(); i++) {
	    long lineMod = i % 32;
	    if(lineMod == 0)
		buf.append(String.format("%04d ",i/32l));
	    if(bitset.get(i)) 
		buf.append(" 1");
	    else
		buf.append(" 0");
	    if(lineMod == 31) 
		buf.append(System.lineSeparator());
	    else 
		buf.append(' ');
	}
	
	
	return buf.toString();
    }

}
