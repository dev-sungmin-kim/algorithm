import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int MAX = 100000;
	static int Depth = (int) (Math.log(MAX) / Math.log(2));
	static int Size = 1 << (Depth + 1);
	static int tree[] = new int[Size];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("switch_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String tokens[] = br.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int C = Integer.parseInt(tokens[1]);
		
		for (int i = 0; i < C; i++) {
			tokens = br.readLine().split(" ");
			int action = Integer.parseInt(tokens[0]);
			int start = Integer.parseInt(tokens[1]);
			int end = Integer.parseInt(tokens[2]);
			
			if (action == 1) {
				update(0, start, end);
			} else {
				int sol = query(0, start, end);
				System.out.println(sol);
			}
		}
	}
	
	public static int query(int node, int start, int end) {
		
	}
	
	public static void update(int node, int start, int end) {
		
	}
}
