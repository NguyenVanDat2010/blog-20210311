package com.kira.blog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListDTO {
    private Integer pageNo;

    private Integer pageSize;

    private Integer offset;

    private Long startDay;

    private Long endDay;
}
