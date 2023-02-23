insert into sample (title, contents, create_id, create_dt, update_id, update_dt)
values ('test1', 'test1', 'test', CURRENT_TIMESTAMP(), 'test', CURRENT_TIMESTAMP());
insert into sample (title, contents, create_id, create_dt, update_id, update_dt)
values ('test2', 'test2', 'test', CURRENT_TIMESTAMP(), 'test', CURRENT_TIMESTAMP());

insert into member (email_id, member_name, member_password, role_id, create_id, create_date, update_id, update_date)
values ('admin@test.com', 'admin', '{bcrypt}$2a$10$eskty9VuayhRul3Quv6Pj.CNIDYT3YxB.QSVdlJMOCrYn11G9wJzG', 'SA', 'DBA', now(), 'DBA', now());

insert into role (id, description)
values ('SA', 'Super Admin');
