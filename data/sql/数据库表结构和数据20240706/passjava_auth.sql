/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : passjava_auth

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 06/07/2024 00:02:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `org_id` int DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`,`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`username`, `password`, `org_id`, `enabled`, `phone`, `email`, `create_time`, `update_time`, `id`, `user_id`) VALUES ('悟空', '$2a$10$xtVXutKXORjh2UmKbOf4WOkiB.1GlXpfvil.yDTQgw1pREhHo0G1O', 1, b'1', '15912345678', 'abc@qq.com', '2022-08-10 07:20:30', '2022-08-10 07:24:33', 1, 'wukong');
INSERT INTO `sys_user` (`username`, `password`, `org_id`, `enabled`, `phone`, `email`, `create_time`, `update_time`, `id`, `user_id`) VALUES ('贝吉塔', '$2a$10$IAHUqoKYSgu/P0l9CiraTOQ/SJottVw9uS.QX9AS.NpZsLmtP5QgW', 1, b'1', '15922334455', 'edf@qq.com', '2022-08-10 07:20:30', '2022-08-10 07:24:33', 2, 'beijita');
INSERT INTO `sys_user` (`username`, `password`, `org_id`, `enabled`, `phone`, `email`, `create_time`, `update_time`, `id`, `user_id`) VALUES ('admin', '$2a$10$IAHUqoKYSgu/P0l9CiraTOQ/SJottVw9uS.QX9AS.NpZsLmtP5QgW', 1, b'1', '15922334455', 'edf@qq.com', '2022-08-10 07:20:30', '2022-08-10 07:24:33', 3, 'admin');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
