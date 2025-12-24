// dec 23 - 9:25pm ~ 9:35pm

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> set = new HashSet();
        Arrays.sort(nums);
        // List<List<Integer>> ans = new ArrayList();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue; // without this, it's hella slow
            int a = i + 1; 
            int b = nums.length - 1;
            while (a < b) {
                if ((nums[i] + nums[a] + nums[b] == 0) && !set.contains(Arrays.asList(nums[i], nums[a], nums[b]))) {
                    set.add(Arrays.asList(nums[i], nums[a], nums[b]));
                } else if (nums[i] + nums[a] + nums[b] < 0) {
                    a++;
                } else {
                    b--;
                }
            }
        } 
        return new ArrayList(set);
    }
}
