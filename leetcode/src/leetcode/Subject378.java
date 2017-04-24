package leetcode;

/**
 * 
 * @author catyee
 *
 *         378、Given a n x n matrix where each of the rows and columns are
 *         sorted in ascending order, find the kth smallest element in the
 *         matrix.
 * 
 *         Note that it is the kth smallest element in the sorted order, not the
 *         kth distinct element.
 * 
 *         Example: matrix = [ [ 1, 5, 9], 
 *         					[10, 11, 13], 
 *         					[12, 13, 15] ], 
 *         k =8,return 13.
 *
 *
 *         译文：给定一个n×n矩阵，其中每个行和列按升序排序，找到矩阵中第k个最小的元素。请注意，它
 *         是排序顺序中第k个最小的元素，而不是第k个独特元素。
 *
 *         思路：正常思路是用一个k个元素的最大堆，遍历一遍数组。但是这就和在一堆乱序的数中找第k大的数没区别了。 
 *         这里可以用二分搜索的思路。
 *         也可以维持一个n个元素的最小堆，每次弹出堆顶元素，然后把与堆顶元素的同列的下一个元素加入堆中。
 *         也可以维护一个n元素的队列。队列中存储的是n列中每一列的最小值，每弹出一个值，做如下操作，比较每一列剩余
 *         元素最小值，找到最小的那一个入队，直到弹出k个元素停止。这种思路和维护n个元素的最小堆思路一样，只是实现
 *         方式不一样。
 *
 */

public class Subject378 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int matrix[][] ={{1,5,9},
//						{10,11,13},
//						{12,13,15}};
//		int matrix[][] ={{1,3,5},
//				{6,7,12},
//				{11,14,14}};
		int matrix[][] ={{1,2},
						{1,4}};
//		int matrix[][] ={{1,2,3,4,5},
//				{7,7,8,9,10},
//				{11,12,13,14,15},
//				{16,17,18,19,20},
//				{21,22,23,24,25}};
		Subject378 temp=new Subject378();
		System.out.println(temp.kthSmallest(matrix, 4));
	}
	
	public int kthSmallest(int[][] matrix, int k) {
		Node[] node = new Node[matrix[0].length];//用一个n长度的数组来充当最小堆
		for (int i = 0; i < matrix[0].length; i++) {
			node[i] = new Node(matrix[0][i], 0, i);//最小堆的初始化，把第一行依次放入数组中，因为行是递增的，所以不用进行调整，就是最小堆
		}
		int result = 0;
		int step = 1;
		while (step <= k) {
			result = heapPopAndAdjust(node, matrix);
			step++;
		}
		return result;

	}

	public int heapPopAndAdjust(Node[] arr, int[][] matrix) {
		int min = arr[0].getVal();
		//获得堆顶节点，同时如果这个节点同列还有下一个元素的话，把这个元素放到堆顶，然后调整堆结构
		if (arr[0].getI() < matrix.length - 1) {
			int join = matrix[arr[0].getI() + 1][arr[0].getJ()];
			arr[0].setVal(join);
			arr[0].setI(arr[0].getI() + 1);
			arr[0].setJ(arr[0].getJ());
			heapAdjust(arr, arr.length);
		} else {//获得堆顶元素后发现同列没有下一个元素了，那么要直接删除堆顶元素，但是数组没有删除功能，所以就把堆顶元素和最后一个节点交换，然后把最后一个节点置为空，同时调整堆结构
			int end = arr.length - 1;
			while (end > 0 && arr[end] == null) {//找到数组中最后一个不为空的位置
				end--;
			}
			arr[0].setVal(arr[end].getVal());
			arr[0].setI(arr[end].getI());
			arr[0].setJ(arr[end].getJ());
			if (end != 0)arr[end] = null;//这里要注意，如果堆只有一个元素的话，不能置空
			heapAdjust(arr, end + 1);

		}
		return min;

	}
	
	/**
	 * 最小堆调整，因为是用数组来存储最小堆，最小堆其实是一个特殊的完全二叉树，对于完全二叉树，一个节点的
	 * 子节点为下标×2+1和下标×2+2,只要让父节点比两个子节点小就可以了，如果父节点比子节点大，那么就进行交换
	 * 这里有个细节，最小堆只要父节点比子节点小就可以了，至于两个子节点间是没有顺序的，但是因为数组本身有序，
	 * 所以只要在调整的时候先拿父节点和右孩子比较，交换，然后和左孩子比较交换就可以使左孩子比右孩子小。
	 * @param arr
	 * @param length
	 */
	public void heapAdjust(Node[] arr, int length) {
		for (int i = 0; i <= Math.ceil(length / 2) - 1; i++) {//最小堆是完全二叉树，叶子节点都不用调整，所以只用遍历接近一半的数就可以了
			if (2 * (i + 1) < arr.length && arr[2 * (i + 1)] != null) {//比较父节点和右孩子
				if (arr[i].getVal() > arr[2 * (i + 1)].getVal()) {
					swap(arr[i], arr[2 * (i + 1)]);
				}
			}
			if (2 * (i + 1) - 1 < arr.length && arr[2 * (i + 1) - 1] != null) {//比较父节点和左孩子
				if (arr[i].getVal() > arr[2 * (i + 1) - 1].getVal()) {
					swap(arr[i], arr[2 * (i + 1) - 1]);
				}
			}

		}
	}
	
	/**
	 * 交换两个节点，不仅交换数值，还要交换二位数组中的下标
	 * @param A
	 * @param B
	 */
	public void swap(Node A, Node B) {
		int tempVal = A.getVal();
		int tempI = A.getI();
		int tempJ = A.getJ();
		A.setVal(B.getVal());
		A.setI(B.getI());
		A.setJ(B.getJ());
		B.setVal(tempVal);
		B.setI(tempI);
		B.setJ(tempJ);
	}
	
	/**
	 * 把二位数组中的数值和下标构成节点
	 * @author catyee
	 *
	 */
	public class Node {
		private int val;
		private int i;
		private int j;

		public int getVal() {
			return val;
		}

		public void setVal(int val) {
			this.val = val;
		}

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}

		public int getJ() {
			return j;
		}

		public void setJ(int j) {
			this.j = j;
		}

		public Node(int val, int i, int j) {
			super();
			this.val = val;
			this.i = i;
			this.j = j;
		}

	}

}
