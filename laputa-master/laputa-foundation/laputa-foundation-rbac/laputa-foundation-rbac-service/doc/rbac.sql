/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.19 : Database - laputa
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_element` */

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
  KEY `FKkrkgdl6kpwuwgqymr5h9f7282` (`sys_permission_id`),
  CONSTRAINT `FKkrkgdl6kpwuwgqymr5h9f7282` FOREIGN KEY (`sys_permission_id`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `FKsi1igg4jysct8fw13f27whr48` FOREIGN KEY (`sys_element_id`) REFERENCES `sys_element` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_element_belongto_relation_sys_permission` */

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
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8;

/*Data for the table `sys_entity` */

insert  into `sys_entity`(`id`,`clazz_name`,`cname`,`code_able`,`descript`,`name_able`,`page_size`,`table_name`,`tree_able`) values (190,'com.laputa.foundation.web.weixin.entity.WeixinBaseConfig','微信公众号基础配置','','微信公众号基础配置','',20,'weixin_base_config','\0'),(191,'com.laputa.foundation.web.weixin.entity.WeixinUser','微信用户','\0','微信用户','\0',20,'weixin_base_config','\0');

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
  KEY `FKi5rhbhjtw17p5va9rhmgmq4j2` (`sys_entity_id`),
  CONSTRAINT `FKcxs5iro3pon0qgmfqtvl0rkm8` FOREIGN KEY (`relation_sys_entity_id`) REFERENCES `sys_entity` (`id`),
  CONSTRAINT `FKi5rhbhjtw17p5va9rhmgmq4j2` FOREIGN KEY (`sys_entity_id`) REFERENCES `sys_entity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2229 DEFAULT CHARSET=utf8;

/*Data for the table `sys_field` */

insert  into `sys_field`(`id`,`audit`,`clazz_name`,`cname`,`column_name`,`column_type_code`,`descript`,`field_name`,`index_number`,`insertable`,`lengthValue`,`mapped_by`,`nest_clazz_name`,`nullable`,`precisionValue`,`relation_sys_entity_id`,`scale`,`showable`,`showwidth`,`sys_entity_id`,`temporal_type`,`tree`,`unique_value`,`updatable`) values (2196,'\0','java.lang.Long','主键','id','COLUMN','主键','id',0,'\0',255,NULL,NULL,'\0',0,NULL,0,'',180,190,NULL,'\0','\0','\0'),(2197,'\0','java.lang.String','名称','cname','COLUMN','名称','cname',1,'',255,NULL,NULL,'\0',0,NULL,0,'',180,190,NULL,'\0','\0',''),(2198,'\0','java.lang.String','编码','code','COLUMN','编码','code',2,'',255,NULL,NULL,'\0',0,NULL,0,'',180,190,NULL,'\0','',''),(2199,'\0','java.lang.String','描述','descript','COLUMN','描述','descript',3,'',255,NULL,NULL,'',0,NULL,0,'',180,190,NULL,'\0','\0',''),(2200,'\0','java.lang.String','appid','appid','COLUMN','appid','appid',4,'',255,NULL,NULL,'\0',0,NULL,0,'',180,190,NULL,'\0','',''),(2201,'\0','java.lang.String','appsecret','appsecret','COLUMN','appsecret','appsecret',5,'',255,NULL,NULL,'\0',0,NULL,0,'',180,190,NULL,'\0','\0',''),(2202,'\0','java.lang.String','token','token','COLUMN','token','token',6,'',255,NULL,NULL,'\0',0,NULL,0,'',180,190,NULL,'\0','',''),(2203,'','java.lang.String','创建用户','created_by','COLUMN','创建用户','createdBy',101,'',50,NULL,NULL,'\0',0,NULL,0,'',180,190,NULL,'\0','\0','\0'),(2204,'','java.util.Date','创建时间','created_date','COLUMN','创建时间','createdDate',102,'',255,NULL,NULL,'\0',0,NULL,0,'',180,190,'TIMESTAMP','\0','\0','\0'),(2205,'','java.lang.String','最后修改用户','last_modified_by','COLUMN','最后修改用户','lastModifiedBy',103,'',50,NULL,NULL,'',0,NULL,0,'',180,190,NULL,'\0','\0','\0'),(2206,'','java.util.Date','最后修改时间','last_modified_date','COLUMN','最后修改时间','lastModifiedDate',104,'',255,NULL,NULL,'',0,NULL,0,'',180,190,'TIMESTAMP','\0','\0','\0'),(2207,'\0','java.lang.Long','主键','id','COLUMN','主键','id',0,'\0',255,NULL,NULL,'\0',0,NULL,0,'',180,191,NULL,'\0','\0','\0'),(2208,'\0','java.lang.Integer','是否订阅','subscribe','COLUMN','是否订阅','subscribe',4,'',255,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0',''),(2209,'\0','java.lang.String','openid','openid','COLUMN','openid','openid',5,'',255,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0',''),(2210,'\0','java.lang.String','昵称','nickname','COLUMN','昵称','nickname',6,'',255,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0',''),(2211,'\0','java.lang.String','昵称表情','nickname_emoji','COLUMN','昵称表情','nicknameEmoji',7,'',255,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0',''),(2212,'\0','java.lang.Integer','性别','sex','COLUMN','性别','sex',8,'',255,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0',''),(2213,'\0','java.lang.String','语言','language','COLUMN','语言','language',9,'',255,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0',''),(2214,'\0','java.lang.String','城市','city','COLUMN','城市','city',10,'',255,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0',''),(2215,'\0','java.lang.String','省份','province','COLUMN','省份','province',11,'',255,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0',''),(2216,'\0','java.lang.String','国家','country','COLUMN','国家','country',12,'',255,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0',''),(2217,'\0','java.lang.String','头像','headimgurl','COLUMN','头像','headimgurl',13,'',255,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0',''),(2218,'\0','java.lang.Integer','订阅时间','subscribe_time','COLUMN','订阅时间','subscribeTime',14,'',255,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0',''),(2219,'\0','java.lang.String','特权','privilege','COLUMN','特权','privilege',15,'',255,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0',''),(2220,'\0','java.lang.String','unionid','unionid','COLUMN','unionid','unionid',16,'',255,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0',''),(2221,'\0','java.lang.Integer','groupid','groupid','COLUMN','groupid','groupid',17,'',255,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0',''),(2222,'\0','java.lang.String','remark','remark','COLUMN','remark','remark',18,'',255,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0',''),(2223,'\0','java.lang.String','tagidList','tagid_list','COLUMN','tagidList','tagidList',19,'',255,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0',''),(2224,'\0','java.lang.String','所属微信编码','weixin_code','COLUMN','所属微信编码','weixinCode',20,'',255,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0',''),(2225,'','java.lang.String','创建用户','created_by','COLUMN','创建用户','createdBy',101,'',50,NULL,NULL,'\0',0,NULL,0,'',180,191,NULL,'\0','\0','\0'),(2226,'','java.util.Date','创建时间','created_date','COLUMN','创建时间','createdDate',102,'',255,NULL,NULL,'\0',0,NULL,0,'',180,191,'TIMESTAMP','\0','\0','\0'),(2227,'','java.lang.String','最后修改用户','last_modified_by','COLUMN','最后修改用户','lastModifiedBy',103,'',50,NULL,NULL,'',0,NULL,0,'',180,191,NULL,'\0','\0','\0'),(2228,'','java.util.Date','最后修改时间','last_modified_date','COLUMN','最后修改时间','lastModifiedDate',104,'',255,NULL,NULL,'',0,NULL,0,'',180,191,'TIMESTAMP','\0','\0','\0');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `sys_file` */

insert  into `sys_file`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`cname`,`descript`,`path`) values (1,'admin','2017-08-18 11:38:31','admin','2017-08-18 11:38:31','定向资源','定向的资源文件','/r/t/2017/06/a'),(2,'admin','2017-08-18 11:38:55','admin','2017-08-18 11:38:55','图片资源A','图片A','/t/2017/a'),(3,'admin','2017-08-29 17:45:41','admin','2017-08-30 17:59:04','kendo资源','kendo资源文件','/static/kendo/**'),(4,'admin','2017-08-30 10:04:40','admin','2017-08-30 17:59:37','敏感静态JS文件','测试反转权限','/static/kendo/js/kendo.fx.js.js');

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
  KEY `FK9g8lgn6ahx4ci7ygua5c77rds` (`sys_permission_id`),
  CONSTRAINT `FK9g8lgn6ahx4ci7ygua5c77rds` FOREIGN KEY (`sys_permission_id`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `FKmx9n6vjgwwpm6kvioi0gtdw84` FOREIGN KEY (`sys_file_id`) REFERENCES `sys_file` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `sys_file_belongto_relation_sys_permission` */

insert  into `sys_file_belongto_relation_sys_permission`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`sys_file_id`,`sys_permission_id`) values (1,'admin','2017-08-18 11:39:13','admin','2017-08-18 11:39:13',1,2),(2,'admin','2017-08-18 11:39:19','admin','2017-08-18 11:39:19',1,1),(3,'admin','2017-08-18 11:39:19','admin','2017-08-18 11:39:19',2,1),(4,'admin','2017-08-29 17:50:51','admin','2017-08-29 17:50:51',3,5),(5,'admin','2017-08-30 10:06:30','admin','2017-08-30 10:06:30',4,4),(6,'admin','2017-09-13 17:07:38','admin','2017-09-13 17:07:38',3,6);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_hello_date` */

/*Table structure for table `sys_hello_element` */

DROP TABLE IF EXISTS `sys_hello_element`;

CREATE TABLE `sys_hello_element` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_hello_element` */

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
  KEY `FK1u2lp3hxsxvushiqr8f453y14` (`parent_id`),
  CONSTRAINT `FK1u2lp3hxsxvushiqr8f453y14` FOREIGN KEY (`parent_id`) REFERENCES `sys_hello_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_hello_menu` */

/*Table structure for table `sys_hello_menu_belong_relation_sys_hello_permission` */

DROP TABLE IF EXISTS `sys_hello_menu_belong_relation_sys_hello_permission`;

CREATE TABLE `sys_hello_menu_belong_relation_sys_hello_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_hello_menu_id` bigint(20) DEFAULT NULL,
  `sys_hello_permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sys_hello_permission_id_union_sys_hello_menu_id` (`sys_hello_permission_id`,`sys_hello_menu_id`),
  KEY `FKjo6iebegv266hvxsw56ranjwh` (`sys_hello_menu_id`),
  CONSTRAINT `FKjo6iebegv266hvxsw56ranjwh` FOREIGN KEY (`sys_hello_menu_id`) REFERENCES `sys_hello_menu` (`id`),
  CONSTRAINT `FKncq3wljf439w8dp7pycr5k699` FOREIGN KEY (`sys_hello_permission_id`) REFERENCES `sys_hello_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_hello_menu_belong_relation_sys_hello_permission` */

/*Table structure for table `sys_hello_permission` */

DROP TABLE IF EXISTS `sys_hello_permission`;

CREATE TABLE `sys_hello_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_hello_permission` */

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
  KEY `FK2jrf4gb0gjqi8882gxytpxnhe` (`parent_id`),
  CONSTRAINT `FK2jrf4gb0gjqi8882gxytpxnhe` FOREIGN KEY (`parent_id`) REFERENCES `sys_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`cname`,`descript`,`code`,`parent_id`,`icon_class`,`order_value`,`resources`) values (1,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','系统管理','','SYSMENU_RBAC_SYSTEM_MANAGER',NULL,'laptop',1,'#'),(2,'system','2017-08-09 10:03:29','admin','2017-08-18 11:28:02','安全','','SYSMENU_RBAC_SYSTEM_MANAGER_RBAC',1,'gavel',1,'#'),(3,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','用户管理','用户管理','SYSMENU_RBAC_SYSTEM_MANAGER_RBAC_USER',2,'',1,'/rbac/sysuser/list'),(4,'system','2017-08-09 10:03:29','admin','2017-08-17 11:32:05','部门管理','菜单管理','SYSMENU_RBAC_SYSTEM_MANAGER_RBAC_USERGROUP',2,'',1,'/rbac/sysusergroup/list'),(5,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','角色管理','角色管理','SYSMENU_RBAC_SYSTEM_MANAGER_RBAC_ROLE',2,'',1,'/rbac/sysrole/list'),(6,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','权限管理','角色管理','SYSMENU_RBAC_SYSTEM_MANAGER_RBAC_PERMISSION',2,'',1,'/rbac/syspermission/list'),(7,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','操作管理','操作管理','SYSMENU_RBAC_SYSTEM_MANAGER_RBAC_OPERATION',2,'',1,'/rbac/sysoperation/treeList'),(8,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','菜单管理','菜单管理','SYSMENU_RBAC_SYSTEM_MANAGER_RBAC_MENU',2,'',1,'/rbac/sysmenu/treeList'),(9,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','文件管理','文件管理','SYSMENU_RBAC_SYSTEM_MANAGER_RBAC_FILE',2,'',1,'/rbac/sysfile/list'),(10,'admin','2017-09-13 17:04:57','admin','2017-09-13 17:04:57','微信管理','微信管理','MENU_WEIXIN',NULL,'',2,'#'),(11,'admin','2017-09-13 17:11:26','admin','2017-09-13 17:11:26','公众号管理','公众号管理','MENU_WX_GZH',10,'',1,'#'),(12,'admin','2017-09-13 17:12:24','admin','2017-09-13 17:12:24','服务号管理','服务号管理','MENU_WX_FWH',11,'',1,'/weixin/weixinbaseconfig/list'),(13,'admin','2017-11-23 14:32:09','admin','2017-11-23 14:32:09','功能管理','功能管理','SYSMENU_RBAC_SYSTEM_MA',2,'',1,'	/rbac/function/list');

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
  KEY `FK9vkp690bi4m1kmtltrbhmc6fl` (`sys_permission_id`),
  CONSTRAINT `FK9vkp690bi4m1kmtltrbhmc6fl` FOREIGN KEY (`sys_permission_id`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `FKlcdcewgfn8sptk7dxs0158r17` FOREIGN KEY (`sys_menu_id`) REFERENCES `sys_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu_belongto_relation_sys_permission` */

insert  into `sys_menu_belongto_relation_sys_permission`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`sys_menu_id`,`sys_permission_id`) values (21,'admin','2017-08-18 16:47:06','admin','2017-08-18 16:47:06',3,1),(22,'admin','2017-08-18 16:47:06','admin','2017-08-18 16:47:06',4,1),(23,'admin','2017-08-18 16:47:06','admin','2017-08-18 16:47:06',5,1),(24,'admin','2017-08-18 16:47:06','admin','2017-08-18 16:47:06',6,1),(25,'admin','2017-08-18 16:47:06','admin','2017-08-18 16:47:06',7,1),(26,'admin','2017-08-18 16:47:06','admin','2017-08-18 16:47:06',8,1),(27,'admin','2017-08-22 18:51:02','admin','2017-08-22 18:51:02',1,1),(28,'admin','2017-08-22 18:51:02','admin','2017-08-22 18:51:02',2,1),(29,'admin','2017-08-22 18:51:02','admin','2017-08-22 18:51:02',9,1),(30,'admin','2017-08-22 18:51:22','admin','2017-08-22 18:51:22',1,2),(31,'admin','2017-08-22 18:51:22','admin','2017-08-22 18:51:22',2,2),(32,'admin','2017-08-22 18:51:22','admin','2017-08-22 18:51:22',3,2),(33,'admin','2017-08-22 18:51:22','admin','2017-08-22 18:51:22',4,2),(34,'admin','2017-08-22 18:51:22','admin','2017-08-22 18:51:22',5,2),(35,'admin','2017-08-22 18:51:22','admin','2017-08-22 18:51:22',6,2),(36,'admin','2017-08-22 18:51:22','admin','2017-08-22 18:51:22',7,2),(37,'admin','2017-08-22 18:51:22','admin','2017-08-22 18:51:22',8,2),(38,'admin','2017-08-22 18:51:22','admin','2017-08-22 18:51:22',9,2),(39,'admin','2017-08-22 18:51:27','admin','2017-08-22 18:51:27',1,3),(40,'admin','2017-08-22 18:51:27','admin','2017-08-22 18:51:27',2,3),(41,'admin','2017-08-22 18:51:27','admin','2017-08-22 18:51:27',3,3),(42,'admin','2017-08-22 18:51:27','admin','2017-08-22 18:51:27',4,3),(43,'admin','2017-08-22 18:51:27','admin','2017-08-22 18:51:27',5,3),(44,'admin','2017-08-22 18:51:27','admin','2017-08-22 18:51:27',6,3),(45,'admin','2017-08-22 18:51:27','admin','2017-08-22 18:51:27',7,3),(46,'admin','2017-08-22 18:51:27','admin','2017-08-22 18:51:27',8,3),(47,'admin','2017-08-22 18:51:27','admin','2017-08-22 18:51:27',9,3),(48,'admin','2017-09-13 17:06:34','admin','2017-09-13 17:06:34',10,6),(49,'admin','2017-09-13 17:12:44','admin','2017-09-13 17:12:44',11,6),(50,'admin','2017-09-13 17:12:44','admin','2017-09-13 17:12:44',12,6);

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
  KEY `FKpna2tchew81vroamv1fw6ne91` (`parent_id`),
  CONSTRAINT `FKpna2tchew81vroamv1fw6ne91` FOREIGN KEY (`parent_id`) REFERENCES `sys_operation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

/*Data for the table `sys_operation` */

insert  into `sys_operation`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`cname`,`descript`,`code`,`parent_id`,`prefix_url`) values (1,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','主页','主页','OPERATION_RBAC_INDEX',NULL,'/index'),(2,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','home','home','OPERATION_RBAC_HOME',NULL,'/home'),(3,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','系统元素','系统元素操作','OPERATION_RBAC_SYSELEMENT',NULL,'/rbac/syselement/*'),(4,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','查看列表','','OPERATION_RBAC_SYSELEMENT_LIST',3,'/rbac/syselement/list'),(5,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','全量读取','','OPERATION_RBAC_SYSELEMENT_READEAGER',3,'/rbac/syselement/readEager'),(6,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','数据源读取','','OPERATION_RBAC_SYSELEMENT_READDATASOURCE',3,'/rbac/syselement/readDataSource'),(7,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','新增','','OPERATION_RBAC_SYSELEMENT_CREATE',3,'/rbac/syselement/create'),(8,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','删除','','OPERATION_RBAC_SYSELEMENT_DESTORY',3,'/rbac/syselement/destory'),(9,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','更新','','OPERATION_RBAC_SYSELEMENT_UPDATE',3,'/rbac/syselement/update'),(10,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','系统文件','系统文件操作','OPERATION_RBAC_SYSFILE',NULL,'/rbac/sysfile/*'),(11,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','查看列表','','OPERATION_RBAC_SYSFILE_LIST',10,'/rbac/sysfile/list'),(12,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','全量读取','','OPERATION_RBAC_SYSFILE_READEAGER',10,'/rbac/sysfile/readEager'),(13,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','数据源读取','','OPERATION_RBAC_SYSFILE_READDATASOURCE',10,'/rbac/sysfile/readDataSource'),(14,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','新增','','OPERATION_RBAC_SYSFILE_CREATE',10,'/rbac/sysfile/create'),(15,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','删除','','OPERATION_RBAC_SYSFILE_DESTORY',10,'/rbac/sysfile/destory'),(16,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','更新','','OPERATION_RBAC_SYSFILE_UPDATE',10,'/rbac/sysfile/update'),(17,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','菜单','菜单操作','OPERATION_RBAC_SYSMENU',NULL,'/rbac/sysmenu/*'),(18,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','查看列表','','OPERATION_RBAC_SYSMENU_LIST',17,'/rbac/sysmenu/list'),(19,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','全量读取','','OPERATION_RBAC_SYSMENU_READEAGER',17,'/rbac/sysmenu/readEager'),(20,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','数据源读取','','OPERATION_RBAC_SYSMENU_READDATASOURCE',17,'/rbac/sysmenu/readDataSource'),(21,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','新增','','OPERATION_RBAC_SYSMENU_CREATE',17,'/rbac/sysmenu/create'),(22,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','删除','','OPERATION_RBAC_SYSMENU_DESTORY',17,'/rbac/sysmenu/destory'),(23,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','更新','','OPERATION_RBAC_SYSMENU_UPDATE',17,'/rbac/sysmenu/update'),(24,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','功能操作','功能操作操作','OPERATION_RBAC_SYSOPERATION',NULL,'/rbac/sysoperation/*'),(25,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','查看列表','','OPERATION_RBAC_SYSOPERATION_LIST',24,'/rbac/sysoperation/list'),(26,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','全量读取','','OPERATION_RBAC_SYSOPERATION_READEAGER',24,'/rbac/sysoperation/readEager'),(27,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','数据源读取','','OPERATION_RBAC_SYSOPERATION_READDATASOURCE',24,'/rbac/sysoperation/readDataSource'),(28,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','新增','','OPERATION_RBAC_SYSOPERATION_CREATE',24,'/rbac/sysoperation/create'),(29,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','删除','','OPERATION_RBAC_SYSOPERATION_DESTORY',24,'/rbac/sysoperation/destory'),(30,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','更新','','OPERATION_RBAC_SYSOPERATION_UPDATE',24,'/rbac/sysoperation/update'),(31,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','权限','权限操作','OPERATION_RBAC_SYSPERMISSION',NULL,'/rbac/syspermission/*'),(32,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','查看列表','','OPERATION_RBAC_SYSPERMISSION_LIST',31,'/rbac/syspermission/list'),(33,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','全量读取','','OPERATION_RBAC_SYSPERMISSION_READEAGER',31,'/rbac/syspermission/readEager'),(34,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','数据源读取','','OPERATION_RBAC_SYSPERMISSION_READDATASOURCE',31,'/rbac/syspermission/readDataSource'),(35,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','新增','','OPERATION_RBAC_SYSPERMISSION_CREATE',31,'/rbac/syspermission/create'),(36,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','删除','','OPERATION_RBAC_SYSPERMISSION_DESTORY',31,'/rbac/syspermission/destory'),(37,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','更新','','OPERATION_RBAC_SYSPERMISSION_UPDATE',31,'/rbac/syspermission/update'),(38,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','角色','角色操作','OPERATION_RBAC_SYSROLE',NULL,'/rbac/sysrole/*'),(39,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','查看列表','','OPERATION_RBAC_SYSROLE_LIST',38,'/rbac/sysrole/list'),(40,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','全量读取','','OPERATION_RBAC_SYSROLE_READEAGER',38,'/rbac/sysrole/readEager'),(41,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','数据源读取','','OPERATION_RBAC_SYSROLE_READDATASOURCE',38,'/rbac/sysrole/readDataSource'),(42,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','新增','','OPERATION_RBAC_SYSROLE_CREATE',38,'/rbac/sysrole/create'),(43,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','删除','','OPERATION_RBAC_SYSROLE_DESTORY',38,'/rbac/sysrole/destory'),(44,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','更新','','OPERATION_RBAC_SYSROLE_UPDATE',38,'/rbac/sysrole/update'),(45,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','用户','用户操作','OPERATION_RBAC_SYSUSER',NULL,'/rbac/sysuser/*'),(46,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','查看列表','','OPERATION_RBAC_SYSUSER_LIST',45,'/rbac/sysuser/list'),(47,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','全量读取','','OPERATION_RBAC_SYSUSER_READEAGER',45,'/rbac/sysuser/readEager'),(48,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','数据源读取','','OPERATION_RBAC_SYSUSER_READDATASOURCE',45,'/rbac/sysuser/readDataSource'),(49,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','新增','','OPERATION_RBAC_SYSUSER_CREATE',45,'/rbac/sysuser/create'),(50,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','删除','','OPERATION_RBAC_SYSUSER_DESTORY',45,'/rbac/sysuser/destory'),(51,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','更新','','OPERATION_RBAC_SYSUSER_UPDATE',45,'/rbac/sysuser/update'),(52,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','用户组','用户组操作','OPERATION_RBAC_SYSUSERGROUP',NULL,'/rbac/sysusergroup/*'),(53,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','查看列表','','OPERATION_RBAC_SYSUSERGROUP_LIST',52,'/rbac/sysusergroup/list'),(54,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','全量读取','','OPERATION_RBAC_SYSUSERGROUP_READEAGER',52,'/rbac/sysusergroup/readEager'),(55,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','数据源读取','','OPERATION_RBAC_SYSUSERGROUP_READDATASOURCE',52,'/rbac/sysusergroup/readDataSource'),(56,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','新增','','OPERATION_RBAC_SYSUSERGROUP_CREATE',52,'/rbac/sysusergroup/create'),(57,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','删除','','OPERATION_RBAC_SYSUSERGROUP_DESTORY',52,'/rbac/sysusergroup/destory'),(58,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','更新','','OPERATION_RBAC_SYSUSERGROUP_UPDATE',52,'/rbac/sysusergroup/update'),(59,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','Hi菜单','Hi菜单操作','OPERATION_GENERATE_SYSHELLOMENU',NULL,'/generate/syshellomenu/*'),(60,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','查看列表','','OPERATION_GENERATE_SYSHELLOMENU_LIST',59,'/generate/syshellomenu/list'),(61,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','全量读取','','OPERATION_GENERATE_SYSHELLOMENU_READEAGER',59,'/generate/syshellomenu/readEager'),(62,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','数据源读取','','OPERATION_GENERATE_SYSHELLOMENU_READDATASOURCE',59,'/generate/syshellomenu/readDataSource'),(63,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','新增','','OPERATION_GENERATE_SYSHELLOMENU_CREATE',59,'/generate/syshellomenu/create'),(64,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','删除','','OPERATION_GENERATE_SYSHELLOMENU_DESTORY',59,'/generate/syshellomenu/destory'),(65,'system','2017-08-09 10:03:29','system','2017-08-09 10:03:29','更新','','OPERATION_GENERATE_SYSHELLOMENU_UPDATE',59,'/generate/syshellomenu/update'),(66,'admin','2017-11-23 14:38:45','admin','2017-11-23 14:38:45','test','test','ttt',NULL,'');

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
  KEY `FK3bkhrrfbtxbn4lniyttfd72y5` (`sys_permission_id`),
  CONSTRAINT `FK3bkhrrfbtxbn4lniyttfd72y5` FOREIGN KEY (`sys_permission_id`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `FKnd02hxehl5os2022b6jqyyqdb` FOREIGN KEY (`sys_operation_id`) REFERENCES `sys_operation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=393 DEFAULT CHARSET=utf8;

/*Data for the table `sys_operation_belongto_relation_sys_permission` */

insert  into `sys_operation_belongto_relation_sys_permission`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`sys_operation_id`,`sys_permission_id`) values (66,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',1,2),(67,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',2,2),(68,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',3,2),(69,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',4,2),(70,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',5,2),(71,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',6,2),(72,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',7,2),(73,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',8,2),(74,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',9,2),(75,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',10,2),(76,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',11,2),(77,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',12,2),(78,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',13,2),(79,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',14,2),(80,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',15,2),(81,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',16,2),(82,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',17,2),(83,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',18,2),(84,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',19,2),(85,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',20,2),(86,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',21,2),(87,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',22,2),(88,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',23,2),(89,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',24,2),(90,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',25,2),(91,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',26,2),(92,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',27,2),(93,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',28,2),(94,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',29,2),(95,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',30,2),(96,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',31,2),(97,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',32,2),(98,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',33,2),(99,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',34,2),(100,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',35,2),(101,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',36,2),(102,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',37,2),(103,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',38,2),(104,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',39,2),(105,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',40,2),(106,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',41,2),(107,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',42,2),(108,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',43,2),(109,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',44,2),(110,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',45,2),(111,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',46,2),(112,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',47,2),(113,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',48,2),(114,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',49,2),(115,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',50,2),(116,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',51,2),(117,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',52,2),(118,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',53,2),(119,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',54,2),(120,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',55,2),(121,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',56,2),(122,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',57,2),(123,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',58,2),(124,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',59,2),(125,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',60,2),(126,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',61,2),(127,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',62,2),(128,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',63,2),(129,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',64,2),(130,'admin','2017-08-18 09:50:30','admin','2017-08-18 09:50:30',65,2),(261,'admin','2017-08-18 10:48:30','admin','2017-08-18 10:48:30',1,1),(262,'admin','2017-08-18 10:48:30','admin','2017-08-18 10:48:30',2,1),(263,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',3,1),(264,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',4,1),(265,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',5,1),(266,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',6,1),(267,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',7,1),(268,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',8,1),(269,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',9,1),(270,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',10,1),(271,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',11,1),(272,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',12,1),(273,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',13,1),(274,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',14,1),(275,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',15,1),(276,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',16,1),(277,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',17,1),(278,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',18,1),(279,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',19,1),(280,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',20,1),(281,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',21,1),(282,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',22,1),(283,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',23,1),(284,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',24,1),(285,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',25,1),(286,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',26,1),(287,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',27,1),(288,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',28,1),(289,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',29,1),(290,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',30,1),(291,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',31,1),(292,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',32,1),(293,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',33,1),(294,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',34,1),(295,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',35,1),(296,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',36,1),(297,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',37,1),(298,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',38,1),(299,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',39,1),(300,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',40,1),(301,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',41,1),(302,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',42,1),(303,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',43,1),(304,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',44,1),(305,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',45,1),(306,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',46,1),(307,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',47,1),(308,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',48,1),(309,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',49,1),(310,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',50,1),(311,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',51,1),(312,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',52,1),(313,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',53,1),(314,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',54,1),(315,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',55,1),(316,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',56,1),(317,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',57,1),(318,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',58,1),(319,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',59,1),(320,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',60,1),(321,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',61,1),(322,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',62,1),(323,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',63,1),(324,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',64,1),(325,'admin','2017-08-18 10:48:31','admin','2017-08-18 10:48:31',65,1),(326,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',1,3),(327,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',2,3),(328,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',3,3),(329,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',4,3),(330,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',5,3),(331,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',6,3),(332,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',7,3),(333,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',8,3),(334,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',9,3),(335,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',10,3),(336,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',11,3),(337,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',12,3),(338,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',13,3),(339,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',14,3),(340,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',15,3),(341,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',16,3),(342,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',17,3),(343,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',18,3),(344,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',19,3),(345,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',20,3),(346,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',21,3),(347,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',22,3),(348,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',23,3),(349,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',24,3),(350,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',25,3),(351,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',26,3),(352,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',27,3),(353,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',28,3),(354,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',29,3),(355,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',30,3),(356,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',31,3),(357,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',32,3),(358,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',33,3),(359,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',34,3),(360,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',35,3),(361,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',36,3),(362,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',37,3),(363,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',38,3),(364,'admin','2017-08-29 16:52:49','admin','2017-08-29 16:52:49',39,3),(365,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',40,3),(366,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',41,3),(367,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',42,3),(368,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',43,3),(369,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',44,3),(370,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',45,3),(371,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',46,3),(372,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',47,3),(373,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',48,3),(374,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',49,3),(375,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',50,3),(376,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',51,3),(377,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',52,3),(378,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',53,3),(379,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',54,3),(380,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',55,3),(381,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',56,3),(382,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',57,3),(383,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',58,3),(384,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',59,3),(385,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',60,3),(386,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',61,3),(387,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',62,3),(388,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',63,3),(389,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',64,3),(390,'admin','2017-08-29 16:52:50','admin','2017-08-29 16:52:50',65,3),(392,'admin','2017-08-30 17:48:16','admin','2017-08-30 17:48:16',39,4);

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`cname`,`descript`,`code`) values (1,'system','2017-08-09 10:03:29','admin','2017-08-18 10:50:58','用户组管理','嗖嗖嗖','PERMISSION_RBAC_SYSUSERGROUP'),(2,'admin','2017-08-15 14:56:45','admin','2017-08-15 14:56:45','新闻权限','新闻发布等等..','QX_XW'),(3,'admin','2017-08-16 16:30:10','admin','2017-08-16 16:30:10','商品管理权限','管理系统内商品','QX_SPGL'),(4,'admin','2017-08-29 16:59:56','admin','2017-08-29 16:59:56','敏感权限','','QX_MG'),(5,'admin','2017-08-29 17:50:51','admin','2017-08-29 17:50:51','Kendo基础','kendo框架JS基础权限','QX_KENDOBASE'),(6,'admin','2017-09-13 17:06:24','admin','2017-09-13 17:06:24','微信管理','微信管理相关权限','WX_GL');

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
  KEY `FKjdc0kgo4xauu5c44h4yqrn5n2` (`sys_role_id`),
  CONSTRAINT `FK9udyk2hqj065tijv7wufmums4` FOREIGN KEY (`sys_permission_id`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `FKjdc0kgo4xauu5c44h4yqrn5n2` FOREIGN KEY (`sys_role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `sys_permission_belongto_relation_sys_role` */

insert  into `sys_permission_belongto_relation_sys_role`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`sys_permission_id`,`sys_role_id`) values (1,'system','2017-08-09 10:03:30','system','2017-08-09 10:03:30',1,1),(12,'admin','2017-08-17 12:53:21','admin','2017-08-17 12:53:21',3,3),(15,'admin','2017-08-21 17:26:51','admin','2017-08-21 17:26:51',3,2),(18,'admin','2017-08-30 10:02:22','admin','2017-08-30 10:02:22',5,2),(19,'admin','2017-09-13 17:07:15','admin','2017-09-13 17:07:15',6,5);

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
  KEY `FK3bea05ca9iwbgsf7fdre8ixnk` (`parent_sys_user_group_id`),
  CONSTRAINT `FK3bea05ca9iwbgsf7fdre8ixnk` FOREIGN KEY (`parent_sys_user_group_id`) REFERENCES `sys_user_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`cname`,`descript`,`code`,`inverted`,`parent_sys_user_group_id`) values (1,'system','2017-08-09 10:03:30','admin','2017-08-17 09:56:49','系统管理员j','系统管理者','ROLE_RBAC_ROOT','\0',NULL),(2,'admin','2017-08-17 10:10:59','admin','2017-08-17 10:11:12','Linux管理','操作系统管理','JS_LINUX_GL','\0',1),(3,'admin','2017-08-17 10:12:35','admin','2017-08-17 10:12:35','路由器管理','路由器管理','JS_LYQ_GL','\0',1),(4,'admin','2017-08-29 16:59:21','admin','2017-08-29 16:59:21','实习生','实习生','JS_SXS','',1),(5,'admin','2017-09-13 17:05:47','admin','2017-09-13 17:05:47','微信运维员','运维微信相关','JS_WEIXIN_YW','\0',1);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`cname`,`descript`,`locked`,`password`,`username`) values (1,'system','2017-08-09 10:03:30','admin','2017-08-24 14:01:11','系统管理员','管理员','\0','$2a$10$a3n.dMS.eiaxHH2eFwHWE.4v2Ou.mAtWvVK5YayoFV/woSHpHh00u','admin'),(2,'admin','2018-03-26 13:42:10','admin','2018-03-26 13:42:10','超级管理员','超级权限','\0','','superAdmin');

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
  `sys_user_group_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sys_user_id_union_sys_role_id` (`sys_user_id`,`sys_role_id`),
  KEY `FKangh2v52q14hegmbw611fds0d` (`sys_role_id`),
  KEY `FK9iclx1pj2lon4w1bkou6s51ne` (`sys_user_group_id`),
  CONSTRAINT `FK1lgog56xv3t52npp33495jort` FOREIGN KEY (`sys_user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK9iclx1pj2lon4w1bkou6s51ne` FOREIGN KEY (`sys_user_group_id`) REFERENCES `sys_user_group` (`id`),
  CONSTRAINT `FKangh2v52q14hegmbw611fds0d` FOREIGN KEY (`sys_role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_belongto_relation_sys_role` */

insert  into `sys_user_belongto_relation_sys_role`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`sys_role_id`,`sys_user_id`,`sys_user_group_id`) values (14,'admin','2017-08-24 14:47:33','admin','2017-08-24 14:47:33',2,1,NULL),(15,'admin','2017-08-24 14:47:33','admin','2017-08-24 14:47:33',3,1,NULL),(16,'admin','2017-08-30 10:07:56','admin','2017-08-30 10:07:56',4,1,NULL),(17,'admin','2017-09-13 17:07:48','admin','2017-09-13 17:07:48',5,1,NULL);

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
  KEY `FK7vn7hd9rhkeiwfbvnvj0hi0tu` (`sys_user_group_id`),
  CONSTRAINT `FK7vn7hd9rhkeiwfbvnvj0hi0tu` FOREIGN KEY (`sys_user_group_id`) REFERENCES `sys_user_group` (`id`),
  CONSTRAINT `FKlrs1p4iu3xh5jbfpjangj6nv8` FOREIGN KEY (`sys_user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_belongto_relation_sys_user_group` */

insert  into `sys_user_belongto_relation_sys_user_group`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`sys_user_group_id`,`sys_user_id`) values (5,'admin','2017-08-24 14:46:27','admin','2017-08-24 14:46:27',2,1),(6,'admin','2017-08-24 14:47:25','admin','2017-08-24 14:47:25',1,1),(10,'admin','2017-08-24 15:10:51','admin','2017-08-24 15:10:51',6,1),(11,'admin','2018-03-26 13:42:21','admin','2018-03-26 13:42:21',1,2),(12,'admin','2018-03-26 13:42:21','admin','2018-03-26 13:42:21',2,2);

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
  KEY `FKlr5amfoqrt4q0dsbyfqgkmuu3` (`parent_id`),
  CONSTRAINT `FKlr5amfoqrt4q0dsbyfqgkmuu3` FOREIGN KEY (`parent_id`) REFERENCES `sys_user_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_group` */

insert  into `sys_user_group`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`cname`,`descript`,`code`,`parent_id`) values (1,'admin','2017-08-09 10:23:00','admin','2017-08-09 10:25:06','技术运维','负责运维整个平台','BM_JSYW',NULL),(2,'admin','2017-08-14 13:53:23','admin','2017-08-14 13:53:23','新闻编辑','新闻发布','BM_XWBJ',NULL),(6,'admin','2017-08-24 15:10:40','admin','2017-08-24 15:10:40','asdsad','dsadsa','asdas',NULL);

/*Table structure for table `sys_user_group_can_to_have_relation_sys_permission` */

DROP TABLE IF EXISTS `sys_user_group_can_to_have_relation_sys_permission`;

CREATE TABLE `sys_user_group_can_to_have_relation_sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `sys_permission_id` bigint(20) DEFAULT NULL,
  `sys_user_group_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sys_user_group_id_union_sys_permission_id` (`sys_user_group_id`,`sys_permission_id`),
  KEY `FKawl9wpxbnvo8s97kg08tvxcox` (`sys_permission_id`),
  CONSTRAINT `FK8i5ll7b4lhe42qa5ur6l6vxkq` FOREIGN KEY (`sys_user_group_id`) REFERENCES `sys_user_group` (`id`),
  CONSTRAINT `FKawl9wpxbnvo8s97kg08tvxcox` FOREIGN KEY (`sys_permission_id`) REFERENCES `sys_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_group_can_to_have_relation_sys_permission` */

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
  KEY `FKam1x5g5nieewi2kcn6yswncd8` (`sys_permission_id`),
  CONSTRAINT `FK2yhwiv26tc3yo5tvdgu2x9wic` FOREIGN KEY (`sys_user_group_id`) REFERENCES `sys_user_group` (`id`),
  CONSTRAINT `FKam1x5g5nieewi2kcn6yswncd8` FOREIGN KEY (`sys_permission_id`) REFERENCES `sys_permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_group_canhave_relation_sys_permission` */

insert  into `sys_user_group_canhave_relation_sys_permission`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`sys_permission_id`,`sys_user_group_id`) values (16,'admin','2017-08-16 18:00:05','admin','2017-08-16 18:00:05',3,2),(26,'admin','2017-08-17 09:35:42','admin','2017-08-17 09:35:42',1,2),(31,'admin','2017-08-17 12:53:13','admin','2017-08-17 12:53:13',3,1),(33,'admin','2017-08-29 17:51:03','admin','2017-08-29 17:51:03',5,1),(35,'admin','2017-09-13 17:06:59','admin','2017-09-13 17:06:59',6,1);

/*Table structure for table `weixin_base_config` */

DROP TABLE IF EXISTS `weixin_base_config`;

CREATE TABLE `weixin_base_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `cname` varchar(255) NOT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `appid` varchar(255) DEFAULT NULL,
  `appsecret` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `groupid` int(11) DEFAULT NULL,
  `headimgurl` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `nickname_emoji` varchar(255) DEFAULT NULL,
  `openid` varchar(255) DEFAULT NULL,
  `privilege` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `subscribe` int(11) DEFAULT NULL,
  `subscribe_time` int(11) DEFAULT NULL,
  `tagid_list` int(11) DEFAULT NULL,
  `unionid` varchar(255) DEFAULT NULL,
  `weixin_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g4cpmpw3lcw0s7r3l0d7buhcq` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `weixin_base_config` */

insert  into `weixin_base_config`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`cname`,`descript`,`code`,`appid`,`appsecret`,`token`,`city`,`country`,`groupid`,`headimgurl`,`language`,`nickname`,`nickname_emoji`,`openid`,`privilege`,`province`,`remark`,`sex`,`subscribe`,`subscribe_time`,`tagid_list`,`unionid`,`weixin_code`) values (1,'admin','2017-09-13 17:15:13','admin','2017-09-13 17:15:13','测试号-A','测试用微信号','wx-test-a','wx84f8c874735fddf5','39609ba93ec7c083715b22706d82aa29','laputaweixin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `weixin_user` */

DROP TABLE IF EXISTS `weixin_user`;

CREATE TABLE `weixin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `groupid` int(11) DEFAULT NULL,
  `headimgurl` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `nickname_emoji` varchar(255) DEFAULT NULL,
  `openid` varchar(255) DEFAULT NULL,
  `privilege` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `subscribe` int(11) DEFAULT NULL,
  `subscribe_time` int(11) DEFAULT NULL,
  `tagid_list` varchar(255) DEFAULT NULL,
  `unionid` varchar(255) DEFAULT NULL,
  `weixin_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `weixin_user` */

insert  into `weixin_user`(`id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`,`city`,`country`,`groupid`,`headimgurl`,`language`,`nickname`,`nickname_emoji`,`openid`,`privilege`,`province`,`remark`,`sex`,`subscribe`,`subscribe_time`,`tagid_list`,`unionid`,`weixin_code`) values (2,'system','2017-09-15 17:43:23','system','2017-09-15 17:43:58','深圳','中国',NULL,'http://wx.qlogo.cn/mmopen/vi_32/ld8lGs0YvKMwQYrAOicibhrz1TKJmxicDLmXgDWiaMoNDrpb9IMJ65q8icNQFIOHMwyKgfLu4HZyUSRoeN5aVqic7kpg/0','zh_CN','蒋东平','蒋东平','oGLQWwSSqraq0HDpbd9x5Zb-2tt4','[]','广东',NULL,1,NULL,NULL,'null','ox9IxwHacy8Mb79TuAbJOS2cuPd0','wx-test-a'),(3,'system','2017-09-18 10:50:54','system','2017-09-18 10:50:54',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'null',NULL,NULL,NULL,NULL,NULL,'null',NULL,'wx-test-a');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
