# 실패율
# https://school.programmers.co.kr/learn/courses/30/lessons/42889

import numpy as np
def solution(N, stages):

    # 스테이지를 도전한 유저의 수
    chanllenger = len(stages)
    answer = []

    for i in range(1,N+1):
        # 분모가 0이되면 런타임 에러가 남
        if chanllenger == 0:
            answer.append(0)
        else:
            # 현재 스테이지에 멈춰있는 유저의 수
            _now = stages.count(i)
            answer.append(_now/chanllenger)

            # 다음 스테이지에 도전한 유저의 수
            chanllenger -= _now

    # answer에서 정렬을 해볼까?
    answer = list([x for x in enumerate(answer)])
    print(answer)
    # print(sorted(answer, key=lambda x: (-x[1], x[0])))
    answer.sort(key=lambda x: (-x[1], x[0]))


    # result = np.argsort(np.array(answer))[::-1]
    # return [x+1 for x in np.argsort(np.array(answer))[::-1]]

    # print([x[0]+1 for x in answer])
    return [x[0]+1 for x in answer]

solution(5, [2, 1, 2, 6, 2, 4, 3, 3])
solution(4, [4,4,4,4,4])