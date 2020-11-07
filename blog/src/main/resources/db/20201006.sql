create table role (
     id int(30) not null auto_increment,
     role_id varchar(50) not null,
     role_name varchar(50) default null,
     role_name_zh varchar(50) default null,
     primary key (id)
 );


create table user_role (
    id int(30) not null auto_increment,
    user_id varchar(50) default null,
    role_id varchar(50) default null,
    primary key (id)
);

alter table user change name username varchar(50) not null unique comment '用户名';
alter table user add username varchar(50) not null unique comment '用户名';
alter table user add enabled boolean comment '是否启用';
alter table user add locked boolean comment '是否锁定';
