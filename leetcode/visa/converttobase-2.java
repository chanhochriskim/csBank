// oct 22: 8:35am ~ 9:10am
// VISA -- key: Diya Mohe (was speed running so)

class Solution {
    public String baseNeg2(int n) {
        if (n==0) return "0";

        StringBuilder sb = new StringBuilder();

        // loops until number becomes 0
        while (n != 0) {
            int remainder = n % (-2);
            int quotient = n / (-2);

            // if remainder is negative, fix it
            if (remainder < 0) {
                // make remainder positive & adjust quotient to maintain correctness
                remainder += 2;
                quotient += 1;
            }
            sb.append(remainder); // build number 
            n = quotient; 
        }

        return sb.reverse().toString();
    }
}
