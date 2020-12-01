package com.fxf.blogbs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxf.blogbs.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

    List<Blog> queryAllBlog();

    Blog findBlogById(Integer blogId);

    List<Blog> findBlogByTitle(String BlogTitle);

    List<Blog> findBlogBySort(Integer sortId);

    List<Blog> findNewBlog();

    int updateBlog(Blog blog);


}

