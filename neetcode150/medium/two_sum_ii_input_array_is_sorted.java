// nov 4 - 8:15am ~ 8:20am
/* analysis
- very basic two pointer
*/

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers.length == 0) {
            return new int[] {};
        }
        int i = 0;
        int j = numbers.length - 1;
        while (i <= j) {
            if (numbers[i] + numbers[j] == target) {
                return new int[] {i + 1, j + 1};
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                j--;
            }
        }

        return new int[] {};
    }
}
