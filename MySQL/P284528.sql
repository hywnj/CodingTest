-- 연간 평가점수에 해당하는 평가 등급 및 성과금 조회하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/284528
SELECT A.EMP_NO
     , A.EMP_NAME
     , ( CASE
             WHEN A.SCORE_AVG >= 96 THEN 'S'
             WHEN A.SCORE_AVG >= 90 THEN 'A'
             WHEN A.SCORE_AVG >= 80 THEN 'B'
             ELSE 'C'
    END ) AS GRADE
     , ( CASE
             WHEN A.SCORE_AVG >= 96 THEN A.SAL * 0.2
             WHEN A.SCORE_AVG >= 90 THEN A.SAL * 0.15
             WHEN A.SCORE_AVG >= 80 THEN A.SAL * 0.1
             ELSE 0
    END ) AS BONUS
FROM (
         SELECT HE.EMP_NO AS EMP_NO
              , HE.EMP_NAME AS EMP_NAME
              , AVG(HG.SCORE) AS SCORE_AVG
              , HE.SAL AS SAL
         FROM HR_EMPLOYEES AS HE, HR_GRADE AS HG
         WHERE HE.EMP_NO = HG.EMP_NO
         GROUP BY EMP_NO
     ) AS A
ORDER BY A.EMP_NO ASC;