// nov 5 -- 8:30 am ~ 8:50am
/* 2 pointer analysis
- pointer a & b.
- compare pointer sizes, move the one that's smaller.
- very trivial after that
*/
class Solution {
    public int trap(int[] height) {
        int a = 0;
        int b = height.length - 1;
        int water = 0;
        int bestA = height[a];
        int bestB = height[b];

        while (a < b) {
            if (height[a] < height[b]) {
                a++;
                if (height[a] < bestA) { // water trap
                    water += bestA - height[a];
                } else { // no trapping water. update bestA
                    bestA = height[a];
                }
            } else {
                b--;
                if (height[b] < bestB) {
                    water += bestB - height[b];
                } else {
                    bestB = height[b];
                }
            }
        }


        return water;
    }
}
