import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int max = 0;


    public int solution() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                lined(i,j); // ㅡ ㅣ
                square4(i,j); // ㅁ
                square6(i,j); // ㅏ ㅓ ㅗ ㅜ L Z ...
            }
        }

        return max;
    }

    private void square6(int y, int x) {
        if(y+2<n&&x+1<m){
            // ㅏ ㅓ
            max = Math.max(max, map[y][x]+map[y+1][x]+map[y+2][x]+map[y+1][x+1]);
            max = Math.max(max, map[y][x+1]+map[y+1][x+1]+map[y+2][x+1]+map[y+1][x]);

            // ⎡ ⎣ ⎤ ⎦
            max = Math.max(max, map[y][x]+map[y+1][x]+map[y+2][x]+map[y][x+1]);
            max = Math.max(max, map[y][x]+map[y+1][x]+map[y+2][x]+map[y+2][x+1]);
            max = Math.max(max, map[y][x+1]+map[y+1][x+1]+map[y+2][x+1]+map[y][x]);
            max = Math.max(max, map[y][x+1]+map[y+1][x+1]+map[y+2][x+1]+map[y+2][x]);

            //⚡️, 반대
            max = Math.max(max, map[y][x]+map[y+1][x]+map[y+1][x+1]+map[y+2][x+1]);
            max = Math.max(max, map[y][x+1]+map[y+1][x+1]+map[y+1][x]+map[y+2][x]);

        }
        if(y+1<n&&x+2<m){
            // ㅗ ㅜ
            max = Math.max(max, map[y+1][x]+map[y+1][x+1]+map[y+1][x+2]+map[y][x+1]);
            max = Math.max(max, map[y][x]+map[y][x+1]+map[y][x+2]+map[y+1][x+1]);

            // ⌐ ⌝ ⌙ ⌟
            max = Math.max(max, map[y][x]+map[y][x+1]+map[y][x+2]+map[y+1][x]);
            max = Math.max(max, map[y][x]+map[y][x+1]+map[y][x+2]+map[y+1][x+2]);
            max = Math.max(max, map[y+1][x]+map[y+1][x+1]+map[y+1][x+2]+map[y][x]);
            max = Math.max(max, map[y+1][x]+map[y+1][x+1]+map[y+1][x+2]+map[y][x+2]);

            // z ⌁
            max = Math.max(max, map[y][x]+map[y][x+1]+map[y+1][x+1]+map[y+1][x+2]);
            max = Math.max(max, map[y][x+2]+map[y][x+1]+map[y+1][x+1]+map[y+1][x]);
        }

    }

    private void square4(int y, int x) {
        if(y+1<n&&x+1<m){ // ㅁ
            max = Math.max(max, map[y][x]+map[y+1][x]+map[y][x+1]+map[y+1][x+1]);
        }
    }

    private void lined(int y, int x) {
        if(y+3<n){ // ㅣ
            max = Math.max(max, map[y][x]+map[y+1][x]+map[y+2][x]+map[y+3][x]);
        }
        if(x+3<m){ // ㅡ
            max = Math.max(max, map[y][x]+map[y][x+1]+map[y][x+2]+map[y][x+3]);
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];

        for(int i = 0; i<n; i++){
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        System.out.println(new Main().solution());

    }
}