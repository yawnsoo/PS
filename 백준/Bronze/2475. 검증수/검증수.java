import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int total = 0;
        
        for(int i = 0; i < 5; i++){
            int a = sc.nextInt();
            total += a*a;
        }
        
        System.out.println(total%10);
    }
}