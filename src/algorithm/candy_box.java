package algorithm;

public class candy_box {
	static int n = 8;
	static int tree_depth = (int) (Math.log(n) / Math.log(2));
	static int tree_size = (1 << (tree_depth + 1));
	
	static long list[] = new long[n];
	static long tree[] = new long[tree_size];
	
	public static void main(String[] args) {
		
		
		for (int i = 0; i < n; i++) {
			list[i] = 0;
		}
//		System.out.println("treedepth: " + tree_depth);
//		System.out.println("treesize: " + tree_size);
		init(list, tree, 0, 0, n-1);
		
		
		
		for (int i = 0; i < n; i++) {
			System.out.print(list[i]);
		}
		System.out.println("");
		for (int i = 0; i < tree_size; i++) {
			System.out.print(tree[i]);
			System.out.print(" ");
		}
		System.out.println("");
		
	}
	
	public static int pop(int index) {
		return 0;
	}
	
	public static void put(int order, int count) {
		list[order] = list[order] + count;
	}
	
	public static long init(long list[], long tree[], int node, int start, int end) {
		if (start == end) {
			return tree[node] = list[start];
		}
		
		int mid = (start + end) / 2;
		
		return tree[node] = init(list, tree, node * 2, start, mid) + init(list, tree, node * 2 + 1, mid + 1, end);
	}
	
	public static void update(long list[], long tree[], int node, int start, int end, int index, long diff) {
		if (!(start <= index && index <= end)) {
			return;
		}
		
		tree[node] = tree[node] + diff;
		
		if (start != end) {
			int mid = (start + end) / 2;
			update(list, tree, node * 2, start, mid, index, diff);
			update(list, tree, node * 2 + 1, mid + 1, end, index, diff);
		}
	}
}
