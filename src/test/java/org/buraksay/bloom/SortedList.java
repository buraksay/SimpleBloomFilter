package org.buraksay.bloom;

import java.util.ArrayList;
import java.util.Collections;

public class SortedList<T extends Comparable<? super T>> extends ArrayList<T> {

    private static final long serialVersionUID = 1L;
    
    @Override
    public boolean add(T e) {
	int index = Collections.binarySearch(this, e);
	if(index >= 0)
	    return false;
	else {
	    super.add(-(index+1), e);
	    return true;
	}
    }

}
