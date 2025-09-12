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

// bit manipulation method 
// sep 11 --> bit manipulation method

class Solution {
    public int concatenatedBinary(int n) {
        final long modulo = (long) (1e9 + 7);
        long result = 0;

        int binaryDigit = 0; // indicates # of binarydigits we need to shift left. <<

        for (int i = 1; i <= n; i++) {

            // if it's power of 2 --> every power of 2, we need 1 additional digit.
            if ((i & (i - 1)) == 0) {
                binaryDigit++; 
            }

            result = ((result << binaryDigit) + i) % modulo; 
        }
        


        return (int) result;
    }
}
