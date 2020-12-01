package com.fxf.blogbs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxf.blogbs.pojo.Sort;

import java.util.List;

public interface SortService {

    List<Sort> findAllSort();

    int insertSort(Sort sort);



}
