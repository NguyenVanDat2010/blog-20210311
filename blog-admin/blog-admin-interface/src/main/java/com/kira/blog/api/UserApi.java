package com.kira.blog.api;

import com.kira.blog.pojo.dto.UpdateUserDTO;
import com.kira.blog.pojo.vo.UserVO;
import com.kira.blog.response.ResponseBase;
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
    @GetMapping("{userUuid}")
    ResponseBase<UserVO> getUserByUserUuid(@PathVariable("userUuid") String userUuid);

    @ApiOperation("Get list user")
    @GetMapping()
    ResponseBase<List<UserVO>> getListUsers();
}
