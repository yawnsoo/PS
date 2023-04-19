import java.util.*;

class Solution {
    int answer = -1;
    
    public void bfs(int N, int number){
        
        Queue<int[]> q = new LinkedList<>();
        int cnt = 0;
        q.add(new int[]{0,N,cnt,1});
        
        while(cnt<9){
            int[] temp = q.poll();
            
            int result = temp[0];
            int num = temp[1];
            int cntT = temp[2];
            cnt = cntT;
            int param = temp[3];
            
            if(result==number){
                answer = cnt;
                return;
            }
            
            if(num==N){
                q.add(new int[]{result+num,num,cntT+param,1});
                q.add(new int[]{result-num,num,cntT+param,1});
                q.add(new int[]{result*num,num,cntT+param,1});
                q.add(new int[]{result/num,num,cntT+param,1});
                q.add(new int[]{result,(num*10)+N,cntT,param+1});
            } else{
                q.add(new int[]{result+num,N,cntT+param,1});
                q.add(new int[]{result-num,N,cntT+param,1});
                q.add(new int[]{result*num,N,cntT+param,1});
                q.add(new int[]{result/num,N,cntT+param,1});
                q.add(new int[]{result,(num*10)+N,cntT,param+1});
            }
            
        }   
    }
    
    
    public int solution(int N, int number) {
        
        bfs(N,number);
        
        return answer;
    }
}