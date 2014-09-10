package org.buraksay.bloom;

import com.google.common.hash.Hashing;

public class AdlerHash implements IHashAlgorithm {

    public long hash(String input) {
	return Hashing.adler32().hashUnencodedChars(input).padToLong();
    }

}
