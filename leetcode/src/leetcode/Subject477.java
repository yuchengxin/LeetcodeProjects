package leetcode;

/**
 * 
 * @author catyee
 *
 *     477    The Hamming distance between two integers is the number of positions
 *         at which the corresponding bits are different.
 *         Now your job is to find the total Hamming distance between all pairs
 *         of the given numbers.
 * 
 *         Example: 
 *         Input: 4, 14, 2
 *         Output: 6
 * 
 *         Explanation: In binary representation, the 4 is 0100, 14 is 1110, and
 *         2 is 0010 (just showing the four bits relevant in this case). So the
 *         answer will be: HammingDistance(4, 14) + HammingDistance(4, 2) +
 *         HammingDistance(14, 2) = 2 + 2 + 2 = 6. 
 *         
 *         Note: Elements of the given array are in the range of 0 to 10^9 Length of
 *          the array will not exceed 10^4.
 * 
 *         译文：翰明距离是指两个数用二进制表达的时候，逐位进行比较，位不同的位的总和。现在给出一个数组，然后计算 数组中每一对 数的翰明距离总和。
 * 
 *         思路:传统思路就是找到每一对数，然后计算每一对数的翰明距离，然后求出总和。计算一对数的翰明距离就是将两个数异或，然后
 *         统计异或后的值中的1的个数。这种方法因为要找到每一对数，所以时间复杂度是O(n^2)。
 * 
 *         更好的方法是所有数一起逐位进行考虑，每一位要么是0要么是1,统计所有数这一位是1的个数，假设统计结果有count个1,那
 *         么就有n-count个0，也就是将数组分成了两部分，一部分是这一位是1的数，另一部分是这一位是0的数，分别从这两部分取出一个数
 *         构成数对，这样的数对在这一位上才会贡献翰明距离。这样的数对总共有count*(n-count)个，所以这一位上贡献的翰明距离的总和
 *         就是count*(n-count);对每一位都这样计算，然后计算总和就是最终的结果。时间复杂度是O(n)。比如上面的例子：
 *         4： 0100
 *         14：1110
 *         2： 0010
 *         最高位有1个1两个0,也就是说这一位上能贡献翰明距离的数对只能为（14,4）和（14,2）这一位贡献的翰明距离就是2（1×（3-1）=2）
 *         第二位有2个1一个0,那么这一位贡献的翰明距离就是2×（3-2）=2
 *         第三位有2个1一个0,那么这一位贡献的翰明距离就是2×（3-2）=2
 *         第四位贡献的翰明距离是0。
 *		   所以最终结果为2+2+2=6。
 *
 */

public class Subject477 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 6,1,8,6,8 };
		Subject477 temp = new Subject477();
		System.out.print(temp.totalHammingDistance(nums));
	}
	
	public int totalHammingDistance(int[] nums) {
		if (nums == null)
			return 0;
		int distance = 0;
		for(int i=31;i>=0;i--){
			int count=0;
			for(int j=0;j<nums.length;j++){
				if((nums[j]&(1<<i))>0){//统计数组中所有数在这一位为1的个数
					count++;
				}
			}
			distance+=(count*(nums.length-count));
			count=0;
		}
		return distance;
	}

	//传统思路
//	public int totalHammingDistance(int[] nums) {
//		if (nums == null)
//			return 0;
//		int distance = 0;
//		for (int i = 0; i < nums.length; i++) {
//			for (int j = i + 1; j < nums.length; j++) {
//				distance += hammingDistance(nums[i], nums[j]);
//			}
//		}
//		return distance;
//	}
//
//	public int hammingDistance(int A, int B) {
//		int num = A ^ B;
	    //统计一个数的二进制表达中1的个数
//		num = (num & 0x55555555) + ((num >>> 1) & 0x55555555);
//		num = (num & 0x33333333) + ((num >>> 2) & 0x33333333);
//		num = (num & 0x0f0f0f0f) + ((num >>> 4) & 0x0f0f0f0f);
//		num = (num & 0x00ff00ff) + ((num >>> 8) & 0x00ff00ff);
//		num = (num & 0x0000ffff) + ((num >>> 16) & 0x0000ffff);
//		return num;
	    //统计一个数的二进制表达中1的个数的另一种方法
//		// int c = 0;
//		// for (; num > 0; c++) {
//		// num &= (num - 1); // 清除最右边的 1
//		// }
//		// return c;
//	}
}
