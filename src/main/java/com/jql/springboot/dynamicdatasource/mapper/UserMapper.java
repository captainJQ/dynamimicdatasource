package com.jql.springboot.dynamicdatasource.mapper;

import com.jql.springboot.dynamicdatasource.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into user (id,name,age) values(#{id},#{name},#{age})")
    Integer insert(User user);
}
