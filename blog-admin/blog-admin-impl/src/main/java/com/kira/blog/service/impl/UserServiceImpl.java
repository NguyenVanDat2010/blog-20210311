package com.kira.blog.service.impl;

import com.kira.blog.constant.ExceptionEnum;
import com.kira.blog.exception.BizException;
import com.kira.blog.mapper.UserMapper;
import com.kira.blog.pojo.dto.UpdateUserDTO;
import com.kira.blog.pojo.po.UserPO;
import com.kira.blog.pojo.vo.UserVO;
import com.kira.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper userMapper;

    @Override
    public UserPO getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public Integer countUserExists(String email, String username, String phoneNumber) {
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
        logger.info("UserServiceImpl - getUserByUserUuid with userUuid is {} and username is {}", userUuid, username);
        if (userUuid == null && username == null) {
            throw new BizException(ExceptionEnum.USER_PARAM_IS_NULL);
        }
        return userMapper.getUserByUserUuidOrUsername(userUuid, username);
    }

    //check them neu la superAdmin thi se update duoc all status
    @Override
    public void updateUser(UpdateUserDTO updateUserDTO, String roleRight) {
        logger.info("UserServiceImpl - updateUser with userUuid is {}", updateUserDTO.getUserUuid());
//        UserVO userVO = getUserByUserUuidOrUsername(updateUserDTO.getUserUuid(), null);
        UserPO userPO = userMapper.selectByPrimaryKey(updateUserDTO.getUserUuid());
        if (ObjectUtils.isEmpty(userPO)) {
            throw new BizException(ExceptionEnum.USER_NOT_EXIST);
        }
        if ("Suspend".equals(userPO.getUserStatus())) {
            throw new BizException(ExceptionEnum.USER_HAVE_NOT_ACTIVE);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!ObjectUtils.isEmpty(updateUserDTO.getNewPassword()) || !ObjectUtils.isEmpty(updateUserDTO.getConfirmPassword())) {
            if (ObjectUtils.isEmpty(updateUserDTO.getOldPassword()) || !encoder.matches(updateUserDTO.getOldPassword(), userPO.getPassword())) {
                throw new BizException(ExceptionEnum.USER_WRONG_PASSWORD);
            }
            if (ObjectUtils.isEmpty(updateUserDTO.getNewPassword()) || !updateUserDTO.getNewPassword().equals(updateUserDTO.getConfirmPassword())) {
                throw new BizException(ExceptionEnum.USER_PASSWORD_NOT_EQUAL_CONFIRM_PASSWORD);
            }
            userPO.setPassword(encodePassword(updateUserDTO.getNewPassword()));
            userPO.setCfPassword(encodePassword(updateUserDTO.getConfirmPassword()));
            userPO.setOldPassword(userPO.getPassword());
        }

        if (!ObjectUtils.isEmpty(updateUserDTO.getFullName())) {
            userPO.setFullName(updateUserDTO.getFullName());
        }
        if (!ObjectUtils.isEmpty(updateUserDTO.getBirthday())) {
            userPO.setBirthday(updateUserDTO.getBirthday());
        }
        if (!ObjectUtils.isEmpty(updateUserDTO.getPhoneNumber())) {
            userPO.setPhoneNumber(updateUserDTO.getPhoneNumber());
        }
        if (!ObjectUtils.isEmpty(updateUserDTO.getGender())) {
            userPO.setGender(updateUserDTO.getGender());
        }
        if (!ObjectUtils.isEmpty(updateUserDTO.getAvatar())) {
            userPO.setAvatar(updateUserDTO.getAvatar());
        }
        userMapper.updateByPrimaryKeySelective(userPO);
    }

    private boolean isNullOrEmpty(String... strArr) {
        for (String st : strArr) {
            if (st == null || st.equals("")) {
                return true;
            }
        }
        return false;
    }

    private Object setNewObject(Object o) {
        Object newObject = new Object();
        Field[] a = o.getClass().getDeclaredFields();
        Map<String, String> map = new HashMap<>();

        for (Field field : a) {
            try {
                field.setAccessible(true);
                String value = (String) field.get(o);
                if (!ObjectUtils.isEmpty(value)) {
                    map.put(field.getName(), value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            newObject = map;
        }
        return newObject;
    }

    private String encodePassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }
}
