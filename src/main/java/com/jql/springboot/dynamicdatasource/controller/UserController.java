package com.jql.springboot.dynamicdatasource.controller;

import com.jql.springboot.dynamicdatasource.entity.User;
import com.jql.springboot.dynamicdatasource.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/addUser1")
    public String addUser1(User user){
        return  userMapper.insert(user)+"";
    }

    @RequestMapping(value = "/addUser2")
    public String addUser2(String name,Integer age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        System.out.println(userMapper);
        return  userMapper.insert(user)+"";
    }

}
