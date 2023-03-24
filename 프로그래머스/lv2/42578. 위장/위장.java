import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        Map<String, Integer> map = new HashMap<>();
        
        
        for(String[] s : clothes){
            map.put(s[1],map.getOrDefault(s[1],0)+1);
        }
        
        for(String s : map.keySet()){
            //각 옷의 종류의 가지수 + 하나도 선택 안할 경우의 수 == (nC1+nC0)
            answer *= map.get(s)+1;
        }
        
        //아무것도 안입었을 경우 -1
        return answer-1;
    }
}

/*
import java.util.*;

class Solution {
    int answer = 0;
    
    public void combination(List<Integer> list, boolean[] check, int start, int pick, int total){
        if(pick==0){
            answer+=total;
        }
        
        for(int i = start; i<list.size(); i++){
            combination(list,check,i+1,pick-1,total*list.get(i));
        }
    }
    
    public int solution(String[][] clothes) {
        
        //1. hashmap<String:Integer> -> <의상 종류, 개수>
        Map<String, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        
        
        for(String[] s : clothes){
            map.put(s[1],map.getOrDefault(s[1],0)+1);
        }
        
        for(String s : map.keySet()){
            list.add(map.get(s));
        }
        
        
        //2. v 배열에서 조합 구하기
        //3. 1개 ~ n개 뽑는 경우의 수 개산
        boolean[] check = new boolean[list.size()];
        for(int i = 1; i<list.size()+1; i++){
            combination(list, check, 0, i, 1);
        }
        // ---------는 조합으로 풀면 케이스 1번 시간초과
        
        
        
        return answer;
    }
}
*/