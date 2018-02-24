CREATE DATABASE  IF NOT EXISTS `evdc_ss` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `evdc_ss`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: evdc_ss
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin_notice`
--

DROP TABLE IF EXISTS `admin_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_notice` (
  `id` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `detail` varchar(45) NOT NULL,
  `updater` int(11) NOT NULL,
  `updateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creater` int(11) NOT NULL,
  `createDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `updater-user_idx` (`updater`),
  KEY `creater-user_idx` (`creater`),
  CONSTRAINT `creater-user` FOREIGN KEY (`creater`) REFERENCES `tier_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `updater-user` FOREIGN KEY (`updater`) REFERENCES `tier_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_service`
--

DROP TABLE IF EXISTS `admin_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `assignTeamId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `service-team_idx` (`assignTeamId`),
  CONSTRAINT `service-team` FOREIGN KEY (`assignTeamId`) REFERENCES `tier_team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_shift`
--

DROP TABLE IF EXISTS `admin_shift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_shift` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `createUserId` int(11) NOT NULL,
  `updateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `shift-user_idx` (`createUserId`),
  CONSTRAINT `shift-user` FOREIGN KEY (`createUserId`) REFERENCES `tier_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_shift2_order`
--

DROP TABLE IF EXISTS `admin_shift2_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_shift2_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notes` varchar(45) DEFAULT NULL,
  `order` int(11) NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_shift2_rule`
--

DROP TABLE IF EXISTS `admin_shift2_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_shift2_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_shift2_schedule`
--

DROP TABLE IF EXISTS `admin_shift2_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_shift2_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uId` int(11) NOT NULL,
  `replaceUId` int(11) DEFAULT NULL,
  `startDate` datetime NOT NULL,
  `endDate` datetime NOT NULL,
  `isPrimary` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_shift_order`
--

DROP TABLE IF EXISTS `admin_shift_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_shift_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ruleId` int(11) NOT NULL,
  `notes` varchar(45) DEFAULT NULL,
  `order` int(11) NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ruleId_idx` (`ruleId`),
  CONSTRAINT `ruleId` FOREIGN KEY (`ruleId`) REFERENCES `admin_shift2_rule` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_shift_rule`
--

DROP TABLE IF EXISTS `admin_shift_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_shift_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shiftId` int(11) NOT NULL,
  `info` varchar(45) NOT NULL,
  `order` int(11) NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `rule-shift_idx` (`shiftId`),
  CONSTRAINT `rule-shift` FOREIGN KEY (`shiftId`) REFERENCES `admin_shift` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_shift_schedule`
--

DROP TABLE IF EXISTS `admin_shift_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_shift_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teamId` int(11) NOT NULL,
  `beginDate` date NOT NULL,
  `circle` int(11) NOT NULL,
  `shiftId` int(11) NOT NULL,
  `enable` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `teamId_UNIQUE` (`teamId`),
  KEY `schedule-shift_idx` (`shiftId`),
  KEY `schedule-team_idx` (`teamId`),
  CONSTRAINT `schedule-shift` FOREIGN KEY (`shiftId`) REFERENCES `admin_shift` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `schedule-team` FOREIGN KEY (`teamId`) REFERENCES `tier_team` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_shift_staff`
--

DROP TABLE IF EXISTS `admin_shift_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_shift_staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shiftId` int(11) NOT NULL,
  `scheduleId` int(11) NOT NULL,
  `orderOfCircle` int(11) NOT NULL,
  `orderOfDay` int(11) NOT NULL,
  `isPrimary` tinyint(4) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `staff-shift_idx` (`shiftId`),
  KEY `staff-schedule_idx` (`scheduleId`),
  KEY `staff-user_idx` (`userId`),
  CONSTRAINT `staff-schedule` FOREIGN KEY (`scheduleId`) REFERENCES `admin_shift_schedule` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `staff-shift` FOREIGN KEY (`shiftId`) REFERENCES `admin_shift` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `staff-user` FOREIGN KEY (`userId`) REFERENCES `tier_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=227 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_shift_staff_cover`
--

DROP TABLE IF EXISTS `admin_shift_staff_cover`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_shift_staff_cover` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oldUserId` int(11) DEFAULT NULL,
  `scheduleId` int(11) NOT NULL,
  `coverdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `newUserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cover-olduser_idx` (`oldUserId`),
  KEY `cover-newuser_idx` (`newUserId`),
  CONSTRAINT `cover-newuser` FOREIGN KEY (`newUserId`) REFERENCES `tier_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `cover-olduser` FOREIGN KEY (`oldUserId`) REFERENCES `tier_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `auth_authority`
--

DROP TABLE IF EXISTS `auth_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authName` varchar(45) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  `authValue` bigint(255) NOT NULL,
  `describe` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `fatherAuthId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `auth_team_role`
--

DROP TABLE IF EXISTS `auth_team_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_team_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(45) NOT NULL,
  `authValue` bigint(255) NOT NULL,
  `delete` tinyint(4) NOT NULL,
  `describe` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COMMENT='组织角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `auth_user_role`
--

DROP TABLE IF EXISTS `auth_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(45) NOT NULL COMMENT '角色名',
  `roleTeamId` int(11) NOT NULL COMMENT '角色所属组',
  `authValue` bigint(255) NOT NULL COMMENT '权限值',
  `delete` tinyint(4) NOT NULL COMMENT '是否可以删除',
  `describe` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `_idx` (`roleTeamId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='角色表，记录了每个角色所属组，以及对应的权限。';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `client_config`
--

DROP TABLE IF EXISTS `client_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_config` (
  `teamRoleId` int(11) NOT NULL,
  `ticketselectId` varchar(54) DEFAULT NULL,
  `consoleMeanId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `console_list`
--

DROP TABLE IF EXISTS `console_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `console_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `listname` varchar(45) NOT NULL,
  `value` int(11) NOT NULL,
  `father` int(11) NOT NULL,
  `path` varchar(45) NOT NULL,
  `isCustomer` varchar(45) NOT NULL DEFAULT 'false',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `email`
--

DROP TABLE IF EXISTS `email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(200) NOT NULL,
  `body` mediumtext NOT NULL,
  `from` varchar(45) NOT NULL,
  `to` text NOT NULL,
  `cc` text,
  `cdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `note` text,
  `ticketId` int(11) DEFAULT NULL,
  `delete` tinyint(4) NOT NULL,
  `emailUniqueId` varchar(200) NOT NULL,
  `fromInbox` tinyint(4) NOT NULL,
  `action` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `emailUniqueId_UNIQUE` (`emailUniqueId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `email_account`
--

DROP TABLE IF EXISTS `email_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `updateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `email_ticket`
--

DROP TABLE IF EXISTS `email_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `client` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `service` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `report_ticket_search`
--

DROP TABLE IF EXISTS `report_ticket_search`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_ticket_search` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `sql` text NOT NULL,
  `describe` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `source` varchar(45) NOT NULL,
  `title` text NOT NULL,
  `description` text NOT NULL,
  `severity` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `serviceId` int(11) NOT NULL,
  `submitDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `submitUserId` int(11) NOT NULL,
  `submitTeamId` int(11) NOT NULL,
  `assignUserId` int(11) DEFAULT NULL,
  `assignTeamId` int(11) DEFAULT NULL,
  `satisfation` int(11) DEFAULT NULL,
  `updateUserId` int(11) DEFAULT NULL,
  `updateDate` timestamp NULL DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  `customerTeamId` int(11) DEFAULT NULL,
  `resolveDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ticket-service_idx` (`serviceId`),
  KEY `ticket-submituser_idx` (`submitUserId`),
  KEY `ticket-assignuser_idx` (`assignUserId`),
  KEY `ticket-assignteam_idx` (`assignTeamId`),
  KEY `ticket-submitTeam_idx` (`submitTeamId`),
  CONSTRAINT `ticket-service` FOREIGN KEY (`serviceId`) REFERENCES `admin_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ticket-submitTeam` FOREIGN KEY (`submitTeamId`) REFERENCES `tier_team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ticket-submituser` FOREIGN KEY (`submitUserId`) REFERENCES `tier_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ticket_attachment`
--

DROP TABLE IF EXISTS `ticket_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticketId` int(11) NOT NULL,
  `messageId` int(11) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `src` text NOT NULL,
  `name` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `attachment-message_idx` (`messageId`),
  KEY `attachment-ticket_idx` (`ticketId`),
  CONSTRAINT `attachment-message` FOREIGN KEY (`messageId`) REFERENCES `ticket_message` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `attachment-ticket` FOREIGN KEY (`ticketId`) REFERENCES `ticket` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ticket_change_record`
--

DROP TABLE IF EXISTS `ticket_change_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_change_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticketId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `filed` varchar(45) NOT NULL,
  `oldValue` text,
  `newValue` text,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ticket_message`
--

DROP TABLE IF EXISTS `ticket_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticketId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `teamId` int(11) DEFAULT NULL,
  `message` text,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `scope` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ticket_subscribe_team`
--

DROP TABLE IF EXISTS `ticket_subscribe_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_subscribe_team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticketId` int(11) NOT NULL,
  `subscribeTeamId` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tier_team`
--

DROP TABLE IF EXISTS `tier_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tier_team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `role` int(11) NOT NULL,
  `companyName` varchar(45) NOT NULL,
  `code` varchar(45) NOT NULL,
  `delete` tinyint(4) DEFAULT '1',
  `description` varchar(45) NOT NULL,
  `isClient` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `team-role_idx` (`role`),
  CONSTRAINT `team-role` FOREIGN KEY (`role`) REFERENCES `auth_team_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tier_user`
--

DROP TABLE IF EXISTS `tier_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tier_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `role` int(11) NOT NULL,
  `teamId` int(11) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `loginId` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId_UNIQUE` (`loginId`),
  KEY `user-team_idx` (`teamId`),
  KEY `user-role_idx` (`role`),
  CONSTRAINT `user-role` FOREIGN KEY (`role`) REFERENCES `auth_user_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user-team` FOREIGN KEY (`teamId`) REFERENCES `tier_team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `view_customerticket`
--

DROP TABLE IF EXISTS `view_customerticket`;
/*!50001 DROP VIEW IF EXISTS `view_customerticket`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_customerticket` AS SELECT 
 1 AS `id`,
 1 AS `title`,
 1 AS `description`,
 1 AS `severity`,
 1 AS `status`,
 1 AS `serviceId`,
 1 AS `submitDate`,
 1 AS `updateDate`,
 1 AS `resolveDate`,
 1 AS `customerId`,
 1 AS `customerTeamId`,
 1 AS `serviceName`,
 1 AS `customerUserName`,
 1 AS `customerTeamName`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_on_duty_user`
--

DROP TABLE IF EXISTS `view_on_duty_user`;
/*!50001 DROP VIEW IF EXISTS `view_on_duty_user`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_on_duty_user` AS SELECT 
 1 AS `id`,
 1 AS `name`,
 1 AS `role`,
 1 AS `teamId`,
 1 AS `phone`,
 1 AS `email`,
 1 AS `password`,
 1 AS `loginId`,
 1 AS `orderOfCircle`,
 1 AS `orderOfDay`,
 1 AS `isPrimary`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_shift`
--

DROP TABLE IF EXISTS `view_shift`;
/*!50001 DROP VIEW IF EXISTS `view_shift`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_shift` AS SELECT 
 1 AS `id`,
 1 AS `name`,
 1 AS `createUser`,
 1 AS `updateDate`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_team_schedule`
--

DROP TABLE IF EXISTS `view_team_schedule`;
/*!50001 DROP VIEW IF EXISTS `view_team_schedule`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_team_schedule` AS SELECT 
 1 AS `id`,
 1 AS `name`,
 1 AS `role`,
 1 AS `companyName`,
 1 AS `code`,
 1 AS `delete`,
 1 AS `description`,
 1 AS `scheduleId`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_ticket`
--

DROP TABLE IF EXISTS `view_ticket`;
/*!50001 DROP VIEW IF EXISTS `view_ticket`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_ticket` AS SELECT 
 1 AS `id`,
 1 AS `source`,
 1 AS `title`,
 1 AS `description`,
 1 AS `severity`,
 1 AS `status`,
 1 AS `serviceId`,
 1 AS `submitDate`,
 1 AS `submitUserId`,
 1 AS `submitTeamId`,
 1 AS `assignUserId`,
 1 AS `assignTeamId`,
 1 AS `satisfation`,
 1 AS `updateUserId`,
 1 AS `updateDate`,
 1 AS `customerId`,
 1 AS `customerTeamId`,
 1 AS `resolveDate`,
 1 AS `serviceName`,
 1 AS `submitUserName`,
 1 AS `submitTeamName`,
 1 AS `assignUserName`,
 1 AS `assignTeamName`,
 1 AS `updateUserName`,
 1 AS `customerUserName`,
 1 AS `customerTeamName`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_ticketchangerecord`
--

DROP TABLE IF EXISTS `view_ticketchangerecord`;
/*!50001 DROP VIEW IF EXISTS `view_ticketchangerecord`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_ticketchangerecord` AS SELECT 
 1 AS `id`,
 1 AS `ticketId`,
 1 AS `userId`,
 1 AS `filed`,
 1 AS `oldValue`,
 1 AS `newValue`,
 1 AS `timestamp`,
 1 AS `name`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_ticketmessage`
--

DROP TABLE IF EXISTS `view_ticketmessage`;
/*!50001 DROP VIEW IF EXISTS `view_ticketmessage`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `view_ticketmessage` AS SELECT 
 1 AS `id`,
 1 AS `ticketId`,
 1 AS `userId`,
 1 AS `teamId`,
 1 AS `message`,
 1 AS `timestamp`,
 1 AS `scope`,
 1 AS `userName`,
 1 AS `teamName`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'evdc_ss'
--

--
-- Dumping routines for database 'evdc_ss'
--

--
-- Final view structure for view `view_customerticket`
--

/*!50001 DROP VIEW IF EXISTS `view_customerticket`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`EvdcTs`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `view_customerticket` AS select `a`.`id` AS `id`,`a`.`title` AS `title`,`a`.`description` AS `description`,`a`.`severity` AS `severity`,`a`.`status` AS `status`,`a`.`serviceId` AS `serviceId`,`a`.`submitDate` AS `submitDate`,`a`.`updateDate` AS `updateDate`,`a`.`resolveDate` AS `resolveDate`,`a`.`customerId` AS `customerId`,`a`.`customerTeamId` AS `customerTeamId`,`b`.`name` AS `serviceName`,`h`.`name` AS `customerUserName`,`i`.`name` AS `customerTeamName` from (((`ticket` `a` left join `admin_service` `b` on((`a`.`serviceId` = `b`.`id`))) left join `tier_user` `h` on((`a`.`customerId` = `h`.`id`))) left join `tier_team` `i` on((`a`.`customerTeamId` = `i`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_on_duty_user`
--

/*!50001 DROP VIEW IF EXISTS `view_on_duty_user`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`EvdcTs`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `view_on_duty_user` AS select `tier_user`.`id` AS `id`,`tier_user`.`name` AS `name`,`tier_user`.`role` AS `role`,`tier_user`.`teamId` AS `teamId`,`tier_user`.`phone` AS `phone`,`tier_user`.`email` AS `email`,`tier_user`.`password` AS `password`,`tier_user`.`loginId` AS `loginId`,`admin_shift_staff`.`orderOfCircle` AS `orderOfCircle`,`admin_shift_staff`.`orderOfDay` AS `orderOfDay`,`admin_shift_staff`.`isPrimary` AS `isPrimary` from (`admin_shift_staff` left join `tier_user` on((`tier_user`.`id` = `admin_shift_staff`.`userId`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_shift`
--

/*!50001 DROP VIEW IF EXISTS `view_shift`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`EvdcTs`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `view_shift` AS select `admin_shift`.`id` AS `id`,`admin_shift`.`name` AS `name`,`tier_user`.`name` AS `createUser`,`admin_shift`.`updateDate` AS `updateDate` from (`admin_shift` left join `tier_user` on((`admin_shift`.`createUserId` = `tier_user`.`id`))) group by `admin_shift`.`id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_team_schedule`
--

/*!50001 DROP VIEW IF EXISTS `view_team_schedule`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`EvdcTs`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `view_team_schedule` AS select `tier_team`.`id` AS `id`,`tier_team`.`name` AS `name`,`tier_team`.`role` AS `role`,`tier_team`.`companyName` AS `companyName`,`tier_team`.`code` AS `code`,`tier_team`.`delete` AS `delete`,`tier_team`.`description` AS `description`,`admin_shift_schedule`.`id` AS `scheduleId` from (`tier_team` left join `admin_shift_schedule` on((`tier_team`.`id` = `admin_shift_schedule`.`teamId`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_ticket`
--

/*!50001 DROP VIEW IF EXISTS `view_ticket`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`EvdcTs`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `view_ticket` AS select `a`.`id` AS `id`,`a`.`source` AS `source`,`a`.`title` AS `title`,`a`.`description` AS `description`,`a`.`severity` AS `severity`,`a`.`status` AS `status`,`a`.`serviceId` AS `serviceId`,`a`.`submitDate` AS `submitDate`,`a`.`submitUserId` AS `submitUserId`,`a`.`submitTeamId` AS `submitTeamId`,`a`.`assignUserId` AS `assignUserId`,`a`.`assignTeamId` AS `assignTeamId`,`a`.`satisfation` AS `satisfation`,`a`.`updateUserId` AS `updateUserId`,`a`.`updateDate` AS `updateDate`,`a`.`customerId` AS `customerId`,`a`.`customerTeamId` AS `customerTeamId`,`a`.`resolveDate` AS `resolveDate`,`b`.`name` AS `serviceName`,`c`.`name` AS `submitUserName`,`d`.`name` AS `submitTeamName`,`e`.`name` AS `assignUserName`,`f`.`name` AS `assignTeamName`,`g`.`name` AS `updateUserName`,`h`.`name` AS `customerUserName`,`i`.`name` AS `customerTeamName` from ((((((((`ticket` `a` left join `admin_service` `b` on((`a`.`serviceId` = `b`.`id`))) left join `tier_user` `c` on((`a`.`submitUserId` = `c`.`id`))) left join `tier_team` `d` on((`a`.`submitTeamId` = `d`.`id`))) left join `tier_user` `e` on((`a`.`assignUserId` = `e`.`id`))) left join `tier_team` `f` on((`a`.`assignTeamId` = `f`.`id`))) left join `tier_user` `g` on((`a`.`updateUserId` = `g`.`id`))) left join `tier_user` `h` on((`a`.`customerId` = `h`.`id`))) left join `tier_team` `i` on((`a`.`customerTeamId` = `i`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_ticketchangerecord`
--

/*!50001 DROP VIEW IF EXISTS `view_ticketchangerecord`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`EvdcTs`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `view_ticketchangerecord` AS select `a`.`id` AS `id`,`a`.`ticketId` AS `ticketId`,`a`.`userId` AS `userId`,`a`.`filed` AS `filed`,`a`.`oldValue` AS `oldValue`,`a`.`newValue` AS `newValue`,`a`.`timestamp` AS `timestamp`,`b`.`name` AS `name` from (`ticket_change_record` `a` left join `tier_user` `b` on((`a`.`userId` = `b`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_ticketmessage`
--

/*!50001 DROP VIEW IF EXISTS `view_ticketmessage`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`EvdcTs`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `view_ticketmessage` AS select `c`.`id` AS `id`,`c`.`ticketId` AS `ticketId`,`c`.`userId` AS `userId`,`c`.`teamId` AS `teamId`,`c`.`message` AS `message`,`c`.`timestamp` AS `timestamp`,`c`.`scope` AS `scope`,`c`.`userName` AS `userName`,`d`.`name` AS `teamName` from (((select `a`.`id` AS `id`,`a`.`ticketId` AS `ticketId`,`a`.`userId` AS `userId`,`a`.`teamId` AS `teamId`,`a`.`message` AS `message`,`a`.`timestamp` AS `timestamp`,`a`.`scope` AS `scope`,`b`.`name` AS `userName` from (`evdc_ss`.`ticket_message` `a` left join `evdc_ss`.`tier_user` `b` on((`a`.`userId` = `b`.`id`))))) `c` left join `evdc_ss`.`tier_team` `d` on((`c`.`teamId` = `d`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-24 15:47:10
