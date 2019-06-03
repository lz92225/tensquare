package com.tensquare.user.mapper;

import com.tensquare.user.pojo.Admin;
import com.tensquare.user.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

public interface AdminMapper {

    @Select("select * from tb_admin where loginname = #{loginname}")
    Admin findByLoginname(String loginname);

    @Insert("insert into tb_admin(id ,loginname,password,state) values(#{id},#{loginname},#{password},#{state})")
    void regist(Admin admin);
}
