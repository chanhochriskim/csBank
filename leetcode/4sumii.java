// sep 17, 10:30 ~ 10:55 passed (but needed some extra explanation on - part on the second for loop.)

/*
analysis: o(n^4) = not optimal. to make it o(n^2), two sum for nums1 and nums2, then
get the corresponding values for nums3 and nums 4 by looping through the value and their frequencies. 

** the - sign in front of num3 and num4 is necessary because we are getting i + j + k + l = 0, meaning i +j = -(k+l), to get the 0. if not, we will get the doubled value!
*/

class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // i j k l 
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                map.put(nums1[i]+nums2[j], map.getOrDefault(nums1[i]+nums2[j], 0) + 1);
            }
        }
        int count = 0;

        for (int k = 0; k < nums3.length; k++) {
            for (int l = 0; l < nums4.length; l++) {
                count += map.getOrDefault(-(nums3[k]+nums4[l]), 0);
            }
        }


        return count; 
    }
}
