package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.*;

public class DataUtilitiesTest extends DataUtilities {
	
	private double[] validData;
    private double[] emptyData;
    private double[] singleElementData;
    private double[] zeroData;
    private double[] negativeValuesData;
    private double[] mixedValuesData;
	
	@Before
	public void setUp() throws Exception{
		
		validData = new double[]{1.5, 2.5, 3.5};
        emptyData = new double[]{};
        singleElementData = new double[]{7.0};
        zeroData = new double[]{0.0};
        negativeValuesData = new double[]{-1.5, -2.5, -3.5};
        mixedValuesData = new double[]{-2.0, 0.0, 3.5};
	}

	//Valid Case
	 @Test
	 public void calculateColumnTotalForMultipleRows() {
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
		 mockingContext.checking(new Expectations() {
				{
					one(values).getRowCount();
					will(returnValue(2));
					one(values).getValue(0, 0);
					will(returnValue(7.5));
		            one(values).getValue(1, 0);
		            will(returnValue(2.5));
				}	
			});
	     double result = DataUtilities.calculateColumnTotal(values, 0);
	     // verify
	     assertEquals(10.0, result, .000000001d);
	 }
	 //Valid Case
	 @Test
	    public void calculateColumnTotalForSingleRowSingleColumn() {
		 	Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(1));
	                one(values).getValue(0, 0);
	                will(returnValue(-5.0));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals(-5.0, result, .000000001d);
	    }
	 //Valid Case
	 @Test
	    public void calculateColumnTotalForNegativeValues() {
		 	Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(3));
	                one(values).getValue(0, 0);
	                will(returnValue(-2.5));
	                one(values).getValue(1, 0);
	                will(returnValue(-3.5));
	                one(values).getValue(2, 0);
	                will(returnValue(4.0));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals(-2.0, result, .000000001d);
	    }
	 //Edge case, data has null values
	 @Test
	    public void calculateColumnTotalForNullValue() {
		 	Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(3));
	                one(values).getValue(0, 0);
	                will(returnValue(null));
	                one(values).getValue(1, 0);
	                will(returnValue(2.0));
	                one(values).getValue(2, 0);
	                will(returnValue(3.0));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals(5.0, result, .000000001d);
	    }
	 //Boundary Case - Empty Table
	 @Test
	    public void calculateColumnTotalForEmptyTable() {
		 	Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(0));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals(0.0, result, .000000001d);
	    }
	 //Invalid Case when invalid data object is passed
	 @Test
	    public void calculateColumnTotalForNullData() {
		 try {
			 DataUtilities.calculateColumnTotal(null, 0);
		 }catch(InvalidParameterException e) {
			 
		 }catch (Exception e) {
			 fail("Excepted InvalidParameterException. Unexpected exception was thrown: ");
		 }
	    }
	 //Invalid Case when column index is out of bounds
	 @Test
	    public void calculateColumnTotalForColumnOutOfBounds() {
		 	Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(0));
	            }
	        });
	        
        	double result = DataUtilities.calculateColumnTotal(values, 5);
 	        assertEquals(0.0, result, .000000001d);
	       
	    }
	 //Invalid Case when column index is negative
	 @Test
	    public void calculateColumnTotalForNegativeColumnIndex() {
		 	Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(0));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, -1);
	        assertEquals(0.0, result, .000000001d);
	    }
	 
	 //Row Tests
	// Valid Case
	 @Test
	 public void calculateRowTotalForMultipleColumns() {
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(0, 1);
	             will(returnValue(2.5));
	         }
	     });

	     double result = DataUtilities.calculateRowTotal(values, 0);
	     assertEquals(10.0, result, .000000001d);
	 }

	 // Valid Case
	 @Test
	 public void calculateRowTotalForSingleRowSingleColumn() {
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(1));
	             one(values).getValue(0, 0);
	             will(returnValue(-5.0));
	         }
	     });

	     double result = DataUtilities.calculateRowTotal(values, 0);
	     assertEquals(-5.0, result, .000000001d);
	 }

	 // Valid Case
	 @Test
	 public void calculateRowTotalForNegativeValues() {
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(3));
	             one(values).getValue(0, 0);
	             will(returnValue(-2.5));
	             one(values).getValue(0, 1);
	             will(returnValue(-3.5));
	             one(values).getValue(0, 2);
	             will(returnValue(4.0));
	         }
	     });

	     double result = DataUtilities.calculateRowTotal(values, 0);
	     assertEquals(-2.0, result, .000000001d);
	 }

	 // Edge case, data has null values
	 @Test
	 public void calculateRowTotalForNullValue() {
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(3));
	             one(values).getValue(0, 0);
	             will(returnValue(null));
	             one(values).getValue(0, 1);
	             will(returnValue(2.0));
	             one(values).getValue(0, 2);
	             will(returnValue(3.0));
	         }
	     });

	     double result = DataUtilities.calculateRowTotal(values, 0);
	     assertEquals(5.0, result, .000000001d);
	 }

	 // Boundary Case - Empty Table
	 @Test
	 public void calculateRowTotalForEmptyTable() {
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(0));
	         }
	     });

	     double result = DataUtilities.calculateRowTotal(values, 0);
	     assertEquals(0.0, result, .000000001d);
	 }

	 // Invalid Case when invalid data object is passed
	 @Test
	 public void calculateRowTotalForNullData() {
		 try {
			 DataUtilities.calculateRowTotal(null, 0);
		 }catch(InvalidParameterException e) {
			 
		 }catch (Exception e) {
			 fail("Excepted InvalidParameterException. Unexpected exception was thrown: ");
		 }
	     
	 }

	 // Invalid Case when row index is out of bounds
	 @Test
	 public void calculateRowTotalForRowOutOfBounds() {
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(0));
	         }
	     });

	     double result = DataUtilities.calculateRowTotal(values, 5);
	     assertEquals(0.0, result, .000000001d);
	 }

	  //Invalid Case when row index is negative
	 @Test
	 public void calculateRowTotalForNegativeRowIndex() {
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(0));
	         }
	     });

	     double result = DataUtilities.calculateRowTotal(values, -1);
	     assertEquals(0.0, result, .000000001d);
	 }
	 
	 //Array Tests
	// Valid Case: Converting a normal array of double values to Number[]
	    @Test
	    public void createNumberArrayWithValidData() {
	        Number[] result = DataUtilities.createNumberArray(validData);

	        assertNotNull(result[2]);
	        assertEquals(3, result.length);
	        assertEquals(1.5, result[0].doubleValue(), .000000001d);
	        assertEquals(2.5, result[1].doubleValue(), .000000001d);
	        assertEquals(3.5, result[2].doubleValue(), .000000001d);
	    }

	    // Edge Case: testing empty array
	    @Test
	    public void createNumberArrayWithEmptyArray() {
	        Number[] result = DataUtilities.createNumberArray(emptyData);

	        assertNotNull(result);
	        assertEquals(0, result.length);
	    }

	    // Edge Case: testing single Element Array
	    @Test
	    public void createNumberArrayWithSingleElement() {
	        Number[] result = DataUtilities.createNumberArray(singleElementData);

	        assertNotNull(result[0]);
	        assertEquals(1, result.length);
	        assertEquals(7.0, result[0].doubleValue(), .000000001d);
	    }

	    // Edge Case: testing array containing zero
	    @Test
	    public void createNumberArrayWithZeroValue() {
	        Number[] result = DataUtilities.createNumberArray(zeroData);

	        assertNotNull(result[0]);
	        assertEquals(1, result.length);
	        assertEquals(0.0, result[0].doubleValue(), .000000001d);
	    }

	    // Edge Case: Testing array containing only negative numbers
	    @Test
	    public void createNumberArrayWithNegativeValues() {
	        Number[] result = DataUtilities.createNumberArray(negativeValuesData);

	        assertNotNull(result[2]);
	        assertEquals(3, result.length);
	        assertEquals(-1.5, result[0].doubleValue(), .000000001d);
	        assertEquals(-2.5, result[1].doubleValue(), .000000001d);
	        assertEquals(-3.5, result[2].doubleValue(), .000000001d);
	    }

	    // Edge Case: testing with both positive and negative values
	    @Test
	    public void createNumberArrayWithMixedValues() {
	        Number[] result = DataUtilities.createNumberArray(mixedValuesData);

	        assertNotNull(result[2]);
	        assertEquals(3, result.length);
	        assertEquals(-2.0, result[0].doubleValue(), .000000001d);
	        assertEquals(0.0, result[1].doubleValue(), .000000001d);
	        assertEquals(3.5, result[2].doubleValue(), .000000001d);
	    }

	    // Invalid Case: Null Input
	    @Test(expected = IllegalArgumentException.class)
	    public void createNumberArrayWithNullInput() {
	        DataUtilities.createNumberArray(null);
	    }
	    
	    @After
	    public void tearDown() {
	        // Reset data after each test
	        validData = null;
	        emptyData = null;
	        singleElementData = null;
	        zeroData = null;
	        negativeValuesData = null;
	        mixedValuesData = null;
	    }
	    
	    
}
