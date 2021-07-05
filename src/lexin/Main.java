package lexin;

import java.util.Scanner;

/**
 * @Author: ZhanLiao
 * @Description: 乐鑫第二道
 * @Date: 2021/6/29 20:34
 * @Version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int[][] info = new int[n][3];
        for (int i = 0; i < n; i++) {
            String[] s = scanner.nextLine().split(" ");
            for (int j = 0; j < s.length; j++) {
                info[i][j] = Integer.valueOf(s[j]);
            }
        }
        int[] nums = new int[n + 1];
        int[] weight = new int[n + 1];
        int[] dp = new int[n + 1];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            nums[info[i][0]] = info[i][1];
            weight[info[i][0]] = info[i][2];
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + weight[i]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
