package com.tensquare.base.mapper;

import com.tensquare.base.pojo.Lable;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LableMapper {
    @Select("select * from tb_label")
    @Transactional(propagation = Propagation.REQUIRED)
    List<Lable> findAll();

    @Select("select * from tb_label where id = ${id}")
    Lable findById(@Param("id") String lableId);

    @Insert("insert into tb_label(id,labelname,state,count,recommend,fans) values(#{id},#{labelname},#{state},#{count},#{recommend},#{fans})")
    void save(Lable lable);

    @Delete({"delete from tb_label where id=#{id}"})
    void deleteById(@Param("id") String lableId);

    void update1(Lable lable);

    List<Lable> searchPage2(Lable lable);

    List<Lable> searchPage(Lable lable);

    @Insert("insert into tb_label(labelname,state,count,recommend,fans) values(#{labelname},#{state},#{count},#{recommend},#{fans})")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void save2(Lable lable);

    void batchAdd(List<Lable> list);
}
