import java.util.*;

class Solution {
    int answer = 0;
    //1. bfs
    //3. 한문자만 다르면 q에 등록, cnt+1, 다른 문자 visited:true
    //4. 같으면 cnt 출력
    public class Word3cnt{
        private String word;
        private int cnt;
        
        public String getWord(){
            return this.word;  
        } 
        public int getCnt(){
            return this.cnt;
        }
        
        public Word3cnt(String word, int cnt){
            this.word = word;
            this.cnt = cnt;
        }
        
    }
    
    public void bfs(String begin, String target, String[] words, boolean[] visited){
        
        Queue<Word3cnt> q = new LinkedList<>();
        int wordl = words[0].length();
        q.offer(new Word3cnt(begin, 0));
        
        while(!q.isEmpty()){
            Word3cnt temp = q.poll();
            
            for(int i = 0; i<words.length; i++){
                if(!visited[i]){
                    int diff = 0;
                    String word = words[i];
                    
                    for(int j = 0; j<wordl; j++){
                        if(temp.getWord().charAt(j)!=word.charAt(j)){
                            diff++;
                        }
                        if(diff>1){
                            break;
                        }
                    }
                    
                    if(diff==1){
                        visited[i] = true;
                        if(words[i].equals(target)){
                            answer = temp.getCnt()+1;
                            return;
                        }
                        
                        q.offer(new Word3cnt(words[i], temp.getCnt()+1));
                    }   
                }
            }
        }
    }
    
    
    public int solution(String begin, String target, String[] words) {
        boolean isContain = false;
        boolean[] visited = new boolean[words.length];
        
        //target이 words[]안에 포함되어 있는지 확인
        for(String s : words){
            if(s.equals(target)){
                isContain = true;
            }
        }
        if(!isContain) return 0;
        
        
        bfs(begin,target,words,visited);
        
        
        return answer;
    }
}