package com.tensquare.mybatistest.mapper;

import com.tensquare.mybatistest.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MtMapper {


    public Book getById(String id);
}
