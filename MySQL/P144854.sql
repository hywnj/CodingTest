-- [String, Date] 조건에 맞는 도서와 저자 리스트 출력하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/144854

SELECT B.BOOK_ID, AUTHOR_NAME, DATE_FORMAT(PUBLISHED_DATE, '%Y-%m-%d') AS PUBLISHED_DATE
FROM BOOK AS B
         JOIN AUTHOR AS A ON A.AUTHOR_ID = B.AUTHOR_ID
WHERE B.CATEGORY = '경제'
ORDER BY PUBLISHED_DATE ASC;