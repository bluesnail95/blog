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

 Date: 07/03/2018 23:51:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_access
-- ----------------------------
DROP TABLE IF EXISTS `user_access`;
CREATE TABLE `user_access`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `classification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `website_type` int(255) NULL DEFAULT NULL,
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_access
-- ----------------------------
INSERT INTO `user_access` VALUES ('0cee360a-a034-444b-a093-08a7a14e551b', NULL, '2018-03-07 21:46:48', 'MySQL高级学习笔记', 'https://www.cnblogs.com/yrrAwx/p/8523361.html', 2, '8a2153bb-d5ec-4678-9f50-0709126b2a73');
INSERT INTO `user_access` VALUES ('2746d8e5-03a3-441c-b0e9-2a3df8805af3', NULL, '2018-03-07 21:46:22', '深度学习大神都推荐入门必须读完这9篇论文', 'http://blog.csdn.net/meyh0x5vDTk48P2/article/details/79072666', 1, '8a2153bb-d5ec-4678-9f50-0709126b2a73');
INSERT INTO `user_access` VALUES ('2a55f5a9-f9cc-4bc0-af98-6509fffaf179', NULL, '2018-03-07 21:47:00', 'W3Techs CMS 流行度统计：WordPress 一枝独秀', 'http://www.oschina.net/news/93930/w3techs-cms-trend', 3, '8a2153bb-d5ec-4678-9f50-0709126b2a73');
INSERT INTO `user_access` VALUES ('712202b5-606c-406d-a962-2515302bbfb5', '资讯', '2018-03-07 21:46:14', '颠覆BAT的新出路', 'http://blog.csdn.net/kXYOnA63Ag9zqtXx0/article/details/79073454', 1, '8a2153bb-d5ec-4678-9f50-0709126b2a73');
INSERT INTO `user_access` VALUES ('8145883c-ef31-4364-8b86-c1a1becf1324', NULL, '2018-03-07 21:46:55', 'Atom 团队推出下一代跨平台高性能文本编辑器 Xray', 'http://www.oschina.net/news/93954/atom-team-opensource-xray', 3, '8a2153bb-d5ec-4678-9f50-0709126b2a73');
INSERT INTO `user_access` VALUES ('a8e1f7a8-0e54-4c76-acc4-a062ee5855c9', NULL, '2018-03-07 21:46:44', 'Django 自定义用户认证', 'https://www.cnblogs.com/forsaken627/p/8523371.html', 2, '8a2153bb-d5ec-4678-9f50-0709126b2a73');
INSERT INTO `user_access` VALUES ('d5a765d4-428a-40f4-81e5-9a4a126f8e97', NULL, '2018-03-07 21:47:07', '码云周刊 | 五花八门的验证码程序，你遇过几个？', 'https://my.oschina.net/gitosc/blog/1627438', 3, '8a2153bb-d5ec-4678-9f50-0709126b2a73');
INSERT INTO `user_access` VALUES ('e5b717be-04ae-43e0-bd29-6768ce31b53b', NULL, '2018-03-07 21:46:37', '为什么你应该停止阅读新闻?', 'https://www.cnblogs.com/IcanFixIt/p/8524843.html', 2, '8a2153bb-d5ec-4678-9f50-0709126b2a73');

SET FOREIGN_KEY_CHECKS = 1;
