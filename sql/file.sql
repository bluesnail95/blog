/*
 Navicat Premium Data Transfer

 Source Server         : mydb
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 13/05/2018 01:43:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `FILE_ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务标识',
  `COUNT` int(11) NULL DEFAULT NULL COMMENT '文件下载的次数',
  `FILE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名',
  `FILE_INTRODUCTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件介绍',
  `GROUP_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'FastDFS分组名称',
  `REMOTE_FILE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'FastDFS文件名',
  `GMT_CREATE` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `GMT_MODIFIED` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('090b8559-cb33-4a8a-a377-82e2c71efc1b', 'd98a6b13-f90d-4cc6-809e-76adae2f06cf', 0, 'photo.jpg', '上传头像', 'group1', 'M00/00/00/rBAAEFrpxHiAUGPRAAAIdkgFegc18.file', '2018-05-04 22:43:38', '2018-05-04 22:43:38');
INSERT INTO `file` VALUES ('3e002402-ea2d-42f8-99ec-4589b7de790a', '40abfd80-a5c5-483c-ab27-768da2e21851', 0, 'Java并发编程实战.xmind', NULL, 'group1', 'M00/00/00/rBAAEFrthKCAKCcFAEYRDpFVsf806.file', '2018-05-05 18:17:21', '2018-05-05 18:17:21');
INSERT INTO `file` VALUES ('e08febdc-d5e0-42a4-bb9c-45122127abd4', 'dc8e5717-1988-4379-bbf4-2bb59a9e11d0', 0, '运行时数据区域.jpg', NULL, 'group1', 'M00/00/00/rBAAEFrtgvyAGlMMAEO3KU52cP867.file', '2018-05-05 18:10:10', '2018-05-05 18:10:10');

SET FOREIGN_KEY_CHECKS = 1;
