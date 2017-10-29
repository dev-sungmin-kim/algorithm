import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int MAX = 100000;
	static int Depth = (int) (Math.log(MAX) / Math.log(2));
	static int Size = (1 << (Depth + 1)) + 1;
	static int tree[] = new int[Size];
	static boolean lazy[] = new boolean[Size];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("switch_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String tokens[] = br.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int C = Integer.parseInt(tokens[1]);
		
		for (int i = 0; i < C; i++) {
			tokens = br.readLine().split(" ");
			int action = Integer.parseInt(tokens[0]);
			int a = Integer.parseInt(tokens[1]);
			int b = Integer.parseInt(tokens[2]);
			
			if (action == 0) {
				update(1, 0, N - 1, a, b);
			} else {
				int sol = query(1, 0, N-1, a, b);
				System.out.println(sol);
			}
		}
	}
	
	public static int query(int node, int start, int end, int a, int b) {
		update_lazy(node, start, end);
		
		if (b < start || a > end) {
			return 0;
		} else if (a <= start && end <= b) {
			return tree[node];
		} else {
			int mid = (start + end) / 2;
			return query(node * 2, start, mid, a, b) +
					query(node * 2 + 1, mid + 1, end, a, b);
		}
	}
	
	public static int update(int node, int start, int end, int a, int b) {
		update_lazy(node, start, end);
		
		if (b < start || a > end) {
			return tree[node];
		} else if (a <=start && end <= b) {
			tree[node] = (end - start + 1) - tree[node];
			
			lazy[node * 2] = !lazy[node * 2];
			lazy[node * 2 + 1] = !lazy[node * 2 + 1];
			
			return tree[node];
		} else {
			int mid = (start + end) / 2;
			tree[node] = update(node * 2, start, mid, a, b) +
					update(node * 2 + 1, mid + 1, end, a, b);
			
			return tree[node];
		}
	}
	
	public static void update_lazy(int node, int start, int end) {
		if (lazy[node]) {
			tree[node] = (end - start + 1) - tree[node];
			
			lazy[node * 2] = !lazy[node * 2];
			lazy[node * 2 + 1] = !lazy[node * 2 + 1];
			
			lazy[node] = false;
		}
		
	}
}
