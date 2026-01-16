// jan 15 - 9:55pm ~ 10:10pm
/* O(m*n)
int left,right = 0, matrix[0] len (col 1 extra)
int top, bottom = 0, matrix len (row 1 extra)

while loop containing 4 for-loops for each direction
*/


class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList();
        int left = 0;
        int right = matrix[0].length; 
        
        int top = 0;
        int bottom = matrix.length;

        while (left < right && top < bottom) {
            // 1. left --> right
            for (int i = left; i < right; i++) {
                res.add(matrix[top][i]);
            }
            top++;

            // 2. top --> bottom
            for (int i = top; i < bottom; i++) {
                res.add(matrix[i][right - 1]);
            }
            right--;

            // sanity checker 
            if (!(left < right && top < bottom)) break;

            // 3. right - 1 --> left
            for (int i = right - 1; i >= left; i--) {
                res.add(matrix[bottom - 1][i]);
            }
            bottom--;

            // 4. bottom - 1 --> top.
            for (int i = bottom - 1; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
        }

        return res;        
    }
}
