class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        Arrays.sort(nums); // sorted: [-4, -1, -1, 0, 1, 2];
        Set<List<Integer>> set = new HashSet(); 

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue; // to avoid repeating triplets. 
            int a = i + 1; // always move by 1 forward
            int b = nums.length - 1; // fixed 

            while (a < b) { 
                int sum = nums[i] + nums[a] + nums[b]; // ex: -4 + (-1) + (2) 
                if (sum == 0) { // if equal to 0, then we add it to the ans list.
                    if (!set.contains(Arrays.asList(nums[i], nums[a], nums[b]))) {
                        set.add(Arrays.asList(nums[i], nums[a], nums[b]));
                        ans.add(Arrays.asList(nums[i], nums[a], nums[b])); 
                    }

                    // skip duplicates for a and b
                    while (a < b && nums[a] == nums[a+1]) a++;
                    while (a < b && nums[b] == nums[b-1]) b--;

                    a++;
                    b--;
                } else if (sum < 0) {
                    a++;
                } else {
                    b--;
                }
    
            }
        } 

        return ans;
    }
}
