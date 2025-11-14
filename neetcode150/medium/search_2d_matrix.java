// nov 14 - 10:00 am ~ 10:30 am (99% by myself)
/* O(M * log(n))
- tl dr; get the row, then do binary search
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (target < matrix[0][0] || target > matrix[matrix.length - 1][matrix[0].length - 1]) return false;
        int[] first = new int[matrix.length]; // collect first val of rows.
        for (int i = 0; i < matrix.length; i++) {
            first[i] = matrix[i][0];
        }

        // 1. find in which row the target resides in. 
        int row = -1;
        for (int i = 0; i < first.length; i++) {
            if (first[i] == target) return true;
            if (first[i] > target) {
                row = i - 1; // ex: target resides in 1st row.
                break;
            }
        }
        if (row == -1) row = matrix.length - 1; // in case its on the last row
        
        // 2. now that we know what row it resides, conduct binary search
        int a = 0;
        int b = matrix[0].length - 1;
        while (a <= b) {
            int mid = a + (b - a) / 2;
            if (matrix[row][mid] == target) {
                return true;
            } else {
                if (matrix[row][mid] < target) {
                    a = mid + 1;
                } else {
                    b = mid - 1;
                }
            }
        }

        return false;
    }
}

// for the optimal O(log(m*n))
/*
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0, right = m*n - 1;

        while (left <= right) {
            int mid = left + (right - left)/2;
            int r = mid / n;
            int c = mid % n;

            if (matrix[r][c] == target) return true;
            if (matrix[r][c] < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }
}


*/
