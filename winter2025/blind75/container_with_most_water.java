// dec 23 - 9:45pm ~ 9:47pm (2 min LOL)

class Solution {
    public int maxArea(int[] height) {
        int a = 0;
        int b = height.length - 1;
        int ans = 0;

        while (a < b) {
            ans = Math.max(ans, (b - a) * Math.min(height[a], height[b]));
            if (height[a] < height[b]) {
                a++;
            } else {
                b--;
            }
        }

        return ans;
    }
}
