// oct 22 - 9:45am ~ 10:10am
// VISA
/* analysis: --> but due to hashmap, its O(n^2) --> slow.
1. for every i, do the 2 sum method!
2. if matches, append that to the answer.
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> set = new HashSet();
        Arrays.sort(nums); // -4 -1 -1 0 1 2.
        
        for (int i = 0; i < nums.length - 2; i++) {
            HashMap<Integer, Integer> map = new HashMap();

            for (int j = i+1; j < nums.length; j++) {
                int value = -(nums[i] + nums[j]); // ex: -(-1+-1)=-(-2)= does 2 exist? (-1, -1, 2)
                if (map.containsKey(value)) {
                    set.add(Arrays.asList(nums[i], nums[j], value));
                }     
                map.put(nums[j], j); // storre index.
            }
        }

        return new ArrayList(set);
    }
}

// two pointer approach instead.
// oct 22 - 9:45am ~ 10:10am
// VISA
/* analysis: 
1. for every i, do the 2 sum method!
2. if matches, append that to the answer.
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> set = new HashSet();
        Arrays.sort(nums); // -4 -1 -1 0 1 2.

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue; // duplicate skip (since sorted, it checks with the adjacent values.)
            int a = i + 1;
            int b = nums.length - 1;

            while (a < b) { // inner loop
                int sum = nums[i] + nums[a] + nums[b]; // 
                if (sum == 0 && !set.contains(Arrays.asList(nums[i], nums[a], nums[b]))) {
                    set.add(Arrays.asList(nums[i], nums[a], nums[b]));
                    a++;
                    b--;
                } else if (sum < 0) { // sum is something negative. bring a up.
                    a++;
                } else { // too big. bring b down. 
                    b--;
                }
            }

        }

        return new ArrayList(set);
    }
}
