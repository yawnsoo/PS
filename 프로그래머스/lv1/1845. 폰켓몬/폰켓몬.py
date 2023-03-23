def solution(nums):

    l = len(nums)
    t = len(set(nums))

    if(l/2>t):
        return t
    else:
        return int(l/2)
