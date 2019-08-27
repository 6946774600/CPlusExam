/*
 Navicat Premium Data Transfer

 Source Server         : xiaoxiaodai
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : cplusexam

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 27/08/2019 11:27:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin_menu`;
CREATE TABLE `tb_admin_menu`  (
  `menuId` int(11) NOT NULL COMMENT '主键',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父id',
  `menuName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menuIcon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `menuUrl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单对应的url请求',
  `showIndex` int(11) NULL DEFAULT NULL COMMENT '展示顺序',
  PRIMARY KEY (`menuId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_admin_menu
-- ----------------------------
INSERT INTO `tb_admin_menu` VALUES (1, NULL, '基本信息管理', 'layui-icon-form', NULL, 1);
INSERT INTO `tb_admin_menu` VALUES (2, NULL, '考题管理', 'layui-icon-survey', NULL, 2);
INSERT INTO `tb_admin_menu` VALUES (3, NULL, '考试管理', 'layui-icon-template', NULL, 3);
INSERT INTO `tb_admin_menu` VALUES (4, NULL, '系统维护', 'layui-icon-set-sm', NULL, 4);
INSERT INTO `tb_admin_menu` VALUES (10, 1, '学生信息管理', 'layui-icon-username', 'adminMenu/studentMsg', 1);
INSERT INTO `tb_admin_menu` VALUES (11, 1, '教师信息管理', 'layui-icon-username', 'adminMenu/teacherMsg', 2);
INSERT INTO `tb_admin_menu` VALUES (12, 1, '班级信息管理', 'layui-icon-user', 'adminMenu/gradeMsg', 3);
INSERT INTO `tb_admin_menu` VALUES (13, 1, '系统角色管理', 'layui-icon-auz', 'adminMenu/shiroMsg', 4);
INSERT INTO `tb_admin_menu` VALUES (21, 2, '考题类型管理', 'layui-icon-list', 'adminMenu/itemTypeMsg', 1);
INSERT INTO `tb_admin_menu` VALUES (22, 2, '题库管理', 'layui-icon-tabs', 'adminMenu/itemListAll', 2);
INSERT INTO `tb_admin_menu` VALUES (31, 3, '试卷题型管理', 'layui-icon-app', 'adminMenu/examItemType', 1);
INSERT INTO `tb_admin_menu` VALUES (32, 3, '考试时间管理', 'layui-icon-notice', 'adminMenu/examTime', 2);
INSERT INTO `tb_admin_menu` VALUES (41, 4, '相关信息统计', 'layui-icon-console', NULL, 1);
INSERT INTO `tb_admin_menu` VALUES (4101, 41, '统计暂留1', 'layui-icon-more-vertical', NULL, 1);
INSERT INTO `tb_admin_menu` VALUES (4102, 41, '统计暂留2', 'layui-icon-more-vertical', NULL, 2);

-- ----------------------------
-- Table structure for tb_exam_model
-- ----------------------------
DROP TABLE IF EXISTS `tb_exam_model`;
CREATE TABLE `tb_exam_model`  (
  `uuid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `exam_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考卷模版名称',
  `create_time` date NULL DEFAULT NULL COMMENT '创建时间',
  `count_score` int(11) NULL DEFAULT NULL COMMENT '总分数',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_exam_model
-- ----------------------------
INSERT INTO `tb_exam_model` VALUES (4, '期中考试测试', '2019-03-09', 25);
INSERT INTO `tb_exam_model` VALUES (7, '期中考试（通用）', '2019-03-12', 120);
INSERT INTO `tb_exam_model` VALUES (8, '测试考题1', '2019-04-01', 60);
INSERT INTO `tb_exam_model` VALUES (9, '测试考试2', '2019-04-01', 100);

-- ----------------------------
-- Table structure for tb_exam_modelitem
-- ----------------------------
DROP TABLE IF EXISTS `tb_exam_modelitem`;
CREATE TABLE `tb_exam_modelitem`  (
  `uuid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `exam_id` int(11) NULL DEFAULT NULL COMMENT '考卷题型id',
  `item_typeid` int(11) NULL DEFAULT NULL COMMENT '考题类型id',
  `item_count` int(11) NULL DEFAULT NULL COMMENT '考题数量',
  `item_score` int(11) NULL DEFAULT NULL COMMENT '单个考题分数',
  `item_counts` int(11) NULL DEFAULT NULL COMMENT '考题总分数',
  `show_index` int(11) NULL DEFAULT NULL COMMENT '考题显示顺序',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_exam_modelitem
-- ----------------------------
INSERT INTO `tb_exam_modelitem` VALUES (3, 4, 1, 5, 3, 15, 1);
INSERT INTO `tb_exam_modelitem` VALUES (4, 4, 2, 2, 5, 10, 2);
INSERT INTO `tb_exam_modelitem` VALUES (9, 7, 1, 10, 5, 50, 1);
INSERT INTO `tb_exam_modelitem` VALUES (10, 7, 2, 5, 10, 50, 2);
INSERT INTO `tb_exam_modelitem` VALUES (11, 7, 3, 5, 2, 10, 3);
INSERT INTO `tb_exam_modelitem` VALUES (12, 7, 4, 5, 2, 10, 4);
INSERT INTO `tb_exam_modelitem` VALUES (13, 8, 1, 5, 3, 15, 1);
INSERT INTO `tb_exam_modelitem` VALUES (14, 8, 2, 5, 5, 25, 2);
INSERT INTO `tb_exam_modelitem` VALUES (15, 8, 3, 5, 2, 10, 3);
INSERT INTO `tb_exam_modelitem` VALUES (16, 8, 4, 5, 2, 10, 4);
INSERT INTO `tb_exam_modelitem` VALUES (17, 9, 2, 5, 10, 50, 1);
INSERT INTO `tb_exam_modelitem` VALUES (18, 9, 4, 5, 10, 50, 2);

-- ----------------------------
-- Table structure for tb_exam_time
-- ----------------------------
DROP TABLE IF EXISTS `tb_exam_time`;
CREATE TABLE `tb_exam_time`  (
  `uuid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键信息',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '试卷名称',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '考试开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '考试结束时间',
  `exam_modelid` int(11) NULL DEFAULT NULL COMMENT '考卷Id',
  `insert_time` date NULL DEFAULT NULL COMMENT '创建时间',
  `show_notice` int(11) NULL DEFAULT NULL COMMENT '是否显示公告(0-不显示 1-显示)',
  `notice_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告标题',
  `notice_msg` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_exam_time
-- ----------------------------
INSERT INTO `tb_exam_time` VALUES (1, '测试', '2019-03-11 14:51:42', '2019-03-11 16:51:49', 4, '2019-03-11', 1, '通告标题', '通告测试');
INSERT INTO `tb_exam_time` VALUES (2, '测试', '2019-03-22 09:00:00', '2019-03-22 11:30:00', 7, '2019-03-12', 1, '关于2019年上半年开展期中考试的通知', '经教务处决定，学校将于2019年3月12日，早晨9:00至11:30开展本学期的其中考试工作，望大家互相转告，积极参与。');
INSERT INTO `tb_exam_time` VALUES (3, '测试', '2019-04-01 09:00:00', '2019-04-01 11:30:00', 7, '2019-03-28', 1, '考试时间测试', '考试时间测试，不用在意');
INSERT INTO `tb_exam_time` VALUES (4, '测试', '2019-04-03 09:30:00', '2019-04-03 11:30:00', 7, '2019-04-01', 1, '考试测试', '测试考试');
INSERT INTO `tb_exam_time` VALUES (5, '测试', '2019-04-03 09:30:00', '2019-04-03 11:30:00', 8, '2019-04-01', 1, '测试1', '测试1');
INSERT INTO `tb_exam_time` VALUES (6, '测试', '2019-04-03 08:00:00', '2019-04-03 11:00:00', 4, '2019-04-01', 1, '测试2', '测试2');
INSERT INTO `tb_exam_time` VALUES (7, '2019上半年期中考试', '2019-04-02 09:00:00', '2019-04-02 09:36:00', 8, '2019-04-01', 1, '2019上半年期中考试', '2019上半年期中考试测试');

-- ----------------------------
-- Table structure for tb_examofficial_msg
-- ----------------------------
DROP TABLE IF EXISTS `tb_examofficial_msg`;
CREATE TABLE `tb_examofficial_msg`  (
  `uuid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_timeid` int(11) NULL DEFAULT NULL COMMENT '考试时间id（哪一次正式考试）',
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `exam_counts` int(11) NULL DEFAULT NULL COMMENT '考试得分',
  `exam_type` int(11) NULL DEFAULT NULL COMMENT '考卷状态（0-未开始，1-未提交，2-已提交）',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_examofficial_msg
-- ----------------------------
INSERT INTO `tb_examofficial_msg` VALUES (2, 3, 'student', 10, 2);
INSERT INTO `tb_examofficial_msg` VALUES (3, 7, 'student', 7, 2);
INSERT INTO `tb_examofficial_msg` VALUES (4, 6, 'student', 0, 0);
INSERT INTO `tb_examofficial_msg` VALUES (5, 4, 'student', 0, 0);
INSERT INTO `tb_examofficial_msg` VALUES (6, 5, 'student', 0, 0);

-- ----------------------------
-- Table structure for tb_examofficial_paper
-- ----------------------------
DROP TABLE IF EXISTS `tb_examofficial_paper`;
CREATE TABLE `tb_examofficial_paper`  (
  `uuid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `examofficial_id` int(11) NULL DEFAULT NULL COMMENT '正式考试基本信息表id',
  `item_type` int(11) NULL DEFAULT NULL COMMENT '考题类型',
  `item_id` int(11) NULL DEFAULT NULL COMMENT '考题id',
  `user_option` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户输入答案',
  `option_tof` int(11) NULL DEFAULT NULL COMMENT '答案是否正确(0-错误  1-正确)',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_examofficial_paper
-- ----------------------------
INSERT INTO `tb_examofficial_paper` VALUES (21, 2, 1, 10, 'option3', 1);
INSERT INTO `tb_examofficial_paper` VALUES (22, 2, 1, 14, 'option2', 1);
INSERT INTO `tb_examofficial_paper` VALUES (23, 2, 1, 8, 'option1', 0);
INSERT INTO `tb_examofficial_paper` VALUES (24, 2, 1, 1, 'option2', 0);
INSERT INTO `tb_examofficial_paper` VALUES (25, 2, 1, 3, 'option6', 1);
INSERT INTO `tb_examofficial_paper` VALUES (26, 2, 1, 12, 'option3', 0);
INSERT INTO `tb_examofficial_paper` VALUES (27, 2, 1, 2, 'option1', 1);
INSERT INTO `tb_examofficial_paper` VALUES (28, 2, 1, 13, 'option2', 1);
INSERT INTO `tb_examofficial_paper` VALUES (29, 2, 1, 4, 'option5', 0);
INSERT INTO `tb_examofficial_paper` VALUES (30, 2, 1, 11, 'option1', 1);
INSERT INTO `tb_examofficial_paper` VALUES (31, 2, 2, 5, 'option2,option3', 1);
INSERT INTO `tb_examofficial_paper` VALUES (32, 2, 2, 3, NULL, 0);
INSERT INTO `tb_examofficial_paper` VALUES (33, 2, 2, 2, 'option3,option4', 0);
INSERT INTO `tb_examofficial_paper` VALUES (34, 2, 2, 1, NULL, 0);
INSERT INTO `tb_examofficial_paper` VALUES (35, 2, 2, 4, NULL, 0);
INSERT INTO `tb_examofficial_paper` VALUES (36, 2, 3, 5, 'hai ', 0);
INSERT INTO `tb_examofficial_paper` VALUES (37, 2, 3, 6, NULL, 0);
INSERT INTO `tb_examofficial_paper` VALUES (38, 2, 3, 3, NULL, 0);
INSERT INTO `tb_examofficial_paper` VALUES (39, 2, 3, 7, NULL, 0);
INSERT INTO `tb_examofficial_paper` VALUES (40, 2, 3, 1, NULL, 0);
INSERT INTO `tb_examofficial_paper` VALUES (41, 2, 4, 1, 'true', 0);
INSERT INTO `tb_examofficial_paper` VALUES (42, 2, 4, 4, 'true', 1);
INSERT INTO `tb_examofficial_paper` VALUES (43, 2, 4, 3, 'true', 0);
INSERT INTO `tb_examofficial_paper` VALUES (44, 2, 4, 5, 'true', 1);
INSERT INTO `tb_examofficial_paper` VALUES (45, 2, 4, 2, 'true', 1);
INSERT INTO `tb_examofficial_paper` VALUES (46, 3, 1, 10, 'option1', 0);
INSERT INTO `tb_examofficial_paper` VALUES (47, 3, 1, 11, 'option1', 1);
INSERT INTO `tb_examofficial_paper` VALUES (48, 3, 1, 2, 'option1', 1);
INSERT INTO `tb_examofficial_paper` VALUES (49, 3, 1, 14, 'option1', 0);
INSERT INTO `tb_examofficial_paper` VALUES (50, 3, 1, 1, 'option2', 0);
INSERT INTO `tb_examofficial_paper` VALUES (51, 3, 2, 1, 'option2,option3,option4', 0);
INSERT INTO `tb_examofficial_paper` VALUES (52, 3, 2, 2, 'option2,option3', 0);
INSERT INTO `tb_examofficial_paper` VALUES (53, 3, 2, 3, 'option3,option4', 0);
INSERT INTO `tb_examofficial_paper` VALUES (54, 3, 2, 5, 'option3,option4', 0);
INSERT INTO `tb_examofficial_paper` VALUES (55, 3, 2, 4, 'option3,option4', 1);
INSERT INTO `tb_examofficial_paper` VALUES (56, 3, 3, 2, NULL, 0);
INSERT INTO `tb_examofficial_paper` VALUES (57, 3, 3, 1, '填空题', 1);
INSERT INTO `tb_examofficial_paper` VALUES (58, 3, 3, 4, NULL, 0);
INSERT INTO `tb_examofficial_paper` VALUES (59, 3, 3, 3, NULL, 0);
INSERT INTO `tb_examofficial_paper` VALUES (60, 3, 3, 6, NULL, 0);
INSERT INTO `tb_examofficial_paper` VALUES (61, 3, 4, 2, 'true', 1);
INSERT INTO `tb_examofficial_paper` VALUES (62, 3, 4, 4, 'true', 1);
INSERT INTO `tb_examofficial_paper` VALUES (63, 3, 4, 5, 'true', 1);
INSERT INTO `tb_examofficial_paper` VALUES (64, 3, 4, 3, 'true', 0);
INSERT INTO `tb_examofficial_paper` VALUES (65, 3, 4, 1, 'true', 0);

-- ----------------------------
-- Table structure for tb_examtest_itemmsg
-- ----------------------------
DROP TABLE IF EXISTS `tb_examtest_itemmsg`;
CREATE TABLE `tb_examtest_itemmsg`  (
  `uuid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `examtest_id` int(11) NULL DEFAULT NULL COMMENT '模拟考卷id',
  `item_type` int(11) NULL DEFAULT NULL COMMENT '考题类型id',
  `item_count` int(11) NULL DEFAULT NULL COMMENT '考题数量',
  `item_score` int(11) NULL DEFAULT NULL COMMENT '考题单个分数',
  `item_counts` int(11) NULL DEFAULT NULL COMMENT '考题总分数',
  `show_index` int(11) NULL DEFAULT NULL COMMENT '考题顺序',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_examtest_itemmsg
-- ----------------------------
INSERT INTO `tb_examtest_itemmsg` VALUES (1, 40, 1, 10, 3, 30, 1);
INSERT INTO `tb_examtest_itemmsg` VALUES (2, 40, 2, 5, 5, 25, 2);
INSERT INTO `tb_examtest_itemmsg` VALUES (3, 40, 3, 5, 2, 10, 3);
INSERT INTO `tb_examtest_itemmsg` VALUES (4, 40, 4, 5, 2, 10, 4);
INSERT INTO `tb_examtest_itemmsg` VALUES (5, 41, 4, 1, 2, 2, 1);
INSERT INTO `tb_examtest_itemmsg` VALUES (6, 42, 3, 1, 2, 2, 1);
INSERT INTO `tb_examtest_itemmsg` VALUES (7, 42, 4, 1, 2, 2, 2);
INSERT INTO `tb_examtest_itemmsg` VALUES (8, 43, 1, 5, 3, 15, 1);

-- ----------------------------
-- Table structure for tb_examtest_itempaper
-- ----------------------------
DROP TABLE IF EXISTS `tb_examtest_itempaper`;
CREATE TABLE `tb_examtest_itempaper`  (
  `uuid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_testid` int(11) NULL DEFAULT NULL COMMENT '模拟考试试卷id',
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `item_type` int(11) NULL DEFAULT NULL COMMENT '考题类型',
  `item_id` int(11) NULL DEFAULT NULL COMMENT '考题id',
  `user_option` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '输入答案',
  `option_tof` int(11) NULL DEFAULT NULL COMMENT '答案是否正确(0-错误  1-正确)',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_examtest_itempaper
-- ----------------------------
INSERT INTO `tb_examtest_itempaper` VALUES (1, 40, 'student', 1, 2, 'option1', 1);
INSERT INTO `tb_examtest_itempaper` VALUES (2, 40, 'student', 1, 14, 'option4', 0);
INSERT INTO `tb_examtest_itempaper` VALUES (3, 40, 'student', 1, 13, 'option1', 0);
INSERT INTO `tb_examtest_itempaper` VALUES (4, 40, 'student', 1, 3, 'option6', 1);
INSERT INTO `tb_examtest_itempaper` VALUES (5, 40, 'student', 1, 10, 'option4', 0);
INSERT INTO `tb_examtest_itempaper` VALUES (6, 40, 'student', 1, 4, 'option1', 0);
INSERT INTO `tb_examtest_itempaper` VALUES (7, 40, 'student', 1, 11, NULL, 0);
INSERT INTO `tb_examtest_itempaper` VALUES (8, 40, 'student', 1, 12, NULL, 0);
INSERT INTO `tb_examtest_itempaper` VALUES (9, 40, 'student', 1, 8, NULL, 0);
INSERT INTO `tb_examtest_itempaper` VALUES (10, 40, 'student', 1, 1, 'option3', 1);
INSERT INTO `tb_examtest_itempaper` VALUES (11, 40, 'student', 2, 1, 'option3,option4', 0);
INSERT INTO `tb_examtest_itempaper` VALUES (12, 40, 'student', 2, 3, 'option1,option2,option3,option4', 0);
INSERT INTO `tb_examtest_itempaper` VALUES (13, 40, 'student', 2, 5, 'option2,option3', 1);
INSERT INTO `tb_examtest_itempaper` VALUES (14, 40, 'student', 2, 4, NULL, 0);
INSERT INTO `tb_examtest_itempaper` VALUES (15, 40, 'student', 2, 2, 'option3,option4', 0);
INSERT INTO `tb_examtest_itempaper` VALUES (16, 40, 'student', 3, 6, '你好', 0);
INSERT INTO `tb_examtest_itempaper` VALUES (17, 40, 'student', 3, 3, '额啊', 0);
INSERT INTO `tb_examtest_itempaper` VALUES (18, 40, 'student', 3, 2, NULL, 0);
INSERT INTO `tb_examtest_itempaper` VALUES (19, 40, 'student', 3, 7, '222', 0);
INSERT INTO `tb_examtest_itempaper` VALUES (20, 40, 'student', 3, 1, '填空题', 1);
INSERT INTO `tb_examtest_itempaper` VALUES (21, 40, 'student', 4, 1, 'false', 1);
INSERT INTO `tb_examtest_itempaper` VALUES (22, 40, 'student', 4, 3, NULL, 0);
INSERT INTO `tb_examtest_itempaper` VALUES (23, 40, 'student', 4, 2, 'true', 1);
INSERT INTO `tb_examtest_itempaper` VALUES (24, 40, 'student', 4, 4, NULL, 0);
INSERT INTO `tb_examtest_itempaper` VALUES (25, 40, 'student', 4, 5, 'true', 1);
INSERT INTO `tb_examtest_itempaper` VALUES (26, 42, 'student', 3, 5, '啊', 0);
INSERT INTO `tb_examtest_itempaper` VALUES (27, 42, 'student', 4, 3, 'true', 0);
INSERT INTO `tb_examtest_itempaper` VALUES (28, 43, 'student', 1, 4, 'option5', 0);
INSERT INTO `tb_examtest_itempaper` VALUES (29, 43, 'student', 1, 10, 'option4', 0);
INSERT INTO `tb_examtest_itempaper` VALUES (30, 43, 'student', 1, 2, 'option1', 1);
INSERT INTO `tb_examtest_itempaper` VALUES (31, 43, 'student', 1, 8, NULL, 0);
INSERT INTO `tb_examtest_itempaper` VALUES (32, 43, 'student', 1, 14, NULL, 0);

-- ----------------------------
-- Table structure for tb_examtest_msg
-- ----------------------------
DROP TABLE IF EXISTS `tb_examtest_msg`;
CREATE TABLE `tb_examtest_msg`  (
  `uuid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建用户id',
  `exam_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模拟考试名称',
  `exam_time` time(0) NULL DEFAULT NULL COMMENT '考试时长',
  `create_time` date NULL DEFAULT NULL COMMENT '创建时间',
  `count_score` int(11) NULL DEFAULT NULL COMMENT '试题总分数',
  `exam_score` int(11) NULL DEFAULT NULL COMMENT '考试分数',
  `examtest_type` int(11) NULL DEFAULT NULL COMMENT '试题状态(0-未开始 1- 考试中 2- 已完成)',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_examtest_msg
-- ----------------------------
INSERT INTO `tb_examtest_msg` VALUES (40, 'student', 'cs', '00:52:48', '2019-03-20', 75, 22, 2);
INSERT INTO `tb_examtest_msg` VALUES (41, 'student', '11', '00:30:00', '2019-03-20', 2, 0, 0);
INSERT INTO `tb_examtest_msg` VALUES (42, 'student', '嗨你好', '00:52:49', '2019-03-20', 4, 0, 2);
INSERT INTO `tb_examtest_msg` VALUES (43, 'student', '单选题填充测试', '00:00:01', '2019-03-26', 15, 3, 2);

-- ----------------------------
-- Table structure for tb_item_check
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_check`;
CREATE TABLE `tb_item_check`  (
  `uuid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键,考题编号',
  `item_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考题描述',
  `item_imageId` int(11) NULL DEFAULT NULL COMMENT '考题图片',
  `option1` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项1',
  `option2` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项2',
  `option3` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项3',
  `option4` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项4',
  `option5` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项5',
  `option6` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项6',
  `item_option` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '正确答案',
  `create_time` date NULL DEFAULT NULL COMMENT '录入日期',
  `create_teacherId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入教师编号',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_item_check
-- ----------------------------
INSERT INTO `tb_item_check` VALUES (1, '多选题第一次测试，不带图片', -1, '1', '2', '3', '4', '6', '10', 'option1,option2,option3,option4,option5,option6', '2019-02-26', 'teacher');
INSERT INTO `tb_item_check` VALUES (2, '答案判断', -1, '1', '2', '3', '4', NULL, NULL, 'option1,option2', '2019-02-26', 'teacher');
INSERT INTO `tb_item_check` VALUES (3, '多选题录入', -1, '第一', '第二', '第三', '第四', NULL, NULL, 'option2,option3', '2019-02-27', 'teacher');
INSERT INTO `tb_item_check` VALUES (4, '我录入了一个带图片的多选题', 64, '第一', '第二', '第三', '第四', NULL, NULL, 'option3,option4', '2019-02-27', 'teacher');
INSERT INTO `tb_item_check` VALUES (5, '卢老师录入', -1, '1', '2', '3', '4', NULL, NULL, 'option2,option3', '2019-03-04', '20190001');

-- ----------------------------
-- Table structure for tb_item_exercise
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_exercise`;
CREATE TABLE `tb_item_exercise`  (
  `uuid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `item_type` int(11) NULL DEFAULT NULL COMMENT '考题类型',
  `item_id` int(11) NULL DEFAULT NULL COMMENT '考题id（已练习）',
  `user_option` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '输入答案',
  `option_tof` int(11) NULL DEFAULT NULL COMMENT '答案是否正确(0-错误  1 正确)',
  `create_time` date NULL DEFAULT NULL COMMENT '练习日期',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_item_exercise
-- ----------------------------
INSERT INTO `tb_item_exercise` VALUES (38, 'student', 3, 3, '12', 1, '2019-03-16');
INSERT INTO `tb_item_exercise` VALUES (39, 'student', 3, 7, '11', 0, '2019-03-16');
INSERT INTO `tb_item_exercise` VALUES (43, 'student', 4, 2, 'true', 1, '2019-03-16');
INSERT INTO `tb_item_exercise` VALUES (45, 'student', 2, 3, 'option2,option3', 1, '2019-03-18');
INSERT INTO `tb_item_exercise` VALUES (46, 'student', 1, 12, 'option1', 0, '2019-03-18');
INSERT INTO `tb_item_exercise` VALUES (47, 'student', 1, 8, 'option1', 0, '2019-03-18');

-- ----------------------------
-- Table structure for tb_item_gap
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_gap`;
CREATE TABLE `tb_item_gap`  (
  `uuid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键,考题id',
  `item_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考题描述',
  `item_imageId` int(11) NULL DEFAULT NULL COMMENT '考题图片',
  `item_option` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '正确答案',
  `create_time` date NULL DEFAULT NULL COMMENT '录入日期',
  `create_teacherId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入教师编号',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_item_gap
-- ----------------------------
INSERT INTO `tb_item_gap` VALUES (1, '这是什么题型（）', -1, '填空题', '2019-02-27', 'teacher');
INSERT INTO `tb_item_gap` VALUES (2, '第二道填空题，第一个空（），第二个空（）。', -1, '嗯捏-知道啦', '2019-03-03', 'teacher');
INSERT INTO `tb_item_gap` VALUES (3, '卢老师录入', -1, '12', '2019-03-04', '20190001');
INSERT INTO `tb_item_gap` VALUES (4, '你好吗（）', -1, '好', '2019-03-12', 'teacher');
INSERT INTO `tb_item_gap` VALUES (5, '嗨', -1, '嗨', '2019-03-12', 'teacher');
INSERT INTO `tb_item_gap` VALUES (6, '222', -1, '222', '2019-03-12', 'teacher');
INSERT INTO `tb_item_gap` VALUES (7, '111', -1, '111', '2019-03-12', 'teacher');

-- ----------------------------
-- Table structure for tb_item_judge
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_judge`;
CREATE TABLE `tb_item_judge`  (
  `uuid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键,考题id',
  `item_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考题描述',
  `item_imageId` int(11) NULL DEFAULT NULL COMMENT '考题图片',
  `item_option` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '正确答案',
  `create_time` date NULL DEFAULT NULL COMMENT '录入日期',
  `create_teacherId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入教师编号',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_item_judge
-- ----------------------------
INSERT INTO `tb_item_judge` VALUES (1, '0', -1, 'false', '2019-02-27', 'teacher');
INSERT INTO `tb_item_judge` VALUES (2, '这是一道判断题', -1, 'true', '2019-02-27', 'teacher');
INSERT INTO `tb_item_judge` VALUES (3, '卢老师录入', -1, 'false', '2019-03-04', '20190001');
INSERT INTO `tb_item_judge` VALUES (4, '我要吃长颈鹿', -1, 'true', '2019-03-12', 'teacher');
INSERT INTO `tb_item_judge` VALUES (5, '你是个傻子吧', -1, 'true', '2019-03-12', 'teacher');

-- ----------------------------
-- Table structure for tb_item_radio
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_radio`;
CREATE TABLE `tb_item_radio`  (
  `uuid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键,考题编号',
  `item_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考题描述',
  `item_imageId` int(11) NULL DEFAULT NULL COMMENT '考题图片',
  `option1` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项1',
  `option2` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项2',
  `option3` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项3',
  `option4` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项4',
  `option5` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项5',
  `option6` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项6',
  `item_option` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '正确答案',
  `create_time` date NULL DEFAULT NULL COMMENT '录入日期',
  `create_teacherId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '录入教师编号',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_item_radio
-- ----------------------------
INSERT INTO `tb_item_radio` VALUES (1, '这是页面添加的第一个单选题，请问完成于什么时候？', -1, '2019-02-24', '2019-02-25', '2019-02-26', '2019-02-27', NULL, NULL, 'option3', '2019-02-26', 'teacher');
INSERT INTO `tb_item_radio` VALUES (2, '页面录入的第二个选择题，还带了图片，你猜我上传了什么图片？', 61, '小马', '小狗', '小猴', '小丑', NULL, NULL, 'option1', '2019-02-26', 'teacher');
INSERT INTO `tb_item_radio` VALUES (3, '请问这道题一共有多少个选项？', 50, '1个', '2个', '3个', '4个', '5个', '6个', 'option6', '2019-02-26', 'teacher');
INSERT INTO `tb_item_radio` VALUES (4, '测试', 51, '1', '2', '3', '4', '5', NULL, 'option3', '2019-02-26', 'teacher');
INSERT INTO `tb_item_radio` VALUES (8, '答案判断', -1, '1', '2', NULL, NULL, NULL, NULL, 'option2', '2019-02-27', 'teacher');
INSERT INTO `tb_item_radio` VALUES (10, '单选题测试', -1, '第一', '第二', '第三', '第四', NULL, NULL, 'option3', '2019-02-27', 'teacher');
INSERT INTO `tb_item_radio` VALUES (11, 'ces', -1, '1', '2', NULL, NULL, NULL, NULL, 'option1', '2019-03-03', 'teacher');
INSERT INTO `tb_item_radio` VALUES (12, '卢老师录入', -1, '1', '2', '3', '4', NULL, NULL, 'option2', '2019-03-04', '20190001');
INSERT INTO `tb_item_radio` VALUES (13, '修改测试啊', -1, '12', '2', NULL, NULL, NULL, NULL, 'option2', '2019-03-05', 'teacher');
INSERT INTO `tb_item_radio` VALUES (14, '的士到', -1, '1', '2', '3', '4', NULL, NULL, 'option2', '2019-03-12', 'teacher');
INSERT INTO `tb_item_radio` VALUES (15, '你好吗', 68, '好', '不好', '不知道', '你管我', NULL, NULL, 'option1', '2019-05-25', 'teacher');

-- ----------------------------
-- Table structure for tb_item_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_type`;
CREATE TABLE `tb_item_type`  (
  `item_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `itemType_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考题类型名称',
  `show_index` int(11) NULL DEFAULT 1 COMMENT '是否启动（0-禁用 1-启用 默认）',
  `def_score` int(11) NULL DEFAULT NULL COMMENT '默认分值',
  `remark` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_item_type
-- ----------------------------
INSERT INTO `tb_item_type` VALUES (1, '单选题', 1, 3, '单项选择');
INSERT INTO `tb_item_type` VALUES (2, '多选题', 1, 5, '多项选择，选错一项不得分，选择不全得3分');
INSERT INTO `tb_item_type` VALUES (3, '填空题', 1, 2, '填空题');
INSERT INTO `tb_item_type` VALUES (4, '判断题', 1, 2, '判断题');

-- ----------------------------
-- Table structure for tb_student_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_student_menu`;
CREATE TABLE `tb_student_menu`  (
  `menuId` int(11) NOT NULL COMMENT '主键',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父id',
  `menuName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menuIcon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `menuUrl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单对应的url请求',
  `showIndex` int(11) NULL DEFAULT NULL COMMENT '菜单的展示顺序',
  PRIMARY KEY (`menuId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_student_menu
-- ----------------------------
INSERT INTO `tb_student_menu` VALUES (1, 0, '首页', 'layui-icon-home', 'studentMenu/studentHome', 1);
INSERT INTO `tb_student_menu` VALUES (2, 0, '题库练习', 'layui-icon-template-1', 'studentMenu/studentItem', 2);
INSERT INTO `tb_student_menu` VALUES (3, 0, '我的考试', 'layui-icon-form', NULL, 3);
INSERT INTO `tb_student_menu` VALUES (4, 0, '相关信息', 'layui-icon-chart-screen', NULL, 4);
INSERT INTO `tb_student_menu` VALUES (31, 3, '模拟考试', NULL, 'studentMenu/studentTestExam', 1);
INSERT INTO `tb_student_menu` VALUES (32, 3, '正式考试', NULL, 'studentMenu/studentOffExam', 2);

-- ----------------------------
-- Table structure for tb_system_grade
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_grade`;
CREATE TABLE `tb_system_grade`  (
  `gradeId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级主键',
  `gradeName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级名称',
  `gradeTerm` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属年级',
  `gradeMajor` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属专业',
  `teacherId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任课教师',
  `gradeRemark` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`gradeId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_system_grade
-- ----------------------------
INSERT INTO `tb_system_grade` VALUES ('20190001', '2019级软件1班', '2019级', '软件工程专业', '20190001', '这是本学期的第一个班级');
INSERT INTO `tb_system_grade` VALUES ('20190002', '2019级软件2班', '2019级', '软件工程专业', 'teacher', '这是本学期的第二个班级');

-- ----------------------------
-- Table structure for tb_system_shiro
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_shiro`;
CREATE TABLE `tb_system_shiro`  (
  `shiroId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `shiroEnName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色英文名称（用于权限控制判断）',
  `shiroCnName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色中文名称（用于角色权限显示）',
  `remark` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`shiroId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_system_shiro
-- ----------------------------
INSERT INTO `tb_system_shiro` VALUES (1, 'admin', '管理员', '系统管理员角色（进行系统用户、考题、考试等基本信息维护，查看系统的相关统计信息）');
INSERT INTO `tb_system_shiro` VALUES (2, 'teacher', '教师', '系统的教师角色（进行考题的录入，并查询所带班级学生的考试信息）');
INSERT INTO `tb_system_shiro` VALUES (3, 'student', '学生', '系统的学生角色（进行练习、模拟、正式考试）');

-- ----------------------------
-- Table structure for tb_system_uploadfile
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_uploadfile`;
CREATE TABLE `tb_system_uploadfile`  (
  `fileId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fileName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `fileUUName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件唯一名称(用于存储)',
  `filTemp` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `used` int(11) NULL DEFAULT NULL COMMENT '使用状态（0-未使用 1-使用）',
  `fileUrl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储路径',
  PRIMARY KEY (`fileId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_system_uploadfile
-- ----------------------------
INSERT INTO `tb_system_uploadfile` VALUES (1, 'GradeModel', 'bf3f6c01c6414501838bd4b46436bc7f', '.xls', 0, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (2, 'GradeModel', '2cadc405dccc404a84e0389baa54adf9', '.xls', 0, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (3, 'GradeModel', '0b23932dfa9847eb9f7d89c5e47ff425', '.xls', 0, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (4, '11111111', '56a6ed31a6fa46dab0829b96f9ba85ec', '.xls', 0, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (5, '11111111', 'e4a11190ebae45e88507c86a48cf6322', '.xls', 0, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (6, '11111111', '2015b8ae1da24788b9dea658014ee8b7', '.xls', 0, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (7, 'GradeModel', '23b5f3c201834986bd1dc88dd1aa13ff', '.xls', 0, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (8, 'GradeModel', 'a13402cef2d34d428475af6f54b71d94', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (9, '11111111', '5013423048274a9983cc167ac43576fc', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (10, 'GradeModel', '928782b88a774de2a79af05a434dba2b', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (11, 'GradeModel', '9be0451f79554496a26a0b50a211f959', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (12, 'GradeModel', 'b0f824f226294880b4e8f34afe041991', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (13, 'GradeModel', '9205dd1bc3c044829b54868ecff9c688', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (14, 'GradeModel (1)', '6d7115c10e7544c787550e5cd3cdb21f', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (15, 'GradeModel (1)', 'ce7b9eb1cc414a339b48fab09a119e21', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (16, 'GradeModel', 'aeda96162a05425a828ef6eedd7c0014', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (17, 'GradeModel', '335d026a2f2047ed94b58530093ffe32', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (18, 'teacherFileModel', 'c27701ba4f004398ad0d02cf6577175f', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (19, 'teacherFileModel', '54cef60d04e34800bf51969be7fceeac', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (20, 'teacherFileModel', 'a0c62be12ca34da6bc5f92769d29effa', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (21, 'teacherFileModel', 'c644916daccc44eca4feb21ab729eee1', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (22, 'teacherFileModel', '6a658b574e724e3393de650f29d83505', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (23, 'teacherFileModel', 'b5871a6c5c354da2ab9e7786bc2871d1', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (24, 'teacherFileModel', '7944888bffd544fa93116890aad40b6f', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (25, 'teacherFileModel', '5141463910c24bdfbedad08b0d6e1f11', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (26, 'teacherFileModel', '1cbd1d729cdf4c4e85f8b1b1bfb5fe86', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (27, 'teacherFileModel', '7f92976432a6447f83e4fa6aec943e86', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (28, 'teacherFileModel', '75d4adbb1cef4760adaadd3c94b13fc0', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (29, 'teacherFileModel', 'f79a5d3bef964ca8a24c39c6251cdefb', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (30, 'teacherFileModel', 'bdd767db69e847f590612dc84363c187', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (31, 'teacherFileModel', '5bb75d13c9aa4534bf9266d9e3f912b1', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (32, 'teacherFileModel', 'cd42476e89614d5e843f9cdfd312202c', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (33, 'teacherFileModel', '9c454f2a481149f38b085ff6dd90f46d', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (34, 'teacherFileModel', 'c8658e1962cb46b8a18af133ba3db0a7', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (35, 'teacherFileModel', '11650009580f4fb983b0d952361ee05e', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (36, 'teacherFileModel', '513239d92a654dbba374f6b9e77fe97b', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (37, 'teacherFileModel', 'a2c1a0db65554a7abfc703b7fd70097d', '.xls', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (38, 'teacherFileModel', 'd2e6026bbe934618ab52b32da8ff7da4', '.xlsx', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (39, 'teacherFileModel', '18639e1fd3734291b40566ec522bcf1e', '.xlsx', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (40, 'teacherFileModel', '9a8b635c4fa640f3912bec0611810bfd', '.xlsx', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (41, 'teacherFileModel', '5bdadee0475e461baf82e63b6711e5ec', '.xlsx', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (42, 'studentFileModel (2)', '386a9d25a839418192836b56a2cd851f', '.xlsx', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (43, 'studentFileModel (2)', '757ae328bd6241e98ae99e9a27d14325', '.xlsx', 1, 'F:/fileUpLoad/CPlusExam/execl/');
INSERT INTO `tb_system_uploadfile` VALUES (44, '小马', '30617ac7de2f45078f065c711a3ff60c', '.png', 0, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (45, '小马', 'd54e9a86a1ff4bc7a4ade7cb2b0f7072', '.png', 0, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (46, '小马', 'ba56b1b01f30491588ef6002af86bcd3', '.png', 0, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (47, '小马', '69f665feba4c4c35ac4058c727d3602c', '.png', 0, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (48, '小马', '43e5b56735ea447882d86d1919b430a6', '.png', 0, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (49, '小马', '3cabc84e7230485289eeb34d4f055eac', '.png', 1, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (50, '小马', 'a57b6beb36ca4153b6e97839cc0e32e4', '.png', 1, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (51, '小马', 'c6457f605f724109bf1391659c2b32f9', '.png', 1, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (52, '小马', '0ba0bbf18c4040beace5c5bb1a27302d', '.png', 0, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (53, '小马', 'c15ff652f3954645b2d838ecefcae551', '.png', 1, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (54, '小马', '76e5acbda50e4ba3b211763b191bafcc', '.png', 0, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (55, 'QQ图片20180408125244', 'ab15b0b7adc14f13976357797f8086be', '.jpg', 0, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (56, 'QQ图片20180408125244', '10bf3898083846af92f2002519df0245', '.jpg', 0, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (57, 'QQ图片20180408125244', 'feeaed0f4e0a438b8cb0a0144afd5a7c', '.jpg', 0, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (58, 'IMG_0014', '294ff996d72b4303b4c55477117727b7', '.JPG', 0, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (59, 'IMG_0014', 'f223046f2d8742168ede80591f044222', '.JPG', 0, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (60, 'IMG_0014', 'a61d486d9ba64b919c46a2791bc17af9', '.JPG', 0, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (61, 'IMG_0014', 'd4ddf63d5f9147a8bb84385203968681', '.JPG', 1, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (62, '小马', '558a5a48b8c74e538d2aaf029f728fa3', '.png', 0, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (63, 'IMG_0014', 'e1267835a8b14295b39ceab99549e9d0', '.JPG', 0, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (64, 'IMG_0014', 'ad2c735314a649c9b31dcd19ff522d03', '.JPG', 1, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (65, '小马', '27f53f2c5e094fa585203f1d6620c5ab', '.png', 0, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (66, 'bd996a199a526aab3967b920bdbb2c89', '73ef6b9b6a2c4ffc9792a73ffdf2ad78', '.jpg', 1, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (67, 'bd996a199a526aab3967b920bdbb2c89', '8fbb73b889c2489eb089b2b5839f1ce8', '.jpg', 0, 'F:/fileUpLoad/CPlusExam/image/');
INSERT INTO `tb_system_uploadfile` VALUES (68, 'c9ea599efd936090a5c85b150d4c320d', 'df3b0586b38b41b39a04d6761d7c6e1c', '.jpg', 1, 'F:/fileUpLoad/CPlusExam/image/');

-- ----------------------------
-- Table structure for tb_system_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_user`;
CREATE TABLE `tb_system_user`  (
  `loginName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号（登录名-主键）',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `sex` int(11) NULL DEFAULT NULL COMMENT '学生性别(0-男  1-女)',
  `toSchool` date NULL DEFAULT NULL COMMENT '入学日期',
  `shiroId` int(11) NULL DEFAULT NULL COMMENT '所属角色类型',
  `gradeId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在班级id',
  `phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`loginName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_system_user
-- ----------------------------
INSERT INTO `tb_system_user` VALUES ('20190001', '3813f284a5d569cf571ca3a63909c822', '卢老师', 0, '2019-01-01', 2, NULL, '12345678910', 'lu@163.com');
INSERT INTO `tb_system_user` VALUES ('201912010101', '45cd8744142b6e601b448381d94d410b', '卢莹', 1, '2018-09-03', 3, '20190001', '13511112222', 'luying@qq.com');
INSERT INTO `tb_system_user` VALUES ('admin', 'f6fdffe48c908deb0f4c3bd36c032e72', '超级管理员', 1, NULL, 1, NULL, '18309225505', '694677460@qq.com');
INSERT INTO `tb_system_user` VALUES ('student', '50d9482e20934ce6df0bf28941f885bc', '帆同学', 0, '2019-01-01', 3, '20190002', '18309225505', '694677460@qq.com');
INSERT INTO `tb_system_user` VALUES ('teacher', '65cff42757952893f25858c3d497202b', '韩老师', 0, '2019-01-01', 2, NULL, '18309225555', 'han@163.com');

-- ----------------------------
-- Table structure for tb_teacher_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher_menu`;
CREATE TABLE `tb_teacher_menu`  (
  `menuId` int(11) NOT NULL COMMENT '主键',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父id',
  `menuName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menuIcon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `menuUrl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单对应的url请求',
  `showIndex` int(11) NULL DEFAULT NULL COMMENT '菜单的展示顺序',
  PRIMARY KEY (`menuId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_teacher_menu
-- ----------------------------
INSERT INTO `tb_teacher_menu` VALUES (1, 0, '首页', 'layui-icon-home', 'teacherMenu/teacherHome', 1);
INSERT INTO `tb_teacher_menu` VALUES (2, 0, '考题录入', 'layui-icon-tabs', 'teacherMenu/itemInpt', 2);
INSERT INTO `tb_teacher_menu` VALUES (3, 0, '查看题库', 'layui-icon-template-1', '', 3);
INSERT INTO `tb_teacher_menu` VALUES (4, 0, '信息浏览', 'layui-icon-chart-screen', '', 4);
INSERT INTO `tb_teacher_menu` VALUES (31, 3, '我的录入', NULL, 'teacherMenu/itemListMy', 1);
INSERT INTO `tb_teacher_menu` VALUES (32, 3, '查看所有', NULL, 'teacherMenu/itemListAll', 2);
INSERT INTO `tb_teacher_menu` VALUES (41, 4, '暂留菜单', NULL, NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;
