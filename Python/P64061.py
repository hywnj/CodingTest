# 크레인 인형뽑기 게임
# https://school.programmers.co.kr/learn/courses/30/lessons/64061

def solution(board, moves):
    _board_col = list(map(list, zip(*board)))
    for idx, entry in enumerate(_board_col):
        _board_col[idx] = [x for x in entry if x != 0]

    stack = []
    answer = 0
    for i in moves:
        if len(_board_col[i - 1]) > 0:
            stack.append(_board_col[i - 1].pop(0))

        _stack_len = len(stack)
        if _stack_len > 1 and stack[_stack_len - 2] == stack[_stack_len - 1]:
            # print(stack[_stack_len - 2], stack[_stack_len - 1])
            stack.pop()
            stack.pop()
            answer += 2

    return answer


print(solution([[0, 0, 0, 0, 0], [0, 0, 1, 0, 3], [0, 2, 5, 0, 1], [4, 2, 4, 4, 2], [3, 5, 1, 3, 1]],
               [1, 5, 3, 5, 1, 2, 1, 4]))
