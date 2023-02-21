insert into sample (title, contents, create_id, create_dt, update_id, update_dt)
values ('test1', 'test1', 'test', CURRENT_TIMESTAMP(), 'test', CURRENT_TIMESTAMP());
insert into sample (title, contents, create_id, create_dt, update_id, update_dt)
values ('test2', 'test2', 'test', CURRENT_TIMESTAMP(), 'test', CURRENT_TIMESTAMP());

insert into member (email_id, member_name, member_password, create_id, create_date, update_id, update_date)
values ('admin@test.com', 'admin', '1234', 'DBA', now(), 'DBA', now());

insert into member_role (role)
values ('SA');
