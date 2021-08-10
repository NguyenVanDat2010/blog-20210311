TRUNCATE TABLE `blog`.`blog_module`;
TRUNCATE TABLE `blog`.`blog_role`;
TRUNCATE TABLE `blog`.`blog_user`;
TRUNCATE TABLE `blog`.`blog_access_right`;

-- init the super user role and default 4 roles
INSERT INTO `blog`.`blog_role` (`role_name`, `role_right`) VALUES ('Super Admin', 'ROLE_SAD');
INSERT INTO `blog`.`blog_role` (`role_name`, `role_right`) VALUES ('Maker or checker as Admin', 'ROLE_MC');
INSERT INTO `blog`.`blog_role` (`role_name`, `role_right`) VALUES ('User', 'ROLE_USER');
INSERT INTO `blog`.`blog_role` (`role_name`, `role_right`) VALUES ('No need', 'ROLE_NA');

-- init the blog_module
INSERT INTO `blog`.`blog_module` (`module_code`, `module_name`, `sort`) VALUES ('ue', 'User events', '1');
INSERT INTO `blog`.`blog_module` (`module_code`, `module_name`, `sort`) VALUES ('rae', 'Role access events', '2');
INSERT INTO `blog`.`blog_module` (`module_code`, `module_name`, `sort`) VALUES ('pe', 'Post events', '3');
INSERT INTO `blog`.`blog_module` (`module_code`, `module_name`, `sort`) VALUES ('cte', 'Category events', '4');
INSERT INTO `blog`.`blog_module` (`module_code`, `module_name`, `sort`) VALUES ('cme', 'Comment events', '5');
INSERT INTO `blog`.`blog_module` (`module_code`, `module_name`, `sort`) VALUES ('tge', 'Tag events', '6');
INSERT INTO `blog`.`blog_module` (`module_code`, `module_name`, `sort`) VALUES ('mte', 'Meta events', '7');

-- init the blog_access_right
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('ue', '1', 'RW');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('rae', '1', 'RW');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('pe', '1', 'RW');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('cte', '1', 'RW');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('cme', '1', 'RW');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('tge', '1', 'RW');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('mte', '1', 'RW');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('ue', '2', 'RW');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('rae', '2', 'RO');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('pe', '2', 'RW');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('cte', '2', 'RW');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('cme', '2', 'RW');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('tge', '2', 'RW');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('mte', '2', 'RW');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('ue', '3', 'NA');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('rae', '3', 'NA');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('pe', '3', 'RO');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('cte', '3', 'RO');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('cme', '3', 'RO');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('tge', '3', 'RO');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('mte', '3', 'RO');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('ue', '4', 'NA');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('rae', '4', 'NA');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('pe', '4', 'NA');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('cte', '4', 'NA');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('cme', '4', 'NA');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('tge', '4', 'NA');
INSERT INTO `blog`.`blog_access_right` (`module_code`, `role_id`, `access_right`) VALUES ('mte', '4', 'NA');

-- init the user //pass: 123456a@  or 123456
INSERT INTO `blog`.`blog_user` (`user_uuid`, `role_id`, `full_name`, `email`, `username`, `password`, `cf_password`, `birthday`, `phone_number`, `gender`) VALUES ('ed2f8903c46d47658d9d9e1c06caeb70', '1', 'Nguyen Van Dat', 'datdhvinh@gmail.com', 'Kira', '$2a$10$XMe4HY4TmmSXJ.Ih8EHt8eb4tTsed6JSow5vCntfSbwRhFaHL7Qn.','$2a$10$JMPP2gHz/khRBcQaNRG4sOq6mFKgFCxTxp7ogDZvyGeIioM3OmUoe', '1995-08-28', '0967838708', 'Male');
INSERT INTO `blog`.`blog_user` (`user_uuid`, `role_id`, `full_name`, `email`, `username`, `password`, `cf_password`, `birthday`, `phone_number`, `gender`) VALUES ('ed2f8903c46d47658d9d9e1c06caeb71', '2', 'Nguyen Manh Thao', 'manhthao@gmail.com', 'NMT', '$2a$10$XMe4HY4TmmSXJ.Ih8EHt8eb4tTsed6JSow5vCntfSbwRhFaHL7Qn.','$2a$10$JMPP2gHz/khRBcQaNRG4sOq6mFKgFCxTxp7ogDZvyGeIioM3OmUoe', '1995-12-28', '0967521121', 'Male');
INSERT INTO `blog`.`blog_user` (`user_uuid`, `role_id`, `full_name`, `email`, `username`, `password`, `cf_password`, `birthday`, `phone_number`, `gender`) VALUES ('ed2f8903c46d47658d9d9e1c06caeb72', '2', 'Tran Xuan Chinh', 'chinhtran@gmail.com', 'CTX', '$2a$10$XMe4HY4TmmSXJ.Ih8EHt8eb4tTsed6JSow5vCntfSbwRhFaHL7Qn.','$2a$10$JMPP2gHz/khRBcQaNRG4sOq6mFKgFCxTxp7ogDZvyGeIioM3OmUoe', '1994-01-16', '0952228708', 'Male');
INSERT INTO `blog`.`blog_user` (`user_uuid`, `role_id`, `full_name`, `email`, `username`, `password`, `cf_password`, `birthday`, `phone_number`, `gender`) VALUES ('ed2f8903c46d47658d9d9e1c06caeb73', '3', 'Nguyen Trong Cuong', 'trongcuong@gmail.com', 'Cuong', '$2a$10$XMe4HY4TmmSXJ.Ih8EHt8eb4tTsed6JSow5vCntfSbwRhFaHL7Qn.','$2a$10$JMPP2gHz/khRBcQaNRG4sOq6mFKgFCxTxp7ogDZvyGeIioM3OmUoe', '1995-06-06', '0985554701', 'Female');
