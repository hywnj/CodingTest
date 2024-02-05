# 폰켓몬
# https://school.programmers.co.kr/learn/courses/30/lessons/1845
def solution(nums):
    answer = 0

    # 가져갈 수 있는 폰켓몬 수
    mine = len(nums) / 2

    # 중복 제거
    set_nums = len(set(nums))

    # 가져갈 수 있는 폰켓몬 수가 중복 제거한 Nums 보다 크다면 set_nums 반환
    if mine > set_nums: return set_nums
    # 가져갈 수 있는 폰켓몬 수가 중복 제거한 Nums 보다 작거나 같다면 mine 반환
    return int(mine)


print(solution([3, 1, 2, 3]))
