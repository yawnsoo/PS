class Solution {
    public int solution(int[] money) {
        int len = money.length;
        
        int[] includeZ = new int[len];
        int[] excludeZ = new int[len];
        
        // 첫번째 집을 포함하는 경우, 마지막 집을 0으로 간주하고 -> 마지막 집 부터 시작하여 점화식 적용 (원 형태이기 때문에 회전해도 무방)
        // 첫번째 집을 포함하지 않는 경우, 첫번째 집을 0으로 간주하고 시작하여 점화식 적용
        for(int i = 1; i<len; i++){
            includeZ[i] = money[i-1];
            excludeZ[i] = money[i];
        }
    
        for(int i = 2; i<len;i++){
            includeZ[i] = Math.max(includeZ[i-1],includeZ[i]+includeZ[i-2]);
            excludeZ[i] = Math.max(excludeZ[i-1],excludeZ[i]+excludeZ[i-2]);
        }
        
        
        return Math.max(includeZ[len-1],excludeZ[len-1]);
    }
}