// aug 24 -- prefixSum (paypal prep) 10:30 ~ 

class NumArray {
    int prefix[]; // 1. declare prefix array. 

    public NumArray(int[] nums) {
        prefix = new int[nums.length]; // same size as nums
        prefix[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            prefix[i] += nums[i] + prefix[i - 1]; // prefix's prev val + nums current.
        }
    }
    
    public int sumRange(int left, int right) {
        if (left == 0) return prefix[right];
        return prefix[right] - prefix[left - 1]; // get the valid range. 
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
