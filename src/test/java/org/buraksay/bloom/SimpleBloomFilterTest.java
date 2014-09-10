package org.buraksay.bloom;

import static org.junit.Assert.fail;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class SimpleBloomFilterTest {
    static InputStream dic;
    static int filterSize;
    static List<String> wordList;
    static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    @BeforeClass
    public static void setUp() throws Exception {
	try {
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    dic = classLoader.getResourceAsStream("dictionary.txt"); 
	    LineNumberReader lnr = new LineNumberReader(new InputStreamReader(dic));
	    wordList = new SortedList<String>();
	    String word;
	    while((word = lnr.readLine()) != null) {
		System.out.println("found: "+word);
		wordList.add(word);
	    }
	    System.out.println("NUMBER OF LINES:" + lnr.getLineNumber());
	    System.out.println("WORDLIST SIZE: "+wordList.size());
	    filterSize = lnr.getLineNumber();
	    System.out.println("FILTER SIZE: "+filterSize);
	    dic.close();
	    lnr.close();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @AfterClass
    public static void tearDown() throws Exception {
    }

    private String getExistingWord() {
	int randomIndex = (int)(Math.random() * (double) (wordList.size() - 1));
	return wordList.get(randomIndex);
    }
    
    private String getNonexistingWord() {
	while(true) {
	    String existingWord = getExistingWord();
	    String nonExistingWord = existingWord + '0';
	    if(wordList.contains(nonExistingWord)) 
		continue;
	    return nonExistingWord;
	}
    }
    
    private char getRandomChar() {
	int randomIndex = (int)(Math.random() * (double) (alphabet.length - 1));
	return alphabet[randomIndex];
    }
    
    @Test
    public void testInsert() {
	IBloomFilter bloomFilter = BloomFactory.createBloomFilter(filterSize);
	for (String word : wordList) {
	    bloomFilter.insert(word);
	}
    }

    @Test
    public void testLookup() {
	IBloomFilter bloomFilter = BloomFactory.createBloomFilter(filterSize);
	long startTime = System.nanoTime();
	for (String word : wordList) {
	    bloomFilter.insert(word);
	}
	long estimatedRuntime = System.nanoTime() - startTime;
	
	int totalNegativeTests = 0;
	int totalFalsePositiveResults = 0;
		
	for(int i = 0; i<10000;i++,totalNegativeTests++) {
	    String testWord = getExistingWord();
	    boolean possibleHit = bloomFilter.lookup(testWord);
	    assertTrue("Failed to catch existing word "+testWord,possibleHit);
	    
	    testWord = getNonexistingWord();
	    possibleHit = bloomFilter.lookup(testWord);
	    if(possibleHit) {
		totalFalsePositiveResults++;
	    }
	}
	System.out.println("Bloom Filter state: "+System.lineSeparator()+bloomFilter);
	System.out.println("Insertion of dictionary took " + estimatedRuntime +"ns");
	System.out.println(String.format("False positives: %d out of %d", totalFalsePositiveResults, totalNegativeTests));
	
    }

    @Test
    public void testInit() {
	List<IHashAlgorithm> hashList = new ArrayList<IHashAlgorithm>(2);
	hashList.add(new MurmurHash());
	hashList.add(new GoodFastHash());
	SimpleBloomFilter simpleBloomFilter = new SimpleBloomFilter();
	simpleBloomFilter.init(100, hashList);
	try {
	    simpleBloomFilter.init(100, hashList);
	    fail("Should have thrown exception");
	} catch (IllegalStateException e) {
	}
    }

}
