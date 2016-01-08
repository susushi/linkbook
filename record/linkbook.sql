/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.32-log : Database - linkbook
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`linkbook` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `linkbook`;

/*Table structure for table `attachment_file` */

DROP TABLE IF EXISTS `attachment_file`;

CREATE TABLE `attachment_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '名称',
  `url` varchar(255) NOT NULL COMMENT '下载地址',
  `size` int(11) NOT NULL COMMENT '大小',
  `creator_uid` bigint(20) NOT NULL COMMENT '上传用户ID',
  `chat_team_id` int(11) NOT NULL DEFAULT '-1' COMMENT '聊天组ID',
  `group_id` int(11) NOT NULL DEFAULT '-1' COMMENT '小组ID',
  `created_time` datetime NOT NULL,
  `updated_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `attachment_file` */

/*Table structure for table `chat_content_read_record` */

DROP TABLE IF EXISTS `chat_content_read_record`;

CREATE TABLE `chat_content_read_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chat_record_id` int(11) NOT NULL,
  `uid` bigint(20) NOT NULL,
  `read_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `chat_content_read_record` */

/*Table structure for table `chat_content_record` */

DROP TABLE IF EXISTS `chat_content_record`;

CREATE TABLE `chat_content_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creator_uid` bigint(20) NOT NULL COMMENT '发送用户',
  `chat_content` varchar(255) NOT NULL COMMENT '聊天内容',
  `received_uid` bigint(20) NOT NULL DEFAULT '-1' COMMENT '内容接受用户',
  `chat_team_id` int(11) NOT NULL DEFAULT '-1' COMMENT '讨论组ID',
  `created_time` bigint(20) NOT NULL COMMENT '时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `chat_content_record` */

/*Table structure for table `chat_team` */

DROP TABLE IF EXISTS `chat_team`;

CREATE TABLE `chat_team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '名称',
  `creator_uid` bigint(20) NOT NULL COMMENT '创建用户',
  `created_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `chat_team` */

/*Table structure for table `group` */

DROP TABLE IF EXISTS `group`;

CREATE TABLE `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '小组名称',
  `creator_uid` bigint(20) NOT NULL COMMENT '创建者ID',
  `created_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `group` */

/*Table structure for table `group_topic` */

DROP TABLE IF EXISTS `group_topic`;

CREATE TABLE `group_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL COMMENT '内容',
  `group_id` int(11) NOT NULL COMMENT '小组ID',
  `attachment_id` int(11) NOT NULL DEFAULT '-1' COMMENT '附件ID，没有为-1',
  `state` int(11) DEFAULT '0' COMMENT '状态 0：正常 -1：已删除',
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `group_topic` */

/*Table structure for table `group_topic_comment` */

DROP TABLE IF EXISTS `group_topic_comment`;

CREATE TABLE `group_topic_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_topic_id` int(11) NOT NULL COMMENT '小组状态ID',
  `comment` varchar(255) NOT NULL COMMENT '评论',
  `creator_uid` bigint(20) NOT NULL COMMENT '评论用户',
  `reply_uid` bigint(20) NOT NULL DEFAULT '-1' COMMENT '回复评论用户，默认为-1',
  `state` int(11) DEFAULT '0' COMMENT '状态 0：正常 -1：已删除',
  `floor` int(11) NOT NULL COMMENT '楼层',
  `created_time` datetime NOT NULL,
  `updated_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `group_topic_comment` */

/*Table structure for table `system_message` */

DROP TABLE IF EXISTS `system_message`;

CREATE TABLE `system_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) NOT NULL COMMENT '消息内容',
  `uid` bigint(20) NOT NULL DEFAULT '-1' COMMENT '接受用户',
  `group_id` int(11) NOT NULL DEFAULT '-1' COMMENT '小组ID',
  `created_time` bigint(20) DEFAULT NULL COMMENT '时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `system_message` */

/*Table structure for table `system_message_read_record` */

DROP TABLE IF EXISTS `system_message_read_record`;

CREATE TABLE `system_message_read_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message_id` int(11) NOT NULL COMMENT '消息ID',
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `read_time` bigint(20) NOT NULL COMMENT '时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `system_message_read_record` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `nickname` varchar(20) NOT NULL COMMENT '用户昵称',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `avatar` varchar(255) NOT NULL COMMENT '用户头像',
  `state` int(11) NOT NULL DEFAULT '-1' COMMENT '状态 -1：未认证  0：认证成功',
  `last_login_time` datetime NOT NULL COMMENT '最后登录时间',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

/*Table structure for table `user_chat_team` */

DROP TABLE IF EXISTS `user_chat_team`;

CREATE TABLE `user_chat_team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chat_team_id` int(11) NOT NULL COMMENT '聊天组ID',
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `state` int(11) NOT NULL DEFAULT '-1' COMMENT '状态 -1：申请中 0：申请通过',
  `created_time` datetime NOT NULL,
  `updated_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_chat_team` */

/*Table structure for table `user_group` */

DROP TABLE IF EXISTS `user_group`;

CREATE TABLE `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL COMMENT '小组id',
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `state` int(11) NOT NULL DEFAULT '-1' COMMENT '状态 -1：申请中  0：申请通过',
  `created_time` datetime NOT NULL,
  `updated_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_group` */

/*Table structure for table `user_relationship` */

DROP TABLE IF EXISTS `user_relationship`;

CREATE TABLE `user_relationship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `local_uid` bigint(20) NOT NULL COMMENT '用户ID',
  `remote_uid` bigint(20) NOT NULL COMMENT '好友ID',
  `state` int(11) NOT NULL DEFAULT '-1' COMMENT '状态 -1：申请中  0：申请通过',
  `created_time` datetime NOT NULL,
  `updated_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_relationship` */

/*Table structure for table `user_topic` */

DROP TABLE IF EXISTS `user_topic`;

CREATE TABLE `user_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL COMMENT '内容',
  `creator_uid` bigint(20) NOT NULL COMMENT '发布用户',
  `attachment_id` int(11) NOT NULL COMMENT '附件',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '状态 0：正常 -1：已删除',
  `created_time` datetime NOT NULL,
  `updated_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_topic` */

/*Table structure for table `user_topic_comment` */

DROP TABLE IF EXISTS `user_topic_comment`;

CREATE TABLE `user_topic_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_topic_id` int(11) NOT NULL,
  `comment` varchar(255) NOT NULL COMMENT '评论',
  `creator_uid` bigint(20) NOT NULL,
  `reply_uid` bigint(20) NOT NULL,
  `floor` int(11) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '状态 0 正常  -1：已删除',
  `created_time` datetime NOT NULL,
  `updated_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_topic_comment` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
