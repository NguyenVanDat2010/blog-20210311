# use blog;

-- ----------------------------
-- Table structure for blog_module
-- ----------------------------
DROP TABLE IF EXISTS `blog`.`blog_module`;
CREATE TABLE `blog`.`blog_module` (
  `module_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'module id',
  `module_code` varchar(10) NOT NULL COMMENT 'module unique code',
  `module_name` varchar(100) NOT NULL COMMENT 'module name',
  `sort` int(11) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT 'system' COMMENT 'creator',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` varchar(50) DEFAULT 'system' COMMENT 'updator',
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`module_id`),
  UNIQUE KEY `module_code_index` (`module_code`),
  UNIQUE KEY `module_name_index` (`module_name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT 'Blog module table';

-- ----------------------------
-- Table structure for blog_role
-- ----------------------------
DROP TABLE IF EXISTS `blog`.`blog_role`;
CREATE TABLE `blog`.`blog_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'user role id',
  `role_name` varchar(50) NOT NULL COMMENT 'role name',
  `role_right` varchar(5) NOT NULL COMMENT 'role right, MC-Macker and Checker flow(As Admin); NA-No need MC flow; SAD-Supper admin',
  `role_status` char(1) DEFAULT '1' NOT NULL COMMENT 'role status: Active, Suspend, Delete',
  `is_edit` char(2) NOT NULL DEFAULT '0' COMMENT 'role is edited? 0: no-edit; 1: edit',
  `created_by` varchar(50) DEFAULT 'system' COMMENT 'created by',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_by` varchar(50) DEFAULT 'system' COMMENT 'update by',
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `user_role_index` (`role_right`,`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT 'Blog role table';

-- ----------------------------
-- Table structure for blog_access_right
-- ----------------------------
DROP TABLE IF EXISTS `blog`.`blog_access_right`;
CREATE TABLE `blog`.`blog_access_right` (
  `module_code` varchar(10) NOT NULL COMMENT 'module code',
  `role_id` int(11) NOT NULL COMMENT 'role id',
  `access_right` varchar(2) NOT NULL DEFAULT 'NA' COMMENT 'access right: NA-No Access; RO-Read Only; RW-Read & Write; MC-Maker or Checker',
  `created_by` varchar(50) DEFAULT 'system' COMMENT 'creator',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` varchar(50) DEFAULT 'system' COMMENT 'updator',
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `right_module_index` (`module_code`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'Blog access right table';

-- ----------------------------
-- Table structure for blog_user
-- ----------------------------
DROP TABLE IF EXISTS `blog`.`blog_user`;
CREATE TABLE `blog`.`blog_user` (
  `user_uuid` varchar(50) NOT NULL COMMENT 'user unique id, auto increment',
  `role_id` int(11) NOT NULL COMMENT 'role id',
  `full_name` varchar(100) NOT NULL COMMENT 'full name of user',
  `email` varchar(100) NOT NULL COMMENT 'email of user',
  `username` varchar(50) NOT NULL COMMENT 'login username',
  `password` varchar(200) NOT NULL COMMENT 'login password',
  `cf_password` varchar(200) NOT NULL COMMENT 'login confirm password',
  `old_password` varchar(200) DEFAULT NULL COMMENT 'login old password',
  `birthday` varchar(100) NOT NULL COMMENT 'birthday of user',
  `phone_number` varchar(100) NOT NULL COMMENT 'phone number of user',
  `gender` varchar(100) NOT NULL COMMENT 'gender of user',
  `avatar` TEXT DEFAULT NULL COMMENT 'avatar of user',
  `user_status` varchar(10) DEFAULT 'Suspend' COMMENT 'user status: Active, Suspend, Delete',
  `is_edit` char(2)  NOT NULL DEFAULT '0' COMMENT 'is edit: 0-not edit 1-edit',
  `is_delete` char(2) NOT NULL DEFAULT '0' COMMENT 'event whether delete. 0: not delete; 1: delete',
  `last_login_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `when_created` varchar(30) DEFAULT '',
  `created_by` varchar(50) DEFAULT 'system' COMMENT 'creator',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_by` varchar(50) DEFAULT 'system' COMMENT 'update user',
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT 'User table';

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog`.`blog_comment`;
CREATE TABLE `blog`.`blog_comment` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'comment id, auto increment',
  `comment_content` text NOT NULL COMMENT 'comment content',
  `user_uuid` varchar(50) NOT NULL COMMENT 'user id of comment',
  `movie_id` bigint(20) NOT NULL COMMENT 'movie id of comment',
  `is_edit` char(2) NOT NULL DEFAULT '0' COMMENT 'is edit: 0-not edit 1-edit',
  `event_id` bigint(20) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT 'system' COMMENT 'created by',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_by` varchar(50) DEFAULT 'system' COMMENT 'update by',
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
