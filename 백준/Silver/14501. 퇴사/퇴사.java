import java.util.*;

public class Main {

    static int answer = 0;
    boolean[] check;


    private void dfs(int n, List<int[]> schedule, int day, int total, int last){
        //종료 조건 : 종료 날짜
        if(day>n-1) { //  - 마지막 날
            if(day==n){
                answer = Math.max(answer,total);
            }else{ //  - 마지막날 초과 : 초과한 날짜 빼기
                answer = Math.max(answer,total-last);
            }

            return;
        }

        for (int i = day; i < n; i++) {
            if(!check[i]){
                check[i] = true;
                dfs(n, schedule, i+schedule.get(i)[0], total+schedule.get(i)[1], schedule.get(i)[1]);
                check[i] = false;
            }
        }

    }

    public int solution(int n, List<int[]> schedule){

        check = new boolean[n];

        dfs(n, schedule, 0, 0,0);

        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<int[]> schedule = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int t = sc.nextInt();
            int p = sc.nextInt();
            schedule.add(new int[]{t,p});
        }

        System.out.println(new Main().solution(n, schedule));
    }
}