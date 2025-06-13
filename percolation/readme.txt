Programming Assignment 1: Percolation

Answer these questions after you implement your solution.

/* *****************************************************************************
 *  Describe the data structures (i.e., instance variables) you used to
 *  implement the Percolation API.
 **************************************************************************** */
  I used a n variable to represent the size of the grid, a 2D boolean array to tell if
  sites were open or not, a "virtual" top site that connected the sites in the first row
  and was the leader of the set, and a bottom site that did the same as the top,
  then I used a variable to count the sites open, and lastly I implemented the Weighted and
  Quick find Union find objects to merge the sets.



/* *****************************************************************************
 *  Briefly describe the algorithms you used to implement each method in
 *  the Percolation API.
 **************************************************************************** */
open(): I used a bunch if statements to check if the index was open or closed, and then
I connected the open sites depending on their location, such as top, bottom, or in between. I
also had to implement private methods such as toIndex to convert 2D array indices to 1d array indices
isOpen(): I checked if index was valid and then returned given site to check if open or not.
isFull(): Again, checked if index was valid then checked if the top connected to current
row and column, or site, that way it could check if it was full.
numberOfOpenSites(): simply returned the variable i had for keeping track, amtOpen
percolates(): it percolates if the virtual top site connected to the virtual bottom site,
so i used union find to check that.

/* *****************************************************************************
 *  First, implement Percolation using QuickFindUF.
 *  What is the largest value of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
 *
 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

 T = 100

 n          time (seconds)
--------------------------
20              0.057
50              0.173
100             1.715
200             27.578
250             62.168 (over a minute)

/* *****************************************************************************
 *  Describe the strategy you used for selecting the values of n.
 **************************************************************************** */
I started off small then i began to basically double the values but as i saw the
jump from 100 to 200, i decided to make it 250 and I was right since 250 more than doubled
in time elapsed.


/* *****************************************************************************
 *  Next, implement Percolation using WeightedQuickUnionUF.
 *  What is the largest value of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
 *
 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

 T = 100

 n          time (seconds)
--------------------------
100             0.105
200             0.27
400             1.071
800             5.14
1600            44.713


/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */





/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */




/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
