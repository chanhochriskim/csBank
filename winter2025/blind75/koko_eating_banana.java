// jan 7 (not part of blind 75, but still wanted to give it a try) 
// 10:30pm ~ 10:50pm

/* analysis (neetcode) O(log(piles) * piles.length)
- say piles: 3, 6, 7, 11. k can be 1~11. here, we are conducting bin search on k.
- get the k, and calculate the total times. if total < h, means value's too big. (ex: 6 < 8). --> try the smaller k values. 
- vice versa, if total > h, means it took way too long.
*/


class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        for (int p : piles) max = Math.max(max, p);

        int a = 1;
        int b = max;
        int ans = 0;

        while (a <= b) {
            int mid = a + (b - a) / 2;
           // System.out.println(mid);
            long total = 0; // temp total 

            for (int i = 0; i < piles.length; i++) {
                total += (int) Math.ceil((double) piles[i] / mid);
            }
            if (total <= h) {
                ans = mid;
                b = mid - 1;
            } else {
                a = mid + 1;
            }
        }


        return ans;
    }
}
