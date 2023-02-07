drop table if exists sample;
create table sample (
    no	INT(11) unsigned auto_increment comment '번호' primary key,
    title	VARCHAR(256) not null comment '제목',
    contents	VARCHAR(1024) null comment '내용',
    createDt	DATETIME not null comment '생성일',
    createId	VARCHAR(10) not null comment '생성자 ID',
    updateDt	DATETIME not null comment '수정일',
    updateId	VARCHAR(10) not null comment '수정자 ID'
) comment 'Sample Table';
alter table sample auto_increment=1000000001;