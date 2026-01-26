// jan 26 - 5:30pm ~ 5:50pm
/*
basic rules: 
I = 1
V = 5
X = 10
L = 50
C = 100
D = 500
M = 1000

I.V = 4
I.X = 9

X.L = 40
X.C = 90

C.D = 400
C.M = 900

*/

class Solution {
    public int romanToInt(String s) {
        int ans = 0;
        int i = 0;

        while (i != s.length()) {
            char a = s.charAt(i);
            
            if (a == 'I') {
                if (i != s.length() - 1) {
                    if (s.charAt(i + 1) == 'V') {
                        ans += 4;
                        i += 2;
                    } else if (s.charAt(i + 1) == 'X') {
                        ans += 9;
                        i += 2;
                    } else {
                        ans += 1;
                        i++;
                    }
                } else {
                    ans += 1;
                    i++;
                }
            } else if (a == 'X') {
                if (i != s.length() - 1) {
                    if (s.charAt(i + 1) == 'L') {
                        ans += 40;
                        i += 2;
                    } else if (s.charAt(i + 1) == 'C') {
                        ans += 90;
                        i += 2;
                    } else {
                        ans += 10;
                        i++;
                    }
                } else {
                    ans += 10;
                    i++;
                }
            } else if (a == 'C') {
                if (i != s.length() - 1) {
                    if (s.charAt(i + 1) == 'D') {
                        ans += 400;
                        i += 2;
                    } else if (s.charAt(i + 1) == 'M') {
                        ans += 900;
                        i += 2;
                    } else {
                        ans += 100;
                        i++;
                    }
                } else {
                    ans += 100;
                    i++;
                }
            } else if (a == 'V') {
                ans += 5;
                i++;
            } else if (a == 'L') {
                ans += 50;
                i++;
            } else if (a == 'D') {
                ans += 500;
                i++;
            } else if (a == 'M') {
                ans += 1000;
                i++;
            }
        }

        return ans;
    }
}
