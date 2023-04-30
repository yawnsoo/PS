class Solution {
    int rightLeft = 987654321;
    
    public void dfs(String name, int total, int start, int distance, boolean[] v){
        
        if(distance==name.length()){
            rightLeft = Math.min(total-distance,rightLeft);
            return;
        }
        
        v[start] = false;
        int right = 0;
        int left = 0;
        int idx = start;
        int toRight = 0;
        int toLeft = 0;
        
        while(left<name.length()){
            if(v[(name.length()+idx-left)%name.length()]){
                toLeft = (name.length()+idx-left)%name.length();
                break;
            }
            left++;
        }

        while(right<name.length()){
            if(v[(idx+right)%name.length()]){
                toRight = (idx+right)%name.length();
                break;
            }
            right++;
        }
        
        dfs(name, total+left, toLeft, left, v);
        dfs(name, total+right, toRight, right,v);

            v[start] = true;
        
    }
    
    // A = 65
    // Z = 90
    public int solution(String name) {
        int upDown = 0;
        String base = "";
        boolean[] v = new boolean[name.length()];
        
        for(int i = 0; i<name.length(); i++){
            base += "A";
        }
        

        //위,아래
        for(int i = 0; i<base.length(); i++){
            if(base.charAt(i)!=name.charAt(i)){
                upDown += Math.min((name.charAt(i)-'A'),('Z'-name.charAt(i))+1);
                v[i] = true;
            }
        }
        
        
        //왼쪽 오른쪽
        dfs(name,0,0,0,v);
        
        return upDown+rightLeft;
    }
}