// jan 19 - 11:45pm - 12:05am
// hashset. if we reach the same number again, that means we're stuck in a loop --> bad.

class Solution {
    public boolean isHappy(int n) {
        Set<Integer> visited = new HashSet();
        
        while (!visited.contains(n)) {
            visited.add(n);
            n = helper(n);
            if (n == 1) return true;
        }
        return false;
    }

    public int helper(int n) {
        int ans = 0;
        while (n > 0) {
            // get the last digit, apply it into ans, then repeat.
            int a = n % 10; // 512, 2.
            ans += (a * a); // 4, 1, 25... 30
            n = n / 10; // 51 now. got rid of 2.
        }
        return ans;
    }
}
