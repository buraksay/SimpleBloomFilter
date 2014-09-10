package org.buraksay.bloom;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuickNDirtyBitsetImplTest {

    QuickNDirtyBitsetImpl impl;
    
    @After
    public void tearDown() throws Exception {
	impl = null;
    }

    @Test
    public void testConstr() throws RuntimeException {
	try {
	    impl = new QuickNDirtyBitsetImpl(Math.abs((long)Integer.MAX_VALUE) + 1);
		assertFalse("shouldnt have hit here", true);
	} catch (RuntimeException re) {
		assertFalse(false);
	}
    }
    
    @Test
    public void testGetSet() {
	impl = new QuickNDirtyBitsetImpl(100);
	for(long i=0;i<100;i++) {
	    assertFalse(impl.get(i));
	    impl.set(i);
	    assertTrue(impl.get(i));
	}
     }

    @Test
    public void testClearLong() {
	impl = new QuickNDirtyBitsetImpl(100);
	for(long i=0;i<100;i++) {
	    assertFalse(impl.get(i));
	    impl.set(i);
	    assertTrue(impl.get(i));
	    impl.clear(i);
	    assertFalse(impl.get(i));
	}
    }

    @Test
    public void testClear() {
	impl = new QuickNDirtyBitsetImpl(100);
	for(long i=0;i<100;i++) {
	    impl.set(i);
	}
	impl.clear();
	impl = new QuickNDirtyBitsetImpl(100);
	for(long i=0;i<100;i++) {
	    assertFalse(impl.get(i));
	}
    }

    @Test
    public void testGetCapacity() {
	impl = new QuickNDirtyBitsetImpl(100);
	assertEquals(100l, impl.getCapacity());
    }

}
