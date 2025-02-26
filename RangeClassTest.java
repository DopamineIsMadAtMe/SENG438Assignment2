package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    private Range exampleRange;
    private Range CompRange;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { exampleRange = new Range(-1, 1);
    }

    //.equals() section

    @Test
    public void ShouldEqualtest() {
    	exampleRange = new Range(1, 1);
    	CompRange = new Range(1, 1);
        assertTrue("Comparing 1,1 to 1,1 should equal",
        exampleRange.equals(CompRange));
    }
    
    @Test
    public void LowerBoundSmall() {
    	exampleRange = new Range(1, 1);
    	CompRange = new Range(0, 1);
    	assertFalse("Comparing 1,1 to 0,1 should not equal", 
    	exampleRange.equals(CompRange));
    }
    
    @Test
    public void LowerBoundBig() {
    	CompRange = new Range(2, 2);
    	exampleRange = new Range(1, 2);
    	assertFalse("Comparing 1,2 to 2,2 should not equal",
    			exampleRange.equals(CompRange));
    }
    @Test
    public void HigherBoundSmall() {
    	exampleRange = new Range(4, 8);
    	CompRange = new Range(4, 7);
    	assertFalse("Comparing 4, 8 to 4, 7 should not equal",
    			exampleRange.equals(CompRange));
    }
    @Test
    public void HigherBoundBig() {
    	exampleRange = new Range(1, 1);
    	CompRange = new Range(1, 2);
    	assertFalse("Comparing 1,1 to 1,2 should not equal",
    			exampleRange.equals(CompRange));
    }
    @Test
    public void BothNonMatchingHigh() {
    	exampleRange = new Range(1,2);
    	CompRange = new Range(3,4);
    	assertFalse("1,2 should not equal 3,4",
    			exampleRange.equals(CompRange));
    }
    @Test
    public void BothNonMatchingLow() {
    	exampleRange = new Range(4,5);
    	CompRange = new Range(1,2);
    	assertFalse("4,5 should not equal 1,2",
    			exampleRange.equals(CompRange));
    }
    @Test
    public void ComparingNegativeNumbers() {
    	exampleRange = new Range(-2,-1);
    	CompRange = new Range(-2, -1);
    	assertTrue("-2,-1 and -2, -1 should equal",
    			exampleRange.equals(CompRange));
    }
    @Test
    public void ComparingNegativewithPositive() {
    	exampleRange = new Range(-2,3);
    	CompRange = new Range(-2,3);
    	assertTrue("-2, 3 and -2, 3 should equal",
    			exampleRange.equals(CompRange));
    }
    @Test
    public void ComparingMismatchSigns() {
    	exampleRange = new Range(2,3);
    	CompRange = new Range(-2,3);
    	assertFalse("2, 3 and -2, 3 should not equal",
    			exampleRange.equals(CompRange));
    }
    @Test
    public void ComparingToNull() {
    	assertFalse("Comparing -1,1 to Null should not equal",
    			exampleRange.equals(null));
    }
    
    //getLength() section
    
    @Test
    public void all0s() {
    	exampleRange = new Range(0, 0);
    	assertEquals("0, 0 should have a range of 0",
    			0, exampleRange.getCentralValue(), .000000001d);
    }
    @Test
    public void all1s() {
    	exampleRange = new Range(1, 1);
    	assertEquals("1, 1 should have a range of 0",
    			0, exampleRange.getCentralValue(), .000000001d);
    }
    @Test
    public void PositiveLength1() {
    	exampleRange = new Range(1, 2);
    	assertEquals("1, 2 should have a range of 1",
    			1, exampleRange.getCentralValue(), .000000001d);
    }
    @Test
    public void NegativeLength1() {
    	exampleRange = new Range(-2, -1);
    	assertEquals("-2, -1 should have a range of 1",
    			1, exampleRange.getCentralValue(), .000000001d);
    }
    @Test
    public void LengthAcross0() {
    	exampleRange = new Range(-1, 1);
    	assertEquals("-1, 1 should have a range of 2",
    			2, exampleRange.getCentralValue(), .000000001d);
    }
    

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}