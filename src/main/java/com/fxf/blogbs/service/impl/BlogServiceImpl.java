package com.fxf.blogbs.service.impl;

import com.fxf.blogbs.dao.BlogMapper;
import com.fxf.blogbs.pojo.Blog;
import com.fxf.blogbs.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    public void setBlogMapper(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    @Override
    public List<Blog> findAllBlog() {
        return blogMapper.queryAllBlog();
    }

    @Override
    public Blog findBlogById(Integer blogId) {
        if (blogId!=null){
            Blog blog = blogMapper.findBlogById(blogId);
            return blog;
        }
        return null;
    }

    @Override
    public List<Blog> findBlogByTitle(String blogTitle) {
        List<Blog> blogBySortAndTitle = blogMapper.findBlogByTitle(blogTitle);
        return blogBySortAndTitle;
    }

    @Override
    public List<Blog> findBlogBySort(Integer sortId) {
        if (sortId!=null){
            List<Blog> blogBySort = blogMapper.findBlogBySort(sortId);
            return blogBySort;
        }
        return null;
    }

    @Override
    public List<Blog> findNewBlog() {
        List<Blog> newBlog = blogMapper.findNewBlog();
        return newBlog;
    }


}
