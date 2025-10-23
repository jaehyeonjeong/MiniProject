-- 제약 이름 붙이고 권한부여해서 테이블 만들어 보기
-- 자동증가 시퀀스도 만들기
-- 데이터 10개 입력 하기
CREATE TABLE MEMBER (
                        id NUMBER,
                        userid varchar2(100),
                        username varchar2(100)
		CONSTRAINT member_username_nn NOT NULL,
                        useremail varchar2(100)
		CONSTRAINT member_useremail_nn_unq NOT NULL UNIQUE,
                        CONSTRAINT member_id_userid_pk PRIMARY KEY (id,userid)
);
CREATE SEQUENCE member_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 99999999999999
    MINVALUE 1
    nocycle;
INSERT INTO MEMBER (id,userid, username,useremail) VALUES (member_seq.nextval,'jjang051', '장성호', 'jjang051@hanmail.net');
INSERT INTO MEMBER (id,userid, username,useremail) VALUES (member_seq.nextval,'hong', '홍길동', 'hong@hanmail.net');
INSERT INTO MEMBER (id,userid, username,useremail) VALUES (member_seq.nextval,'kim', '김유신', 'kim@hanmail.net');
COMMIT;
SELECT * FROM MEMBER;

ALTER TABLE MEMBER
    ADD userpw varchar2(100) DEFAULT '1234'
CONSTRAINT member_userpw_nn NOT NULL;

SELECT * FROM MEMBER WHERE userid = 'jjang051' AND userpw = '1234';


CREATE TABLE board  (
                        id NUMBER CONSTRAINT board_id_pk PRIMARY KEY,
                        writer  varchar2(100) CONSTRAINT board_writer_nn NOT NULL,
                        title   varchar2(3000) CONSTRAINT board_title_nn NOT NULL,
                        content varchar2(3000) CONSTRAINT board_content_nn NOT NULL,
                        regdate DATE DEFAULT sysdate,
                        hit NUMBER,
                        password varchar2(100)
);

CREATE SEQUENCE board_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 99999999999999
    MINVALUE 1
    nocycle;
insert into board(id,title,content,writer,regdate,hit,password) values
    (board_seq.nextval,'테스트','테스트','테스트유저',sysdate,1,'1234');
select * FROM board;


CREATE TABLE board (
                       id        NUMBER PRIMARY KEY,
                       writer    VARCHAR2(100) NOT NULL,
                       title     VARCHAR2(3000) NOT NULL,
                       content   VARCHAR2(3000) NOT NULL,
                       regdate   DATE DEFAULT SYSDATE,
                       hit       NUMBER,
                       password  VARCHAR2(100)
);

ALTER TABLE board
    ADD secretValue CHAR(1) DEFAULT 'N'
        CONSTRAINT board_secretValue_ck CHECK (secretValue IN ('Y', 'N'));

ALTER TABLE board
    ADD secretPW VARCHAR2(100);



--비밀게시글 삽입 예시
INSERT INTO board (id, title, content, writer, regdate, hit, password, secretValue, secretPW)
VALUES (board_seq.nextval, '비밀글 제목', '비밀 내용', 'jjang051', SYSDATE, 0, '1234', 'Y', '5678');

--전체 게시글 비밀글 표시 포함
SELECT id, title, writer, secretValue FROM board;
--비밀게시글만 필터링
SELECT * FROM board WHERE secretValue = 'Y';

--비밀글의 비밀번호만 따로 출력(SELECT) 하는 쿼리
SELECT secretPW
FROM board
WHERE id = 5
  AND secretValue = 'Y';
--SQL 글쓰기 수정 입니다.
UPDATE board
SET title = '수정된 제목',
    content = '수정된 본문입니다.',
    writer = '수정된 작성자입니다'
WHERE id = 1523;



CREATE TABLE copy_board AS
SELECT * FROM board;

ALTER TABLE copy_board
    ADD userid VARCHAR2(20);

--업데이트부분은 DB 값 확인 후 값 입력 필요
UPDATE copy_board
SET USERID = 'song'
WHERE ID = 1523;


--member 테이블 userid에 unique 추가로 동일한 아이디의 회원가입 방지 추가
--member 테이블의 userid는 writer_id가 아니라 회원가입에서 사용된 real_id라고 테이블에 값 출력할 때 보이게 설정
--copy_board 테이블에서 userid가 writer_id라고 보이게 변경
ALTER TABLE member
    ADD CONSTRAINT member_userid_unq UNIQUE (userid);

ALTER TABLE copy_board
    ADD CONSTRAINT fk_copy_board_userid
        FOREIGN KEY(userid)
            REFERENCES member(userid);

SELECT
    b.id,
    b.userid AS writer_id,
    b.title,
    b.content,
    b.regdate,
    b.hit,
    m.userid AS real_id,
    m.username AS writer_name
FROM copy_board b
         JOIN member m ON b.userid = m.userid
where m.userid = 'song'
ORDER BY b.id DESC;