TRUNCATE TABLE `blog`.`blog_error_message`;

-- init error message
INSERT INTO `blog`.`blog_error_message` (`error_code`, `error_message`) VALUES ('ERR_SEC_NEW_LOGIN_ID_NO_ALPHA_AND_NUMERIC', 'Invalid Username format. Your Username must be 8 - 16 characters of alphabets and numbers. (2216)');
INSERT INTO `blog`.`blog_error_message` (`error_code`, `error_message`) VALUES ('ERR_SEC_LOGIN_ID_RESERVED', 'Login ID is Reserved (5966)');
INSERT INTO `blog`.`blog_error_message` (`error_code`, `error_message`) VALUES ('ERR_SEC_LOGIN_ID_TAKEN', 'The Username entered has been taken, Please create another new Username. (2087)');
INSERT INTO `blog`.`blog_error_message` (`error_code`, `error_message`) VALUES ('ERR_SEC_INVALID_LOGIN_ID_LENGTH', 'Your Username must be 8 - 16 characters of alphabets and numbers. (2084)');
INSERT INTO `blog`.`blog_error_message` (`error_code`, `error_message`) VALUES ('ERR_CUST_NOT_FOUND_DB', 'Customer not found. (2050)');
INSERT INTO `blog`.`blog_error_message` (`error_code`, `error_message`) VALUES ('ERR_COM_NO_RECORD_FOUND', 'No Record Found. (2367)');
INSERT INTO `blog`.`blog_error_message` (`error_code`, `error_message`) VALUES ('ERR_COM_CATEGORY_INVALID', 'Category is either missing or invalid (1030)*');
INSERT INTO `blog`.`blog_error_message` (`error_code`, `error_message`) VALUES ('ERR_SEC_FUNCTION_ACTION_NOT_FOUND', 'Sorry, we are unable to process your request at this time. Please try again later. (2185)');
INSERT INTO `blog`.`blog_error_message` (`error_code`, `error_message`) VALUES ('ERR_SEC_AUTHZ_MAX_COUNT_EXCEEDED_FOR_TRANSACTION', 'Sorry, you have been logged out because you have exceeded the maximum limit of invalid TAC. (2077)');
INSERT INTO `blog`.`blog_error_message` (`error_code`, `error_message`) VALUES ('ERR_SEC_AUTHZ_CODE_EXPIRED', 'Your TAC has expired. You may request a new TAC if applicable, or return to the main menu to submit a new request. (2165)');
INSERT INTO `blog`.`blog_error_message` (`error_code`, `error_message`) VALUES ('ERR_SEC_AUTHZ_INVALID_CODE', 'You\'ve entered an invalid TAC. Please ensure you key in the right number. (2076)');
INSERT INTO `blog`.`blog_error_message` (`error_code`, `error_message`) VALUES ('ERR_AUTHZ_STATUS_REPEATED', 'This temporary ID has already been used. Visit the branch or call us to get a new one. (2025)');
INSERT INTO `blog`.`blog_error_message` (`error_code`, `error_message`) VALUES ('ERR_EAAS_VALIDATE_AUTH_CODE_PARAM_INCOMPLETE', 'Sorry, we are unable to process your request at this time. Please try again later. (2185)');
INSERT INTO `blog`.`blog_error_message` (`error_code`, `error_message`) VALUES ('ERR_AUTHZ_INVALID_AUTHZ_INFO', 'Sorry, we are unable to process your request at this time. Please try again later. (2185)');
