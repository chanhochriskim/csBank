// nov 4 -- 9:00am ~ 9:10am 
/* analysis O(n) / S.C O(1)
- yk the drill lol (two pointer, lower value moves. update the max if needed)
*/

class Solution {
    public int maxArea(int[] height) {
        int a = 0;
        int b = height.length - 1;
        int max = 0;
        int curr = 0;
        while (a < b) {
            curr = (b - a) * Math.min(height[a], height[b]);
            max = Math.max(curr, max);
            if (height[a] < height[b]) {
                a++;
            } else {
                b--;
            }
        }

        return max;
    }
}
