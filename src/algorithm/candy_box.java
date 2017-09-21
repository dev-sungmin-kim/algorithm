package algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class candy_box {
	static int MAX = 100000;
	static int tree_depth = (int) (Math.log(MAX) / Math.log(2));
	static int tree_size = (1 << (tree_depth + 2));
	
	static int list[] = new int[MAX];
	static int tree[] = new int[tree_size];
	
	public static void main(String[] args) throws IOException {
//		System.out.println("treedepth: " + tree_depth);
//		System.out.println("treesize: " + tree_size);
		
		System.setIn(new FileInputStream("candy_box_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			String list[] = line.split(" ");
			
			
			int action = Integer.parseInt(list[0]);
			
//			System.out.println("action: " + action);
			if (action == 1) {
				int order = Integer.parseInt(list[1]);
//				System.out.println("order: " + order);
				
				int index = pop(tree, 1, 0, MAX-1, order);
				System.out.println(index);
				update(tree, 1, 0, MAX-1, index, -1);
			} else {
				int index = Integer.parseInt(list[1]);
				int count = Integer.parseInt(list[2]);
//				System.out.println("index: " + index);
//				System.out.println("count: " + count);
				
				update(tree, 1, 0, MAX-1, index, count);
			}
		}
		
//		init(list, tree, 0, 0, n-1);
		
//		System.out.println("");
//		for (int i = 0; i < tree_size; i++) {
//			System.out.print(tree[i]);
//			System.out.print(" ");
//		}
//		System.out.println("");
		
	}

	public static int pop(int[] tree, int node, int start, int end, int order) {
		if (start == end) {
			return start;
		}
		
		int mid = (start + end) / 2;
		
		if (node * 2 <= tree_size && tree[node * 2] >= order) {
			return pop(tree, node * 2, start, mid, order);
		}
		
		if (node * 2 + 1 <= tree_size && tree[node * 2 + 1] >= order) {
			return pop(tree, node * 2 + 1, mid + 1, end, order);
		}
		return 0;
	}
	
	public static void update(int tree[], int node, int start, int end, int index, int diff) {
//		System.out.println("start: " + start);
//		System.out.println("end: " + end);
//		System.out.println("node: " + node);
		if (!(start <= index && index <= end)) {
			return;
		}
		
		tree[node] = tree[node] + diff;
		
		if (start != end) {
			int mid = (start + end) / 2;
			update(tree, node * 2, start, mid, index, diff);
			update(tree, node * 2 + 1, mid + 1, end, index, diff);
		}
		return;
	}
}
