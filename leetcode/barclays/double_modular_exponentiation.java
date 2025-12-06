// dec 6 -- 9:20am ~ 9:40am
/*
- literally math lmao
- for each arrays, go over the formula, if true, then add that trial index to the ans.

*** ok, i was passing literally all the test cases, but had to use customPow (answer key from someone else) to handle the overflow.
*/

class Solution {
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < variables.length; i++) {
            int[] var = new int[4];
            var[0] = variables[i][0];
            var[1] = variables[i][1];
            var[2] = variables[i][2];
            var[3] = variables[i][3];

            // now, do the math
            double a = Math.pow(var[0], var[1]);
            double b = Math.pow(a % 10, var[2]);
            double c = b % var[3];
            if (target == (int) c) {
                ans.add(i);
            }
        }

        return ans;
    }
}

/* <------ answer key version ------> */

class Solution {
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> ans = new ArrayList();
        int i = 0;
        for (int[] row : variables) {
            long a = customPow(row[0], row[1], 10);
            long b = customPow((int) (a % 10), row[2], row[3]);
            if (b == target) {
                ans.add(i);
            }
            i++;
        }
        return ans;
    }

    // binary exponentiation
    public long customPow(int base, int exponent, int mod) {
        long result = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exponent /=2;
        }

        return result;
    }
}
