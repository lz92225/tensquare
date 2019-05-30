package com.tensquare.user.mapper;

import com.tensquare.user.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    @Select("select * from tb_admin where loginname = #{loginname}")
    Admin findByLoginname(String loginname);
}
