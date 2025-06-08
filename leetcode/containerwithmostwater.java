"class Solution {
    public int maxArea(int[] height) {
        if (height.length == 0) return 0;
        int vertical = 0;
        int length = 0;
        int result = 0;

        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                vertical = Math.min(height[i], height[j]); // ex: 1, 8 (1);
                length = j - i; // ex: 1 - 0 = 1.
                result = Math.max(result, vertical * length);
            }
        }

        return result;
    }
}" // my brute force way of solving it O(n^2), which isnt optimal. 

// here is the optimized two pointer approach. 
class Solution {
    public int maxArea(int[] height) {
        if (height.length == 0) return 0;
        int left = 0;
        int right = height.length - 1;
        int h = 0;
        int length = 0;
        int result = 0;
        while (left < right) {
            h = Math.min(height[left], height[right]);
            length = right - left;
            result = Math.max(h * length, result);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return result;
    }
}
