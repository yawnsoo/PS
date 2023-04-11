import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        //(idx) 0 1 2 3 4 5 6 7 8 9
        //(pri) 1 2 3 4 2 3 2 1 4 3
        //(ans) 9 6 2 1 3 1 1 2 1 0
        
        Stack<Integer> s = new Stack<>();
        for(int i = 0 ; i<prices.length-1; i++){
            //pri=1 이면 확정
            if(prices[i]==1){
                answer[i] = prices.length-i-1;
            //값 증가/유지 면 stack에 넣고
            } else if(prices[i]<=prices[i+1]){
                s.push(i);
                // System.out.println(s);
            //값 줄어들면 answer[idx]에 값 지정
            } else {
                s.push(i);
                // System.out.println(s);
                while(prices[s.peek()]>prices[i+1]){
                    //i = 4, idx = 3, pri = 4
                    int idx = s.pop();
                    int pri = prices[idx];
                    // System.out.printf("idx : %d, pri : %d\n",idx,pri);
                    answer[idx] = i+1 - idx;
                    
                    if(s.isEmpty()) break;
                }
            }
        }
        
        
        // System.out.println("end loop");
        // System.out.println(s);
        
        
            while(!s.isEmpty()){
                int temp = s.pop();
                answer[temp] = prices.length-temp-1;
            }
        
        
        
        return answer;
    }
}