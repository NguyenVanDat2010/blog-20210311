package com.kira.blog.response.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {

    private Integer pageNO;

    private Integer pageSize;

    private Integer total;

    private List<T> list;

}
