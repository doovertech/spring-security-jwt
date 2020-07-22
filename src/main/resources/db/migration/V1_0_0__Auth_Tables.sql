create sequence users_user_id_seq
    start with 100
    increment by 1
    no minvalue
    no maxvalue
    cache 1;

create sequence roles_role_id_seq
    start with 100
    increment by 1
    no minvalue
    no maxvalue
    cache 1;

create sequence user_roles_user_role_id_seq
    start with 100
    increment by 1
    no minvalue
    no maxvalue
    cache 1;


create table users (
                       user_id integer default nextval('users_user_id_seq'::regclass) not null,
                       email character varying(100) not null,
                       username character varying(100) not null,
                       first_name character varying(100) not null,
                       last_name character varying(100) not null,
                       password character varying(100),
                       reg_date date default current_date,
                       upd_date date default current_date,
                       state character(20) default 'ACTIVE'::bpchar not null
);

create table roles (
                       role_id integer default nextval('roles_role_id_seq'::regclass) not null,
                       role varchar,
                       description varchar,
                       created_at date default current_date
);

create table user_roles (
                            user_role_id integer default nextval('user_roles_user_role_id_seq'::regclass) not null,
                            user_id integer not null,
                            role_id integer not null
);

alter table only users
    add constraint pkusers primary key (user_id);

alter table only roles
    add constraint pkroles primary key (role_id);

alter table only user_roles
    add constraint pkuser_roles primary key (user_role_id);

alter table only user_roles
    add constraint fk_user_roles_users foreign key (user_id) references users(user_id);

alter table only user_roles
    add constraint fk_user_roles_roles foreign key (role_id) references roles(role_id);


insert into users(email, first_name, last_name, username, password)
values ('info.doovertech.com', 'Admin', 'Admin', 'DooverTech',
        '$2a$10$r0yp/aTI92nVveGFYerEI.GD6DxbH8Tc0PX63Trke5mHwv6Eg27Nu'); -- password = Adm!ndoovertech2020temppass

insert into roles(role, description) values ('ADMIN', 'access to all apis');
insert into roles(role, description) values ('SALES_MANAGER', 'access to management and sales apis');
insert into roles(role, description) values ('USER', 'access to all basic and common apis');

insert into user_roles(user_id, role_id) values (100, 100);

