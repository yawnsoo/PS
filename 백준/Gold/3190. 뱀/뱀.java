import java.util.*;

public class Main {

    private static int n = 0;
    private static int answer = 0;

    //record는 java14 부터 이용 가능
    //record는 DTO 역할
    //내부 클래스는 static으로 만드는게 좋음
    //  - 공간 복잡도 낮출 수 있음
    private static class TimeAndDir{

        private final int time;
        private final String dir;

        private TimeAndDir(int time, String dir) {
            this.time = time;
            this.dir = dir;
        }

        public int getTime() {
            return time;
        }

        public String getDir() {
            return dir;
        }
    }

    private static class Snake{

        private final Deque<int[]> headToTail;
        private final int time;

        private Snake(Deque<int[]> headToTail, int time){
            this.headToTail = headToTail;
            this.time = time;
        }

        private Deque<int[]> getHeadToTail(){
            return headToTail;
        }

        private int getTime(){
            return time;
        }
    }


    public int solution(int[][] map, Queue<TimeAndDir> turningPoint, boolean[][] visited){

        Queue<Snake> snakeFootPrint = new LinkedList<>();
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{0,0});
        visited[0][0] = true;
//        게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1 이다. 뱀은 처음에 오른쪽을 향한다.
        snakeFootPrint.offer(new Snake(deque, 0)); // y,x,cnt
        int[] dir = new int[]{0,1};

        while (!snakeFootPrint.isEmpty()){
            Snake snake = snakeFootPrint.poll();
            Deque<int[]> headToTail = snake.getHeadToTail();
            int y = headToTail.getFirst()[0];
            int x = headToTail.getFirst()[1];
            int cnt = snake.getTime()+1;
            int ny = y+dir[0];
            int nx = x+dir[1];
//        뱀은 매 초마다 이동을 하는데 다음과 같은 규칙을 따른다.



//            4. 벽, 자기 자신과 부딪히면 종료
            if(ny<0||nx<0||ny>=n||nx>=n||visited[ny][nx]){
                answer = cnt;
                break;
            }

            headToTail.offerFirst(new int[]{ny,nx});
            visited[ny][nx] = true;

//            1. 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
            if(map[ny][nx]==1){
//            2. 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
                map[ny][nx]=0;
            } else{
//            3. 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
                int[] pos = headToTail.removeLast();
                visited[pos[0]][pos[1]] = false;
            }

            snakeFootPrint.offer(new Snake(headToTail, cnt));


//            5. X초가 끝난 뒤 List<timeAndDir>에 따라 방향 전환
            if(!turningPoint.isEmpty()&&cnt==turningPoint.peek().getTime()){
//                - L : 왼쪽
                if(turningPoint.peek().getDir().equals("L")){
                    if(dir[0]==0&&dir[1]==1){
                        dir[0] = -1;
                        dir[1] = 0;
                    } else if(dir[0]==-1&&dir[1]==0){
                        dir[0] = 0;
                        dir[1] = -1;
                    } else if(dir[0]==0&&dir[1]==-1){
                        dir[0] = 1;
                        dir[1] = 0;
                    } else{
                        dir[0] = 0;
                        dir[1] = 1;
                    }
//                - D : 오른쪽
                }else{
                    if(dir[0]==0&&dir[1]==1){
                        dir[0] = 1;
                        dir[1] = 0;
                    } else if(dir[0]==-1&&dir[1]==0){
                        dir[0] = 0;
                        dir[1] = 1;
                    } else if(dir[0]==0&&dir[1]==-1){
                        dir[0] = -1;
                        dir[1] = 0;
                    } else{
                        dir[0] = 0;
                        dir[1] = -1;
                    }
                }

                turningPoint.poll();
            }

        }


        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int[][] map = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        int k = sc.nextInt();
        for (int i = 0; i < k; i++) {
            int y = sc.nextInt();
            int x = sc.nextInt();

            map[y-1][x-1] = 1;
        }


        int l = sc.nextInt();
        Queue<TimeAndDir> turningPoint = new LinkedList<>();
        for (int i = 0; i < l; i++) {
            int time = sc.nextInt();
            String dir = sc.next();
            turningPoint.add(new TimeAndDir(time, dir));
        }

        System.out.println(new Main().solution(map,turningPoint, visited));
    }
}