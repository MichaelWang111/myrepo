/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : passjava_cms

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 06/07/2024 00:02:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cms_banner
-- ----------------------------
DROP TABLE IF EXISTS `cms_banner`;
CREATE TABLE `cms_banner` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `img_url` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片路径',
  `title` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标题',
  `display_order` int DEFAULT NULL COMMENT '排序',
  `ENABLE` tinyint DEFAULT NULL COMMENT '是否显示',
  `render_type` tinyint DEFAULT NULL COMMENT '跳转类型',
  `render_url` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '跳转路径',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记（0-正常，1-删除）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='内容-横幅广告表';

-- ----------------------------
-- Records of cms_banner
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cms_news
-- ----------------------------
DROP TABLE IF EXISTS `cms_news`;
CREATE TABLE `cms_news` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `image_url` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片路径',
  `title` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标题',
  `display_order` int DEFAULT NULL COMMENT '排序',
  `render_url` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '跳转路径',
  `ENABLE` tinyint DEFAULT NULL COMMENT '是否显示',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记（0-正常，1-删除）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='内容-资讯表';

-- ----------------------------
-- Records of cms_news
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
