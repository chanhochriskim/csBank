// my og version (didnt work due to int not handling more than 64 bits) -->gotta use bit manipulation
class Solution {
    public int concatenatedBinary(int n) {
        if (n == 0) return 0;
        String binary = "";

        for (int i = 1; i <= n; i++) {
            // convert to binary then concatenate (helper later)
            binary += Integer.toBinaryString(i); 
        }
        
        return convert2num(binary);
    }


    public int convert2num(String s) { 
        // 11011
        int actual = 0;
        int ans = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                ans += Math.pow(2, actual);
            }
            actual++;
        }

        return ans;
    }
}
