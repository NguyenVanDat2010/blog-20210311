package com.kira.blog.mapper;

import com.kira.blog.pojo.vo.LoginVO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

@Mapper
public interface LoginMapper {

    LoginVO getUserByUsername(@Param("username") String username);

    void updateLoginTime(@Param("username") String username);

}
