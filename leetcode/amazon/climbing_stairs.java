// dec 16 - 7:15pm ~ 7:30pm
/* neetcode recursion
set 2 variables one / two as 1s.
looping through until n-1, 
temp = one // old one value 
one = two
two = temp + two. 
*/

class Solution {
    public int climbStairs(int n) {
        int one = 1;
        int two = 1;

        // i = 0 1 2
        for (int i = 0; i < n - 1; i++) {
            int temp = one;
            one = two;
            two += temp; 
        }
        return two;
    }
}
