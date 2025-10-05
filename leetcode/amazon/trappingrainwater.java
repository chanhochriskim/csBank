// oct 5 - amazon

class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n - 1;
        int water = 0;

        // each will dynamically compared and changed.
        int leftmax = height[left];
        int rightmax = height[right];

        while (left < right) {
            if (height[left] < height[right]) { 
                left++;
                if (height[left] > leftmax) {
                    // update leftmax
                    leftmax = height[left];
                } else {
                    // water retainer activated.
                    water += leftmax - height[left];
                }
            } else {
                right--;
                if (height[right] > rightmax) {
                    rightmax = height[right];
                } else {
                    // water retainer activated.
                    water += rightmax - height[right];
                }
            }


        }
        return water; 
    }
}
