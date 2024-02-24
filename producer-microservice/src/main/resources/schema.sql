drop table if exists users;

create table if not exists users
(
    user_id bigint generated by default as identity,
    name    varchar(250) not null,
    constraint users_pk primary key (user_id)
);