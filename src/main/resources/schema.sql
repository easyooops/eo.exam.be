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

drop table if exists comn_code;
create table comn_code
(
    comn_grp_cd     VARCHAR(10) not null comment '그룹 코드',
    comn_cd         VARCHAR(10) not null comment '코드',
    comn_grp_nm     VARCHAR(50) not null comment '그룹 코드명',
    comn_nm         VARCHAR(50) not null comment '코드명',
    comn_ord        VARCHAR(3) not null comment '정렬 순서',
    comn_desc       VARCHAR(256) not null comment '설명',
    del_yn          VARCHAR(1) not null default 'N' comment '삭제 유무',
    create_date     DATETIME not null comment '생성일',
    create_id       VARCHAR(10) not null comment '생성자 ID',
    update_date     DATETIME not null comment '수정일',
    update_id       VARCHAR(10) not null comment '수정자 ID'
) comment 'ExamOoops ExamOoops Common Code Table';