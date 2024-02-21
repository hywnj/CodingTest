# 햄버거 만들기 (★★★☆☆)
# https://school.programmers.co.kr/learn/courses/30/lessons/133502

def solution(ingredient):
    answer = 0
    stack = []

    for idx in range(len(ingredient)):
        stack.append(ingredient[idx])
        if [1, 2, 3, 1] == stack[-4:]:
            answer += 1
            stack.pop()
            stack.pop()
            stack.pop()
            stack.pop()

    return answer


print(solution([2, 1, 1, 2, 3, 1, 2, 3, 1]))  # 2
print(solution([1, 3, 2, 1, 2, 1, 3, 1, 2]))  # 0

# [Solution]
# 빵 – 야채 – 고기 - 빵
# 위 순서로 들어온게 있으면 먼저 1개를 플러스
# 들어온건 지우고 다시 배열 탐색해서 반복
# 1 빵 2 야채 3 고기
# 1 2 3 1

# 문자열로 변환해서 1231을 찾고 기존에 들어온 배열에서 해당 index 찾아서 삭제?

# 두번째 시도로는 그냥 배열에서 차례로 찾기
# 한번 찾은 후에도 또 있을수도 있으니까 다시 찾아야돼
# 모두 탐색했는데도 없으면 나와야되고
# n번 찾다가 배열 인덱스 오버 안되게 해야돼
# 재귀?

# 시간 초과 이슈때문에 스택으로!

# [Problems]
# 1. 첫번째 시도시, 시간 초과로 실패
# while True:
#     str_ingredient = ''.join(str(s) for s in ingredient)
#     idx_target = str_ingredient.find('1231')

#     if idx_target > -1:
#         answer += 1
#         del ingredient[idx_target:idx_target+4]
#     elif idx_target == -1:
#         break

# 2. 문자열이랑 리스트로 변환하는 작업을 줄임 -> 시간초과(5번 12번)
# answer = 0
# str_ingredient = ''.join(str(s) for s in ingredient)
# while True:
#     idx_target = str_ingredient.find('1231')
#     if idx_target == -1:
#         return answer
#     if idx_target > -1:
#         answer += 1
#         str_ingredient = str_ingredient[:idx_target] + str_ingredient[idx_target+4:]

# 3. 스택사용
