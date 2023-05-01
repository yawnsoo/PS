import java.util.*;

public class Main {
    static int n;
    static int l;
    static int answer;
    static int[][] map_w; //가로
    static int[][] map_h; //세로


    public static void solution() {

        for (int i = 0; i < n; i++) {
            if(isPath(map_w[i])){
                answer++;
            }
            if(isPath(map_h[i])){
                answer++;
            }
        }
    }

    //TODO
    // 연속 경사로의 경우 추가 고려해야함
    private static boolean isPath(int[] ints) {
        Deque<Integer> front = new ArrayDeque<>();
        Deque<Integer> back = new ArrayDeque<>();
        boolean[] filled = new boolean[n];

        front.add(ints[0]);

        for (int i = 1; i < n; i++) {

//            if(front.isEmpty()){
//                front.add(ints[i]);
//                continue;
//            }

            int diff = front.getLast() - ints[i];

            if(diff==0){ //1. 차이가 0이면 front에 넣고
                front.addLast(ints[i]);

            } else if(diff==-1){ //2. 1 만큼 높아지면,
                //  front의 길이와 l 비교

                if(front.size() < l) return false; // l보다 작으면 false
                else{
                //      l보다 같거나 크면 : front 초기화
                    front.clear();
                    for (int j = 0; j < l; j++) {
                        if(!filled[i-j-1]){
                            filled[i-j-1] = true;
                        } else return false;
                    }
                    front.addLast(ints[i]);
                }
            }else if(diff==1){ //3. 1 만큼 낮아지면,
                int t = i;
                int cnt = 0;
                //  i번째와 숫자가 같을 때 까지 back에 add
                //      back의 길이가 l이 되면 break
                while (t<n && ints[i] == ints[t] && cnt < l) {
                    back.addLast(ints[t]);
                    t++;
                    cnt++;
                }
                //  back의 길이가 l보다 작으면 false
                if(cnt<l||back.isEmpty()) return false;
                else{
                    //  front, back 초기화
                    for (int j = 0; j <l; j++) {
                        if(!filled[i+j]){
                            filled[i+j] = true;
                        } else return false;
                    }

                    i = t-1;

                    front.clear();
                    back.clear();
                    front.addLast(ints[i]);

                    //낮아졌다 바로 높아진 경우
                    if(t<n&&ints[t-1]<ints[t]) return false;

                }
            } else{
                //4. 나머지는 false
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        l = sc.nextInt();
        map_w = new int[n][n];
        map_h = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int a = sc.nextInt();
                map_w[i][j] = a;
                map_h[j][i] = a;
            }
        }

        solution();

        System.out.println(answer);
    }
}