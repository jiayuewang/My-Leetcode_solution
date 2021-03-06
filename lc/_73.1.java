package com.fishercoder.solutions;

/**
 * 73. Set Matrix Zeroes
 *
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

 Follow up:

 Did you use extra space?
 A straight forward solution using O(mn) space is probably a bad idea.
 A simple improvement uses O(m + n) space, but still not the best solution.
 Could you devise a constant space solution?
 */
public class _73 {

    public static class Solution1 {
        /**
         * Space: O(m*n)
         */
        public void setZeroes(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return;
            }
            int height = matrix.length;
            int width = matrix[0].length;
            boolean[][] zero = new boolean[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (matrix[i][j] == 0) {
                        zero[i][j] = true;
                    }
                }
            }
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (zero[i][j]) {
                        for (int k = 0; k < height; k++) {
                            matrix[k][j] = 0;
                        }
                        for (int k = 0; k < width; k++) {
                            matrix[i][k] = 0;
                        }
                    }
                }
            }
        }
    }

    public static class Solution2 {
        /**
         * Space: O(m+n)
         */
        public void setZeroes(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return;
            }
            int m = matrix.length;
            int n = matrix[0].length;
            boolean[] row = new boolean[m];
            boolean[] col = new boolean[n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        row[i] = true;
                        col[j] = true;
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (row[i] && col[j]) {
                        for (int k = 0; k < m; k++) {
                            matrix[k][j] = 0;
                        }
                        for (int k = 0; k < n; k++) {
                            matrix[i][k] = 0;
                        }
                    }
                }
            }
        }
    }

    public static class Solution3 {
        /**
         * Space: O(1)
         */
        public void setZeroes(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return;
            }
            int m = matrix.length;
            int n = matrix[0].length;
            boolean firstRow = false;
            boolean firstCol = false;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        if (i == 0) {
                            firstRow = true;
                        }
                        if (j == 0) {
                            firstCol = true;
                        }
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }

            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }

            if (firstRow) {
                for (int j = 0; j < n; j++) {
                    matrix[0][j] = 0;
                }
            }

            if (firstCol) {
                for (int i = 0; i < m; i++) {
                    matrix[i][0] = 0;
                }
            }
        }
    }
}

class Solution {
    public void setZeroes(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean row = false;
        boolean col =false;// for loop to find zero and set the fist element in row/col, if meet zero inner the matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    if(i == 0) row = true;
                    if(j == 0) col = true;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {// i 从1开始，但是要判断matrix[i][0] 为参照
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
         for (int i = 1; i < n; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        //改变第一行和第一列，要放在最后改变，因为其他行列作为参照改变
        if(row){
            for(int i = 0; i < n; i++){
                matrix[0][i] = 0;
            }
        }
        if(col){
            for(int i = 0; i < m; i++){
                matrix[i][0] = 0;
            }
        }
        
    }
}
