// nov 14 -- 10:35am ~ 11:10am 
/* analysis (neetcode) O(log(piles) * piles.length)
- say piles: 3, 6, 7, 11. k can be 1~11. here, we are conducting bin search on k.
- get the k, and calculate the total times. if total < h, means value's too big. (ex: 6 < 8). --> try the smaller k values. 
- vice versa, if total > h, means it took way too long.
*/

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // get the max value within piles (neetcode)
        int max = 0;
        for (int p : piles) max = Math.max(max, p);

        int a = 1;
        int b = max;
        int ans = Integer.MAX_VALUE;
        while (a <= b) {
            int mid = a + (b - a) / 2;    
            long total = 0; // for int overflow question
            for (int i = 0; i < piles.length; i++) {
                total += (int) Math.ceil((double) piles[i] / mid); // error: no (piles[i] / mid), parenthesis around that!
            }
            if (total <= h) { // value too big. set ans as mid, and try if there's any smaller.
                ans = mid;
                b = mid - 1;
            } else {
                a = mid + 1;
            }
        }

        return ans;
    }
}
