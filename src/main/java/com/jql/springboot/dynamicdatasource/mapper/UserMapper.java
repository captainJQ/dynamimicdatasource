package com.jql.springboot.dynamicdatasource.mapper;

import com.jql.springboot.dynamicdatasource.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user (id,name,age) values(#{id},#{name},#{age})")
    Integer insert(User user);
    @Select("select * from user where id = #{id}")
    User selectByPrimaryKey(Integer id);
}
