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

// aug 16 -- 4:40 ~ 5:00 --> attempted without looking at the solution. (didn't use two pointers zamn)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // i = -4. if any other matches with formula, if set doesn't contain it, add it.
        // formula: i + ((i + 1) + (i + x)) == 0. 
        // loop until nums.size - 2. [-1, -1, 2] [-1, 0, 1]
        List<List<Integer>> ans = new ArrayList();
        Arrays.sort(nums); // sorted: -4 -1 -1 0 1 2. 
        Set<List<Integer>> set = new HashSet();

        for (int i = 0; i < nums.length - 2; i++) { // first pointer = i + 1
            for (int j = i + 2; j < nums.length; j++) { // second pointer 
                if ((nums[i] + nums[i+1] + nums[j] == 0) && !set.contains(Arrays.asList(nums[i], nums[i + 1], nums[j]))) {
                    set.add(Arrays.asList(nums[i], nums[i + 1], nums[j]));
                    ans.add(Arrays.asList(nums[i], nums[i + 1], nums[j]));
                } 
            }            
        }
        return ans;
    }
}

// aug - 16: 5:00 ~ 5:10 revised the actual two pointer method, and re-did it. understood it yay
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // use two pointers
        List<List<Integer>> ans = new ArrayList();
        Set<List<Integer>> set = new HashSet();
        Arrays.sort(nums); // -4, -1, -1, 0, 1, 2

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // to avoid duplicates (-1 no need)
            int a = i + 1; // the very next
            int b = nums.length - 1; // end value

            while (a < b) { // inner loop
                int sum = nums[i] + nums[a] + nums[b];
                if (sum == 0 && !set.contains(Arrays.asList(nums[i], nums[a], nums[b]))) {
                    set.add(Arrays.asList(nums[i], nums[a], nums[b]));
                    ans.add(Arrays.asList(nums[i], nums[a], nums[b]));
                    a++;
                    b--;
                } else if (sum < 0) { // sum is something negative. bring a up.
                    a++;
                } else { // too big. bring b down. 
                    b--;
                }         
            }
        }
        return ans;
    }
}
