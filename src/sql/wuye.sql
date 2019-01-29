drop table if exists t_normal_user_address;
create table t_normal_user_address
(
  id bigint auto_increment primary key,
  customer_name varchar(64) comment '联系人',
  phone_number varchar(12) comment '联系方式',
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
  create_by bigint default 1 comment '创建者',
  del_flag boolean default false comment '删除标志'
);
# 区域实体表
drop table if exists t_normal_user_address_entity;
create table t_normal_user_address_entity
(
  id bigint auto_increment primary key,
  type varchar(2) comment '00-国家 01-省 02-市 03-区或县 04-镇 05-小区 06-楼号 07-单元号 08-房间号',
  parent_id bigint comment '父级区域的id',
  name varchar(256) comment '名称',
  code varchar(64) comment '代码',
  short_name varchar(64) comment '简称',
  img_url varchar(256) comment '图片路径',
  create_time timestamp default now(),
  create_by bigint default 1,
  del_flag boolean default false
);

#普通用户表
drop table if exists t_normal_user;
create table t_normal_user
(
  id bigint auto_increment primary key,
  openid varchar(64) unique comment 'openid',
  nick_name varchar(128) comment '昵称',
  sex varchar(1) comment '性别：1-男，2-女',
  nation varchar(64) comment '国家',
  province varchar(128) comment '省',
  city varchar(128) comment '城市',
  country varchar(128) comment '区或者县',
  head_img_url varchar(256) comment '头像地址',
  unionid varchar(64) comment '统一id',
  create_time timestamp default now() comment '创建时间',
  create_by bigint default 1 ,
  del_flag boolean default false
);

#报修投诉列表
drop table if exists t_problem;
create table t_problem
(
  id bigint auto_increment primary key,
  openid varchar(64) comment '客户的openid',
  normal_user_id bigint comment '客户的id',
  problem_type_id bigint comment '报修问题类型的id',
  problem_description varchar(256) comment '报修问题描述',
  appointment_time timestamp comment '预约的截止时间',
  create_time timestamp default now() comment '创建时间',
  create_by bigint default 1 comment '',
  del_flag boolean default false comment ''
);

#问题类型表
drop table if exists t_problem_type;
create table t_problem_type
(
  id bigint auto_increment primary key,
  name varchar(64) comment '问题类型名称',
  short_name varchar(32) comment '简称',
  description varchar(64) comment '描述',
  create_time timestamp default now() comment '',
  create_by bigint default 1 comment '',
  del_flag boolean default false comment ''
);

#报修问题关联图片表
drop table if exists t_problem_picture;
create table t_problem_picture
(
  id bigint auto_increment primary key,
  problem_id bigint comment '问题id',
  url varchar(256) comment '保存在对象存储服务器上的url',
  create_time timestamp default now() comment '',
  create_by bigint default 1 comment '',
  del_flag boolean default false comment ''
);