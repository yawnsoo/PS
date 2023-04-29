import java.util.*;

public class Main {
    static int n;
    static int diffMin = 100*10*9;
    static int total;
    static int[][] map;
    static List<Integer> team0 = new ArrayList<>();


    public static void solution() {

        //1. 반 나누는 조합 : 19C9 = 92378
        dfs(1, 1);


    }

    private static void dfs(int pick, int start) {
        if(pick==n/2){
            //2. 팀 차이 계산 : 10*10
            calculateDiff();
            return;
        }

        for (int i = start; i < n; i++) {
            team0.add(i);
            dfs(pick+1,i+1);
            team0.remove(team0.size()-1);
        }
    }

    private static void calculateDiff() {
        List<Integer> team1 = new LinkedList<>();
        int t0 = 0;
        int t1 = 0;

        for (int i = 0; i < n; i++) {
            if(!team0.contains(i)){
                team1.add(i);
            }
        }

        for (int i : team0) {
            for (int j: team0) {
                t0 += map[i][j];
            }
        }

        for (int i : team1) {
            for (int j: team1) {
                t1 += map[i][j];
            }
        }

        diffMin = Math.min(diffMin, Math.abs(t0-t1));
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int a = sc.nextInt();
                map[i][j] = a;
                total += a;
            }
        }

        team0.add(0);
        solution();

        System.out.println(diffMin);
    }
}