import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        //1. 우선순위 목록 만들기(내림차순)
        TreeSet<Integer> tree = new TreeSet<>();
        //2. priorities를 queue로 만들기
        Queue<Integer> queue = new LinkedList<>();
        Queue<Boolean> check = new LinkedList<>();
        
        for(int i = 0 ; i<priorities.length; i++){
            tree.add(priorities[i]);
            queue.offer(priorities[i]);
            if(i==location){
                check.offer(true);
            } else{
                check.offer(false);
            }
        }
        
        
        while(!tree.isEmpty()){
        int max = tree.pollLast();
            while(queue.contains(max)){
                //3. 우선순위일 경우 출력(제거)
                if(max==queue.peek()){ //  3-1. 원하는 출력물 아닐 경우 answer+1
                    queue.poll();
                    answer++;
                    if(check.poll()){ //  3-2. 원하는 출력물일 경우 return
                        return answer;
                    }
                } else{ //4. 우선순위 아닐 경우 맨 뒤로
                    int t = queue.poll();
                    boolean c = check.poll();
                    queue.offer(t);
                    check.offer(c);
                }
            }
        }
        
        
        return answer;
    }
}