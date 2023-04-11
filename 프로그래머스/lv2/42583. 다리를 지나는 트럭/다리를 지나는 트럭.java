import java.util.*;

class Solution {
    int answer = 0;
    int cnt = 0;
    int inBridge = 0;

    public void addTruckToBridge(Queue<Integer> trucks, Queue<Integer> bridge, Queue<Integer> timeIn){
        int truckIn = trucks.poll();
        //  - 다리q에 무게 추가
        bridge.offer(truckIn);
        //  - 총 무게에 추가
        inBridge += truckIn;
        //  - 시각q에 들어간 시간 추가
        timeIn.offer(answer);
    }

    public void passedTruckFromBridge(Queue<Integer> bridge, Queue<Integer> timeIn){
        //  - 시간q.pop()
        timeIn.poll();
        //  - 총 무게에 빼기
        inBridge -= bridge.poll();
        //  - cnt++
        cnt++;
    }


    public int solution(int bridge_length, int weight, int[] truck_weights) {


        //queue 3개로 구현 : 다리q, 시각q, 트럭무게q
        Queue<Integer> bridge = new LinkedList<>();
        Queue<Integer> timeIn = new LinkedList<>();
        Queue<Integer> trucks = new LinkedList<>();

        for(int i : truck_weights){
            trucks.offer(i);
        }

        //첫 트럭은 무조건 건넘, 초기화
        answer++;
        addTruckToBridge(trucks, bridge, timeIn);


        //3. cnt == 트럭의 개수 이면 종료
        //  - 빼고
        //  - 더하고
        while(cnt < truck_weights.length){ // (!bridge.isEmpty() 조건도 가능)
            answer++;

            //1. 시각q.pop()+다리 길이 의 시간에 도달 할 경우
            if(timeIn.peek()+bridge_length == answer){
                passedTruckFromBridge(bridge, timeIn);
            }

            //2. 다리(queue)에 올라가 있는 무게 총값 + 들어갈 트럭 <= 다리가 견딜 수 있는 무게
            if(!trucks.isEmpty()){ //트럭이 남아 있을 경우에
                if(inBridge+trucks.peek()<=weight){
                    addTruckToBridge(trucks, bridge, timeIn);
                }
            }
        }

        //4. 시각 return;
        return answer;
    }
}