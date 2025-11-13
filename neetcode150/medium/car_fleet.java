// nov 13 -- 9:05am ~ 10:00am
/* analysis (neetcode explanation) O(nlogn) / sc: O(n)
- use 2d array for pair (position/speed) & sort them in an increasing order
- use stack for counting how many fleets in total.
- add the calculated value (time - position) / speed --> time it takes to reach an end
- if stack size >= 2 AND stack.peek is smaller (<=) than the stack.get(stack.size - 2) --> the one in the bottom (ahead of the tested car), then we pop the peek (merge 2 cars into 1)

*/

class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int[][] pair = new int[position.length][2]; // pair -- position / speed
        for (int i = 0; i < position.length; i++) {
            pair[i][0] = position[i];
            pair[i][1] = speed[i];
        }

        Arrays.sort(pair, (a, b) -> Integer.compare(b[0], a[0])); // sort based on position
        Stack<Double> stack = new Stack(); 
        for (int[] p : pair) {
            stack.push((double) (target - p[0]) / p[1]); // push the time it takes to reach the target
            if (stack.size() >= 2 && stack.peek() <= stack.get(stack.size() - 2)) {
                stack.pop();
            }
        }

        return stack.size();
    }
}
