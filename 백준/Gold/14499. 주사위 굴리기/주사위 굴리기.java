import java.util.*;

public class Main {
    static int n;
    static int m;
    static int ny; //x
    static int nx; //y
    static int[][] map;
    static int[] dice = new int[6];
    static boolean isMoved;

    public int solution(int command) {
        //주사위 이동
        moveDice(command);
        //이동 여부 확인
        if(!isMoved){
            return -1;
        }

        //주사위 회전
        rollDice(command);

        if(map[ny][nx]==0){ //이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
            map[ny][nx] = dice[5];
        }else{ //0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
            dice[5] = map[ny][nx];
            map[ny][nx] = 0;
        }

        return dice[0];
    }

    private void moveDice(int command) {
        switch (command) {
            case 1 :
                if(nx<m-1){
                    nx += 1;
                    isMoved = true;
                }
                break;

            case 2 :
                if(nx>0){
                    nx -= 1;
                    isMoved = true;
                }
                break;

            case 3 :
                if(ny>0){
                    ny -= 1;
                    isMoved = true;
                }
                break;

            case 4 :
                if(ny<n-1){
                    ny += 1;
                    isMoved = true;
                }
                break;

            default : System.out.println("moveDice : Invalid command");
        }
    }
    private void rollDice(int command) {
        int[] temp = new int[6];
        switch (command) {
            case 1 : //동
                temp[0] = dice[3];
                temp[1] = dice[1];
                temp[2] = dice[0];
                temp[3] = dice[5];
                temp[4] = dice[4];
                temp[5] = dice[2];
                break;

            case 2 : //서
                temp[0] = dice[2];
                temp[1] = dice[1];
                temp[2] = dice[5];
                temp[3] = dice[0];
                temp[4] = dice[4];
                temp[5] = dice[3];
                break;

            case 3 : //북
                temp[0] = dice[4];
                temp[1] = dice[0];
                temp[2] = dice[2];
                temp[3] = dice[3];
                temp[4] = dice[5];
                temp[5] = dice[1];
                break;

            case 4 : //남
                temp[0] = dice[1];
                temp[1] = dice[5];
                temp[2] = dice[2];
                temp[3] = dice[3];
                temp[4] = dice[0];
                temp[5] = dice[4];
                break;
            default : System.out.println("rollDice : Invalid command");
        }
        // Arrays.copy() vs System.arraycopy vs for-loop
        // https://stackoverflow.com/questions/18638743/is-it-better-to-use-system-arraycopy-than-a-for-loop-for-copying-arrays
        System.arraycopy(temp, 0, dice, 0, 6);

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        ny = sc.nextInt(); //x
        nx = sc.nextInt(); //y
        int k = sc.nextInt();

        map = new int[n][m];

        for(int i = 0; i<n; i++){
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < k; i++) {
            isMoved = false;
            int command = sc.nextInt();
            int answer = new Main().solution(command);

            if(answer!=-1){
                System.out.println(answer);
            }
        }

    }
}
