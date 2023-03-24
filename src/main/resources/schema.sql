drop table if exists sample;
create table sample
(
    no        INT(11) unsigned auto_increment comment '번호' primary key,
    title     VARCHAR(256)  not null comment '제목',
    contents  VARCHAR(1024) null comment '내용',
    create_dt DATETIME      not null comment '생성일',
    create_id VARCHAR(10)   not null comment '생성자 ID',
    update_dt DATETIME      not null comment '수정일',
    update_id VARCHAR(10)   not null comment '수정자 ID'
) comment 'Sample Table';
alter table sample
    auto_increment = 1000000001;

drop table if exists member;
create table member
(
    id              INT(10) unsigned auto_increment comment '10자리 사용자 고유 식별 번호 ID' primary key,
    email_id        VARCHAR(100) not null comment '사용자 로그인 Email ID',
    member_name     VARCHAR(100) null comment '사용자 이름',
    member_password VARCHAR(256) null comment '사용자 비밀번호',
    last_login_date DATETIME     null comment '마지막 접속일',
    role_id         VARCHAR(10)  null comment '유저 권한 Code ID',
    create_id       VARCHAR(10)  not null comment '생성자 ID',
    create_date     DATETIME     not null comment '생성일',
    update_id       VARCHAR(10)  not null comment '수정자 ID',
    update_date     DATETIME     not null comment '수정일'
) comment 'ExamOoops Member Table';
alter table member
    auto_increment = 1000000001;

drop table if exists role;
create table role
(
    id          VARCHAR(10) comment '유저 권한 Code ID' primary key,
    description VARCHAR(100) not null comment '권한 설명'
) comment 'ExamOoops Member Role Relation Table';

drop table if exists exam;
create table exam
(
    exam_no         INT(10) unsigned auto_increment comment '시험번호' primary key,
    pub_exam_no     VARCHAR(20) not null comment '발행시험번호',
    exam_open_yn    VARCHAR(1) not null default 'N' comment '공개유무',
    exam_reg_tp     VARCHAR(10) not null comment '등록유형',
    exam_rnd_yn     VARCHAR(1) not null default 'N' comment '랜덤유무',
    dump_file       VARCHAR(500) null comment '첨부파일',
    qst_cnt         INT(5) unsigned not null comment '문항 수',
    create_id       VARCHAR(10)  not null comment '생성자 ID',
    create_date     DATETIME     not null comment '생성일',
    update_id       VARCHAR(10)  not null comment '수정자 ID',
    update_date     DATETIME     not null comment '수정일'
) comment 'ExamOoops exam Table';
alter table exam
    auto_increment = 1000000001;

drop table if exists question;
create table question
(
    qst_no          INT(10) unsigned auto_increment comment '문제번호' primary key,
    exam_no         INT(10) not null comment '시험번호',
    qst_cont        varchar(2000) not null comment '문항',
    create_id       VARCHAR(10)  not null comment '생성자 ID',
    create_date     DATETIME     not null comment '생성일',
    update_id       VARCHAR(10)  not null comment '수정자 ID',
    update_date     DATETIME     not null comment '수정일'

) comment 'ExamOoops question Table';
alter table question
    auto_increment = 1000000001;

drop table if exists answer;
create table answer
(
    ans_no          INT(10) unsigned auto_increment comment '답안번호' primary key,
    exam_no         INT(10) not null comment '시험번호',
    qst_no          INT(10) not null comment '문제번호',
    ans_cont        varchar(2000) not null comment '답안내용',
    ans_hit_yn      varchar(1) not null default 'N' comment '정답여부',
    create_id       VARCHAR(10)  not null comment '생성자 ID',
    create_date     DATETIME     not null comment '생성일',
    update_id       VARCHAR(10)  not null comment '수정자 ID',
    update_date     DATETIME     not null comment '수정일'
) comment 'ExamOoops answer Table';
alter table answer
    auto_increment = 1000000001;


