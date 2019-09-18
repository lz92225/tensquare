package com.tensquare.user.mapper;

import com.tensquare.user.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from tb_user where nickname = #{nickname}")
    User login(String nickname);

    void addUser(User user);

    @Select("select nickname, mobile, birthday from tb_user")
    List<User> findAll();

    @Select("select * from tb_user where nickname = ${nickname}")
    User selectByNickname(String nickname);
}
