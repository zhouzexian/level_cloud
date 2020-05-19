/*
 Navicat Premium Data Transfer

 Source Server         : base_db
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : psn_db

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 18/05/2020 09:08:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `accountId` int(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `accountNumber` varchar(128) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `statusId` int(1) NOT NULL DEFAULT '1' COMMENT '状态1=正常，0=禁用',
  `psnId` int(16) NOT NULL COMMENT '人员id',
  PRIMARY KEY (`accountId`) USING BTREE,
  UNIQUE KEY `idx_account_number` (`accountNumber`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `authorityId` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `authorityName` varchar(64) NOT NULL COMMENT '权限名',
  `authorityKey` varchar(64) DEFAULT NULL COMMENT '权限key',
  PRIMARY KEY (`authorityId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='权限信息表';

-- ----------------------------
-- Table structure for authority_uri
-- ----------------------------
DROP TABLE IF EXISTS `authority_uri`;
CREATE TABLE `authority_uri` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uri` varchar(64) NOT NULL COMMENT 'uri过滤',
  `authorityKey` varchar(16) NOT NULL COMMENT '角色标识',
  `remarks` varchar(64) DEFAULT NULL COMMENT '备注',
  `statusId` int(2) NOT NULL DEFAULT '1' COMMENT '状态1=启用0=禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='访问权限';

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `psnId` int(11) NOT NULL AUTO_INCREMENT COMMENT '人员标识',
  `psnName` varchar(64) NOT NULL COMMENT '人员名称',
  `sex` int(1) NOT NULL DEFAULT '0' COMMENT '性别0=未知1=男2=女',
  `telephone` varchar(16) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(64) DEFAULT NULL COMMENT '地址',
  `email` varchar(64) DEFAULT NULL COMMENT '电子邮件',
  `qq` varchar(16) DEFAULT NULL COMMENT 'qq号码',
  `wechat` varchar(16) DEFAULT NULL COMMENT '微信号码',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`psnId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='人员信息表';

-- ----------------------------
-- Table structure for rel_person_role
-- ----------------------------
DROP TABLE IF EXISTS `rel_person_role`;
CREATE TABLE `rel_person_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `psnId` int(11) NOT NULL COMMENT '人员标识',
  `roleId` int(11) NOT NULL COMMENT '角色标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='人员角色关系表';

-- ----------------------------
-- Table structure for rel_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `rel_role_authority`;
CREATE TABLE `rel_role_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT NULL COMMENT '角色标识',
  `authorityId` int(11) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关系表';

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleId` int(16) NOT NULL AUTO_INCREMENT COMMENT '角色标识',
  `roleName` varchar(64) NOT NULL COMMENT '角色名称',
  `roleKey` varchar(64) NOT NULL COMMENT '角色key',
  `roleType` varchar(64) DEFAULT NULL COMMENT '角色类型',
  `parentId` int(11) DEFAULT NULL COMMENT '父级id',
  `statusId` int(1) DEFAULT '1' COMMENT '状态1=启用0=禁用',
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='角色信息表';

SET FOREIGN_KEY_CHECKS = 1;
