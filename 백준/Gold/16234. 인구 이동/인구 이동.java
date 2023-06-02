import java.util.*;

public class Main {

    static int l, r, n, total, cnt;
    static int[][] map;
    static boolean[][] check;
    static int[] dx = new int[]{0,1,0,-1};
    static int[] dy = new int[]{-1,0,1,0};


    public static void main(String[] args) {
        int ans = 0;

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new int[n][n];
        l = sc.nextInt();
        r = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        cnt = 1;
        while (cnt>0) {
            cnt = 0;
            check = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!check[i][j]) {
                        total = map[i][j];
                        List<int[]> un = new ArrayList<>();

                        un.add(new int[]{i,j});
                        check[i][j] = true;

                        dfs(i,j, un);

                        int divided = total/un.size();

                        for (int[] a : un) {
                            map[a[0]][a[1]] = divided;
                        }

                    }
                }
            }

            if(cnt>0) ans++;

        }

        System.out.println(ans);

    }

    private static void dfs(int y, int x, List<int[]> un) {
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny<0||nx<0||ny>=n||nx>=n) continue;

            int diff = Math.abs(map[y][x]-map[ny][nx]);
            if (diff <= r && diff >= l && !check[ny][nx]) {
                un.add(new int[]{ny, nx});
                check[ny][nx] = true;
                total += map[ny][nx];
                cnt++;
                dfs(ny, nx, un);
            }
        }
    }

}