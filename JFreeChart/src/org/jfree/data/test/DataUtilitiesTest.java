package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {

		// Test Methods for createNumberArray2D() method
	   
		/**
	    * Tests whether a valid 2D array of doubles is correctly converted to a 2D array of Numbers.
	    */
	    @Test
	    public void testCreateNumberArray2D_ValidInput() {
	        double[][] input = {{1.1, 2.2}, {3.3, 4.4}};
	        Number[][] expected = {{1.1, 2.2}, {3.3, 4.4}};
	        Number[][] result = DataUtilities.createNumberArray2D(input);
	        assertArrayEquals("Valid input should be converted correctly", expected, result);
	    }

	    /**
	     * Tests whether an empty 2D double array correctly returns an empty 2D Number array.
	     */
	    @Test
	    public void testCreateNumberArray2D_EmptyArray() {
	        double[][] input = {};
	        Number[][] expected = {};
	        Number[][] result = DataUtilities.createNumberArray2D(input);
	        assertArrayEquals("Empty array should return an empty Number array", expected, result);
	    }

	    /**
	     * Tests whether passing a null input throws an InvalidParameterException.
	     */
	    @Test
	    public void testCreateNumberArray2D_NullInput() {
	        try {
		        double[][] data = null;
		        createNumberArray2D(data);
	    	} catch (InvalidParameterException e) {
	    		
	    	} catch (Exception e) {
	    		fail("Expected InvalidParameterException. Unexpected exception was thrown: ");
	    	}
	    }

	    /**
	     * Tests conversion when the 2D array contains a single element.
	     */
	    @Test
	    public void testCreateNumberArray2D_SingleElement() {
	        double[][] input = {{5.5}};
	        Number[][] expected = {{5.5}};
	        Number[][] result = DataUtilities.createNumberArray2D(input);
	        assertArrayEquals("Single element array should be correctly converted", expected, result);
	    }

	    /**
	     * Tests conversion when the 2D array contains negative values.
	     */
	    @Test
	    public void testCreateNumberArray2D_NegativeValues() {
	        double[][] input = {{-1.1, -2.2}, {-3.3, -4.4}};
	        Number[][] expected = {{-1.1, -2.2}, {-3.3, -4.4}};
	        Number[][] result = DataUtilities.createNumberArray2D(input);
	        assertArrayEquals("Negative values should be correctly converted", expected, result);
	    }

	    /**
	     * Tests conversion when the 2D array contains only zeros.
	     */
	    @Test
	    public void testCreateNumberArray2D_ZeroValues() {
	        double[][] input = {{0, 0}, {0, 0}};
	        Number[][] expected = {{0, 0}, {0, 0}};
	        Number[][] result = DataUtilities.createNumberArray2D(input);
	        assertArrayEquals("Zero values should be correctly converted", expected, result);
	    }

	    /**
	     * Tests conversion when the 2D array contains large numerical values.
	     */
	    @Test
	    public void testCreateNumberArray2D_LargeValues() {
	        double[][] input = {{1e10, 2e10}, {3e10, 4e10}};
	        Number[][] expected = {{1e10, 2e10}, {3e10, 4e10}};
	        Number[][] result = DataUtilities.createNumberArray2D(input);
	        assertArrayEquals("Large values should be correctly converted", expected, result);
	    }
	    
	    /**
	     * Tests conversion when the 2D array contains mixed numerical values.
	     */
	    @Test
	    public void testCreateNumberArray2D_MixedValues() {
	        double[][] input = {{-1.1, 0, 2.2}, {3.3, -4.4, 5.5}};
	        Number[][] expected = {{-1.1, 0, 2.2}, {3.3, -4.4, 5.5}};
	        Number[][] result = DataUtilities.createNumberArray2D(input);
	        assertArrayEquals("Mixed values should be correctly converted", expected, result);
	    }

	    /**
	     * Tests conversion when the 2D array has non-uniform sub-arrays.
	     */
	    @Test
	    public void testCreateNumberArray2D_NonUniformSubarrays() {
	        double[][] input = {{1.1}, {2.2, 3.3}};
	        Number[][] expected = {{1.1}, {2.2, 3.3}};
	        Number[][] result = DataUtilities.createNumberArray2D(input);
	        assertArrayEquals("Non-uniform subarrays should be correctly converted", expected, result);
	    }
	    
	    // Test Methods for getCumulativePercentages() method
	    
	    /**
	     * Tests cumulative percentage calculation for an empty dataset.
	     */
	    @Test
	    public void testGetCumulativePercentagesWithEmptyDataset() {
	        Mockery mockingContext = new Mockery();
	        final KeyedValues data = mockingContext.mock(KeyedValues.class);
	        mockingContext.checking(new Expectations() {{
	            allowing(data).getItemCount(); will(returnValue(0));
	        }});
	        KeyedValues result = DataUtilities.getCumulativePercentages(data);
	        assertEquals("Empty dataset should return no values", 0, result.getItemCount());
	    }
	    
	    /**
	     * Tests cumulative percentage calculation when dataset contains fractional values.
	     */
	    @Test
	    public void testGetCumulativePercentagesWithFractionalValues() {
	        Mockery mockingContext = new Mockery();
	        final KeyedValues data = mockingContext.mock(KeyedValues.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(data).getItemCount(); will(returnValue(3));
	            allowing(data).getKey(0); will(returnValue(0));
	            allowing(data).getKey(1); will(returnValue(1));
	            allowing(data).getKey(2); will(returnValue(2));
	            allowing(data).getValue(0); will(returnValue(0.2));
	            allowing(data).getValue(1); will(returnValue(0.3));
	            allowing(data).getValue(2); will(returnValue(0.5));
	        }});

	        KeyedValues result = DataUtilities.getCumulativePercentages(data);

	        assertEquals("The cumulative percentage for the first key should be 0.2", 0.2, result.getValue(0));
	        assertEquals("The cumulative percentage for the second key should be 0.5", 0.5, result.getValue(1));
	        assertEquals("The cumulative percentage for the third key should be 1.0", 1.0, result.getValue(2));
	    }

	    /**
	     * Tests cumulative percentage calculation when dataset contains alternating positive and negative values.
	     */
	    @Test
	    public void testGetCumulativePercentagesWithAlternatingPositiveNegativeValues() {
	        Mockery mockingContext = new Mockery();
	        final KeyedValues data = mockingContext.mock(KeyedValues.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(data).getItemCount(); will(returnValue(3));
	            allowing(data).getKey(0); will(returnValue(0));
	            allowing(data).getKey(1); will(returnValue(1));
	            allowing(data).getKey(2); will(returnValue(2));
	            allowing(data).getValue(0); will(returnValue(-10));
	            allowing(data).getValue(1); will(returnValue(20));
	            allowing(data).getValue(2); will(returnValue(-30));
	        }});

	        KeyedValues result = DataUtilities.getCumulativePercentages(data);

	        assertEquals("The cumulative percentage for the first key should be -0.3333", -0.3333, result.getValue(0).doubleValue(), 0.0001);
	        assertEquals("The cumulative percentage for the second key should be 0.3333", 0.3333, result.getValue(1).doubleValue(), 0.0001);
	        assertEquals("The cumulative percentage for the third key should be 1.0", 1.0, result.getValue(2).doubleValue(), 0.0001);
	    }

	    /**
	     * Tests cumulative percentage calculation when dataset contains small values.
	     */
	    @Test
	    public void testGetCumulativePercentagesWithSmallValues() {
	        Mockery mockingContext = new Mockery();
	        final KeyedValues data = mockingContext.mock(KeyedValues.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(data).getItemCount(); will(returnValue(3)); 
	            allowing(data).getKey(with(any(Integer.class)));
	            allowing(data).getValue(0); will(returnValue(0.0001));
	            allowing(data).getValue(1); will(returnValue(0.0002));
	            allowing(data).getValue(2); will(returnValue(0.0003));
	        }});

	        KeyedValues result = DataUtilities.getCumulativePercentages(data);

	        assertEquals("The cumulative percentage for the first key should be 0.1667", 0.16666666666666666, result.getValue(0));
	        assertEquals("The cumulative percentage for the second key should be 0.5", 0.5, result.getValue(1));
	        assertEquals("The cumulative percentage for the third key should be 1.0", 1.0, result.getValue(2));
	    }

	    /**
	     * Tests cumulative percentage calculation when dataset contains zero values.
	     */
	    @Test
	    public void testGetCumulativePercentagesWithZeroValues() {
	        Mockery mockingContext = new Mockery();
	        final KeyedValues data = mockingContext.mock(KeyedValues.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(data).getItemCount(); will(returnValue(3));
	            allowing(data).getKey(with(any(Integer.class)));
	            allowing(data).getValue(0); will(returnValue(0));
	            allowing(data).getValue(1); will(returnValue(0));
	            allowing(data).getValue(2); will(returnValue(0));
	        }});

	        KeyedValues result = DataUtilities.getCumulativePercentages(data);

	        assertEquals("The cumulative percentage for the first key should be 0.0", 0.0, result.getValue(0));
	        assertEquals("The cumulative percentage for the second key should be 0.0", 0.0, result.getValue(1));
	        assertEquals("The cumulative percentage for the third key should be 0.0", 0.0, result.getValue(2));
	    }

	    /**
	     * Tests cumulative percentage calculation when dataset contains negative values.
	     */
	    @Test
	    public void testGetCumulativePercentagesWithNegativeValues() {
	        Mockery mockingContext = new Mockery();
	        final KeyedValues data = mockingContext.mock(KeyedValues.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(data).getItemCount(); will(returnValue(3));
	            allowing(data).getKey(with(any(Integer.class)));
	            allowing(data).getValue(0); will(returnValue(-5));
	            allowing(data).getValue(1); will(returnValue(10));
	            allowing(data).getValue(2); will(returnValue(15));
	        }});

	        KeyedValues result = DataUtilities.getCumulativePercentages(data);

	        assertEquals("The cumulative percentage for the first key should be -0.1667", -0.16666666666666666, result.getValue(0));
	        assertEquals("The cumulative percentage for the second key should be 0.25", 0.25, result.getValue(1));
	        assertEquals("The cumulative percentage for the third key should be 1.0", 1.0, result.getValue(2));
	    }

	    /**
	     * Tests cumulative percentage calculation when dataset contains a single value.
	     */
	    @Test
	    public void testGetCumulativePercentagesWithSingleValue() {
	        Mockery mockingContext = new Mockery();
	        final KeyedValues data = mockingContext.mock(KeyedValues.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(data).getItemCount(); will(returnValue(1));
	            allowing(data).getKey(with(any(Integer.class)));
	            allowing(data).getValue(0); will(returnValue(5));
	        }});

	        KeyedValues result = DataUtilities.getCumulativePercentages(data);

	        assertEquals("The cumulative percentage for the single key should be 1.0", 1.0, result.getValue(0));
	    }

	    /**
	     * Tests cumulative percentage calculation when dataset contains large values.
	     */
	    public void testGetCumulativePercentagesWithLargeValues() {
	        Mockery mockingContext = new Mockery();
	        final KeyedValues data = mockingContext.mock(KeyedValues.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(data).getItemCount(); will(returnValue(3));
	            allowing(data).getKey(with(any(Integer.class)));
	            allowing(data).getValue(0); will(returnValue(1000000)); 
	            allowing(data).getValue(1); will(returnValue(2000000)); 
	            allowing(data).getValue(2); will(returnValue(3000000)); 
	        }});

	        KeyedValues result = DataUtilities.getCumulativePercentages(data);

	        assertEquals("The cumulative percentage for the first key should be 0.1667", 0.16666666666666666, result.getValue(0));
	        assertEquals("The cumulative percentage for the second key should be 0.5", 0.5, result.getValue(1));
	        assertEquals("The cumulative percentage for the third key should be 1.0", 1.0, result.getValue(2));
	    }
}