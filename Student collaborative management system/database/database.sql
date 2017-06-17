	公告管理	images/leftico03.png
		manager/bulletin/list	公告列表
		manager/bulletin/category/list	公告类别
		manager/bulletin/add	发布公告
	用户管理	images/leftico02.png
		manager/user/add	添加用户
		manager/user/list	用户列表
		manager/user/role/list	角色列表
	系统管理	images/leftico04.png
		manager/system/operationRecord/list	操作记录
		manager/system/userAccessLog/list	登录记录
		manager/system/systemLog/list	系统运行日志
		manager/system/sysCategory/update	默认公告类别



 /*
Navicat MySQL Data Transfer  

Source Server         : MySQL
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : scmstest

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-02-24 10:33:56

系统默认表
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for url_method_map
-- ----------------------------
DROP TABLE IF EXISTS `url_method_map`;
CREATE TABLE `url_method_map` (
  `Modular_Name` int(11) NOT NULL,
  `modular_method_url` varchar(255) DEFAULT NULL,
  `modular_method_name` varchar(255) NOT NULL,
  PRIMARY KEY (`Modular_Name`,`modular_method_name`),
  KEY `FKC57371CE192AAB4B` (`Modular_Name`),
  CONSTRAINT `FKC57371CE192AAB4B` FOREIGN KEY (`Modular_Name`) REFERENCES `t_sys_modular` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of url_method_map
-- ----------------------------
INSERT INTO `url_method_map` VALUES ('1', 'manager/bulletin/list.do', '公告列表');
INSERT INTO `url_method_map` VALUES ('1', 'manager/bulletin/information/list.do', '消息管理');
INSERT INTO `url_method_map` VALUES ('1', 'manager/bulletin/category/list.do', '类别管理');
INSERT INTO `url_method_map` VALUES ('1', 'manager/bulletin/complaint/listUI.do', '投诉管理');
INSERT INTO `url_method_map` VALUES ('2', 'manager/user/add.do', '添加用户');
INSERT INTO `url_method_map` VALUES ('2', 'manager/user/list.do', '用户列表');
INSERT INTO `url_method_map` VALUES ('2', 'manager/user/role/list.do', '角色列表');
INSERT INTO `url_method_map` VALUES ('3', 'manager/system/operationRecord/list.do', '操作记录');
INSERT INTO `url_method_map` VALUES ('3', 'manager/system/userAccessLog/list.do', '登录记录');
INSERT INTO `url_method_map` VALUES ('3', 'manager/system/systemLog/list.do', '系统运行日志');
INSERT INTO `url_method_map` VALUES ('3', 'manager/system/sysCategory/update.do', '系统默认类别');

-- ----------------------------
-- Table structure for t_userinformation
-- ----------------------------
DROP TABLE IF EXISTS `t_userinformation`;
CREATE TABLE `t_userinformation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `UserInformation_email` varchar(100) DEFAULT NULL,
  `UserInformation_qq` varchar(100) DEFAULT NULL,
  `UserInformation_tel` varchar(100) DEFAULT NULL,
  `UserInformation_record` varchar(100) DEFAULT NULL,
  `UserInformation_headImages` varchar(255) DEFAULT NULL,
  `UserInformation_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEE231A7655B14087` (`UserInformation_user`),
  CONSTRAINT `FKEE231A7655B14087` FOREIGN KEY (`UserInformation_user`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_userinformation
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `User_name` varchar(50) DEFAULT NULL,
  `User_account` varchar(50) DEFAULT NULL,
  `User_password` varchar(50) DEFAULT NULL,
  `User_isModifyPassword` int(11) DEFAULT NULL,
  `User_isLocking` int(11) DEFAULT NULL,
  `User_isDelete` int(11) DEFAULT NULL,
  `User_date` datetime DEFAULT NULL,
  `User_role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCB5540D6484648D3` (`User_role`),
  CONSTRAINT `FKCB5540D6484648D3` FOREIGN KEY (`User_role`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', null, 'admin', 'c43c191578fd651e', '-1', '-1', '-1', '2017-02-24 10:29:59', '1');

-- ----------------------------
-- Table structure for t_temp_informationsendrole
-- ----------------------------
DROP TABLE IF EXISTS `t_temp_informationsendrole`;
CREATE TABLE `t_temp_informationsendrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `InformationSendRole_Role` int(11) DEFAULT NULL,
  `role_Id` int(11) DEFAULT NULL,
  `index` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_temp_informationsendrole
-- ----------------------------

-- ----------------------------
-- Table structure for t_temp_informationanduser
-- ----------------------------
DROP TABLE IF EXISTS `t_temp_informationanduser`;
CREATE TABLE `t_temp_informationanduser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `InformationAndUser_State` int(11) DEFAULT NULL,
  `InformationAndUser_user` int(11) DEFAULT NULL,
  `InformationAndUser_information` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_temp_informationanduser
-- ----------------------------

-- ----------------------------
-- Table structure for t_temp_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_temp_authority`;
CREATE TABLE `t_temp_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Authority_Modular` int(11) DEFAULT NULL,
  `authority_Id` int(11) DEFAULT NULL,
  `index` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE20EA3A313D3AAB9` (`Authority_Modular`),
  KEY `FKE20EA3A36AE4D400` (`authority_Id`),
  CONSTRAINT `FKE20EA3A313D3AAB9` FOREIGN KEY (`Authority_Modular`) REFERENCES `t_sys_modular` (`id`),
  CONSTRAINT `FKE20EA3A36AE4D400` FOREIGN KEY (`authority_Id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_temp_authority
-- ----------------------------
INSERT INTO `t_temp_authority` VALUES ('1', '1', '1', '0');
INSERT INTO `t_temp_authority` VALUES ('2', '2', '1', '1');
INSERT INTO `t_temp_authority` VALUES ('3', '3', '1', '2');

-- ----------------------------
-- Table structure for t_sys_useraccesslog
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_useraccesslog`;
CREATE TABLE `t_sys_useraccesslog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `AccessLog_ip` varchar(255) DEFAULT NULL,
  `AccessLog_date` datetime DEFAULT NULL,
  `AccessLog_systemInfo` varchar(255) DEFAULT NULL,
  `AccessLog_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_useraccesslog
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_operationrecord
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_operationrecord`;
CREATE TABLE `t_sys_operationrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `OperationRecord_record` varchar(255) DEFAULT NULL,
  `OperationRecord_date` datetime DEFAULT NULL,
  `OperationRecord_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_operationrecord
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_modular
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_modular`;
CREATE TABLE `t_sys_modular` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `imgUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_modular
-- ----------------------------
INSERT INTO `t_sys_modular` VALUES ('1', '公告管理', 'images/leftico03.png');
INSERT INTO `t_sys_modular` VALUES ('2', '用户管理', 'images/leftico02.png');
INSERT INTO `t_sys_modular` VALUES ('3', '系统管理', 'images/leftico04.png');

-- ----------------------------
-- Table structure for t_sys_category
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_category`;
CREATE TABLE `t_sys_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `SysCategory_state` int(11) DEFAULT NULL,
  `SysCategory_index_1` int(11) DEFAULT NULL,
  `SysCategory_index_2` int(11) DEFAULT NULL,
  `SysCategory_index_3` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7944B03B5E8D6A5F` (`SysCategory_index_3`),
  KEY `FK7944B03B5E8D6A5E` (`SysCategory_index_2`),
  KEY `FK7944B03B5E8D6A5D` (`SysCategory_index_1`),
  CONSTRAINT `FK7944B03B5E8D6A5D` FOREIGN KEY (`SysCategory_index_1`) REFERENCES `t_category` (`id`),
  CONSTRAINT `FK7944B03B5E8D6A5E` FOREIGN KEY (`SysCategory_index_2`) REFERENCES `t_category` (`id`),
  CONSTRAINT `FK7944B03B5E8D6A5F` FOREIGN KEY (`SysCategory_index_3`) REFERENCES `t_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_category
-- ----------------------------
INSERT INTO `t_sys_category` VALUES ('1', '1', '1', '2', '3');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Role_name` varchar(255) NOT NULL,
  `Role_state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '超级管理员', '1');

-- ----------------------------
-- Table structure for t_information
-- ----------------------------
DROP TABLE IF EXISTS `t_information`;
CREATE TABLE `t_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Information_Date` datetime DEFAULT NULL,
  `Information_Title` longtext,
  `Information_Content` longtext,
  `Information_State` int(11) DEFAULT NULL,
  `Information_browseInforMationCount` bigint(20) DEFAULT NULL,
  `Information_User` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC70B6DC19BFD4ABC` (`Information_User`),
  CONSTRAINT `FKC70B6DC19BFD4ABC` FOREIGN KEY (`Information_User`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_information
-- ----------------------------

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Category_Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES ('1', '学院新闻');
INSERT INTO `t_category` VALUES ('2', '通知公告');
INSERT INTO `t_category` VALUES ('3', '其他');

-- ----------------------------
-- Table structure for t_bulletin
-- ----------------------------
DROP TABLE IF EXISTS `t_bulletin`;
CREATE TABLE `t_bulletin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Bulletin_Date` datetime DEFAULT NULL,
  `Bulletin_Title` varchar(4000) DEFAULT NULL,
  `Bulletin_Content` longtext,
  `Bulletin_DeptManagerName` varchar(200) DEFAULT NULL,
  `Bulletin_Record` varchar(4000) DEFAULT NULL,
  `Bulletin_State` int(11) DEFAULT NULL,
  `Bulletin_browseInforMationCount` bigint(20) DEFAULT NULL,
  `Bulletin_User` int(11) DEFAULT NULL,
  `Bulletin_category` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6A60113269B7B863` (`Bulletin_category`),
  KEY `FK6A601132EA791B21` (`Bulletin_User`),
  CONSTRAINT `FK6A60113269B7B863` FOREIGN KEY (`Bulletin_category`) REFERENCES `t_category` (`id`),
  CONSTRAINT `FK6A601132EA791B21` FOREIGN KEY (`Bulletin_User`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bulletin
-- ----------------------------







/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : scmstest

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-03-03 14:18:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_complaint
-- ----------------------------
DROP TABLE IF EXISTS `t_complaint`;
CREATE TABLE `t_complaint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Complaint_Date` datetime DEFAULT NULL,
  `Complaint_Title` longtext,
  `problemContent_Content` varchar(200) DEFAULT NULL,
  `Complaint_Content` longtext,
  `Complaint_DeptManagerName` varchar(100) DEFAULT NULL,
  `Complaint_Record` varchar(100) DEFAULT NULL,
  `Complaint_State` varchar(255) DEFAULT NULL,
  `Complaint_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;



/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : scmstest

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-03-06 21:57:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_complaint
-- ----------------------------
DROP TABLE IF EXISTS `t_complaint`;
CREATE TABLE `t_complaint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Complaint_Date` datetime DEFAULT NULL,
  `Complaint_Title` longtext,
  `problemContent_Content` varchar(200) DEFAULT NULL,
  `Complaint_Content` longtext,
  `Complaint_DeptManagerName` varchar(100) DEFAULT NULL,
  `Complaint_Record` varchar(100) DEFAULT NULL,
  `Complaint_State` int(11) DEFAULT NULL,
  `Complaint_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;



