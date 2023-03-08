insert into sample (title, contents, create_id, create_dt, update_id, update_dt)
values ('test1', 'test1', 'test', CURRENT_TIMESTAMP(), 'test', CURRENT_TIMESTAMP());
insert into sample (title, contents, create_id, create_dt, update_id, update_dt)
values ('test2', 'test2', 'test', CURRENT_TIMESTAMP(), 'test', CURRENT_TIMESTAMP());

insert into member (email_id, member_name, member_password, role_id, create_id, create_date, update_id, update_date)
values ('admin@test.com', 'admin', '{bcrypt}$2a$10$eskty9VuayhRul3Quv6Pj.CNIDYT3YxB.QSVdlJMOCrYn11G9wJzG', 'SA', 'DBA', now(), 'DBA', now());

insert into role (id, description)
values ('SA', 'Super Admin');

insert into comn_code(comn_grp_cd, comn_cd, comn_grp_nm, comn_nm, comn_ord, comn_desc, del_yn, create_date, create_id, update_date, update_id)
values
    ('G001', 'C001', '시험결과', 'PASS', 1, '설명1', 'N', now(), 'DBA', now(), 'DBA')
    , ('G001', 'C002', '시험결과', 'FAIL', 2, '설명2', 'Y', now(), 'DBA', now(), 'DBA')
    , ('G002', 'D001', '시험결과', 'FAIL', 3, '설명3', 'N', now(), 'DBA', now(), 'DBA');