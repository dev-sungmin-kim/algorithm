import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("rgb_street_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] matrix = new int[N][3];
		int[][] dpTable = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			String line[] = br.readLine().split(" ");
			for (int j = 0; j < 3; j++) {
				matrix[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		for (int i = 0; i < N; i++) {
			dp(matrix, dpTable, i);
		}
		System.out.println(Math.min(Math.min(dpTable[N-1][0], dpTable[N-1][1]), dpTable[N-1][2]));
	}
	
	public static void dp(int[][] matrix, int[][] dpTable, int n) {
		if (n == 0) {
			dpTable[n][0] = matrix[n][0];
			dpTable[n][1] = matrix[n][1];
			dpTable[n][2] = matrix[n][2];
		} else {
			dpTable[n][0] = Math.min(dpTable[n-1][1] + matrix[n][0], dpTable[n-1][2] + matrix[n][0]);
			dpTable[n][1] = Math.min(dpTable[n-1][0] + matrix[n][1], dpTable[n-1][2] + matrix[n][1]);
			dpTable[n][2] = Math.min(dpTable[n-1][0] + matrix[n][2], dpTable[n-1][1] + matrix[n][2]);
		}
	}
}
