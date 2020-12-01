package com.fxf.blogbs.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxf.blogbs.dao.SortMapper;
import com.fxf.blogbs.pojo.Sort;
import com.fxf.blogbs.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortServiceImpl implements SortService {

    @Autowired
    private SortMapper sortMapper;

    public void setBookMapper(SortMapper sortMapper) {
        this.sortMapper = sortMapper;
    }

    @Override
    public List<Sort> findAllSort() {
        List<Sort> list = sortMapper.findAllSort();
        return list;
    }

    @Override
    public int insertSort(Sort sort) {
        return 0;
    }


}
