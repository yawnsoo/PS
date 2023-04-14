import java.util.*;

public class Main {

    int[] attacker = new int[2];
    int[] target = new int[2];
    List<int[]> footprint;


    private void findAttackerAndTarget(int[][] map, int n, int m, int[][] attackTime) {
        int min = 5001;
        int max = 0;
        int attackTimeMin = 1001;
        int attackTimeMax = 0;
        int attackerMaxMN = 0;
        int targetMinMN = m+n+1;
        int attackerMaxM = 0;
        int targetMinM = m+1;

        Queue<int[]> attackerQ = new LinkedList<>();
        Queue<int[]> targetQ = new LinkedList<>();

        // 부서지지 않은 포탑 중 가장 약한 포탑, 가장 강한 포탑의 값
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j]>max){
                    max = map[i][j];
                }
                if(map[i][j]!=0&&map[i][j]<min){
                    min = map[i][j];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j]==min){ // 공격력이 가장 낮은 포탑이 attacker.
                    attackerQ.offer(new int[]{i,j});
                } else if(map[i][j]==max){ // 가장 강한 포탑이 target
                    targetQ.offer(new int[]{i,j});
                }
            }
        }

        //  - 가장 최근에 공격한 포탑이 가장 약한 포탑입니다. (모든 포탑은 시점 0에 모두 공격한 경험이 있다고 가정하겠습니다.)
        if(attackerQ.size()>1){
            for (int i = 0; i < attackerQ.size(); i++) {
                int[] t = attackerQ.poll();
                if(attackTime[t[0]][t[1]]>attackTimeMax){
                    attackTimeMax = attackTime[t[0]][t[1]];
                }
                attackerQ.offer(t);
            }

            int size = attackerQ.size();
            for (int i = 0; i < size; i++) {
                int[] t = attackerQ.poll();
                if(attackTime[t[0]][t[1]]==attackTimeMax){
                    attackerQ.offer(t);
                }
            }
        }

        //  - 공격한지 가장 오래된 포탑이 가장 강한 포탑입니다. (모든 포탑은 시점 0에 모두 공격한 경험이 있다고 가정하겠습니다.)
        if(targetQ.size()>1){
            for (int i = 0; i < targetQ.size(); i++) {
                int[] t = targetQ.poll();
                if(attackTime[t[0]][t[1]]<attackTimeMin){
                    attackTimeMin = attackTime[t[0]][t[1]];
                }
                targetQ.offer(t);
            }

            int size = targetQ.size();
            for (int i = 0; i < size; i++) {
                int[] t = targetQ.poll();
                if(attackTime[t[0]][t[1]]==attackTimeMin){
                    targetQ.offer(t);
                }
            }
        }

        //  - 각 포탑 위치의 행과 열의 합이 가장 큰 포탑이 가장 약한 포탑입니다.
        if(attackerQ.size()>1){
            for (int i = 0; i < attackerQ.size(); i++) {
                int[] t = attackerQ.poll();
                if(t[0]+t[1]>attackerMaxMN){
                    attackerMaxMN = t[0]+t[1];
                }
                attackerQ.offer(t);
            }

            int size = attackerQ.size();
            for (int i = 0; i < size; i++) {
                int[] t = attackerQ.poll();
                if(t[0] + t[1]==attackerMaxMN){
                    attackerQ.offer(t);
                }
            }
        }


        //  - 각 포탑 위치의 행과 열의 합이 가장 작은 포탑이 가장 강한 포탑입니다.
        if(targetQ.size()>1){
            for (int i = 0; i < targetQ.size(); i++) {
                int[] t = targetQ.poll();
                if(t[0]+t[1]<targetMinMN){
                    targetMinMN = t[0]+t[1];
                }
                targetQ.offer(t);
            }

            int size = targetQ.size();
            for (int i = 0; i < size; i++) {
                int[] t = targetQ.poll();
                if(t[0]+t[1]==targetMinMN){
                    targetQ.offer(t);
                }
            }
        }


        //  - 각 포탑 위치의 열(m) 값이 가장 큰 포탑이 가장 약한 포탑입니다.
        if(attackerQ.size()>1){
            for (int i = 0; i < attackerQ.size(); i++) {
                int[] t = attackerQ.poll();
                if(t[1]>attackerMaxM){
                    attackerMaxM = t[1];
                }
                attackerQ.offer(t);
            }

            int size = attackerQ.size();
            for (int i = 0; i < size; i++) {
                int[] t = attackerQ.poll();
                if(t[1]==attackerMaxM){
                    attackerQ.offer(t);
                }
            }
        }

        //  - 각 포탑 위치의 열 값이 가장 작은 포탑이 가장 강한 포탑입니다.
        if(targetQ.size()>1){
            for (int i = 0; i < targetQ.size(); i++) {
                int[] t = targetQ.poll();
                if(t[1]<targetMinM){
                    targetMinM = t[1];
                }
                targetQ.offer(t);
            }

            int size = targetQ.size();
            for (int i = 0; i < size; i++) {
                int[] t = targetQ.poll();
                if(t[1]==targetMinM){
                    targetQ.offer(t);
                }
            }
        }

        attacker = attackerQ.poll();
        target = targetQ.poll();
        if(attackerQ.size()+ targetQ.size()>0){
            System.out.println("error");
        }

    }


    public static class PosAndRoute{
        private final int[] pos;
        private final List<int[]> route;

        public PosAndRoute(int[] pos, List<int[]> route){
            this.pos = pos;
            this.route = route;
        }


        public int[] getPos() {
            return pos;
        }

        public List<int[]> getRoute() {
            return new ArrayList<>(route);
        }
    }


    private boolean bfs(int[][] map, int n, int m, boolean[][] visited) {
        int[] dy = new int[]{0,1,0,-1};
        int[] dx = new int[]{1,0,-1,0};

        Queue<PosAndRoute> q = new LinkedList<>();
        q.offer(new PosAndRoute(new int[]{attacker[0], attacker[1]}, new ArrayList<>()));
        visited[attacker[0]][attacker[1]] = true;


        while (!q.isEmpty()){
            PosAndRoute temp = q.poll();
            int[] pos = temp.getPos();
            List<int[]> route = temp.getRoute();

            for (int i = 0; i < 4; i++) {
                int ny = pos[0] + dy[i];
                int nx = pos[1] + dx[i];

                //  - 가장자리에서 막힌 방향으로 진행하고자 한다면, 반대편으로 나옵니다.
                if (ny < 0 || ny >= n) ny = (ny + n) % n;
                if (nx < 0 || nx >= m) nx = (nx + m) % m;

                //  - 부서진 포탑이 있는 위치는 지날 수 없습니다.
                if(!visited[ny][nx]&&map[ny][nx]!=0){

                    if(ny==target[0]&&nx==target[1]){
                        visited[ny][nx] = true;
                        footprint = route;
                        return true;
                    }

                    route.add(new int[]{ny, nx});
                    q.offer(new PosAndRoute(new int[]{ny, nx}, new ArrayList<>(route)));
                    route.remove(route.size()-1);
                    visited[ny][nx] = true;
                }
            }
        }
        return false;
    }

    private void attack(int[][] map, int n, int m, boolean[][] mapEffected) {

        //레이저 공격
//        if (isReachable(map, attacker, n, m) || isReachable(map, target, n, m)) {

        //bfs로 가중치 남기고
        boolean[][] visited = new boolean[n][m];
        //  - 공격자의 위치에서 공격 대상 포탑까지의 최단 경로로 공격합니다.

        // a. 레이저 공격
        if(bfs(map,n,m, visited)){
            // 루트에 있는 위치들 mapEffected에 넣고, 계산
            //  - 레이저 경로에 있는 포탑도 공격을 받게 되는데, 이 포탑은 공격자 공격력의 절반 만큼의 공격을 받습니다.
            for(int[] i : footprint){
                mapEffected[i[0]][i[1]] = true;
                map[i[0]][i[1]] -= map[attacker[0]][attacker[1]]/2;
                //  - 공격을 받아 공격력이 0 이하가 된 포탑은 부서집니다.
                if(map[i[0]][i[1]]<0) map[i[0]][i[1]] = 0;
            }

            map[target[0]][target[1]] -= map[attacker[0]][attacker[1]];
            if(map[target[0]][target[1]]<0) map[target[0]][target[1]] = 0;

        } else {
            //  - 주위 8개의 방향에 있는 포탑도 피해를 입는데,
            int[] dy = new int[]{-1,-1,-1,0,0,1,1,1};
            int[] dx = new int[]{-1,0,1,-1,1,-1,0,1};

            for (int i = 0; i < 8; i++) {
                int ny = target[0] + dy[i];
                int nx = target[1] + dx[i];

                //  - 가장자리에서 막힌 방향으로 진행하고자 한다면, 반대편으로 나옵니다.
                if (ny < 0 || ny >= n) ny = (ny + n) % n;
                if (nx < 0 || nx >= m) nx = (nx + m) % m;

                //  - 공격자는 해당 공격에 영향을 받지 않습니다
                if(ny==attacker[0]&&nx==attacker[1]) continue;

                if(map[ny][nx]!=0){
                    //  - 공격자 공격력의 절반 만큼의 피해를 받습니다.
                    map[ny][nx] -= map[attacker[0]][attacker[1]]/2;
                    //  - 공격을 받아 공격력이 0 이하가 된 포탑은 부서집니다.
                    if(map[ny][nx]<0) map[ny][nx] = 0;
                    mapEffected[ny][nx] = true;
                }
            }

            map[target[0]][target[1]] -= map[attacker[0]][attacker[1]];
            if(map[target[0]][target[1]]<0) map[target[0]][target[1]] = 0;
        }
    }

    private void repair(int[][] map, boolean[][] mapEffected, int n, int m) {
        for (int j = 0; j < n; j++) {
            for (int l = 0; l < m; l++) {
                if(!mapEffected[j][l]&&map[j][l]!=0){
                    map[j][l]++;
                }
            }
        }
    }

    public int solution(int[][] map, int n, int m, int k){

        int[][] attackTime = new int[n][m];

        for (int i = 1; i < k+1; i++) {
            boolean[][] mapEffected = new boolean[n][m];

            //1.공격자, 타겟 선정 :
            findAttackerAndTarget(map,n,m, attackTime);

            if (Objects.nonNull(target)) {

                // N+M만큼의 공격력이 증가, 공격 시간 적용
                map[attacker[0]][attacker[1]] += (m+n);
                attackTime[attacker[0]][attacker[1]] = i;
                mapEffected[attacker[0]][attacker[1]] = true;
                //2.공격자의 공격 :
                // 공격자는 자신을 제외한 가장 강한 포탑을 공격합니다.
                // 타겟이 없는 경우도 있음
                mapEffected[target[0]][target[1]] = true;
                attack(map, n, m, mapEffected);

            } else {
                break;
            }

            //3. 포탑 정비 :
            // 부서지지 않은 포탑 중 공격과 무관했던 포탑은 공격력이 1씩 올라갑니다
            //  : 공격자도 아니고, 공격에 피해를 입은 포탑도 아니라는 뜻
            repair(map, mapEffected, n ,m);

        }

        return findAnswer(map);
    }

    private int findAnswer(int[][] map) {

        int max = 0;

        for(int[] y : map){
            for(int x : y){
                if(x>max) max = x;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        System.out.println(new Main().solution(map,n,m,k));
    }
}
