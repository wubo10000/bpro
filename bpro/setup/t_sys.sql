/*
Navicat MySQL Data Transfer

Source Server         : rm-wz9qt812z0q2f832po.mysql.rds.aliyuncs.com
Source Server Version : 50629
Source Host           : rm-wz9qt812z0q2f832po.mysql.rds.aliyuncs.com:3306
Source Database       : jungong2

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2016-06-28 17:48:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_config
-- ----------------------------
DROP TABLE IF EXISTS `t_config`;
CREATE TABLE `t_config` (
  `T_KEY` int(4) DEFAULT NULL COMMENT '关键字',
  `T_VALUE` varchar(256) DEFAULT NULL COMMENT '内qqqqqq容',
  `T_GROUP` varchar(32) DEFAULT NULL COMMENT '分组',
  `T_GROUP_NAME` varchar(32) DEFAULT NULL COMMENT '分组名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `ID` varchar(128) NOT NULL COMMENT '菜单ID',
  `NAME` varchar(64) DEFAULT NULL COMMENT '菜单名称',
  `DESCRIPTION` varchar(50) DEFAULT NULL COMMENT '菜单描述',
  `URL` varchar(256) DEFAULT NULL COMMENT 'URL请求地址',
  `PRIORITY` int(3) DEFAULT NULL COMMENT '优先级',
  `PARENT_ID` varchar(128) DEFAULT NULL COMMENT '父节点ID',
  `LEVEL` int(2) DEFAULT NULL COMMENT '菜单层级',
  `IMG_URL` varchar(256) DEFAULT NULL COMMENT '菜单图片',
  `CONFIG` varchar(100) DEFAULT NULL COMMENT '菜单映射配置',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `ID` varchar(128) NOT NULL COMMENT '角色编号',
  `NAME` varchar(64) NOT NULL COMMENT '角色名称',
  `ROLE_STATE` int(2) NOT NULL COMMENT '角色状态\r\n:1可用，2不可用',
  `PRIORITY` int(3) DEFAULT '0' COMMENT '优先级',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `ID` varchar(128) NOT NULL COMMENT '角色菜单ID',
  `ROLE_ID` varchar(128) DEFAULT NULL COMMENT '角色ID',
  `MENU_ID` varchar(128) DEFAULT NULL COMMENT '菜单ID',
  `PRIORITY` int(3) DEFAULT NULL COMMENT '优先级',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `ID` varchar(128) NOT NULL COMMENT 'ID',
  `LOGIN_NAME` varchar(32) NOT NULL COMMENT '登录名称',
  `NAME` varchar(32) NOT NULL COMMENT '用户真实姓名',
  `PASSWORD` varchar(128) NOT NULL COMMENT '用户密码',
  `TEL` varchar(16) DEFAULT NULL COMMENT '联系电话',
  `PHONE` varchar(16) DEFAULT NULL COMMENT '移动电话',
  `E_MAIL` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建日期',
  `LAST_LOGIN_TIME` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `T_STATE` int(2) NOT NULL DEFAULT '1' COMMENT '状态：1正常，2禁用',
  `ROLE_ID` varchar(128) NOT NULL COMMENT '角色ID',
  `T_IMG` varchar(128) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
