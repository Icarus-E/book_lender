/*
 Navicat Premium Data Transfer

 Source Server         : LocalLink
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : book_system

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 11/06/2022 22:28:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_book
-- ----------------------------
DROP TABLE IF EXISTS `tbl_book`;
CREATE TABLE `tbl_book`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_book
-- ----------------------------
INSERT INTO `tbl_book` VALUES (13, 'history???', 'Sapiens', 'a brief history of humankind');
INSERT INTO `tbl_book` VALUES (17, 'Changed', 'Changed', 'Changed');
INSERT INTO `tbl_book` VALUES (18, 'Test', 'Test', 'Test');
INSERT INTO `tbl_book` VALUES (21, 'Test', 'Test', 'Test');
INSERT INTO `tbl_book` VALUES (37, 'novel', 'Gone with the wind', '...');
INSERT INTO `tbl_book` VALUES (38, 'test', 'test', 'test');
INSERT INTO `tbl_book` VALUES (39, 'test', 'test', 'test');

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES (1, '1234', '1234', '赵六');
INSERT INTO `tbl_user` VALUES (2, 'admin', '1234', '张三');
INSERT INTO `tbl_user` VALUES (4, 'icarus', '112233', '李四');

-- ----------------------------
-- Table structure for user_merge_book
-- ----------------------------
DROP TABLE IF EXISTS `user_merge_book`;
CREATE TABLE `user_merge_book`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `book_id` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `book_id`(`book_id`) USING BTREE,
  CONSTRAINT `user_merge_book_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_merge_book_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `tbl_book` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_merge_book
-- ----------------------------
INSERT INTO `user_merge_book` VALUES (51, 2, 13);
INSERT INTO `user_merge_book` VALUES (53, 2, 17);
INSERT INTO `user_merge_book` VALUES (55, 2, 18);
INSERT INTO `user_merge_book` VALUES (57, 1, 17);

SET FOREIGN_KEY_CHECKS = 1;
