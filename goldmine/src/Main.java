import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static int MAX = 3000;
	static int tree_depth = (int) (Math.log(MAX) / Math.log(2));
	static int tree_size = (1 << (tree_depth + 2));
	
	
	public static class SegNode {
		int sum;
		int left_sum;
		int right_sum;
		int max_sum;
		
		public SegNode (int sum, int left_sum, int right_sum, int max_sum) {
			this.sum = sum;
			this.left_sum = left_sum;
			this.right_sum = right_sum;
			this.max_sum = max_sum;
		}
	}
	
	
	public static class Goldmine implements Comparable {
		int x;
		int y;
		int val;
		
		public Goldmine(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}

		public int compareTo(Goldmine o) {
			return this.y - o.y;
		}

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
	

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("goldmine_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Goldmine[] list = new Goldmine[N];
		ArrayList<Integer> yList = new ArrayList<Integer>();
		
		for (int i = 0; i < N; i++) {
			String temp[] = br.readLine().split(" ");
			int x = Integer.parseInt(temp[0]);
			int y = Integer.parseInt(temp[1]);
			int val = Integer.parseInt(temp[2]);
			
			list[i] = new Goldmine(x, y, val);
			yList.add(y);
		}
		
		Arrays.sort(list);
		yList.sort(null);
//		yList.
		
		int result = 0;
		for (int i = 0; i < N; i++) {
			SegNode tree[] = new SegNode[tree_size];
			
			if (i > 0 && list[i].x == list[i - 1].x) {
				continue;
			}
			
			for (int j = i; j < N; j++) {
				update(tree, 1, 0, yList.size() - 1, list[j].y, list[j].val);
				
				if (j != N -1 && list[j].x == list[j + 1].x) {
					continue;
				}
				
				result = Math.max(result, query(tree, 0, yList.size() - 1, 1, 0, yList.size() - 1));
			}
		}
	}


	public static int query(SegNode[] tree, int a, int b, int node, int start, int end) {
		if (end < a || b < start) {
			return 0;
		}
		
		if (a <= start && end <= b) {
			return tree[node].max_sum;
		}
		
		int mid = (start + end) / 2;
		
		return Math.max(query(tree, a, b, node * 2, start, mid), query(tree, a, b, node * 2 + 1, mid + 1, end));
	}
	
	public static void update(SegNode[] tree, int node, int start, int end, int index, int val) {
		if (index < start || end < index) {
			return;
		}
		
		if (start == end) {
			tree[node].sum += val;
			tree[node].left_sum += val;
			tree[node].right_sum += val;
			tree[node].max_sum += val;
			return;
		} else {
			int mid = (start + end) / 2;
			
			update(tree, node * 2, start, mid, index, val);
			update(tree, node * 2 + 1, mid + 1, end, index, val);
			
			tree[node].left_sum = Math.max(tree[node * 2].left_sum, (tree[node * 2].sum + tree[node * 2 + 1].left_sum));
			tree[node].right_sum = Math.max(tree[node * 2 + 1].right_sum, (tree[node * 2 + 1].sum + tree[node * 2].right_sum));
			tree[node].sum = tree[node * 2].sum + tree[node * 2 + 1].sum;
			tree[node].max_sum = max(tree[node * 2].right_sum + tree[node * 2 + 1].left_sum,
									tree[node * 2].max_sum,
									tree[node * 2 + 1].max_sum,
									tree[node].left_sum,
									tree[node].right_sum);
		}
		return;
	}
	
	public static int max (int a, int b, int c, int d, int e) {
		return Math.max(Math.max(Math.max(a, b), Math.max(c, d)), e);
	}
}
