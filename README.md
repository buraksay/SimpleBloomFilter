SimpleBloomFilter
=================

Proof of concept implementation of a Bloom Filter using hash algorithms in Guava's Hashing library.

Currently bit set is implemented as a very space-inefficient boolean array. Planning to convert to
long array and use bitmasks to access each bucket.

