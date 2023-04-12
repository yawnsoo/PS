import java.util.*;

public class Main {

    int max = 0;
    static int n = 0;
    int[][] input = new int[n][n];

    public int maxNum(int[][] map){
        int maxMap = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(maxMap<map[i][j]){
                    maxMap = map[i][j];
                }
            }
        }

        return maxMap;
    }

    public int[][] copyMap(int[][] map){
        int[][] mapCopied = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mapCopied[i][j] = map[i][j];
            }
        }

        return mapCopied;
    }

    public int[][] mapMoved(int[][] map, String param){

        input = copyMap(map);

        switch (param){
            case "L" :
                for (int i = 0; i < n; i++) {
                    //한쪽으로 몰아 넣기
                    Queue<Integer> q1 = new LinkedList<>();
                    //더하기
                    Queue<Integer> q2 = new LinkedList<>();

                    //0이 아닌 경우 이웃하게 만들기
                    for(int num : map[i]){
                        if(num!=0){
                            q1.offer(num);
                        }
                    }

                    //더하기
                    while(!q1.isEmpty()){
                        int a = q1.poll();
                        if(!q1.isEmpty()&&a==q1.peek()){
                            a += q1.poll();
                        }
                        q2.offer(a);
                    }

                    //더한 값 적용하기
                    for (int j = 0; j < n; j++) {
                        if(!q2.isEmpty()){
                            map[i][j] = q2.poll();
                        } else{
                            map[i][j] = 0;
                        }
                    }
                }
                break;

            case "R" :
                for (int i = 0; i < n; i++) {
                    //한쪽으로 몰아 넣기
                    Stack<Integer> s1 = new Stack<>();
                    //더하기
                    Queue<Integer> q1 = new LinkedList<>();

                    //0이 아닌 경우 이웃하게 만들기
                    for(int num : map[i]){
                        if(num!=0){
                            s1.push(num);
                        }
                    }

                    //더하기
                    while(!s1.isEmpty()){
                        int a = s1.pop();
                        if(!s1.isEmpty()&&a==s1.peek()){
                            a += s1.pop();
                        }
                        q1.offer(a);
                    }

                    //더한 값 적용하기
                    for (int j = n-1; j > -1; j--) {
                        if(!q1.isEmpty()){
                            map[i][j] = q1.poll();
                        } else{
                            map[i][j] = 0;
                        }
                    }
                }
                break;

            case "U" :
                for (int i = 0; i < n; i++) {
                    //한쪽으로 몰아 넣기
                    Queue<Integer> q1 = new LinkedList<>();
                    //더하기
                    Queue<Integer> q2 = new LinkedList<>();

                    //0이 아닌 경우 이웃하게 만들기
                    for (int j = 0; j < n; j++) {
                        int num = map[j][i];
                        if(num!=0){
                            q1.offer(num);
                        }
                    }

                    //더하기
                    while(!q1.isEmpty()){
                        int a = q1.poll();
                        if(!q1.isEmpty()&&a==q1.peek()){
                            a += q1.poll();
                        }
                        q2.offer(a);
                    }

                    //더한 값 적용하기
                    for (int j = 0; j < n; j++) {
                        if(!q2.isEmpty()){
                            map[j][i] = q2.poll();
                        } else{
                            map[j][i] = 0;
                        }
                    }
                }
                break;

            case "D" :
                for (int i = 0; i < n; i++) {
                    //한쪽으로 몰아 넣기
                    Stack<Integer> s1 = new Stack<>();
                    //더하기
                    Queue<Integer> q1 = new LinkedList<>();

                    //0이 아닌 경우 이웃하게 만들기
                    for (int j = 0; j < n; j++) {
                        int num = map[j][i];
                        if(num!=0){
                            s1.push(num);
                        }
                    }

                    //더하기
                    while(!s1.isEmpty()){
                        int a = s1.pop();
                        if(!s1.isEmpty()&&a==s1.peek()){
                            a += s1.pop();
                        }
                        q1.offer(a);
                    }

                    //더한 값 적용하기
                    for (int j = n-1; j > -1; j--) {
                        if(!q1.isEmpty()){
                            map[j][i] = q1.poll();
                        } else{
                            map[j][i] = 0;
                        }
                    }
                }
                break;
        }



        return map;
    }

    //변경사항 확인
    public boolean isSame(int[][] map){

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j]!=input[i][j]){
                    return false;
                }
            }
        }

        return true;
    }

    public void dfs(int[][] map, int phase){

        //종료 : 5번 or 전이랑 변경 없으면
        if(phase==5||isSame(map)){
            //  - 최댓값 비교 후 저장
            int maxNum = maxNum(map);
            if(max<maxNum){
                max = maxNum;
            }
            return;
        }


        //반복 :
        //  - 왼쪽
        dfs(mapMoved(copyMap(map),"L"),phase+1);
        //  - 오른쪽
        dfs(mapMoved(copyMap(map),"R"),phase+1);
        //  - 위
        dfs(mapMoved(copyMap(map),"U"),phase+1);
        //  - 아래
        dfs(mapMoved(copyMap(map),"D"),phase+1);

    }

    public int Main(int[][] map) {

        //dfs
        dfs(map, 0);

        return max;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int a = new Main().Main(map);

        System.out.println(a);
    }
}
