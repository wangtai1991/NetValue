/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50535
Source Host           : localhost:3306
Source Database       : web

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2015-09-16 15:59:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) NOT NULL COMMENT '产品名称',
  `user_id` varchar(100) NOT NULL COMMENT 'user表的id，通过“,”分隔开，例如：1,2,3,',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', 'name', '2,1,');

-- ----------------------------
-- Table structure for `product_net_value`
-- ----------------------------
DROP TABLE IF EXISTS `product_net_value`;
CREATE TABLE `product_net_value` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) NOT NULL,
  `value` decimal(18,4) NOT NULL,
  `date_time` date NOT NULL,
  `type` tinyint(2) NOT NULL COMMENT '类型：1 估计值，2 准确值',
  `user_id` int(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `product_net_value_log`
-- ----------------------------
DROP TABLE IF EXISTS `product_net_value_log`;
CREATE TABLE `product_net_value_log` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) NOT NULL,
  `value` decimal(18,4) NOT NULL,
  `date_time` date NOT NULL,
  `type` tinyint(2) NOT NULL COMMENT '类型：1 估计值，2 准确值',
  `user_id` int(20) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'name1', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('2', 'name2', 'e10adc3949ba59abbe56e057f20f883e');
