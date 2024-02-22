package Java;

import java.util.*;

/**
 * 더 맵게
 * https://school.programmers.co.kr/learn/courses/30/lessons/42626
 */
public class P42626 {
    final static int ROOT_INDEX = 1;
    static int leafIdx;
    static int[] heap;

    public static void main(String[] args) {
        System.out.println(solution(new int[]{10, 2, 3, 9, 1, 12}, 7)); // 2
    }

    /**
     * Heap (Min-Heap) 으로 구현
     *  - 왼쪽 자식노드 = 부모노드 * 2
     *  - 오른쪽 자식노드 = 부모노드 * 2 + 1
     *  - 부모노드 = 자식노드 / 2
     */
    public static int solution(int[] scoville, int K) {
        // 최초 scoville 오름차순 정렬
        List<Integer> list = new LinkedList<>();
        for (int val: scoville) {
            list.add(val);
        }
        Collections.sort(list);

        // Heap 초기화
        heap = new int[scoville.length + 1]; // 개발의 용이성을 위해 index는 1부터
        for (int i=1; i<heap.length; i++) {
            heap[i] = list.get(i-1);
        }

        // 반복 연산
        boolean finish = false;
        int count = 0; // 섞은 횟수
        leafIdx = heap.length - 1;
        while (!finish) {
            // 연산할 값 가져오고 해당 값은 heap에서 삭제
            int first = heap[ROOT_INDEX];
            remove();
            // 루트 노드(first)의 자식 노드끼리 비교해서 더 작은 값이 second
            int second = heap[ROOT_INDEX];
            remove();

            // 더이상 Heap에 비교할 값이 없음 = 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우
            if (first == 0 || second == 0) {
                count = -1;
                break;

            } else {
                // 섞은 음식의 스코빌 지수를 Heap에 삽입
                int mix = first + (second * 2);
                add(mix);
                count++;

                // 루트 노드 값이 K와 같거나 크면 종료
                if (heap[ROOT_INDEX] >= K) {
                    break;
                }
            }
        }

        return count;
    }

    /**
     * Heap 삽입 연산
     *  1) 마지막 위치에 삽입
     *  2) 부모 노드와 비교해서 더 작은값이면 교환 / 같거나 크면 그대로 (상향 선별)
     */
    public static void add(int value) {
        // leafIdx 갱신, 마지막 위치에 삽입
        leafIdx++;
        int index = leafIdx;
        heap[index] = value;

        // 최대 루트 노드까지 비교: index = 1까지
        while (index > ROOT_INDEX) {
            int parentIdx = index / 2; // 부모노드 인덱스

            if (parentIdx > 0) {
                // 새로 삽입된 노드가 부모 노드보다 값이 작으면 교환
                if (heap[index] < heap[parentIdx]) {
                    int temp = heap[parentIdx];
                    heap[parentIdx] = heap[index];
                    heap[index] = temp;

                    // 비교할 인덱스가 부모 인덱스로 변경되었으므로 index 갱신
                    index = parentIdx;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /**
     * Heap 삭제 연산
     * 1) 루트 노드 제거, 마지막 노드랑 루트 노드 변경
     * 2) 자식 노드랑 비교해서, 본인이 자식 노드보다 값이 크면 교환 (하향 선별)
     *      - 자식 노드 중 더 작은 값이랑 본인 값이랑 비교
     *          - 왼쪽 자식노드 = 부모노드 * 2
     *          - 오른쪽 자식노드 = 부모노드 * 2 + 1
     */
    public static void remove () {
        int leaf = heap[leafIdx]; // 마지막 노드 값
        heap[ROOT_INDEX] = leaf; // 루트 노드에 마지막 노드 값 가져오기

        // 마지막 노드 없애기 & 마지막 노드 인덱스 갱신
        heap[leafIdx] = 0;
        leafIdx--;

        // 현재 비교 해야할 노드 인덱스
        int index = ROOT_INDEX;

        // 최대 마지막 노드까지 반복수행: leafIdx = index까지
        while (index < leafIdx) {
            int leftChildIdx = index * 2;
            int rightChildIdx = (index * 2) + 1;

            // leaf 노드 범위 체크
            if (leftChildIdx > leafIdx || rightChildIdx > leafIdx) break;

            // 자식 노드 중 더 작은 값 찾기
            int leftChild = heap[leftChildIdx];
            int rightChild = heap[rightChildIdx];
            int targetChildIdx = (leftChild < rightChild ? leftChildIdx : rightChildIdx); // 비교할 자식 노드 인덱스

            if (leaf > heap[targetChildIdx]) { // 현재 비교할 값이 본인의 자식 노드보다 크다면 값 교환
                heap[index] = heap[targetChildIdx];
                heap[targetChildIdx] = leaf;
            } else { // 자식 노드랑 같거나 작다면 끝
                return;
            }
            // 현재 비교할 값의 인덱스가 자식 노드 인덱스로 변경되었으므로 index 갱신
            index = targetChildIdx;
        }
    }
}
