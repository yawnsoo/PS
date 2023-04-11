import heapq as hq

def solution(scoville, K):
    s = scoville
    k = K
    count = 0
    bp = len(scoville)

    #리스트 힙으로 변환
    hq.heapify(s)

    while s[0] < k :
        count += 1
        s0 = hq.heappop(s)
        s1 = hq.heappop(s) #O(log(n))
        new_s = s0 + s1*2
        
        hq.heappush(s,new_s) #O(n)
        
        bp -= 1
        if bp < 2 and s[0] < k:
            return -1

    return count