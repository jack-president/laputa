/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.5.6-rc : Database - laputa
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`laputa` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `laputa`;

/*Table structure for table `sys_element` */

DROP TABLE IF EXISTS `sys_element`;

CREATE TABLE `sys_element` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `cname` varchar(255) NOT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rkfylbx121r8oiua06h2aolst` (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `sys_element_belongto_relation_sys_permission` */

DROP TABLE IF EXISTS `sys_element_belongto_relation_sys_permission`;

CREATE TABLE `sys_element_belongto_relation_sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `sys_element_id` bigint(20) DEFAULT NULL,
  `sys_permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sys_element_id_union_sys_permission_id` (`sys_element_id`,`sys_permission_id`),
  KEY `FKkrkgdl6kpwuwgqymr5h9f7282` (`sys_permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `sys_entity` */

DROP TABLE IF EXISTS `sys_entity`;

CREATE TABLE `sys_entity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clazz_name` varchar(255) DEFAULT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `code_able` bit(1) DEFAULT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `name_able` bit(1) DEFAULT NULL,
  `page_size` int(11) DEFAULT NULL,
  `table_name` varchar(255) DEFAULT NULL,
  `tree_able` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_field` */

DROP TABLE IF EXISTS `sys_field`;

CREATE TABLE `sys_field` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `audit` bit(1) DEFAULT NULL,
  `clazz_name` varchar(255) DEFAULT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `column_name` varchar(255) DEFAULT NULL,
  `column_type_code` varchar(255) DEFAULT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `field_name` varchar(255) DEFAULT NULL,
  `index_number` int(11) DEFAULT NULL,
  `insertable` bit(1) DEFAULT NULL,
  `lengthValue` int(11) DEFAULT NULL,
  `mapped_by` varchar(255) DEFAULT NULL,
  `nest_clazz_name` varchar(255) DEFAULT NULL,
  `nullable` bit(1) DEFAULT NULL,
  `precisionValue` int(11) DEFAULT NULL,
  `relation_sys_entity_id` bigint(20) DEFAULT NULL,
  `scale` int(11) DEFAULT NULL,
  `showable` bit(1) DEFAULT NULL,
  `showwidth` int(11) DEFAULT NULL,
  `sys_entity_id` bigint(20) DEFAULT NULL,
  `temporal_type` varchar(255) DEFAULT NULL,
  `tree` bit(1) DEFAULT NULL,
  `unique_value` bit(1) DEFAULT NULL,
  `updatable` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcxs5iro3pon0qgmfqtvl0rkm8` (`relation_sys_entity_id`),
  KEY `FKi5rhbhjtw17p5va9rhmgmq4j2` (`sys_entity_id`)
) ENGINE=MyISAM AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_file` */

DROP TABLE IF EXISTS `sys_file`;

CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `cname` varchar(255) NOT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `sys_file_belongto_relation_sys_permission` */

DROP TABLE IF EXISTS `sys_file_belongto_relation_sys_permission`;

CREATE TABLE `sys_file_belongto_relation_sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `sys_file_id` bigint(20) DEFAULT NULL,
  `sys_permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sys_file_id_union_sys_permission_id` (`sys_file_id`,`sys_permission_id`),
  KEY `FK9g8lgn6ahx4ci7ygua5c77rds` (`sys_permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `sys_hello_date` */

DROP TABLE IF EXISTS `sys_hello_date`;

CREATE TABLE `sys_hello_date` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `ac_time` datetime DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `wakeup` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `sys_hello_element` */

DROP TABLE IF EXISTS `sys_hello_element`;

CREATE TABLE `sys_hello_element` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `sys_hello_menu` */

DROP TABLE IF EXISTS `sys_hello_menu`;

CREATE TABLE `sys_hello_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `cname` varchar(255) NOT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `icon_class` varchar(255) DEFAULT NULL,
  `order_value` int(11) DEFAULT NULL,
  `resources` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tly0mb8bnnxjoptk6hx5w140l` (`code`),
  KEY `FK1u2lp3hxsxvushiqr8f453y14` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `sys_hello_menu_belong_relation_sys_hello_permission` */

DROP TABLE IF EXISTS `sys_hello_menu_belong_relation_sys_hello_permission`;

CREATE TABLE `sys_hello_menu_belong_relation_sys_hello_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_hello_menu_id` bigint(20) DEFAULT NULL,
  `sys_hello_permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sys_hello_permission_id_union_sys_hello_menu_id` (`sys_hello_permission_id`,`sys_hello_menu_id`),
  KEY `FKjo6iebegv266hvxsw56ranjwh` (`sys_hello_menu_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `sys_hello_permission` */

DROP TABLE IF EXISTS `sys_hello_permission`;

CREATE TABLE `sys_hello_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `cname` varchar(255) NOT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `icon_class` varchar(255) DEFAULT NULL,
  `order_value` int(11) DEFAULT NULL,
  `resources` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tq5314uwm3dsbq5mjd8mwdg2f` (`code`),
  KEY `FK2jrf4gb0gjqi8882gxytpxnhe` (`parent_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_menu_belongto_relation_sys_permission` */

DROP TABLE IF EXISTS `sys_menu_belongto_relation_sys_permission`;

CREATE TABLE `sys_menu_belongto_relation_sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `sys_menu_id` bigint(20) DEFAULT NULL,
  `sys_permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sys_menu_id_union_sys_permission_id` (`sys_menu_id`,`sys_permission_id`),
  KEY `FK9vkp690bi4m1kmtltrbhmc6fl` (`sys_permission_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_operation` */

DROP TABLE IF EXISTS `sys_operation`;

CREATE TABLE `sys_operation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `cname` varchar(255) NOT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `prefix_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6agwic08sqje8pdur2ltvllr6` (`code`),
  KEY `FKpna2tchew81vroamv1fw6ne91` (`parent_id`)
) ENGINE=MyISAM AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_operation_belongto_relation_sys_permission` */

DROP TABLE IF EXISTS `sys_operation_belongto_relation_sys_permission`;

CREATE TABLE `sys_operation_belongto_relation_sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `sys_operation_id` bigint(20) DEFAULT NULL,
  `sys_permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sys_operation_id_union_sys_permission_id` (`sys_operation_id`,`sys_permission_id`),
  KEY `FK3bkhrrfbtxbn4lniyttfd72y5` (`sys_permission_id`)
) ENGINE=MyISAM AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `cname` varchar(255) NOT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2vm98en2ouht0v15fvef2whp4` (`code`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_permission_belongto_relation_sys_role` */

DROP TABLE IF EXISTS `sys_permission_belongto_relation_sys_role`;

CREATE TABLE `sys_permission_belongto_relation_sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `sys_permission_id` bigint(20) DEFAULT NULL,
  `sys_role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sys_permission_id_union_sys_role_id` (`sys_permission_id`,`sys_role_id`),
  KEY `FKjdc0kgo4xauu5c44h4yqrn5n2` (`sys_role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `cname` varchar(255) NOT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `inverted` bit(1) NOT NULL,
  `parent_sys_user_group_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_plpigyqwsqfn7mn66npgf9ftp` (`code`),
  KEY `FK3bea05ca9iwbgsf7fdre8ixnk` (`parent_sys_user_group_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `cname` varchar(255) NOT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `locked` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_51bvuyvihefoh4kp5syh2jpi4` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_user_belongto_relation_sys_role` */

DROP TABLE IF EXISTS `sys_user_belongto_relation_sys_role`;

CREATE TABLE `sys_user_belongto_relation_sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `sys_role_id` bigint(20) DEFAULT NULL,
  `sys_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sys_user_id_union_sys_role_id` (`sys_user_id`,`sys_role_id`),
  KEY `FKangh2v52q14hegmbw611fds0d` (`sys_role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_user_belongto_relation_sys_user_group` */

DROP TABLE IF EXISTS `sys_user_belongto_relation_sys_user_group`;

CREATE TABLE `sys_user_belongto_relation_sys_user_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `sys_user_group_id` bigint(20) DEFAULT NULL,
  `sys_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sys_user_id_union_sys_user_group_id` (`sys_user_id`,`sys_user_group_id`),
  KEY `FK7vn7hd9rhkeiwfbvnvj0hi0tu` (`sys_user_group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `sys_user_group` */

DROP TABLE IF EXISTS `sys_user_group`;

CREATE TABLE `sys_user_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `cname` varchar(255) NOT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4ulvpmlfwpsfk58miyqd0j7mr` (`code`),
  KEY `FKlr5amfoqrt4q0dsbyfqgkmuu3` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `sys_user_group_canhave_relation_sys_permission` */

DROP TABLE IF EXISTS `sys_user_group_canhave_relation_sys_permission`;

CREATE TABLE `sys_user_group_canhave_relation_sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `sys_permission_id` bigint(20) DEFAULT NULL,
  `sys_user_group_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sys_user_group_id_union_sys_permission_id` (`sys_user_group_id`,`sys_permission_id`),
  KEY `FKam1x5g5nieewi2kcn6yswncd8` (`sys_permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
