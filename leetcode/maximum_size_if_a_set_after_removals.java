// nov 17 -- couldn't really lock in... 
/* explanation (Ayush, Easy video explanation w Proofs using Set)
- code determines the max size of a set formed by merging 2 sets.
- it considers the size of first set, then iterates through second set, incrementing the size based on elements not present in first set, upto half the size of second set.
T.C: O(nlog(n))
S.C: O(n)
*/

class Solution {
    public int maximumSetSize(int[] nums1, int[] nums2) {
        Set<Integer> s1 = new HashSet();
        Set<Integer> s2 = new HashSet();

        for (int i : nums1) {
            s1.add(i);
        }
        for (int i : nums2) {
            s2.add(i);
        }

        // ex: n1 [1 2 3 4 5 6] n2 [2 3 2 3 2 3]
        int length = nums1.length; // nums1 & nums2 = same length
        int x = s1.size(); // 6, [1 2 3 4 5 6]
        int y = s2.size(); // 2, [2 3]

        int ans = Math.min(length / 2, x); // min: 3, 6 = 3. 
        int remaining = x - ans; // 6 - 3 = 3. (3 more to go)
        int c = 0; // distinct values gained from nums2

        for (int i : s2) {
            if (!s1.contains(i)) {
                c++;
            } else if (remaining > 0) {
                c++;
                remaining--;
            }
            if (c >= length / 2) { 
                break;
            }
        }
        return ans + c; // final: ans = 3, c = 2 --> ans 5
    }
}
