package com.kira.blog.api;

import com.kira.blog.pojo.dto.UpdateUserDTO;
import com.kira.blog.pojo.vo.UserVO;
import com.kira.blog.pojo.vo.UserVO1;
import com.kira.blog.response.ResponseBase;
import com.kira.blog.response.common.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
public interface UserApi {

    /**
     * Quy chuan:
     * username: RequestHeader
     * userUuid: RequestParam
     */

    @ApiOperation("Update user by Id")
    @PutMapping()
    ResponseBase updateUser(@RequestParam("userUuid") String userUuid,
                            @Validated @RequestBody UpdateUserDTO updateUserDTO,
                            @RequestHeader String roleRight);

    @ApiOperation("Delete user by Id")
    @DeleteMapping()
    ResponseBase deleteUser(@RequestParam("userUuid") String userUuid);

    @ApiOperation("Get user by userUuid")
    @GetMapping("{get-user}")
    ResponseBase<UserVO1> getUserByUsername(@RequestHeader("username") String username);

    @ApiOperation("Get list user")
    @GetMapping()
    ResponseBase<Page<UserVO>> getListUsers(
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize,
            @RequestParam(value = "startTime", required = false, defaultValue = "") String startTime,
            @RequestParam(value = "endTime", required = false, defaultValue = "") String endTime
    );

    @ApiOperation("Active user")
    @PutMapping("active")
    ResponseBase activeUserByUsername(@RequestHeader("username") String username);
}
