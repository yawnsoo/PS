/**
 * 1. 최소이므로 bfs 문제
 * 2. queue 이용
 *      - 이동 조건
 *          - "." 일 경우
 *              - "#" || "B" 만나면 멈춤
 *          - 최소가 되기 위해선 무조건 가로 -> 세로 || 세로 -> 가로 순서로 움직임
 *              - ex) 왼쪽 -> 오른쪽 으로 기울이면 제자리로 돌아옴
 *          - 공이 같은 위치에 존재할 경우 X
 *              - R,B가 이동한 각각의 값 중 최솟값 이용해서 위치 조정
 *
 *      - 종료 조건
 *          - R이 "0"에 도달할 경우
 *              - B가 있는지 확인
 *                  - 있으면 정답
 *          - B가 "0"에 도달할 경우
 *              - 무시하고 진행
 *          - 11번째 시행한 경우
 *              - -1 반환
 *
 * 3. R가 움직일 경우
 *      - B를 R과 같은 방향으로 이동
 * 4. 10번 이상 시행될 경우 -1 반환
 *
 *
 * **/

import java.util.*;

public class Main {
    int answer = 0;

    static class Ball{
        private int y;
        private int x;

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public Ball(int y, int x){
            this.y = y;
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setX(int x) {
            this.x = x;
        }

    }

    static class Balls3Cnt{
        private Ball ballRed;
        private Ball ballBlue;
        private int cnt;
        private boolean[] dir;

        public Balls3Cnt(Ball ballRed, Ball ballBlue, int cnt, boolean[] dir) {
            this.ballRed = ballRed;
            this.ballBlue = ballBlue;
            this.cnt = cnt;
            this.dir = dir;
        }

        public boolean[] getDir() {
            return dir;
        }

        public Ball getBallRed() {
            return ballRed;
        }


        public Ball getBallBlue() {
            return ballBlue;
        }


        public int getCnt() {
            return cnt;
        }
    }

    public void bfs(Ball ballRed, Ball ballBlue, String[][] map){
        Queue<Balls3Cnt> q = new LinkedList<>();

        int[] dy = new int[]{1,0,-1,0};
        int[] dx = new int[]{0,1,0,-1};
        boolean[] dir = new boolean[4];

        q.add(new Balls3Cnt(ballRed, ballBlue,0, dir));

        while(!q.isEmpty()){
            Balls3Cnt t = q.poll();
            Ball rbt = t.getBallRed();
            Ball bbt = t.getBallBlue();
            int cnt = t.getCnt();

            int ry = rbt.getY();
            int rx = rbt.getX();

            int by = bbt.getY();
            int bx = bbt.getX();


            for (int i = 0; i < 4; i++) {

                int nry = ry + dy[i];
                int nrx = rx + dx[i];
                int nby = by + dy[i];
                int nbx = bx + dx[i];

                int rmoved = 1;
                int bmoved = 1;
                boolean isBlueBall = true;

                try{

                    if(!t.getDir()[i]){

                        boolean[] dirt = new boolean[4];

                        while(!map[nby][nbx].equals("#")){
                            if(map[nby][nbx].equals("O")){
                                isBlueBall = false;
                                break;
                            }

                            nby += dy[i];
                            nbx += dx[i];
                            bmoved++;
                        }

                        while(!map[nry][nrx].equals("#")&&isBlueBall){

                            if(map[nry][nrx].equals("O")){
                                answer = cnt+1;
                                return;
                            }

                            nry += dy[i];
                            nrx += dx[i];
                            rmoved++;
                        }

                        dirt[i] = true;
                        dirt[(i+2)%4] = true;

                        if(nry==nby&&nrx==nbx){
                            //공이 하나만 움직였을 경우, min값은 항상 1이됨.
                                if(rmoved<bmoved){
                                    nby = by + dy[i] * (bmoved-1);
                                    nbx = bx + dx[i] * (bmoved-1);
                                } else if(rmoved>bmoved){
                                    nry = ry + dy[i] * (rmoved-1);
                                    nrx = rx + dx[i] * (rmoved-1);
                                }

                        }

                        if(cnt<10){
                            if (!(nry - dy[i] == ry && nrx - dx[i] == rx && nby - dy[i] == by && nbx - dx[i] == bx)&&isBlueBall) {
                                q.add(new Balls3Cnt(
                                        new Ball(nry - dy[i], nrx - dx[i]),
                                        new Ball(nby - dy[i], nbx - dx[i]),
                                        cnt + 1, dirt));
                            }

                        }

                    }

                }catch (Exception ignored){}
            }
        }
    }

    public int solution(int h, int w, String[][] map){
        Ball red = new Ball(0,0);
        Ball blue = new Ball(0,0);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if(map[i][j].equals("R")){
                    red.setY(i);
                    red.setX(j);
                } else if(map[i][j].equals("B")){
                    blue.setY(i);
                    blue.setX(j);
                }
            }
        }

        bfs(red, blue, map);

        return answer==0||answer==11?-1:answer;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int h = sc.nextInt();
        int w = sc.nextInt();
        String[][] map = new String[h][w];
        sc.nextLine();

        for (int i = 0; i < h; i++) {
            map[i] = sc.nextLine().split("");
        }

        int ans = new Main().solution(h, w, map);
        System.out.println(ans);

    }
}
