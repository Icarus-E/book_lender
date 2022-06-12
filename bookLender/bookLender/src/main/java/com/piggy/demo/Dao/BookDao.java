package com.piggy.demo.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piggy.demo.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BookDao extends BaseMapper<Book> {
}
