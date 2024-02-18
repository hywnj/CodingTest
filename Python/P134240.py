# 푸드 파이터 대회
# https://school.programmers.co.kr/learn/courses/30/lessons/134240

def solution(food):
    answer = ''
    for idx, val in enumerate(food):
        if idx == 0: continue

        for _ in range(val//2):
            answer += str(idx)

    reverse_answer = answer[::-1]
    return answer + '0' + reverse_answer


print(solution([1, 3, 4, 6]))