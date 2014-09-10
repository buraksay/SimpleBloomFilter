package org.buraksay.bloom;

import com.google.common.hash.Hashing;

public class GoodFastHash implements IHashAlgorithm {

    public long hash(String input) {
	return Hashing.goodFastHash(64).hashUnencodedChars(input).padToLong();

    }

}
