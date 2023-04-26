import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int answer;
    static int y;
    static int x;
    static int d;
    static int[] dy = new int[]{-1,0,1,0};
    static int[] dx = new int[]{0,1,0,-1};
    static int ny;
    static int nx;


    public int solution() {
        answer = 0;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y,x});

        while (!q.isEmpty()){
            int[] pos = q.poll();
//    1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if(map[pos[0]][pos[1]]==0){
                map[pos[0]][pos[1]] = 2;
                answer++;
            }

            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                ny = pos[0] + dy[i];
                nx = pos[1] + dx[i];

                if(ny<0||nx<0||ny>=n||nx>=m){
                    continue;
                }
                if(map[ny][nx]==0){
                    flag = true;
                    break;
                }
            }

//    2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
            if(!flag){
                ny = pos[0] + dy[(d+2)%4];
                nx = pos[1] + dx[(d+2)%4];
                //    바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
                if(ny<0||nx<0||ny>=n||nx>=m||map[ny][nx]==1){
                    return answer;
                }
                //    바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
                q.add(new int[]{ny,nx});
            } else{ //    3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
                //    반시계 방향으로 90도 회전한다.
                d = (d+3)%4;
                ny = pos[0] + dy[d];
                nx = pos[1] + dx[d];

                //    바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
                if(!(ny<0||nx<0||ny>=n||nx>=m)&&map[ny][nx]==0) {
                    q.add(new int[]{ny,nx});
                } else{ //1번으로 돌아간다.
                    q.add(pos);
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt() -2;
        m = sc.nextInt() -2;

        y = sc.nextInt() -1;
        x = sc.nextInt() -1;
//           0인 경우 북쪽,
//           1인 경우 동쪽,
//           2인 경우 남쪽,
//           3인 경우 서쪽
        d = sc.nextInt();


        map = new int[n][m];

        for (int i = 0; i < n+2; i++) {
            for (int j = 0; j < m+2; j++) {
                if(i==0||i==n+1||j==0||j==m+1) sc.nextInt();
                else{
                    map[i-1][j-1] = sc.nextInt();
                }
            }
        }
        System.out.println(new Main().solution());

    }
}