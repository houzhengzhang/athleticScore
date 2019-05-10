/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : athletic

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-05-10 14:02:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for adminstrator
-- ----------------------------
DROP TABLE IF EXISTS `adminstrator`;
CREATE TABLE `adminstrator` (
  `adminstratorId` varchar(32) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `roleId` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`adminstratorId`),
  KEY `adminstrator_ibfk_1` (`roleId`),
  CONSTRAINT `adminstrator_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON DELETE CASCADE ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adminstrator
-- ----------------------------
INSERT INTO `adminstrator` VALUES ('C33251F1E4CE4FF191433D619D8984C5', '123@qq.com', '123456', 'admin', '1', 'F10079D3840F4411A324B8DF7483FE3F');

-- ----------------------------
-- Table structure for athlete
-- ----------------------------
DROP TABLE IF EXISTS `athlete`;
CREATE TABLE `athlete` (
  `athleteId` varchar(32) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `roleId` varchar(32) DEFAULT NULL,
  `athleteTeamId` varchar(32) DEFAULT NULL,
  KEY `roleId` (`roleId`),
  KEY `athleteId` (`athleteId`),
  KEY `athleteTeamId` (`athleteTeamId`),
  CONSTRAINT `athlete_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON DELETE CASCADE,
  CONSTRAINT `athlete_ibfk_2` FOREIGN KEY (`athleteTeamId`) REFERENCES `athleteteam` (`athleteTeamId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of athlete
-- ----------------------------

-- ----------------------------
-- Table structure for athletecompetition
-- ----------------------------
DROP TABLE IF EXISTS `athletecompetition`;
CREATE TABLE `athletecompetition` (
  `athleteId` varchar(32) NOT NULL,
  `competitonId` varchar(32) DEFAULT NULL,
  `competitionStageId` varchar(32) DEFAULT NULL,
  `score` double(32,0) DEFAULT NULL,
  PRIMARY KEY (`athleteId`),
  KEY `competitonId` (`competitonId`),
  KEY `competitionStageId` (`competitionStageId`),
  CONSTRAINT `athletecompetition_ibfk_1` FOREIGN KEY (`athleteId`) REFERENCES `athlete` (`athleteId`) ON DELETE CASCADE,
  CONSTRAINT `athletecompetition_ibfk_2` FOREIGN KEY (`competitonId`) REFERENCES `competition` (`competitionId`) ON DELETE CASCADE,
  CONSTRAINT `athletecompetition_ibfk_3` FOREIGN KEY (`competitionStageId`) REFERENCES `competitionstage` (`competitionStageId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of athletecompetition
-- ----------------------------

-- ----------------------------
-- Table structure for athleteteam
-- ----------------------------
DROP TABLE IF EXISTS `athleteteam`;
CREATE TABLE `athleteteam` (
  `athleteTeamId` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `totalPoint` int(32) DEFAULT NULL,
  PRIMARY KEY (`athleteTeamId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of athleteteam
-- ----------------------------
INSERT INTO `athleteteam` VALUES ('4021A14ECBEF40C3B4150077A5FA9690', 'gs', '内蒙古大学', '0');

-- ----------------------------
-- Table structure for competition
-- ----------------------------
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition` (
  `competitionId` varchar(32) NOT NULL,
  `competitionStageId` varchar(32) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `fieldId` varchar(32) DEFAULT NULL,
  `startTime` datetime(6) DEFAULT NULL,
  `endTime` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`competitionId`),
  KEY `competition_ibfk_1` (`competitionStageId`),
  KEY `fieldId` (`fieldId`),
  CONSTRAINT `competition_ibfk_1` FOREIGN KEY (`competitionStageId`) REFERENCES `competitionstage` (`competitionStageId`) ON DELETE CASCADE,
  CONSTRAINT `competition_ibfk_2` FOREIGN KEY (`fieldId`) REFERENCES `competitionfield` (`fieldId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of competition
-- ----------------------------
INSERT INTO `competition` VALUES ('B1CEA16D65384C169030B16A00BC3AC6', 'B47507AE6987430E98BBE646D17350A8', '足球', 'E339641B65A049CEB7105840836D9584', '2019-01-15 11:41:52.000000', '2019-01-19 11:41:52.000000');
INSERT INTO `competition` VALUES ('C94FC7B4098B4336907B7C15003FCDAD', 'B47507AE6987430E98BBE646D17350A8', '篮球', '1534C519F87E4A1B97584CAD86AEF0D8', '2019-01-07 13:17:11.000000', '2019-01-10 13:17:11.000000');

-- ----------------------------
-- Table structure for competitionfield
-- ----------------------------
DROP TABLE IF EXISTS `competitionfield`;
CREATE TABLE `competitionfield` (
  `fieldId` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fieldId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of competitionfield
-- ----------------------------
INSERT INTO `competitionfield` VALUES ('00C3FDFAB9DE466781B982F314CEEBBD', '体育馆', '内蒙古自治区', '占有');
INSERT INTO `competitionfield` VALUES ('1534C519F87E4A1B97584CAD86AEF0D8', '内蒙古大学体育馆', '内蒙古大学', '空闲');
INSERT INTO `competitionfield` VALUES ('E339641B65A049CEB7105840836D9584', '文体馆', '内蒙古大学', '空闲');

-- ----------------------------
-- Table structure for competitionstage
-- ----------------------------
DROP TABLE IF EXISTS `competitionstage`;
CREATE TABLE `competitionstage` (
  `competitionStageId` varchar(32) NOT NULL,
  `state` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`competitionStageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of competitionstage
-- ----------------------------
INSERT INTO `competitionstage` VALUES ('B47507AE6987430E98BBE646D17350A8', '初赛');
INSERT INTO `competitionstage` VALUES ('BA28BA6C1D7D421796C39C2BF3F397F8', '决赛');

-- ----------------------------
-- Table structure for ranking
-- ----------------------------
DROP TABLE IF EXISTS `ranking`;
CREATE TABLE `ranking` (
  `rankingId` varchar(32) NOT NULL,
  `competitonId` varchar(32) DEFAULT NULL,
  `athleteId` varchar(32) NOT NULL,
  PRIMARY KEY (`rankingId`),
  KEY `competitonId` (`competitonId`),
  KEY `athleteId` (`athleteId`),
  CONSTRAINT `ranking_ibfk_1` FOREIGN KEY (`competitonId`) REFERENCES `competition` (`competitionId`) ON DELETE CASCADE,
  CONSTRAINT `ranking_ibfk_2` FOREIGN KEY (`athleteId`) REFERENCES `athlete` (`athleteId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ranking
-- ----------------------------

-- ----------------------------
-- Table structure for referee
-- ----------------------------
DROP TABLE IF EXISTS `referee`;
CREATE TABLE `referee` (
  `refereeId` varchar(32) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `roleId` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`refereeId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `referee_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of referee
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleId` varchar(32) NOT NULL,
  `roleName` varchar(128) DEFAULT NULL,
  `authority` int(11) DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  KEY `⁯roleId` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('4E495D120C40434EAF8B103DA6195E7F', '运动员', '4');
INSERT INTO `role` VALUES ('6A1DAA858C1E4EB1BB612D9939416CA4', '计分员', '2');
INSERT INTO `role` VALUES ('A5B522FF23A146DB94D1D16A6D13AD18', '裁判员', '3');
INSERT INTO `role` VALUES ('F10079D3840F4411A324B8DF7483FE3F', '管理员', '1');

-- ----------------------------
-- Table structure for scoringstaff
-- ----------------------------
DROP TABLE IF EXISTS `scoringstaff`;
CREATE TABLE `scoringstaff` (
  `sId` varchar(32) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `roleId` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`sId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `scoringstaff_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scoringstaff
-- ----------------------------
