import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class mordor {
    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("mordor_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        for (int i = 0; i < T; i++) {
            String numbers[] = br.readLine().split(" ");
            int N = Integer.parseInt(numbers[0]);
            int Q = Integer.parseInt(numbers[1]);
             
            String pathString[] = br.readLine().split(" ");
            int path[] = new int[pathString.length];
            for (int j = 0; j < pathString.length; j++) {
                path[i] = Integer.parseInt(pathString[i]);
            }
            
            int section[][] = new int[Q][2];
            for (int j = 0; j < Q; j++) {
            	String sectionString[] = br.readLine().split(" ");
            	section[j][0] = Integer.parseInt(sectionString[0]);
            	section[j][1] = Integer.parseInt(sectionString[1]);
			}
            
            solve(N, Q, path, section);
        }
    }
     
    public static void solve(int N, int Q, int path[], int section[][]) {
        int tree_depth = (int) Math.ceil((Math.log(N) / Math.log(2)));
        int tree_size = (1 << (tree_depth + 1));
        int maxTree[] = new int[tree_size];
        int minTree[] = new int[tree_size];
        Arrays.fill(minTree, Integer.MAX_VALUE);
         
        initMax(path, maxTree, 1, 0, N - 1);
        initMin(path, minTree, 1, 0, N - 1);
        
        for (int i = 0; i < Q; i++) {
        	int max = getMax(maxTree, 1, 0, N - 1, section[i][0], section[i][1]);
            int min = getMin(minTree, 1, 0, N - 1, section[i][0], section[i][1]);
            System.out.println("max: " + max);
            System.out.println("min: " + min);
            System.out.println(max - min);
		}
    }
     
    public static int initMax(int path[], int tree[], int node, int start, int end) {
        if (start == end) {
            return tree[node] = path[start];
        }
         
        int mid = (start + end) / 2;
         
        int left = initMax(path, tree, node * 2, start, mid);
        int right = initMax(path, tree, node * 2 + 1, mid + 1, end);
         
        if (left > right) {
            tree[node] = left;
        } else {
            tree[node] = right;
        }
         
        return tree[node];
    }
     
    public static int getMax(int tree[], int node, int start, int end, int startSection, int endSection) {
        if (startSection <= start && end <= endSection) {
            return tree[node];
        }
        
        if (startSection > end || endSection < start) {
        	return 0;
        }
        
        int mid = (start + end) / 2;
         
        int left = getMax(tree, node * 2, start, mid, startSection, endSection);
        int right = getMax(tree, node * 2 + 1, mid + 1, end, startSection, endSection);
        if (left > right) {
            return left;
        } else {
            return right;
        }
    }
    
    public static int initMin(int path[], int tree[], int node, int start, int end) {
        if (start == end) {
            return tree[node] = path[start];
        }
         
        int mid = (start + end) / 2;
         
        int left = initMin(path, tree, node * 2, start, mid);
        int right = initMin(path, tree, node * 2 + 1, mid + 1, end);
         
        if (left < right) {
            tree[node] = left;
        } else {
            tree[node] = right;
        }
         
        return tree[node];
    }
     
    public static int getMin(int tree[], int node, int start, int end, int startSection, int endSection) {
        if (startSection <= start && end <= endSection) {
            return tree[node];
        }
        
        if (startSection > end || endSection < start) {
        	return Integer.MAX_VALUE;
        }
        
        int mid = (start + end) / 2;
         
        int left = getMin(tree, node * 2, start, mid, startSection, endSection);
        int right = getMin(tree, node * 2 + 1, mid + 1, end, startSection, endSection);
        if (left < right) {
            return left;
        } else {
            return right;
        }
    }
}