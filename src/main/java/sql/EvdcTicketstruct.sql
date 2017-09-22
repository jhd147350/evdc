-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 124.251.110.252    Database: evdc_ss
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.16.04.1-log

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
-- Dumping data for table `admin_notice`
--

LOCK TABLES `admin_notice` WRITE;
/*!40000 ALTER TABLE `admin_notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_notice` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_service`
--

LOCK TABLES `admin_service` WRITE;
/*!40000 ALTER TABLE `admin_service` DISABLE KEYS */;
INSERT INTO `admin_service` VALUES (1,'bluemix',3),(2,'iot',3),(3,'cloudant',3),(4,'evdc',3),(5,'bluebox',3),(6,'sso',3);
/*!40000 ALTER TABLE `admin_service` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_authority`
--

LOCK TABLES `auth_authority` WRITE;
/*!40000 ALTER TABLE `auth_authority` DISABLE KEYS */;
INSERT INTO `auth_authority` VALUES (1,'组织角色管理','/teamRoleManagement/teamRoleManagementPage',2,'获取组织角色页面','subModule24',0),(2,'添加组织角色','/teamRoleManagement/addTeamRole',2,NULL,'',1),(3,'修改组织角色','/teamRoleManagement/updateTeamRole',2,NULL,'',1),(4,'删除组织角色','/teamRoleManagement/deleteTeamRole',2,NULL,NULL,1),(5,'组织管理','/teamManagement/teamManagementPage',3,NULL,'subModule24',0),(6,'添加组织','/teamManagement/addTeam',3,NULL,NULL,5),(7,'修改组织','/teamManagement/updateTeam',3,NULL,NULL,5),(8,'删除组织','/teamManagement/deleteTeam',3,NULL,NULL,5),(9,'人员角色管理','/userRoleManagement/userRoleManagementPage',5,NULL,'subModule25',0),(10,'添加人员角色','/userRoleManagement/addUserRole',5,NULL,NULL,9),(11,'修改人员角色','/userRoleManagement/updateUserRole',5,NULL,NULL,9),(12,'删除人员角色','/userRoleManagement/deleteUserRole',5,NULL,NULL,9),(13,'组织人员管理','/teamUserManagement/teamUserManagementPage',7,NULL,'subModule25',0),(14,'添加组织人员','/teamUserManagement/addTeamUser',7,NULL,'',13),(15,'修改组织人员','/teamUserManagement/updateTeamUser',7,NULL,NULL,13),(16,'删除组织人员','/teamUserManagement/deleteTeamUser',7,NULL,NULL,13),(17,'工单控制台','/ticket/ticketConsole',11,NULL,NULL,0),(18,'本组订阅工单','/ticket/findAllTicketsBySubscibeTeamAndKeyword',19,NULL,'ticketFind',0),(19,'本组指派工单','/ticket/findAllTicketsByAssignTeamAndKeyword',17,NULL,'ticketFind',0),(20,'本组提交工单','/ticket/findAllTicketsBySubmitTeamAndKeyword',13,NULL,'ticketFind',0),(21,'查询所有工单','/ticket/findAllTicketsByKeyword',23,NULL,'ticketFind',0),(22,'上传工单附件','/ticket/uploadTicketFile',11,NULL,NULL,17),(23,'创建新工单','/ticket/createTicket',11,NULL,NULL,17),(24,'系统管理','',0,NULL,'mainModule',24),(25,'组织管理','',0,NULL,'mainModule',25);
/*!40000 ALTER TABLE `auth_authority` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8 COMMENT='组织角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_team_role`
--

LOCK TABLES `auth_team_role` WRITE;
/*!40000 ALTER TABLE `auth_team_role` DISABLE KEYS */;
INSERT INTO `auth_team_role` VALUES (1,'systemadmin',1,1,'系统管理员'),(2,'customer',6,1,'客户'),(3,'L1',6,1,'技术支持'),(4,'L2',6,1,'运维'),(120,'L1测试组织角色',37182145,0,'L1测试组织角色');
/*!40000 ALTER TABLE `auth_team_role` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色表，记录了每个角色所属组，以及对应的权限。';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_role`
--

LOCK TABLES `auth_user_role` WRITE;
/*!40000 ALTER TABLE `auth_user_role` DISABLE KEYS */;
INSERT INTO `auth_user_role` VALUES (1,'21vsysadmin1',1,1,1,'管理员'),(6,'admin',18,37182145,1,'');
/*!40000 ALTER TABLE `auth_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `source` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
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
  PRIMARY KEY (`id`),
  KEY `ticket-service_idx` (`serviceId`),
  KEY `ticket-submituser_idx` (`submitUserId`),
  KEY `ticket-assignuser_idx` (`assignUserId`),
  KEY `ticket-assignteam_idx` (`assignTeamId`),
  KEY `ticket-submitTeam_idx` (`submitTeamId`),
  CONSTRAINT `ticket-service` FOREIGN KEY (`serviceId`) REFERENCES `admin_service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ticket-submitTeam` FOREIGN KEY (`submitTeamId`) REFERENCES `tier_team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ticket-submituser` FOREIGN KEY (`submitUserId`) REFERENCES `tier_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (2,'web','fsd ','','Sev1','New',1,'2017-09-19 11:43:56',1,1,0,0,0,0,NULL),(3,'web','胜多负少的','','Sev1','New',1,'2017-09-19 13:10:19',1,1,0,0,0,0,NULL),(4,'web','sdfsd','','Sev1','New',1,'2017-09-19 13:27:03',1,1,0,0,0,0,NULL),(5,'web','SDFDS ','','Sev1','New',1,'2017-09-19 13:43:33',1,1,0,0,0,0,NULL),(6,'web','SDFDS','','Sev1','New',1,'2017-09-19 13:44:51',1,1,0,0,0,0,NULL),(7,'web','发生的发生的','','Sev1','New',1,'2017-09-19 14:05:40',1,1,0,0,0,0,NULL),(8,'web','第三方','','Sev1','New',1,'2017-09-19 14:08:28',1,1,0,0,0,0,NULL),(9,'web','胜多负少的','','Sev1','New',1,'2017-09-19 14:09:11',1,1,0,0,0,0,NULL),(10,'web','dsfs','sdfsd','Sev1','New',1,'2017-09-20 14:07:03',6,18,0,0,0,0,NULL),(11,'web','dfds','dsfds','Sev1','New',1,'2017-09-21 10:31:46',6,18,0,0,0,0,NULL),(12,'web','sdd','dsfsd','Sev1','New',1,'2017-09-21 10:46:46',6,18,0,0,0,0,NULL),(13,'web','工单详情测试','<p><b>工单详情测试<u><i>地方大师傅似的</i></u></b><br></p>','Sev1','New',5,'2017-09-21 13:45:52',6,18,0,0,0,0,NULL);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

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
  `src` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `src_UNIQUE` (`src`),
  KEY `attachment-message_idx` (`messageId`),
  KEY `attachment-ticket_idx` (`ticketId`),
  CONSTRAINT `attachment-message` FOREIGN KEY (`messageId`) REFERENCES `ticket_message` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `attachment-ticket` FOREIGN KEY (`ticketId`) REFERENCES `ticket` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_attachment`
--

LOCK TABLES `ticket_attachment` WRITE;
/*!40000 ALTER TABLE `ticket_attachment` DISABLE KEYS */;
INSERT INTO `ticket_attachment` VALUES (4,6,0,NULL,'20170919214448Desert.jpg','Desert.jpg'),(5,6,0,NULL,'20170919214450Koala.jpg','Koala.jpg'),(6,10,0,NULL,'201709202207021.gif','1.gif');
/*!40000 ALTER TABLE `ticket_attachment` ENABLE KEYS */;
UNLOCK TABLES;

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
  `oldValue` varchar(45) NOT NULL,
  `newValue` varchar(45) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `change-user_idx` (`userId`),
  KEY `change-ticket_idx` (`ticketId`),
  CONSTRAINT `change-ticket` FOREIGN KEY (`ticketId`) REFERENCES `ticket` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `change-user` FOREIGN KEY (`userId`) REFERENCES `tier_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_change_record`
--

LOCK TABLES `ticket_change_record` WRITE;
/*!40000 ALTER TABLE `ticket_change_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_change_record` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_message`
--

LOCK TABLES `ticket_message` WRITE;
/*!40000 ALTER TABLE `ticket_message` DISABLE KEYS */;
INSERT INTO `ticket_message` VALUES (0,NULL,NULL,NULL,NULL,'2017-09-19 13:42:33',NULL);
/*!40000 ALTER TABLE `ticket_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_subscibe_team`
--

DROP TABLE IF EXISTS `ticket_subscibe_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_subscibe_team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticketId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `subscibeTeamId` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `sycn-user_idx` (`userId`),
  KEY `sycn-team_idx` (`subscibeTeamId`),
  KEY `sycn-ticket_idx` (`ticketId`),
  CONSTRAINT `sycn-team` FOREIGN KEY (`subscibeTeamId`) REFERENCES `tier_team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sycn-ticket` FOREIGN KEY (`ticketId`) REFERENCES `ticket` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sycn-user` FOREIGN KEY (`userId`) REFERENCES `tier_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_subscibe_team`
--

LOCK TABLES `ticket_subscibe_team` WRITE;
/*!40000 ALTER TABLE `ticket_subscibe_team` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_subscibe_team` ENABLE KEYS */;
UNLOCK TABLES;

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
  PRIMARY KEY (`id`),
  KEY `team-role_idx` (`role`),
  CONSTRAINT `team-role` FOREIGN KEY (`role`) REFERENCES `auth_team_role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tier_team`
--

LOCK TABLES `tier_team` WRITE;
/*!40000 ALTER TABLE `tier_team` DISABLE KEYS */;
INSERT INTO `tier_team` VALUES (1,'21vsysamdin',1,'21v','1111',1,'21vsysamdin'),(2,'customer1',2,'customer1','2222',1,'customer1'),(3,'21vL1',3,'21v','3333',1,'21vL1'),(4,'21vL2',4,'21v','4444',1,'21vL2'),(18,'L1测试组',120,'L1测试组','L1测试组',0,'L1测试组');
/*!40000 ALTER TABLE `tier_team` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tier_user`
--

LOCK TABLES `tier_user` WRITE;
/*!40000 ALTER TABLE `tier_user` DISABLE KEYS */;
INSERT INTO `tier_user` VALUES (0,'21vdef',1,1,NULL,'def@test','123456','def@test'),(1,'21vsysadmin',1,1,NULL,'123@321','123456','123@321'),(6,'测试L1人员管理员',6,18,'123321','L1@test','123321','L1@test');
/*!40000 ALTER TABLE `tier_user` ENABLE KEYS */;
UNLOCK TABLES;

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
/*!50001 VIEW `view_shift` AS select 1 AS `id`,1 AS `name`,1 AS `createUser`,1 AS `updateDate` */;
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
/*!50001 VIEW `view_team_schedule` AS select 1 AS `id`,1 AS `name`,1 AS `role`,1 AS `companyName`,1 AS `code`,1 AS `delete`,1 AS `description`,1 AS `scheduleId` */;
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

-- Dump completed on 2017-09-22 17:35:44
