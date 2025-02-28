**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group \#:      |         16         |
| -------------- | ------------------ |
| Student Names: | Sahib Singh Thethi |
|                | Sukriti Badhwar    |
|                | Wade Banman        |
|                | Rohan Lange        |

# 1. Introduction

The objective of this lab was to explore the fundamentals of automated unit testing using JUnit, with a particular focus on requirements-based testing. Through this lab, we gained hands-on experience in designing and implementing test cases for the DataUtilities and Range classes from the JFreeChart library. Additionally, we explored the use of mocking to test methods that depend on interfaces, ensuring a controlled and isolated test environment. Prior to this lab, our knowledge of JUnit was primarily theoretical, and we had limited exposure to using mock objects in unit testing. The lab provided an opportunity to apply black-box testing techniques, such as equivalence partitioning and boundary value analysis, to systematically derive test cases that comprehensively validate the functional behavior of the given classes.

# 2. Detailed description of unit test strategy

**Test Plan for Range Test:**

In order to make sure we encapsulate and exhaust possible inputs, equivilence class was used for methods getLength() and equals(). In order to fully understand and test what is nessisary we looked at the javadoc regarding these individual functions to see what are and are not expected input values as well as how the function is expected to behave.

Starting with equals(), we centered my options around the possible inputs. Seeing what happens with lower bound being incorrect both above and below the base comparison object. Then swapping and trying the higher bound. This is repeated for negative numbers as well to make sure the logic holds. Finally a test across 0 is needed to ensure the boundry point is checked as before we had only done X < 0 and X > 0 but not equaling nor across.
    
Next is the getLength() method, This method relys only on one parameter making it much simplier to test. Same as before we used equivilence class testing to partition the possible inputs. First staring with 0,0 to get the boundry point out first. X > 0. One test for when length is 0 and another for 1.

This is then repeated for negative valules (X < 0) Then finally a range across 0 to make sure the logic does not behave differently when signs are mixed. 

**Test Plan for Data Utilities Test:**

The purpose of the Data Utilities tests was to verify the correctness and robustness of all  5 methods - calculateColumnTotal, calculateRowTotal, createNumberArray, createNumberArray2D, and getCumulativePercentages

To ensure thorough validation, we used following strategies:
- **Equivalence Partitioning:** Input values were categorized into valid, edge, and invalid partitions to cover all possible scenarios efficiently.  
- **Boundary Analysis:** We tested values at the limits (e.g., empty tables, null values, out-of-bound indices) to identify potential issues at critical points.  

This strategy ensured functional coverage of all scenarios, validated error handling, and checked for boundary conditions, making these methods more reliable.  

   
# 3. Test cases developed

## **Test Cases for Range Class**

| **Method**       | **Partition**              | **Test Method Name**          | **Example Inputs**                 |
|-----------------|--------------------------|------------------------------|------------------------------------|
| **equals()**    | X ≥ 0                     | `ShouldEqualTest`            | `(1,1) == (1,1)`                  |
|                 |                            | `LowerBoundSmall`            | `(1,1) != (0,1)`                  |
|                 |                            | `LowerBoundBig`              | `(1,2) != (2,2)`                  |
|                 |                            | `HigherBoundSmall`           | `(4,8) != (4,7)`                  |
|                 |                            | `HigherBoundBig`             | `(1,1) != (1,2)`                  |
|                 |                            | `BothNonMatchingHigh`        | `(1,2) != (3,4)`                  |
|                 |                            | `BothNonMatchingLow`         | `(4,5) != (1,2)`                  |
|                 | X < 0                      | `ComparingNegativeNumbers`   | `(-2,-1) == (-2,-1)`              |
|                 | Negative < X < Positive   | `ComparingNegativeWithPositive` | `(-2,3) == (-2,3)`              |
|                 |                            | `ComparingMismatchSigns`     | `(2,3) != (-2,3)`                  |
|                 | Null Test                  | `ComparingToNull`            | `(1,2) != null`                    |
| **getLength()** | X = 0                      | `all0s`                      | `(0,0)`                            |
|                 | X > 0                      | `all1s`                      | `(1,1)`                            |
|                 |                            | `PositiveLength1`            | `(1,2)`                            |
|                 | X < 0                      | `NegativeLength1`            | `(-2,-1)`                          |
|                 | -1 ≤ X ≤ 1                 | `LengthAcross0`              | `(-1,1)`                           |
| **constrain()** | Value within range        | `testConstrainWithinRange`   | `(1,5)`, `3`                       |
|                 | Value below lower bound   | `testConstrainBelowLowerBound` | `(1,5)`, `0`                     |
|                 | Value above upper bound   | `testConstrainAboveUpperBound` | `(1,5)`, `6`                     |
---

## **Test Cases for Range Class**


| **Partition** | **Method** | **Test Name** | **Example Inputs** | **Expected Output** |
|--------------|-----------|---------------|--------------------|--------------------|
| **Valid Cases** | | | | |
| Multiple rows/columns with valid values | `calculateColumnTotal()` | `calculateColumnTotalForMultipleRows` | `Values2D: [[7.5], [2.5]]`, `Column: 0` | `10.0` |
|  | `calculateRowTotal()` | `calculateRowTotalForMultipleColumns` | `Values2D: [[7.5, 2.5]]`, `Row: 0` | `10.0` |
| Single row/column with one value | `calculateColumnTotal()` | `calculateColumnTotalForSingleRowSingleColumn` | `Values2D: [[-5.0]]`, `Column: 0` | `-5.0` |
|  | `calculateRowTotal()` | `calculateRowTotalForSingleRowSingleColumn` | `Values2D: [[-5.0]]`, `Row: 0` | `-5.0` |
| Negative values in multiple rows/columns | `calculateColumnTotal()` | `calculateColumnTotalForNegativeValues` | `Values2D: [[-2.5], [-3.5], [4.0]]`, `Column: 0` | `-2.0` |
|  | `calculateRowTotal()` | `calculateRowTotalForNegativeValues` | `Values2D: [[-2.5, -3.5, 4.0]]`, `Row: 0` | `-2.0` |
| Converting a valid double array to Number[] | `createNumberArray()` | `createNumberArrayWithValidData` | `double[]: [1.5, 2.5, 3.5]` | `[1.5, 2.5, 3.5]` as `Number[]` |
| Array contains only negative values | `createNumberArray()` | `createNumberArrayWithNegativeValues` | `double[]: [-1.5, -2.5, -3.5]` | `[-1.5, -2.5, -3.5]` as `Number[]` |
| Array contains a mix of positive and negative values | `createNumberArray()` | `createNumberArrayWithMixedValues` | `double[]: [-2.0, 0.0, 3.5]` | `[-2.0, 0.0, 3.5]` as `Number[]` |
| Converting a valid 2D double array to Number[][] | `createNumberArray2D()` | `testCreateNumberArray2D_SingleElement` | `double[][]: [[5.5]]` | `Number[][]: [[5.5]]` |
| 2D array contains negative values | `createNumberArray2D()` | `testCreateNumberArray2D_NegativeValues` | `double[][]: [[-1.1, -2.2], [-3.3, -4.4]]` | `Number[][]: [[-1.1, -2.2], [-3.3, -4.4]]` |
| 2D array contains mixed values | `createNumberArray2D()` | `testCreateNumberArray2D_MixedValues` | `double[][]: [[-1.1, 0, 2.2], [3.3, -4.4, 5.5]]` | `Number[][]: [[-1.1, 0, 2.2], [3.3, -4.4, 5.5]]` |
| Data contains fractional values | `getCumulativePercentages` | `testGetCumulativePercentagesWithFractionalValues` | `[0.2, 0.3, 0.5]` | `[0.2, 0.5, 1.0]` |
| Data contains large values | `getCumulativePercentages` | `testGetCumulativePercentagesWithLargeValues` | `[1000000, 2000000, 3000000]` | `[0.1667, 0.5, 1.0]` |
| Data contains small values | `getCumulativePercentages` | `testGetCumulativePercentagesWithSmallValues` | `[0.0001, 0.0002, 0.0003]` | `[0.1667, 0.5, 1.0]` |
| Data contains negative values | `getCumulativePercentages` | `testGetCumulativePercentagesWithNegativeValues` | `[-5, 10, 15]` | `[-0.1667, 0.25, 1.0]` |
| Dataset contains single values | `getCumulativePercentages` | `testGetCumulativePercentagesWithSingleValue` | `[5]` | `[1.0]` |
| **Edge Cases** | | | | |
| Column/row contains `null` values | `calculateColumnTotal()` | `calculateColumnTotalForNullValue` | `Values2D: [[null], [2.0], [3.0]]`, `Column: 0` | `5.0` |
|  | `calculateRowTotal()` | `calculateRowTotalForNullValue` | `Values2D: [[null, 2.0, 3.0]]`, `Row: 0` | `5.0` |
| Empty table | `calculateColumnTotal()` | `calculateColumnTotalForEmptyTable` | `Values2D: []`, `Column: 0` | `0.0` |
|  | `calculateRowTotal()` | `calculateRowTotalForEmptyTable` | `Values2D: []`, `Row: 0` | `0.0` |
| Empty array input | `createNumberArray()` | `createNumberArrayWithEmptyArray` | `double[]: []` | `Number[]: []` |
| Single element in array | `createNumberArray()` | `createNumberArrayWithSingleElement` | `double[]: [7.0]` | `[7.0]` as `Number[]` |
| Array contains zero | `createNumberArray()` | `createNumberArrayWithZeroValue` | `double[]: [0.0]` | `[0.0]` as `Number[]` |
| 2D array contains only zeros | `createNumberArray2D()` | `testCreateNumberArray2D_ZeroValues` | `double[][]: [[0, 0], [0, 0]]` | `Number[][]: [[0, 0], [0, 0]]` |
| 2D array has non-uniform subarrays | `createNumberArray2D()` | `testCreateNumberArray2D_NonUniformSubarrays` | `double[][]: [[1.1], [2.2, 3.3]]` | `Number[][]: [[1.1], [2.2, 3.3]]` |
| Zero values in dataset | `getCumulativePercentages` | `testGetCumulativePercentagesWithZeroValues` | `[0, 0, 0]` | `[0.0, 0.0, 0.0]` |
| Empty Dataset | `getCumulativePercentages` | `testGetCumulativePercentagesWithEmptyDataset` | `[]` (empty dataset) | `[]`|
| **Invalid Cases** | | | | |
| Index out of bounds | `calculateColumnTotal()` | `calculateColumnTotalForColumnOutOfBounds` | `Values2D: [[]]`, `Column: 5` | `0.0` |
|  | `calculateRowTotal()` | `calculateRowTotalForRowOutOfBounds` | `Values2D: [[]]`, `Row: 5` | `0.0` |
| Negative index | `calculateColumnTotal()` | `calculateColumnTotalForNegativeColumnIndex` | `Values2D: [[]]`, `Column: -1` | `0.0` |
|  | `calculateRowTotal()` | `calculateRowTotalForNegativeRowIndex` | `Values2D: [[]]`, `Row: -1` | `0.0` |
| Null data object | `calculateColumnTotal()` | `calculateColumnTotalForNullData` | `Values2D: null`, `Column: 0` | `InvalidParameterException` |
| Null array input | `createNumberArray()` | `createNumberArrayWithNullInput` | `double[]: null` | `IllegalArgumentException` |
| Null 2D array input | `createNumberArray2D()` | `testCreateNumberArray2D_NullInput` | `double[][]: null` | `InvalidParameterException` |
| Null Dataset | `getCumulativePercentages` | `testGetCumulativePercentagesWithNullDataset` | `null` | `IllegalArgumentException` or equivalent error |
| Null values in Dataset | `getCumulativePercentages` | `testGetCumulativePercentagesWithNullValues` | `[5, null, 10]` | `IllegalArgumentException` or ignores nulls |
| Non numeric values in dataset | `getCumulativePercentages` | `testGetCumulativePercentagesWithNonNumericValues` | `["A", "B", "C"]` | `IllegalArgumentException` |

---

**Benefits and Drawbacks of Mocking:**

Mocking is definitely a powerful tool to isolate the system under test. It helped in creating tests that were solely focused on testing the behaviour of the method. It was very useful in simulating edge cases, failures, and unexpected behaviors that might be difficult to reproduce with real dependencies. This improved test coverage and robustness. It also helped ensure that the tests produced the same results every time which improved the reliance of tests.

One drawback we identified about mocking was that while they replace real dependencies, at the same time they do not test how the actual system components interact. This means potential issues in real-world scenarios can go unnoticed in the testing stage. It creates a false sense of security if a test passes with the mockup but can fail in real environment. Initially, we also found mockups to be complex and a bit difficult to understand.

# 4. How the team work/effort was divided and managed

The tasks were distributed among the team members to ensure an efficient workflow and a balanced workload. The responsibilities were divided as follows:

Sukriti: Implemented test cases for calculateColumnTotal(), calculateRowTotal(), and createNumberArray() in the DataUtilities class.

Sahib: Focused on createNumberArray2D(), getCumulativePercentages() in DataUtilities, and constrain() in Range.

Wade: Developed test cases for equals() and getLength() in the Range class.

Rohan: Covered the shift() and expand() methods in the Range class.
After implementation, all team members reviewed each other's test cases to ensure full coverage and correctness. Additionally, those who did not work directly on mocking took the time to understand the concept and successfully gave a demo.  
# 5. Difficulties encountered, challenges overcome, and lessons learned

During the lab, we encountered several challenges, particularly with mocking interfaces using JUnit. Some team members initially struggled with setting up and utilizing mock objects to test methods that rely on interfaces such as Values2D and KeyedValues. However, through collaboration and guided research, we successfully implemented mock objects to isolate dependencies and improve test reliability. The other main challenge we encountered was having our tests that are expected to fail not to have any errors, and only report a failure instead. We all worked together as a team to solve these errors and debug the tests to only give failures or passes.                         

Creating meaningful test scenarios that follow the guidelines of black-box testing was another significant issue. Iteratively improving our test cases was necessary to make sure we covered all pertinent input partitions. Additionally, troubleshooting unsuccessful test cases revealed possible flaws in the specified classes and gave us a better grasp of the implementation specifics.

Through this lab, we reinforced our understanding of unit testing best practices, the importance of well-structured test cases, and the role of automation in software quality assurance. The experience also highlighted the value of teamwork and effective communication in software testing projects.

# 6. Comments/feedback on the lab itself

Overall, this lab provided a practical and insightful introduction to requirements-based unit testing. The structured approach to test case design, implementation, and execution helped us develop a systematic mindset for software testing. The emphasis on mocking added a new dimension to our understanding of unit testing, demonstrating how dependencies can be effectively managed in test environments.

Despite some initial difficulties, the lab was a valuable learning experience that strengthened our skills in JUnit testing, black-box testing techniques, and collaborative software testing workflows.
