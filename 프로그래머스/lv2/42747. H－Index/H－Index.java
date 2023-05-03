import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        
        Arrays.sort(citations);
        
        
        // 0 1 3 3 5 6
        for(int i = 0 ; i<citations.length; i++){
            int h = citations.length - i;
            boolean flag = true;
            
            
            for(int j = i; j<citations.length; j++){
                if(citations[j]<h){
                    flag = false;
                    break;
                }
            }
            
            if(flag) {
                return h;
            }
        }
        
        
        return answer;
    }
}