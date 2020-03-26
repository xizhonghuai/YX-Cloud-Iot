/*
 Navicat Premium Data Transfer

 Source Server         : loaclhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 127.0.0.1:3306
 Source Schema         : edge-server

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 26/03/2020 17:04:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for device_msg
-- ----------------------------
DROP TABLE IF EXISTS `device_msg`;
CREATE TABLE `device_msg`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `device_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `msgbody` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `create_date` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `service_id`(`service_id`) USING BTREE,
  INDEX `device_id`(`device_id`) USING BTREE,
  INDEX `create_date`(`create_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
