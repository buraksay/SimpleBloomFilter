package org.buraksay.bloom;

import java.util.ArrayList;
import java.util.List;

public class BloomFactory {

    public static IBloomFilter createBloomFilter(long initialCapacity) {
	List<IHashAlgorithm> hashList = new ArrayList<IHashAlgorithm>(2);
	hashList.add(new MurmurHash());
	hashList.add(new GoodFastHash());
	hashList.add(new CrcHash());
	hashList.add(new AdlerHash());
	
	SimpleBloomFilter simpleBloomFilter = new SimpleBloomFilter();
	simpleBloomFilter.init(initialCapacity, hashList);
	return (IBloomFilter) simpleBloomFilter;
    }
}
