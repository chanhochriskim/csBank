// jan 14 -- 11:25pm ~ 11:45pm (did it on my own!)
// flip half the side / reverse order. 

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j]; // matrix[0][1] = 1.
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            int a = 0;
            int b = n - 1;
            while (a < b) {
                int temp = matrix[i][a]; // 5 in 5 1 9 11.
                matrix[i][a] = matrix[i][b];
                matrix[i][b] = temp;
                a++;
                b--;
            }
        }
    }
}
