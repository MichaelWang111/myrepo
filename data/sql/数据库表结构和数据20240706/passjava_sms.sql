/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : passjava_sms

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 06/07/2024 00:02:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sms_study_time
-- ----------------------------
DROP TABLE IF EXISTS `sms_study_time`;
CREATE TABLE `sms_study_time` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ques_type` bigint DEFAULT NULL COMMENT '题目类型id',
  `member_id` bigint DEFAULT NULL COMMENT '用户id',
  `total_time` int DEFAULT NULL COMMENT '学习时常（分）',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记（0-正常，1-删除）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='学习-用户学习时常表';

-- ----------------------------
-- Records of sms_study_time
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sms_view_log
-- ----------------------------
DROP TABLE IF EXISTS `sms_view_log`;
CREATE TABLE `sms_view_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ques_id` bigint DEFAULT NULL COMMENT '题目id',
  `ques_type` bigint DEFAULT NULL COMMENT '题目类型id',
  `member_id` bigint DEFAULT NULL COMMENT '用户id',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记（0-正常，1-删除）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='学习-用户学习浏览记录表';

-- ----------------------------
-- Records of sms_view_log
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
