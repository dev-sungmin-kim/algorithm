import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("panda_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int forest[][] = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			String[] l = line.split(" ");
			for (int j = 0; j < N; j++) {
				forest[i][j] = Integer.parseInt(l[j]);
			}
		}
//		print(N, forest);
		int map[][] = new int[N][N];
		int out = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				out = Math.max(out, dp(N, i, j, forest, map));
			}
		}
		System.out.println(out);
//		print(N, map);
	}
	
	public static int dp(int n, int x, int y, int[][] forest, int[][] map) {
		int listX[] = {-1, 0, 1, 0};
		int listY[] = {0, -1, 0, 1};
		
		if (map[x][y] != 0) {
			return map[x][y];
		}
		
		map[x][y] = 1;
		
		for (int i = 0; i < 4; i++) {
			int nextX = x + listX[i];
			int nextY = y + listY[i];
			
			if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) {
				continue;
			}
			
			if (forest[nextX][nextY] <= forest[x][y]) {
				continue;
			}
			
			map[x][y] = Math.max(map[x][y], dp(n, nextX, nextY, forest, map) + 1);
		}
		
		return map[x][y];
	}
	
	public static void print(int n, int matrix[][]) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
