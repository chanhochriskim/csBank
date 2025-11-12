// nov 12 - 9:55am - 10:25am

/* analysis
- stack is for indices tracker, int[] ans is for # of days of warm temp for each day.
- loop over the temperatures / while (stack != empty && temp[stack.peek] < temp[i])
    - then we pop out the peek & update the ans[stack.peek] with i - stack.peek for # of days
*/
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack(); // indices tracker
        int[] ans = new int[temperatures.length]; // num of days of warm temp for each
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                ans[stack.peek()] = i - stack.peek(); 
                stack.pop(); 
            }
            stack.push(i);
        }
        return ans;
    }
}
