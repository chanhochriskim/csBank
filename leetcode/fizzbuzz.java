class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<String>();
        // edge case handler.
        if (n == 0) {
            return result;
        } else if (n == 1) {
            result.add("1");
            return result;
        } else if (n == 2) {
            result.add("1");
            result.add("2");      
            return result;     
        }
        result.add("1");
        result.add("2");
        for (int i = 3; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(Integer.toString(i));
            }
        }
        return result;
    }
}
