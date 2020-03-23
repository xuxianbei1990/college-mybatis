--
--    Copyright 2009-2016 the original author or authors.
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.
--

drop table users if exists;

create table users (
  id int,
  name varchar(20)
);

insert into users (id, name) values(1, 'User1');


CREATE TABLE `t_lkb_order_buyer`  (
  `forder_buyer_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品订单表主键id',
  `forder_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单编号',
  `forder_parent_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单父编号',
  `fseller_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '店铺id',
  `fshop_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商家名称',
  `fbuyer_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  `fpick_up_point_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '提货地址表主键',
  `fbuyer_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名称',
  `forder_payment_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付订单号（系统）',
  `fgoods_cn_amount` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品金额(rmb)',
  `fgoods_tax_cn_amount` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品税费(rmb)',
  `ffreight_cn_amount` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '运费（rmb）',
  `fdiscount_cn_amount` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '优惠金额（rmb）',
  `forder_cn_amount` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单金额（rmb）',
  `fcurrency_name_en` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '币种',
  `fexchange_rate` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '汇率:(10000外币兑人民币)',
  `forder_type` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单类型（备用 默认：0）',
  `forder_status` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单状态,0：待支付，1：已支付（待发货），2：订单取消，3：已发货，4：已完成（签收），5：延迟收货 6：交易关闭 7: 待确认 8: 待提货',
  `fafter_sale_status` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单退款退货状态，0：未申请退货退款，1：申请退款退货，2：已退货退款，3：申请退货退款失败',
  `fpush_status` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单推送状态，0：未推送，1：已推送',
  `ffinish_time` datetime(0) NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '订单完成时间',
  `fpush_time` datetime(0) NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '订单推送时间',
  `fdelivery_time` datetime(0) NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '订单发货时间',
  `flogistics_order` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单物流单号',
  `fcarriage_temp_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '运费模板id',
  `freceive_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '收货人姓名；如果forder_origin =1 为 预约的用户名',
  `freceive_province` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户收货省编码',
  `freceive_city` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户收货市编码',
  `freceive_region` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户收货区编码',
  `freceive_addr` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '详细收货地址',
  `freceive_mobile` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '收货人手机号码',
  `freceive_addr_flag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '提货地址标记：0：普通；1：机场',
  `freceive_email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '收货人邮箱',
  `forderer_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订购人表主键id',
  `forderer_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订购人姓名',
  `forderer_id_card` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订购人身份证; 关联forder_origin字段；如果是1 该字段代表护照',
  `ftake_delivery_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '提货时间',
  `fconfirm_time` datetime(0) NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '确认时间',
  `freality_take_delivery_time` datetime(0) NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '实际提货时间',
  `fleave_flight_number` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '离境航班',
  `fleave_time` datetime(0) NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '离境时间',
  `fpayer_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '支付人姓名',
  `fpayer_id_card` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '支付人身份证',
  `fpay_time` datetime(0) NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '支付时间',
  `forder_remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单备注',
  `forder_reason` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单原因',
  `fcreate_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建（下单）时间',
  `fmodify_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `ffreight_rule_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '运费计算规则id',
  `fthird_trade_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '第三方支付流水号（例：微信支付流水号）',
  `forder_pay_channel` tinyint(4) NOT NULL DEFAULT 0 COMMENT '订单支付通道：默认0：微信；1:qfpay',
  `forder_pay_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '订单支付方式：默认0：线上支付； 1：线下支付',
  `forder_origin` tinyint(4) NOT NULL DEFAULT 0 COMMENT '订单来源：默认0； 1：代表预约提货',
  `forder_pickup_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '提货码',
  `fshopping_guide_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '导购',
  `finvite_id` bigint(20) NULL DEFAULT NULL COMMENT '邀请人ID',
  `fshare_buyer_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分享者id 非群内用户下单使用',
  `fleader_buyer_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分享者上级id 非群内用户下单使用',
  `fshare_buyer_group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '分享者所在群id',
  `api_transport_sync` tinyint(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '运单同步标记0： 未同步；1同步',
  PRIMARY KEY (`forder_buyer_id`) USING BTREE,
  INDEX `idx_forder_no`(`forder_no`) USING BTREE COMMENT '订单编号唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 3491 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户商品订单表' ROW_FORMAT = Dynamic;
INSERT INTO `t_lkb_order_buyer`(`forder_buyer_id`, `forder_no`, `forder_parent_no`, `fseller_id`, `fshop_name`, `fbuyer_id`, `fpick_up_point_id`, `fbuyer_name`, `forder_payment_id`, `fgoods_cn_amount`, `fgoods_tax_cn_amount`, `ffreight_cn_amount`, `fdiscount_cn_amount`, `forder_cn_amount`, `fcurrency_name_en`, `fexchange_rate`, `forder_type`, `forder_status`, `fafter_sale_status`, `fpush_status`, `ffinish_time`, `fpush_time`, `fdelivery_time`, `flogistics_order`, `fcarriage_temp_id`, `freceive_name`, `freceive_province`, `freceive_city`, `freceive_region`, `freceive_addr`, `freceive_mobile`, `freceive_addr_flag`, `freceive_email`, `forderer_id`, `forderer_name`, `forderer_id_card`, `ftake_delivery_time`, `fconfirm_time`, `freality_take_delivery_time`, `fleave_flight_number`, `fleave_time`, `fpayer_name`, `fpayer_id_card`, `fpay_time`, `forder_remark`, `forder_reason`, `fcreate_time`, `fmodify_time`, `ffreight_rule_id`, `fthird_trade_no`, `forder_pay_channel`, `forder_pay_type`, `forder_origin`, `forder_pickup_code`, `fshopping_guide_id`, `finvite_id`, `fshare_buyer_id`, `fleader_buyer_id`, `fshare_buyer_group_id`, `api_transport_sync`) VALUES (102, '101388305907580928', '', 100003, '大阪熊免税店', 5, 0, '', 101388305907580929, 2442, 226, 3757, 0, 6424, 'JPY', 62603, 0, 2, 0, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00', '1970-01-01 00:00:00', '', 0, 'bond', 330000, 330100, 330108, '西科科技园', '15012681079', 0, '', 0, 'bond', '421125199011071112', '2019-07-30 10:31:27', '2019-07-31 13:57:59', '2019-07-31 13:57:59', '', '1970-01-01 00:00:00', '', '', '1970-01-01 00:00:00', '', '', '2019-05-24 02:40:55', '2019-05-24 03:10:00', 0, '', 0, 0, 0, '', 0, NULL, 0, 0, '', 0);
