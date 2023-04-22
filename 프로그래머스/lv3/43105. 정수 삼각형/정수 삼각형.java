import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        for(int i = 1; i<triangle.length;i++){
            
            if(i==1){
                triangle[1][0] = triangle[0][0] + triangle[1][0];
                triangle[1][1] = triangle[0][0] + triangle[1][1];
            } else{
                for(int j = 0; j<triangle[i].length;j++){
                    if(j==0){
                        triangle[i][j] = triangle[i-1][j] + triangle[i][j];
                    } else if(j==triangle[i].length-1){
                        triangle[i][j] = triangle[i-1][j-1] + triangle[i][j];
                    } else{
                        triangle[i][j] = Math.max(triangle[i-1][j-1] + triangle[i][j],triangle[i-1][j] + triangle[i][j]);
                    }
                }
            }
            
        }
        
        
        return Arrays.stream(triangle[triangle.length-1]).max().getAsInt();
    }
}