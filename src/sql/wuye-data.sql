insert into t_normal_user_address(province_id,city_id,country_or_district_id,town_id,community_id,building_id,unit_id,room_id,normal_user_id,normal_users_default_address,create_by) value (1,1,1,1,1,1,1,1,1,true,1);
insert into t_normal_user_address_province(name, code, short_name, create_by) VALUE ('内蒙古自治区','nmg','内蒙古',1);
insert into t_normal_user_address_city(name, code, short_name, create_by) VALUE ('鄂尔多斯市','eeds','鄂尔多斯',1);
insert into t_normal_user_address_country(name, code, short_name, create_by) VALUE ('伊金霍洛旗','yjhl','伊旗',1);
insert into t_normal_user_address_town(name, code, short_name, create_by) value ('阿勒腾席热镇','altxr','阿镇',1);
insert into t_normal_user_address_community(name, code, short_name, create_by) VALUE ('万佳御园','wanjiayuyuan','万佳御园',1);
insert into t_normal_user_address_building(name, code, short_name, create_by) VALUE ('5号楼','5','5号楼',1);
insert into t_normal_user_address_unit(name, code, short_name, create_by) VALUE ('1单元','1','1单元',1);
insert into t_normal_user_address_room(name, code, short_name, create_by) VALUE ('903室','903','903室',1);

insert into t_normal_user(openid, nick_name, sex, nation, province, city, country, head_img_url, unionid, create_by) VALUES ('oLb0h1kMIeuxBV8DnVPUM8XVPNck','石伊波','1','中国','内蒙古','鄂尔多斯',null,'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI1Wldr2FDEysPH26zNPTicMibuwtQfx1giamy6scKdOWlcZpiaS0LfuSJS9McxTxp9bVl2CBaibHUrC9w/132',null,1)