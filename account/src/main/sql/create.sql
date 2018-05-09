drop table if exists c_account_info;

/*==============================================================*/
/* Table: c_account_info                                        */
/*==============================================================*/
create table c_account_info
(
      id                   bigint not null auto_increment,
   user_name            varchar(30) comment '用户名',
   password             varchar(64) not null comment '密码',
   father_id            bigint not null comment '一级推荐人',
   grand_id             bigint comment '二级推荐人',
   ggrand_id            bigint comment '三级推荐人',
   mobile_no            varchar(13) not null comment '手机号',
   identity_card        varchar(256) comment '身份证图片',
   driving_card         varchar(256) comment '行驶证图片',
   car_side_img         varchar(256) comment '车侧面正面照片',
   car_header_img       varchar(256) comment '车头照片',
   real_name            varchar(10) comment '真实姓名',
   identity_no          varchar(30) comment '身份证号',
   borth_date           varchar(10) comment '出生日期',
   plate_no             varchar(10) not null comment '车牌号',
   detail_address       varchar(256) comment '详细地址',
   province             varchar(10) comment '省',
   city                 varchar(10) comment '市',
   area                 varchar(10) comment '区/县',
   province_code        varchar(10),
   city_code            varchar(10),
   area_code            varchar(10),
   old_address          varchar(256) comment '原地址',
   village              varchar(30) comment '小区',
   car_brand            varchar(10) comment '车辆品牌',
   car_model            varchar(10) comment '车辆型号',
   sex                  int(1),
   bank_car_no          varchar(30) comment '银行卡号',
   open_bank            varchar(30) comment '开户行',
   net_car              int(1) comment '是否为网约车1是0否',
   flag                 int(1) not null default 0 comment '0待审核 1审核通过2审核未通过 5已删除',
   create_time          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   update_time          datetime comment '修改时间',
   lgt                  double(13,7) comment '经度',
   lat                  double(13,7) comment '纬度',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  comment 'c端（车主）账户信息';