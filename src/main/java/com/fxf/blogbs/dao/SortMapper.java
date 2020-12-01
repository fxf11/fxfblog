package com.fxf.blogbs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxf.blogbs.pojo.Sort;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SortMapper extends BaseMapper<Sort> {
    List<Sort> findAllSort();

    int insertSort(Sort sort);



}
