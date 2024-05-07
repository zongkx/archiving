/*
 Navicat Premium Data Transfer

 Source Server         : MySQL1
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : localhost:3306
 Source Schema         : raynor

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 04/06/2019 16:37:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for behavior
-- ----------------------------
DROP TABLE IF EXISTS `behavior`;
CREATE TABLE `behavior`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `software_id` bigint(20) NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `score` float(255, 0) NULL DEFAULT NULL,
  `time` time(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of behavior
-- ----------------------------
INSERT INTO `behavior` VALUES (24, 64, 'test', -1, NULL);
INSERT INTO `behavior` VALUES (25, 64, 'test', 5, '02:34:08');
INSERT INTO `behavior` VALUES (26, 66, 'test', 5, '02:34:47');
INSERT INTO `behavior` VALUES (27, 66, 'test', -1, NULL);
INSERT INTO `behavior` VALUES (28, 65, 'root', 3, '02:34:59');
INSERT INTO `behavior` VALUES (29, 65, 'root', -1, NULL);
INSERT INTO `behavior` VALUES (30, 66, 'test1', 1, '02:38:11');
INSERT INTO `behavior` VALUES (31, 60, 'test1', -1, NULL);
INSERT INTO `behavior` VALUES (32, 60, 'test1', 4, '02:38:33');
INSERT INTO `behavior` VALUES (33, 65, 'test', -1, NULL);
INSERT INTO `behavior` VALUES (34, 1, 'test', 5, '06:24:50');
INSERT INTO `behavior` VALUES (35, 1, 'root', 3, '03:41:09');
INSERT INTO `behavior` VALUES (36, 1, 'root', -1, NULL);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言表主键',
  `from_uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论用户id',
  `to_uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论目标用户id',
  `aid` int(11) NULL DEFAULT NULL COMMENT '关联动态或者软件的ID',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '留言时间',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题: 1 软件 2.动态 3.系统消息',
  `flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '已读标记: 0 未读 1 已读',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 227 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (191, 'test', '', 57, '2019-05-15 23:00:47', 'sdffsdf', '1', '0');
INSERT INTO `comment` VALUES (192, 'root', '', 1, '2019-05-18 22:44:17', '圣达菲', '1', '0');
INSERT INTO `comment` VALUES (193, 'root', '', 75, '2019-05-19 00:06:24', '这是root的评论', '2', '0');
INSERT INTO `comment` VALUES (194, 'test', 'root', 1, '2019-05-19 00:20:15', '@root  撒旦飞洒地方', '1', '1');
INSERT INTO `comment` VALUES (195, 'test', 'test', 1, '2019-05-28 18:58:25', '@test  弟弟', '1', '1');
INSERT INTO `comment` VALUES (196, '系统批处理', 'root', 61, '2019-06-01 02:22:34', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (197, '系统批处理', 'root', 61, '2019-06-01 02:24:51', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (198, '系统批处理', 'root', 61, '2019-06-01 02:30:07', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (199, '系统批处理', 'root', 61, '2019-06-01 02:46:29', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (200, '系统批处理', 'root', 61, '2019-06-01 02:49:43', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (201, '系统批处理', 'root', 61, '2019-06-02 06:22:44', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (202, '系统批处理', 'root', 61, '2019-06-02 06:44:16', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (203, '系统批处理', 'root', 61, '2019-06-02 06:53:26', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (204, '系统批处理', 'root', 61, '2019-06-02 07:03:42', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (205, '系统批处理', 'root', 61, '2019-06-02 07:12:13', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (206, '系统批处理', 'root', 61, '2019-06-02 07:15:12', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (207, '系统批处理', 'root', 61, '2019-06-02 07:17:17', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (208, '系统批处理', 'root', 61, '2019-06-02 07:26:02', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (209, '系统批处理', 'root', 61, '2019-06-02 07:27:40', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (210, 'test', '', 58, '2019-06-01 23:27:56', '撒旦飞洒地方', '1', '0');
INSERT INTO `comment` VALUES (211, 'test', '', 3, '2019-06-01 23:28:11', '踩踩踩踩踩', '1', '0');
INSERT INTO `comment` VALUES (212, '系统批处理', 'root', 61, '2019-06-02 07:29:35', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (213, '系统批处理', 'root', 61, '2019-06-02 07:46:33', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (214, '系统批处理', 'root', 61, '2019-06-02 08:54:13', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (215, '系统批处理', 'root', 61, '2019-06-02 09:08:24', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (216, '系统批处理', 'test', 64, '2019-06-02 09:08:24', '       您发布的 ===测试文章 By用户Test=====的下载链接已经失效,请及时维护', '3', '1');
INSERT INTO `comment` VALUES (217, '系统批处理', 'root', 61, '2019-06-03 06:52:46', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (218, '系统批处理', 'test', 64, '2019-06-03 06:52:46', '       您发布的 ===测试文章 By用户Test=====的下载链接已经失效,请及时维护', '3', '1');
INSERT INTO `comment` VALUES (219, '系统批处理', 'root', 61, '2019-06-03 06:57:10', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (220, '系统批处理', 'test', 64, '2019-06-03 06:57:11', '       您发布的 ===测试文章 By用户Test=====的下载链接已经失效,请及时维护', '3', '1');
INSERT INTO `comment` VALUES (221, 'root', 'author1', NULL, '2019-06-04 03:17:38', '恭喜您,已经通过审核', '3', '0');
INSERT INTO `comment` VALUES (222, 'root', 'test3', NULL, '2019-06-04 03:36:29', '恭喜您,已经通过审核', '3', '0');
INSERT INTO `comment` VALUES (223, '系统批处理', 'root', 61, '2019-06-04 03:39:56', '       您发布的 ===风格和的风格=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (224, '系统批处理', 'root', 64, '2019-06-04 03:39:57', '       您发布的 ===测试文章 By用户Test=====的下载链接已经失效,请及时维护', '3', '0');
INSERT INTO `comment` VALUES (225, 'root', '', 1, '2019-06-03 19:41:36', '萨的方法', '1', '0');
INSERT INTO `comment` VALUES (226, 'root', 'root', 1, '2019-06-03 19:41:42', '@root  士大夫撒旦', '1', NULL);

-- ----------------------------
-- Table structure for head
-- ----------------------------
DROP TABLE IF EXISTS `head`;
CREATE TABLE `head`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of head
-- ----------------------------
INSERT INTO `head` VALUES (1, 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEkAAABJCAIAAAD+EZyLAAAACXBIWXMAAA7EAAAOxAGVKw4bAAAA6ElEQVRoge3b0Q2CMAAGYTAu4Uiuoe7gDA5hHM8nd2AAWkPTlHB/7nskEbg0KSnF+Xf7TCvP73198LBel3fx+Gnn+9iTbUy2MSW3nTt/X5t/tz9F+s9QkzxutjHZxpTcNj+uQ/JqM/vauDVH8rjZxmQbU3Lbq');
INSERT INTO `head` VALUES (2, 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEkAAABJCAIAAAD+EZyLAAAACXBIWXMAAA7EAAAOxAGVKw4bAAAA20lEQVRoge3a0QnCQBAG4YtagT3ZjAVYhAWIvdicDRi5Y1nI/Mz3rEeGAze5uJ3f19HgcXtNfvL5uXdcwBjj1LTuEdjGZBuTbUy2MdnGZBvTpfj9vfv9+bv7+gp7kvfNNibbmJLbNs+CkGxjso3JNqbqfJufY');
INSERT INTO `head` VALUES (3, 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEkAAABJCAIAAAD+EZyLAAAACXBIWXMAAA7EAAAOxAGVKw4bAAAAyklEQVRoge3bsQ2DMBQGYYNSMRirUKTJMimyCtNlASLZsl7E/bqvRsDpFZaNWPbt1Qq896Pzyuf5qXiB1tpadN87sI3JNibbmGxjso3JNibbmGxjso0puW2ZPAvqP/MZNX9GlDw325hsY7KNyTYm25hsY0puu');
INSERT INTO `head` VALUES (4, 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEkAAABJCAIAAAD+EZyLAAAACXBIWXMAAA7EAAAOxAGVKw4bAAAA10lEQVRoge3ayw2DMBAA0ZDQQ1pJJxxSHYcc0knKSgFeS1grPrOad+QAjFayZcH0XB+3xmd5tRff31978WBDL3bf+WXOZBuTbUyV2+ZwVWXpJVSem21MtjFVbpvCc8B2+20h+WNH5bnZxmQbk21MtjHZxmQbk');
INSERT INTO `head` VALUES (5, 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEkAAABJCAIAAAD+EZyLAAAACXBIWXMAAA7EAAAOxAGVKw4bAAAA5ElEQVRoge3b0QnCMBRGYSuC0wgO4Ca+OYULdRM3cBUHcIAm0HCh5Pyc77Fo8HAhoQSX1289bayP7/bhtJ6fW/P5+eDfcSTbmGxjSm677P9oc6vtnRa9fbm4wtDhlDw325hsY0puW673d+X7vb1+/2ZdX6Ene');
INSERT INTO `head` VALUES (6, 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEkAAABJCAIAAAD+EZyLAAAACXBIWXMAAA7EAAAOxAGVKw4bAAAA4klEQVRoge3a0QnCMBhF4VTEGZxD53AWV3AFZ/LZPXxyBgdICikh2HM532MpJYcfGmu7XB6nUnm+P/XB+/XceebayU2brtC/sFLKoXMFRLYx2caU3La8bt9/r2GW5LnZxmQbU3Lb0nwOGLf2677W/8SwVfLcb');
INSERT INTO `head` VALUES (7, 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEkAAABJCAIAAAD+EZyLAAAACXBIWXMAAA7EAAAOxAGVKw4bAAAA6UlEQVRoge2ZwQ3CMBAEY0QNlMKbLnjTAVXwpgWedEEntEIBSSSfggWzmnlakZPRSjmfr12OhymU3a8/YCC6MdGNSbJbe7xv89XX+T5fPD2vnU9+hf7XLT45ZeemGxPdmCS7NfsAJLox0Y1Jstt+0L5rZ/M54');
INSERT INTO `head` VALUES (8, 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEkAAABJCAIAAAD+EZyLAAAACXBIWXMAAA7EAAAOxAGVKw4bAAAA4UlEQVRoge3awQnCQBQGYSPBfqxFvFqEJyvwaBE2ZDXeLSAbSHhs4vzMdxTNOjxI2NXh+3geJm6vz/TFVd7388J39lvrWLzuP7ONyTam5Lax+Pnl9/q1V6g/G5LnZhuTbUzJbWP9Vtu0/LL1p8jcWslzs43JN');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url地址',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (39, '/role', '角色管理');
INSERT INTO `permission` VALUES (40, '/perm', '权限管理');
INSERT INTO `permission` VALUES (41, '/user', '角色管理');
INSERT INTO `permission` VALUES (42, '/test', '测试');
INSERT INTO `permission` VALUES (47, '/allSoftware', '软件的全部管理权限');
INSERT INTO `permission` VALUES (48, '/oneSoftware', '作者本人的管理权限');
INSERT INTO `permission` VALUES (49, '/share', '动态管理');
INSERT INTO `permission` VALUES (50, '/comment', '评论权限');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '管理员');
INSERT INTO `role` VALUES (2, 'customer', '注册用户');
INSERT INTO `role` VALUES (6, 'author', '文章发布者');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rid` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 189 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (168, 1, 49);
INSERT INTO `role_permission` VALUES (169, 1, 42);
INSERT INTO `role_permission` VALUES (170, 1, 41);
INSERT INTO `role_permission` VALUES (171, 1, 40);
INSERT INTO `role_permission` VALUES (172, 1, 39);
INSERT INTO `role_permission` VALUES (173, 1, 47);
INSERT INTO `role_permission` VALUES (174, 1, 48);
INSERT INTO `role_permission` VALUES (175, 1, 50);
INSERT INTO `role_permission` VALUES (185, 6, 48);
INSERT INTO `role_permission` VALUES (186, 6, 49);
INSERT INTO `role_permission` VALUES (187, 2, 48);
INSERT INTO `role_permission` VALUES (188, 2, 49);

-- ----------------------------
-- Table structure for share
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分享表主键',
  `datetime` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联到用户表ID',
  `text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '正文',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `software_id` bigint(20) NULL DEFAULT NULL COMMENT '关联到软件表ID',
  `likenum` int(11) NULL DEFAULT NULL COMMENT '点赞数',
  `like_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录点赞者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of share
-- ----------------------------
INSERT INTO `share` VALUES (75, '2019-05-19 00:06:03', '', 'test', '这是我的动态', '', NULL, 1, 'root');

-- ----------------------------
-- Table structure for software
-- ----------------------------
DROP TABLE IF EXISTS `software`;
CREATE TABLE `software`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'soft id 主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '软件名称',
  `text` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '资源内容',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '发布者(关联到User.id)',
  `score` float(2, 1) NULL DEFAULT NULL COMMENT '评分',
  `photo` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片路径',
  `score_log` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评分人记录',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of software
-- ----------------------------
INSERT INTO `software` VALUES (1, '', '<p><a href=\"https://www.appinn.com/floccus/\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">Floccus</a><span style=\"color: rgb(68, 68, 68);\">&nbsp;是一款可以通过 Nextcloud，WebDAV 或本地文件（以及任何文件同步解决方案）跨浏览器同步书签的插件，支持 Chrome、Firefox、Kiwi 等浏览器（不支持 iOS）。@</span><a href=\"https://meta.appinn.com/u/reveanime\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">reveanime</a></p><p><img src=\"https://img3.appinn.com/images/201905/floccus.jpg!o\" alt=\"Floccus - è·¨å¹³å°æµè§å¨ãä¹¦ç­¾åæ­¥ãæä»¶ 1\"></p><p>来自<a href=\"https://meta.appinn.com/t/floccus/10161\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">发现频道</a>，原文如下：<span class=\"ql-cursor\">﻿</span></p><ul><li>项目地址:&nbsp;<a href=\"https://github.com/marcelklehr/floccus\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\"><strong>Github – Floccus</strong></a></li></ul><h2>简介</h2><p>目前 Floccus 可以使用以下同步方法：</p><ul><li><strong>WebDAV</strong>&nbsp;：目前支持 「坚果云、nextcloud / owncloud，box」 等 WebDAV 服务器</li><li><strong>Nextcloud 书签</strong>&nbsp;：利用 Nextcloud 自带的书签应用程序进行同步，可通过 Web 访问。(需要nextcloud v12 以上版本)。</li><li><strong>本地文件同步</strong>&nbsp;：利用&nbsp;<a href=\"https://github.com/TCB13/LoFloccus\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">LoFloccus&nbsp;4</a>&nbsp;软件将书签同步到本地, 再利用 Dropbox，Syncthing，rsync 等进行跨设备同步。</li></ul><p><strong>注意：</strong>&nbsp;浏览器中内置的书签同步服务可能会导致同步出现问题, 建议关闭。</p><p><img src=\"https://img3.appinn.com/meta/uploads/default/optimized/2X/b/b2ce77cfa4a6798789ce8db6ee76ec8db590357b_2_516x500.png\" alt=\"Floccus - 跨平台浏览器「书签同步」插件 2\" height=\"250\" width=\"258\"></p><p><strong>注意：</strong>&nbsp;nextcloud 中的 bookmarks_fulltextsearch 会导致其中的书签应用程序出现问题, 请不要在 nextcloud 中安装 bookmarks_fulltextsearch。</p><h2>安装</h2><h3>Chrome</h3><ul><li>谷歌扩展商店下载:&nbsp;<a href=\"https://chrome.google.com/webstore/detail/floccus-nextcloud-sync/fnaicdffflnofjppbagibeoednhnbjhg\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">Floccus&nbsp;15</a>&nbsp;;</li><li>下载&nbsp;<a href=\"https://github.com/marcelklehr/floccus/releases/\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">.crx&nbsp;2</a>&nbsp;文件, 开发者模式拖拽安装。</li></ul><h3>Firefox</h3><ul><li>火狐扩展商店下载:&nbsp;<a href=\"https://addons.mozilla.org/en-US/firefox/addon/floccus/\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">Floccus</a>;</li></ul><h3>Android</h3><p>Floccus 暂不支持&nbsp;<a href=\"https://developer.mozilla.org/en-US/Add-ons/WebExtensions/API/bookmarks#Browser_compatibility\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">Firefox for Android</a>.&nbsp;<strong>但是</strong>&nbsp;, 安卓端可以使用&nbsp;<a href=\"https://play.google.com/store/apps/details?id=com.kiwibrowser.browser\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">Kiwi Browser</a>&nbsp;来安装 Floccus 进行书签同步。( Kiwi 是真香啊 (‾◡◝) )</p><ul><li>Kiwi 浏览器 基于 Chromium Canary，自带 adblock, 夜间模式, 网页翻译 等功能改进, 支持安装包括&nbsp;<a href=\"https://chrome.google.com/webstore/detail/ublock-origin/cjpalhdlnbpafiamejdnhcphjbkeiagm\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">ublock</a>,&nbsp;<a href=\"https://chrome.google.com/webstore/detail/%E7%8C%AB%E6%8A%93/jfedfbgedapdagkghmgibemcoggfppbb\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">猫抓</a>,&nbsp;<a href=\"https://chrome.google.com/webstore/detail/dark-reader/eimadpbcbfnmbkopoojfekhnkhdbieeh\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">Dark Reader</a>,&nbsp;<a href=\"https://chrome.google.com/webstore/detail/tampermonkey/dhdgffkkebhmkfjojejmpbldmpobfkfo\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">Tampermonkey</a>&nbsp;<a href=\"https://chrome.google.com/webstore/detail/%E6%B2%99%E6%8B%89%E6%9F%A5%E8%AF%8D-%E8%81%9A%E5%90%88%E8%AF%8D%E5%85%B8%E5%88%92%E8%AF%8D%E7%BF%BB%E8%AF%91/cdonnmffkdaoajfknoeeecmchibpmkmg\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">沙拉查词</a>&nbsp;等 Chrome 扩展&nbsp;<strong>(强烈安利以上扩展)</strong>。 相比较&nbsp;<a href=\"https://play.google.com/store/apps/details?id=com.yandex.browser\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">Yandex 浏览器</a>&nbsp;而言, Kiwi 支持的扩展更多, 对扩展的支持程度也更好, 并且可以自定义主页, 自由导入导出书签。</li><li>Kiwi 项目地址:&nbsp;<a href=\"https://labs.xda-developers.com/store/app/com.kiwibrowser.browser\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">XDA – Kiwi Browser</a>, 下载地址:&nbsp;<a href=\"https://play.google.com/store/apps/details?id=com.kiwibrowser.browser\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">Play Store</a>,&nbsp;<a href=\"https://www.coolapk.com/apk/com.kiwibrowser.browser\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">酷市场</a></li></ul><h2>坚果云 + Floccus</h2><p>以坚果云的 WebDav 为例简单介绍第一种同步方法， 新建 XBEL in WebDAVA 账户，接下来就一张图，不想敲了 (ง •_•)ง</p><p><img src=\"https://img3.appinn.com/meta/uploads/default/optimized/2X/a/a9b11c391e335b2bed9b17e734146049d8bd9743_2_558x500.jpeg\" alt=\"Floccus - 跨平台浏览器「书签同步」插件 3\"></p><p>我现在电脑 Chorme, 手机 via + kiwi, 书签跨平台同步真的舒服。</p>', 'Floccus – 跨平台浏览器「书签同步」插件', 'windows', '2019-05-12 13:28:45', 1, 4.2, '<img  src=\'https://img3.appinn.com/images/201905/floccus.jpg!o\' style=\'height:inherit;width:100% \' >', 'test,root');
INSERT INTO `software` VALUES (2, '', '<p><a href=\"https://www.appinn.com/lenny-keyboard-for-iphone/\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">Lenny Keyboard</a>&nbsp;是一款 iPhone、iPad、Apple Watch 上的随机颜文字键盘，用来快速组合自己的颜文字，非常方便。@Appinn</p><p><img src=\"https://img3.appinn.com/images/201905/lenny-keyboard-1.jpg!o\" alt=\"Lenny Keyboard - 随机颜文字键盘[iPhone] 1\"></p><p>感谢 @Neurogram 的推荐。</p><p>Lenny Keyboard 能够非常方便的组合颜文字，就像下面视频里这样的：</p><iframe class=\"ql-video\" frameborder=\"0\" allowfullscreen=\"true\" src=\"https://player.bilibili.com/player.html?aid=52006746&amp;cid=91047773&amp;page=1\"></iframe><p><br></p><p>就像，你没有办法用语言描述颜文字一样，看了上面的视频，也不好描述 Lenny Keyboard…</p><p>你只需要随便选择几个特殊符号，然后 Lenny Keyboard 就帮你组成有趣的颜文字了：</p><p><img src=\"https://img3.appinn.com/images/201905/690x0w-1.jpg!o\" alt=\"Lenny Keyboard - 随机颜文字键盘[iPhone] 2\" height=\"318\" width=\"345\"></p><p>Lenny Keyboard 在&nbsp;<a href=\"https://itunes.apple.com/cn/app/lenny-keyboard-shrug-emoji/id1095061668#?mt=8\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">App Store</a>&nbsp;有售，价格 6 元，上次更新时间为 2018年10月。</p>', 'Lenny Keyboard – 随机颜文字键盘', 'ios', '2019-05-12 13:28:58', 1, 0.0, '<img  src=\'https://img3.appinn.com/images/201905/lenny-keyboard-1.jpg!o\' style=\'height:inherit;width:100% \' >', NULL);
INSERT INTO `software` VALUES (3, '', '<p><br></p><p><a href=\"https://www.appinn.com/tag/idm/\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">IDM</a>（全称 Internet Download Manager） 是一款非常强大的老牌 Windows 下载工具，最近一次更新在 10 天前，提供了包括自动捕获链接、站点抓取和映射服务器、多媒体下载、静默下载、国内外网盘（百度盘）下载等多个功能，并且界面无广告、不弹窗，没有内置浏览器，<strong>几十年如一日的只是在做下载工具</strong>。@Appinn</p><p><img src=\"https://img3.appinn.com/images/201904/idm.jpg!o\" alt=\"最受全球用户欢迎的下载工具 IDM 6 折了[Windows] 1\"></p><p>原价 79 元的 IDM 一年授权现仅需 44 元，原价 149 的终身授权，现仅需 94 元，由数码荔枝提供，需<a href=\"https://partner.lizhi.io/appinn/idm\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">点击领取</a>小众软件 5 元专属优惠券。</p><p>IDM 的主要特点如下：</p><h2><strong>自动捕获链接</strong></h2><p>IDM 能够在使用浏览器下载文件时，自动捕获下载链接并添加下载任务。IDM 支持大部分主流浏览器，如 Chrome、Safari、Firefox、Edge、Internet Explorer 等。</p><p><img src=\"https://img3.appinn.com/images/201710/1.png!o\" alt=\"Windows 下载神器 IDM 「终身授权」优惠来了 2\"></p><h2><strong>多媒体下载</strong></h2><p>只要打开想要下载的音视频网站页面，IDM 就会自动检测在线播放器发出的多媒体请求，并在播放器上显示下载浮动条。用户可以直接下载流媒体网站中的视频，在本地离线观看。</p><p><img src=\"https://img3.appinn.com/images/201710/2.png!o\" alt=\"Windows 下载神器 IDM 「终身授权」优惠来了 3\"></p><p>IDM 支持 MP4 / MP3 / MOV / AAC 等常见音视频格式的检测与下载，在设置窗口，你还可以指定特定站点显示或隐藏软件的下载浮动条等更多自定义操作。</p><h2><strong>静默下载</strong></h2><p>大多数人在下载文件时，都会习惯性地将文件保存到某个固定位置，等下载完成后再进一步处理。如果每次下载都需要重复点选「保存对话框」中的按键，会显得非常多余和低效。</p><p><img src=\"https://img3.appinn.com/images/201710/3.jpg!o\" alt=\"Windows 下载神器 IDM 「终身授权」优惠来了 4\"></p><p>IDM 的静默下载功能能自动最小化下载窗口。如果下载过程中，希望修改保存位置或其他选项，可直接在托盘中呼出 IDM 图标即可。</p><h2><strong>百度云盘加速下载</strong></h2><p>用一个&nbsp;<a href=\"https://greasyfork.org/zh-CN/scripts/39776-%E7%99%BE%E5%BA%A6%E7%BD%91%E7%9B%98%E7%9B%B4%E6%8E%A5%E4%B8%8B%E8%BD%BD%E5%8A%A9%E6%89%8B%E4%BF%AE%E6%94%B9%E7%89%88\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">油猴脚本</a>&nbsp;配合 IDM 就能实现，非常容易。</p><p><img src=\"https://img3.appinn.com/images/201804/2018-04-2211-24-15.png!o\" alt=\"Windows 下载神器 IDM 「终身授权」优惠来了 5\"></p><h2><strong>站点抓取</strong></h2><p>「站点抓取」功能能够让你在输入链接后，直接选择要下载网页中的指定内容而不需要使用通配符，包括图片、音频、视频、文件或者包含完整样式的网站离线文件，IDM 都可以做到。你还可以根据需求自定义站点抓取的内容与规则，并将它们保存下来方便下次调用。</p><h2><strong>支持主流国外网盘</strong></h2><p>希望下载国外网盘中的资源？搭IDM&nbsp;能将网盘文件作为队列进行批量下载，让你独立定制每个队列的下载时间、下载文件数量等，灵活地提高下载效率。</p><p>目前支持的网盘包括&nbsp;RapidShare、Uploaded.net、FileServe&nbsp;等（支持登录）。可<a href=\"http://www.internetdownloadmanager.com/register/new_faq/sites_main.cgi\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">访问官网查看</a>&nbsp;所有IDM 支持网盘。</p><h2><strong>批量下载</strong></h2><p>只要使用软件默认或自定义的通配符，你就可以用 IDM 下载一个链接中所包含的所有文件，比如网页中的所有图片，类似的形式如下：</p><blockquote>https://www.appinn.com/pictures/img*.jpg</blockquote><p>使用上述命令你就可以下载 IDM 官网中名称为 img001.jpg、img002.jpg 等命名规则的所有图片。</p><h2><strong>实惠的售价</strong></h2><p>最后，IDM 的六折优惠活动，原价 79 的 IDM 一年授权现仅需 44 元，原价 149 的终生版，现仅需 94 元！一波入正的好时机～</p><p>先领小众软件专属优惠券（可多次领取），再购买，地址：<a href=\"https://partner.lizhi.io/appinn/idm\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">https://partner.lizhi.io/appinn/idm</a></p><p>两种授权均可激活一台设备，有 30 天免费试用，区别如下：</p><p><strong>一年授权</strong>：1 年使用权，1 年免费更新。</p><p><strong>永久授权</strong>：永久使用权，3 年免费更新。</p><p>如果更换电脑，只需要在旧电脑卸载 IDM，在新电脑安装并重新激活即可。</p>', '最受全球用户欢迎的下载工具 IDM 6 折了', 'android', '2019-05-12 13:30:00', 1, 0.0, '<img  src=\'https://img3.appinn.com/images/201904/idm.jpg!o\' style=\'height:inherit;width:100% \' >', '');
INSERT INTO `software` VALUES (57, '', '<p><a href=\"https://www.appinn.com/sleeptown/\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">睡眠小镇</a>&nbsp;首先是一款闹钟，其次是一款规律睡眠习惯养成工具，最后是一款组队睡觉创建世界奇观的应用，有 iPhone 与 Android 客户端。@Appinn</p><p><img src=\"https://img3.appinn.com/images/201905/sleeptown.jpg!o\" alt=\"睡眠小镇 - 找个一起睡的人来创建世界奇观 1\"></p><p>睡眠小镇的开发者也是 Forest 的开发者，<a href=\"https://www.appinn.com/forestapp/\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">Forest</a>（小众软件介绍）可以让你摆脱低头族，少用手机。</p><p>睡眠小镇用游戏的玩法来帮你养成良好的睡眠习惯，来自 Play 商店的个性化推荐，可能是觉得青小蛙最近的睡眠不太好…</p><p>首次运行睡眠小镇，需要设置想要的睡眠时间以及起床时间。每次完成任务（按时睡觉、起床），就会随机创建一栋小屋，完不成任务，建造中的小屋将被摧毁，不过没关系之前的小屋没事。</p><p><img src=\"https://img3.appinn.com/images/201905/photo_2019-05-05_10-54-52.jpg!o\" alt=\"睡眠小镇 - 找个一起睡的人来创建世界奇观 2\"></p><p>睡觉、起床前两个小时都算完成任务，但超过起床时间 10 分钟，房屋会倒塌。支持启动摇晃一段时间手机才算起床，未完成摇晃任务房屋倒塌。</p><p><img src=\"https://img3.appinn.com/images/201905/photo_2019-05-05_11-02-10.jpg!o\" alt=\"睡眠小镇 - 找个一起睡的人来创建世界奇观 3\" height=\"960\" width=\"492\"></p><p>每次达成目标都会建造一栋小屋，小屋数量与连续性决定了税收。连续 7 天能召唤「神龙」，可以让你更大几率获得稀有小屋。</p><p>每次建成的小屋，都可以根据自己的意愿摆放在小镇里，有超过 70 种房屋种类，比如商业区、游乐场、住宅之类，青小蛙找了一张来自 @<a href=\"https://www.zhihu.com/question/317989579/answer/635966360\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">池鱼游啊游</a>&nbsp;同学的截图，非常有意思：</p><p><img src=\"https://img3.appinn.com/images/201905/v2-5e8dd64ec722dac8e3e786ddd977aee3_r.jpg\" alt=\"睡眠小镇 - 找个一起睡的人来创建世界奇观 4\" height=\"640\" width=\"360\"></p><p>最后，还可以创建小圈圈，与好友一起组队睡觉（需要同样的睡眠目标，一起睡觉至少需要两个人），能…建造世界奇观。（该功能在 Android 需要内购解锁。）</p><p>睡眠城镇在&nbsp;<a href=\"https://itunes.apple.com/cn/app/id1210251567\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">App Store</a>&nbsp;售价 12 元，无内购，在&nbsp;<a href=\"https://play.google.com/store/apps/details?id=seekrtech.sleep&amp;hl=zh&amp;rdid=seekrtech.sleep\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">Google Play</a>&nbsp;和<a href=\"https://android.myapp.com/myapp/detail.htm?apkName=seekrtech.sleep\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">应用宝</a>&nbsp;免费安装。</p><p>不过一个问题是，你能找到几个和你一起睡觉的家伙？</p><p>最后，还有一段视频：</p><iframe class=\"ql-video\" frameborder=\"0\" allowfullscreen=\"true\" src=\"https://player.bilibili.com/player.html?aid=51507939&amp;cid=90157516&amp;page=1\"></iframe><p><br></p><p>收藏&nbsp;1</p>', '睡眠小镇 – 找个一起睡的人来创建世界奇观', 'ios', '2019-05-12 13:31:04', 1, 0.0, '<img  src=\'https://img3.appinn.com/images/201905/sleeptown.jpg!o\' style=\'height:inherit;width:100% \' >', NULL);
INSERT INTO `software` VALUES (58, '', '<p><a href=\"https://www.appinn.com/coloree-for-iphone/\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">Coloree</a>&nbsp;是一款 AR 取色工具，能够获取真实世界中的色彩，比较特色的是拥有 macOS 客户端并可以通过蓝牙同步颜色，也支持 Sketch 插件。@Appinn</p><p><img src=\"https://img3.appinn.com/images/201904/coloreef.jpg!o\" alt=\"Coloree - AR 取色[iPhone] 1\"></p><p>既然叫做 AR 取色，那么 Coloree 的 AR 特色在哪里呢？</p><p>看图：</p><p><img src=\"https://img3.appinn.com/images/201904/image-1-1.jpg!o\" alt=\"Coloree - AR 取色[iPhone] 2\" height=\"866\" width=\"400\"></p><p>当开启 AR 模式的时候，屏幕上会在实时位置显示历史上的颜色。但注意这张截图上的几个点并没有在原始位置…可能是距离太近的缘故…</p><p>每次选色都需要按下快门，历史记录会在快门上方的一排中，可以随时拷贝颜色代码，不过比较特色的是当你购买了 macOS 版本的客户端后，颜色会通过蓝牙同步到 Mac 中，玩法大概是下面这个样子：</p><p><img src=\"https://img3.appinn.com/images/201904/image-macbook-colore.jpg!o\" alt=\"Coloree - AR 取色[iPhone] 3\"></p><p>真香。</p><p>Coloree 官网<a href=\"http://underlama.com/coloree\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">在这里</a>，<a href=\"https://itunes.apple.com/cn/app/coloree/id1436002921?mt=8\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">App Store</a>&nbsp;地址，以及&nbsp;<a href=\"https://itunes.apple.com/cn/app/coloree/id1435299183?mt=12&amp;ign-mpt=uo%3D4\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">Mac App Store</a>&nbsp;地址</p>', 'Coloree – AR 取色', 'ios', '2019-05-12 13:31:34', 1, 0.0, '<img  src=\'https://img3.appinn.com/images/201904/coloreef.jpg!o\' style=\'height:inherit;width:100% \' >', '');
INSERT INTO `software` VALUES (59, '', '<p><a href=\"https://www.appinn.com/bitdock-for-windows/\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">BitDock</a>&nbsp;是一款 Windows 任务栏美化增强工具，模仿了经典的 macOS 程序坞 Dock 设计，可以在屏幕边缘快速启动程序，还拥有天气预报、搜索、截图、取色的小工具。@Appinn</p><p><img src=\"https://img3.appinn.com/images/201904/bitdock.jpg!o\" alt=\"BitDock - 仿程序坞 Dock 设计，让你的 Windows 像 macOS 一样 1\"></p><p>来自<a href=\"https://meta.appinn.com/t/windows-bitdock/7691\" target=\"_blank\" style=\"color: rgb(0, 122, 156);\">发现频道</a>，</p><blockquote>BitDock 是一款运行在Windows系统中的停靠栏工具，这种停靠栏很有个性，而且很适用。它让你在使用 Windows 更加的动感，而且还支持使用拖曳的方式来自定义要启动的程序，或是资料夹，内置了多款实用工具，让你自己轻松打造 一个动感味十足，界面漂亮，占用极少资源的个人专属快捷工具栏。</blockquote><p>BitDock 使用上和 Dock 无异，只需要将文件、程序、文件夹拖进去就可以固定在上面了，不过文件夹无法展开预览，点击后直接打开了。不过可以右键添加扩展菜单将多个应用放到里面。</p><p>自带的一些小工具包括：</p><ul><li>LaunchPad</li><li>天气预报</li><li>搜索</li><li>截图</li><li>取色</li><li>定时关机</li><li>桌面便签</li><li>桌面时间</li></ul><p><img src=\"https://img3.appinn.com/images/201904/2019-04-05-10-28-54.jpg!o\" alt=\"BitDock - 仿程序坞 Dock 设计，让你的 Windows 像 macOS 一样 2\"></p><p>支持自定义位置，设置大小、透明度、快捷键等等。</p><p><img src=\"https://img3.appinn.com/images/201904/2019-04-0510-34.jpg!o\" alt=\"BitDock - 仿程序坞 Dock 设计，让你的 Windows 像 macOS 一样 3\"></p><p>细节上面，距离原生 macOS 程序坞那种丝般顺滑的体验还有不小差距，比如鼠标经过不同应用图标的时候，动画效果略显生硬。这应该是系统设计上的区别，这么多年未见超越。</p>', 'BitDock – 仿程序坞 Dock 设计，让你的 Windows 像 macOS 一样', 'windows', '2019-05-12 13:32:09', 1, 0.0, '<img  src=\'https://img3.appinn.com/images/201904/bitdock.jpg!o\' style=\'height:inherit;width:100% \' >', '');
INSERT INTO `software` VALUES (60, '链接: https://pan.baidu.com/s/1bp6aY5BmfYjsTEg7x8ZUPg 提取码: mq92 复制这段内容后打开百度网盘手机App，操作更方便哦', '<p>身体微软微软为fghj</p>', '测试文章3 BY Test', 'android', '2019-06-01 02:29:14', 47, 4.5, '<img  src=\'\' style=\'height:inherit;width:100% \' >', 'test1');
INSERT INTO `software` VALUES (61, '链接: https://pan.baidu.com/s/1gfinNhh 提取码: mq92 复制这段内容后打开百度网盘手机App，操作更方便哦\r ', '<p>十大发射点</p>', '风格和的风格', 'ios', '2019-05-15 07:50:02', 1, 0.0, '<img  src=\'\' style=\'height:inherit;width:100% \' >', NULL);
INSERT INTO `software` VALUES (64, '链接: https://yunpan.360.cn/surl_yFHqU5XAuRI 提取码: mq92', '<p>这是测试内容撒旦飞洒地方</p>', '测试文章 By用户Test', 'android', '2019-06-04 03:39:06', 1, 0.0, '<img  src=\'\' style=\'height:inherit;width:100% \' >', 'test');
INSERT INTO `software` VALUES (65, '', '<p>现场v现场v</p>', '测试文章2 By Test', 'android', '2019-06-01 02:28:32', 47, 3.0, '<img  src=\'\' style=\'height:inherit;width:100% \' >', 'root');
INSERT INTO `software` VALUES (66, NULL, '<p>手动阀手动阀手动阀手动阀</p>', '测试文章 1  ROOT', 'android', '2019-06-01 02:29:39', 1, 3.2, '<img  src=\'\' style=\'height:inherit;width:100% \' >', 'test,test1');
INSERT INTO `software` VALUES (67, NULL, '<p>踩踩踩踩踩踩</p>', '测试文章4 root', 'android', '2019-06-01 02:29:53', 1, 0.0, '<img  src=\'\' style=\'height:inherit;width:100% \' >', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'user表主键',
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱|登录帐号',
  `pswd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` bigint(1) NULL DEFAULT 1 COMMENT '1:有效，0:禁止登录',
  `apply` bigint(1) NULL DEFAULT 0 COMMENT '1:申请中, 0:未申请,2:已批准,3:已禁止',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', '121@qq.com', '734056f1d2605567f6c51124ec4caab8', '2019-03-12 12:55:57', '2019-06-04 03:40:56', 1, 2);
INSERT INTO `user` VALUES (47, 'test', '123412342@QQ.COM', '734056f1d2605567f6c51124ec4caab8', '2019-05-03 17:29:40', '2019-06-04 03:40:29', 1, 2);
INSERT INTO `user` VALUES (48, 'author1', '1223@qq.com', '734056f1d2605567f6c51124ec4caab8', '2019-05-03 09:34:07', '2019-06-04 03:37:27', 1, 2);
INSERT INTO `user` VALUES (49, 'test2', '1223@qq.com', '734056f1d2605567f6c51124ec4caab8', '2019-05-12 13:35:23', '2019-05-14 13:29:12', 1, 1);
INSERT INTO `user` VALUES (50, 'test3', '1223@qq.com', '734056f1d2605567f6c51124ec4caab8', '2019-05-13 03:03:41', '2019-05-13 03:05:08', 1, 2);
INSERT INTO `user` VALUES (51, 'test1', '1101732819@qq.com', '734056f1d2605567f6c51124ec4caab8', '2019-05-16 06:30:07', '2019-06-01 02:38:42', 1, 1);
INSERT INTO `user` VALUES (52, 'test12', NULL, '4c0379fae407db62fcc4124bb3cb10ad', '2019-05-29 07:19:55', '2019-06-02 07:12:25', 1, 0);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '关联表user_id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '关联表role_id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_role_user_id_fk`(`user_id`) USING BTREE,
  INDEX `user_role_role_id_fk`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (46, 47, 6);
INSERT INTO `user_role` VALUES (47, 48, 6);
INSERT INTO `user_role` VALUES (48, 49, 2);
INSERT INTO `user_role` VALUES (49, 50, 6);
INSERT INTO `user_role` VALUES (50, 51, 2);
INSERT INTO `user_role` VALUES (51, 52, 2);

SET FOREIGN_KEY_CHECKS = 1;
