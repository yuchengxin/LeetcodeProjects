package leetcode;

/**
 * 
 * @author catyee
 *
 *    240. Write an efficient algorithm that searches for a value in an m x
 *         n matrix. This matrix has the following properties:
 * 
 *         Integers in each row are sorted in ascending from left to right.
 *         Integers in each column are sorted in ascending from top to bottom.
 *         For example,
 * 
 *         Consider the following matrix:
 * 
 *         [ [1, 4, 7, 11, 15], [2, 5, 8, 12, 19], [3, 6, 9, 16, 22], [10, 13,
 *         14, 17, 24], [18, 21, 23, 26, 30] ]
 * 
 *         Given target = 5, return true. Given target = 20, return false.
 * 
 *         译文：一个int类型的二维数组，每一行递增，每一列递增，在数组中查询给定的某个值是否存在。
 *
 *         思路：从二维矩阵的右上角入手，如果给定的值大于这个值，则往下找，如果小于这个位置的值，则往左找，
 *         如果相等，则返回true
 */

public class Subject240 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int matrix[][] = { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 },
				{ 3, 6, 9, 16, 22 }, { 10, 13, 14, 17, 24 },
				{ 18, 21, 23, 26, 30 } };
		int target = 5;
		System.out.println(searchMatrix(matrix, target));

	}

	public static boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0|| (matrix.length == 1 && matrix[0].length == 0))
			return false;// 二维数组为空
		int column = matrix[0].length - 1;
		int row = 0;
		while (row < matrix.length && column >= 0) {
			if (matrix[row][column] == target) {
				return true;
			} else if (matrix[row][column] < target) {
				row++;
			} else if (matrix[row][column] > target) {
				column--;
			}

		}
		return false;
	}
}
