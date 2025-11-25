// nov 25 -- java version, 8:55am - 8:56am (took me < 1min lmao)

class Solution {
    public int minimumOperations(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += Math.min(nums[i] % 3, 3 - (nums[i] % 3));
        }
        return count;
    }
}

# nov 25 -- pythonmaxxing

class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        count = 0
        for i, num in enumerate(nums):
            count += min(num % 3, 3 - (num % 3))
        return count

        
