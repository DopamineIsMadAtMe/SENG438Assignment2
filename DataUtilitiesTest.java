package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.*;

public class DataUtilitiesTest extends DataUtilities {
	
	private Values2D values;
	private Mockery mockingContext;
	private double[] validData;
    private double[] emptyData;
    private double[] singleElementData;
    private double[] zeroData;
    private double[] negativeValuesData;
    private double[] mixedValuesData;
	
	@Before
	public void setUp() throws Exception{
		mockingContext = new Mockery();
		values = mockingContext.mock(Values2D.class);
		
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
	 @Test(expected = InvalidParameterException.class)
	    public void calculateColumnTotalForNullData() {
	        DataUtilities.calculateColumnTotal(null, 0);
	    }
	 //Invalid Case when column index is out of bounds
	 @Test
	    public void calculateColumnTotalForColumnOutOfBounds() {
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(2));
	                one(values).getColumnCount();
	                will(returnValue(2));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 5);
	        assertEquals(0.0, result, .000000001d);
	    }
	 //Invalid Case when column index is negative
	 @Test
	    public void calculateColumnTotalForNegativeColumnIndex() {
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(2));
	                one(values).getColumnCount();
	                will(returnValue(2));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, -1);
	        assertEquals(0.0, result, .000000001d);
	    }
	// Valid Case
	 @Test
	 public void calculateRowTotalForMultipleColumns() {
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
	 @Test(expected = InvalidParameterException.class)
	 public void calculateRowTotalForNullData() {
	     DataUtilities.calculateRowTotal(null, 0);
	 }

	 // Invalid Case when row index is out of bounds
	 @Test
	 public void calculateRowTotalForRowOutOfBounds() {
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getColumnCount();
	             will(returnValue(2));
	         }
	     });

	     double result = DataUtilities.calculateRowTotal(values, 5);
	     assertEquals(0.0, result, .000000001d);
	 }

	  //Invalid Case when row index is negative
	 @Test
	 public void calculateRowTotalForNegativeRowIndex() {
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getColumnCount();
	             will(returnValue(2));
	         }
	     });

	     double result = DataUtilities.calculateRowTotal(values, -1);
	     assertEquals(0.0, result, .000000001d);
	 }
	 
	// Valid Case: Converting a normal array of double values to Number[]
	    @Test
	    public void createNumberArrayWithValidData() {
	        Number[] result = DataUtilities.createNumberArray(validData);

	        assertNotNull(result);
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

	        assertNotNull(result);
	        assertEquals(1, result.length);
	        assertEquals(7.0, result[0].doubleValue(), .000000001d);
	    }

	    // Edge Case: testing array containing zero
	    @Test
	    public void createNumberArrayWithZeroValue() {
	        Number[] result = DataUtilities.createNumberArray(zeroData);

	        assertNotNull(result);
	        assertEquals(1, result.length);
	        assertEquals(0.0, result[0].doubleValue(), .000000001d);
	    }

	    // Edge Case: Testing array containing only negative numbers
	    @Test
	    public void createNumberArrayWithNegativeValues() {
	        Number[] result = DataUtilities.createNumberArray(negativeValuesData);

	        assertNotNull(result);
	        assertEquals(3, result.length);
	        assertEquals(-1.5, result[0].doubleValue(), .000000001d);
	        assertEquals(-2.5, result[1].doubleValue(), .000000001d);
	        assertEquals(-3.5, result[2].doubleValue(), .000000001d);
	    }

	    // Edge Case: testing with both positive and negative values
	    @Test
	    public void createNumberArrayWithMixedValues() {
	        Number[] result = DataUtilities.createNumberArray(mixedValuesData);

	        assertNotNull(result);
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
}