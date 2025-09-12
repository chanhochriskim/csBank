// sep 12, friday morning 9:25 - 9:55 (tried to do int left, right method, but came up with map)

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap();
        map.put(numbers[0], 0); // [0, 3] -- key: 0 value: 3. 

        for (int i = 1; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                return new int[] {map.get(target - numbers[i]) + 1, i + 1};
            }
            map.put(numbers[i], i); // each # --> unique index. 
        }

        return new int[] {};
    }
}
