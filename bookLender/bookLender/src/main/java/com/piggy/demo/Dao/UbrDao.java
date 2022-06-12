package com.piggy.demo.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piggy.demo.domain.Book;
import com.piggy.demo.domain.UserBookRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UbrDao extends BaseMapper<UserBookRelation> {
    @Select("select b.* from tbl_book b,user_merge_book ub where ub.user_id = #{id} and b.id = ub.book_id;")
    List<Book> selectBooksByUserId(Integer id);
}
