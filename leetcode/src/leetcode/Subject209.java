package leetcode;

/**
 * 
 * @author catyee
 *
 *         209、 Given an array of n positive integers and a positive integer s,
 *         find the minimal length of a contiguous subarray of which the sum ≥
 *         s. If there isn't one, return 0 instead.
 * 
 *         For example, given the array [2,3,1,2,4,3] and s = 7, the subarray
 *         [4,3] has the minimal length under the problem constraint.
 *
 *         译文：给出一个有n个元素的正整数数组，和一个正整数s，找到最短的连续子序列使和大于等于s，如果不存在则返回0.
 *         比如数组是[2,3,1,2,4,3]，s=7,有[4,3]满足要求，所以返回长度2。
 *
 *         思路：用滑动窗口的思路去解决，用两个指针start和end，end向前走直到和大于s，记录下长度，然后start向前走
 *         一步或者几步，直到和小于s，这时候end又向前走直到和大于s，更新长度。
 *
 */

public class Subject209 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 1, 4, 4 };
		System.out.println(minSubArrayLen(4, nums));
	}

	public static int minSubArrayLen(int s, int[] nums) {
		if (nums == null || nums.length == 0)return 0;// 数组为空
		
		int length = 0;// 和大于等于s时的长度
		int lengthmin = nums.length + 1;// 和大于s的所有长度中的最小值,初始值为数组的长度+1,便于后面的判断
		int start = 0, end = 0;
		int sum = 0;// start到end的数的和
		
		while (start < nums.length && end < nums.length) {// 边界条件控制
			sum += nums[end];

			if (sum >= s) {// 如果和大于等于s之后，要更新长度和滑动窗口
				length = end - start + 1;// 当前大于等于s的长度
				if (lengthmin > length) {// 更新最小长度
					lengthmin = length;
				}
				while (sum >= s && start < nums.length - 1) {// 更新滑动窗口。注意这里也要更新长度，同时注意边界条件
					sum -= nums[start];// sum减去start，同时start右移一步
					start++;
					if (sum >= s) {// 如果start右移一步之后，sum仍然大于等于7，那么就要更新长度，同时也要更新最小长度
						length--;
						if (lengthmin > length) {
							lengthmin = length;
						}
					}
				}
			}

			end++;
		}
		if (lengthmin > nums.length) {// 如果最小长度比数组长度还大，说明没有找到，那就返回0
			lengthmin = 0;
		}
		return lengthmin;

	}

}
