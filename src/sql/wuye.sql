drop table if exists t_normal_user_address;
create table t_normal_user_address
(
  id bigint auto_increment primary key,
  province_id bigint comment '省id',
  city_id bigint comment '市id',
  country_or_district_id bigint comment '县或区id',
  town_id bigint comment '镇id',
  community_id bigint comment '小区id',
  building_id bigint comment '楼号id',
  unit_id bigint comment '单元id',
  room_id bigint comment '房间id',
  normal_user_id bigint comment '该地址所属普通用户的id',
  normal_users_default_address boolean comment '是否是用户的默认小区',
  create_time timestamp default now() comment '创建时间',
  create_by bigint not null comment '创建者',
  del_flag boolean default false comment '删除标志'
);
# 省
drop table if exists t_normal_user_address_province;
create table t_normal_user_address_province
(
  id bigint auto_increment primary key,
  name varchar(256) comment '名称',
  code varchar(64) comment '代码',
  short_name varchar(64) comment '简称',
  create_time timestamp default now(),
  create_by bigint not null ,
  del_flag boolean default false
);
# 市
drop table if exists t_normal_user_address_city;
create table t_normal_user_address_city
(
  id bigint auto_increment primary key,
  name varchar(256) comment '名称',
  code varchar(64) comment '代码',
  short_name varchar(64) comment '简称',
  create_time timestamp default now(),
  create_by bigint not null ,
  del_flag boolean default false
);
# 县
drop table if exists t_normal_user_address_country;
create table t_normal_user_address_country
(
  id bigint auto_increment primary key,
  name varchar(256) comment '名称',
  code varchar(64) comment '代码',
  short_name varchar(64) comment '简称',
  create_time timestamp default now(),
  create_by bigint not null ,
  del_flag boolean default false
);
# 镇
drop table if exists t_normal_user_address_town;
create table t_normal_user_address_town
(
  id bigint auto_increment primary key,
  name varchar(256) comment '名称',
  code varchar(64) comment '代码',
  short_name varchar(64) comment '简称',
  create_time timestamp default now(),
  create_by bigint not null ,
  del_flag boolean default false
);
# 小区
drop table if exists t_normal_user_address_community;
create table t_normal_user_address_community
(
  id bigint auto_increment primary key,
  name varchar(256) comment '名称',
  code varchar(64) comment '代码',
  short_name varchar(64) comment '简称',
  create_time timestamp default now(),
  create_by bigint not null ,
  del_flag boolean default false
);
# 楼号
drop table if exists t_normal_user_address_building;
create table t_normal_user_address_building
(
  id bigint auto_increment primary key,
  name varchar(256) comment '名称',
  code varchar(64) comment '代码',
  short_name varchar(64) comment '简称',
  create_time timestamp default now(),
  create_by bigint not null ,
  del_flag boolean default false
);
# 单元号
drop table if exists t_normal_user_address_unit;
create table t_normal_user_address_unit
(
  id bigint auto_increment primary key,
  name varchar(256) comment '名称',
  code varchar(64) comment '代码',
  short_name varchar(64) comment '简称',
  create_time timestamp default now(),
  create_by bigint not null ,
  del_flag boolean default false
);
# 房间号
drop table if exists t_normal_user_address_room;
create table t_normal_user_address_room
(
  id bigint auto_increment primary key,
  name varchar(256) comment '名称',
  code varchar(64) comment '代码',
  short_name varchar(64) comment '简称',
  create_time timestamp default now(),
  create_by bigint not null ,
  del_flag boolean default false
);

