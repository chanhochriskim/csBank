// dec 20 -- 9:30pm ~ 9:40pm


class Solution {
    public int maxArea(int[] height) {
        int res = 0;

        int a = 0;
        int b = height.length - 1;

        while (a < b) {
            int c = Math.min(height[a], height[b]);
            res = Math.max(res, c * (b - a));
            if (height[a] < height[b]) {
                a++;
            } else {
                b--;
            }
        }

        return res;
    }
}
