**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group: 16                          |
|------------------------------------|
| Student 1 name: Sahib Singh Thethi |   
| Student 2 name: Sukriti Badhwar    |   
| Student 3 name: Rohan Lange        |   
| Student 4 name: Wade Banman        | 

# 1 Introduction

In this lab, our group worked on 10 total methods within the org.jfree.data Utilities and Range classes.
The methods we chose were the following:
Range: getLength(), equals(), __, __, __
Utilities: __, __, __, __, __

To create our tests, we used methods of black-box testing that we will further discuss in the next section.


# 2 Detailed description of unit test strategy

// including the input partitions you have designed

In order to make sure we encapsulate and exhaust possible inputs, equivilence class was used for methods getLength() and equals(). In order to fully understand and test what is nessisary we looked at the javadoc regarding these individual functions to see what are and are not expected input values as well as how the function is expected to behave.

Starting with equals(), I centered my options around the possible inputs. Seeing what happens with lower bound being incorrect both above and below the base comparison object. Then swapping and trying the higher bound. This is repeated for negative numbers as well to make sure the logic holds. Finally a test across 0 is needed to ensure the boundry point is checked as before I had only done X < 0 and X > 0 but not equaling nor across.
    

Next is the getLength() method, This method relys only on one parameter making it much simplier to test. Same as before I used equivilence class testing to partition the possible inputs. First staring with 0,0 to get the boundry point out first. X > 0. One test for when length is 0 and another for 1.

This is then repeated for negative valules (X < 0) Then finally a range across 0 to make sure the logic does not behave differently when signs are mixed. 




# NEXT PART HERE!! 
    	
    
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




# 4 How the team work/effort was divided and managed

Text…

# 5 Difficulties encountered, challenges overcome, and lessons learned

Text…

# 6 Comments/feedback on the lab itself

Text…
