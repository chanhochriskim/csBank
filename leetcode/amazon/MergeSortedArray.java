// Oct 19 -- 2:00 pm ~ 2:15pm
/* analysis:
1. start from the end --> compare as you go by. 
2. 4 cases: 
    - if y runs out, just break; (leave nums1 as it is)
    - if x runs out, append all y into the nums1.
    - if nums1[x] < nums2[y] --> append y, y--
    - else --> append x, x--

*/

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // start form behind
        int x = m - 1;
        int y = n - 1;

        
        for (int i = m + n - 1; i >= 0; i--) {
            if (x < 0) { 
                nums1[i] = nums2[y];
                y--;
            } else if (y < 0) {
                break;
            } else if (nums1[x] < nums2[y]) {
                nums1[i] = nums2[y];
                y--;
            } else {
                nums1[i] = nums1[x];
                x--;
            }
        }
    }
}
