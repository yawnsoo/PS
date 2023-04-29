import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        int[] check = new int[n];
        Arrays.fill(check,1);
        
        for(int r : reserve){
            check[r-1] = 2;
        }
        
        for(int l : lost){
            if(check[l-1]==2){
                check[l-1] = 1;
            }
            else if(check[l-1]==1){
                check[l-1] = 0;
            }
            
        }
        
        for(int i = 0 ; i<n; i++){
            if(check[i]==2){
                if(i==0 && check[i+1]==0){
                    check[i]=1;
                    check[i+1]=1;
                } else if(i == n-1 && check[i-1]==0){
                    check[i] = 1;
                    check[i-1] = 1;
                } else if(i>0 && i<n-1){
                    if(check[i-1] == 0){
                        check[i]=1;
                        check[i-1] =1;
                    } else if(check[i+1]==0){
                        check[i]=1;
                        check[i+1]=1;
                    }
                }        
            }
        }
        
        for(int c : check){
            if(c==1||c==2){
                answer++;
            }
        }
        
        
        return answer;
    }
}