package com.fxf.blogbs.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    @TableId
    private int blogId;
    private Integer userId;
    private String blogTitle;
    private String blogContent;
    private String blogPhoto;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date blogDate;
    private Integer sortId;

    private Sort sort;
    private User user;

}
