-- use blog;

-- ----------------------------
-- Table structure for blog_module
-- ----------------------------
DROP TABLE IF EXISTS `blog`.`blog_module`;
CREATE TABLE `blog`.`blog_module`
(
    `module_id`    int(11) NOT NULL AUTO_INCREMENT COMMENT 'module id',
    `module_code`  varchar(10)  NOT NULL COMMENT 'module unique code',
    `module_name`  varchar(100) NOT NULL COMMENT 'module name',
    `sort`         int(11) DEFAULT NULL,
    `created_by`   varchar(50) DEFAULT 'system' COMMENT 'creator',
    `created_date` datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`   varchar(50) DEFAULT 'system' COMMENT 'updator',
    `updated_date` datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`module_id`),
    UNIQUE KEY `module_code_index` (`module_code`),
    UNIQUE KEY `module_name_index` (`module_name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT 'Blog module table';

-- ----------------------------
-- Table structure for blog_role
-- ----------------------------
DROP TABLE IF EXISTS `blog`.`blog_role`;
CREATE TABLE `blog`.`blog_role`
(
    `role_id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'user role id',
    `role_name`    varchar(50) NOT NULL COMMENT 'role name',
    `role_right`   varchar(5)  NOT NULL DEFAULT 'NA' COMMENT 'role right, MC-Macker and Checker flow(As Admin); NA-No need MC flow; SAD-Supper admin',
    `role_status`  char(1)              DEFAULT '1' NOT NULL COMMENT 'role status: 0-Delete, 1-Exist',
    `is_edit`      char(2)     NOT NULL DEFAULT '0' COMMENT 'role is edited? 0: no-edit; 1: edit',
    `created_by`   varchar(50)          DEFAULT 'system' COMMENT 'created by',
    `created_date` datetime             DEFAULT CURRENT_TIMESTAMP,
    `updated_by`   varchar(50)          DEFAULT 'system' COMMENT 'update by',
    `updated_date` datetime             DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `user_role_index` (`role_right`,`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT 'Blog role table';

-- ----------------------------
-- Table structure for blog_access_right
-- ----------------------------
DROP TABLE IF EXISTS `blog`.`blog_access_right`;
CREATE TABLE `blog`.`blog_access_right`
(
    `module_code`  varchar(10) NOT NULL COMMENT 'module code',
    `role_id`      int(11) NOT NULL COMMENT 'role id',
    `access_right` varchar(2)  NOT NULL DEFAULT 'RO' COMMENT 'access right: NA-No Access; RO-Read Only; RW-Read & Write',
    `created_by`   varchar(50)          DEFAULT 'system' COMMENT 'create by',
    `created_date` datetime             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `updated_by`   varchar(50)          DEFAULT 'system' COMMENT 'update by',
    `updated_date` datetime             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY `right_module_index` (`module_code`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'Blog access right table';

-- ----------------------------
-- Table structure for blog_user
-- ----------------------------
DROP TABLE IF EXISTS `blog`.`blog_user`;
CREATE TABLE `blog`.`blog_user`
(
    `user_uuid`       varchar(50)  NOT NULL COMMENT 'user unique id, auto increment',
    `role_id`         int(11) NOT NULL DEFAULT '4' COMMENT 'role id',
    `full_name`       varchar(100) NOT NULL COMMENT 'full name of user',
    `email`           varchar(100) NOT NULL COMMENT 'email of user',
    `username`        varchar(50)  NOT NULL COMMENT 'login username',
    `password`        varchar(200) NOT NULL COMMENT 'login password',
    `cf_password`     varchar(200) NOT NULL COMMENT 'login confirm password',
    `old_password`    varchar(200)          DEFAULT NULL COMMENT 'login old password',
    `birthday`        varchar(100) NOT NULL COMMENT 'birthday of user',
    `phone_number`    varchar(100) NOT NULL COMMENT 'phone number of user',
    `gender`          varchar(100) NOT NULL COMMENT 'gender of user',
    `avatar`          TEXT                  DEFAULT NULL COMMENT 'avatar of user',
    `user_status`     varchar(10)           DEFAULT 'Suspend' COMMENT 'user status: Active, Suspend, Delete',
    `is_edit`         char(2)      NOT NULL DEFAULT '0' COMMENT 'is edit: 0-not edit 1-edit',
    `is_delete`       char(2)      NOT NULL DEFAULT '0' COMMENT 'event whether delete. 0: not delete; 1: delete',
    `last_login_time` datetime              DEFAULT CURRENT_TIMESTAMP,
    `when_created`    varchar(30)           DEFAULT '',
    `created_by`      varchar(50)           DEFAULT 'system' COMMENT 'creator',
    `created_date`    datetime              DEFAULT CURRENT_TIMESTAMP,
    `updated_by`      varchar(50)           DEFAULT 'system' COMMENT 'update user',
    `updated_date`    datetime              DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT 'User table';

-- ----------------------------
-- Table structure for blog_post
-- ----------------------------
DROP TABLE IF EXISTS `blog`.`blog_post`;
CREATE TABLE `blog`.`blog_post`
(
    `post_id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Post id, auto increment',
    `author_id`        bigint(20) NOT NULL COMMENT 'Author id for per post',
    `parent_id`        varchar(100)          DEFAULT NULL COMMENT 'Parent id, as relationship of post',
    `title`            varchar(200) NOT NULL COMMENT 'Post\' s title ',
    `content`          longtext     NOT NULL COMMENT ' Post\'s content',
    `summary`          tinytext              DEFAULT NULL COMMENT 'Post\' s summary ',
    `post_status`      char(1)      NOT NULL DEFAULT '0' COMMENT ' Post\'s status; Ex: published-1, not publish-0',
    `meta_title`       varchar(200)          DEFAULT NULL COMMENT 'Post\' s meta title (title of web) for SEO ',
    `slug`             varchar(100)          DEFAULT NULL COMMENT ' It\'s an important part of URL for SEO',
    `post_has_article` char(1)      NOT NULL DEFAULT '0' COMMENT 'Post has article; 0-no article, 1-has article',
    `article_title`    varchar(200)          DEFAULT NULL COMMENT 'Article\' s title ',
    `article_content`  LONGTEXT              DEFAULT NULL COMMENT ' Article\'s content',
    `created_by`       varchar(50)           DEFAULT 'system' COMMENT 'created by',
    `created_date`     datetime              DEFAULT CURRENT_TIMESTAMP,
    `updated_by`       varchar(50)           DEFAULT 'system' COMMENT 'update by',
    `updated_date`     datetime              DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`post_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT 'Blog post table';

-- ---------------------------------
-- Table structure for blog_category
-- ---------------------------------
DROP TABLE IF EXISTS `blog`.`blog_category`;
CREATE TABLE `blog`.`blog_category`
(
    `category_id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Category id, auto increment',
    `title`        varchar(200) NOT NULL COMMENT 'Category\' s title ',
    `meta_title`   tinytext     DEFAULT NULL COMMENT ' Category\'s meta title (title of web) for SEO',
    `slug`         varchar(100) DEFAULT NULL COMMENT 'It\' s an important part of URL for SEO ',
    `content`      longtext     NOT NULL COMMENT ' Category\'s content',
    `created_by`   varchar(50)  DEFAULT 'system' COMMENT 'created by',
    `created_date` datetime     DEFAULT CURRENT_TIMESTAMP,
    `updated_by`   varchar(50)  DEFAULT 'system' COMMENT 'update by',
    `updated_date` datetime     DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`category_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT 'Blog category table';

-- --------------------------------------
-- Table structure for blog_post_category
-- --------------------------------------
DROP TABLE IF EXISTS `blog`.`blog_post_category`;
CREATE TABLE `blog`.`blog_post_category`
(
    `post_id`      bigint(20) NOT NULL COMMENT 'Post id, auto increment',
    `category_id`  bigint(20) NOT NULL COMMENT 'Category id, auto increment',
    `created_by`   varchar(50) DEFAULT 'system' COMMENT 'created by',
    `created_date` datetime    DEFAULT CURRENT_TIMESTAMP,
    `updated_by`   varchar(50) DEFAULT 'system' COMMENT 'update by',
    `updated_date` datetime    DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`post_id`, `category_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT 'Blog post category table';

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog`.`blog_tag`;
CREATE TABLE `blog`.`blog_tag`
(
    `tag_id`       bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Tag id, auto increment',
    `post_id`      bigint(20) NOT NULL COMMENT 'Post id, auto increment',
    `title`        varchar(200) NOT NULL COMMENT 'Tag\' s title ',
    `meta_title`   tinytext     DEFAULT NULL COMMENT ' Tag\'s meta title (title of web) for SEO',
    `slug`         varchar(100) DEFAULT NULL COMMENT 'It\' s an important part of URL for SEO ',
    `content`      longtext     NOT NULL COMMENT ' Tag\'s content',
    `created_by`   varchar(50)  DEFAULT 'system' COMMENT 'created by',
    `created_date` datetime     DEFAULT CURRENT_TIMESTAMP,
    `updated_by`   varchar(50)  DEFAULT 'system' COMMENT 'update by',
    `updated_date` datetime     DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`tag_id`),
    UNIQUE KEY `post_id` (`post_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT 'Blog category table';

-- --------------------------------
-- Table structure for blog_comment
-- --------------------------------
DROP TABLE IF EXISTS `blog`.`blog_comment`;
CREATE TABLE `blog`.`blog_comment`
(
    `comment_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'comment id, auto increment',
    `post_id`        bigint(20) NOT NULL COMMENT 'Post id, auto increment',
    `parent_id`      bigint(20) DEFAULT NULL COMMENT 'Parent id of comment for reply comment',
    `content`        text    NOT NULL COMMENT 'Comment content',
    `comment_status` char(1) NOT NULL DEFAULT '0' COMMENT '0-not published; 1-published',
    `created_by`     varchar(50)      DEFAULT 'system' COMMENT 'created by',
    `created_date`   datetime         DEFAULT CURRENT_TIMESTAMP,
    `updated_by`     varchar(50)      DEFAULT 'system' COMMENT 'update by',
    `updated_date`   datetime         DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`comment_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- -----------------------------
-- Table structure for blog_meta
-- -----------------------------
DROP TABLE IF EXISTS `blog`.`blog_meta`;
CREATE TABLE `blog`.`blog_meta`
(
    `meta_id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'comment id, auto increment',
    `post_id`      bigint(20) NOT NULL COMMENT 'Post id, auto increment',
    `key`          varchar(100) NOT NULL COMMENT 'Key of post',
    `content`      text         NOT NULL COMMENT 'Content of key',
    `created_by`   varchar(50) DEFAULT 'system' COMMENT 'created by',
    `created_date` datetime    DEFAULT CURRENT_TIMESTAMP,
    `updated_by`   varchar(50) DEFAULT 'system' COMMENT 'update by',
    `updated_date` datetime    DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`meta_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- ---------------------------------------
-- Table structure for blog_user_event_log
-- ---------------------------------------
DROP TABLE IF EXISTS `blog`.`blog_user_event_log`;
CREATE TABLE `blog`.`blog_user_event_log`
(
    `log_id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_uuid`     varchar(50)      DEFAULT NULL COMMENT 'id user',
    `post_id`       bigint(20) NOT NULL COMMENT 'Post id, auto increment',
    `event_type`    char(2) NOT NULL DEFAULT '00' COMMENT '00-Others, 01-Successful, 02-Failed, 03-OTP error, 04-Checking error, 05-RAMCI Litigation error, 06-IRL error, 07-AML error, 08-Login/Logout, 09-Web service call',
    `event_name`    text    NOT NULL COMMENT 'event name',
    `details`       text COMMENT 'Event details',
    `event_session` char(2)          DEFAULT '01' COMMENT '01-session current, 02-session past',
    `created_by`    varchar(45)      DEFAULT 'system' COMMENT 'created by',
    `updated_by`    varchar(45)      DEFAULT 'system' COMMENT 'updated by',
    `created_date`  datetime         DEFAULT CURRENT_TIMESTAMP COMMENT 'created date',
    `updated_date`  datetime         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Updated date',
    PRIMARY KEY (`log_id`),
    KEY             `blog_user_event_log_movie_id_index` (`user_uuid`, `post_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='Blog User event logs';

-- ----------------------------
-- Table structure for blog_error_message
-- ----------------------------
-- DROP TABLE IF EXISTS `blog`.`blog_error_message`;
-- CREATE TABLE `blog`.`blog_error_message`
-- (
--     `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'unique id',
--     `error_code`    varchar(150) NOT NULL DEFAULT 'rib.Demo' COMMENT 'RIB error code',
--     `error_message` varchar(255)          DEFAULT 'Undified' COMMENT 'RIB error message',
--     `lang_code`     varchar(10)           DEFAULT 'en' COMMENT 'error message language code, default is ''en''',
--     `is_edit`       char(2)      NOT NULL DEFAULT '0' COMMENT 'is edit. 0-not edit, 1-edit',
--     `event_id`      bigint(20) DEFAULT NULL COMMENT 'event id',
--     `created_by`    varchar(100)          DEFAULT 'system' COMMENT 'created by',
--     `created_date`  datetime              DEFAULT CURRENT_TIMESTAMP,
--     `updated_by`    varchar(100)          DEFAULT 'system' COMMENT 'update by',
--     `updated_date`  datetime              DEFAULT CURRENT_TIMESTAMP,
--     PRIMARY KEY (`id`),
--     UNIQUE KEY `index_error_code` (`error_code`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;