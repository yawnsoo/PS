import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static List<int[]> chickenPos = new ArrayList<>();
    static int ans = 2147483647;


    public static void comb(int toPick, List<int[]> chosen, int start, int listSize) {
        if (toPick == 0) {
            // 2. 각 조합의 경우의 수 에서 치킨 거리 구하기
            ans = Math.min(ans,totalChickenDistance(chosen));
            return;
        }

        for (int i = start; i < listSize; i++) {
            int[] target = chickenPos.get(i);
            chosen.add(target);
            comb(toPick-1,chosen,i+1,listSize);
            chosen.remove(target);
        }

    }

    private static int totalChickenDistance(List<int[]> chosen) {
        int tcd = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int min = 101;
                if(map[i][j]==1){
                    for (int[] given : chosen) {
                        min = Math.min(min, Math.abs(i-given[0])+Math.abs(j-given[1]));
                    }
                    tcd += min;
                }
            }
        }

        return tcd;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int num = sc.nextInt();
                map[i][j] = num;
                if (num == 2) {
                    chickenPos.add(new int[]{i,j});
                }
            }
        }

        // 1. 주어진 치킨집의 조합을 구한다.
        //  m이 13일 경우, 조합의 경우의 수 = 13C1 + 13C2 + ... + 13C12 + 13C13 = 2^13 - 1 = 8191
        for (int i = 1, listSize = chickenPos.size(); i <=  m; i++) {
            List<int[]> chosen = new ArrayList<>();
            comb(i, chosen, 0, listSize);
        }

        System.out.println(ans);
    }
}