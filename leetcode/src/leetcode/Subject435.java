package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * @author catyee
 *
 *      435、Given a collection of intervals, find the minimum number of intervals
 *         you need to remove to make the rest of the intervals non-overlapping.
 * 
 *         Note: You may assume the interval's end point is always bigger than
 *         its start point. Intervals like [1,2] and [2,3] have borders
 *         "touching" but they don't overlap each other. 
 *         
 *         Example 1: Input: [[1,2], [2,3], [3,4], [1,3] ]
 *         Output: 1
 *         Explanation: [1,3] can be removed and the rest of intervals are
 *         non-overlapping. 
 *         
 *         Example 2: Input: [ [1,2], [1,2], [1,2] ]
 *         Output: 2
 *         Explanation: You need to remove two [1,2] to make the rest of
 *         intervals non-overlapping. 
 *         
 *         Example 3: Input: [ [1,2], [2,3] ]
 *         Output: 0
 *         Explanation: You don't need to remove any of the intervals since
 *         they're already non-overlapping.
 *         
 *         译文：给定一个Interval数组，每一个Interval对象包含一个start和end，代表一段长度，在整个数组中删除最少的节点，
 *         使得每个Interval对象代表的长度没有重合。
 *         
 *         思路：本题即为在一个时间段内能安排最大活动数的简单变形。应用贪心的思想，首先将所有的interval按照结束时间排序，
 *         然后找出不符合的部分就可以了。
 */

public class Subject435 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	public int eraseOverlapIntervals(Interval[] intervals) {
		if (intervals == null) {
			return 0;
		}
		//匿名内部类重写Comparator来对intervals排序
		Arrays.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				// TODO Auto-generated method stub
				return o1.end - o2.end;
			}
		});

		int lengh = intervals.length;
		int count = 0;//用来记录不符合的个数
		int i = 0, j = 1;
		while (i < lengh && j < lengh) {
			if (intervals[i].end > intervals[j].start) {//不符合
				count++;
				j++;
			} else {
				i = j;
				j++;

			}
		}
		return count;

	}

	class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

}
