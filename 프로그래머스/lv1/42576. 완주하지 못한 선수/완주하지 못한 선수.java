import java.util.*;

class Solution {
        public String solution(String[] participant, String[] completion) {
            final String[] answer = {""};

            Map<String, Integer> map = new HashMap<>();

            for(String s : participant){
                map.put(s, map.getOrDefault(s,0)+1);
            }

            for(String s : completion){
                map.replace(s, map.get(s)-1);
            }

            map.forEach((k,v)->{
                if(map.get(k)>0){
                    answer[0] = k;
                }
            });

            return answer[0];
        }
}