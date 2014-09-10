package org.buraksay.bloom;

import com.google.common.hash.Hashing;

public class CrcHash implements IHashAlgorithm {

    public long hash(String input) {
	return Hashing.crc32().hashUnencodedChars(input).padToLong();
    }

}
