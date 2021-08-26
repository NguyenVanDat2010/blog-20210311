package com.kira.blog.api;

import com.kira.blog.pojo.dto.UpdateUserDTO;
import com.kira.blog.pojo.vo.UserManagerVO2;
import com.kira.blog.pojo.vo.UserVO1;
import com.kira.blog.response.ResponseBase;
import com.kira.blog.response.common.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
public interface UserApi {

    /**
     * Quy chuan:
     * username: RequestHeader
     * userUuid: RequestParam
     */

    @ApiOperation("Update user by Id")
    @PutMapping("{userUuid}")
    ResponseBase updateUser(@PathVariable("userUuid") String userUuid,
                            @RequestHeader("username") String username,
                            @RequestHeader("roleRight") String roleRight,
                            @Validated @RequestBody UpdateUserDTO updateUserDTO);

    @ApiOperation("Delete user by Id")
    @DeleteMapping("{userUuid}")
    ResponseBase deleteUser(@PathVariable("userUuid") String userUuid,
                            @RequestHeader("username") String username,
                            @RequestHeader("roleRight") String roleRight);

    @ApiOperation("Get user by userUuid")
    @GetMapping("{userUuid}")
    ResponseBase<UserVO1> getUserByUserUuid(@PathVariable("userUuid") String userUuid,
                                            @RequestHeader("username") String username,
                                            @RequestHeader("roleRight") String roleRight);

    @ApiOperation("Get list user")
    @GetMapping()
    ResponseBase<Page<UserManagerVO2>> getListUsers(
            @RequestHeader("roleRight") String roleRight,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize,
            @RequestParam(value = "startTime", required = false, defaultValue = "") String startTime,
            @RequestParam(value = "endTime", required = false, defaultValue = "") String endTime
    );

}
