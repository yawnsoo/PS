// https://www.hackerrank.com/challenges/flipping-the-matrix/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'flippingMatrix' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY matrix as parameter.
     */
     
     
     //완전탐색? X
     //  최악 = 2^(2*N)*2^(2*N) = 2^512
     
     //점화식
     // 0,0 에 위치 가능한 수 : 0,0 / 0,2n-1 / 2n-1,0 / 2n-1,2n-1
     // 0,1 에 위치 가능한 수 : 0,1 / 0,2n-1-1 / 2n-1,1 / 2n-1,2n-1-1
     // ....
     // 0,j 에 위치 가능한 수 : 0,j / 0,2n-1-j / 2n-1,j / 2n-1,2n-1-j
     //
     // 1,0 에 위치 가능한 수 : 1,0 / 1,2n-1 / 2n-1-1,0 / 2n-1-1,2n-1
     // ....
     // i,0 에 위치 가능한 수 : i,0 / i,2n-1 / 2n-1-i,0 / 2n-1-i,2n-1
     //
     // i,j 에 위치 가능한 수 : i,j / i,2n-1-j / 2n-1-i,j / 2n-1-i,2n-1-j
     // i<n, j<n
     // 각 위치의 max값을 구한 다음에 더하면 됨.
     // 시간 복잡도 : 4n^2
     
     
    public static int flippingMatrix(List<List<Integer>> matrix) {
    // Write your code here
        int answer = 0;
        int n = matrix.size()/2;
        
        for(int i = 0 ; i< n ; i++){
            for(int j = 0 ; j< n ; j++){
                answer += Math.max(
                    matrix.get(i).get(j),
                    Math.max(
                        matrix.get(i).get(2*n-1-j),
                        Math.max(matrix.get(2*n-1-i).get(j),
                        matrix.get(2*n-1-i).get(2*n-1-j))));
            }
        }
        
        return answer;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<List<Integer>> matrix = new ArrayList<>();

                IntStream.range(0, 2 * n).forEach(i -> {
                    try {
                        matrix.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int result = Result.flippingMatrix(matrix);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
