package com.fxf.blogbs.controller;

import com.fxf.blogbs.dao.BlogMapper;
import com.fxf.blogbs.pojo.Blog;
import com.fxf.blogbs.pojo.Sort;
import com.fxf.blogbs.service.BlogService;
import com.fxf.blogbs.service.impl.BlogServiceImpl;
import com.fxf.blogbs.utils.OSSClientUtil;
import com.fxf.blogbs.utils.RegexMatchesUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private BlogService blogService = new BlogServiceImpl();

    @Autowired
    private BlogMapper blogMapper;



    /**
     * 查询博客内容及作者等相关数据
     * @return
     */
    @GetMapping("/queryAllBlog")
    public List<Blog> queryAllBlog(){

        return blogService.findAllBlog();
    }

    /**
     * 分页查询博客文章
     * @param pageNum
     * @return
     */
    @GetMapping("/queryBlogByPage/{pageNum}")
    public PageInfo<Blog> queryBlogByPage(@PathVariable("pageNum")int pageNum){
        PageHelper.startPage(pageNum, 5);
        List<Blog> list = blogService.findAllBlog();
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(list);
        return pageInfo;
    }

    /**
     * 根据分类查询博客
     * @param sortId
     * @return
     */
    @GetMapping("/findBlogBySort/{sortId}")
    public List<Blog> findBlogBySort(@PathVariable("sortId") Integer sortId){
        List<Blog> blogBySort = blogService.findBlogBySort(sortId);
        System.out.println(blogBySort);
        return blogBySort;

    }

    /**
     * 查询最近的5篇博客
     * @return
     */
    @GetMapping("/findNewBlog")
    public List<Blog> findNewBlog(){
        List<Blog> newBlog = blogService.findNewBlog();
        return newBlog;
    }

    @GetMapping("/findBlogByPage/{pageNum}")
    public PageInfo<Blog> findByPage(@PathVariable("pageNum")int pageNum, HttpServletRequest request){
        String token = request.getHeader("token");
//        System.out.println("headerToken:"+token);
        PageHelper.startPage(pageNum, 5);
        List<Blog> list = blogMapper.queryAllBlog();
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(list);
        return pageInfo;
    }





    /**
     * 根据博客id查询博客
     * @param blogId
     * @return
     */
    @GetMapping("/findBlogById/{blogId}")
    public Blog findBlogById(@PathVariable("blogId") Integer blogId){
        Blog blogById = blogService.findBlogById(blogId);
        return blogById;
    }

    @GetMapping("/findBlogByTitle/{blogTitle}")
    public List<Blog> findBlogBySortAndTitle(@PathVariable("blogTitle") String blogTitle){
        System.out.println(blogTitle);
        return blogService.findBlogByTitle(blogTitle);
    }


}
