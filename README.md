# Employee Department

## Task details

### Assumptions
Main assumption is that employees are sorted. First is the CEO, then comes manager and manager's subordinates.
So the situation where subordinate comes before subordinate's manager is impossible.

### Usage Instructions
Put csv file into resources folder and run the app. The result will appear in console in three sections:
- Underpaid managers
- Overpaid managers
- Employees with too many managers


### Task Description

BIG COMPANY is employing a lot of employees. Company would like to analyze its organizational
structure and identify potential improvements. Board wants to make sure that every manager earns
at least 20% more than the average salary of its direct subordinates, but no more than 50% more
than that average. Company wants to avoid too long reporting lines, therefore we would like to
identify all employees which have more than 4 managers between them and the CEO.

You are given a CSV file which contains information about all the employees. File structure looks like
this:
```text
Id,firstName,lastName,salary,managerId
123,Joe,Doe,60000,
124,Martin,Chekov,45000,123
125,Bob,Ronstad,47000,123
300,Alice,Hasacat,50000,124
305,Brett,Hardleaf,34000,300
```
Each line represents an employee (CEO included). CEO has no manager specified. Number of rows
can be up to 1000.  

Write a simple program which will read the file and report:
- which managers earn less than they should, and by how much
- which managers earn more than they should, and by how much
- which employees have a reporting line which is too long, and by how much
  
Key points:
- use only Java SE (any version), and Junit (any version) for tests.
- use maven for project structure and build
- your application should read data from a file and print out output to console. No GUIs
  needed.
- code will be assessed on correctness, simplicity, readability and cleanliness
- If you have any doubts make a sensible assumption and document it  

When you're ready with your assignment please send us link to an online code repository (github,
  bitbucket, etc.) so we can review your code.

## Implementation details
Implementation provides thread safe service that provides three collections that requested by task with objects 
employees and required data:

-     getHighSalaryManagers - returns managers with higher salary and amount;
-     getLowSalaryManagers - returns managers with lower salary and amount;
-     getWrongDepartmentStructureManagers - returns employees with long managers line;

