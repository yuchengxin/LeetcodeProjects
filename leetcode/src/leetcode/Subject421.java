package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author catyee
 *
 *     421、Given a non-empty array of numbers, a0, a1, a2, … , a(n-1), where 0 ≤
 *         ai < 2^31.Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 *         Could you do this in O(n) runtime?
 * 
 *         Example:
 *         Input: [3, 10, 5, 25, 2, 8]
 *         Output: 28
 *         Explanation: The maximum result is 5 ^ 25 = 28.
 *         
 *         译文：给出一个非空的数组，找到数组中两个数，使这两个数的异或最大。返回这个异或值。
 *         
 *         思路：题目中有限制。数组中的数小于2^31，所以用4个字节就可以存下，一个数比如10，二进制表示就是：
 *         0000 0000 0000 0000 0000 0000 0000 0000 1010，
 *         与10异或最大的值是：
 *         0111 1111 1111 1111 1111 1111 1111 1111 0101,（最高位是符号位）
 *         位运算不同于加减乘除，加减乘除会影响到旁边的位，比如个位上的9+5,就需要在十位上进一，但是与、或、异或、非运算
 *         没有这个顾虑，当前位的运算不会影响到临近位，所以可以通过一位一位的来找出最大的异或值，而不用担心当前位会影响
 *         临近位而找错。如果数组为A，假设我们要找的是最大异或值是T，a和b都是数组中的元素，有a^b=T，那么a^T=b;
 *         利用这个性质，我们可以先构造出T，然后判断a^T的值是不是还是在数组中，如果在数组中，那么T就是正确的。当然这里
 *         T是一位一位构造出来的，构造也很简单，T的每一位要么是0要么是1,初始都是0,从最高位开始构造，构造的时候把这一位
 *         的0置为1,然后进行一次探测，如果当前位符合A（prefix）.contain（a（prefix）^T）的，那探测就是成功的，记录
 *         下当前位，然后构造下一位，再进行探测。（A（prefix）代表A中每个元素在当前为的前缀）
 *         
 *         实现思路的话可以用Trib树，也可以用一个set。
 *         
 *
 */

public class Subject421 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums={3,10,5,25,2,8};
		Subject421 temp=new Subject421();
		System.out.print(temp.findMaximumXOR(nums));
	}
	
	public int findMaximumXOR(int[] nums) {
		int res=0;//用来记录最大的异或值，是从高位开始一位一位的进行记录
		int preLoc=0;//用来提取出数的前缀
		for(int i=31;i>=0;i--){
			preLoc |=(1<<i);//用来提取数的前缀，从最高位开始
			Set<Integer> set=new HashSet<Integer>();
			for(int num:nums){
				set.add(num&preLoc);//set中记录的是当前位 数组中每个数的前缀
			}
			//temp是构造出来的用于探测最大异或值的临时变量，即思路中的T。
			//res则是记录已经探测成功的位，也就是已经确定下来的当前位之前的最大异或值
			//temp的探测思路是从高位开始一位一位的去探测，初始时每一位都是0,探测到哪一位就把哪一位变成1,
			//然后通过下面的步骤去检查，如果符合条件则说明探测成功，则要在res中记录探测成功的这一位，然后再探测下一位，
			//如果不符合条件则说明探测失败，不用更新res，继续探测下一位就可以了
			int temp=res|(1<<i);
			for(int prefix:set){
				//检查前缀prefix^temp是否还是在set中，如果在那当前位探测就是成功的，更新res
				if(set.contains(prefix^temp)){
					res=temp;
					break;
				}
			}
		}
		return res;
    }

}
