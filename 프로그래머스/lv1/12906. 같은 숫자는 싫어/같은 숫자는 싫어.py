def solution(arr):
    answer = []


    for i in arr:

        answer.append(i)
        j = len(answer)-1

        if j>0 :
            if (answer[j] == answer[j - 1]):
                answer.pop()
    
    
    return answer