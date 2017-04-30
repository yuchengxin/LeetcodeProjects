package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 
 * @author catyee 
 * 			
 * 		 515  You need to find the largest value in each row of a binary tree.
 * 
 *         Example: 
 *         Input:
 *                1 
 *               / \ 
 *              3   2 
 *             / \   \ 
 *            5   3   9
 * 
 *         Output: [1, 3, 9]
 *         
 *         译文：输出二叉树每一行的最大值。
 *         
 *         思路：层次遍历。关键点是层次遍历的时候必须知道每一行的最后一个节点，到达最后一个节点就该换行了。
 *         层次遍历是用一个队列来进行的，首先根节点入队，可以用last变量记录每一行的最后一个节点，preNode
 *         变量记录每次出队的节点，每出队一个节点就检查这个节点是否有左右孩子，有就依次入队，同时用nlast变
 *         量记录每次入队的节点，每当preNode=last节点的时候，说明这一行到尽头了，这时候nlast一定是下一
 *         行的最后一个元素，所以只要令last=nlast就可以了。
 *
 */

public class Subject515 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Subject515 temp = new Subject515();
		TreeNode root = temp.creatTree();
		List<Integer> largeList = temp.largestValues(root);
		for (int i = 0; i < largeList.size(); i++) {
			System.out.println(largeList.get(i));
		}
	}

	public TreeNode creatTree() {
		TreeNode node0 = new TreeNode(3);
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(5);
		TreeNode node3 = new TreeNode(0);
		TreeNode node4 = new TreeNode(2);
		TreeNode node5 = new TreeNode(4);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(3);
		node0.left = node1;
		node0.right = node2;
		node1.left = node3;
		node1.right = node4;
		node2.left=node5;
		node2.right = node6;
		node5.right=node7;
		return node0;
	}

	public List<Integer> largestValues(TreeNode root) {
		List<Integer> largeList = new ArrayList<Integer>();//用来记录每一行的最大值
		if (root == null)return largeList;
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();//队列，用来层次遍历
		int max = Integer.MIN_VALUE;//用来更新每一行的最大值，初始为int的最小值，每换一行重新置为int的最小值
		TreeNode last = root;//用来记录每一行的最后一个节点
		TreeNode nlast=null;//用来记录每次入队的节点
		TreeNode preNode = null;//当前弹出的节点
		list.add(root);
		while (!list.isEmpty()) {
			preNode = list.remove();//弹出元素
			if (preNode.val > max)max = preNode.val;//记录当前的最大值
			if (preNode.left != null) {//如果左孩子不空，那左孩子入队，同时令nlast=左孩子
				nlast=preNode.left;
				list.add(preNode.left);
			}
			if (preNode.right != null) {//如果右孩子不空，那右孩子入队，同时令nlast=右孩子
				nlast=preNode.right;
				list.add(preNode.right);
			}
			if (preNode.equals(last)) {//如果弹出的当前节点=last，说明要换行了
				last=nlast;//这时nlast一定是下一行的最有节点，所以令last=nlast
				largeList.add(max);//记录当前行的最大值
				max = Integer.MIN_VALUE;//max重新置为int的最小值（初始值）
			}
		}
		return largeList;
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

}
