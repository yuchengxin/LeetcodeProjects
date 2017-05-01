package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author catyee
 *
 *         448 Given an array of integers where 1 ≤ a[i] ≤ n (n = size of
 *         array), some elements appear twice and others appear once.
 * 
 *         Find all the elements of [1, n] inclusive that do not appear in this
 *         array.
 * 
 *         Could you do it without extra space and in O(n) runtime? You may
 *         assume the returned list does not count as extra space.
 * 
 *         Example: Input: [4,3,2,7,8,2,3,1] Output: [5,6]
 *
 *         译文：给出一个数组，数组中的长度为n，数组中元素满足 1 ≤ a[i] ≤ n，有一些重复出现了两次，剩余的只出现一次，
 *         还有一些没有出现，找出这些没有出现的数，要求：时间复杂度为O(n)，并且不占用额外的空间。
 *         
 *         思路：不占用额外空间的话，可以用数组的下标来记录没有出现过的数字。遍历数组，对于出现过的数字，就把对应的下标
 *         中记录的数字变成-1,比如5出现了，就把a[4]变为-1，但是有个问题如果直接把a[4]变为-1,那么a[4]这个位置记录的
 *         信息就丢失了，所以要用一个临时变量记录a[4]的信息，同时下一次处理时就从这个临时变量记录的位置开始处理。比如
 *         上面的例子：
 *         下标：0 1 2 3 4 5 6 7 
 *         数组：4 3 2 7 8 2 3 1 
 *         处理顺序为i=0,所以a[0]=4-->a[3]=7-->a[6]=3-->a[2]=2-->a[1]=3,这时候发现a[2]=-1
 *         那么i++，这时候a[1]=-1,所以i++...
 *         这样一次遍历完成之后，再遍历一次，哪个位置记录的数字不是-1，那对应的下标+1就是没出现过的数字。
 *
 */

public class Subject448 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = { 4, 3, 2, 7, 8, 2, 3, 1 };
		Subject448 temp = new Subject448();
		List<Integer> resault = temp.findDisappearedNumbers(nums);
		for (int i = 0; i < resault.size(); i++) {
			System.out.println(resault.get(i));
		}
	}

	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> resault = new ArrayList<Integer>();//用来记录结果
		if (nums == null) {
			return resault;
		}
		for (int i = 0; i < nums.length;) {//遍历数组
			int h = i;//用来记录遍历的位置
			int j = nums[i];
			while (j != -1 && nums[j - 1] != -1) {
				i = nums[j - 1];
				nums[j - 1] = -1;
				j = i;
			}
			i = ++h;//下一次遍历
		}
		for (int i = 0; i < nums.length; i++) {//第二次遍历，用来记录结果
			if (nums[i] != -1)
				resault.add(i + 1);
		}
		return resault;
	}

}
