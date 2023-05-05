import java.util.*;

public class Main {
    static int n;
    static int[] cardPacks;
    static int[] dp;


    public static void solution() {

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] + cardPacks[j]);
            }
        }

    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        cardPacks = new int[n+1];
        for (int i = 1; i <= n; i++) {
            cardPacks[i] = sc.nextInt();
        }
        dp = new int[n+1];

        solution();

        System.out.println(dp[n]);
    }
}