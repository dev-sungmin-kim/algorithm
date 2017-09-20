package algorithm;

public class candy_box {
	public static void main(String[] args) {
		int n = 100000;
		int tree_depth = (int) (Math.log(n) / Math.log(2));
		int tree_size = (1 << tree_depth);
		
		long list[] = new long[n];
		long tree[] = new long[tree_size];
		
		for (int i = 0; i < n; i++) {
			list[i] = 0;
		}
		
		init(list, tree, 0, 0, n-1);
		
		for (int i = 0; i < n; i++) {
			System.out.print(list[i]);
		}
		for (int i = 0; i < n; i++) {
			System.out.print(list[i]);
			System.out.print(" ");
		}
		System.out.println("");
		
	}
	
	public static long init(long list[], long tree[], int node, int start, int end) {
		if (start == end) {
			return tree[node] = list[start];
		}
		
		int mid = (start + end) / 2;
		
		return tree[node] = init(list, tree, node * 2, start, mid) + init(list, tree, node * 2 + 1, mid + 1, end);
	}
}
