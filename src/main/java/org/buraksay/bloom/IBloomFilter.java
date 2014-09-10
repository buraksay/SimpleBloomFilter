package org.buraksay.bloom;

import java.util.List;

public interface IBloomFilter {
    void insert(String input);
    boolean lookup(String input);
    void init(long capacity, List<IHashAlgorithm> hashList);
}
