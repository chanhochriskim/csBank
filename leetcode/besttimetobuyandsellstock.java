// original
class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        int i = 0;
        int j = i + 1;

        while (i < prices.length - 1) {
            if (prices[i] < prices[j]) {
                max = Math.max(prices[j] - prices[i], max);

                while (j < prices.length - 1) {
                    j++;
                    if (prices[i] < prices[j]) {
                        max = Math.max(prices[j] - prices[i], max);
                    }
                }     
            }
            i++;
            j = i + 1;
        }
        return max;
    }
}

// optimal O(n) sliding window method.
class Solution {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;

        for (int price: prices) {
            if (price < min) {
                min = price;
            } else {
                max = Math.max(max, price - min);
            }
        }

        return max;
    }
}
