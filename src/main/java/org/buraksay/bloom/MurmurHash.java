package org.buraksay.bloom;

import com.google.common.hash.Hashing;

public class MurmurHash implements IHashAlgorithm {

    public long hash(String input) {
	return Hashing.murmur3_128().hashUnencodedChars(input).padToLong();
    }

}
