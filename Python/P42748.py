# K번째 수
# https://school.programmers.co.kr/learn/courses/30/lessons/42748

# commands에 있는 각 리스트가 [i,j,k]로,
# 1) array의 i번째부터 j번째까지 자른 후,
#   - commands를 기준으로 잡고 반복해서 가져오기
#   - commands의 각 리스트를 _command로 받아와서
#   - _command[0], _command[1]로 잘라서 li에 할당
# 2) 정렬한 리스트의 k번째 숫자를 각각 반환
#   - li를 sort한 list의 k번째 인덱스를 answer에 할당

# ? 리스트 접근을 다르게 할 수 있는 방법 ?

def solution(array, commands):
    answer = []

    for _command in commands:
        li = array[_command[0]-1:_command[1]]
        answer.append(sorted(li)[_command[2]-1])

    return answer


print(solution([1, 5, 2, 6, 3, 7, 4], [[2, 5, 3], [4, 4, 1], [1, 7, 3]]))