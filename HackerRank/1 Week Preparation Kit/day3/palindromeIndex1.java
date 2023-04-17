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


/*
시간 초과. 
*/
class Result {

    /*
     * Complete the 'palindromeIndex' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static boolean checkPalindrome(String s){
        
        StringBuffer front;
        StringBuffer back;
        
        if(s.length()%2==0){
            front = new StringBuffer(s.substring(0, s.length()/2));
            back = new StringBuffer(s.substring(s.length()/2));
        } else{
            front = new StringBuffer(s.substring(0, s.length()/2));
            back = new StringBuffer(s.substring(s.length()/2+1));
        }
        
        //StringBuffer에서 equals()는 메서드는 ==과 같다.
        //비교를 위해선 .toString()으로 변환 후 사용해야함.
        return front.toString().equals(back.reverse().toString());
    }
    
    
    public static int palindromeIndex(String s) {
    // Write your code here
    
    if(checkPalindrome(s)) return -1;
    
        for(int i = 0; i<s.length(); i++){
            String s2 = s.substring(0, i) + s.substring(i+1);
            
            if(checkPalindrome(s2)){
              return i;  
            }
        }
        
        return -1;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = Result.palindromeIndex(s);

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
