import java.util.*;

class Solution {
    String[] answer;
    boolean[] used;
    
    public class Ticket implements Comparable<Ticket>{
        String from;
        String to;
        int idx;
        
        public Ticket(String from, String to, int idx){
            this.from = from;
            this.to = to;
            this.idx = idx;
        }
        
        public String getFrom(){
            return from;
        }
        
        public String getTo(){
            return to;
        }
        
        public int getIdx(){
            return idx;
        }
        
        
        @Override
        public int compareTo(Ticket t){
            return this.to.compareTo(t.to);
            // if(this.to.compareTo(t.to)>0){
            //     return 1;
            // } else if(this.to.compareTo(t.to)<0){
            //     return -1;
            // } else return 0;
        }
        
    }
    
    public void dfs(String start, String[][] tickets, int depth){
        
        List<Ticket> canGo = new ArrayList<>();
        
        for(int i = 0; i<tickets.length; i++){
            if(tickets[i][0].equals(start)&&!used[i]){
                canGo.add(new Ticket(tickets[i][0], tickets[i][1], i));
            }
        }
        
        Collections.sort(canGo);
        
        for(int i = 0; i<canGo.size();i++){
            Ticket temp = canGo.get(i);
        
            // used[temp.getIdx()] = true;
            used[temp.idx] = true;
            // answer[depth] = temp.getFrom();
            answer[depth] = temp.from;
        
            if(depth==tickets.length-1){
                // answer[depth+1] = temp.getTo();
                answer[depth+1] = temp.to;
                return;
            }
        
            // dfs(temp.getTo(), tickets, depth+1);
            dfs(temp.to, tickets, depth+1);
            
            if(answer[answer.length-1]==null){
                // used[temp.getIdx()] = false;
                used[temp.idx] = false;
            } else return;
        }
        
        
    }
    
    public String[] solution(String[][] tickets) {
        answer = new String[tickets.length+1];
        used = new boolean[tickets.length];
        
        dfs("ICN", tickets, 0);
        
        
        return answer;
    }
}