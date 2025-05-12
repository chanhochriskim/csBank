class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        // edge case
        if (n == 0) {
            ans[0] = 0;
            return ans;
        } else if (n == 1) {
            ans[0] = 0;
            ans[1] = 1;
            return ans;
        }

        ans[0] = 0;
        ans[1] = 1;
        int offset = 2;

        for (int i = 2; i <= n; i++) {
            if ((offset * 2) == i) {
                offset = i; // update offset
            }
            ans[i] = ans[i - offset] + 1;
        }


        return ans;
    }
}
