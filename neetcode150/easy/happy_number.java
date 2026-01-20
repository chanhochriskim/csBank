// jan 19 - 11:45pm ~ 

class Solution {
    public boolean isHappy(int n) {
        while () {
            String str = String.valueOf(n); 
            char[] ch = str.toCharArray(); // "1", "9"
            
            for (int i = 0; i < ch.length; i++) {
                int a += (ch[i] - '0') * (ch[i] - '0');
            }
            if (a == 1) return true;
            
        }

        return false;
    }
}
