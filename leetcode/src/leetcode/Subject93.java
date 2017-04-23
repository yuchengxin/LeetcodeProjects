package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author catyee
 *
 *     93、 Given a string containing only digits, restore it by returning all
 *         possible valid IP address combinations.
 * 
 *         For example: Given "25525511135",
 * 
 *         return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 *         
 *         译文：给出一个字符串，返回所有可能的IP地址
 *         
 *         思路：回溯法。ip地址分为四段，每段的数字范围为0~255,但是不会出现025或者01这种情况。
 *         每一段都只有三种情况，要么为一位数，要么为2位数，要么为三位数
 */
public class Subject93 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "25525511135";
		Subject93 a = new Subject93();
		List<String> x = a.restoreIpAddresses(s);
		for (int i = 0; i < x.size(); i++) {
			System.out.println(x.get(i));
		}
	}
	
	/**
	 * 程序的入口
	 * @param s
	 * @return
	 */
	public List<String> restoreIpAddresses(String s) {
		List<String> temp = new ArrayList<String>();//结果集
		StringBuilder arr = new StringBuilder();
		select(s, 0, 0, arr, temp, 0);//处理
		return temp;
	}
	
	/**
	 * 回溯法选择合理的数字，拼接出正确的Ip地址
	 * 
	 * @param s:待处理的字符串
	 * @param index:处理的初始位置
	 * @param step:处理的步数，Ip地址有4段，所以是4步，初始值是0
	 * @param arr:用来拼接待处理IP地址
	 * @param temp：用来存储拼接完成的IP地址
	 * @param pos:步长，可能是1,2,3，未处理之前步长为0
	 */

	public void select(String s, int index, int step, StringBuilder arr,List<String> temp, int pos) {
		
		//递归退出条件：如果整个字符串处理完最后一个字符，并且刚好分成了4段，就结束递归
		if ((s.length() - arr.toString().length() + step) == 0 && step == 4) {
			//将得到的正确结果放入temp中去，因为拼接的原因，要把最后的"."去掉。
			temp.add(arr.toString().substring(0, arr.toString().length() - 1));
			return;
		}
		
		//一个字符串25525511135，如果第一段取2,后面有10位，而每一段最长为3位，3段也只有9位，这种情况就直接结束递归，然后回溯
		if ((s.length() - arr.toString().length() + step) > 3 * (4 - step)) {
			return;
		}
		
		//一段取一位数的情况
		if (index < s.length() && judge(s, index, index + 1)) {
			//程序能走到这一步说明，现阶段选取的数字是合法的，所以直接拼接IP地址
			arr = arr.append(s.substring(index, index + 1)).append(".");
			//然后递归的选取下一个合法的数字
			select(s, ++index, ++step, arr, temp, 1);
			//程序能走到这里说明上面的递归结束了，也就是说不能拼接出正确的ip地址，所以要回溯
			//把拼接的IP地址中上一步拼接的部分去除掉，同时处理的位置回到上一步的位置，处理的步数也减1
			arr = arr.replace(0, arr.toString().length(),arr.substring(0, arr.toString().length() - 2));
			index--;
			step--;
		}
		//一段取2位数的情况
		if (index < s.length() - 1 && judge(s, index, index + 2)) {
			arr = arr.append(s.substring(index, index + 2)).append(".");
			index += 2;
			select(s, index, ++step, arr, temp, 2);
			arr = arr.replace(0, arr.toString().length(),
					arr.substring(0, arr.toString().length() - 3));
			index -= 2;
			step--;
		}
		//一段取三位数的情况
		if (index < s.length() - 2 && judge(s, index, index + 3)) {
			arr = arr.append(s.substring(index, index + 3)).append(".");
			index += 3;
			select(s, index, ++step, arr, temp, 3);
			arr = arr.replace(0, arr.toString().length(),
					arr.substring(0, arr.toString().length() - 4));
			index -= 3;
			step--;
		}

	}
	
	/**
	 * 判断截取的数字是否符合ip地址中每一段的要求
	 * 
	 * @param s
	 * @param start：开始位置，从0开始
	 * @param end：结束位置，0-1取的是第一个字符，0到s.length()取得是整个字符串
	 * @return
	 */
	public boolean judge(String s, int start, int end) {
		//如果字符串为空，或者start或end的输入不合法，直接返回false
		if (s.length() == 0 || start < 0 || end < 0 || start > end|| start > s.length() || end > s.length()) {
			return false;
		}
		int x = Integer.parseInt(s.substring(start, end));
		if (x < 256 && x >= 0) {
			if (end - start == 3 && x >= 100) {//如果截取的是三位，那么值一定大于等于100,即不会出现010这样的情况
				return true;
			} else if (end - start == 2 && x >= 10) {//如果截取的是两位，那么值一定大于等于10,即不会出现01这样的情况
				return true;
			} else if (end - start == 1 && x >= 0) {
				return true;
			} else
				return false;
		}
		return false;

	}

}
