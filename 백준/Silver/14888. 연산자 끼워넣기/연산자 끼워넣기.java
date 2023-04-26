import java.util.*;

public class Main {

    static int n;
    static int[] numbers;
    static int[] pmtd = new int[4];
    static int max = -1000000000;
    static int min = 1000000000;

    public static void solution(int depth, int total) {
        if(depth==n-1){
            max = Math.max(max, total);
            min = Math.min(min, total);
            return;
        }

        for (int i = 0, l = pmtd.length; i < l; i++) {
            if(pmtd[i]>0){
                pmtd[i]--;
                switch (i){
                    case 0 : // +
                        solution(depth+1,total+numbers[depth+1]);
                        break;
                    case 1 : // -
                        solution(depth+1,total-numbers[depth+1]);
                        break;
                    case 2 : // *
                        solution(depth+1,total*numbers[depth+1]);
                        break;
                    case 3 : // /
                        solution(depth+1,total/numbers[depth+1]);
                        break;
                }
                pmtd[i]++;
            }
        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        for (int i = 0; i < 4; i++) {
            pmtd[i] = sc.nextInt();
        }

        solution(0,numbers[0]);

        System.out.println(max);
        System.out.println(min);

    }
}