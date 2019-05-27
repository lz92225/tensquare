package com.tensquare.base.mapper;

import com.tensquare.base.pojo.Lable;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LableMapper {
    @Select("select * from tb_label")
    List<Lable> findAll();

    @Select("select * from tb_label where id = #{id}")
    Lable findById(@Param("id") String lableId);

    @Insert("insert into tb_label(id,labelname,state,count,recommend,fans) values(#{id},#{labelname},#{state},#{count},#{recommend},#{fans})")
    void save(Lable lable);

    @Delete("delete from tb_label where id=#{id}")
    void deleteById(@Param("id") String lableId);

    void update1(Lable lable);
}
