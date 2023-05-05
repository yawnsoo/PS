import java.util.*;

public class Main {
    static int n;
    static int[] cardPacks;
    static int[] ans;


    public static void solution() {

        for (int i = 1; i <= n; i++) {
            ans[i] = cardPacks[i];
            for (int j = 1; j <=i; j++) {
                ans[i] = Math.min(ans[i], ans[i-j] + cardPacks[j]);
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
        ans = new int[n+1];

        solution();

        System.out.println(ans[n]);
    }
}