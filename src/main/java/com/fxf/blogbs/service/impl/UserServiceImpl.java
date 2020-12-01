package com.fxf.blogbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fxf.blogbs.dao.UserMapper;
import com.fxf.blogbs.pojo.User;
import com.fxf.blogbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User checkUser(User user) {
        Map<String, String> map = new HashMap<>();
        map.put("user_Name",user.getUserName());
        map.put("user_Password",user.getUserPassword());
        User user1 = userMapper.selectOne(new QueryWrapper<User>().allEq(map));
        if(user1 != null){
            return user1;
        }
        throw new RuntimeException("登陆失败");
    }
}
