package com.kira.blog.service.impl;

import com.kira.blog.constant.ExceptionEnum;
import com.kira.blog.constant.GlobalConst;
import com.kira.blog.constant.UserConst;
import com.kira.blog.exception.BizException;
import com.kira.blog.mapper.UserMapper;
import com.kira.blog.pojo.dto.UpdateUserDTO;
import com.kira.blog.pojo.dto.UserListDTO;
import com.kira.blog.pojo.po.UserPO;
import com.kira.blog.pojo.vo.UserManagerVO2;
import com.kira.blog.pojo.vo.UserVO;
import com.kira.blog.response.common.Page;
import com.kira.blog.service.LoginService;
import com.kira.blog.service.UserService;
import com.kira.blog.utils.RoleValidateUtil;
import com.kira.blog.utils.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author datnv33
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private LoginService loginService;

    @Override
    public UserPO getUserByUsername(String username) {
        logger.info("UserServiceImpl - getUserByUsername with username is {}", username);
        return userMapper.getUserByUsername(username);
    }

    @Override
    public int countUserExists(String email, String username, String phoneNumber) {
        logger.info("UserServiceImpl - countUserExists by email-{}, username-{}, phone number-{}", email, username, phoneNumber);
        return userMapper.countUserExists(email, username, phoneNumber);
    }

    @Override
    public int saveUser(UserPO record) {
        logger.info("UserServiceImpl - saveUser with userPO is {}", record);
        return userMapper.insertSelective(record);
    }

    @Override
    public UserVO getUserByUserUuidOrUsername(String userUuid, String username) {
        logger.info("UserServiceImpl - getUserByUserUuidOrUsername with userUuid is {} and username is {}", userUuid, username);
        if (userUuid == null && username == null) {
            throw new BizException(ExceptionEnum.USER_PARAM_IS_NULL);
        }
        return userMapper.getUserByUserUuidOrUsername(userUuid, username);
    }

    @Override
    public UserPO getUserByUserUuid(String userUuid) {
        logger.info("UserServiceImpl - getUserByUserUuid with userUuid is {}", userUuid);
        if (userUuid == null) {
            throw new BizException(ExceptionEnum.VALIDATE_ERROR);
        }
        UserPO userPO = userMapper.selectByPrimaryKey(userUuid);
        if (!ObjectUtils.isEmpty(userPO)) {
            return userPO;
        }
        throw new BizException(ExceptionEnum.USER_NOT_EXIST);
    }

    //check them neu la superAdmin thi se update duoc all status
    @Override
    public void updateUser(UpdateUserDTO updateUserDTO, String username) {
        logger.info("UserServiceImpl - updateUser with userUuid is {}", updateUserDTO.getUserUuid());
        UserPO userPO = userMapper.selectByPrimaryKey(updateUserDTO.getUserUuid());
        if (ObjectUtils.isEmpty(userPO)) {
            throw new BizException(ExceptionEnum.USER_NOT_EXIST);
        }
        if (UserConst.USER_STATUS_SUSPEND.equals(userPO.getUserStatus())) {
            throw new BizException(ExceptionEnum.USER_HAVE_NOT_ACTIVE);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!StringUtils.isEmpty(updateUserDTO.getNewPassword()) || !StringUtils.isEmpty(updateUserDTO.getConfirmPassword())) {
            if (StringUtils.isEmpty(updateUserDTO.getOldPassword()) || !encoder.matches(updateUserDTO.getOldPassword(), userPO.getPassword())) {
                throw new BizException(ExceptionEnum.USER_WRONG_PASSWORD);
            }
            if (StringUtils.isEmpty(updateUserDTO.getNewPassword()) || !updateUserDTO.getNewPassword().equals(updateUserDTO.getConfirmPassword())) {
                throw new BizException(ExceptionEnum.USER_PASSWORD_NOT_EQUAL_CONFIRM_PASSWORD);
            }
            userPO.setPassword(encodePassword(updateUserDTO.getNewPassword()));
            userPO.setCfPassword(encodePassword(updateUserDTO.getConfirmPassword()));
            userPO.setOldPassword(userPO.getPassword());
        }

        if (!StringUtils.isEmpty(updateUserDTO.getFullName())) {
            userPO.setFullName(updateUserDTO.getFullName());
        }
        if (!StringUtils.isEmpty(updateUserDTO.getBirthday())) {
            userPO.setBirthday(updateUserDTO.getBirthday());
        }
        if (!StringUtils.isEmpty(updateUserDTO.getPhoneNumber())) {
            userPO.setPhoneNumber(updateUserDTO.getPhoneNumber());
        }
        if (!StringUtils.isEmpty(updateUserDTO.getGender())) {
            userPO.setGender(updateUserDTO.getGender());
        }
        if (!StringUtils.isEmpty(updateUserDTO.getAvatar())) {
            userPO.setAvatar(updateUserDTO.getAvatar());
        }
        if (!StringUtils.isEmpty(username)) {
            userPO.setUpdatedBy(username);
        }
        userMapper.updateByPrimaryKeySelective(userPO);
        logger.info("Update user successfully!");
    }

    @Override
    public void deleteUserByUserUuid(String userUuid, String username) {
        logger.info("UserServiceImpl - deleteUserByUserUuid with userUuid is {}", userUuid);
        UserPO userPO = userMapper.selectByPrimaryKey(userUuid);
        loginService.logout(userPO.getUsername(), null);
        userMapper.deleteUserByUserUuid(userUuid, username);
        logger.info("Delete user successfully!");
    }

//    @Override
//    public List<UserVO> getListUsers(UserListDTO userListDTO) {
//        logger.info("UserServiceImpl - getListUsers");
//        return userMapper.getListUsers(userListDTO);
//    }

    @Override
    public Page<UserManagerVO2> listUsers(String roleRight, String pageNo, String pageSize, String startTime, String endTime) {
        logger.info("UserServiceImpl - listUsers, pageNo={}, pageSize={}, startTime={}, endTime={}", pageNo, pageSize, startTime, endTime);
        try {
            // 1. compare roleRight if need to do the maker-checker flow.
            boolean withCheck = RoleValidateUtil.roleNeedCheck(roleRight);
            if (!withCheck) {
                throw new BizException(ExceptionEnum.USER_HAVE_NO_PERMISSION);
            }
            Long startDay;
            Long endDay;
            if (StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)) {
                startDay = TimeUtil.getTimestamp(false);
                endDay = TimeUtil.getTimestamp(true);
            } else {
                startDay = TimeUtil.month2Timestamp(startTime, "start");
                endDay = TimeUtil.month2Timestamp(endTime, "end");
            }
            int pn = Integer.parseInt(pageNo);
            int ps = Integer.parseInt(pageSize);
            UserListDTO userListDTO = new UserListDTO(pn, ps, (pn - 1) * ps, startDay, endDay);
            Integer total = userMapper.countUsers(userListDTO);
            if (total == 0) {
                return new Page<>(GlobalConst.DEFAULT_PAGE_NO, GlobalConst.DEFAULT_PAGE_SIZE, 0, null);
            }
            List<UserManagerVO2> appList = userMapper.getListUsers(userListDTO);
            logger.info("UserServiceImpl - Get list user successfully!");
            return new Page<>(userListDTO.getPageNo(), userListDTO.getPageSize(), total, appList);
        } catch (NumberFormatException ex) {
            logger.error("Get list application error, {}", ex.getMessage());
            throw new BizException(ExceptionEnum.USER_GET_LIST_WRONG_PARAM);
        }
    }

//    private boolean isNullOrEmpty(String... strArr) {
//        for (String st : strArr) {
//            if (st == null || "".equals(st)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private Object setNewObject(Object o) {
//        Object newObject = new Object();
//        Field[] a = o.getClass().getDeclaredFields();
//        Map<String, String> map = new HashMap<>();
//
//        for (Field field : a) {
//            try {
//                field.setAccessible(true);
//                String value = (String) field.get(o);
//                if (!ObjectUtils.isEmpty(value)) {
//                    map.put(field.getName(), value);
//                }
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            newObject = map;
//        }
//        return newObject;
//    }

    private String encodePassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }
}
