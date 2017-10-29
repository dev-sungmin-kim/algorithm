import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("coin_change_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int i = 0; i < T; i++) {
			int money = br.read();
			int n = br.read();
			br.readLine();
			
			int coins[] = new int[n];
			
			for (int j = 0; j < n; j++) {
				coins[j] = br.read();
			}
			
			dp(money, coins);
		}
	}
	
	public static void dp(int money, int[] coins) {
		int table[][] = new int[coins.length + 1][money];
		
		table[0][0] = 1;
		
		for (int i = 1; i < coins.length; i++) {
			int coin = coins[i];
			for (int j = 0; j < money; j++) {
				if (j < coins[i]) {
					table[i][j] = table[i - 1][j];
				} else {
					table[i][j] = table[i][j - coin] + table[i - 1][j];
				}
			}
		}
		System.out.println(table[coins.length][money]);
	}
}
