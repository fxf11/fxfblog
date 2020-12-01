package com.fxf.blogbs.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxf.blogbs.dao.BlogMapper;
import com.fxf.blogbs.dao.SortMapper;
import com.fxf.blogbs.dao.UserMapper;
import com.fxf.blogbs.pojo.Blog;
import com.fxf.blogbs.pojo.Sort;
import com.fxf.blogbs.pojo.User;
import com.fxf.blogbs.service.BlogService;
import com.fxf.blogbs.service.SortService;
import com.fxf.blogbs.service.UserService;
import com.fxf.blogbs.service.impl.BlogServiceImpl;
import com.fxf.blogbs.utils.JWTUtil;
import com.fxf.blogbs.utils.OSSClientUtil;
import com.fxf.blogbs.utils.RegexMatchesUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BlogService blogService = new BlogServiceImpl();

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private SortMapper sortMapper;

    @Autowired
    private SortService sortService;



    @GetMapping("/login")
    public Map<String,Object> login(@RequestBody User user){
        log.info("用户名：[{}]",user.getUserName());
        log.info("密码：[{}]",user.getUserPassword());
        Map<String,Object> map = new HashMap<>();

        try {
            User user1 = userService.checkUser(user);
            System.out.println("登录");
            Map<String, String> payload = new HashMap<>();
            payload.put("id", String.valueOf(user1.getUserId()));
            payload.put("name",user1.getUserName());
            payload.put("photo",user1.getUserProfilePhoto());
            //生成JWT令牌
            String token = JWTUtil.getToken(payload);
            map.put("state",true);
            map.put("msg","认证成功");
            map.put("token",token);
        }catch (Exception e){
            map.put("state",false);
            map.put("msg",e.getMessage());
        }

        return map;
    }

//    @PostMapping("/test")
//    public Map<String,Object> test(HttpServletRequest request){
//
//        Map<String, Object> map = new HashMap<>();
//        String token = request.getHeader("token");
//        DecodedJWT verify = JWTUtil.verify(token);
//        log.info("用户名：[{}]",verify.getClaim("userName").asString());
//        log.info("用户id：[{}]",verify.getClaim("userId").asString());
//        //处理业务逻辑
//        map.put("state",true);
//        map.put("msg","请求成功");
//        return map;
//    }


//    用户登录
    @PostMapping("/login")
    public Map<String,Object> Login(@RequestBody User user1,RedirectAttributes attributes,HttpSession session){

//        String userName = map.get("userName");
//        String userPassword = map.get("password");

        User user = userService.checkUser(user1);
        System.out.println("登录");
        Map<String, Object> map = new HashMap<>();
        if(user!=null){
            session.setAttribute("user",user);
            Map<String, String> payload = new HashMap<>();
//            前台传递过来的数据
            payload.put("id", String.valueOf(user1.getUserId()));
            payload.put("name",user1.getUserName());
            payload.put("photo",user1.getUserProfilePhoto());
            System.out.println(payload);
            String token = JWTUtil.getToken(payload);
//            数据库查询返回的数据
            log.info("用户id：[{}]",user.getUserId());
            log.info("用户名：[{}]",user.getUserName());
            log.info("密码：[{}]",user.getUserPassword());
            if (token !=null){
                map.put("msg","认证成功");
                map.put("token",token);
                map.put("userId",user.getUserId());
                map.put("userName",user.getUserName());
                map.put("photo",user.getUserProfilePhoto());

                System.out.println(map);
            }
            return map;
        }else {
            attributes.addFlashAttribute("message","用户名和密码错误");
            return map;
        }


    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user"); //退出登录则清除session中的用户信息
        return "success";
    }

    /**
     * 分页查询博客数据
     * @param pageNum
     * @return
     */
    @GetMapping("/blog/findBlogByPage/{pageNum}")
    public PageInfo<Blog> findByPage(@PathVariable("pageNum")int pageNum, HttpServletRequest request){
        String token = request.getHeader("token");
//        System.out.println("headerToken:"+token);
        PageHelper.startPage(pageNum, 5);
        List<Blog> list = blogMapper.queryAllBlog();
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(list);
        return pageInfo;
    }
    /**
     * 后台新增博客
     * @param blog
     * @return
     */
    @PostMapping("/blog/insertBlog")
    public String addBlog(@RequestBody Blog blog){
        if (RegexMatchesUtils.isBlank(blog.getBlogContent()) || RegexMatchesUtils.isBlank(blog.getBlogTitle())){
            return "error";
        }
        if (blog!=null){
            System.out.println("前端表单传递的数据："+blog);
            int i = blogMapper.insert(blog);
            if (i>0){
                System.out.println("博客添加成功");
                return "success";
            }else{
                return "error";
            }
        }else{
            return "null";
        }
    }

    /**
     * 修改博客内容
     * @param blog
     * @return
     */
    @PutMapping("/blog/updateBlog")
    public String updateBlog(@RequestBody Blog blog){
        System.out.println(blog);
        if (blog!=null){
            int i = blogMapper.updateBlog(blog);
            if (i>0){
                return "success";
            }else {
                return "error";
            }
        }else {
            return "error";
        }
    }

    /**
     * 删除博客
     * @param blogId
     */
    @DeleteMapping("/blog/deleteBlog/{blogId}")
    public void deleteBlog(@PathVariable("blogId") int blogId){
        System.out.println("成功删除id为："+blogId+"的博客");
        try {
            blogMapper.deleteById(blogId);
        }catch (Exception e){
            e.printStackTrace();
        }

    }




    @Autowired
    OSSClientUtil ossUtil; //注入OssUtil

    /**
     * 上传图片到阿里云oss
     * @param file
     * @return
     */
    @PostMapping("/blog/uploadfile")
    public Object fileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String name = ossUtil.uploadHomeImageOSS(file); //调用OSS工具类
            //查询上传图片的url地址
            String url = ossUtil.getUrl(name);
            System.out.println(url);
            Map<String, Object> returnbody = new HashMap<>();
            Map<String, Object> returnMap = new HashMap<>();
            returnMap.put("url", url);
            returnbody.put("data", returnMap);
            returnbody.put("code", "200");
            returnbody.put("message", "上传成功");
            return returnbody;
        } catch (Exception e) {
            Map<String, Object> returnbody = new HashMap<>();
            returnbody.put("data", null);
            returnbody.put("code", "400");
            returnbody.put("message", "上传失败");
            return returnbody;
        }


    }
    //  新增分类
    @PostMapping("/sort/insertSort")
    public String insertSort(@RequestBody Sort sort){
        //System.out.println(sort);
        if (RegexMatchesUtils.isBlank(sort.getSortName())){
            return "error";
        }
        if (sort!=null){
            int s = sortMapper.insert(sort);
            if (s>0){
                return "success";
            }else{
                return "error";
            }
        }
        return "null";

    }

    //    删除分类
    @DeleteMapping("/sort/deleteSort/{sortId}")
    public void deleteSort(@PathVariable("sortId") int sortId){
        System.out.println(sortId);
        sortMapper.deleteById(sortId);

    }

    /**
     * 修改分类
     * @param sort
     * @return
     */
    @PutMapping("/sort/updateSort")
    public String updateSort(@RequestBody Sort sort){
        System.out.println(sort);
        if (RegexMatchesUtils.isBlank(sort.getSortName())){
            return "error";
        }
        int sort1 = sortMapper.updateById(sort);
        if (sort1 > 0){
            return "success";
        }else {
            return "error";
        }

    }

}

