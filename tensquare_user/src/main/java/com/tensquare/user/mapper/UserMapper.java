package com.tensquare.user.mapper;

import com.tensquare.user.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from tb_user where nickname = #{nickname}")
    User login(String nickname);

    void addUser(User user);
}
