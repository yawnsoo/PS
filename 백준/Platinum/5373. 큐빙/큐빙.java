import java.util.*;

public class Main {
    static int c;
    static String[][][] cube;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        c = sc.nextInt();

        for (int i = 0; i < c; i++) {
            int n = sc.nextInt();
            sc.nextLine();
            String methods = sc.nextLine();

            cube = new String[][][]{
                    {{"w", "w", "w"}, {"w", "w", "w"}, {"w", "w", "w"}}, // u 0
                    {{"r", "r", "r"}, {"r", "r", "r"}, {"r", "r", "r"}}, // f 1
                    {{"y", "y", "y"}, {"y", "y", "y"}, {"y", "y", "y"}}, // d 2
                    {{"o", "o", "o"}, {"o", "o", "o"}, {"o", "o", "o"}}, // b 3
                    {{"g", "g", "g"}, {"g", "g", "g"}, {"g", "g", "g"}}, // l 4
                    {{"b", "b", "b"}, {"b", "b", "b"}, {"b", "b", "b"}}  // r 5
            };

            tilts(n, methods);
        }

    }

    private static void tilts(int n, String methods) {
        String[] methodsArr = methods.split(" ");

        for (String method : methodsArr) {
            char side = method.charAt(0);
            char dir = method.charAt(1);
            String[] temp2 = new String[3];

            if (dir == '-') { //반시계
                switch (side) {
                    case 'U' :
                        //해당 면 회전
                        tiltSide(0,dir);

                        //인접 면 회전
                        temp2 = cube[1][0].clone(); // f
                        cube[1][0] = cube[4][0];
                        cube[4][0] = cube[3][0];
                        cube[3][0] = cube[5][0];
                        cube[5][0] = temp2;
                        break;
                    case 'F' :
                        tiltSide(1,dir);

                        //인접 면 회전
                        temp2 = cube[0][2].clone();
                        for (int i = 0; i < 3; i++) {
                            cube[0][2][i] = cube[5][i][0];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[5][i][0] = cube[2][0][2-i];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[2][0][i] = cube[4][i][2];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[4][i][2] = temp2[2-i];
                        }
                        break;
                    case 'D' :
                        tiltSide(2,dir);

                        //인접 면 회전
                        temp2 = cube[1][2].clone(); // f
                        cube[1][2] = cube[5][2];
                        cube[5][2] = cube[3][2];
                        cube[3][2] = cube[4][2];
                        cube[4][2] = temp2;
                        break;
                    case 'B' :
                        tiltSide(3,dir);

                        //인접 면 회전
                        temp2 = cube[0][0].clone();
                        for (int i = 0; i < 3; i++) {
                            cube[0][0][i] = cube[4][2-i][0];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[4][i][0] = cube[2][2][i];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[2][2][i] = cube[5][2-i][2];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[5][i][2] = temp2[i];
                        }
                        break;
                    case 'L' :
                        tiltSide(4,dir);

                        //인접 면 회전
                        for (int i = 0; i < 3; i++) {
                            temp2[i] = cube[0][i][0];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[0][i][0] = cube[1][i][0];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[1][i][0] = cube[2][i][0];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[2][i][0] = cube[3][2-i][2];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[3][i][2] = temp2[2-i];
                        }
                        break;
                    case 'R' :
                        tiltSide(5,dir);

                        //인접 면 회전
                        for (int i = 0; i < 3; i++) {
                            temp2[i] = cube[0][i][2];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[0][i][2] = cube[3][2-i][0];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[3][i][0] = cube[2][2-i][2];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[2][i][2] = cube[1][i][2];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[1][i][2] = temp2[i];
                        }
                        break;
                }
            } else{ //시계
                switch (side) {
                    case 'U' :
                        //해당 면 회전
                        tiltSide(0,dir);

                        //인접 면 회전
                        temp2 = cube[1][0].clone(); // f
                        cube[1][0] = cube[5][0];
                        cube[5][0] = cube[3][0];
                        cube[3][0] = cube[4][0];
                        cube[4][0] = temp2;
                        break;
                    case 'F' :
                        tiltSide(1,dir);

                        //인접 면 회전
                        temp2 = cube[0][2].clone();
                        for (int i = 0; i < 3; i++) {
                            cube[0][2][i] = cube[4][2-i][2];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[4][i][2] = cube[2][0][i];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[2][0][i] = cube[5][2-i][0];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[5][i][0] = temp2[i];
                        }
                        break;
                    case 'D' :
                        tiltSide(2,dir);

                        //인접 면 회전
                        temp2 = cube[1][2].clone(); // f
                        cube[1][2] = cube[4][2];
                        cube[4][2] = cube[3][2];
                        cube[3][2] = cube[5][2];
                        cube[5][2] = temp2;
                        break;
                    case 'B' :
                        tiltSide(3,dir);

                        //인접 면 회전
                        temp2 = cube[0][0].clone();
                        for (int i = 0; i < 3; i++) {
                            cube[0][0][i] = cube[5][i][2];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[5][i][2] = cube[2][2][2-i];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[2][2][i] = cube[4][i][0];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[4][i][0] = temp2[2-i];
                        }
                        break;
                    case 'L' :
                        tiltSide(4,dir);

                        //인접 면 회전
                        for (int i = 0; i < 3; i++) {
                            temp2[i] = cube[0][i][0];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[0][i][0] = cube[3][2-i][2];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[3][i][2] = cube[2][2-i][0];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[2][i][0] = cube[1][i][0];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[1][i][0] = temp2[i];
                        }
                        break;
                    case 'R' :
                        tiltSide(5,dir);

                        //인접 면 회전
                        for (int i = 0; i < 3; i++) {
                            temp2[i] = cube[0][i][2];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[0][i][2] = cube[1][i][2];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[1][i][2] = cube[2][i][2];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[2][i][2] = cube[3][2-i][0];
                        }
                        for (int i = 0; i < 3; i++) {
                            cube[3][i][0] = temp2[2-i];
                        }
                        break;
                }
            }
        }

        for (String[] s : cube[0]) {
            System.out.printf("%s%s%s\n",s[0],s[1],s[2]);
        }
    }

    private static void tiltSide(int side, char dir) {
        String[][] temp = new String[3][3];

        if (dir == '-') {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[i][j] = cube[side][j][2-i];
                }
            }
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[i][j] = cube[side][2-j][i];
                }
            }
        }

        cube[side] = temp;
    }
}