//https://www.hackerrank.com/challenges/one-week-preparation-kit-plus-minus/problem


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
     * Complete the 'plusMinus' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void plusMinus(List<Integer> arr) {
    // Write your code here
    int[] pmz = new int[3];
    
    for(int i : arr){
        if(i>0){
            pmz[0]++;
        } else if(i<0){
            pmz[1]++;
        } else{
            pmz[2]++;
        }
    }
    
    for(int i : pmz){
        //String.format()을 사용하면 Math.round()와 똑같이 사용할 수 있다.
        // Math.round()의 경우 소수점 아래가 0일 경우 절삭하고 : Math.round((5000.000/1000)*1000) -> 5000
        // String.format()의 경우 소수점 아래가 0일 경우에도 모두 출력한다. : String.format("%.3f",5000.000) -> 5000.000
        // 하지만 성능은 Math.round()가 더 좋다.
        System.out.printf(String.format("%.6f\n",i/(float)arr.size()));
    }
    
    
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.plusMinus(arr);

        bufferedReader.close();
    }
}
