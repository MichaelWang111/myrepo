/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : passjava_chms

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 06/07/2024 00:01:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chms_access_token
-- ----------------------------
DROP TABLE IF EXISTS `chms_access_token`;
CREATE TABLE `chms_access_token` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `access_token` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'access_token',
  `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
  `channel_id` bigint DEFAULT NULL COMMENT '渠道id',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记（0-正常，1-删除）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='渠道-认证表';

-- ----------------------------
-- Records of chms_access_token
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for chms_channel
-- ----------------------------
DROP TABLE IF EXISTS `chms_channel`;
CREATE TABLE `chms_channel` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `NAME` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '渠道名称',
  `appid` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '渠道appid',
  `appsecret` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '渠道appsecret',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记（0-正常，1-删除）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='渠道-渠道表';

-- ----------------------------
-- Records of chms_channel
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
