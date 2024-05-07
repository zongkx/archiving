/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50087
 Source Host           : localhost:3306
 Source Schema         : springboot

 Target Server Type    : MySQL
 Target Server Version : 50087
 File Encoding         : 65001

 Date: 09/09/2018 10:18:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_createtime` date NULL DEFAULT NULL,
  `u_state` int(255) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`u_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '110', '123', '2018-09-08', 1);
INSERT INTO `user` VALUES (2, '111', '123', '2018-09-01', 1);
INSERT INTO `user` VALUES (3, '112', '123', '2018-09-05', 0);

SET FOREIGN_KEY_CHECKS = 1;
