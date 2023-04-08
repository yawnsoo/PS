class Solution {
    int answer = 0;
    
    public void dfs(int[] numbers, int start, int pick, int total, int target){
        
        if(pick==numbers.length){
            if(total==target) answer++;
            return;
        }
        
        dfs(numbers,start+1, pick+1, total+numbers[start], target);
        dfs(numbers,start+1, pick+1, total-numbers[start], target);
        
    }
    
    public int solution(int[] numbers, int target) {
        
        //1. +,- 개수 = numbers.length
        //2. dfs로 구현 (마지막 결과값으로 판단)
        // 2.1 종료 조건 : pick==numbers.length
        //      2.1.1 target과 같을 때 answer++
        // 2.2 dfs(numbers[], start, pick, total)
        
        dfs(numbers,0,0,0,target);
        
        return answer;
    }
}