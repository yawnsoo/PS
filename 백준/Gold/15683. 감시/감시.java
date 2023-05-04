import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static List<int[]> posCCTV = new ArrayList<>();
    static int answer;


    public static void solution() {
        int ans = 0;

        //기본값
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    ans++;
                }
            }
        }
        answer = ans;

        spin(0, posCCTV.size());

    }

    private static void spin(int depth, int param) {
        //list(CCTV 위치의 idx)안에 있는 cctv 회전 시키기
        if (depth == param) {
            //0 계산
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 0) {
                        cnt++;
                    }
                }
            }
            answer = Math.min(answer, cnt);
            return;
        }


        int[] pos = posCCTV.get(depth);
        int cctvType = map[pos[0]][pos[1]];

        switch (cctvType) {
            case 1:
                //동
                watchingR(map, pos);
                spin(depth+1, param);
                reverseWatchingR(map, pos);
                //서
                watchingL(map, pos);
                spin(depth+1, param);
                reverseWatchingL(map, pos);
                //남
                watchingD(map, pos);
                spin(depth+1, param);
                reverseWatchingD(map, pos);
                //북
                watchingU(map, pos);
                spin(depth+1, param);
                reverseWatchingU(map, pos);
                break;
            case 2:
                //동서
                watchingR(map, pos);
                watchingL(map, pos);
                spin(depth+1, param);
                reverseWatchingR(map, pos);
                reverseWatchingL(map, pos);
                //남북
                watchingD(map, pos);
                watchingU(map, pos);
                spin(depth+1, param);
                reverseWatchingD(map, pos);
                reverseWatchingU(map, pos);
                break;
            case 3:
                //북동
                watchingU(map, pos);
                watchingR(map, pos);
                spin(depth+1, param);
                reverseWatchingU(map, pos);
                reverseWatchingR(map, pos);
                //동남
                watchingR(map, pos);
                watchingD(map, pos);
                spin(depth+1, param);
                reverseWatchingR(map, pos);
                reverseWatchingD(map, pos);
                //남서
                watchingD(map, pos);
                watchingL(map, pos);
                spin(depth+1, param);
                reverseWatchingD(map, pos);
                reverseWatchingL(map, pos);
                //서북
                watchingL(map, pos);
                watchingU(map, pos);
                spin(depth+1, param);
                reverseWatchingL(map, pos);
                reverseWatchingU(map, pos);
                break;
            case 4:
                //서남북
                watchingL(map, pos);
                watchingD(map, pos);
                watchingU(map, pos);
                spin(depth+1, param);
                reverseWatchingL(map, pos);
                reverseWatchingD(map, pos);
                reverseWatchingU(map, pos);

                //동남북
                watchingR(map, pos);
                watchingD(map, pos);
                watchingU(map, pos);
                spin(depth+1, param);
                reverseWatchingR(map, pos);
                reverseWatchingD(map, pos);
                reverseWatchingU(map, pos);

                //동서북
                watchingR(map, pos);
                watchingL(map, pos);
                watchingU(map, pos);
                spin(depth+1, param);
                reverseWatchingR(map, pos);
                reverseWatchingL(map, pos);
                reverseWatchingU(map, pos);

                //동서남
                watchingR(map, pos);
                watchingL(map, pos);
                watchingD(map, pos);
                spin(depth+1, param);
                reverseWatchingR(map, pos);
                reverseWatchingL(map, pos);
                reverseWatchingD(map, pos);
                break;
            case 5:
                //동서남북
                watchingR(map, pos);
                watchingL(map, pos);
                watchingD(map, pos);
                watchingU(map, pos);
                spin(depth+1, param);
                reverseWatchingR(map, pos);
                reverseWatchingL(map, pos);
                reverseWatchingD(map, pos);
                reverseWatchingU(map, pos);
                break;
        }

    }

    public static void watchingR(int[][] map, int[] pos) {

        for (int i = pos[1]; i < m; i++) {
            if (map[pos[0]][i] < 1) {
                map[pos[0]][i]--;
            } else if (map[pos[0]][i] == 6) {
                break;
            }
        }
    }

    public static void reverseWatchingR(int[][] map, int[] pos) {

        for (int i = pos[1]; i < m; i++) {
            if (map[pos[0]][i] < 1) {
                map[pos[0]][i]++;
            } else if (map[pos[0]][i] == 6) {
                break;
            }
        }
    }

    public static void watchingL(int[][] map, int[] pos) {

        for (int i = pos[1]; i > -1; i--) {
            if (map[pos[0]][i] < 1) {
                map[pos[0]][i]--;
            } else if (map[pos[0]][i] == 6) {
                break;
            }
        }
    }

    public static void reverseWatchingL(int[][] map, int[] pos) {

        for (int i = pos[1]; i > -1; i--) {
            if (map[pos[0]][i] < 1) {
                map[pos[0]][i]++;
            } else if (map[pos[0]][i] == 6) {
                break;
            }
        }
    }

    public static void watchingD(int[][] map, int[] pos) {

        for (int i = pos[0]; i < n; i++) {
            if (map[i][pos[1]] < 1) {
                map[i][pos[1]]--;
            } else if (map[i][pos[1]] == 6) {
                break;
            }
        }
    }

    public static void reverseWatchingD(int[][] map, int[] pos) {

        for (int i = pos[0]; i < n; i++) {
            if (map[i][pos[1]] < 1) {
                map[i][pos[1]]++;
            } else if (map[i][pos[1]] == 6) {
                break;
            }
        }
    }

    public static void watchingU(int[][] map, int[] pos) {

        for (int i = pos[0]; i > -1; i--) {
            if (map[i][pos[1]] < 1) {
                map[i][pos[1]]--;
            } else if (map[i][pos[1]] == 6) {
                break;
            }
        }
    }

    public static void reverseWatchingU(int[][] map, int[] pos) {

        for (int i = pos[0]; i > -1; i--) {
            if (map[i][pos[1]] < 1) {
                map[i][pos[1]]++;
            } else if (map[i][pos[1]] == 6) {
                break;
            }
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int t = sc.nextInt();
                map[i][j] = t;

                if(t!=0&&t!=6){
                    posCCTV.add(new int[]{i, j});
                }
            }
        }

        solution();

        System.out.println(answer);
    }
}