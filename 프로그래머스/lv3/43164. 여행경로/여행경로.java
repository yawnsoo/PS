import java.util.*;

class Solution {
    //1. dfs
    //2. 모든 표를 사용한 경우 List<String>에 추가
    //3. List 정렬하고 0번째꺼 가져와서 .split(" ")
    List<String> answer = new ArrayList<>();
    
    public void dfs(String[][] tickets, boolean[] visited, int pick, String start, String route){
        if(pick==tickets.length){
            answer.add(route);
            return;
        }
        
        for(int i = 0; i<tickets.length; i++){
            if(!visited[i]&&tickets[i][0].equals(start)){
                visited[i]=true;
                if(pick+1==tickets.length){
                    dfs(tickets, visited, pick+1, tickets[i][1], route+start+" "+tickets[i][1]);
                } else{
                    dfs(tickets, visited, pick+1, tickets[i][1], route+start+" ");
                }
                visited[i]=false;
            }
        }
    }
    
    public String[] solution(String[][] tickets) {
        
        boolean[] visited = new boolean[tickets.length];
        
        dfs(tickets,visited,0,"ICN","");
        
        Collections.sort(answer);
        
        return answer.get(0).split(" ");
    }
}