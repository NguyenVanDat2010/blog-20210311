
-- init the super user role and default 4 roles
INSERT INTO `blog`.`blog_role` (`role_id`, `role_code`, `role_name`, `role_right`, `role_status`) VALUES ('1', 'SUPER_ADMIN', 'Super Admin', 'NA', '1');
INSERT INTO `blog`.`blog_role` (`role_id`, `role_code`, `role_name`, `role_right`, `role_status`) VALUES ('2', 'ADMIN', 'Admin', 'MC', '1');
INSERT INTO `blog`.`blog_role` (`role_id`, `role_code`, `role_name`, `role_right`, `role_status`) VALUES ('3', 'USER_VIEW', 'View or read only', 'MC', '1');
INSERT INTO `blog`.`blog_role` (`role_id`, `role_code`, `role_name`, `role_right`, `role_status`) VALUES ('4', 'USER_EDIT', 'Edit or update', 'MC', '1');
INSERT INTO `blog`.`blog_role` (`role_id`, `role_code`, `role_name`, `role_right`, `role_status`) VALUES ('5', 'USER_CREATE', 'Create new record', 'MC', '1');

-- init the blog_role_detail
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('1', 'Create post', 'CREATE', '1', '1');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('2', 'Edit post', 'EDIT', '1', '1');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('3', 'Delete post', 'DELETE', '1', '1');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('4', 'View post', 'VIEW', '1', '1');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('5', 'Create post', 'CREATE', '2', '1');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('6', 'Edit post', 'EDIT', '2', '1');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('7', 'Delete post', 'DELETE', '2', '0');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('8', 'View post', 'VIEW', '2', '1');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('9', 'Create post', 'CREATE', '3', '0');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('10', 'Edit post', 'EDIT', '3', '0');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('11', 'Delete post', 'DELETE', '3', '0');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('12', 'View post', 'VIEW', '3', '1');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('13', 'Create post', 'CREATE', '4', '0');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('14', 'Edit post', 'EDIT', '4', '1');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('15', 'Delete post', 'DELETE', '4', '0');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('16', 'View post', 'VIEW', '4', '1');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('17', 'Create post', 'CREATE', '5', '1');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('18', 'Edit post', 'EDIT', '5', '0');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('19', 'Delete post', 'DELETE', '5', '0');
INSERT INTO `blog`.`blog_role_detail` (`role_detail_id`, `action_name`, `action_code`, `role_id`, `action_status`) VALUES ('20', 'View post', 'VIEW', '5', '1');

-- init the super user //pass: 123456
INSERT INTO `blog`.`blog_user` (user_uuid, full_name, email, username, password, cf_password, birthday, phone_number, gender) VALUES ('ed2f8903c46d47658d9d9e1c06caeb70', 'Nguyen Van Dat', 'datdhvinh@gmail.com', 'Kira', '$2a$10$XMe4HY4TmmSXJ.Ih8EHt8eb4tTsed6JSow5vCntfSbwRhFaHL7Qn.','$2a$10$JMPP2gHz/khRBcQaNRG4sOq6mFKgFCxTxp7ogDZvyGeIioM3OmUoe', '1995-08-28', '0967838708', 'Male');
INSERT INTO `blog`.`blog_user` (user_uuid, full_name, email, username, password, cf_password, birthday, phone_number, gender) VALUES ('ed2f8903c46d47658d9d9e1c06caeb71', 'Nguyen Manh Thao', 'manhthao@gmail.com', 'NMT', '$2a$10$XMe4HY4TmmSXJ.Ih8EHt8eb4tTsed6JSow5vCntfSbwRhFaHL7Qn.','$2a$10$JMPP2gHz/khRBcQaNRG4sOq6mFKgFCxTxp7ogDZvyGeIioM3OmUoe', '1995-12-28', '0967521121', 'Male');
INSERT INTO `blog`.`blog_user` (user_uuid, full_name, email, username, password, cf_password, birthday, phone_number, gender) VALUES ('ed2f8903c46d47658d9d9e1c06caeb72', 'Tran Xuan Chinh', 'chinhtran@gmail.com', 'CTX', '$2a$10$XMe4HY4TmmSXJ.Ih8EHt8eb4tTsed6JSow5vCntfSbwRhFaHL7Qn.','$2a$10$JMPP2gHz/khRBcQaNRG4sOq6mFKgFCxTxp7ogDZvyGeIioM3OmUoe', '1994-01-16', '0952228708', 'Male');
INSERT INTO `blog`.`blog_user` (user_uuid, full_name, email, username, password, cf_password, birthday, phone_number, gender) VALUES ('ed2f8903c46d47658d9d9e1c06caeb73', 'Nguyen Trong Cuong', 'trongcuong@gmail.com', 'Cuong', '$2a$10$XMe4HY4TmmSXJ.Ih8EHt8eb4tTsed6JSow5vCntfSbwRhFaHL7Qn.','$2a$10$JMPP2gHz/khRBcQaNRG4sOq6mFKgFCxTxp7ogDZvyGeIioM3OmUoe', '1995-06-06', '0985554701', 'Male');

-- init the blog_user_role
INSERT INTO `blog`.`blog_user_role` (user_role_id, user_uuid, role_id, licensed) VALUES ('1', 'ed2f8903c46d47658d9d9e1c06caeb70', '1', '1');
INSERT INTO `blog`.`blog_user_role` (user_role_id, user_uuid, role_id, licensed) VALUES ('2', 'ed2f8903c46d47658d9d9e1c06caeb70', '2', '1');
INSERT INTO `blog`.`blog_user_role` (user_role_id, user_uuid, role_id, licensed) VALUES ('3', 'ed2f8903c46d47658d9d9e1c06caeb71', '3', '1');
INSERT INTO `blog`.`blog_user_role` (user_role_id, user_uuid, role_id, licensed) VALUES ('4', 'ed2f8903c46d47658d9d9e1c06caeb72', '4', '1');
INSERT INTO `blog`.`blog_user_role` (user_role_id, user_uuid, role_id, licensed) VALUES ('5', 'ed2f8903c46d47658d9d9e1c06caeb73', '5', '1');
