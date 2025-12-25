// dec 24 - 9:45pm ~ 9:55pm

class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int ans = 0;
        int a = 0;
        for (int b = 1; b < prices.length; b++) {
            if (prices[b] - prices[a] >= 0) {
                ans = Math.max(ans, prices[b] - prices[a]);
            } else if (prices[a] > prices[b]) {
                a = b;
            } else {
                a++;
            }
        }
        return ans;
    }
}
