/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : yyhd_shiro

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 06/07/2021 11:01:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_permissions
-- ----------------------------
DROP TABLE IF EXISTS `auth_permissions`;
CREATE TABLE `auth_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限表id 作为表主键 用于关联',
  `permissionsName` varchar(32) DEFAULT NULL COMMENT '权限名称',
  `perRemarks` varchar(255) DEFAULT NULL COMMENT '备注，预留字段',
  `createTIme` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';

-- ----------------------------
-- Records of auth_permissions
-- ----------------------------
BEGIN;
INSERT INTO `auth_permissions` VALUES (1, 'article:read', NULL, '2021-06-24 14:31:25', '2021-06-24 14:38:41');
INSERT INTO `auth_permissions` VALUES (2, 'article:delete', NULL, '2021-06-24 14:36:45', '2021-06-24 14:38:46');
COMMIT;

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` bigint NOT NULL COMMENT '角色id 作为表主键 用于关联',
  `roleName` varchar(32) DEFAULT NULL COMMENT '角色名',
  `roleRemarks` varchar(255) DEFAULT NULL COMMENT '备注，预留字段',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of auth_role
-- ----------------------------
BEGIN;
INSERT INTO `auth_role` VALUES (1, 'admin', '管理员', '2021-06-24 14:29:39', '2021-06-24 14:29:39');
INSERT INTO `auth_role` VALUES (2, 'user', '普通用户', '2021-06-24 14:41:04', '2021-06-24 14:41:04');
COMMIT;

-- ----------------------------
-- Table structure for auth_role_per
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_per`;
CREATE TABLE `auth_role_per` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `roleId` bigint DEFAULT NULL COMMENT '角色表的主键id',
  `perId` bigint DEFAULT NULL COMMENT '权限表的主键id',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';

-- ----------------------------
-- Records of auth_role_per
-- ----------------------------
BEGIN;
INSERT INTO `auth_role_per` VALUES (1, 1, 1, '2021-06-24 11:23:55', '2021-06-24 14:37:03');
INSERT INTO `auth_role_per` VALUES (2, 1, 2, '2021-06-24 14:37:09', '2021-06-24 14:37:09');
INSERT INTO `auth_role_per` VALUES (3, 2, 1, '2021-06-24 14:41:25', '2021-06-24 14:41:31');
COMMIT;

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nickName` varchar(64) DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `trueName` varchar(64) DEFAULT NULL COMMENT '身份证姓名',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `locked` int NOT NULL DEFAULT '1' COMMENT '是否锁定',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';

-- ----------------------------
-- Records of auth_user
-- ----------------------------
BEGIN;
INSERT INTO `auth_user` VALUES (1, 'nitric oxide', 'ca8008049ef647249f5f43e8c65b0f35a1d0da8e', '一氧化氮', '16666666666', 1, '2021-06-23 18:56:58', '2021-06-24 10:51:40');
INSERT INTO `auth_user` VALUES (2, '张三', 'fc7b80581f4468939be40c9ab323bb28467101db', '张三三', '17777777777', 1, '2021-06-24 14:40:16', '2021-06-24 14:40:16');
COMMIT;

-- ----------------------------
-- Table structure for auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userId` bigint NOT NULL COMMENT '帐号表的主键id',
  `roleId` bigint NOT NULL COMMENT '角色表的主键id',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色关联表';

-- ----------------------------
-- Records of auth_user_role
-- ----------------------------
BEGIN;
INSERT INTO `auth_user_role` VALUES (1, 1, 1, '2021-06-24 14:29:59', '2021-06-24 14:29:59');
INSERT INTO `auth_user_role` VALUES (2, 2, 2, '2021-06-24 14:41:12', '2021-06-24 14:41:12');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
