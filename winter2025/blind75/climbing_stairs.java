// jan 7 -- 10:00pm ~ 10:10pm
/*
- 2 ways to reach a specific step. (either 1 step or 2 steps)
- set 2 variables: one / two, loop until n-1. 
    - save one value to temp.
    - one inherits two value.
    - two += temp.
- return two which contains combined values.

*/

class Solution {
    public int climbStairs(int n) {
        int one = 1;
        int two = 1;

        for (int i = 0; i < n - 1; i++) {
            int temp = one;
            one = two;
            two += temp;
        }

        return two;
    }
}
