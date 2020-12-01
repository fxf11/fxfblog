package com.fxf.blogbs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxf.blogbs.pojo.User;

public interface UserMapper extends BaseMapper<User> {


    public User findByUserNameAndPassword(User user);
}
