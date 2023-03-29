import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int cnt = 1;
        
        for(int i = 0; i<progresses.length; i++){    
            int progress = progresses[i];
            int speed = speeds[i];
            
            // 1. 완료되는 날짜 Stack에 추가
            int days = (int) Math.ceil((100 - progress)/(double)speed);
            
            if(stack.isEmpty()){
                stack.add(days);
            } else{
                int temp = stack.pop();
                if(temp>=days){ // 2-1. Stack에 있는 날짜 보다 작거나 같으면 cnt++ (같은 날 배포)
                    cnt++;
                    stack.add(temp);
                }else{ // 2-2. Stack에 있는 날짜 보다 크면 cnt=1 (다른 날 배포)
                    answer.add(cnt);
                    cnt = 1;
                    stack.add(days);
                }
            }
        }
        
        // 3. stack에 값 남아 있으므로 마지막 cnt 값 추가
        answer.add(cnt);
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}