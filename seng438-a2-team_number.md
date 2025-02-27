**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group \#:      |         16         |
| -------------- | ------------------ |
| Student Names: | Sahib Singh Thethi |
|                | Sukriti Badhwar    |
|                | Wade Banman        |
|                | Rohan Lange        |

# 1 Introduction

The objective of this lab was to explore the fundamentals of automated unit testing using JUnit, with a particular focus on requirements-based testing. Through this lab, we gained hands-on experience in designing and implementing test cases for the DataUtilities and Range classes from the JFreeChart library. Additionally, we explored the use of mocking to test methods that depend on interfaces, ensuring a controlled and isolated test environment. Prior to this lab, our knowledge of JUnit was primarily theoretical, and we had limited exposure to using mock objects in unit testing. The lab provided an opportunity to apply black-box testing techniques, such as equivalence partitioning and boundary value analysis, to systematically derive test cases that comprehensively validate the functional behavior of the given classes.

# 2 Detailed description of unit test strategy

// including the input partitions you have designed

In order to make sure we encapsulate and exhaust possible inputs, equivilence class was used for methods getLength() and equals(). In order to fully understand and test what is nessisary we looked at the javadoc regarding these individual functions to see what are and are not expected input values as well as how the function is expected to behave.

Starting with equals(), we centered my options around the possible inputs. Seeing what happens with lower bound being incorrect both above and below the base comparison object. Then swapping and trying the higher bound. This is repeated for negative numbers as well to make sure the logic holds. Finally a test across 0 is needed to ensure the boundry point is checked as before we had only done X < 0 and X > 0 but not equaling nor across.
    
Next is the getLength() method, This method relys only on one parameter making it much simplier to test. Same as before we used equivilence class testing to partition the possible inputs. First staring with 0,0 to get the boundry point out first. X > 0. One test for when length is 0 and another for 1.

This is then repeated for negative valules (X < 0) Then finally a range across 0 to make sure the logic does not behave differently when signs are mixed. 

   
# 3 Test cases developed

Text…

// write down the name of the test methods and classes. Organize the based on
the source code method // they test. identify which tests cover which partitions
you have explained in the test strategy section //above
## getLentgh()
## X = 0
### all0s() 
* exampleRange = new Range(0, 0)

## X > 0
### all1s() 
* exampleRange = new Range(1, 1)
### PositiveLength1() 
* exampleRange = new Range(1, 2)
## X < 0
### NegativeLength1() 
* exampleRange = new Range(-2, -1)
## -1 <= X <= 1
### LengthAcross0() 
* exampleRange = new Range(-1, 1)


## equals()
## X >= 0
### ShouldEqualtest()
* exampleRange = new Range(1, 1)
* CompRange = new Range(1, 1)

### LowerBoundSmall() 
* exampleRange = new Range(1, 1)
* CompRange = new Range(0, 1)

### LowerBoundBig() 
* CompRange = new Range(2, 2)
* exampleRange = new Range(1, 2)

### HigherBoundSmall() 
* exampleRange = new Range(4, 8);
* CompRange = new Range(4, 7);

### HigherBoundBig() 
* exampleRange = new Range(1, 1)
* CompRange = new Range(1, 2)

### BothNonMatchingHigh() 
* exampleRange = new Range(1,2)
* CompRange = new Range(3,4)
    
### BothNonMatchingLow() 
* exampleRange = new Range(4,5)
* CompRange = new Range(1,2)

## X < 0
### ComparingNegativeNumbers() 
* exampleRange = new Range(-2,-1)
* CompRange = new Range(-2, -1)


## Negative < X < Positive
### ComparingNegativewithPositive() 
* exampleRange = new Range(-2,3)
* CompRange = new Range(-2,3)
    	
### ComparingMismatchSigns() 
* exampleRange = new Range(2,3)
* CompRange = new Range(-2,3)

## Null Test
### ComparingToNull() 
* exampleRange.equals(null)

## constrain()
testConstrainWithinRange()
testConstrainBelowLowerBound()
testConstrainAboveUpperBound()

## createNumberArray2D()
testCreateNumberArray2D_ValidInput()
testCreateNumberArray2D_EmptyArray()
testCreateNumberArray2D_NullInput()
testCreateNumberArray2D_SingleElement()
testCreateNumberArray2D_NegativeValues()
testCreateNumberArray2D_ZeroValues()
testCreateNumberArray2D_LargeValues()
testCreateNumberArray2D_MixedValues()
testCreateNumberArray2D_NonUniformSubarrays()

## getCumulativePercentages()
testGetCumulativePercentagesWithEmptyDataset()
testGetCumulativePercentagesWithFractionalValues()
testGetCumulativePercentagesWithAlternatingPositiveNegativeValues()
testGetCumulativePercentagesWithSmallValues()
testGetCumulativePercentagesWithZeroValues()
testGetCumulativePercentagesWithNegativeValues()
testGetCumulativePercentagesWithSingleValue()
testGetCumulativePercentagesWithLargeValues()

# 4 How the team work/effort was divided and managed

The tasks were distributed among the team members to ensure an efficient workflow and a balanced workload. The responsibilities were divided as follows:

Sukriti: Implemented test cases for calculateColumnTotal(), calculateRowTotal(), and createNumberArray() in the DataUtilities class.

Sahib:: Focused on createNumberArray2D(), getCumulativePercentages() in DataUtilities, and constrain() in Range.

Wade: Developed test cases for equals() and getLength() in the Range class.

Rohan: Covered the shift() and expand() methods in the Range class.

# 5 Difficulties encountered, challenges overcome, and lessons learned

During the lab, we encountered several challenges, particularly with mocking interfaces using JUnit. Some team members initially struggled with setting up and utilizing mock objects to test methods that rely on interfaces such as Values2D and KeyedValues. However, through collaboration and guided research, we successfully implemented mock objects to isolate dependencies and improve test reliability.                            

Creating meaningful test scenarios that follow the guidelines of black-box testing was another significant issue. Iteratively improving our test cases was necessary to make sure we covered all pertinent input partitions. Additionally, troubleshooting unsuccessful test cases revealed possible flaws in the specified classes and gave us a better grasp of the implementation specifics.

Through this lab, we reinforced our understanding of unit testing best practices, the importance of well-structured test cases, and the role of automation in software quality assurance. The experience also highlighted the value of teamwork and effective communication in software testing projects.

# 6 Comments/feedback on the lab itself

Overall, this lab provided a practical and insightful introduction to requirements-based unit testing. The structured approach to test case design, implementation, and execution helped us develop a systematic mindset for software testing. The emphasis on mocking added a new dimension to our understanding of unit testing, demonstrating how dependencies can be effectively managed in test environments.

Despite some initial difficulties, the lab was a valuable learning experience that strengthened our skills in JUnit testing, black-box testing techniques, and collaborative software testing workflows.
