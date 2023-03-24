import java.util.*;

/*
hash map
1. (k,v) = (전화번호, 개수(중복이 없으므로 무조건 1))
2. 접두어가 있는지 확인
    - 길이 i인 전화번호의 0부터 i-1번째 index까지 잘라서 hashmap에 있는지 확인.
        (i-1까지인 이유 : 자기 자신은 hashmap에 무조건 있기 때문)
3. 접두어인 경우 종료 : return false;
*/

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Map<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i<phone_book.length;i++){
            map.put(phone_book[i],1);
        }
        
        for(int i = 0; i<phone_book.length;i++){
            for(int j = 1; j<phone_book[i].length();j++){
                if(map.containsKey(phone_book[i].substring(0,j))){
                    return false;
                }
            }
        }
        
        
        return answer;
    }
}

/*

> dfs 이용한 풀이

import java.util.*;

class Solution {
    boolean answer = true;
    String[] picked = new String[2];
    
    public boolean isStartWith(String[] p){
        String[] a = p[0].split("");
        String[] b = p[1].split("");
        
        int min = Math.min(a.length,b.length);
        
        for(int i = 0; i<min;i++){
            if(!a[i].equals(b[i])){
                return false;
            }
        }
        
        return true;
    }
    
    public void combination(String[] phone_book, int start, int depth, String[] picked){
        if(depth==2){
            if(isStartWith(picked)){
                answer = false;
            }
            return;
        }
        
        
        for(int i = start; i<phone_book.length; i++){
            if(!answer) return;
            picked[depth] = phone_book[i];
            combination(phone_book, i+start+1, depth+1, picked);
        }
    }
    
    
    public boolean solution(String[] phone_book) {

        combination(phone_book, 0, 0, picked);
        
        return answer;
    }
}

*/