package com.fxf.blogbs.service;

import com.fxf.blogbs.pojo.Blog;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BlogService {

    List<Blog> findAllBlog();

    Blog findBlogById(Integer blogId);

    List<Blog> findBlogByTitle(String blogTitle);

    List<Blog> findBlogBySort(Integer sortId);

    List<Blog> findNewBlog();
}
