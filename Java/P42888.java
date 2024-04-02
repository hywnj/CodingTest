package Java;

import java.util.*;

/**
 * 오픈채팅방
 * https://school.programmers.co.kr/learn/courses/30/lessons/42888
 */
public class P42888 {
    public static void main(String[] args) {
        // ["Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."]
        System.out.println(Arrays.toString(solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"})));
    }
    public static String[] solution(String[] record) {
        List<User> answerList = new ArrayList<>(); // User 정보를 담은 채팅방 메시지 List
        Map<String, String> idMap = new HashMap<>(); // 사용자 닉네임 (id-name) Map

        String action, id;
        String[] arr = {};
        // 1. 채팅방 메시지 List(answerList), 사용자 닉네임 Map(idMap) 세팅
        for (String str : record) {
            arr = str.split(" ");
            action = arr[0];
            id = arr[1];

            if (action.equals("Change")) { // 닉네임 변경된 경우, 사용자 id에 매칭되는 닉네임 변경
                idMap.put(id, arr[2]);
            } else if (action.equals("Enter")){ // 들어온 경우, 채팅방 메시지에 표시되어야 하므로 answerList 추가 + 사용자 닉네임 추가
                idMap.put(id, arr[2]);
                answerList.add(new User(id, "님이 들어왔습니다."));
            } else if (action.equals("Leave")) { // 나간 경우, 채팅방 메시지에 표시되어야 하므로 answerList 추가
                answerList.add(new User(id, "님이 나갔습니다."));
            }
        }

        // 2. 정답 배열 반환
        String[] answer = new String[answerList.size()];
        for (int i=0; i<answer.length; i++) {
            User user = answerList.get(i);
            answer[i] = idMap.get(user.getId()) + user.getAction();
        }

        return answer;
    }

    static class User {
        String id;
        String action;

        public User(String id, String action) {
            this.id = id;
            this.action = action;
        }

        public String getId() {
            return id;
        }

        public String getAction() {
            return action;
        }
    }

}
