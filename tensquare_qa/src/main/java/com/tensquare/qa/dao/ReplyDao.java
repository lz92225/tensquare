package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
@Mapper
public interface ReplyDao{

    Reply findById(String id);

    void save(Reply reply);

    void update(Reply reply);

    void deleteById(String id);

    List<Reply> findAll();
}
