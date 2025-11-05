// nov 5 -- 8:55am ~ 9:05am
/* O(n) basically sliding window = 2 pointers starting at the beginning
- if prices below 0, move only a.
- else, compare each then update the best price.
*/

class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 1 || prices.length == 0) return 0;
        int a = 0;
        int b = 1;
        int best = 0;

        while (b != prices.length) {
            if (prices[b] - prices[a] < 0) {
                a++;
            } else {
                best = Math.max(best, prices[b] - prices[a]);
                b++;
            }
        }

        return best;
    }
}
