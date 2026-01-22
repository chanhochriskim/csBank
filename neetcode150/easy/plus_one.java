// jan 21 - 8:27pm ~ 8:45pm
// loop from the very end, and if the end value < 9, return everything + 1. if not, update digits to have 1 extra index, and set digits[0] == 1.
class Solution {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
