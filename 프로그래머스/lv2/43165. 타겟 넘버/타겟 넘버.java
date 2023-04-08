import java.util.*;

class Solution {
    int answer = 0;
    
    public void dfs(int[] numbers, int target, int depth,int result){
        if(depth==numbers.length){
            if(target==result){
                answer++;
            }
            return;
        }
        
        
        dfs(numbers, target, depth+1, result+numbers[depth]);
        dfs(numbers, target, depth+1, result-numbers[depth]);
        
        
    }
    
    public int solution(int[] numbers, int target) {
        
        
        dfs(numbers,target,0,0);
        
        return answer;
    }
}