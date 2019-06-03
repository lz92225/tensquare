package com.tensquare.qa.mapper;

import com.tensquare.qa.pojo.Problem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProblemMapper {

    @Insert("insert into tb_problem(id, title, content) values(#{id}, #{title}, #{content})")
    void addProblem(Problem problem);

    @Select("select * from tb_problem")
    List<Problem> findAll();
}
