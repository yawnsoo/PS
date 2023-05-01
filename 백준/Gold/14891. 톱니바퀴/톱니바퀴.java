import java.util.*;

public class Main {
    static int k;
    static String[] cogwheels = new String[4];


    public static int solution(Queue<int[]> commands) {
        int ans = 0;

        while (!commands.isEmpty()) {
            int[] cmd = commands.poll();
            bfs(cmd[0], cmd[1]);
        }

        for (int i = 0; i < 4; i++) {
            ans += (cogwheels[i].charAt(0) - 48) * Math.pow(2, i);
        }

        return ans;
    }

    private static void bfs(int cogIdx, int param) {
        Queue<int[]> cogIdxAndParam = new LinkedList<>();
        boolean[] turned = new boolean[4];
        cogIdxAndParam.add(new int[]{cogIdx, param}); // n번째 톱니, 회전 방향

        while (!cogIdxAndParam.isEmpty()) {
            int[] cmd = cogIdxAndParam.poll();

            if(cmd[0]==0){ //1번째
                if(!turned[cmd[0]+1]&&cogwheels[cmd[0]].charAt(2)!=cogwheels[cmd[0]+1].charAt(6)){
                    cogIdxAndParam.add(new int[]{cmd[0]+1, cmd[1]*-1});
                }
            } else if (cmd[0] == 3) { //4번째
                if(!turned[cmd[0]-1]&&cogwheels[cmd[0]].charAt(6)!=cogwheels[cmd[0]-1].charAt(2)){
                    cogIdxAndParam.add(new int[]{cmd[0]-1, cmd[1]*-1});
                }
            } else { //중간
                if(!turned[cmd[0]+1]&&cogwheels[cmd[0]].charAt(2)!=cogwheels[cmd[0]+1].charAt(6)){
                    cogIdxAndParam.add(new int[]{cmd[0]+1, cmd[1]*-1});
                }
                if(!turned[cmd[0]-1]&&cogwheels[cmd[0]].charAt(6)!=cogwheels[cmd[0]-1].charAt(2)){
                    cogIdxAndParam.add(new int[]{cmd[0]-1, cmd[1]*-1});
                }
            }

            String s = cogwheels[cmd[0]];
            if (cmd[1] == 1) { // 회전 : 시계 방향
                s = s.charAt(7) + s.substring(0, 7);
                cogwheels[cmd[0]] = s;
            } else { // 회전 : 반 시계
                s = s.substring(1, 8) + s.charAt(0);
                cogwheels[cmd[0]] = s;
            }
            turned[cmd[0]] = true;
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 4; i++) {
            //오른쪽 idx=2, 왼쪽 idx=6
            cogwheels[i] = sc.nextLine();
        }


        k = sc.nextInt();
        Queue<int[]> commands = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            commands.add(new int[]{sc.nextInt()-1,sc.nextInt()});
        }

        System.out.println(solution(commands));
    }
}