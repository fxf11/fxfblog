package com.fxf.blogbs.controller;

import com.fxf.blogbs.dao.SortMapper;
import com.fxf.blogbs.pojo.Sort;
import com.fxf.blogbs.service.SortService;
import com.fxf.blogbs.utils.RegexMatchesUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 博客分类
 */
@RestController
@CrossOrigin
@RequestMapping("sort")
public class SortController {



    @Autowired
    private SortMapper sortMapper;

    @Autowired
    private SortService sortService;

//    根据id查询分类
    @GetMapping("/getSortById/{sortId}")
    public Sort getSortById(@PathVariable int sortId){
        return sortMapper.selectById(sortId);
    }

//   查询分类表中所有数据
    @GetMapping("/allSort")
    public List<Sort> fidAllSort(){
        return sortMapper.selectList(null);
    }
//  分页查询
    @GetMapping("/findByPage/{pageNum}")
    public PageInfo<Sort> findByPage( @PathVariable("pageNum")int pageNum){

        PageHelper.startPage(pageNum, 5);
        List<Sort> list = sortMapper.selectList(null);
        PageInfo<Sort> pageInfo = new PageInfo<Sort>(list);
        return pageInfo;
    }




}
