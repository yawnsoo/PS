import java.util.*;

class Solution {
    int answer = -1;
    boolean[][] visited;
    
    public void bfs(int[][] maps){
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,1});
        visited[0][0] = true;
        
        int[] dy = new int[]{0,1,0,-1};
        int[] dx = new int[]{1,0,-1,0};
        
        
        while(!q.isEmpty()){
            int[] p = q.poll();
            
            int y = p[0];
            int x = p[1];
            int cnt = p[2];
            
            if(y==maps.length-1&&x==maps[0].length-1){
                answer = cnt;
                return;
            }
            
            for(int i = 0; i<4; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];
                
                if(ny<0||nx<0||ny>=maps.length||nx>=maps[0].length) continue;
                
                if(!visited[ny][nx]&&maps[ny][nx]==1){
                    q.add(new int[]{ny,nx,cnt+1});
                    visited[ny][nx]=true;
                }
                
            }
            
        }
        
    }
    
    
    public int solution(int[][] maps) {
        visited = new boolean[maps.length][maps[0].length];
        
        bfs(maps);
        
        
        return answer;
    }
}