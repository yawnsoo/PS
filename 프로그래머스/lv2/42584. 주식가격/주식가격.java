import java.util.*;

class Solution {
        public int[] solution(int[] prices) {

            int[] answer = new int[prices.length];
            Stack<Integer> s = new Stack<>();

            for(int i = 0 ; i<prices.length-1; i++){
                //pri=1 이면 확정
                if(prices[i]==1){
                    answer[i] = prices.length-i-1;

                //stack에 idx값 넣고 비교
                } else {
                    s.push(i);
                    while(prices[s.peek()]>prices[i+1]){
                        int idx = s.pop();
                        answer[idx] = i+1 - idx;

                        if(s.isEmpty()) break;
                    }
                }
            }

            //stack에 값이 마지막까지 남아 있는 경우
            while(!s.isEmpty()){
                int temp = s.pop();
                answer[temp] = prices.length-temp-1;
            }

            return answer;
        }
    }