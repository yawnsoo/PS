class Solution {
    
    //1. for문으로 node 방문
    //2. node 방문 안했으면 dfs진입, answer++
    //3. dfs로 node와 이어진 부분 모두 true
    
    int answer = 0;
    boolean[] visited;
    int nodes;
    
    public void dfs(int[][] computers, int node, int start){
        
        if(start==nodes) return;
        
        for(int i = start; i < nodes; i++){
            if(!visited[i]&&computers[node][i]==1){
                visited[i] = true;
                dfs(computers, i, 0);
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        
        nodes = n;
        visited = new boolean[n];
        
        for(int i = 0; i < n; i++){
            //i 번째 node와 이어진 모든 node 방문
            if(!visited[i]){
                dfs(computers, i, 0);
                answer++;
            }
            
        }
        
        return answer;
    }
}