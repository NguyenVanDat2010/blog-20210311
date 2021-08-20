package com.kira.blog.api;

import com.kira.blog.pojo.dto.UpdateUserDTO;
import com.kira.blog.response.ResponseBase;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
public interface UserApi {

    @ApiOperation("Update user by Id")
    @PutMapping("{userUuid}")
    ResponseBase updateUser(@PathVariable("userUuid") String userUuid,
                            @Validated @RequestBody UpdateUserDTO updateUserDTO,
                            @RequestHeader String roleRight);
}
