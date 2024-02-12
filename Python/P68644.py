# 두 개 뽑아서 더하기
# https://school.programmers.co.kr/learn/courses/30/lessons/68644

import copy
def solution(numbers):
    # 기준이 되는 numbers와 더할 next_numbers를 깊은 복사로 초기화
    next_numbers = copy.deepcopy(numbers)

    # 결과에는 중복제거
    result = set()

    # pre_num : 더하는 기준이 되는 number
    for pre_num in numbers:
        # pre_num을 제외한 값을 모두 더해야하므로 next_numbers의 0번째 인덱스 값을 제거
        # - numbers에서 제거시, for문을 range + numbers[idx]로 접근하여 진행해야함 > 파이썬답지 않다? :)
        del next_numbers[0]

        for next_num in next_numbers:
            result.add(pre_num + next_num)

    # 정렬한 결과를 리턴
    return sorted(list(result))