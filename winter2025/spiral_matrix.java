// dec 21 - 8:35pm - 9:20pm
/* O(m*n)
int left,right = 0, matrix[0] len (col 1 extra)
int top, bottom = 0, matrix len (row 1 extra)

while loop containing 4 for-loops for each direction
*/

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList();

        int left = 0;
        int right = matrix[0].length; // 1 outward (column)
        int top = 0;
        int bottom = matrix.length; // 1 outward (row)

        while (left < right && top < bottom) {
            for (int i = left; i < right; i++) {
                res.add(matrix[top][i]);
            }
            top ++; // top = down by 1

            for (int i = top; i < bottom; i++) {
                res.add(matrix[i][right - 1]);
            }
            right--;

            if (!(left < right && top < bottom)) break;

            for (int i = right - 1; i >= left; i--) {
                res.add(matrix[bottom - 1][i]);
            }
            bottom--;
            
            for (int i = bottom - 1; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
        }

        return res;
    }
}
