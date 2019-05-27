package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
@Mapper
public interface ProblemDao {
    Page<Problem> newlist(String labelid, Pageable pageable);

    Page<Problem> hotlist(String labelid, Pageable pageable);

    Page<Problem> waitlist(String labelid, Pageable pageable);

    List<Problem> findAll();

    Problem findById(String id);

    void save(Problem problem);

    void deleteById(String id);

    void update(Problem problem);

//    @Query(value = "SELECT * FROM tb_problem, tb_pl WHERE id = problemid AND labelid=? ORDER BY replytime DESC", nativeQuery = true)
//    public Page<Problem> newlist(String labelid, Pageable pageable);
//
//    @Query(value = "SELECT * FROM tb_problem, tb_pl WHERE id = problemid AND labelid=? ORDER BY reply DESC", nativeQuery = true)
//    public Page<Problem> hotlist(String labelid, Pageable pageable);
//
//    @Query(value = "SELECT * FROM tb_problem, tb_pl WHERE id = problemid AND labelid=? AND reply=0 ORDER BY createtime DESC", nativeQuery = true)
//    public Page<Problem> waitlist(String labelid, Pageable pageable);
}
