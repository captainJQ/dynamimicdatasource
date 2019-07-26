package com.jql.springboot.dynamicdatasource.controller;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.jql.springboot.dynamicdatasource.entity.User;
import com.jql.springboot.dynamicdatasource.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private LoadingCache<Integer,User> localCache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterAccess(1, TimeUnit.MINUTES)
            //.removalListener(Object::notify)
            .build(new CacheLoader<Integer, User>() {
                @Override
                public User load(Integer key) throws Exception {
                    return userMapper.selectByPrimaryKey(key);
                }
            });

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

    @RequestMapping(value = "/selectUser")
    public User selectUser(Integer id){
        try {
            User user = localCache.get(id);
            return user;
        } catch (ExecutionException e) {
            return null;
        }
    }

}
