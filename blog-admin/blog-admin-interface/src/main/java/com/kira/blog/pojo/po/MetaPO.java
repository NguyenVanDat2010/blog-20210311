package com.kira.blog.pojo.po;

import lombok.Data;

import java.util.Date;

@Data
public class MetaPO {
    private Long metaId;

    private Long postId;

    private String key;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    private String content;

}